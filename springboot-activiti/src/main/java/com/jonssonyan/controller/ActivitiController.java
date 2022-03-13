package com.jonssonyan.controller;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 部署流程
     */
    @PostMapping("/initProcesses")
    public String initProcesses() {
        //部署对象
        Deployment deployment = repositoryService.createDeployment()
                // bpmn文件
                .addClasspathResource("processes/demo.bpmn20.xml")
                .name("请假申请流程")
                .deploy();
        log.info("流程部署id: {}", deployment.getId());
        log.info("流程部署名称: {}", deployment.getName());
        return "success";
    }

    /**
     * 启动流程
     */
    @PostMapping("/startProcesses")
    public String startPro() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("demo");
        log.info("流程启动成功，流程id: {}", processInstance.getId());
        log.info("流程启动成功，流程definitionName: {}", processInstance.getProcessDefinitionName());
        log.info("流程启动成功，流程businessKey: {}", processInstance.getBusinessKey());
        return "success";
    }
}
