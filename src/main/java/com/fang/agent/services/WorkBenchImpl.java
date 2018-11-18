package com.fang.agent.services;

import com.fang.agent.config.URLConfig;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: SimulationFang
 * @description: 实现类
 * @author: Mr.Dai
 * @create: 2018-11-17 21:05
 **/
public class WorkBenchImpl implements WorkBench {

    private Logger logger= LoggerFactory.getLogger(WorkBenchImpl.class);
    private static WebClient webClient=null;

    @Override
    public HtmlPage gotoLeftColumn(String url) {
        GlobalSession session = new GlobalSessionImpl();
        webClient = session.getYetLoginFangSession();
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(4000);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("地址有问题");
        }
        return page;
    }
}
