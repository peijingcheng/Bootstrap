package org.jiangbeicheng.vhr01.bean;

/**
 * @ClassName Meta
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/12 19:39
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
public class Meta {
    private Boolean keepAlive;

    private Boolean requireAuth;

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
