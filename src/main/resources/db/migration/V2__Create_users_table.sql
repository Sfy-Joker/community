

create table users
(
    ID           int auto_increment,
    ACCOUNT_ID   CHARACTER(50),
    NAME         CHARACTER(50),
    TOKEN        CHARACTER(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_T_PK
        primary key (ID)
);