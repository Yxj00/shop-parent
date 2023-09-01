package com.csi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class SentinelController {
    @RequestMapping("/sentinel1")
    public String sentinel1() throws InterruptedException {
        //模拟⼀次⽹络延时
        TimeUnit.SECONDS.sleep(1);
        return "sentinel1";
    }
    @RequestMapping("/sentinel2")
    public String sentinel2()  {
        return "sentinel2";
    }
}
