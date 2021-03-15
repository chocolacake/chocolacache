package com.chocolacake.chocolacache.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * This is the Cache Value
 */
@Data
@NoArgsConstructor
public class CacheValue implements Serializable {

    private String value;

    private Long lastModifiedTimestamp;

    private Long createTimestamp;

}
