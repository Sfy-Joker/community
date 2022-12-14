package com.hong.cummunity.mapper;

import com.hong.cummunity.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into users (account_id, name, token, gmt_create, gmt_modified, avatar_url) values( #{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void addUser(User user);

    @Select("select * from users where token = #{token}")
    User findByToken(@Param("token") String token);


    @Select("select * from users where id = #{id}")
    User findById(@Param("id") Integer creator);

    @Select("select * from users where account_id = #{account_id}")
    User findByAccount(@Param("account_id") String account_id);

    @Update("update users set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where id = #{id}")
    void updateUser(User user);
}
