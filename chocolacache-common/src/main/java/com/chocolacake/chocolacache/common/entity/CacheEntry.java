package com.chocolacake.chocolacache.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CacheEntry implements Serializable {

    private CacheKey cacheKey;

    private CacheValue cacheValue;
}
