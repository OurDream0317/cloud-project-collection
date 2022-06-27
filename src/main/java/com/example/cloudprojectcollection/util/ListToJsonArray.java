package com.example.cloudprojectcollection.util;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * @Author: Wwx
 * @createTime: 2022年06月27日 12:19:40
 * @version:
 * @Description:
 */
public class ListToJsonArray {
	public static JSONArray getJSONArrayByList(List<?> list){
	    JSONArray jsonArray = new JSONArray();
	    if (list==null ||list.isEmpty()) {
	        return jsonArray;//nerver return null
	    }
	    for (Object object : list) {
	        jsonArray.add(object);
	    }
	    return jsonArray;
	}

}
