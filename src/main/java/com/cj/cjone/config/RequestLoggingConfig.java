package com.cj.cjone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingConfig {

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);     // 쿼리 파라미터 포함
		filter.setIncludePayload(true);         // 요청 본문 포함
		filter.setMaxPayloadLength(10000);      // 본문 최대 길이
		filter.setIncludeHeaders(false);        // 헤더 포함 여부
		filter.setAfterMessagePrefix("요청 데이터: ");
		return filter;
	}
}