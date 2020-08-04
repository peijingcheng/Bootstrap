package org.jiangbeicheng.vhr01.mapper;

import org.apache.ibatis.annotations.Param;
import org.jiangbeicheng.vhr01
.bean.Employee;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmployeeByPage(@Param("page") Integer page,@Param("size") Integer size,@Param("keyword") String keyword);

    Long getTotal(@Param("keyword") String keyword);
}