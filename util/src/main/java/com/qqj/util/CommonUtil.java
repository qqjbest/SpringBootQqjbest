package com.qqj.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CommonUtil
{
    public static void main(String[] args)
    {
        // System.out.println(strArray2longArray("123123")[0]);
        // System.out.println(strArray2longArray("123123,123".split(","))[1]);
        System.out.println(generateShortUuid(6));
    }

    /**
     * 字符串或字符串数组转化为整形数组
     *
     * @author qqjbest
     * @since 2016年4月12日 下午4:49:27
     * @param arr
     * @return
     */
    public static Long[] strArray2longArray(String... arr)
    {
        Long[] longArr = new Long[arr.length];

        for (int i = 0; i < arr.length; i++)
        {
            longArr[i] = Long.parseLong(arr[i]);
        }
        return longArr;
    }

    public static String[] chars = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                                                 "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                                                 "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 生成len位数随机码
     *
     * @author qqjbest
     * @since 2016年4月22日 上午10:58:16
     * @return
     */
    public static String generateShortUuid(int len)
    {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < len; i++)
        {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    public static String[] getNullPropertyNames(Object source)
    {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds)
        {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object fromObj, Object toObject)
    {
        BeanUtils.copyProperties(fromObj, toObject, getNullPropertyNames(fromObj));
    }
}
