package com.example.forumBackEnd.mapper.typeHandler;

import com.example.forumBackEnd.pojo.enumClass.PostStatus;
import com.example.forumBackEnd.pojo.enumClass.TagStatus;
import com.example.forumBackEnd.pojo.enumClass.ValueEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@MappedTypes(value = {TagStatus.class, PostStatus.class})
public class EnumTypeHandler<E extends ValueEnum> extends BaseTypeHandler<E> {
    private Class<E> type;
    private E[] enums;

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            preparedStatement.setString(i, Objects.toString(e.getValue()));
        } else {
            preparedStatement.setObject(i, e.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        if (!resultSet.wasNull()) {
            return getEnum(string);
        }
        return null;
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        if (!resultSet.wasNull()) {
            return getEnum(string);
        }
        return null;
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        if (!callableStatement.wasNull()) {
            return getEnum(string);
        }
        return null;
    }

    private E getEnum(String value){
        for (E e:
                enums) {
            if (e.getValue().equals(value)){
                return e;
            }
        }
        return null;
    }
}
