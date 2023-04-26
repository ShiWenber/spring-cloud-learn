package edu.ynu.controller;

import edu.ynu.entity.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerServiceFallback {
    @RequestMapping(value = "/fallback", method = RequestMethod.GET)
    public CommonResult getCommonResult() {
        return new CommonResult<>(403, "由于ConsumerService服务异常，进行降级", null);
    }
}
