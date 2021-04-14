package com.abastos.cache;



import org.apache.commons.collections4.keyvalue.MultiKey;


public interface CacheManager {

	public  void put(String n, Object k, Object o2);
	public  Cache get(String n);
	public void remove(String n);
}
