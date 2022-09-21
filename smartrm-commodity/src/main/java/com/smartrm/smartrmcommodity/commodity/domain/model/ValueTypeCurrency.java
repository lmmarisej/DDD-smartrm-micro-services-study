package com.smartrm.smartrmcommodity.commodity.domain.model;

import java.math.BigDecimal;

public class ValueTypeCurrency implements ValueType<BigDecimal> {
    
    @Override
    public ValueTypeCode getType() {
        return ValueTypeCode.CURRENCY;
    }
    
    @Override
    public Class<BigDecimal> getValueClass() {
        return BigDecimal.class;
    }
    
}
