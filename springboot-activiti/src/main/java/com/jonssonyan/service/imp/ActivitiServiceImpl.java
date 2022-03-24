package com.jonssonyan.service.imp;

import com.jonssonyan.service.ActivitiService;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service("activitiService")
public class ActivitiServiceImpl implements ActivitiService {

    @Override
    public void task1(DelegateExecution delegateExecution) {
        System.out.println("task1");
        String var = (String) delegateExecution.getVariable("var");
        System.out.println("变量：" + var);
    }

    @Override
    public void task2(DelegateExecution delegateExecution) {
        System.out.println("task2");
        String var = (String) delegateExecution.getVariable("var");
        System.out.println("变量：" + var);
    }

    @Override
    public Boolean execTask1OrTask2(DelegateExecution delegateExecution) {
        return false;
    }
}
