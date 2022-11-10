package com.example.cloudprojectcollection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * @Author: Wwx
 * @createTime: 2022年08月15日
 * @version: 0.0.1
 * @Description:
 */

public class Test {

    @org.junit.Test
    public void m() throws IOException {
        //创建链接
            Connection connection = Jsoup.connect("https://pvp.qq.com/web201605/herolist.shtml");
        //获取document对象
        Document document = connection.get();
//    System.out.println(document);
        //获取对应元素
        Elements imgs = document.getElementsByTag("img");
        System.out.println("检测到图片");
        System.out.println("开始下载。。。。");
        for (Element img : imgs) {
            //获取绝对路径
            String imgStr = img.attr("abs:src");
            System.out.println(imgStr);
            download("D:/img", imgStr);
        }
        System.out.println("下载完成");
    }



    /**
     * 下载图片到指定目录
     *
     * @param filePath 文件路径
     * @param imgUrl   图片地址
     */
    public static void download(String filePath, String imgUrl) {
        //判断文件是否存在，如果不存在创建目录
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //导出文件名称
        String fileName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1, imgUrl.length());
        try {
            //将文件名转化为指定格式，html5之前字符集格式不统一，需要转化成UTF-8
            String urlTail = URLEncoder.encode(fileName, "UTF-8");
            //因此需要将加号转化为UTF-8中的20%
            imgUrl = imgUrl.substring(0, imgUrl.lastIndexOf('/') + 1) + urlTail.replaceAll("\\+", "\\%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //File.separator的作用是加入文件分隔符
        File file1 = new File(filePath + File.separator + fileName);

        try {
            //获取图片URL
            URL url = new URL(imgUrl);
            //获取连接
            URLConnection urlConnection = url.openConnection();
            //设置连接时间
            urlConnection.setConnectTimeout(10 * 1000);
            //设置输入流
            InputStream in = urlConnection.getInputStream();
            //设置输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file1));
            byte[] buf = new byte[1024];
            int len;
            while (-1 != (len = in.read(buf))) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void mm(){
        Map map = new HashMap();
        map.put("name","wwx");
        mm1(map);
        System.out.println(JSON.toJSONString(map));
    }

    public static Map  mm1(Map map){
        map.put("name1","wwx1");
        return map;
    }


    @org.junit.Test
    public void mmm(){
       /* if ("".equals("3333")){
            System.out.println("333333");
        }
        //标签挂失。无签挂起，无签注销  7.15后不再进行注销上报
        else
            if ("600207101,600207102,600207103,600207111,600207112".indexOf("600207101") >= 0) {
            System.out.println("11111111111");
        }else{
            System.out.println("222222222222");

        }*/

        System.out.println( System.currentTimeMillis());
    }

    @org.junit.Test
    public void m1m(){
        String text1 = "2022-07-31";
        Temporal temporal1 = LocalDate.parse(text1);
        String text2 = "2022-09-30";
        Temporal temporal2 = LocalDate.parse(text2);
        // 方法返回为相差月份
        long l = ChronoUnit.MONTHS.between(temporal1, temporal2);
        System.out.println(l);
    }

    @org.junit.Test
    public void mmm1() throws ParseException {
        String s = "{\"data\":[{\"id\":\"87fe4efa4a004841a97137c6f82021e4\",\"photoNumber\":\"豫A6EPC3-600107101\",\"frontUrl\":\"/nfsdata/pcPhotos/mobile/20220914/fbe3ce46b0a54378b3d9a3986a4271ef.jpg\",\"backUrl\":\"/nfsdata/pcPhotos/mobile/20220914/a797adbe402842eb8a21ae5d8bfa0993.jpg\",\"createTime\":\"2022-09-28 18:19:16\",\"remove\":\"0\",\"clientNo\":\"41010122091400000011\",\"businessType\":\"200103104\",\"spare2\":\"/nfsdata/pcPhotos/mobile/20220926/da6e5a744c674b03896a9fe9a27d21af.jpg\"}],\"additional\":{\"idFrontUrl\":\"/nfsdata/pcPhotos/mobile/20220914/fbe3ce46b0a54378b3d9a3986a4271ef.jpg\",\"idBackUrl\":\"/nfsdata/pcPhotos/mobile/20220914/a797adbe402842eb8a21ae5d8bfa0993.jpg\",\"licenceFrontUrl\":\"/nfsdata/pcPhotos/mobile/20220926/da6e5a744c674b03896a9fe9a27d21af.jpg\",\"vehicleFrontUrl\":\"/nfsdata/pcPhotos/mobile/20220926/6f3ae1288bf94d14825c961c95dc68ff.jpg\"}}";
        JSONObject parse = (JSONObject) JSONObject.parse(s);
        JSONArray jsonArray = (JSONArray) parse.get("data");
        JSONObject o = (JSONObject) jsonArray.get(0);
        String frontUrl = o.get("frontUrl") == null ? null : o.get("frontUrl").toString();
        String backUrl = o.get("backUrl") == null ? null : o.get("backUrl").toString();
        String spare2 = o.get("spare2") == null ? null : o.get("spare2").toString();
        String spare3 = o.get("spare3") == null ? null : o.get("spare3").toString();
        System.out.println("frontUrl="+frontUrl);
        System.out.println("backUrl="+backUrl);
        System.out.println("spare2="+spare2);
        System.out.println("spare3="+spare3);
    }
    /**
     * 获取两个日期相差几个月
     * @author 石冬冬-Heil Hilter(dd.shi02@zuche.com)
     * @date 2016-11-30 下午7:57:32
     * @param start
     * @param end
     * @return
     */
    public  int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)&& (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }
    public int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval--;
        }
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) {
            monthInterval--;
        }
        monthInterval %= 12;
        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
        return monthsDiff;
    }
}
