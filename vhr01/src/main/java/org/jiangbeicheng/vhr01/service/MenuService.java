package org.jiangbeicheng.vhr01.service;

import org.jiangbeicheng.vhr01.bean.Hr;
import org.jiangbeicheng.vhr01.bean.Menu;
import org.jiangbeicheng.vhr01.mapper.MenuMapper;
import org.jiangbeicheng.vhr01.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName MenuService
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/12 19:48
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;
    public List<Menu> getMenusByHrId(){
        return menuMapper.getMenusByHrId(((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    //@Cacheable
    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }


    public List<Integer> getMidsByRid(Integer rid) {
        return  menuMapper.getMidsByRid(rid);
    }
    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        Integer result=menuRoleMapper.insertRecord(rid,mids);
        return  result==mids.length;
    }
}
