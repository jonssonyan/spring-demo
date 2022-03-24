package com.jonssonyan.service;

import org.activiti.engine.delegate.DelegateExecution;

public interface ActivitiService {

    void task1(DelegateExecution delegateExecution);

    void task2(DelegateExecution delegateExecution);

    Boolean execTask1OrTask2(DelegateExecution delegateExecution);
}
