package com.fx.rates.quoteservice;

import java.util.HashMap;

public enum ECurrency {


    EUR("EUR"),
    RON("RON"),
    GBP("GBP"),
    USD("USD");
    //todo add eur, ron, gbp, usd
    //add label property
    //add constructor with label property as parameter

    String label;

    ECurrency(String label){
        this.label = label;
    }

    public static ECurrency getByLabel(String toUpperCase) {
        for (ECurrency e: ECurrency.values()) {
            if (e.label.equals(toUpperCase)){
                return e;
            }
        }
        return null;
    }
}
