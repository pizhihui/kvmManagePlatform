package com.yonyou.findata.dao;

import com.yonyou.findata.model.KvmConfig;
import com.yonyou.findata.model.KvmConfigExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KvmConfigMapper {
    int countByExample(KvmConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KvmConfig record);

    int insertSelective(KvmConfig record);

    List<KvmConfig> selectByExample(KvmConfigExample example);

    KvmConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KvmConfig record);

    int updateByPrimaryKey(KvmConfig record);
}