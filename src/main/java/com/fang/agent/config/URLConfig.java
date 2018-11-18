package com.fang.agent.config;

/**
 * @program: SimulationFang
 * @description: 逻辑地址配置
 * @author: Mr.Dai
 * @create: 2018-11-17 21:23
 **/
public interface URLConfig {

    /**
     * 主页面下的租房->录入租房页面
     */
    String WORKBECH_URL= "http://sh.agent.fang.com/magent/house/lease/leaseinput.aspx";

    /**
     * 主页面下的租房->写字楼出租
     */
    String OFFICE_URL="http://sh.agent.fang.com/magent/house/office/officeLeaseInput.aspx?ownertype=0";


}
