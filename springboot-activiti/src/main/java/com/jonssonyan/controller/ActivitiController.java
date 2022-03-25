package com.jonssonyan.controller;


import com.jonssonyan.entity.dto.TaskDto;
import com.jonssonyan.entity.vo.InitProcessVo;
import com.jonssonyan.entity.vo.Result;
import com.jonssonyan.entity.vo.StartProcessVo;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/initProcess")
    public Result initProcess(@RequestBody InitProcessVo initProcessVo) {
        //部署对象
        Deployment deployment = repositoryService.createDeployment()
                .name(initProcessVo.getName())
                // bpmn文件
                .addClasspathResource(String.format("processes/%s.bpmn20.xml", initProcessVo.getResource()))
                .deploy();
        log.info("流程部署id: {}", deployment.getId());
        log.info("流程部署name: {}", deployment.getName());
        return Result.success();
    }

    /**
     * 启动流程
     */
    @PostMapping("/startProcess")
    public Result startPro(@RequestBody StartProcessVo startProcessVo) {
        Map<String, Object> map = new HashMap<>();
        map.put("var", "我是流程变量");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessVo.getProcessDefinitionKey(), map);
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
     * @param taskDto 流程
     * @return
     */
    @PostMapping("/completeByTaskById")
    public Result completeTaskById(@RequestBody TaskDto taskDto) {
        taskService.complete(taskDto.getId());
        return Result.success();
    }

    /**
     * 结束流程
     *
     * @param taskDto 流程
     * @return
     */
    @PostMapping("/deleteByTaskById")
    public Result deleteProcessInstance(@RequestBody TaskDto taskDto) {
        taskService.deleteTask(taskDto.getId());
        return Result.success();
    }
}
