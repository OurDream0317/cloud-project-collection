package com.example.cloudprojectcollection.util;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * httpClient 工具类
 */
@Slf4j
public class RestfulHttpClient {

    /**
     * 发送http请求获取json
     *
     * @param url url+参数
     * @return 响应json字符串
     */
    public static String sendHttpRequest(String url) {

        StringBuilder responseJson = new StringBuilder();
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setValidateAfterInactivity(1200000);//5秒清除不活动的连接

        //设置超时时间
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(1200000)
                .setConnectTimeout(1200000)
                .setConnectionRequestTimeout(1200000)
                .build();

        //构建HttpClient对象
        //CloseableHttpClient client = HttpClients.createDefault();
        //设置有超时时间的客户端
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(manager)
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();

        //构建POST请求
        HttpGet httpPost = new HttpGet(url);
        InputStream inputStream = null;
        try {

            //发送请求
            HttpResponse response = client.execute(httpPost);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (200 == statusCode) {
                    inputStream = entity.getContent();
                    //转换为字节输入流
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                    String body;
                    while ((body = br.readLine()) != null) {
                        responseJson.append(body);
                    }
                }
            }
        } catch (IOException e) {
            log.error("发送http请求发生错误: " + e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseJson.toString();
    }

    /**
     * 发送http请求获取文件
     *
     * @param url 请求地址+参数
     */
    public static InputStream sendHttpRequestResFile(String url) throws IOException {

        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setValidateAfterInactivity(30000);//30秒清除不活动的连接

        //设置超时时间
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .build();

        //构建HttpClient对象
        //CloseableHttpClient client = HttpClients.createDefault();
        //设置有超时时间的客户端
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(manager)
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();

        //构建POST请求
        HttpGet httpPost = new HttpGet(url);
        String filename = null;
        @Cleanup InputStream inputStream = null;

        //发送请求
        HttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            int statusCode = response.getStatusLine().getStatusCode();
            inputStream = entity.getContent();
            if (200 == statusCode) {
                Header[] headers = response.getHeaders("Content-Disposition");
                filename = headers[0].getValue().split(";")[1].split("=")[1];
                return inputStream;
            }
        }
        return inputStream;
    }

    /**
     * Java发送http请求发送json对象
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String httpPostWithJson(String url, String json){
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            BasicResponseHandler handler = new BasicResponseHandler();
            StringEntity entity = new StringEntity(json, "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            result = httpClient.execute(httpPost, handler);
            return result;
        } catch (Exception e) {
           log.error("请求报错{}",e);

        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
