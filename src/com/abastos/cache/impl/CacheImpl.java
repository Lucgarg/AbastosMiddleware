package com.abastos.cache.impl;

import java.util.HashMap;

import java.util.Map;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.cache.Cache;



public class CacheImpl implements Cache{
	private static Logger logger = LogManager.getLogger(CacheImpl.class);
	private Map<Object, Object> cache = null;
	
	public CacheImpl() {
		
		cache = new HashMap<Object, Object>();
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
