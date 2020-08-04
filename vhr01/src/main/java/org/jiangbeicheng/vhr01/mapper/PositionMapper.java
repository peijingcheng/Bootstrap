package org.jiangbeicheng.vhr01.mapper;

import org.apache.ibatis.annotations.Param;
import org.jiangbeicheng.vhr01.bean.Position;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPositions();


    Integer deletePositionByIds(@Param("ids") Integer[] ids);
}