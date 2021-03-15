package com.chocolacake.chocolacache.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * This is the cache key
 */
@Data
@NoArgsConstructor
public class CacheKey implements Serializable {

    private String key;
}
