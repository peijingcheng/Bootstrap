package org.jiangbeicheng.vhr01.controller.config;

import org.jiangbeicheng.vhr01.bean.Menu;
import org.jiangbeicheng.vhr01.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SystemConfigController
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/12 19:45
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    @Autowired
    MenuService menuService;
    @GetMapping("/menu")
    public List<Menu> getMenusByHrId(){
        return menuService.getMenusByHrId();
    }
}
