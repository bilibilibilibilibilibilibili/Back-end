package com.example.forumBackEnd.mapper.typeHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedJdbcTypes(JdbcType.LONGVARCHAR)
public class ListTypeHandler extends BaseTypeHandler<List<String>> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        try {
            preparedStatement.setString(i, OBJECT_MAPPER.writeValueAsString(strings));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String jsonArray = resultSet.getString(s);
        if (jsonArray != null){
            try{
                return json2List(jsonArray);
            }catch (Exception e){ throw new RuntimeException(e); }
        }
        return null;
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String jsonArray = resultSet.getString(i);
        if (jsonArray != null){
            try{
                return json2List(jsonArray);
            }catch (Exception e){ throw new RuntimeException(e); }
        }
        return null;
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String jsonArray = callableStatement.getString(i);
        if (jsonArray != null){
            try{
                return json2List(jsonArray);
            }catch (Exception e){ throw new RuntimeException(e); }
        }
        return null;
    }

    private List<String> json2List(String jsonArray) throws Exception{
        CollectionType javaType = OBJECT_MAPPER.getTypeFactory()
                .constructCollectionType(List.class, String.class);
        return OBJECT_MAPPER.readValue(jsonArray, javaType);
    }
}
