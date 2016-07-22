package com.omade.monitor.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by daniel on 15-9-18.
 */
/*
 * Copyright 2012-2013 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

@Transactional
public interface UserDao extends CrudRepository<UserItem, Long> {

	@Query("from UserItem where username =:name")
	public UserItem findByUserName(@Param("name") String username);

	@Query("select md5passwd from UserItem where username=:name")
	public String findUserPassWordMD5(@Param("name") String username);

	@Query("from UserItem where del = false")
	public List<UserItem> findAllUsers();

	@Query("from UserItem where username =:name and del=false")
	public List<UserItem> userExists(@Param("name") String username);

	@Modifying(clearAutomatically = true)
	@Query("update UserItem set del=true where username =:name")
	public void deleteUserByUserName(@Param("name") String username);

}
