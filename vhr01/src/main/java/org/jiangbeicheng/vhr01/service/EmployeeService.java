package org.jiangbeicheng.vhr01.service;

import org.jiangbeicheng.vhr01.bean.Employee;
import org.jiangbeicheng.vhr01.bean.RespBean;
import org.jiangbeicheng.vhr01.bean.RespPageBean;
import org.jiangbeicheng.vhr01.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeService
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/20 20:54
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword) {
        if(page!=null && size!=null){
            page=(page-1)*size;
        }
        List<Employee> data =employeeMapper.getEmployeeByPage(page,size,keyword);
        Long total=employeeMapper.getTotal(keyword);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
