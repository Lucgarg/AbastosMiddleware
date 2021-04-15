package com.abastos.cache.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;

import com.abastos.cache.EhCache;

public class CacheImpl implements EhCache{
	private static Logger logger = LogManager.getLogger(CacheImpl.class);
	private Cache cache = null;
	public CacheImpl(Cache cache) {
	
		this.cache = cache;

	}

	@Override
	public void put(Object o, Object o2) {
		
		this.cache.put(o, o2);
	
	}

	@Override
	public Object get(Object o) {
		
		return this.cache.get(o);
	}



}
