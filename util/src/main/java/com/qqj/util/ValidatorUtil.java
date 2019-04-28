package com.qqj.util;

import org.json.JSONObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 验证工具类
 *
 * @author qqjbest
 * @since 2016年4月12日 下午4:38:52
 */
public class ValidatorUtil
{
    public static boolean isNull(Object obj)
    {
        if (obj instanceof Map)
        {
            return isNull((Map) obj);
        }
        else if (obj instanceof JSONObject)
        {
            return isNull((JSONObject) obj);
        }
        else if (obj instanceof Long)
        {
            return isNull((Long) obj);
        }
        else if (obj instanceof List)
        {
            return isNull((List) obj);
        }
        else if (obj instanceof Collection)
        {
            return isNull((Collection) obj);
        }
        else if (obj instanceof String)
        {
            return isNull((String) obj);
        }
        else if (obj == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNull(JSONObject json)
    {
        return ((json == null) || json.length() == 0);
    }

    public static boolean isNull(Map m)
    {
        return ((m == null) || m.size() == 0);
    }

    public static boolean isNull(Long l)
    {
        return ((l == null) || l.longValue() == 0);
    }

    public static boolean isNull(String s)
    {
        return (s == null) || (s.length() == 0);
    }

    public static boolean isNull(List list)
    {
        return ((list == null) || (list.size() == 0));
    }

    public static boolean isNull(Collection coll)
    {
        return ((coll == null) || (coll.size() == 0));
    }

    public static boolean isNull(Object[] array)
    {
        return ((array == null) || (array.length == 0));
    }

    public static boolean isNotNull(Object obj)
    {
        return !isNull(obj);
    }

    public static boolean isNotNull(JSONObject obj)
    {
        return !isNull(obj);
    }

    public static boolean isNotNull(Long l)
    {
        return !isNull(l);
    }

    public static boolean isNotNull(String s)
    {
        return !isNull(s);
    }

    public static boolean isNotNull(List list)
    {
        return !isNull(list);
    }

    public static boolean isNotNull(Collection coll)
    {
        return !isNull(coll);
    }

    public static boolean isNotNull(Map map)
    {
        return !isNull(map);
    }

    public static boolean isNotNull(Object[] array)
    {
        return !isNull(array);
    }
}
