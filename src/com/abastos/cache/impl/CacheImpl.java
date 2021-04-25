package com.abastos.cache.impl;

import java.util.HashMap;

import java.util.Map;



import com.abastos.cache.Cache;



public class CacheImpl implements Cache{

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
