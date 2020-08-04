package org.jiangbeicheng.vhr01.service;

import org.jiangbeicheng.vhr01.bean.Position;
import org.jiangbeicheng.vhr01.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PositionService
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/14 16:01
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@Service
public class PositionService {

    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions(){
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insertSelective(position);
    }

    public Integer updatePositions(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePositionById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public Integer deletePositionByIds(Integer[] ids) {
        return positionMapper.deletePositionByIds(ids);
    }
}
