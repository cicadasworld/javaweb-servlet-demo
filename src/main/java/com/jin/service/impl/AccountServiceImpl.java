package com.jin.service.impl;

import com.jin.bean.entity.Account;
import com.jin.bean.vo.AccountVO;
import com.jin.bean.vo.PageBean;
import com.jin.dao.AccountDao;
import com.jin.dao.impl.AccountDaoImpl;
import com.jin.service.AccountService;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hujin
 * @version 2021/11/1
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    @Override
    public List<AccountVO> findAll() throws Exception {
        List<Account> accounts = accountDao.selectAll();
        return accounts2AccountVOs(accounts);
    }

    @Override
    public boolean add(Account account) {
        Account accountCondition = new Account();
        accountCondition.setName(account.getName());
        try {
            Account oneAccount = accountDao.selectOne(accountCondition);
            if (oneAccount == null) {
                boolean insertResult = accountDao.insert(account);
                if (insertResult) {
                    return true;
                }
            } else {
                throw new RuntimeException("账号名已存在!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Account accountCondition = new Account();
        accountCondition.setId(id);
        try {
            Account account = accountDao.selectOne(accountCondition);
            if (account != null) {
                // 账号在数据库中存在
                return accountDao.delete(accountCondition);
            } else {
                throw new RuntimeException("删除账号失败，账号不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Account findById(Long id) {
        Account accountCondition = new Account();
        accountCondition.setId(id);
        try {
            Account account = accountDao.selectOne(accountCondition);
            if (account != null) {
                return account;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Account account) {
        // 检查账号是否存在
        try {
            Account accountCheck = this.findById(account.getId());
            if (accountCheck != null) {
                return accountDao.update(account);
            } else {
                throw new RuntimeException("账号信息不存在!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public PageBean<AccountVO> findByPage(Long pageNo, Integer pageSize) {
        PageBean<AccountVO> pageBean = new PageBean<>();
        try {
            Long totalCount = accountDao.selectTotalCount();
            pageBean.setTotalCount(totalCount);

            List<Account> accounts = accountDao.selectByPage(pageNo, pageSize);
            List<AccountVO> accountVOs = accounts2AccountVOs(accounts);
            if (accountVOs != null && !accountVOs.isEmpty()) {
                pageBean.setDataList(accountVOs);
            }

            // 总页数=总条数%每页条数==0?总条数/每页条数:总条数/每页条数+1
            Long totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
            pageBean.setTotalPage(totalPage);

            pageBean.setPageNo(pageNo);
            pageBean.setPageSize(pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return pageBean;
    }

    private List<AccountVO> accounts2AccountVOs(List<Account> accounts) {
        if (accounts != null && !accounts.isEmpty()) {
            List<AccountVO> accountVOs = new ArrayList<>();
            for (Account account : accounts) {
                AccountVO accountVO = new AccountVO();
                accountVO.setId(account.getId());
                accountVO.setName(account.getName());
                accountVO.setBalance(account.getBalance());
                accountVO.setStatus(account.getStatus());
                accountVO.setCreateDate(account.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                accountVO.setUpdateDate(account.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                accountVOs.add(accountVO);
            }
            return accountVOs;
        }
        return null;
    }
}
