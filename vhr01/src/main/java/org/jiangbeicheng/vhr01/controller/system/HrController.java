package org.jiangbeicheng.vhr01.controller.system;

import org.jiangbeicheng.vhr01.bean.Hr;
import org.jiangbeicheng.vhr01.bean.RespBean;
import org.jiangbeicheng.vhr01.bean.Role;
import org.jiangbeicheng.vhr01.service.HrService;
import org.jiangbeicheng.vhr01.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName HrController
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/19 22:51
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;
    @Autowired
    RoleService roleService;
    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords){
        return hrService.getAllHrs(keywords);
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if(hrService.updateHr(hr)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.ok("更新失败");
    }
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
    @PutMapping("/role")
    public RespBean updateHrRoles(Integer hrid,Integer[] rids){
        if(hrService.updateHrRoles(hrid,rids)){
            return  RespBean.ok("更新成功");
        }
        return  RespBean.error("更新失败");
    }
    @DeleteMapping("/")
    public RespBean deleteHrById(@PathVariable Integer id){
            if(hrService.deleteHrById(id)==1){
                    return RespBean.ok("删除成功");
            }
        return RespBean.error("删除失败");
    }
}
