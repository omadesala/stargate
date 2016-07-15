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

	public static void update(EhCacheCacheManager ehcache,
			String cacheUseridToken, String userId, String token) {

		if (!Strings.isNullOrEmpty(token)) {

			Cache cache = ehcache.getCache(cacheUseridToken);

			// // 1. if oldtoken by userid is exist.
			// ValueWrapper valueWrapper = cache.get(userId);
			// String oldtoken = "";
			// if (valueWrapper != null && valueWrapper.get() != null) {
			// oldtoken = valueWrapper.get().toString();
			// // 2. remove oldtoken
			// cache.evict(oldtoken);
			// }

			// 3. update with new token(token -> userid)
			cache.put(token, userId);

			// 4. update userid->tokenf
			// cache.put(userId, token);
		}
	}

	public static String getUserIdByToken(EhCacheCacheManager ehcache,
			String cacheUseridToken, String token) {

		Cache cache = ehcache.getCache(cacheUseridToken);
		ValueWrapper useridWrapper = cache.get(token);
		if (useridWrapper == null) {
			throw new IllegalStateException("token is invalid");
		}

		String userid = useridWrapper.get().toString();
		return userid;
	}

}