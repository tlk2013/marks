package com.dwsoft.marks.controller;


import com.dwsoft.marks.common.ResponUtil;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class MyControllerAdvice {
	private static org.apache.commons.logging.Log logg = LogFactory
			.getLog(MyControllerAdvice.class);
	@ResponseBody
	@ExceptionHandler({ Exception.class })
	public Object handleArithmeticException(Exception ex) throws Exception {
		logg.warn("MyControllerAdvice>>"+ex);
		return ResponUtil.respon(1001, "�����쳣: " + ex.getMessage());
	}

}
