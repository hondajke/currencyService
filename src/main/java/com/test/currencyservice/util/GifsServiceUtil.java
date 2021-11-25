package com.test.currencyservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PropertySource("classpath:service.properties")
@FeignClient(value = "gifs", url = "${gifs_api_url}")
public interface GifsServiceUtil {
    @Value("${gifs_api_key}")
    String app_id = null;

    @GetMapping("/random")
    String getGif(@RequestParam("api_key") String api_key,
                      @RequestParam("tag") String tag);
}
