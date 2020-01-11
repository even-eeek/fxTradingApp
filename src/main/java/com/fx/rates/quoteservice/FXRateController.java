package com.fx.rates.quoteservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class FXRateController {

    @Autowired
    IQuoteService quoteService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
            return "Hello";
    }


    /*@CrossOrigin
    //todo getRates: primaryCcy, secondaryCcy
    @RequestMapping(value = "/fx-rate", method = RequestMethod.GET), @RequestParam("primaryCcy") String primaryCcy
    public String h(){

    }*/
    //return RateVO
    @CrossOrigin
    @RequestMapping(value = "/fx-rate", method = RequestMethod.GET)
    @ResponseBody
    public RateVO x (@RequestParam("primaryCcy") String primaryCcy, @RequestParam("secondaryCcy") String secondaryCcy){
        return quoteService.getRate(primaryCcy, secondaryCcy);
    }


//    @CrossOrigin
    //todo getCurrencies
    //@RequestMapping("currencies"), RequestMethod
    //hint:ENUM.values()
    //return List<String>
    @CrossOrigin
    @RequestMapping(value = "/currencies", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getCurrencies(){
        List<String> curr = new ArrayList<String>();
        for (ECurrency e: ECurrency.values()) {
            curr.add(e.label);
        }
        return curr;
    }










}
