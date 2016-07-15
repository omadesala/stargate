package com.omade.monitor.service;

public class Constant {

	public static String RESPONSE_CODE = "code";
	// 缓存时间(秒)，从配置文件中获取
	// public static long CACHED_TIME = 30;
	// header中存放的token的key
	public static String XAUTH_TOKEN = "X-Auth-Token";
	public static String AUTHORIZATION = "Authorization";
	// 没有权限
	public static String FORBIDDEN = "Forbidden";
	public static String REQUEST_CONTEXT_USER_ID = "__REQUEST_CONTEXT_USER_ID__";
	public static String REQUEST_CONTEXT_USER_NAME = "__REQUEST_CONTEXT_USER_NAME";
	public static String REQUEST_CONTEXT_USER = "__REQUEST_CONTEXT_USER__";
	public static String REQUEST_CONTEXT_IP = "__REQUEST_CONTEXT_IP__";
	public static String REQUEST_CONTEXT_TENANTIDS = "__REQUEST_CONTEXT_TENANT_IDS__";
	public static String REQUEST_ONE_AA_USER_NAME = "userName";
	public static String REQUEST_ONE_AA_USER_ID = "userId";
	public static String REQUEST_CONTEXT_USER_PROJECTS = "";
	public static String LOG_SPACE = "auditlogger";
	public static String DEFAULT_REALM_NAME = "master";
	public static String PRIVILEGE_OBJ_KEY = "privilege_obj_key";
	// 缓存token
	public static final String CURRENT_CLIENT_HOST = "__CURRENT_CLIENT_HOST__";
	public static final String CURRENT_AUDIT_ACTION = "__CURRENT_AUDIT_ACTION__";
	public static final String CURRENT_AUDIT_DESCRIPTION = "__CURRENT_AUDIT_DESCRIPTION__";
	public static final String CURRENT_DAO_EXECTION_TIME = "__CURRENT_DAO_EXECTION_TIME__";
	public static final String CURRENT_IP_ADDRESS = "one_aa_ip_address";
	// public static void main(String args[]){
	//
	// }
}