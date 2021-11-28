package com.jin.service;

import com.jin.bean.entity.Account;
import com.jin.bean.vo.AccountVO;
import com.jin.bean.vo.PageBean;
import com.jin.service.impl.AccountServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author hujin
 * @version 2021/11/1
 */
public class AccountServiceTest {

    private AccountService accountService = new AccountServiceImpl();

    @Test
    public void testFindAllAccounts() {
        try {
            List<AccountVO> accountVOList = accountService.findAll();
            if (accountVOList != null) {
                for (AccountVO accountVO : accountVOList) {
                    System.out.println(accountVO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddAccountNotExists() {
        Account account = new Account();
        account.setName("Jerry");
        account.setBalance(new BigDecimal("1100000.00"));
        boolean addResult = accountService.add(account);
        Assert.assertTrue(addResult);
    }

    @Test
    public void testAddAccountExists() {
        Account account = new Account();
        account.setName("Jerry");
        account.setBalance(new BigDecimal("1100000.00"));
        boolean addResult = accountService.add(account);
        Assert.assertFalse(addResult);
    }

    @Test
    public void testDeleteAccountById() {
        boolean deleteResult = accountService.deleteById(8L);
        Assert.assertTrue(deleteResult);
    }

    @Test
    public void testFindAccountById() {
        Account account = accountService.findById(7L);
        Assert.assertNotNull(account);
    }

    @Test
    public void testUpdate() {
        Account accountCondition = new Account();
        accountCondition.setId(7L);
        accountCondition.setName("Emma");
        accountCondition.setBalance(new BigDecimal(11111111));
        accountCondition.setStatus(1);
        boolean updateResult = accountService.update(accountCondition);
        Assert.assertTrue(updateResult);

        accountCondition.setId(99L);
        updateResult = accountService.update(accountCondition);
    }

    @Test
    public void testFindByPage() {
        PageBean<AccountVO> pageBean = accountService.findByPage(1L, 2);
        System.out.println(pageBean);
    }
}
