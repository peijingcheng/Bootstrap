package org.jiangbeicheng.vhr01.controller.system.basic;

import org.jiangbeicheng.vhr01.bean.JobLevel;
import org.jiangbeicheng.vhr01.bean.RespBean;
import org.jiangbeicheng.vhr01.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName JobLevelController
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/16 0:32
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;
    @GetMapping("/")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
       if(jobLevelService.addJobLevel(jobLevel)==1){
            return RespBean.ok("添加成功");
       }
        return RespBean.error("添加失败");
    }
    @PutMapping("/")
    public RespBean updateJobLevelById(@RequestBody JobLevel jobLevel){
        if(jobLevelService.updateJobLevelById(jobLevel)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id ){
        if(jobLevelService.deleteJobLevelById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @DeleteMapping("/")
    public RespBean deleteJobLevelByIds(Integer[] ids){
        if(jobLevelService.deleteJobLevelByIds(ids)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
