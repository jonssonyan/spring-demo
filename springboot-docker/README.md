# Dockerfile

```dockerfile
# 基础镜像
FROM java:8

# 作者信息
MAINTAINER "Jonsson Yan"

# 暴露8080端口
EXPOSE 8080

# 添加变量，如果使用dockerfile-maven-plugin，则会自动替换这里的变量内容
ARG JAR_FILE=target/*.jar

# 复制jar包到容器中
COPY ${JAR_FILE} app.jar

# 启动镜像自动运行程序
ENTRYPOINT ["java","-jar","/springboot-docker.jar"]
```

# 手动打包

```shell
# 前往 Dockerfile 目录，打开命令行执行
docker build -t springboot-docker .
# 查看生成镜像
docker images
# 运行
docker run -d -p 8080:8080 --name springboot-docker-demo springboot-docker
# 查看启动日志
docker logs -n 300 -f springboot-docker-demo
```

# Reference
- Docker 官方文档：https://docs.docker.com/
- Dockerfile 命令 参考文档：https://docs.docker.com/engine/reference/builder/
- Maven插件使用 参考地址：https://github.com/spotify/dockerfile-maven
- Spring Boot with Docker:https://spring.io/guides/gs/spring-boot-docker/