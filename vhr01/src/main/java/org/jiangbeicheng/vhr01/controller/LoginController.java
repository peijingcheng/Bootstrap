package org.jiangbeicheng.vhr01.controller;

import org.jiangbeicheng.vhr01.bean.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/11 22:34
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@RestController
public class LoginController {
    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录！");
    }
}
