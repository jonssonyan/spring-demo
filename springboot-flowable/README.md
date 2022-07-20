# 引入依赖

```xml

<dependency>
    <groupId>org.flowable</groupId>
    <artifactId>flowable-spring-boot-starter</artifactId>
    <version>6.4.0</version>
</dependency>
```

# 表说明

| 表分类          | 表名称                 | 表含义                                                           |
|--------------|---------------------|---------------------------------------------------------------|
| 通用数据表        | act_ge_bytearray    | 二进制数据表，如流程定义、流程模板、流程图的字节流文件                                   |
| &nbsp;       | act_ge_property     | 属性数据表（不常用）                                                    |
| 历史表          | act_hi_actinst      | 历史节点表，存放流程实例运转的各个节点信息（包含开始、结束等非任务节点）                          |
| &nbsp;       | act_hi_attachment   | 历史附件表，存放历史节点上传的附件信息（不常用）                                      |
| &nbsp;       | act_hi_comment      | 历史意见表                                                         |
| &nbsp;       | act_hi_detail       | 历史详情表，存储节点运转的一些信息（不常用）                                        |
| &nbsp;       | act_hi_identitylink | 历史流程人员表，存储流程各节点候选、办理人员信息，常用于查询某人或部门的已办任务                      |
| &nbsp;       | act_hi_procinst     | 历史流程实例表，存储流程实例历史数据（包含正在运行的流程实例）                               |
| &nbsp;       | act_hi_taskinst     | 历史流程任务表，存储历史任务节点                                              |
| &nbsp;       | act_hi_varinst      | 流程历史变量表，存储流程历史节点的变量信息                                         |
| 用户相关表        | act_id_group        | 用户组信息表，对应节点选定候选组信息                                            |
| &nbsp;       | act_id_info         | 用户扩展信息表，存储用户扩展信息                                              |
| &nbsp;       | act_id_membership   | 用户与用户组关系表                                                     |
| &nbsp;       | act_id_user         | 用户信息表，对应节点选定办理人或候选人信息                                         |
| 流程定义、流程模板相关表 | act_re_deployment   | 部属信息表，存储流程定义、模板部署信息                                           |
| &nbsp;       | act_re_procdef      | 流程定义信息表，存储流程定义相关描述信息，但其真正内容存储在act_ge_bytearray表中，以字节形式存储      |
| &nbsp;       | act_re_model        | 流程模板信息表，存储流程模板相关描述信息，但其真正内容存储在act_ge_bytearray表中，以字节形式存储      |
| 流程运行时表       | act_ru_task         | 运行时流程任务节点表，存储运行中流程的任务节点信息，重要，常用于查询人员或部门的待办任务时使用               |
| &nbsp;       | act_ru_event_subscr | 监听信息表，不常用                                                     |
| &nbsp;       | act_ru_execution    | 运行时流程执行实例表，记录运行中流程运行的各个分支信息（当没有子流程时，其数据与act_ru_task表数据是一一对应的） |
| &nbsp;       | act_ru_identitylink | 运行时流程人员表，重要，常用于查询人员或部门的待办任务时使用                                |
| &nbsp;       | act_ru_job          | 运行时定时任务数据表，存储流程的定时任务信息                                        |
| &nbsp;       | act_ru_variable     | 运行时流程变量数据表，存储运行中的流程各节点的变量信息                                   |

# 流程引擎API

1. RepositoryService很可能是使用Flowable引擎要用的第一个服务。这个服务提供了管理与控制部署(deployments)与流程定义(process definitions)的操作。管理静态信息，
2. RuntimeService用于启动流程定义的新流程实例。
3. IdentityService很简单。它用于管理（创建，更新，删除，查询……）组与用户。
4. FormService是可选服务。也就是说Flowable没有它也能很好地运行，而不必牺牲任何功能。
5. HistoryService暴露Flowable引擎收集的所有历史数据。要提供查询历史数据的能力。
6. ManagementService通常在用Flowable编写用户应用时不需要使用。它可以读取数据库表与表原始数据的信息，也提供了对作业(job)的查询与管理操作。
7. DynamicBpmnService可用于修改流程定义中的部分内容，而不需要重新部署它。例如可以修改流程定义中一个用户任务的办理人设置，或者修改一个服务任务中的类名。

# 参考文章

1. https://juejin.cn/post/7053633279643090958