package com.love.life.common;


import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 
 * 〈功能简述〉
 * @author wugy08
 * @version [版本号，默认V1.0.0]
 * @Credited 2016年3月16日 下午2:37:10
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class BaseController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 失败返回
     * @param code
     * @return
     */
    protected CommonResponse errorReturn(int code) {
        CommonResponse response = new CommonResponse();
        response.setStatus("failure");
        response.setErrorCode(code);
        response.setErrorMessage(ErrorCodeMsgUtil.getMessage(code));
        response.setData(new JSONArray());
        
        return response;
    }
    
    /**
     * 失败返回
     * @param code
     * @return
     */
   protected CommonResponse errorReturn(CommonException e) {
        CommonResponse response = new CommonResponse();
        response.setStatus("failure");
        response.setErrorCode(e.getCode());
        response.setErrorMessage(e.getMessage());
        response.setData(new JSONArray());
       
        return response;
    }


    /**
     * 成功返回
     * @param data
     * @return
     */
    protected CommonResponse successReturn(Object data) {
        CommonResponse response = new CommonResponse();
        response.setStatus("success");
        response.setErrorCode(0);
        response.setErrorMessage("");
        response.setData(data);
        
        return response;
    }
    
    /**
     * 使用此方法确保data中status和error_code有值
     * @param data
     * @return
     */
    protected CommonResponse commonReturn(JSONObject data) {
        CommonResponse response = new CommonResponse();
        response.setStatus(data.getString("status"));
        response.setErrorCode(Integer.parseInt(data.getString("error_code")));
        response.setData(data.get("data"));
        response.setErrorMessage(data.getString("error_message"));
      
        return response;
    }

    protected CommonResponse commonSuccessReturn(JSONObject data) {
        CommonResponse response = new CommonResponse();
        response.setStatus("success");
        response.setErrorCode(0);
        response.setData(data);
        response.setErrorMessage("");
       
        return response;
    }

    /**
     * 失败返回
     * @param code
     * @param args
     * @return
     */
    protected CommonResponse errorReturn(int code,Object[] args) {
        CommonResponse response = new CommonResponse();
        response.setStatus("failure");
        response.setErrorCode(code);
        response.setErrorMessage(ErrorCodeMsgUtil.getMessage(code,args));
        response.setData(new JSONArray());
       
        return response;
    }

    /**
     * 失败返回
     * @param code
     * @param message
     * @return
     */
    protected CommonResponse errorReturn(int code,String message) {
        CommonResponse response = new CommonResponse();
        response.setStatus("failure");
        response.setErrorCode(code);
        response.setErrorMessage(message);
        response.setData(new JSONArray());
        
        return response;
    }

}
