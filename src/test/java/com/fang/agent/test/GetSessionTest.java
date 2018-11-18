package com.fang.agent.test;

import com.fang.agent.services.GlobalSession;
import com.fang.agent.services.GlobalSessionImpl;

/**
 * @program: SimulationFang
 * @description: 登录测试
 * @author: Mr.Dai
 * @create: 2018-11-17 17:56
 **/
public class GetSessionTest {

    public static void main(String[] args) {

        GlobalSession session = new GlobalSessionImpl();

        session.getYetLoginFangSession();

    }
}
