package com.example.mock.demomock.vo;

public class BillpayResponseEntity {
    String merchantId ;
    String terminalId;
    String customerId;
    String externalRefNumber;
    String storablePan;
    String token;
    String responseCode;
    String responseTextMessage;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getExternalRefNumber() {
        return externalRefNumber;
    }

    public void setExternalRefNumber(String externalRefNumber) {
        this.externalRefNumber = externalRefNumber;
    }

    public String getStorablePan() {
        return storablePan;
    }

    public void setStorablePan(String storablePan) {
        this.storablePan = storablePan;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseTextMessage() {
        return responseTextMessage;
    }

    public void setResponseTextMessage(String responseTextMessage) {
        this.responseTextMessage = responseTextMessage;
    }
}
