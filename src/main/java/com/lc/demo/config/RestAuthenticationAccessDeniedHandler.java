package com.lc.demo.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.lc.demo.entity.ResultCode;
import com.lc.demo.entity.ResultJson;

/**
  *  权限不足处理类，返回403
 * @author liangc
 * 2019年6月30日
 */
@Component
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// 登陆状态下，权限不足执行该方法
        logger.error("权限不足：" + accessDeniedException.getMessage());
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = ResultJson.failure(ResultCode.FORBIDDEN, accessDeniedException.getMessage()).toString();
        printWriter.write(body);
        printWriter.flush();

	}

}
