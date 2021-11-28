package com.jin.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收的参数名和方法名相同且方法参数也相同
        /*if ("findAllAccount".equals(method)) {
            findAllAccount(request, response);
        } else if ("addAccount".equals(method)) {
            addAccount(request, response);
        } else if ("deleteAccountById".equals(method)) {
            deleteAccountById(request, response);
        } else if ("toUpdateAccountPage".equals(method)) {
            toUpdateAccountPage(request, response);
        } else if ("updateAccount".equals(method)) {
            updateAccount(request, response);
        } else if ("findAccountByPage".equals(method)) {
            findAccountByPage(request, response);
        }*/
        String method = request.getParameter("method");
        try {
            Method[] methods = this.getClass().getMethods();
            if (methods != null && methods.length > 0) {
                for (Method curMethod : methods) {
                    if (curMethod.getName().equals(method)) {
                        curMethod.invoke(this, request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
