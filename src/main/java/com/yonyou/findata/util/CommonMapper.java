package com.yonyou.findata.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承通用mapper插件
 * @author: pizhihui
 * @datae: 2017-05-08
 */
public interface CommonMapper<T> extends Mapper<T>,MySqlMapper<T>{

    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错

}
