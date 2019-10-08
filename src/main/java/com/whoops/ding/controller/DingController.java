package com.whoops.ding.controller;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DingController {

    @PostMapping("/callback")
    public String callback(@RequestParam("timeStamp")String timeStamp){
        DingTalkEncryptor dingTalkEncryptor;
        try {
            // 创建加解密类
            // 第一个参数为创建应用之时填写的token
            // 第二个参数为创建应用之时生成的数据加密密钥（ENCODING_AES_KEY）
            // 第三个参数，ISV开发传入应用的suiteKey
            // 具体参数值请查看开发者后台(//open-dev.dingtalk.com)
            // 注：其中，对于第三个参数，在第一次接受『验证回调URL有效性事件的时候』
            // 传入Env.CREATE_SUITE_KEY，对于这种情况，已在异常中catch做了处理。
            dingTalkEncryptor = new DingTalkEncryptor("moon2003", "b7zy1qq4rgfoyzcfilun7lwqlom3wb25i90dayk4r9c", "suitel2zkza6sxsdngva0");
            // 获取从encrypt解密出来的明文
            plainText = dingTalkEncryptor.getDecryptMsg(msgSignature, timeStamp, nonce, encrypt);
        } catch (DingTalkEncryptException e) {
            e.printStackTrace();
        }

        //  对从encrypt解密出来的明文进行处理
        //  不同的eventType的明文数据格式不同
        JSONObject plainTextJson = JSONObject.parseObject(plainText);
        String eventType = plainTextJson.getString("EventType");
        // res是需要返回给钉钉服务器的字符串，一般为success
        // "check_create_suite_url"和"check_update_suite_url"事件为random字段
        // 具体请查看文档或者对应eventType的处理步骤
        //
        String res = "success";
        switch (eventType) {
            case "check_create_suite_url":
                //可返回表明服务端“收到了”的字段
                break;
        }
        //  对返回信息进行加密
        long timeStampLong = Long.parseLong(timeStamp);
        Map <String, String> jsonMap = null;
        try {
            // jsonMap是需要返回给钉钉服务器的加密数据包
            jsonMap = dingTalkEncryptor.getEncryptedMap(res, timeStampLong, nonce);
        } catch (DingTalkEncryptException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.putAll(jsonMap);
        response.getWriter().append(json.toString());


        return "success";
    }

}
