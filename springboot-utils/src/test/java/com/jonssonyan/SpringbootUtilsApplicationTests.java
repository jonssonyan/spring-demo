package com.jonssonyan;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jonssonyan.mapper.AccountConverter;
import com.jonssonyan.module.A;
import com.jonssonyan.module.Account;
import com.jonssonyan.module.BagOfPrimitives;
import com.jonssonyan.module.City;
import com.jonssonyan.module.Model;
import com.jonssonyan.module.User;
import com.jonssonyan.module.vo.AccountVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringbootUtilsApplicationTests {

    /**
     * fastjson测试类: 序列化一个对象成JSON字符串
     */
    @Test
    void fastjsonTest1() {
        User user = new User();
        user.setName("测试");
        user.setAge(6);
        user.setSalary(new BigDecimal("123456789.0123"));
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);
        // 输出: {"age":6,"name":"测试","salary":123456789.0123}
    }

    /**
     * fastjson测试类: 反序列化一个JSON字符串成Java对象
     */
    @Test
    void fastjsonTest2() {
        String jsonString = "{\"age\":3,\"birthdate\":1496738822842,\"name\":\"测试\",\"old\":true,\"salary\":123456789.0123}";
        User u = JSON.parseObject(jsonString, User.class);
        System.out.println(u.getName());
        // 输出: 测试

        String jsonStringArray = "[{\"age\":3,\"birthdate\":1496738822842,\"name\":\"测试\",\"old\":true,\"salary\":123456789.0123}]";
        List<User> userList = JSON.parseArray(jsonStringArray, User.class);
        System.out.println(userList.size());
        // 输出: 1
    }

    /**
     * fastjson测试类: 对于日期的处理
     */
    @Test
    void fastjsonTest3() {
        JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        JSON.defaultLocale = Locale.US;
        Model model = new Model();
        model.setDate(new Date());
        model.setDate2(new Date());
        System.out.println(JSON.toJSONString(model));
        // 输出: {"date":"Dec 22, 2021 11:11:36 AM","date2":"Dec-22-2021 11:11:36 AM"}
    }

    /**
     * fastjson测试类: 设置字段名/设置是否不序列化某字段/设置是否不反序列化某字段/设置字段顺序/Bean和数组转换
     */
    @Test
    void fastjsonTest4() {
        A a = new A();
        a.setId(1);
        a.setDate(new Date());
        a.setDate2(new Date());
        User user = new User();
        user.setName("测试");
        user.setAge(6);
        a.setUserList(Collections.singletonList(user));
        System.out.println(JSON.toJSONString(a));
        // 输出: {"date2":1640143716838,"ID":1,"userList":[{"age":6,"name":"测试"}]}
    }

    /**
     * jackson测试类: 序列化一个对象成JSON字符串
     */
    @Test
    void jacksonTest1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        City case1 = new City();
        case1.setCity("上海");
        case1.setAge(1997);
        String jsonStr = mapper.writeValueAsString(case1);
        System.out.println(jsonStr);
        // 输出: {"city":"上海","age":1997}
    }

    /**
     * jackson测试类: 反序列化一个JSON字符串成Java对象
     */
    @Test
    void jacksonTest2() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 如果里面有未知属性，比如json中有desc字段，但是City中没有相应字段，会报错, 需要设置如下
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String inputJsonStr = "{\"city\":\"上海\",\"age\":1997,\"desc\":\"描述\"}";
        City readCase = mapper.readValue(inputJsonStr, City.class);
        System.out.println(readCase);
        // 输出: City(city=上海, age=1997)
    }

    /**
     * gson测试类: 序列化 基础类型/对象/数组/集合
     */
    @Test
    void gsonTest1() {
        // 基础类型
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));
        // 输出: 1
        System.out.println(gson.toJson("abcd"));
        // 输出: "abcd"
        System.out.println(gson.toJson(10L));
        // 输出: 10
        int[] values = {1};
        System.out.println(gson.toJson(values));
        // 输出: [1]

        // 对象
        BagOfPrimitives bagOfPrimitives = new BagOfPrimitives();
        System.out.println(gson.toJson(bagOfPrimitives));
        // 输出: {"value1":1,"value2":"abc"}

        // 数组
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};
        System.out.println(gson.toJson(ints));
        // 输出: [1,2,3,4,5]
        System.out.println(gson.toJson(strings));
        // 输出: ["abc","def","ghi"]

        // 集合
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(gson.toJson(integers));
        // 输出: [1,2,3,4,5]
    }

    /**
     * gson测试类: 反序列化
     */
    @Test
    void gsonTest2() {
        // 基础类型
        Gson gson = new Gson();
        System.out.println(gson.fromJson("1", int.class));
        // 输出: 1
        System.out.println(gson.fromJson("1", Integer.class));
        // 输出: 1
        System.out.println(gson.fromJson("1", Long.class));
        // 输出: 1
        System.out.println(gson.fromJson("false", Boolean.class));
        // 输出: false
        System.out.println(gson.fromJson("\"abc\"", String.class));
        // 输出: abc

        // 对象
        System.out.println(gson.fromJson("{\"value1\":1,\"value2\":\"abc\"}", BagOfPrimitives.class));
        // 输出: BagOfPrimitives(value1=1, value2=abc, value3=3)

        // 数组
        System.out.println(Arrays.toString(gson.fromJson("[1,2,3,4,5]", int[].class)));
        // 输出: [1, 2, 3, 4, 5]

        // 集合
        System.out.println(gson.fromJson("[\"abc\"]", List.class));
        // 输出: [abc]
    }

    /**
     * mapstruct测试类:
     */
    @Test
    void mapstructTest1() {
        Account account = new Account()
                .setId(1L)
                .setUsername("zhangsan")
                .setSex(1)
                .setPassword("abc123")
                .setCreateTime(LocalDateTime.now())
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig("[{\"field1\":\"Test Field1\",\"field2\":500}]");

        AccountVo accountVo = AccountConverter.INSTANCE.do2vo(account);

        assertNotNull(accountVo);
        assertEquals(accountVo.getId(), account.getId());

        System.out.println(account);
        System.out.println(accountVo);
    }

    /**
     * mapstruct测试类:
     */
    @Test
    void mapstructTest2() {
        AccountVo.UserConfig userConfig = new AccountVo.UserConfig();
        userConfig.setField1("Test Field1");
        userConfig.setField2(500);

        AccountVo accountVo = new AccountVo()
                .setId(1L)
                .setUsername("zhangsan")
                .setGender(2)
                .setCreateTime("2020-01-18 15:32:54")
                .setBirthday(LocalDate.of(1999, 9, 27))
                .setConfig(Collections.singletonList(userConfig));
        Account account = AccountConverter.INSTANCE.vo2Do(accountVo);

        assertNotNull(accountVo);
        assertEquals(accountVo.getId(), account.getId());

        System.out.println(account);
        System.out.println(accountVo);
    }
}
