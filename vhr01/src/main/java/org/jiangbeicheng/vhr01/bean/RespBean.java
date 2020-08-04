package org.jiangbeicheng.vhr01.bean;

/**
 * @ClassName RespBean
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/11 19:22
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;

    public static RespBean ok(String msg){
        return  new RespBean(200,msg,null);
    }
    public static RespBean ok(String msg,Object obj){
        return  new RespBean(200,msg,obj);
    }
    public static RespBean error(String msg){
        return  new RespBean(200,msg,null);
    }
    public static RespBean error(String msg,Object obj){
        return  new RespBean(200,msg,obj);
    }
    private RespBean() {
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
