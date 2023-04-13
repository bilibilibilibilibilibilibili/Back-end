package com.example.forumBackEnd.mapper.typeHandler.enumTypeHandler;

import com.example.forumBackEnd.pojo.enumClass.Identity;
import com.example.forumBackEnd.pojo.enumClass.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Stream;

public class SexTypeHandler implements TypeHandler<SexEnum> {
    private SexEnum[] enums = SexEnum.values();

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            preparedStatement.setString(i, Objects.toString(sexEnum.getValue()));
        } else {
            preparedStatement.setObject(i, sexEnum.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        if (!resultSet.wasNull()) {
            try {
                return getSexEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        if (!resultSet.wasNull()) {
            try {
                return getSexEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public SexEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        if (!callableStatement.wasNull()) {
            try {
                return getSexEnum(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private SexEnum getSexEnum(String value) throws IOException {
        return Stream.of(enums)
                .filter(i -> i.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("未知的枚举类型：" + value + ",请核对SexEnum"));
    }
}
