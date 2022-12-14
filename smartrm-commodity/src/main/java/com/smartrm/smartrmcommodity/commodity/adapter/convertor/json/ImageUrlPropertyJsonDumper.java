package com.smartrm.smartrmcommodity.commodity.adapter.convertor.json;

import com.smartrm.smartrmcommodity.commodity.domain.model.ImageUrl;
import com.smartrm.smartrmcommodity.commodity.domain.model.Property;

/**
 * @author: yoda
 * @description:
 */
public class ImageUrlPropertyJsonDumper extends PropertyJsonDumper<ImageUrl> {
    
    @Override
    public void dump(Property<ImageUrl> from) {
        if (from.getMaxRepeat() > 1) {
            root.withArray(from.getName()).addPOJO(from.getValue());
        } else {
            root.putPOJO(from.getName(), from.getValue());
        }
    }
    
}
