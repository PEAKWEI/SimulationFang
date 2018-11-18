package com.fang.agent.services;

import com.fang.agent.config.URLConfig;
import com.fang.agent.config.UserConfig;
import com.fang.agent.exception.UserException;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.URL;


/**
 * @program: SimulationFang
 * @description: 获取经纪云后台管理会话
 * @author: Mr.Dai
 * @create: 2018-11-17 17:32
 **/
public class GlobalSessionImpl implements GlobalSession {

    /**
     * 全局web browser
     */
    private  WebClient client = new WebClient(BrowserVersion.FIREFOX_52);

    private final static String JING_JI_YUNURL = "http://agent.fang.com/";

    private static final Logger logger = LoggerFactory.getLogger(GlobalSessionImpl.class);
    private HtmlPage page;


    @Override
    public WebClient getYetLoginFangSession() {
        try {
            //设置下载图片
            client.getOptions().setDownloadImages(true);
            //设置css
            client.getOptions().setCssEnabled(false);
            client.getCookieManager().setCookiesEnabled(true);
            client.getOptions().setRedirectEnabled(true);
            // 设置Ajax异步
            client.setAjaxController(new NicelyResynchronizingAjaxController());
            //忽略ssl认证
            client.getOptions().setUseInsecureSSL(true);
            // 屏蔽掉异常
            client.getOptions().setThrowExceptionOnFailingStatusCode(false);
            client.getOptions().setThrowExceptionOnScriptError(false);
            client.getOptions().setPrintContentOnFailingStatusCode(false);
            // 设置连接超时时间 ，这里是10S。如果为0，则无限期
            client.getOptions().setJavaScriptEnabled(true);
            client.getOptions().setTimeout(8000);
            client.setJavaScriptTimeout(6000);

            //设置js延迟执行时间
            page = page = client.getPage(JING_JI_YUNURL);

            client.waitForBackgroundJavaScript(10000);

            // <a id="loginID" href="javascript:;">登录</a>
            final HtmlElement curnode = page.querySelector("#loginID");
            curnode.click();

            /**
             * 这里只需要弹出登录面板即可不需要再一次模拟点击代码
             */
            //<a href="javascript:;" class="login-tab fr">账号密码登录</a>
           /* final HtmlElement loginpanel = page.querySelector(".login-tab fr");
            client.waitForBackgroundJavaScript(3000);
            System.out.println(page.asXml());
            System.out.println(loginpanel.asXml());
            loginpanel.click();*/


            //<input id="txtusername" type="text" placeholder="手机号/用户名/邮箱" value="手机号/用户名/邮箱" class="username inputpub">
            //<input id="password" name="password" type="password" value="请输入登录密码" class="password inputpub" placeholder="请输入登录密码">

            final HtmlInput username = page.querySelector("#txtusername");

            final HtmlInput password = page.querySelector("#password");

            username.setValueAttribute(UserConfig.USERNAME);
            password.setValueAttribute(UserConfig.PASSWORD);
            username.setNodeValue(UserConfig.USERNAME);
            password.setNodeValue(UserConfig.PASSWORD);

            //<input type="button" id="imgbt_login" value="登录" class="loginbtn">

            HtmlElement loginBtn = page.querySelector("#imgbt_login");
            HtmlPage mainpage = loginBtn.click();
            client.waitForBackgroundJavaScript(5000);

            String mainhtml = mainpage.asXml();
            if (!mainhtml.contains(UserConfig.CHINANAME)) {
                throw new UserException("登录失败！");
            }
        }
        catch (UserException e){
            e.printStackTrace();
        } catch (FailingHttpStatusCodeException | IOException e) {
            e.printStackTrace();
            logger.error("打开网站失败！");
        }
        return page.getWebClient();
    }

    @Override
    public HtmlPage gotoURlForPage(String url) throws IOException {
        HtmlPage page = client.getPage(new URL(url));
        return page;
    }
}
