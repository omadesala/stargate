package com.omade.monitor.domain;

import java.util.List;

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
public interface DeviceDao extends CrudRepository<DeviceItem, Long> {

	public DeviceItem findBydeviceid(String deviceid);

	@Query("select md5 from DeviceItem")
	public List<String> findDevicesMD5();

	@Query("select deviceid from DeviceItem where deviceid =:id")
	public List<String> devicesExists(@Param("id") String deviceid);

}
