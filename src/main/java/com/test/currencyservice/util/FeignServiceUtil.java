package com.test.currencyservice.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "currencies", url = "https://openexchangerates.org/api")
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
