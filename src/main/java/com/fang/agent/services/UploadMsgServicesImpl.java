package com.fang.agent.services;

import com.fang.agent.config.URLConfig;
import com.fang.agent.exception.UserException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

/**
 * @program: SimulationFang
 * @description:
 * @author: Mr.Dai
 * @create: 2018-11-17 21:31
 **/
public class UploadMsgServicesImpl implements UploadMsgServices {

    @Override
    public void writeDataOffices() {
        WorkBench workBench = new WorkBenchImpl();
        RentingHouseImpl house = new RentingHouseImpl();
        try {
            boolean b = house.writeToOfficePageMessage(workBench.gotoLeftColumn(URLConfig.OFFICE_URL));
            if(!b){
                throw new UserException("写入租房页面数据失败！");
            }
        } catch (UserException |IOException e) {
            e.printStackTrace();
        }
    }
}
