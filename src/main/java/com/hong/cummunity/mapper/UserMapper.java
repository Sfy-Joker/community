package com.hong.cummunity.mapper;

import com.hong.cummunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into users (account_id, name, token, gmt_create, gmt_modified) values( #{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified})")
    void addUser(User user);
}
