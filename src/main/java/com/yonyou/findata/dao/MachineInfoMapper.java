package com.yonyou.findata.dao;

import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.model.MachineInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MachineInfoMapper {
    int countByExample(MachineInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MachineInfo record);

    int insertSelective(MachineInfo record);

    List<MachineInfo> selectByExample(MachineInfoExample example);

    MachineInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MachineInfo record);

    int updateByPrimaryKey(MachineInfo record);
}