package com.hong.cummunity.mapper;

import com.hong.cummunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount})")
    void addQuestion(Question question);

    @Select("select title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count from question limit #{offset}, #{size}")
    List<Question> getQuestions(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count from question limit #{offset}, #{size}")
    List<Question> getQuestionsById(@Param("creator") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator = #{creator}")
    Integer countById(@Param("creator") Integer userId);

}
