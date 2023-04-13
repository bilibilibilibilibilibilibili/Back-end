package com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler;

import com.example.forumBackEnd.pojo.enumClass.Identity;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Stream;

public class IdentityTypeHandler implements TypeHandler<Identity> {
    private Identity[] enums = Identity.values();

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Identity identity, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            preparedStatement.setString(i, Objects.toString(identity.getValue()));
        } else {
            preparedStatement.setObject(i, identity.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public Identity getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        if (!resultSet.wasNull()) {
            try {
                return getIdentityEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Identity getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        if (!resultSet.wasNull()) {
            try {
                return getIdentityEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Identity getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        if (!callableStatement.wasNull()) {
            try {
                return getIdentityEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private Identity getIdentityEnum(String value) throws IOException {
        return Stream.of(enums)
                .filter(i -> i.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("未知的枚举类型：" + value + ",请核对Identity"));
    }
}
