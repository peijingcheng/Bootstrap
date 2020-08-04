package org.jiangbeicheng.vhr01.controller.system.basic;

import org.jiangbeicheng.vhr01.bean.Menu;
import org.jiangbeicheng.vhr01.bean.RespBean;
import org.jiangbeicheng.vhr01.bean.Role;
import org.jiangbeicheng.vhr01.service.MenuService;
import org.jiangbeicheng.vhr01.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PermissController
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/16 19:18
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }
    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids){
        if(menuService.updateMenuRole(rid,mids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.addRole(role)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(Integer rid){
        if(roleService.deleteRoleById(rid)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.ok("删除失败");
    }
}
