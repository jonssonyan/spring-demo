# 引入依赖

```xml

<dependency>
    <groupId>org.activiti</groupId>
    <artifactId>activiti-spring-boot-starter-basic</artifactId>
    <version>6.0.0</version>
</dependency>
```

# 表说明

启动项目，会在数据库生成28张表

表名默认以“ACT_”开头,并且表名的第二部分用两个字母表明表的用例，而这个用例也基本上跟Service API匹配。

- ACT_GE_* : “GE”代表“General”（通用），用在各种情况下；
- ACT_HI_* : “HI”代表“History”（历史），这些表中保存的都是历史数据，比如执行过的流程实例、变量、任务，等等。Activit默认提供了4种历史级别：
- ACT_ID_* : “ID”代表“Identity”（身份），这些表中保存的都是身份信息，如用户和组以及两者之间的关系。如果Activiti被集成在某一系统当中的话，这些表可以不用，可以直接使用现有系统中的用户或组信息；
- ACT_RE_* : “RE”代表“Repository”（仓库），这些表中保存一些‘静态’信息，如流程定义和流程资源（如图片、规则等）；
- ACT_RU_* :
  “RU”代表“Runtime”（运行时），这些表中保存一些流程实例、用户任务、变量等的运行时数据。Activiti只保存流程实例在执行过程中的运行时数据，并且当流程结束后会立即移除这些数据，这是为了保证运行时表尽量的小并运行的足够快；

|表分类|表名称|表含义|
|  ----  | ----  |----|
一般数据|act_evt_log|事件处理日志表
&nbsp;|act_ge_bytearray|通用的流程定义和流程资源
&nbsp;|act_ge_property|系统相关属性
流程历史记录|act_hi_actinst|历史的流程实例
&nbsp;|act_hi_attachment|历史的流程附件
&nbsp;|act_hi_comment|历史的说明性信息
&nbsp;|act_hi_detail|历史的流程运行中的细节信息
&nbsp;|act_hi_identitylink|历史的流程运行过程中用户关系
&nbsp;|act_hi_procinst|历史的流程实例
&nbsp;|act_hi_taskinst|历史的任务实例
&nbsp;|act_hi_varinst|历史的流程运行中的变量信息
用户用户组表|act_id_group|身份信息-用户组信息表
&nbsp;|act_id_info|身份信息
&nbsp;|act_id_membership|身份信息
&nbsp;|act_id_user|身份信息
&nbsp;|act_procdef_info|流程定义数据表
流程定义表|act_re_deployment|部署单元信息
&nbsp;|act_re_model|模型信息
&nbsp;|act_re_procdef|已部署的流程定义
运行实例表|act_ru_deadletter_job|执行失败任务表
&nbsp;|act_ru_event_subscr|运行时事件
&nbsp;|act_ru_execution|运行时流程执行实例
&nbsp;|act_ru_identitylink|运行时用户关系信息
&nbsp;|act_ru_job|运行时作业
&nbsp;|act_ru_suspended_job|运行时暂停任务
&nbsp;|act_ru_task|运行时任务
&nbsp;|act_ru_timer_job|运行时定时任务
&nbsp;|act_ru_variable|运行时变量表

> 表的作用及其详细介绍：https://lucaslz.gitbooks.io/activiti-5-22/content/biaoxiang_qing_md.html

# Activiti提供的服务 7大接口

1. RepositoryService：提供一系列管理流程部署和流程定义的API，帮助我们实现流程定义的部署。此服务会处理与流程定义相关的静态数据。
2. RuntimeService：在流程运行时对流程实例进行管理与控制。管理 ProcessInstances（当前正在运行的流程）以及流程变量。
3. TaskService：对流程任务进行管理，例如任务提醒、任务完成和创建任务等。会跟踪 UserTasks，需要由用户手动执行的任务是Activiti API的核心。我们可以使用此服务创建任务，声明并完成任务，分配任务的受让人等。
4. FormService：表单服务。是一项可选服务，它用于定义中开始表单和任务表单。
5. IdentityService：提供对流程角色数据进行管理的API，这些角色数据包括用户组、用户及它们之间的关系。管理用户和组。
6. HistoryService：对流程的历史数据进行操作，包括查询、删除这些历史数据。我们还可以设置不同的历史级别。
7. ManagementService：提供对流程引擎进行管理和维护的服务。与元数据相关，在创建应用程序时通常不需要。
8. DynamicBpmnService：帮助我们在不重新部署的情况下更改流程中的任何内容。

# 参考文章

1. https://github.com/oycyqr/springboot-learning-demo/blob/master/springboot-activiti
2. https://blog.csdn.net/LT11hka/article/details/108080068