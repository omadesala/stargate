package com.omade.monitor.domain;

/*
 * Copyright 2012-2013 the original author or authors.
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

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "devices")
public class DeviceItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String idstr;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Date createdate;

	@Column(nullable = false)
	private String md5;

	@Column(nullable = false)
	private Boolean del;

	public DeviceItem() {
	}

	public DeviceItem(Long id) {
		this.id = id;
	}

	public DeviceItem(Long id, String idstr, String description,
			Date createDate, String md5, Boolean del) {

		this.id = id;
		this.idstr = idstr;
		this.description = description;
		this.createdate = createDate;
		this.md5 = md5;
		this.del = del;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "DeviceItem [id=" + id + ", idstr=" + idstr + ", description="
				+ description + ", createdate=" + createdate + ", md5=" + md5
				+ ", del=" + del + "]";
	}

}
