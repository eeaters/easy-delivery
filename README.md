# easy-delivery Project

想学quarkus + 需要写论文 , 那么就一遍学习quarkus一遍写一个论文系统吧

## 项目环境
环境:

- JDK 18 (大于11)
- Maven 3.8.6 (大于3.8.1)

在项目根目录下执行命令
```shell script
./mvnw compile quarkus:dev
```

jar启动: 
```shell script
mvn clean package -U -Dquarkus.package.type=uber-jar
```
然后再target下java -jar 启动项目

## 后台功能
- 维护三方账户

## 前台功能
- 维护配送策略
- 维护门店
- 运单列表

