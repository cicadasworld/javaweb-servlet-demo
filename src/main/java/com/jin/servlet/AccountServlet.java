package com.jin.servlet;

import com.jin.bean.entity.Account;
import com.jin.bean.vo.AccountVO;
import com.jin.bean.vo.PageBean;
import com.jin.service.AccountService;
import com.jin.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends BaseServlet {

    private AccountService accountService = new AccountServiceImpl();

    public void findAllAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<AccountVO> accountVOList = accountService.findAll();
            if (accountVOList != null) {
                // 将账户列表放入域对象中
                request.setAttribute("accounts", accountVOList);
                // 请求转发跳转到account_list.jsp
                request.getRequestDispatcher("pages/account/account_list.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("index.html").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    public void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String balance = request.getParameter("balance");
        String status = request.getParameter("status");
        String errorMessage = null;
        try {
            errorMessage = validate(name, balance, status);
            // 校验通过
            if (errorMessage == null) {
                Account account = new Account();
                account.setName(name);
                account.setBalance(new BigDecimal(balance));
                account.setStatus(Integer.valueOf(status));
                boolean addResult = accountService.add(account);
                // 添加成功
                if (addResult) {
                    // 跳转到查询所有账号页面
                    response.sendRedirect("account?method=findAll");
                }
            } else {
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("pages/account/account_add.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 账号已存在，返回添加账号页面并给出响应的提示信息
            errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("pages/account/account_add.jsp").forward(request, response);
        }
    }

    private String validate(String name, String balance, String status) {
        String errorMessage = null;
        if (name == null || name.isEmpty()) {
            errorMessage = "账号名为空";
        } else if (balance == null || balance.isEmpty()) {
            errorMessage = "账号余额为空";
        } else if (new BigDecimal(balance).compareTo(BigDecimal.ZERO) == -1) {
            errorMessage = "账号余额为负数";
        } else if (status == null || status.isEmpty()) {
            errorMessage = "账号状态为空";
        }
        return errorMessage;
    }

    public void deleteAccountById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String errorMessage = null;
        try {
            boolean deleteResult = accountService.deleteById(id);
            if (deleteResult) {
                // 删除成功，跳转到显示所有账号列表页面
                response.sendRedirect("account?method=findAll");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "账号删除失败!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("account?method=findAll").forward(request, response);
        }
    }

    public void toUpdateAccountPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Account account = accountService.findById(id);
        if (account != null) {
            request.setAttribute("account", account);
            request.getRequestDispatcher("pages/account/account_update.jsp").forward(request, response);
        } else {
            // 没有查询到就回到查询所有账号列表页面
            response.sendRedirect("account?method=findAll");
        }
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String balance = request.getParameter("balance");
        String status = request.getParameter("status");
        String errorMessage = null;
        try {
            errorMessage = validate(name, balance, status);
            // 校验通过
            if (errorMessage == null) {
                Account account = new Account();
                account.setId(Long.valueOf(id));
                account.setName(name);
                account.setBalance(new BigDecimal(balance));
                account.setStatus(Integer.valueOf(status));
                boolean updateResult = accountService.update(account);
                // 修改成功
                if (updateResult) {
                    // 跳转到显示所有账号列表页面
                    response.sendRedirect("account?method=findAll");
                }
            } else {
                // 校验失败
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("account?method=toUpdateAccountPage&id=" + id).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = e.getMessage();
            // 修改异常
            request.setAttribute("errorMessage", errorMessage);
            // 跳转到显示所有账号列表页面
            request.getRequestDispatcher("account?method=findAll").forward(request, response);
        }
    }

    public void findAccountByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long pageNo = Long.valueOf(request.getParameter("pageNo"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        PageBean<AccountVO> pageBean = accountService.findByPage(pageNo, pageSize);
        if (pageBean != null && pageBean.getDataList() != null && !pageBean.getDataList().isEmpty()) {
            request.setAttribute("pageBean", pageBean);
            // 请求转发跳转到account_list_page.jsp
            request.getRequestDispatcher("pages/account/account_list_page.jsp").forward(request, response);
        }
    }
}
