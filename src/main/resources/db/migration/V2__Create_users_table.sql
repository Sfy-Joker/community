

create table users
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