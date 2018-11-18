package com.fang.agent.services;


import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;

/**
 * @program: SimulationFang
 * @description: 实现类
 * @author: Mr.Dai
 * @create: 2018-11-17 21:15
 **/
public class RentingHouseImpl implements RentingHouse {


    @Override
    public boolean writeToOfficePageMessage(HtmlPage page) throws IOException {
        HtmlPage officePage=page;

        //<input id="input_PROJNAME"
        HtmlInput builderName = officePage.querySelector("#input_PROJNAME");
        builderName.removeAttribute("onchange");
        builderName.click();
        HtmlPage type = (HtmlPage) builderName.type('z');
        //builderName.type(KeyboardEvent.DOM_VK_SPACE);
        builderName.setValueAttribute("中安银领国际");
        builderName.setNodeValue("中安银领国际");
        builderName.setTextContent("中安银领国际");



        /**
         * 算法规则:（1）：builderName可以输入模糊值，通过遍历Suggestion 拿到具体楼盘名字
         * （2）： builderName可以输入最接近的准确值的数据，DomNodeList<HtmlElement> 直接获取第二个即可
         * 这里选择2
         */


        // <div id="showprojdc" style="display: none">
        HtmlDivision node = officePage.querySelector("#showprojdc");

        //<span style="float: left;margin-right:5px">上海周边[嘉兴]</span>
        //node.setNodeValue("<span style=\"float: left;margin-right:5px\">上海周边[嘉兴]</span>");

        HtmlElement span = node.appendChildIfNoneExists("span");

        span.setAttribute("style","float: left;margin-right:5px");
        span.setNodeValue("上海周边[嘉兴]");
        span.setTextContent("上海周边[嘉兴]");

        //<input id="input_ADDRESS"

        HtmlInput address = officePage.querySelector("#input_ADDRESS");
        address.setValueAttribute("嘉兴中环东路与周安路的交汇处周安路399号");
        address.setTextContent("嘉兴中环东路与周安路的交汇处周安路399号");

        System.out.println(address.asXml());


        // <input id="str_innerid" name="str_innerid" class="input01
        HtmlInput buildingNumber = officePage.querySelector("#str_innerid");
        buildingNumber.removeAttribute("validation");
        buildingNumber.setValueAttribute("2");
        buildingNumber.setTextContent("2");
        buildingNumber.click();

        System.out.println(buildingNumber.asXml());
        /**
         * <td class="equipment">
         * input_y_str_SubType_xzl ==>纯写字楼
         * input_y_str_SubType_szl ==>商住楼
         * input_y_str_SubType_ys  ==>商业综合体楼
         * input_y_str_SubType_jd  ==>酒店写字楼
         */
        HtmlInput buildingType = officePage.querySelector("#input_y_str_SubType_xzl");
        buildingType.click();
        buildingType.setAttribute("checked","checked");

        System.out.println(buildingType.asXml());

        //<input id="input_PRICE" name="input_y_nu
        HtmlInput input_price = officePage.querySelector("#input_PRICE");
        input_price.removeAttribute("validation");
        input_price.setValueAttribute("12");
        input_price.setTextContent("12");

        System.out.println(input_price.asXml());

        //<input id="input_PropFee" name="input_n_num_PropFee" class="input01 wid
        HtmlInput input_PropFee = officePage.querySelector("#input_PropFee");
        input_PropFee.setValueAttribute("22");
        input_PropFee.setTextContent("22");

        System.out.println(input_PropFee.asXml());

        //<input class="input01 wid120 input_error" name="6b5e2e9binput9d54" type="text" id="BuildingArea"
        HtmlInput builderAera = officePage.querySelector("#BuildingArea");
        builderAera.removeAttribute("validation");
        builderAera.setValueAttribute("25");
        builderAera.setTextContent("25");

        System.out.println(builderAera.asXml());

        //<input class="input01 wid30 input_error" name="input_FLOOR" type="text" maxlength="3" id="input_FLOOR"
        //<input class="input01 wid30 input_error" name="input_ALLFLOOR" type="text" id="input_ALLFLOOR"
        HtmlInput FirstFloor = officePage.querySelector("#input_FLOOR");
        HtmlInput totalFloor = officePage.querySelector("#input_ALLFLOOR");
        FirstFloor.setValueAttribute("1");
        totalFloor.setValueAttribute("3");
        FirstFloor.setTextContent("1");
        totalFloor.setTextContent("3");

        System.out.println(FirstFloor.asXml());
        System.out.println(totalFloor.asXml());
        //<div id="showofficeleveldc"> 请选择写字楼级别
        /**
         * input_n_str_propertyGrade1==>甲级
         * input_n_str_propertyGrade2==》乙级
         * input_n_str_propertyGrade3==》丙级
         * input_n_str_propertyGrade4==》其它
         */
        HtmlInput showofficeleveldc = officePage.querySelector("#input_n_str_propertyGrade1");
        showofficeleveldc.click();
        showofficeleveldc.setAttribute("checked","checked");

        System.out.println(showofficeleveldc.asXml());

        // <input id="houseTitle" class="input01 wid350 input_error"
        HtmlInput houseTitle = officePage.querySelector("#houseTitle");

        houseTitle.removeAttribute("onkeyup");
        houseTitle.removeAttribute("onfocus");
        houseTitle.removeAttribute("onchange");

        houseTitle.setValueAttribute("三大三大所");
        houseTitle.setTextContent("三大三大所");

        System.out.println(houseTitle.asXml());

        // <input type="button" class="btu01" id="agentmainput_salesentry_00" value="提交"
        HtmlInput submit = officePage.querySelector("#agentmainput_salesentry_00");
        HtmlPage page1 = submit.click();
        //System.out.println(page1.asXml());

        //mainform
        HtmlForm mainform = officePage.querySelector("#mainform");

        HtmlPage click = mainform.click();
        System.out.println(click.asXml());

        return true;
    }
}
