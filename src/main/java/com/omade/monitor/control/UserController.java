package com.omade.monitor.control;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omade.monitor.domain.UserDao;
import com.omade.monitor.domain.UserItem;
import com.omade.monitor.utils.JsonMessage;
import com.omade.monitor.utils.Produces;

@RestController
@RequestMapping("/stargate/v1/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/item", method = RequestMethod.POST, produces = Produces.JSON_STRING)
	public JsonMessage create(@RequestBody String json,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());

		JSONObject jo = new JSONObject(json);
		String username = jo.getString("username");
		String password = jo.getString("password");
		String description = jo.getString("description");

		List<UserItem> userExists = userDao.userExists(username);
		if (userExists.size() > 0) {
			JsonMessage jm = new JsonMessage();
			jm.setStatus(HttpStatus.SC_CONFLICT);
			jm.setMessage(" user alread exist !!!");
			return jm;
		}

		UserItem userinfo = new UserItem();

		userinfo.setUsername(username);
		userinfo.setMd5passwd(password);
		userinfo.setDescription(description);
		userinfo.setCreatedate(new Timestamp(System.currentTimeMillis()));
		userinfo.setDel(false);
		userDao.save(userinfo);

		logger.info("save user to database  ");

		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		response.setStatus(HttpStatus.SC_CREATED);
		jm.setMessage("add user success !!!");
		return jm;

	}

	@RequestMapping(value = "/item", method = RequestMethod.DELETE, produces = Produces.JSON_STRING)
	public JsonMessage deleteUser(@RequestParam("username") String username,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());

		List<UserItem> userExists = userDao.userExists(username);
		if (userExists.size() == 0) {
			JsonMessage jm = new JsonMessage();
			jm.setStatus(HttpStatus.SC_CONFLICT);
			jm.setMessage(" user NOT exist !!!");
			return jm;
		}
		userDao.deleteUserByUserName(username);

		logger.info("delete user ");

		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		response.setStatus(HttpStatus.SC_OK);
		jm.setMessage("delete user success !!!");
		return jm;

	}

	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public JsonMessage showUser(@RequestParam("username") String username,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());

		UserItem findUser = userDao.findByUserName(username);
		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		jm.setMessage("get all user info !!!");
		jm.setData(findUser);
		logger.info("user: " + findUser.toString());
		logger.info("jm: " + jm.toString());
		return jm;

	}

	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = Produces.JSON_STRING)
	public JsonMessage showAllUsers(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("uri: " + request.getRequestURI());

		List<UserItem> findAllUsers = userDao.findAllUsers();
		JsonMessage jm = new JsonMessage();
		jm.setStatus(HttpStatus.SC_OK);
		jm.setMessage("get all user info !!!");
		jm.setData(findAllUsers);
		return jm;

	}

}
