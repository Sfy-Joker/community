package com.hong.cummunity.mapper;

import com.hong.cummunity.dto.QuestionDTO;
import com.hong.cummunity.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void addQuestion(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> getQuestions(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> getQuestionsById(@Param("creator") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator = #{creator}")
    Integer countById(@Param("creator") Integer userId);

    @Select("select * from question where id = #{id}")
    Question findQuestionById(@Param("id") Integer id);

    @Update("update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    void update(Question question);

    @Update("update question set view_count = #{viewCount} + 1 where id = #{id}")
    void updateViewCount(Question question);

    @Update("update question set comment_count = #{commentCount} + 1 where id = #{id}")
    void addCommentCount(QuestionDTO question);
}
