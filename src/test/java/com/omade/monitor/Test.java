package com.omade.monitor;

import java.sql.Timestamp;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) {

		Map<String, Object> data = Maps.newHashMap();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		data.put("key1", "value1");
		data.put("key2", "value2");
		data.put("key3", "value3");
		data.put("dt", ts);
		Gson gson = new Gson();
		String json = gson.toJson(data);
		System.out.println("map: " + json);

		System.out.println("Timestamp: " + ts.toString());

		String path = Test.class.getClassLoader().getResource("").getPath();
		System.out.println("Timestamp: " + path);

	}

}
