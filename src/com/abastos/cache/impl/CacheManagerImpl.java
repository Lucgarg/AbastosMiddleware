package com.abastos.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.xml.XmlConfiguration;

import com.abastos.cache.CacheManager;
import com.abastos.cache.EhCache;

public class CacheManagerImpl implements CacheManager {
	private static Map<String, EhCache> caches = null;
	private static Logger logger = LogManager.getLogger(CacheImpl.class);
	private CacheManagerImpl() {
		
		caches = new HashMap<String, EhCache>();
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
		
		EhCache cache = new CacheImpl(new EhCacheAdapter());
		cache.put(k, o2);
		
		caches.put(n, cache);
	}

	@Override
	public EhCache get(String n) {
		 
		 EhCache cache =  caches.get(n);
		if(cache == null) {
			cache = new CacheImpl(new EhCacheAdapter());
		
			caches.put(n, cache);
			
		}
		return caches.get(n);
	}
	@Override
	public void remove(String n) {
	 caches.remove(n);

	}


}
