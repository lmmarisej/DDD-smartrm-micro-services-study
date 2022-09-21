package com.smartrm.smartrmtrade.trade.domain.share;

/**
 * @author: yoda
 * @description:
 */
public enum VendingMachineType {
    SLOT(0),
    CABINET(1);
    
    private final int code;
    
    private VendingMachineType(int code) {
        this.code = code;
    }
    
    public static VendingMachineType of(int code) {
        for (VendingMachineType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
    
    public int code() {
        return this.code;
    }
}
