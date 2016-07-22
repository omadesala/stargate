package com.omade.monitor.control;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.omade.monitor.domain.DataItem;
import com.omade.monitor.domain.DataItemDao;
import com.omade.monitor.utils.JsonMessage;
import com.omade.monitor.utils.Produces;

@RestController
@RequestMapping("/stargate/v1/data")
public class DataController {

	private static Logger logger = Logger.getLogger(DataController.class);

	@Autowired
	private DataItemDao dataDao;

	@RequestMapping(value = "/item", method = RequestMethod.POST, produces = Produces.JSON_STRING)
	public JsonMessage createDeviceRecord(@RequestBody String json,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());
		logger.info("json: " + json);
		JSONObject jo = new JSONObject(json);
		String deviceid = jo.getString("deviceid");
		String description = jo.getString("description");
		String data = jo.getString("data");

		DataItem record = new DataItem();
		record.setDeviceid(deviceid);
		record.setDescription(description);
		record.setCreatedate(new Timestamp(System.currentTimeMillis()));
		record.setDel(false);
		record.setData(data);
		dataDao.save(record);
		logger.info("add data to storage:  ");

		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		response.setStatus(HttpStatus.SC_CREATED);
		jm.setMessage("add device success !!!");
		return jm;

	}

	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public JsonMessage showDeviceRecord(
			@RequestParam("deviceid") String deviceid,
			@RequestParam("page") Integer page,
			@RequestParam("size") Integer size, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());

		if (page < 0) {
			page = 0;
		}

		if (size <= 0) {
			size = Integer.MAX_VALUE;
		}

		Pageable pageable = new PageRequest(page, size);
		List<DataItem> ret = Lists.newArrayList();
		Page<DataItem> findBydeviceId = dataDao.findBydeviceid(deviceid,
				pageable);

		Iterator<DataItem> iterator = findBydeviceId.iterator();
		while (iterator.hasNext()) {
			ret.add(iterator.next());
		}

		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		response.setStatus(HttpStatus.SC_OK);
		if (ret.size() == 0) {
			jm.setMessage("no record for this deviceid !!!");
		} else {
			jm.setMessage("find recored for this deviceid !!!");
		}
		jm.setData(ret);

		logger.info("find device records by deviceid ");
		return jm;

	}

}
