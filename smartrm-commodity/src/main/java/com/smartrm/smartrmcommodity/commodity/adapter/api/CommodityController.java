package com.smartrm.smartrmcommodity.commodity.adapter.api;

import com.smartrm.infracore.api.CommonResponse;
import com.smartrm.smartrmcommodity.commodity.application.dto.CommodityInfoDto;
import com.smartrm.smartrmcommodity.commodity.application.dto.ListCommodityByIdQueryDto;
import com.smartrm.smartrmcommodity.commodity.application.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: yoda
 * @description: 商品服务入口
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    
    @Autowired
    private CommodityService commodityService;
    
    @RequestMapping(value = "/detail/{commodityId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<CommodityInfoDto> getDetail(@PathVariable String commodityId) {
        CommodityInfoDto commodityInfoDto = commodityService.getCommodityDetail(commodityId);
        return CommonResponse.success(commodityInfoDto);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<List<CommodityInfoDto>> getCommodityList(@RequestBody
                                                                   ListCommodityByIdQueryDto request) {
        return CommonResponse.success(commodityService.getCommodityList(request.getIds()));
    }
}
