package com.qqj.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by qqjbest on 2017/10/11.
 */
public class BusinessUtil
{
    /**
     * 判断手机格式是否正确
     * true：正确 ；false：错误
     *
     * @author qqjbest
     * @since 2016年4月11日 下午7:41:03
     * @param tel
     * @return
     */
    public static boolean valiTelFormat(String tel)
    {
        return (tel.startsWith("1") && tel.length() == 11);
    }

    /**
     * 生成4位随机验证码
     *
     *
     * @author qqjbest
     * @since 2016年4月11日 下午7:46:02
     * @return
     */
    public static String generateCode() {
        Random random = new Random();
        int x = random.nextInt(8999);
        x = x + 1000;
        return String.valueOf(x);
    }

    /**
     * 获取随机文件名
     * @Auther qqjbest  qqjbest@sina.com
     * @Date 2017/12/19 9:57
     */
    public static String getRandomFileName() {
        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return rannum + str;// 当前时间
    }

}
