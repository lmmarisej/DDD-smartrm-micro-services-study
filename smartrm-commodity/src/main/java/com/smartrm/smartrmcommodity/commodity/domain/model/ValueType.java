package com.smartrm.smartrmcommodity.commodity.domain.model;

public interface ValueType<T> {
    
    ValueTypeCode getType();
    
    Class<T> getValueClass();
    
}
