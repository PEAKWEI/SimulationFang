package com.fang.agent.services;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;


/**
 * @program: SimulationFang
 * @description: 全局Session
 * @author: Mr.Dai
 * @create: 2018-11-17 17:27
 **/
public interface GlobalSession {


    /**
     * 获取已经登录目的网站Session
     * @return WebClient
     */
    public WebClient getYetLoginFangSession();

    /**
     * 获取已经登录的页面去往需要操作的页面
     * @param url 登录会话中的URl
     * @return  可以持续操作的页面
     * @throws IOException
     */
    public HtmlPage gotoURlForPage(String url) throws IOException;

}
