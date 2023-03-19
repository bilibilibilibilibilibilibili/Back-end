package com.example.loginandregistry.mapper.typeHandler;

import com.example.loginandregistry.pojo.enumClass.PostStatus;
import com.example.loginandregistry.pojo.enumClass.TagStatus;
import com.example.loginandregistry.pojo.enumClass.ValueEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = {TagStatus.class, PostStatus.class})
public class EnumTypeHandler<E extends ValueEnum> extends BaseTypeHandler<E> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
