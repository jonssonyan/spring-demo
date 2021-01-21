package com.springboot.utils;


import com.springboot.entity.vo.ResultVO;

public class ResultVOUtils {
    public static ResultVO<Object> success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(1);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO<Object> success() {
        return ResultVOUtils.success(null);
    }

    public static ResultVO<Object> fail(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("失败");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO<Object> fail() {
        return ResultVOUtils.fail(null);
    }
}
