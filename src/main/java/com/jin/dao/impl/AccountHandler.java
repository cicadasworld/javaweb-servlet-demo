package com.jin.dao.impl;

import com.jin.bean.entity.Account;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hujin
 * @version 2021/11/1
 */
public class AccountHandler extends BeanListHandler<Account> {

    public AccountHandler() {
        super(Account.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
    }

    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("create_date", "createDate");
        columnsToFieldsMap.put("update_date", "updateDate");
        return columnsToFieldsMap;
    }
}
