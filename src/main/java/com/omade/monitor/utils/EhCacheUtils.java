/**
 * JWatch - Quartz Monitor: http://code.google.com/p/jwatch/
 * Copyright (C) 2011 Roy Russo and the original author or authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301 USA
 **/
package com.omade.monitor.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.google.common.base.Strings;

public class EhCacheUtils {

	public static void update(EhCacheCacheManager ehcache, String cacheName,
			String tokenKey, String tokenValue) {

		if (!Strings.isNullOrEmpty(tokenKey)) {
			Cache cache = ehcache.getCache(cacheName);
			cache.put(tokenKey, tokenValue);
		}
	}

	public static String getTokenFromNamedCache(EhCacheCacheManager ehcache,
			String cacheName, String tokenKey) {

		Cache cache = ehcache.getCache(cacheName);
		ValueWrapper tokenWrapper = cache.get(tokenKey);
		if (tokenWrapper == null) {
			throw new IllegalStateException("token is invalid");
		}

		String token = tokenWrapper.get().toString();
		return token;
	}

}