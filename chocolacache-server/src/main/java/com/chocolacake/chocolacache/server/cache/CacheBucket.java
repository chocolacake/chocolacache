package com.chocolacake.chocolacache.server.cache;


import com.chocolacake.chocolacache.common.entity.CacheEntry;

import java.util.HashMap;
import java.util.Map;

/**
 * The cache bucket store the cache value
 */
public class CacheBucket {

    private Map<String, CacheEntry> cacheMap = new HashMap<>();


    public CacheEntry getCacheEntryByKey(String key) {
        return cacheMap.get(key);
    }
}
