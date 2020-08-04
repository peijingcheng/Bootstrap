package org.jiangbeicheng.vhr01.controller.system.basic;

import org.jiangbeicheng.vhr01.bean.Position;
import org.jiangbeicheng.vhr01.bean.RespBean;
import org.jiangbeicheng.vhr01.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PositionController
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/14 14:57
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;
    @GetMapping("/")
    public List<Position> getAllPositions(){
       return positionService.getAllPositions();
    }
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if(positionService.addPosition(position)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }
    @PutMapping("/")
    public RespBean updatePositions(@RequestBody Position position){
        if(positionService.updatePositions(position)==1){
            return RespBean.ok("刷新成功");
        }
        return RespBean.error("刷新失败");
    }
    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id){
        if(positionService.deletePositionById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Integer[] ids) {
        if (positionService.deletePositionByIds(ids) == ids.length) {
            return RespBean.ok("删除成功");
        }
        return  RespBean.error("删除失败");
    }
}
