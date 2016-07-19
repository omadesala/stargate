package com.omade.monitor.control;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.omade.monitor.config.ServiceProperties;
import com.omade.monitor.domain.DeviceDao;
import com.omade.monitor.domain.DeviceItem;
import com.omade.monitor.utils.GateFileUtils;
import com.omade.monitor.utils.JsonMessage;
import com.omade.monitor.utils.MD5Util;
import com.omade.monitor.utils.Produces;

@RestController
@RequestMapping("/stargate/v1")
public class ManagerController {

	private static Logger logger = Logger.getLogger(ManagerController.class);

	@Autowired
	private DeviceDao jobDao;

	@Autowired
	@Qualifier("serviceProperties")
	private ServiceProperties config;

	@RequestMapping(value = "/devices", method = RequestMethod.POST, produces = Produces.JSON_STRING)
	public String createDeviceRecord(@RequestBody String json,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());
		logger.info("json: " + json);
		JSONObject jo = new JSONObject(json);
		String deviceid = jo.getString("deviceid");
		String description = jo.getString("description");

		List<String> devicesExists = jobDao.devicesExists(deviceid);
		if (devicesExists.size() > 0) {
			JsonMessage jm = new JsonMessage();
			jm.setStatus(HttpStatus.SC_CONFLICT);
			jm.setMessage(" device alead exist !!!");
			Gson gson = new Gson();
			return gson.toJson(jm);
		}

		DeviceItem recored = new DeviceItem();

		recored.setDeviceid(deviceid);
		recored.setDescription(description);
		recored.setMd5(MD5Util.MD5(deviceid));
		recored.setCreatedate(new Date(System.currentTimeMillis()));

		jobDao.save(recored);
		logger.info("add data to storage:  ");

		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		response.setStatus(HttpStatus.SC_CREATED);
		jm.setMessage("add device success !!!");
		Gson gson = new Gson();
		return gson.toJson(jm);

	}

	// @RequestMapping(value = "/devices", method = RequestMethod.POST, produces
	// = Produces.JSON_STRING)
	// public void createRecord(@RequestBody String json,
	// HttpServletRequest request, HttpServletResponse response) {
	//
	// logger.info("uri: " + request.getRequestURI());
	// DeviceItem recored = new DeviceItem();
	//
	// String idStr = UUID.randomUUID().toString();
	// recored.setIdstr(idStr);
	// recored.setDescription("descripe what used for such device");
	// recored.setMd5(MD5Util.MD5(idStr));
	// recored.setCreatedate(new Date(System.currentTimeMillis()));
	//
	// jobDao.save(recored);
	// logger.info("add data to storage:  ");
	//
	// }

	@RequestMapping(value = "/devices", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public Iterable<DeviceItem> showAllDeviceRecord(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());
		DeviceItem recored = new DeviceItem();

		String idStr = UUID.randomUUID().toString();
		recored.setDeviceid(idStr);
		recored.setDescription("descripe what used for such device");
		recored.setMd5(MD5Util.MD5(idStr));
		recored.setCreatedate(new Date(System.currentTimeMillis()));
		recored.setDel(false);

		return jobDao.findAll();

	}

	@RequestMapping(value = "/version", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public String versionCheck(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());
		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		jm.setMessage("get latest version");
		Map<String, String> data = Maps.newHashMap();
		data.put("version", GateFileUtils.newVersion(config.getClientfolder()));
		Gson gson = new Gson();
		jm.setData(gson.toJson(data));
		return gson.toJson(jm);

	}

}
