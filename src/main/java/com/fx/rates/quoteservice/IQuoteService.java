package com.fx.rates.quoteservice;

public interface IQuoteService {
    RateVO getRate(String fromS, String toS);
}
