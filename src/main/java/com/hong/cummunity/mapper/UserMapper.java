package com.hong.cummunity.mapper;

import com.hong.cummunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into users (account_id, name, token, gmt_create, gmt_modified) values( #{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified})")
    void addUser(User user);

    @Select("select * from users where token = #{token}")
    User findByToken(@Param("token") String token);
}
