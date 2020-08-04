package org.jiangbeicheng.vhr01.utils;

import org.jiangbeicheng.vhr01.bean.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName HrUtils
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/19 22:58
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
public class HrUtils {
    public static Hr getCurrentHr(){
       return ((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
