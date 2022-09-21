package com.smartrm.smartrmcommodity.commodity.adapter;

import com.smartrm.smartrmcommodity.commodity.domain.model.Commodity;

import java.io.IOException;

/**
 * @author: yoda
 * @description:
 */
public interface CommodityProcessor {
    
    public void onCommodity(Commodity commodity) throws IOException;
    
}
