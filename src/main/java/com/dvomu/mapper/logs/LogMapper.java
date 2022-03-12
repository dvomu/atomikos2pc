package com.dvomu.mapper.logs;

import com.dvomu.pojo.LogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LogMapper {

    /**
     * 新增
     * @param logInfo
     * @return
     */
    int insertLogInfo(LogInfo logInfo);

    /**
     * 批量新增
     * @param logInfoList
     * @return
     */
    int insertBatchLogs(@Param("logInfoList") List<LogInfo> logInfoList);

}