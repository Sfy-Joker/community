package com.hong.cummunity.mapper;

import com.hong.cummunity.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment (parent_id,type,content,gmt_create,gmt_modified,like_count,commentator) values(#{parentId},#{type},#{content},#{gmtCreate},#{gmtModified},#{likeCount},#{commentator})")
    void createComment(Comment comment);

    @Select("select count(1) from comment where parent_id = #{parentId}")
    Integer findCommentCount(@Param("parentId") Integer parentId);
}
