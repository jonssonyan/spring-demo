package com.jonssonyan.controller;


import com.jonssonyan.entity.vo.InitProcessesVo;
import com.jonssonyan.entity.vo.Result;
import com.jonssonyan.entity.vo.StartProcessesVo;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ActivitiController {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;

    /**
     * classpath资源部署
     */
    @PostMapping("/initProcesses")
    public Result initProcesses(@RequestBody InitProcessesVo initProcessesVo) {
        //部署对象
        Deployment deployment = repositoryService.createDeployment()
                .name(initProcessesVo.getName())
                // bpmn文件
                .addClasspathResource(String.format("process/%s", initProcessesVo.getResource()))
                .deploy();
        log.info("流程部署id: {}", deployment.getId());
        log.info("流程部署名称: {}", deployment.getName());
        return Result.success();
    }

    /**
     * 启动流程
     */
    @PostMapping("/startProcesses")
    public Result startPro(@RequestBody StartProcessesVo startProcessesVo) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessesVo.getProcessDefinitionKey());
        log.info("流程启动成功，流程id: {}", processInstance.getId());
        log.info("流程启动成功，流程definitionName: {}", processInstance.getProcessDefinitionName());
        log.info("流程启动成功，流程businessKey: {}", processInstance.getBusinessKey());
        return Result.success();
    }

    /**
     * 查询待办
     *
     * @return
     */
    @PostMapping("/getTaskList")
    public Result getTaskList() {
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().list();
        return Result.success(historicActivityInstances);
    }

    /**
     * 完成任务
     *
     * @param taskId 流程id
     * @return
     */
    @PostMapping("/completeByTaskById")
    public Result completeTaskById(@RequestParam String taskId) {
        taskService.complete(taskId);
        return Result.success();
    }

    /**
     * 结束流程
     *
     * @param taskId 流程id
     * @return
     */
    @PostMapping("/deleteByTaskById")
    public Result deleteProcessInstance(@RequestParam String taskId) {
        taskService.deleteTask(taskId);
        return Result.success();
    }
}
