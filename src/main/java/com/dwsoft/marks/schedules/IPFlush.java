package com.dwsoft.marks.schedules;

import com.dwsoft.marks.common.UtilFSR;
import net.sf.json.JSONArray;

import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;




public class IPFlush extends QuartzJobBean {
	private static boolean flag=false;
	private static org.apache.commons.logging.Log logg = LogFactory
			.getLog(IPFlush.class);
	/**
	 * 生成日志表
	 */
	protected void executeInternal(JobExecutionContext arg0)
			{
		try {
			if(flag)
			work();
			else{
				logg.warn("没有数据 定时获取ip 受阻" );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		work();
	}
	private static void work (){
		logg.warn("定时获取ip  start" );
		flush();
		logg.warn("定时获取结束ip   end" );
	}
	public static void flush() {
		try {
			JSONArray jSONArray= UtilFSR.getJSONArray();
			UtilFSR.setJSONArray(jSONArray);
		} catch (Exception e) {
			logg.warn("wandou >>> ip>>>" +e.getMessage());
			e.printStackTrace();
		}
	}

	public static void setFlag(boolean flag) {
		IPFlush.flag = flag;
	}
}
