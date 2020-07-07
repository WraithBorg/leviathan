package com.io.hydralisk.eum;

public enum  OrderState {

    UN_PAY(101, "unpay","待付款"),
    UN_PIN(202, "unpin","待成团"),
    UN_SEND(304, "unsend","待发货"),
    UN_RECEIVE(406, "unreceive","待收货"),
    UN_RATY(508, "unraty","待评价"),
    ;
    public  Integer id;
    public String val;
    public String text;

    OrderState(Integer id,String val, String text) {
        this.id = id;
        this.text = text;
        this.val = val;
    }
    public static Integer getId(String val){
        for (OrderState s : values()){
            if (s.val.equals(val)){
                return s.id;
            }
        }
        return null;
    }
}
