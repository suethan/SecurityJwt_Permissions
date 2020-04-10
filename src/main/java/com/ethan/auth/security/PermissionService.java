package com.ethan.auth.security;

import cn.hutool.core.util.ObjectUtil;
import com.ethan.auth.untils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/10 13:58
 */
@Service("ps")
@Slf4j
public class PermissionService {

    @Autowired
    AuthServices authServices;
    public boolean hasPermi(String permission){

        log.info("PermissionService+hasPermi");
        if (ObjectUtil.isNull(permission)){
            return false;
        }
        LoginUser loginUser = authServices.getLoginUser(ServletUtils.getRequest());
        if (ObjectUtil.isNull(loginUser)||ObjectUtil.isNull(loginUser.getPermissions())){
            return false;
        }

        if (loginUser.getPermissions().contains(permission)){
            return true;
        }else{
            return false;
        }

    }
}
