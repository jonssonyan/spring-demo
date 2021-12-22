package com.jonssonyan.mapper;

import com.alibaba.fastjson.JSON;
import com.jonssonyan.module.Account;
import com.jonssonyan.module.vo.AccountVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountConverter {
    AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

    @Mapping(target = "gender", source = "sex")
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    AccountVo do2vo(Account account);

    @Mapping(target = "sex", source = "gender")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Account vo2Do(AccountVo accountVo);

    List<AccountVo> do2voList(List<Account> userList);

    default List<AccountVo.UserConfig> strConfigToListUserConfig(String config) {
        return JSON.parseArray(config, AccountVo.UserConfig.class);
    }

    default String listUserConfigToStrConfig(List<AccountVo.UserConfig> list) {
        return JSON.toJSONString(list);
    }
}
