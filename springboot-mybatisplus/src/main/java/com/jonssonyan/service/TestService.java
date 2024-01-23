package com.jonssonyan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jonssonyan.entity.Test;
import com.jonssonyan.entity.vo.TestVO;

public interface TestService extends IService<Test> {
    IPage<Test> selectPage(TestVO testVO);
}
