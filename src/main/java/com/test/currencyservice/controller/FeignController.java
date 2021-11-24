package com.test.currencyservice.controller;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.currencyservice.data.CurrenciesData;
import com.test.currencyservice.data.DateClass;
import com.test.currencyservice.util.FeignServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/main")
public class FeignController {
    DateClass dateClass = new DateClass();

    @Autowired
    private FeignServiceUtil feignServiceUtil;

    @GetMapping("/index")
    public String getCurrencies(Model model) throws JsonProcessingException {
        String data = feignServiceUtil.getCurrencies();
        ArrayList<CurrenciesData> currenciesData = new ArrayList<CurrenciesData>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(data);
        Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();

        while(fields.hasNext()) {
            CurrenciesData currency = new CurrenciesData();
            Map.Entry<String, JsonNode> field = fields.next();
            String   fieldName  = field.getKey();
            JsonNode fieldValue = field.getValue();
            currency.setKeyValue(fieldName);
            currency.setKeyValueExtended(fieldValue.asText());
            currenciesData.add(currency);
            //System.out.println(fieldName + " = " + fieldValue.asText());
        }
        model.addAttribute("data", currenciesData);
        return "currencies";
    }

    @GetMapping("/currency")
    public String getCurrency(@RequestParam("key") String key, Model model) throws JsonProcessingException {
        String data = feignServiceUtil.getCurrency("d50c32ae8c124a72ba400fc9164e78eb", key);
        String date = dateClass.getYesterdayDateString();
        String data1 = feignServiceUtil.getYesterdayCurrency(date, "d50c32ae8c124a72ba400fc9164e78eb", key);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(data);
        JsonNode rootNode1 = mapper.readTree(data1);
        JsonNode jsonNode = rootNode.get("rates");
        JsonNode jsonNode1 = rootNode1.get("rates");

        float rate = 0;
        float rate1 = 0;

        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        Iterator<Map.Entry<String, JsonNode>> fields1 = jsonNode1.fields();


        while(fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            Map.Entry<String, JsonNode> field1 = fields1.next();
            String   fieldName  = field.getKey();
            JsonNode fieldValue = field.getValue();
            rate = field.getValue().floatValue();
            rate1 = field1.getValue().floatValue();
            System.out.println(fieldName + " = " + fieldValue.asText());
        }

        model.addAttribute("currency", key);
        model.addAttribute("rate",rate);
        model.addAttribute("yesterdayRate", rate1);
        return "currency";
    }
}
