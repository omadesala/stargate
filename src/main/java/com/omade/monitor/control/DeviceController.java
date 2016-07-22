package com.omade.monitor.control;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.omade.monitor.config.ServiceProperties;
import com.omade.monitor.domain.DeviceDao;
import com.omade.monitor.domain.DeviceItem;
import com.omade.monitor.utils.GateFileUtils;
import com.omade.monitor.utils.JsonMessage;
import com.omade.monitor.utils.MD5Util;
import com.omade.monitor.utils.Produces;

@RestController
@RequestMapping("/stargate/v1/device")
public class DeviceController {

	private static Logger logger = Logger.getLogger(DeviceController.class);

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	@Qualifier("serviceProperties")
	private ServiceProperties config;

	@RequestMapping(value = "/item", method = RequestMethod.POST, produces = Produces.JSON_STRING)
	public JsonMessage createDeviceRecord(@RequestBody String json,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());
		logger.info("json: " + json);
		JSONObject jo = new JSONObject(json);
		String deviceid = jo.getString("deviceid");
		String description = jo.getString("description");

		List<String> devicesExists = deviceDao.devicesExists(deviceid);
		if (devicesExists.size() > 0) {
			JsonMessage jm = new JsonMessage();
			jm.setStatus(HttpStatus.SC_CONFLICT);
			jm.setMessage(" device alread exist !!!");
			return jm;
		}

		DeviceItem recored = new DeviceItem();

		recored.setDeviceid(deviceid);
		recored.setDescription(description);
		recored.setMd5(MD5Util.MD5(deviceid));
		recored.setCreatedate(new Timestamp(System.currentTimeMillis()));
		recored.setDel(false);

		deviceDao.save(recored);
		logger.info("add data to storage:  ");

		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		response.setStatus(HttpStatus.SC_CREATED);
		jm.setMessage("add device success !!!");
		return jm;

	}

	@RequestMapping(value = "/item", method = RequestMethod.DELETE, produces = Produces.JSON_STRING)
	public JsonMessage deleteRecord(@RequestParam("deviceid") String deviceid,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());

		deviceDao.deleteDeviceById(deviceid);

		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		response.setStatus(HttpStatus.SC_OK);
		jm.setMessage("delete device success !!!");
		logger.info("delete device by id logicly");

		return jm;

	}

	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public JsonMessage showDeviceRecord(
			@RequestParam("deviceid") String deviceid,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());
		DeviceItem findBydeviceid = deviceDao.findBydeviceid(deviceid);

		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		response.setStatus(HttpStatus.SC_OK);
		if (findBydeviceid == null) {
			jm.setMessage("no device information for such deviceid !!!");
		} else {
			jm.setMessage("find device information by deviceid !!!");
		}
		jm.setData(findBydeviceid);

		logger.info("find device information by deviceid ");
		return jm;

	}

	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public JsonMessage showAllDeviceRecord(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());
		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		response.setStatus(HttpStatus.SC_OK);
		jm.setMessage("get all device information !!!");

		List<DeviceItem> findAllDevices = deviceDao.findAllDevices();
		jm.setData(findAllDevices);

		logger.info("delete device by id logicly");
		return jm;

	}

	@RequestMapping(value = "/version", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public JsonMessage versionCheck(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());
		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		jm.setMessage("get latest version");
		Map<String, String> data = Maps.newHashMap();
		data.put("version", GateFileUtils.newVersion(config.getClientfolder()));
		jm.setData(data);
		return jm;

	}

}
