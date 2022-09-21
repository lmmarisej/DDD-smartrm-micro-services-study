package com.smartrm.smartrmcommodity.commodity.adapter.convertor.json;

import com.smartrm.smartrmcommodity.commodity.domain.model.Property;

/**
 * @author: yoda
 * @description:
 */
public class FloatPropertyJsonDumper extends PropertyJsonDumper<Double> {
    
    @Override
    public void dump(Property<Double> from) {
        if (from.getMaxRepeat() > 1) {
            root.withArray(from.getName()).add(from.getValue().toString());
        } else {
            root.put(from.getName(), from.getValue());
        }
    }
}
