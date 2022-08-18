package com.example.cloudprojectcollection;

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
}
