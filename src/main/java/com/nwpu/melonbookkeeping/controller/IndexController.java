package com.nwpu.melonbookkeeping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author noorall
 * @date 2021/1/79:36 下午
 * @Description: 首页控制器
 */
@Controller
public class IndexController {
    /**
     * 全站首页
     * @return 首页
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * API页面
     * @return api页面
     */
    @RequestMapping("/api")
    public String api(){
        return "api";
    }
}
