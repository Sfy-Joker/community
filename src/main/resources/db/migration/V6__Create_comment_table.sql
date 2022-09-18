create table comment
(
    id bigint auto_increment,
    parent_id int not null,
    type int,
    content varchar(1024),
    gmt_create bigint,
    gmt_modified bigint,
    like_count bigint default 0,
    constraint COMMENT_PK
        primary key (id)
);

