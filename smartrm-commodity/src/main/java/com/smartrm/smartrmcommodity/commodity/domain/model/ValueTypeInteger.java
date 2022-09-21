package com.smartrm.smartrmcommodity.commodity.domain.model;

public class ValueTypeInteger implements ValueType<Long> {
    
    @Override
    public ValueTypeCode getType() {
        return ValueTypeCode.INTEGER;
    }
    
    @Override
    public Class<Long> getValueClass() {
        return Long.class;
    }
    
}
