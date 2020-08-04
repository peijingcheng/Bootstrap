package org.jiangbeicheng.vhr01.service;

import org.jiangbeicheng.vhr01.bean.Department;
import org.jiangbeicheng.vhr01.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DepartmentService
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/18 18:38
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public void addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
    }

    public void deleteDepById(Department dep) {
        departmentMapper.deleteDepById(dep);
    }
}
