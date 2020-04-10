package com.ethan.auth.security;

import com.ethan.auth.entity.SysUser;
import com.ethan.auth.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/9 16:23
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserServices userServices;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("---->UserDetailsServiceImpl+loadUserByUsername");
        SysUser sysUser =userServices.getByName(username);
        if (sysUser!=null){
            Set<String>set = new HashSet<>();
            set.add("system:test");
            return new LoginUser(sysUser,set);
        }
        return null;
    }
}
