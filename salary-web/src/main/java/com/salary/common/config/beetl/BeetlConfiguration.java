package com.salary.common.config.beetl;

import com.salary.common.config.beetl.ext.DictExt;
import com.salary.common.config.beetl.ext.ShiroExt;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Override
    protected void initOther() {
        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.registerFunctionPackage("dict", new DictExt());
    }
}
