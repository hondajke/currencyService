package com.test.currencyservice.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.currencyservice.data.CurrenciesData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class CurrenciesJsonParser {
    ObjectMapper mapper = new ObjectMapper();

    public ArrayList<CurrenciesData> getCurrenciesFromJson(String data) throws JsonProcessingException {
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
        }

        return currenciesData;
    }

    public float getRatesValueFromJson(String data) throws JsonProcessingException {
        JsonNode rootNode = mapper.readTree(data);
        JsonNode jsonNode = rootNode.get("rates");

        float rate = 0;

        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

        while(fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            rate = field.getValue().floatValue();
        }
        return rate;
    }
    public String getGifURLFromJson(String data) throws JsonProcessingException {
        JsonNode rootNode = mapper.readTree(data);
        String url = null;
        url = rootNode.get("data").get("images").get("fixed_height").get("url").asText();

        return url;
    }
}
