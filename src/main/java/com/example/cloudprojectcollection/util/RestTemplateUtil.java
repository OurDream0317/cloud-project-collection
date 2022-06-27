package com.example.cloudprojectcollection.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @Author: Wwx
 * @createTime: 2022年06月27日 12:19:40
 * @version:
 * @Description:
 */
public class RestTemplateUtil {

	 public static <T> T post(String url,Map<String, Object> map, Class<T> clazz) {
	    	RestTemplate restTemplate = new RestTemplate();
	    	HttpHeaders headers = new HttpHeaders();
	    	//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
	    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    	//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
	    	MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>(map.entrySet().size());
	    	for (Entry<String, Object> e : map.entrySet()) {
	    		params.set(e.getKey(), e.getValue().toString());
			}
	    	//  也支持中文
	    	HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	    	//  执行HTTP请求
	    	return restTemplate.postForObject(url, requestEntity, clazz);

	    }
}
