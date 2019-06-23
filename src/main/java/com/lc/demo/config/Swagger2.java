package com.lc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author liangc
 * @ClassName com.lc.demo.config.Swgger2
 * @Description
 * @date 
 */
@Configuration
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(apiInfo())
				.select()
				// com.lc.demo.controller接口类所在的包
				.apis(RequestHandlerSelectors.basePackage("com.lc.demo.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.title("springboot利用swagger构建api文档")
//				.description("简单优雅的restfun风格，http://blog.csdn.net/saytime")
//				.termsOfServiceUrl("http://blog.csdn.net/saytime")
//				.version("1.0")
//				.build();
//	}
}
