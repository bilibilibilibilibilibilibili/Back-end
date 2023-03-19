package com.example.loginandregistry.mapper.typeHandler;

import com.example.loginandregistry.pojo.Post;
import com.example.loginandregistry.pojo.Resource;
import com.example.loginandregistry.pojo.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MappedTypes(value = {Resource.class, Tag.class})
@MappedJdbcTypes(JdbcType.LONGVARCHAR)
public class JsonTypeHandler<T> implements TypeHandler<List<T>> {

    private Class<T> targetClass;

    public JsonTypeHandler(Class<T> targetClass) {
        if (targetClass == null){
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.targetClass = targetClass;
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<T> ts, JdbcType jdbcType) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            preparedStatement.setString(i, mapper.writeValueAsString(ts));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> getResult(ResultSet resultSet, String s) throws SQLException {
        String jsonArray = resultSet.getString(s);
        if (jsonArray != null){
            try{
                return json2List(jsonArray);
            }catch (JsonProcessingException e){ throw new RuntimeException(e); }
        }
        return null;
    }

    @Override
    public List<T> getResult(ResultSet resultSet, int i) throws SQLException {
        String jsonArray = resultSet.getString(i);
        if (jsonArray != null){
            try {
                return json2List(jsonArray);
            }catch (JsonProcessingException e){ throw new RuntimeException(e); }
        }
        return null;
    }

    @Override
    public List<T> getResult(CallableStatement callableStatement, int i) throws SQLException {
        String jsonArray = callableStatement.getString(i);
        if (jsonArray != null){
            try {
                return json2List(jsonArray);
            }catch (JsonProcessingException e){ throw new RuntimeException(e); }
        }
        return null;
    }

    private List<T> json2List(String jsonArray) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        CollectionType javaType = mapper.getTypeFactory()
                .constructCollectionType(List.class, targetClass);
        return mapper.readValue(jsonArray, javaType);
    }
}
