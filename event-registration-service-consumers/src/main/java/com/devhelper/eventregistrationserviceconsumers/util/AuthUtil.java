package com.devhelper.eventregistrationserviceconsumers.util;

import com.devhelper.eventregistrationserviceconsumers.constant.HeaderConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
public class AuthUtil {

   public HttpHeaders createHeaders(String username, String password){
       HttpHeaders httpHeaders=new HttpHeaders();
       String auth =basicAuth(username,password);
       httpHeaders.add("Authorization",auth);
       httpHeaders.add(HeaderConstant.TID,"122334");
       return httpHeaders;
    }

    public String basicAuth(String userName,String password){
        String valueToEncode = userName + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
