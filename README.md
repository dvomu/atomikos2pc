# Atomikos实现二阶段提交
atomikos一套开源的，JTA规范的实现。它是基于二阶段提交思想实现分布式事务的控制。是分布式刚性事务的一 种解决方案。在当下互联网开发中选择此种解决方案的场景也不是很多了。实现原理参考[IBM社区](https://www.ibm.com/developerworks/cn/java/j-lo-jta/index.html)
**场景说明**
一个企业级应用项目:进销存系统。系统要对针对库存记录访问日志。并且，库存系统数据库和日志数据库不是同
一个数据库。代码详见GitHub
**服务拓扑图**
![](media/16470447757668/16471013535339.jpg)

**测试代码**
```Java
@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private LogMapper logMapper;

    @Override
    public void addOrder(OrderInfo orderInfo) {
        int insert = orderMapper.insertOrderInfo(orderInfo);
        log.info("order库新增sql执行条数:{}",insert);

        //测试1 异常回滚
        int i=1/0;

        LogInfo logInfo=new LogInfo();
        logInfo.setId((new Random().nextInt()));
        logInfo.setCreateTime(new Date());
        logInfo.setContent(orderInfo.toString());
        int insert1 = logMapper.insertLogInfo(logInfo);
        log.info("logs库新增sql执行条数:{}",insert1);

        //测试2 异常回滚
//        int i=1/0;
    }
}
```

**测试结果**
测试1：业务执行前出现异常，数据库未进行插入操作，数据库无数据。

测试2：业务执行后出现异常
异常发生前控制台日志显示插入成功，但是数据库中并没有数据，当异常出现后，实际数据从头到尾并没有提交
![](http://upload.dvomu.com/mweb/16471013535339.jpg)

测试3：无异常情况，logs和order数据库表中均有数据

**二阶段提交总结**
二阶段提交作为早期分布式事务的解决方案，逐渐的淡出了主流方案的圈子。这里面其最重要的原因就是它是刚性事务，即需要满足强一致性。它的优点就是可以在多数据库间实现事务控制，而摆脱单一数据库使用事务的宿命。但是**阻塞式**这个缺点确是致命的，因为参与全局事务的数据库被动听从事务管理器的命令，执行或放弃事务，如果运行事务管理器的机器宕机，那整个系统就不能用了。当然，在极端情况下还可能同时影响其他系统，如果事务管理器挂了，但是这个数据库的表锁还没释放，因为数据库还在等待事务管理器的命令，因此，使用这个数据库的其他应用也会收到影响。