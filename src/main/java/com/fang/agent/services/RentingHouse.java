package com.fang.agent.services;


import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

/**
 * @program: SimulationFang
 * @description: 租房栏目
 * @author: Mr.Dai
 * @create: 2018-11-17 21:04
 **/
public interface RentingHouse {


    /**
     * x向写字楼出租写信息
     * @param page 传入写字楼出租页面
     * @return
     */
    public boolean writeToOfficePageMessage(HtmlPage page) throws IOException;


}
