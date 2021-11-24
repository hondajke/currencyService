package com.test.currencyservice.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gifs", url = "api.giphy.com/v1/gifs")
public interface GifsServiceUtil {
    String app_id = "ULE3CDTr1h56WGPaOVIBwfmNYLMluEAD";

    @GetMapping("/random")
    String getGif(@RequestParam("api_key") String api_key,
                      @RequestParam("tag") String tag);
}
