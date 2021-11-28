package com.jin.dao.impl;

import com.jin.bean.entity.Account;
import com.jin.dao.AccountDao;
import com.jin.util.DruidDataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author hujin
 * @version 2021/11/1
 */
public class AccountDaoImpl implements AccountDao {

    private static final QueryRunner queryRunner = new QueryRunner(DruidDataSourceUtil.getDataSource());

    @Override
    public List<Account> selectAll() throws Exception {
        String sql = "select id, name, balance, status, create_date, update_date from jdbc_account";
        List<Account> accounts = queryRunner.query(sql, new AccountHandler());
        if (accounts != null && !accounts.isEmpty()) {
            return accounts;
        }
        return null;
    }

    @Override
    public boolean insert(Account account) throws SQLException {
        String sql = "insert into jdbc_account values(null, ?, ?, ?, now(), now())";
        int row = queryRunner.update(sql, account.getName(), account.getStatus(), account.getBalance());
        return row == 1 ? true : false;
    }

    @Override
    public Account selectOne(Account accountCondition) throws Exception {
        if (accountCondition == null) {
            return null;
        }

        String name = accountCondition.getName();
        if (name != null && !name.isEmpty()) {
            String sql = "select id, name, balance, status, create_date, update_date from jdbc_account where name = ?";
            List<Account> accounts = queryRunner.query(sql, new AccountHandler(), name);
            if (accounts != null && accounts.size() == 1) {
                return accounts.get(0);
            }
        }

        Long id = accountCondition.getId();
        if (id != null) {
            String sql = "select id, name, balance, status, create_date, update_date from jdbc_account where id = ?";
            List<Account> accounts = queryRunner.query(sql, new AccountHandler(), id);
            if (accounts != null && accounts.size() == 1) {
                return accounts.get(0);
            }
        }
        return null;
    }

    @Override
    public boolean delete(Account accountCondition) throws SQLException {
        if (accountCondition == null) {
            return false;
        }

        Long id = accountCondition.getId();
        if (id != null) {
            String sql = "delete from jdbc_account where id = ?";
            int row = queryRunner.update(sql, id);
            return row == 1 ? true : false;
        }
        return false;
    }

    @Override
    public boolean update(Account accountCondition) throws SQLException {
        if (accountCondition == null) {
            return false;
        }

        Long id = accountCondition.getId();
        if (id != null) {
            String sql = "update jdbc_account set name = ?, balance = ?, status=?, update_date=now() where id = ?";
            int row = queryRunner.update(sql, accountCondition.getName(), accountCondition.getBalance(), accountCondition.getStatus(), accountCondition.getId());
            return row == 1 ? true : false;
        }
        return false;
    }

    @Override
    public Long selectTotalCount() throws SQLException {
        String sql = "select count(*) from jdbc_account";
        Long totalCount = queryRunner.query(sql, new ScalarHandler<>());
        if (totalCount != null) {
            return totalCount;
        }
        return null;
    }

    @Override
    public List<Account> selectByPage(Long pageNo, Integer pageSize) throws Exception {
        // select 列名 from 表名 [where 条件] limit (pageNo-1)*pageSize, pageSize
        String sql = "select id, name, balance, status, create_date, update_date from jdbc_account limit ?, ?";
        List<Account> accounts = queryRunner.query(sql, new AccountHandler(), (pageNo - 1) * pageSize, pageSize);
        if (accounts != null && !accounts.isEmpty()) {
            return accounts;
        }
        return null;
    }

    @Override
    public List<Account> selectByPage(Long pageNo, Integer pageSize, Account accountCondition) throws Exception {
        if (accountCondition != null && accountCondition.getStatus() != null) {
            String sql = "select id, name, balance, status, create_date, update_date from jdbc_account where status = ? limit ?, ?";
            List<Account> accounts = queryRunner.query(sql, new AccountHandler(),
                    accountCondition.getStatus(), (pageNo - 1) * pageSize, pageSize);
            if (accounts != null && !accounts.isEmpty()) {
                return accounts;
            }
        }
        return null;
    }
}
