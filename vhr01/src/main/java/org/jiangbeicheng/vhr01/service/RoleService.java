package org.jiangbeicheng.vhr01.service;

import org.jiangbeicheng.vhr01.bean.Role;
import org.jiangbeicheng.vhr01.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/16 19:22
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        return roleMapper.insert(role);
    }

    public Integer deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
