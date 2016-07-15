/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chinacloud.bds.datastorage.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.rackspacecloud.client.cloudfiles.FilesClient;

//@ConfigurationProperties(prefix = "service", ignoreUnknownFields = false)
@ConfigurationProperties(prefix = "service", locations = "classpath:application.properties")
@Component("serviceProperties")
public class ServiceProperties {

	private String url = "localhost";
	private String objectstoretype = "ceph";
	private String adminid = "access_key";
	private String adminkey = "secret_key";
	private String exampleUrl = "exampleUrl";

	private String oneaaurl = "";
	private String oneaaclientid = "";
	private String oneaasecret = "";
	private String oneaapolicyfile = "";

	private ConcurrentMap<String, FilesClient> usrIdClients = Maps
			.newConcurrentMap();
	private ConcurrentMap<String, String> usrIdSecretKey = Maps
			.newConcurrentMap();

	private List<String> userNameList = Collections
			.synchronizedList(new ArrayList<String>());

	private ConcurrentMap<String, String> usrIdSwiftToken = Maps
			.newConcurrentMap();

	public String getObjectstoretype() {
		return objectstoretype;
	}

	public void setObjectstoretype(String objectstoretype) {
		this.objectstoretype = objectstoretype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getAdminkey() {
		return adminkey;
	}

	public void setAdminkey(String adminkey) {
		this.adminkey = adminkey;
	}

	public ConcurrentMap<String, FilesClient> getUsrIdClients() {
		return usrIdClients;
	}

	public void setUsrIdClients(ConcurrentMap<String, FilesClient> usrIdClients) {
		this.usrIdClients = usrIdClients;
	}

	public ConcurrentMap<String, String> getUsrIdSecretKey() {
		return this.usrIdSecretKey;
	}

	public void setUsrIdSecretKey(ConcurrentMap<String, String> usrIdSecretKey) {
		this.usrIdSecretKey = usrIdSecretKey;
	}

	public List<String> getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}

	public ConcurrentMap<String, String> getUsrIdSwiftToken() {
		return usrIdSwiftToken;
	}

	public void setUsrIdSwiftToken(ConcurrentMap<String, String> usrIdSwiftToken) {
		this.usrIdSwiftToken = usrIdSwiftToken;
	}

	public String getExampleUrl() {
		return exampleUrl;
	}

	public void setExampleUrl(String exampleUrl) {
		this.exampleUrl = exampleUrl;
	}

	public String getOneaaurl() {
		return oneaaurl;
	}

	public void setOneaaurl(String oneaaurl) {
		this.oneaaurl = oneaaurl;
	}

	public String getOneaaclientid() {
		return oneaaclientid;
	}

	public void setOneaaclientid(String oneaaclientid) {
		this.oneaaclientid = oneaaclientid;
	}

	public String getOneaasecret() {
		return oneaasecret;
	}

	public void setOneaasecret(String oneaasecret) {
		this.oneaasecret = oneaasecret;
	}

	public String getOneaapolicyfile() {
		return oneaapolicyfile;
	}

	public void setOneaapolicyfile(String oneaapolicyfile) {
		this.oneaapolicyfile = oneaapolicyfile;
	}

}
