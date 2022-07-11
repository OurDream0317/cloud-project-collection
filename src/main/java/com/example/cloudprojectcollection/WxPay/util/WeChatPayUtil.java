package com.example.cloudprojectcollection.WxPay.util;

import com.example.cloudprojectcollection.WxPay.config.WeChatConfig;
import com.example.cloudprojectcollection.WxPay.entity.WeChatPayDto;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import static com.example.cloudprojectcollection.WxPay.util.HttpUtils.generateNonceStr;
import static com.github.wxpay.sdk.WXPayUtil.generateSignature;
import static com.github.wxpay.sdk.WXPayUtil.mapToXml;

/**
 * @Author: Wwx
 * @createTime: 2022年06月29日
 * @version: 0.0.1
 * @Description: 微信支付工具类
 */
@Slf4j
@Component
public class WeChatPayUtil {
    /**
     * 微信统一下单接口
     * @param miniDTO
     * @param openId
     * @return
     * @throws Exception
     */
    public Map<String, String> getPrePayInfo(WeChatPayDto miniDTO, String openId) throws Exception {
        Map<String, String> map = Maps.newHashMap();
        //微信分配的小程序ID
        map.put("appid", "wxd678efh567hg6787");
        //微信支付分配的商户号
        map.put("mch_id", "1230000109");
        //随机字符串，长度要求在32位以内
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        //商品简单描述
        map.put("body", miniDTO.getBody());
        //商户系统内部订单号
        map.put("out_trade_no", miniDTO.getOutTradeNo());
        //订单总金额，单位为分
        map.put("total_fee", miniDTO.getTotalFee());
        //支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
        map.put("spbill_create_ip", getLocalIp());
        //交易类型  JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付，不同trade_type决定了调起支付的方式
        map.put("trade_type", "JSAPI");
        //回调地址  异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
        map.put("notify_url", "https://wx.zschool.com/anon/weChatPay/notify");
        //trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
        map.put("openid", openId);
        String unifiedorderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 微信统一下单URL
        String sign = generateSignature(map, "ABCDEFGHIJKabcdefghijk1211167890");// 生成签名 PAY_API_SECRET=微信支付相关API调用时使用的秘钥
        map.put("sign", sign);  // 参数配置 我直接写成"sign"
        String xml = mapToXml(map);
        //请求微信统一下单接口
        String xmlStr = HttpUtils.httpRequest(unifiedorderUrl, "POST", xml);

        //解析xml
        Map map1 = HttpUtils.doXMLParse(xmlStr);
        String return_code = (String) map1.get("return_code");//返回状态码
        String result_code = (String) map1.get("result_code");//返回状态码
        String err_code = (String) map1.get("err_code");//返回状态码
        String err_code_des = (String) map1.get("err_code_des");//返回状态码
        log.info(xmlStr);
        if (return_code.equals("SUCCESS") || return_code.equals(result_code)) {
            // 业务结果
            String prepay_id = (String) map1.get("prepay_id");//返回的预付单信息
            Map<String, String> payMap = new HashMap<>();
            payMap.put("appId", WeChatConfig.WECHAT_APPID);  // 参数配置
            payMap.put("timeStamp", HttpUtils.getCurrentTimestamp() + "");  //时间
            payMap.put("nonceStr", generateNonceStr());  // 获取随机字符串
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            String paySign = generateSignature(payMap, WeChatConfig.WECHAT_key); //第二次生成签名
            payMap.put("paySign", paySign);
            payMap.put("prepayId", prepay_id);
            payMap.put("out_trade_no", miniDTO.getOutTradeNo());
            return payMap;   //返回给前端，让前端去调支付 ，完成后你去调支付查询接口，看支付结果，处理业务。
        } else {
            //打印失败日志
        }
        return null;

    }

    /**
     * 获取当前机器的ip
     *
     * @return String
     */
    public static String getLocalIp() {
        InetAddress ia = null;
        String localip = null;
        try {
            ia = InetAddress.getLocalHost();
            localip = ia.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localip;

    }

}
