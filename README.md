# 工程简介



# 延伸阅读

# 资料
[GitHub-OAuth](https://docs.github.com/cn/developers/apps/building-github-apps/identifying-and-authorizing-users-for-github-apps)

[OKHttp](https://square.github.io/okhttp/)

[H2](http://h2database.com/html/main.html)

[Spring Boot Mybatis](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

[flyway](https://flywaydb.org/documentation/)

```sql
-- auto-generated definition
create table USERS
(
    ID           INTEGER auto_increment,
    ACCOUNT_ID   CHARACTER,
    NAME         CHARACTER,
    TOKEN        CHARACTER(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_T_PK
        primary key (ID)
);


```