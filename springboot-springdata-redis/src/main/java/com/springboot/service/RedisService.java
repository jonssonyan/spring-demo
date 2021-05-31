package com.springboot.service;

import com.springboot.entity.RedisKey;
import lombok.SneakyThrows;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Description: 设值
     *
     * @param key     缓存 {@link RedisKey}
     * @param value   值
     * @param seconds 有效时长 (秒)
     * @return T 放入缓存中的值
     */
    public <T> T setValue(RedisKey key, T value, long seconds) {
        redisTemplate.opsForValue().set(key.of(), value, seconds, TimeUnit.SECONDS);
        return value;
    }

    private Object getValue(String key) {
        if (!Optional.ofNullable(redisTemplate.hasKey(key)).orElse(Boolean.FALSE)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * Description: 取值
     *
     * @param key   缓存 {@link RedisKey}
     * @param clazz 缓存对应的对象的 class 对象
     * @return T or null
     * @see RedisService#getValue(String)
     */
    public <T> T getValue(RedisKey key, Class<T> clazz) {
        return clazz.cast(getValue(key.of()));
    }

    /**
     * Description: 删除
     *
     * @param key 缓存 {@link RedisKey}
     * @return boolean
     */
    public boolean delete(RedisKey key) {
        return Optional.ofNullable(redisTemplate.delete(key.of())).orElse(false);
    }

    /**
     * Description: 延长指定 key 的过期时间
     *
     * @param key     {@link RedisKey}
     * @param seconds 有效时长 (秒)
     * @return boolean
     */
    public boolean expire(RedisKey key, long seconds) {
        return Optional.ofNullable(redisTemplate.expire(key.of(), seconds, TimeUnit.SECONDS)).orElse(false);
    }

    public class Files {

        private static final String CACHE_KEY_PREFIX = "file:";

        private static final String FIELD_FILE_NAME = "fileName";

        private static final String FIELD_FILE_CONTENT = "fileContent";

        /**
         * Description: 缓存文件<br>
         * Details: 将文件对象读入内存, 获取字节数组, 最后 {@code Base64} 编码. 以 缓存前缀 + 文件名 作为缓存 key
         *
         * @param file (Required) 文件对象
         */
        @SneakyThrows
        public void setFile(File file) {
            final HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

            final String fileName = Objects.requireNonNull(file, "文件不能为空").getName();
            final String fileContent = new String(
                    Base64.getEncoder().encode(IOUtils.toByteArray(FileUtils.openInputStream(Objects.requireNonNull(file, "文件对象不能为空")))),
                    StandardCharsets.UTF_8
            );

            final HashMap<String, String> map = new HashMap<>(2);
            map.put(FIELD_FILE_NAME, fileName);
            map.put(FIELD_FILE_CONTENT, fileContent);
            ops.putAll(CACHE_KEY_PREFIX + fileName, map);
        }

        /**
         * Description: 获取文件
         *
         * @param fileName (Required) 文件名
         * @return java.io.File 文件对象
         */
        @SneakyThrows
        public File getFile(String fileName) {
            final HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

            // 缓存 Key
            final Map<Object, Object> entries = ops.entries(CACHE_KEY_PREFIX + Objects.requireNonNull(fileName, "文件名不能为空"));
            if (MapUtils.isEmpty(entries)) {
                return null;
            }

            final String cachedFileName = MapUtils.getString(entries, FIELD_FILE_NAME);
            final String cachedFileContent = MapUtils.getString(entries, FIELD_FILE_CONTENT);

            final File file = new File(FileUtils.getTempDirectoryPath() + cachedFileName);
            try (
                    final FileOutputStream out = new FileOutputStream(file);
                    final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out)
            ) {
                bufferedOutputStream.write(Base64.getDecoder().decode(cachedFileContent));
            }
            return file;
        }

        /**
         * Description: 获取文件的字节数组
         *
         * @param fileName (Required) 文件名
         * @return byte[] 文件的字节数组
         */
        public byte[] getBytes(String fileName) {
            final HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

            final Map<Object, Object> entries = ops.entries(CACHE_KEY_PREFIX + Objects.requireNonNull(fileName, "文件名不能为空"));
            return Base64.getDecoder().decode(MapUtils.getString(entries, FIELD_FILE_CONTENT));
        }
    }
}