package com.jonssonyan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jonssonyan.dao.TestDao;
import com.jonssonyan.entity.Test;
import com.jonssonyan.entity.vo.TestVO;
import com.jonssonyan.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<TestDao, Test> implements TestService {
    @Autowired
    private TestDao testDao;

    public IPage<Test> selectPage(TestVO testVO) {
        Page<Test> testPage = new Page<>(testVO.getPageNum(), testVO.getPageSize());
        return testDao.selectPage(testPage, testVO);
    }
}
