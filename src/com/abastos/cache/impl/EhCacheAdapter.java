package com.abastos.cache.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.ehcache.Cache;
import org.ehcache.config.CacheRuntimeConfiguration;
import org.ehcache.spi.loaderwriter.BulkCacheLoadingException;
import org.ehcache.spi.loaderwriter.BulkCacheWritingException;
import org.ehcache.spi.loaderwriter.CacheLoadingException;
import org.ehcache.spi.loaderwriter.CacheWritingException;

public class EhCacheAdapter implements Cache{
	private Map cache = null;

	public EhCacheAdapter() {
		cache = new HashMap<Object, Object>();

	}

	@Override
	public Object get(Object key) throws CacheLoadingException {
	return	cache.get(key);
	}

	@Override
	public void put(Object key, Object value) throws CacheWritingException {
		cache.put(key, value);
		
	}

	@Override
	public boolean containsKey(Object key) {
		return cache.containsKey(key);
	}

	@Override
	public void remove(Object key) throws CacheWritingException {
		cache.remove(key);
		
	}

	@Override
	public Map getAll(Set keys) throws BulkCacheLoadingException {
		
		return null;
	}

	@Override
	public void putAll(Map entries) throws BulkCacheWritingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll(Set keys) throws BulkCacheWritingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object putIfAbsent(Object key, Object value) throws CacheLoadingException, CacheWritingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object key, Object value) throws CacheWritingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object replace(Object key, Object value) throws CacheLoadingException, CacheWritingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean replace(Object key, Object oldValue, Object newValue)
			throws CacheLoadingException, CacheWritingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CacheRuntimeConfiguration getRuntimeConfiguration() {
		
		return null;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
