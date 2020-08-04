package org.jiangbeicheng.vhr01.bean;

import java.util.List;

/**
 * @ClassName RespPageBean
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/20 20:48
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
public class RespPageBean {
    private Long total;
    private List<?> data;


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
