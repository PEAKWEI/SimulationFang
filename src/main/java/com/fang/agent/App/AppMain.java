package com.fang.agent.App;

import com.fang.agent.services.UploadMsgServices;
import com.fang.agent.services.UploadMsgServicesImpl;

/**
 * @program: SimulationFang
 * @description:
 * @author: Mr.Dai
 * @create: 2018-11-17 21:16
 **/
public class AppMain {

    public static void main(String[] args) {
        UploadMsgServices services = new UploadMsgServicesImpl();
        services.writeDataOffices();
    }
}
