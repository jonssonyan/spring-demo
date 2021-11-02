# spring-boot-dubbo

#### 介绍
一个springboot集成dubbo的实例，包含common工程（提供接口），consumer工程（消费者），provider工程（提供者），sql（数据库文件）
使用zookeeper进行注册

#### 框架/技术
1. springboot
2. mybatis-plus
3. dubbo
4. zookeeper 3.4.14
5. fastjson

### 项目启动顺序
1. 先启动zookeeper
2. 在启动provider工程
3. 最后启动consumer工程

### 参考文章
1. Dubbo Spring Boot 工程 GitHub主页
https://github.com/apache/dubbo-spring-boot-project/blob/master/README_CN.md
2. Dubbo 官网
http://dubbo.apache.org/
3. Zookeeper 官网
https://zookeeper.apache.org/
4. dubbo-admin GitHub主页
https://github.com/apache/dubbo-admin
5. https://blog.csdn.net/wangzibai/article/details/100106261
6. https://blog.csdn.net/qq_31748587/article/details/84878167

### 端口相关
1. dubbo-admin-server 端口8080
2. consumer 端口9093
3. provider 端口9094
4. zookeeper 端口2181（如果要修改配置文件在\conf\zoo.cfg，记得将consumer/provider中的端口也一并修改）