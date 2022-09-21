package com.smartrm.smartrmcommodity.commodity.adapter.convertor;

import com.smartrm.smartrmcommodity.commodity.domain.model.Property;

/**
 * @author: yoda
 * @description:
 */
public interface CommodityPropertyDumper<T> {
    
    void dump(Property<T> from);
    
}
