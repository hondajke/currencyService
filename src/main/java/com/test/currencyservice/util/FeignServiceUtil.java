package com.test.currencyservice.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@PropertySource("classpath:service.properties")
@FeignClient(value = "currencies", url = "${currency_api_url}")
public interface FeignServiceUtil {

    @GetMapping(value = "/currencies.json")
    String getCurrencies();

    @GetMapping(value = "/latest.json")
    String getCurrency(@RequestParam("app_id") String app_id,
                       @RequestParam("symbols") String key);

    @GetMapping(value = "/historical/{date}.json")
    String getYesterdayCurrency(@PathVariable String date,
                                @RequestParam("app_id") String app_id,
                                @RequestParam("symbols") String key);
}
