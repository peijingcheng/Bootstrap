package org.jiangbeicheng.vhr01.service;

import org.jiangbeicheng.vhr01.bean.Hr;
import org.jiangbeicheng.vhr01.mapper.HrMapper;
import org.jiangbeicheng.vhr01.mapper.HrRoleMapper;
import org.jiangbeicheng.vhr01.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/11 16:56
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Autowired
    HrRoleMapper hrRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr=hrMapper.loadUserByUsername(username);
        if(hr==null){
            throw new UsernameNotFoundException("用户名不存在");

        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    public List<Hr> getAllHrs(String keywords) {
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);
    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }
    @Transactional
    public boolean updateHrRoles(Integer hrid, Integer[] rids) {
        hrRoleMapper.deleteByHrid(hrid);
        return hrRoleMapper.addRole(hrid,rids)==rids.length;
    }

    public Integer deleteHrById(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }
}
