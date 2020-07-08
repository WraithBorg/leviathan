package com.io.hydralisk.result;

import com.io.hydralisk.util.CCommonUtils;

public class Test {
    public static void main(String[] args) {

        MsgResult.warn("",null).then(e -> {
            System.out.println("1");
        }).then(e -> {
            System.out.println("2");
            return MsgResult.warn("warn...");
        }).then(e -> {
            System.out.println("3");
            return MsgResult.fail("fail...");
        }).then(e -> {
            System.out.println("4");
        });


    }
}
