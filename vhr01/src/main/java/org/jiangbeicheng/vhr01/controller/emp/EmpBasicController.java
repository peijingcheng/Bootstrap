package org.jiangbeicheng.vhr01.controller.emp;

import org.jiangbeicheng.vhr01.bean.RespPageBean;
import org.jiangbeicheng.vhr01.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EmpBasicController
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/20 20:47
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,String keyword){
        return employeeService.getEmployeeByPage(page,size,keyword);
    }
    
}
