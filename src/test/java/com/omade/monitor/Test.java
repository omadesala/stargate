package com.omade.monitor;

import java.util.Map;

import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) {

		String json = "{\"version\":\"0.0.1\"}";
		Gson gson = new Gson();
		Map fromJson = gson.fromJson(json, Map.class);
		System.out.println("map: " + fromJson.toString());

	}

}
