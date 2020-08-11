package com.example.mock.demomock.common;

import com.example.mock.demomock.vo.BillpayResponseEntity;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLEncodeModel2 {

    public String toSendData(Charset charset, BillpayResponseEntity billpayResponseEntity) {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"").append(charset.displayName()).append("\"?>");
        builder.append("<ROOT>");
        Document document1;

        //这是在创建一个根节点

        Element root = DocumentHelper.createElement("MasMessage");

        //把根节点变成一个Document 对象方便添加子节点

        document1 = DocumentHelper.createDocument(root);

        Element version = root.addElement("version");
        version.setText("1.0");

        Element indAuthContent = root.addElement("indAuthContent");
        Element merchantId = indAuthContent.addElement("merchantId");
        merchantId.setText(billpayResponseEntity.getMerchantId());
        Element terminalId = indAuthContent.addElement("terminalId");
        terminalId.setText(billpayResponseEntity.getTerminalId());
        Element customerId = indAuthContent.addElement("customerId");
        customerId.setText(billpayResponseEntity.getCustomerId());
        Element externalRefNumber = indAuthContent.addElement("externalRefNumber");
        externalRefNumber.setText(billpayResponseEntity.getExternalRefNumber());
        Element storablePan = indAuthContent.addElement("storablePan");
        storablePan.setText(billpayResponseEntity.getStorablePan());
        Element token = indAuthContent.addElement("token");
        token.setText(billpayResponseEntity.getToken());
        Element responseCode = indAuthContent.addElement("responseCode");
        responseCode.setText(billpayResponseEntity.getResponseCode());

        Element responseTextMessage = indAuthContent.addElement("responseTextMessage");
        responseTextMessage.setText(billpayResponseEntity.getResponseTextMessage());
//        document1.add(root);
//        document1.add(version);
//        document1.add(indAuthContent);
//        document1.add(merchantId);
//        document1.add(terminalId);
//        document1.add(customerId);
//        document1.add(externalRefNumber);
//        document1.add(storablePan);
//        document1.add(token);
//        document1.add(responseCode);
//        document1.add(responseTextMessage);
//        String requestXml = document1.asXML();
//        return requestXml;
        return createXml(billpayResponseEntity);
    }



    public String  createXml(BillpayResponseEntity billpayResponseEntity){
        StringBuffer soapResultData = new StringBuffer();
        soapResultData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version><indAuthContent>");
        Map<String,String> resmap= new LinkedHashMap<>();
        resmap.put("merchantId",billpayResponseEntity.getMerchantId());
        resmap.put("terminalId",billpayResponseEntity.getTerminalId());
        resmap.put("customerId",billpayResponseEntity.getCustomerId());
        resmap.put("externalRefNumber",billpayResponseEntity.getExternalRefNumber());
        resmap.put("responseCode",billpayResponseEntity.getResponseCode());
        resmap.put("responseTextMessage",billpayResponseEntity.getResponseTextMessage());
        resmap.put("storablePan",billpayResponseEntity.getStorablePan());
        resmap.put("token",billpayResponseEntity.getToken());
        //请求体
        soapResultData.append(requesttoxml(resmap));
        soapResultData.append("</indAuthContent></MasMessage>");
        return soapResultData.toString();
    }

    public  String requesttoxml(Map<String, String> resmap){
        StringBuffer soapResultData = new StringBuffer();
        for(String key : resmap.keySet()){
            soapResultData.append("<");
            soapResultData.append(key);
            soapResultData.append(">");
            soapResultData.append(resmap.get(key));
            soapResultData.append("</");
            soapResultData.append(key);
            soapResultData.append(">");
        }
        return soapResultData.toString();
    }

}
