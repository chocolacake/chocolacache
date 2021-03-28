package com.chocolacake.chocolacache.server.cache;


import com.chocolacake.chocolacache.common.entity.CacheEntry;
import com.chocolacake.chocolacache.protocol.utils.DateUtil;

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

    public void putCacheEntry(String key, String value) {
        long currentTimeStamp = DateUtil.getCurrentTimeStamp();
        CacheEntry cacheEntry = CacheEntry.builder()
                .key(key)
                .value(value)
                .createTimestamp(currentTimeStamp)
                .lastModifiedTimestamp(currentTimeStamp)
                .build();
        cacheMap.put(key, cacheEntry);
    }
}
