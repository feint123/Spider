package com.feint.bean.meta;

/**
 * Created by feint on 17/6/17.
 */
public class NumberLong {
    private long $numberLong;

    public NumberLong(){}


    public NumberLong(long hid) {
        $numberLong=hid;
    }

    public long get$numberLong() {
        return $numberLong;
    }

    public void set$numberLong(long $numberLong) {
        this.$numberLong = $numberLong;
    }
}
