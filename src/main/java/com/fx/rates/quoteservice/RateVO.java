package com.fx.rates.quoteservice;

import java.util.Date;

public class RateVO {

    //todo add buyRate, sellRate, ts
    private double buyRate;
    private double sellRate;
    private Date ts;

    public double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    public double getSellRate() {
        return sellRate;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public RateVO(double buyRate, double sellRate) {
        setBuyRate(buyRate);
        setSellRate(sellRate);
        setTs(new Date());
    }

    //todo add constructor with parameters
}
