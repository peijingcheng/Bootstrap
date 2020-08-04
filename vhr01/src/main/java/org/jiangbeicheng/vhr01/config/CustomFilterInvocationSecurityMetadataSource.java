package org.jiangbeicheng.vhr01.config;

import org.jiangbeicheng.vhr01.bean.Menu;
import org.jiangbeicheng.vhr01.bean.Role;
import org.jiangbeicheng.vhr01.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName MyFilter
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/13 19:22
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
/*
 * 这个类的作用，主要是根据用户传来的请求地址，分析出请求需要的角色
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
    AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //请求的路径
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menus = menuService.getAllMenusWithRole();
        for (Menu menu : menus) {
            if(antPathMatcher.match(menu.getUrl(),requestUrl)){

                List<Role> roles = menu.getRoles();

                String[] str =new String[roles.size()];

                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }

        return SecurityConfig.createList("ROLB_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
