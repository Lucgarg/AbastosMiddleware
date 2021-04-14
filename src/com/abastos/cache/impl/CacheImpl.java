package com.abastos.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.keyvalue.MultiKey;

import com.abastos.cache.Cache;

public class CacheImpl implements Cache{
	private Map<Object, Object> cache = null;
	public CacheImpl() {
		cache = new HashMap<Object, Object>();
	}

	@Override
	public void put(Object o, Object o2) {
		cache.put(o, o2);
		
	}

	@Override
	public Object get(Object o) {
		
		return cache.get(o);
	}



}
