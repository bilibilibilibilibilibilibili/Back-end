package com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler;

import com.example.forumBackEnd.pojo.enumClass.TagStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Stream;

public class TagStatusTypeHandler implements TypeHandler<TagStatus> {
    private TagStatus[] enums = TagStatus.values();

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, TagStatus tagStatus, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            preparedStatement.setString(i, Objects.toString(tagStatus.getValue()));
        } else {
            preparedStatement.setObject(i, tagStatus.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public TagStatus getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        if (!resultSet.wasNull()) {
            try {
                return getTagEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public TagStatus getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        if (!resultSet.wasNull()) {
            try {
                return getTagEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public TagStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        if (!callableStatement.wasNull()) {
            try {
                return getTagEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private TagStatus getTagEnum(String value) throws IOException {
        return Stream.of(enums)
                .filter(i -> i.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("未知的枚举类型：" + value + ",请核对TagStatus"));
    }
}
