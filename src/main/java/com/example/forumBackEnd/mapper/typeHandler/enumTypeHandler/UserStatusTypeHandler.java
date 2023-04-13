package com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler;

import com.example.forumBackEnd.pojo.enumClass.Identity;
import com.example.forumBackEnd.pojo.enumClass.UserStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Stream;

public class UserStatusTypeHandler implements TypeHandler<UserStatus> {
    private UserStatus[] enums = UserStatus.values();

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, UserStatus userStatus, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            preparedStatement.setString(i, Objects.toString(userStatus.getValue()));
        } else {
            preparedStatement.setObject(i, userStatus.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public UserStatus getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        if (!resultSet.wasNull()) {
            try {
                return getUserStatusEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public UserStatus getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        if (!resultSet.wasNull()) {
            try {
                return getUserStatusEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public UserStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        if (!callableStatement.wasNull()) {
            try {
                return getUserStatusEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private UserStatus getUserStatusEnum(String value) throws IOException {
        return Stream.of(enums)
                .filter(i -> i.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("未知的枚举类型：" + value + ",请核对UserStatus"));
    }
}
