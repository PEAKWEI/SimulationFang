package com.fang.agent.services;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @program: SimulationFang
 * @description: 后台
 * @author: Mr.Dai
 * @create: 2018-11-17 21:03
 **/
public interface WorkBench {

    /**
     * 去往那个栏目
     * @param url 地址
     */
    public HtmlPage gotoLeftColumn(String url);

}
