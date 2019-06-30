package com.lc.demo.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lc.demo.entity.RespModel;

/**
  *  认证失败处理类，返回401
 * @author liangc
 * 2019年6月30日
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		//验证为未登陆状态会进入此方法，认证错误
        logger.error("认证失败：" + authException.getMessage());
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        RespModel respModel = new RespModel(401, "token验证失败！");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(respModel));
        printWriter.flush();
        
	}

}
