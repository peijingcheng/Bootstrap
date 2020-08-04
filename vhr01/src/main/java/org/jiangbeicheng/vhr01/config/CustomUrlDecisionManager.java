package org.jiangbeicheng.vhr01.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ClassName MyDesicionManager
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/13 20:07
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            String needRole = configAttribute.getAttribute();
            if("ROLB_LOGIN".equals(needRole)){
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登录，请登录！");
                }else{
                    return;
                }
            }
            Collection<?extends GrantedAuthority> authorities=authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足请联系管理员！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
