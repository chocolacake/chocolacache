package com.chocolacake.chocolacache.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CacheEntry implements Serializable {

    private String key;
    private String value;

    private Long lastModifiedTimestamp;
    private Long createTimestamp;
}
