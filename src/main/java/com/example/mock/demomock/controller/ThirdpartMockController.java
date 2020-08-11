package com.example.mock.demomock.controller;

import com.example.mock.demomock.common.XMLEncodeModel2;
import com.example.mock.demomock.vo.BillpayResponseEntity;
import jdk.internal.util.xml.impl.XMLWriter;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutputStream;
import java.io.IOException;

@Slf4j
@RequestMapping("/mock")
@Controller
public class ThirdpartMockController {
    @RequestMapping("/billpay")
    public String billpayProtocalPay(@RequestBody  String request, HttpServletResponse response){
       log.info("模拟快钱响应接口入参：{}",request);
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            sos.write(new String(parseXml(request)).getBytes("UTF-8"));
        } catch (IOException e) {
            log.error("快钱异步通知响应信息异常",e);
        }finally {
            try{
                sos.flush();
                sos.close();
            }catch (IOException e){
                log.error("快钱异步通知响应信息-关闭流异常",e);
            }
        }

        return "";
    }

    private String  parseXml(String request) {
        Document document = null;
        String externalRefNumber = "";
        String customerId = "";
        String merchantId = "";
        String terminalId = "";
        try {
            document = DocumentHelper.parseText(request);
            Element rootElement = document.getRootElement();
            merchantId = rootElement.element("indAuthContent").element("merchantId").getTextTrim();
            externalRefNumber = rootElement.element("indAuthContent").element("externalRefNumber").getTextTrim();
            customerId = rootElement.element("indAuthContent").element("customerId").getTextTrim();
            terminalId = rootElement.element("indAuthContent").element("terminalId").getTextTrim();


            //拼装响应报文
            BillpayResponseEntity billpayResponseEntity = new BillpayResponseEntity();
            billpayResponseEntity.setCustomerId(customerId);
            billpayResponseEntity.setExternalRefNumber(externalRefNumber);
            billpayResponseEntity.setMerchantId(merchantId);
            billpayResponseEntity.setTerminalId(terminalId);
            billpayResponseEntity.setResponseCode("00");
            billpayResponseEntity.setResponseTextMessage("gmm成功挡板");
            billpayResponseEntity.setStorablePan("123");
            billpayResponseEntity.setToken("token123");

            XMLEncodeModel2 xmlEncodeModel2 = new XMLEncodeModel2();
            return xmlEncodeModel2.createXml(billpayResponseEntity);
        } catch (Exception e) {
            return "";
        }
    }

}
