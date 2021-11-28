package com.jin.dao;

import com.jin.bean.entity.Account;
import com.jin.dao.impl.AccountDaoImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * @author hujin
 * @version 2021/11/1
 */
public class AccountDaoTest {

    private AccountDao accountDao = new AccountDaoImpl();

    @Test
    public void testSelectAll() {
        try {
            List<Account> accounts = accountDao.selectAll();
            Assert.assertEquals(accounts.size(), 7);
            if (accounts != null && !accounts.isEmpty()) {
                for (Account account : accounts) {
                    System.out.println(account);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        try {
            Account account = new Account();
            account.setName("Tim");
            account.setBalance(new BigDecimal("1000000.00"));
            boolean insertResult = accountDao.insert(account);
            Assert.assertTrue(insertResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectOneByNameExists() {
        Account accountCondition = new Account();
        accountCondition.setName("Tony");
        try {
            Account account = accountDao.selectOne(accountCondition);
            System.out.println(account);
            Assert.assertEquals(account.getName(), "Tony");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectOneByNameNotExists() {
        Account accountCondition = new Account();
        accountCondition.setName("Lucy");
        try {
            Account account = accountDao.selectOne(accountCondition);
            Assert.assertNull(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        Account accountCondition = new Account();
        accountCondition.setId(10L);
        try {
            boolean deleteResult = accountDao.delete(accountCondition);
            Assert.assertTrue(deleteResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectOneById() {
        Account accountCondition = new Account();
        accountCondition.setId(7L);
        try {
            Account account = accountDao.selectOne(accountCondition);
            Assert.assertNotNull(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        Account accountCondition = new Account();
        accountCondition.setId(7L);
        accountCondition.setName("Emma");
        accountCondition.setBalance(new BigDecimal(88888888));
        accountCondition.setStatus(0);
        try {
            boolean updateResult = accountDao.update(accountCondition);
            Assert.assertTrue(updateResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectTotalCount() throws SQLException {
        Long totalCount = accountDao.selectTotalCount();
        Assert.assertEquals(totalCount.longValue(), 7);
    }

    @Test
    public void testSelectByPage() {
        try {
            List<Account> accounts = accountDao.selectByPage(1L, 2);
            Assert.assertEquals(accounts.size(), 2);
            for (Account account : accounts) {
                System.out.println(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectConditionByPage() {
        Account accountCondition = new Account();
        accountCondition.setStatus(1);
        try {
            List<Account> accounts = accountDao.selectByPage(1L, 2, accountCondition);
            Assert.assertEquals(accounts.size(), 2);
            for (Account account : accounts) {
                System.out.println(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
