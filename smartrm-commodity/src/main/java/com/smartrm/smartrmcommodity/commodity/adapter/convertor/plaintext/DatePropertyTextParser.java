package com.smartrm.smartrmcommodity.commodity.adapter.convertor.plaintext;

import com.smartrm.infracore.exception.DomainException;
import com.smartrm.smartrmcommodity.commodity.adapter.convertor.CommodityPropertyParser;
import com.smartrm.smartrmcommodity.commodity.domain.model.DateParser;
import com.smartrm.smartrmcommodity.commodity.infrastructure.CommodityError;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

/**
 * @author: yoda
 * @description:
 */
public class DatePropertyTextParser implements
        CommodityPropertyParser<LocalDate, String> {
    
    @Override
    public LocalDate parse(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        
        LocalDate date = DateParser.asLocalDate(value);
        if (date == null) {
            throw new DomainException(CommodityError.CommodityParseError);
        }
        return date;
    }
}
