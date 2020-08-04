package org.jiangbeicheng.vhr01.controller.system.basic;

import org.jiangbeicheng.vhr01.bean.Department;
import org.jiangbeicheng.vhr01.bean.RespBean;
import org.jiangbeicheng.vhr01.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DepartmentController
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/18 18:21
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep){
        departmentService.addDep(dep);
        if(dep.getResult()==1){
            return RespBean.ok("添加成功",dep);
        }
        return RespBean.error("添加失败");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Integer id){
        Department dep = new Department();
        dep.setId(id);
        departmentService.deleteDepById(dep);
        if(dep.getResult()==-2){
            return RespBean.error("该部门有子部门，删除失败");
        }else if(dep.getResult()==-1){
            return RespBean.error("该部门有员工，删除失败");
        }else if(dep.getResult()==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
