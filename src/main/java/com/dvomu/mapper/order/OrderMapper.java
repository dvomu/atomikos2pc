package com.dvomu.mapper.order;

import com.dvomu.pojo.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrderMapper {

    /**
     * 新增
     *
     * @param orderInfo
     * @return
     */
    int insertOrderInfo(OrderInfo orderInfo);

    /**
     * 批量新增
     *
     * @param orderInfos
     * @return
     */
    int insertBatchOrder(@Param("orderInfos") List<OrderInfo> orderInfos);
}