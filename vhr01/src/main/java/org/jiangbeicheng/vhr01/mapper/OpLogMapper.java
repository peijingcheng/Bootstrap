package org.jiangbeicheng.vhr01.mapper;

import org.jiangbeicheng.vhr01
.bean.OpLog;

public interface OpLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OpLog record);

    int insertSelective(OpLog record);

    OpLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OpLog record);

    int updateByPrimaryKey(OpLog record);
}