package com.abastos.cache;


public interface CacheManager {

	public  void put(String n, Object k, Object o2);
	public  Cache get(String n);
	public void remove(String n);
}
