package com.js.summary.typeHandler;

import com.js.summary.common.utils.JsonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ListTypeHandler<T> extends BaseTypeHandler<List<T>> {

    private Class<T> clazz;

    public ListTypeHandler(Class<T> clazz){
        if (clazz == null) throw new RuntimeException("Type argument cannot be null");
        this.clazz = clazz;
    }

    @Override
    public List<T> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return super.getResult(rs, columnIndex);
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<T> ts, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JsonUtils.beanToJson(ts));
    }

    @Override
    public List<T> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JsonUtils.jsonToList(resultSet.getString(s), clazz);
    }

    @Override
    public List<T> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JsonUtils.jsonToList(resultSet.getString(i),clazz);
    }

    @Override
    public List<T> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return JsonUtils.jsonToList(callableStatement.getString(i), clazz);
    }
}
