package com.io.hydralisk.eum;

/**
 * 支付状态枚举
 */
public enum PayStatus {
    WAIT_PAY(0, "待付款"),
    ;
    public  Integer id;
    public String text;

    PayStatus(Integer id, String text) {
        this.id = id;
        this.text = text;
    }
    public  static String getText(Integer s) {
        for (PayStatus val : values()) {
            if (val.id.equals(s)){
                return val.text;
            }
        }
        return "";
    }


}
