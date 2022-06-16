# easy-delivery Project

想学quarkus + 需要写论文 , 那么就一遍学习quarkus一遍写一个论文系统吧

## 项目主要依赖

| 依赖                                           | 功能      |
|----------------------------------------------|---------|
| quarkus-resteasy-reactive-qute               | view层页面 |
| quarkus-resteasy-reactive                    | 展示层     |
| quarkus-rest-client-reactive                 | 三方交互    |
| quarkus-hibernate-reactive-rest-data-panache | 持久化层    |
| quarkus-reactive-mysql-client                | 数据库交互   |
## 项目本地启动
环境:

- JDK 18 (大于11)
- Maven 3.8.6 (大于3.8.1)

在项目根目录下执行命令
```shell script
./mvnw compile quarkus:dev
```
