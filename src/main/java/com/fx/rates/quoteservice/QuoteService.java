package com.fx.rates.quoteservice;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class QuoteService implements IQuoteService {
    private static final float MAX_VALUE_FOR_DELTA = 0.9F;
    private static final Map<ECurrency, Map<ECurrency, Float>> RATES = new HashMap<ECurrency, Map<ECurrency, Float>>();
    private static final Map<ECurrency, Float> EUR_RATES = new HashMap<ECurrency, Float>();
    private static final Map<ECurrency, Float> USD_RATES = new HashMap<ECurrency, Float>();
    private static final Map<ECurrency, Float> GBP_RATES = new HashMap<ECurrency, Float>();
    private static final Map<ECurrency, Float> RON_RATES = new HashMap<ECurrency, Float>();

    // Static block is used for initializing the static variables.
    //This block gets executed when the class is loaded in the memory
    //can we have more than one?

    static {
        EUR_RATES.put(ECurrency.GBP, 0.9F);
        EUR_RATES.put(ECurrency.USD, 1.18F);
        EUR_RATES.put(ECurrency.RON, 4.66F);
        EUR_RATES.put(ECurrency.EUR, 1F);
        RATES.put(ECurrency.EUR, EUR_RATES);

        USD_RATES.put(ECurrency.GBP, 0.76F);
        USD_RATES.put(ECurrency.RON, 3.96F);
        USD_RATES.put(ECurrency.USD, 1F);
        RATES.put(ECurrency.USD, USD_RATES);

        GBP_RATES.put(ECurrency.GBP, 1F);
        GBP_RATES.put(ECurrency.RON, 5.18F);
        RATES.put(ECurrency.GBP, GBP_RATES);

        RON_RATES.put(ECurrency.RON, 1F);
        RATES.put(ECurrency.RON, RON_RATES);
    }


    public RateVO getRate(String fromS, String toS) {
        ECurrency from = ECurrency.getByLabel(fromS.toUpperCase());
        ECurrency to = ECurrency.getByLabel(toS.toUpperCase());

        //received unknown currency
        if (from == null || to == null) {
            return null;
        }


        Map<ECurrency, Float> fromRates = RATES.get(from);
        Float baseRate = fromRates.get(to);

        //received Unkown Currency Pair
        if (baseRate == null && RATES.get(to).get(from) == null) {
            return null;
        }

        if (baseRate == null) {
            baseRate = 1.0F/RATES.get(to).get(from);
        }


        float buyRate = baseRate != 1 ? baseRate + getRandomizedDelta(0, baseRate) : 1;
        float sellRate = baseRate != 1 ? baseRate - getRandomizedDelta(0, baseRate) : 1;
        return new RateVO(buyRate, sellRate);



    }

    private static float getRandomizedDelta(float min, float max) {

        Random rand = new Random();

        float result = rand.nextFloat() * (max - min) + min ;

        return result;

    }
}
