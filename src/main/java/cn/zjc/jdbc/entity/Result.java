package cn.zjc.jdbc.entity;


import cn.zjc.jdbc.config.DaoConst;
import cn.zjc.exception.DaoException;
import cn.zjc.jdbc.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangjinci
 * @version 2016/8/29 9:25
 * @function Result对象, 使用List<Map>封装结果集
 */
public class Result implements Map<String, Object>, java.io.Serializable, Comparable<Result> {


    private static final Logger log = LoggerFactory.getLogger(Result.class);

    private Map<String, Object> map;

    private Map<String, Integer> sqlTypeMap;

    public Result() {
        map = new LinkedHashMap<>();
        sqlTypeMap = new HashMap<>();
    }

    public static Result create(ResultSet rs) {
        Result re = new Result();
        create(re, rs, null);
        return re;
    }

    public Result set(String name, Object value) {
        map.put(name.toLowerCase(), value);
        return this;
    }

    protected void setSqlType(String name, int value) {
        sqlTypeMap.put(name.toLowerCase(), value);
    }

    public Set<String> getColumnNames() {
        return map.keySet();
    }

    public int getColumnCount() {
        return map.size();
    }

    public Integer getInteger(String name){
          try {
              Object val = get(name);
              if (val == null){
                  return DaoConst.DEFAULT_INT_VALUE;
              }
              return Integer.valueOf(val.toString());
          }catch (Exception e){
              log.error("Cast value to Integer failed :" + e.getMessage());
          }
          return DaoConst.DEFAULT_INT_VALUE;
    }

    public String getString(String name){
        Object val = get(name);
        if (val == null){
            return null;
        }
        return val.toString();
    }

    public Date getDateTime(String name){
        Object val = get(name);
        if (val == null){
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(val.toString());
        } catch (ParseException e) {
            log.error("parse date failed:" + e.getMessage());
        }
        return null;
    }

    public Object getObject(String name){
        return this.get(name);
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonWithNull(map);
    }


    public static void create(Map<String, Object> re, ResultSet rs, ResultSetMetaData metaData) {
        String name = null;
        int i = 0;
        try {
            if (metaData == null) {
                metaData = rs.getMetaData();
            }
            int count = metaData.getColumnCount();
            for (i = 1; i <= count; i++) {

                name = metaData.getColumnName(i);
                switch (metaData.getColumnType(i)) {
                    case Types.TIMESTAMP: {
                        re.put(name, rs.getTimestamp(i));
                        break;
                    }

                    //mysql datetime类型会默认转化为Timestamp类型
                    case Types.DATE: {
                        re.put(name, rs.getTimestamp(i));
                        break;
                    }

                    case Types.CLOB: {
                        re.put(name, rs.getString(i));
                        break;
                    }

                    default:
                        re.put(name, rs.getObject(i));
                }

                if (re instanceof Result) {
                    ((Result) re).setSqlType(name, metaData.getColumnType(i));
                }
            }
        } catch (Exception e) {
            log.error("create Result failed:" + e.getMessage());
            throw new DaoException("create Result failed:" + e.getMessage());
        }
    }


    @Override
    public Object putIfAbsent(String key, Object value) {
        return map.putIfAbsent(key.toLowerCase(), value);
    }


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key.toString().toLowerCase());
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        if (key == null) {
            return null;
        }
        return map.get(key.toString().toLowerCase());
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key.toLowerCase(), value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key.toString().toLowerCase());
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        for (Entry<? extends String, ?> entry : m.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }

    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return map.equals(obj);
    }



    @Override
    public int compareTo(Result o) {
        if (null == o) {
            return 1;
        }
        if (o.size() == this.size()) {
            return 0;
        }
        return o.size() > this.size() ? -1 : 1;
    }
}
