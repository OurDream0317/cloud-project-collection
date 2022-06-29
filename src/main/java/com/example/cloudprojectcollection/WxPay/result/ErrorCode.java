package com.example.cloudprojectcollection.WxPay.result;

/**
 * @Author: Wwx
 * @createTime: 2022年06月29日
 * @version: 0.0.1
 * @Description: 小程序端返回码信息
 */

public class ErrorCode {
    /**
     * 状态码
     */
    public static final String STATE ="returnCode";
    /**
     * 返回信息
     */
    public static final String MSG ="msg";
    /**
     * 返回数据的key
     */
    public static final String DATA ="data";
    /**
     * 成功 200
     */
    public static final int SUCCESS =200;
    /**
     * 失败 400
     */
    public static final int FAIl =400;
    /**
     * 登录状态失效 403
     */
    public static final int INVALID =403;
    /**
     * 服务器错误 500
     */
    public static final int ERROR =500;
    /**
     * code,3rd_session失效 401
     */
    public static final int CODE_INVALID =401;

}
