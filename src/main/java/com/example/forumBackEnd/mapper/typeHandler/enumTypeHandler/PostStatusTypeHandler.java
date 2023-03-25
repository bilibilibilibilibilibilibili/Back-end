package com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler;

import com.example.forumBackEnd.pojo.enumClass.PostStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@MappedTypes(value = PostStatus.class)
public class PostStatusTypeHandler implements TypeHandler<PostStatus> {
    private PostStatus[] enums = PostStatus.values();

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, PostStatus postStatus, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            preparedStatement.setString(i, Objects.toString(postStatus.getValue()));
        } else {
            preparedStatement.setObject(i, postStatus.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public PostStatus getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        if (!resultSet.wasNull()) {
            try {
                return getPostEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public PostStatus getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        if (!resultSet.wasNull()) {
            try {
                return getPostEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public PostStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        if (!callableStatement.wasNull()) {
            try {
                return getPostEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private PostStatus getPostEnum(String value) throws IOException {
         return Stream.of(enums)
                 .filter(i -> i.getValue().equals(value))
                 .findAny()
                 .orElseThrow(() -> new IllegalArgumentException("未知的枚举类型：" + value + ",请核对PostStatus"));
    }
}
