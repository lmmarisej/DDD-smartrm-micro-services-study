package com.smartrm.smartrmcommodity.commodity.adapter.convertor.plaintext;

import com.smartrm.infracore.exception.DomainException;
import com.smartrm.smartrmcommodity.commodity.adapter.convertor.CommodityPropertyParser;
import com.smartrm.smartrmcommodity.commodity.infrastructure.CommodityError;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author: yoda
 * @description:
 */
public class IntegerPropertyTextParser implements
        CommodityPropertyParser<Long, String> {
    
    @Override
    public Long parse(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        
        try {
            return NumberUtils.createLong(content);
        } catch (NumberFormatException e) {
            throw new DomainException(CommodityError.CommodityParseError);
        }
    }
}
