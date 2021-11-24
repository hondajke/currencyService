package com.test.currencyservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.currencyservice.data.CurrenciesData;
import com.test.currencyservice.data.DateClass;
import com.test.currencyservice.json.CurrenciesJsonParser;
import com.test.currencyservice.util.FeignServiceUtil;
import com.test.currencyservice.util.GifsServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class FeignController {
    DateClass dateClass = new DateClass();
    CurrenciesJsonParser jsonParser = new CurrenciesJsonParser();

    @Autowired
    private FeignServiceUtil feignServiceUtil;
    @Autowired
    private GifsServiceUtil gifsServiceUtil;

    @GetMapping("/index")
    public String getCurrencies(Model model) throws JsonProcessingException {
        String data = feignServiceUtil.getCurrencies();
        ArrayList<CurrenciesData> currenciesData = jsonParser.getCurrenciesFromJson(data);
        model.addAttribute("data", currenciesData);
        return "currencies";
    }

    @GetMapping("/currency")
    public String getCurrency(@RequestParam("key") String key, Model model) throws JsonProcessingException {
        String data = feignServiceUtil.getCurrency("d50c32ae8c124a72ba400fc9164e78eb", key);
        String quote = "dunno";
        String date = dateClass.getYesterdayDateString();
        String data1 = feignServiceUtil.getYesterdayCurrency(date, "d50c32ae8c124a72ba400fc9164e78eb", key);
        float rate = jsonParser.getRatesValueFromJson(data);
        float rate1 = jsonParser.getRatesValueFromJson(data1);

        if(rate > rate1) quote = "rich";
        else if(rate < rate1) quote = "broke";
        String gifData = gifsServiceUtil.getGif("ULE3CDTr1h56WGPaOVIBwfmNYLMluEAD", quote);
        String gifURL = jsonParser.getGifURLFromJson(gifData);

        model.addAttribute("gif_data", gifURL);
        model.addAttribute("currency", key);
        model.addAttribute("rate",rate);
        model.addAttribute("yesterdayRate", rate1);

        return "currency";
    }
}
