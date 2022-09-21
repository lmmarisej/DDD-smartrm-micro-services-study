package com.smartrm.smartrmtrade.trade.adapter.remote;

import com.smartrm.infracore.api.CommonError;
import com.smartrm.infracore.api.CommonResponse;
import com.smartrm.infracore.exception.DomainException;
import com.smartrm.smartrmtrade.trade.application.dto.PopCommodityCmdDto;
import com.smartrm.smartrmtrade.trade.application.dto.VendingMachineDto;
import com.smartrm.smartrmtrade.trade.domain.service.TradeDeviceService;
import com.smartrm.smartrmtrade.trade.domain.share.InventoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
@Service
public class TradeDeviceServiceImpl implements TradeDeviceService {
    
    @Autowired
    private RestTemplate deviceRestTemplate;
    
    @Override
    public List<InventoryInfo> getInventory(long machineId) {
        String path = "/inventory/" + machineId;
        ParameterizedTypeReference<CommonResponse<VendingMachineDto>> reference = new ParameterizedTypeReference<CommonResponse<VendingMachineDto>>() {
        };
        ResponseEntity<CommonResponse<VendingMachineDto>> response = deviceRestTemplate
                .exchange(path, HttpMethod.GET, null, reference);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new DomainException(CommonError.UnExpected)
                    .withMsg(response.getStatusCode().toString());
        } else if (response.getBody().getCode() != CommonError.NoError.getCode()) {
            throw new DomainException(CommonError.UnExpected).withMsg(response.getBody().getMsg());
        }
        return response.getBody().getData().getInventory();
    }
    
    @Override
    public void popCommodity(long machineId, String commodityId, long orderId)
            throws Exception {
        PopCommodityCmdDto cmd = new PopCommodityCmdDto();
        cmd.setCommodityId(commodityId);
        cmd.setMachineId(machineId);
        cmd.setOrderId(orderId);
        ParameterizedTypeReference<CommonResponse<VendingMachineDto>> reference = new ParameterizedTypeReference<CommonResponse<VendingMachineDto>>() {
        };
        ResponseEntity<CommonResponse<VendingMachineDto>> response = deviceRestTemplate
                .exchange("/pop", HttpMethod.POST, new HttpEntity(cmd), reference);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new DomainException(CommonError.UnExpected)
                    .withMsg(response.getStatusCode().toString());
        } else if (response.getBody().getCode() != CommonError.NoError.getCode()) {
            throw new DomainException(CommonError.UnExpected).withMsg(response.getBody().getMsg());
        }
    }
    
    @Override
    public void openCabinetVendingMachine(long machineId) throws DomainException {
        String path = "/open/" + machineId;
        ParameterizedTypeReference<CommonResponse<VendingMachineDto>> reference = new ParameterizedTypeReference<CommonResponse<VendingMachineDto>>() {
        };
        ResponseEntity<CommonResponse<VendingMachineDto>> response = deviceRestTemplate
                .exchange(path, HttpMethod.POST, null, reference);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new DomainException(CommonError.UnExpected)
                    .withMsg(response.getStatusCode().toString());
        } else if (response.getBody().getCode() != CommonError.NoError.getCode()) {
            throw new DomainException(CommonError.UnExpected).withMsg(response.getBody().getMsg());
        }
    }
}
