package com.abastos.cache;

import org.apache.commons.collections4.keyvalue.MultiKey;

public interface EhCache {
	
	public void put(Object s, Object o2);
	
	public Object get(Object s);


	
}
