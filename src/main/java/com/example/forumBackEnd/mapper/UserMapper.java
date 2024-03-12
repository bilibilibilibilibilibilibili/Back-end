package com.example.forumBackEnd.mapper;

import com.example.forumBackEnd.mapper.typeHandler.ListTypeHandler;
import com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler.IdentityTypeHandler;
import com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler.SexTypeHandler;
import com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler.UserStatusTypeHandler;
import com.example.forumBackEnd.pojo.Collection;
import com.example.forumBackEnd.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Insert("INSERT INTO user_table( email, password, userName, salt, confirmCode, activateTime, status, sex," +
            "fans, follow, banTag, post, identity )" +
    "VALUES ( #{email}, #{password}, #{userName}, #{salt}, #{confirmCode}, #{activateTime}, #{status}, #{sex}," +
            "#{fans}, #{follow}, #{banTag}, #{post}, #{identity})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM user_table WHERE id=#{id}")
    @ResultMap(value = "UserMap")
    User selectUserById(@Param("id") int id);

    /**
     * 根据id修改状态值为NORMAL（可用）
     * @param userId
     * @return
     */
    @Update("UPDATE user_table SET status='NORMAL' WHERE id=#{id} AND status!='CANCEL' ")
    int activateUserById(@Param("id") String userId);

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    @Select("SELECT * FROM user_table WHERE email = #{email} AND status != 'cancel' LIMIT 1")
    @Results(id="UserMap", value = {
            @Result(property = "id", column = "id", id=true, jdbcType = JdbcType.INTEGER),
            @Result(property = "password", column = "password", jdbcType = JdbcType.VARCHAR),
            @Result(property = "phone", column = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(property = "email", column = "email", jdbcType = JdbcType.VARCHAR),
            @Result(property = "idNumber", column = "idNumber", jdbcType = JdbcType.VARCHAR),
            @Result(property = "identity", column = "identity", typeHandler = IdentityTypeHandler.class),
            @Result(property = "userName", column = "userName", jdbcType = JdbcType.VARCHAR),
            @Result(property = "bandage", column = "bandage", jdbcType = JdbcType.VARCHAR),
            @Result(property = "salt", column = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(property = "status", column = "status", typeHandler = UserStatusTypeHandler.class),
            @Result(property = "sex", column = "sex", typeHandler = SexTypeHandler.class),
            @Result(property = "registerTime", column = "registerTime", jdbcType = JdbcType.TIME),
            @Result(property = "studentNumber", column = "studentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(property = "grade", column = "grade", jdbcType = JdbcType.VARCHAR),
            @Result(property = "birthday", column = "birthday", jdbcType = JdbcType.TIME),
            @Result(property = "banTag", column = "banTag", typeHandler = ListTypeHandler.class),
            @Result(property = "follow", column = "follow", typeHandler = ListTypeHandler.class),
            @Result(property = "fans", column = "fans", typeHandler = ListTypeHandler.class),
            @Result(property = "confirmCode", column = "confirmCode", jdbcType = JdbcType.VARCHAR),
            @Result(property = "activateTime", column = "activateTime", jdbcType = JdbcType.TIME),
    })
    User selectUserByEmail(@Param("email") String email); //在集合中根据邮箱寻找账号

    @Select("SELECT password FROM user WHERE id = #{id} AND is_valid = 1 ")
    String selectPasswordById(@Param("id")int id);

    @Select("SELECT id,activateTime FROM user_table WHERE confirmCode=#{confirmCode}")
    @ResultMap(value = "UserMap")
    User selectUserByConfirmCode(@Param("confirmCode") String confirmCode);

    @Insert("Insert INTO collection_table ( userId, postId, `time`, `category`) VALUES ( #{userId}, #{postId}, #{time}, #{category})")
    int addCollection(Collection collection);

    @Delete("DELETE FROM collection_table WHERE userId = #{userId} AND postId = #{postId}")
    int deleteCollection(Collection collection);

}
