package com.jin.service;

import com.jin.bean.entity.Account;
import com.jin.bean.vo.AccountVO;
import com.jin.bean.vo.PageBean;

import java.util.List;

/**
 * @author hujin
 * @version 2021/11/1
 */
public interface AccountService {

    List<AccountVO> findAll() throws Exception;

    boolean add(Account account);

    boolean deleteById(Long id);

    Account findById(Long id);

    boolean update(Account account);

    PageBean<AccountVO> findByPage(Long pageNo, Integer pageSize);
}
