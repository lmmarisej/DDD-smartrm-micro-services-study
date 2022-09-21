package com.smartrm.smartrmtrade.trade.adapter.remote;

import com.smartrm.infracore.api.CommonError;
import com.smartrm.infracore.api.CommonResponse;
import com.smartrm.infracore.exception.DomainException;
import com.smartrm.smartrmtrade.trade.application.dto.CommodityInfoDto;
import com.smartrm.smartrmtrade.trade.application.dto.ListCommodityByIdQueryDto;
import com.smartrm.smartrmtrade.trade.domain.service.TradeCommodityService;
import com.smartrm.smartrmtrade.trade.domain.share.CommodityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description:
 */
@Service
public class TradeCommodityServiceImpl implements TradeCommodityService {
    
    @Autowired
    private RestTemplate commodityRestTemplate;
    
    @Override
    public CommodityInfo getCommodityDetail(String commodityId) {
        String path = "/detail/" + commodityId;
        ParameterizedTypeReference<CommonResponse<CommodityInfoDto>> reference = new ParameterizedTypeReference<CommonResponse<CommodityInfoDto>>() {
        };
        ResponseEntity<CommonResponse<CommodityInfoDto>> response = commodityRestTemplate
                .exchange(path, HttpMethod.GET, null, reference);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new DomainException(CommonError.UnExpected)
                    .withMsg(response.getStatusCode().toString());
        } else if (response.getBody().getCode() != CommonError.NoError.getCode()) {
            throw new DomainException(CommonError.UnExpected).withMsg(response.getBody().getMsg());
        }
        CommodityInfoDto dto = response.getBody().getData();
        return new CommodityInfo(dto.getCommodityId(), dto.getCommodityName(), dto.getImageUrl(),
                dto.getPrice());
    }
    
    @Override
    public List<CommodityInfo> getCommodityList(List<String> commodityIds) {
        String path = "/list";
        ListCommodityByIdQueryDto query = new ListCommodityByIdQueryDto();
        query.setIds(commodityIds);
        ParameterizedTypeReference<CommonResponse<List<CommodityInfoDto
                >>> reference = new ParameterizedTypeReference<CommonResponse<List<CommodityInfoDto>>>() {
        };
        ResponseEntity<CommonResponse<List<CommodityInfoDto>>> response = commodityRestTemplate
                .exchange(path, HttpMethod.POST, new HttpEntity<>(query), reference);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new DomainException(CommonError.UnExpected)
                    .withMsg(response.getStatusCode().toString());
        } else if (response.getBody().getCode() != CommonError.NoError.getCode()) {
            throw new DomainException(CommonError.UnExpected).withMsg(response.getBody().getMsg());
        }
        List<CommodityInfoDto> dtos = response.getBody().getData();
        return dtos.stream().map(
                dto -> new CommodityInfo(dto.getCommodityId(), dto.getCommodityName(), dto.getImageUrl(),
                        dto.getPrice())).collect(Collectors.toList());
    }
}
