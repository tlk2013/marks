package com.dwsoft.marks.controller;

import java.util.Date;

import com.dwsoft.marks.common.CommonUtils;
import com.dwsoft.marks.common.ResponUtil;
import com.dwsoft.marks.model.UserInfo;
import com.dwsoft.marks.service.UserInfoService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author tlk
 *
 */
@RequestMapping("/data")
@RestController
public class DataAction {
	private static org.apache.commons.logging.Log logg = LogFactory
			.getLog(DataAction.class);
	@Autowired
	UserInfoService userInfoService;

	/**
	 * 号码识别
	 *
	 * @param mdn
	 * @return
	 */
	@RequestMapping(value = "/query", method = { RequestMethod.POST,
			RequestMethod.GET })
	public Object query(@RequestParam(value = "mdn") String mdn) {
		try {
			UserInfo user = userInfoService.getUserInfoByMdn(mdn);
			if (user != null)
				return ResponUtil.success_yes(user);
		} catch (Exception e) {
			e.printStackTrace();
			logg.warn("query>>"+e.getMessage());
			return ResponUtil.error();
		}
		return ResponUtil.success_no();
	}
	/**
	 * 数据同步
	 * @param mdn
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/sync", method = { RequestMethod.POST,
			RequestMethod.GET })
	public Object sync(
			@RequestParam(value = "mdn") String mdn,
			@RequestParam(value = "status") String status,
			@RequestParam(value = "url") String url) {
		try {
			UserInfo userInfo=new UserInfo(mdn, url, CommonUtils.dateToString(new Date(), 1),CommonUtils.dateToString(new Date(), 1), "",status);
			logg.warn("sync>>"+userInfo);
			userInfoService.saveOrupdateUserInfo(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logg.warn("sync>>"+e.getMessage());
			return ResponUtil.error();
		}
		return ResponUtil.respon(1000, "请求成功");
	}
}
