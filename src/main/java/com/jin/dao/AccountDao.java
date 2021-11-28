package com.jin.dao;

import com.jin.bean.entity.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * @author hujin
 * @version 2021/11/1
 */
public interface AccountDao {

    List<Account> selectAll() throws Exception;

    boolean insert(Account account) throws SQLException;

    Account selectOne(Account accountCondition) throws Exception;

    boolean delete(Account accountCondition) throws SQLException;

    boolean update(Account accountCondition) throws SQLException;

    Long selectTotalCount() throws SQLException;

    List<Account> selectByPage(Long pageNo, Integer pageSize) throws Exception;

    List<Account> selectByPage(Long pageNo, Integer pageSize, Account accountCondition) throws Exception;
}
