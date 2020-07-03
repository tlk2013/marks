package com.dwsoft.marks.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.dwsoft.marks.common.MDNCheck;
import com.dwsoft.marks.common.ReadText;
import com.dwsoft.marks.model.MarkInfo;
import com.dwsoft.marks.service.UserInfoService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/mdn")
@Controller
public class MDNAction {
	 private static org.apache.commons.logging.Log logg = LogFactory.getLog(MDNAction.class);
	@Autowired
	UserInfoService userInfoService;
	/**
	 * @param mdn
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/check", method = {
			RequestMethod.POST, RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	public Object getCheck(
			@RequestParam(value = "mdn") String mdn,
			@RequestParam(value = "who",defaultValue = "unknown") String who
			) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		String re= MDNCheck.GetCheckBy360(mdn);
		MarkInfo ph=null;
		String sjy="360";
		if("normal".equals(re)){
		 re=MDNCheck.GetCheckByBD(mdn);
		 sjy="baidu";
		 if("normal".equals(re)){
				re=MDNCheck.GetCheckBySG(mdn);
				sjy="sougou";
			}
		}
		map.put(mdn,re);
		if(!"normal".equals(re) && !re.contains("快递100")){
			ph=new MarkInfo();
			ph.setMdn(mdn);
			ph.setMark(re);
			ph.setDate(LocalDate.now().toString());
			userInfoService.savePhoneInfo(ph);
		}
		logg.warn(who+"["+sjy+"]>>omarko>>"+map);
	 return map;
	}
	public static void main(String[] args) {
		String mdn = "13720036073";
		String re=MDNCheck.GetCheckBy360(mdn);
		String sjy="360";
//		if("normal".equals(re)){
		 re=MDNCheck.GetCheckByBD(mdn);
		 sjy="baidu";
//		 if("normal".equals(re)){
				re=MDNCheck.GetCheckBySG(mdn);
				sjy="sougou";
//			}
//		}
		Map<String,String> map=new HashMap<>();
		map.put(mdn,re);
		logg.warn(sjy+">>>>"+map);
	}
	@ResponseBody
	@RequestMapping("/flushMap")
	public String flushMap(){
		ReadText.cleartextMap();
		return "success";
	}

}
