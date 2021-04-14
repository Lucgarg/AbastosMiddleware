package com.abastos.cache.impl;

import java.util.HashMap;

import java.util.Map;



import com.abastos.cache.Cache;
import com.abastos.cache.CacheManager;

public class CacheManagerImpl implements CacheManager {
	private static Map<String, Cache> caches = null;
	private CacheManagerImpl() {
		caches = new HashMap<String, Cache>();
	}
	public static CacheManager INSTANCE = null;
	public static CacheManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new CacheManagerImpl();
		}
		return INSTANCE;
	};
	@Override
	public void put(String n, Object k, Object o2) {
		Cache cache = new CacheImpl();
		cache.put(k, o2);
		caches.put(n, cache);
	}

	@Override
	public Cache get(String n) {
		 
		 Cache cache =  caches.get(n);
		if(cache == null) {
			cache = new CacheImpl();
			caches.put(n, cache);
			
		}
		return caches.get(n);
	}
	@Override
	public void remove(String n) {
	 caches.remove(n);

	}


}
