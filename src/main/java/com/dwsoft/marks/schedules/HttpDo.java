package com.dwsoft.marks.schedules;

import com.dwsoft.marks.common.ReadText;
import com.dwsoft.marks.common.UtilFSR;
import net.sf.json.JSONArray;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class HttpDo extends QuartzJobBean {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(1500000));
    private static org.apache.commons.logging.Log logg = LogFactory
            .getLog(HttpDo.class);


    public static void main(String[] args) {
        for (int i = 0; i <10000 ; i++) {
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i==100){
                executor.getQueue().clear();
                System.out.println(">>>>>>>>>>线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                        executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
           return;
            }
            int finalI = i;
            executor.execute(new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.currentThread().sleep(5*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(finalI);
                }
            }));
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
    }
    /**
     * 生成日志表
     */
    protected void executeInternal(JobExecutionContext arg0) {
        try {
            if(ReadText.existsFile()){
            if(ReadText.checkTxtMap()){
                logg.warn("当日数据已经加载");
            }else{
                logg.warn("开始今天的战斗");
                executor.getQueue().clear();
                work();
                logg.warn("开始今天的战斗----game over");
            }
            }else {
                logg.warn("当日数据还未上传,继续昨天的战斗");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void work() {
        logg.warn("先来一波删除 ");
        ReadText.deleteFile();
        logg.warn("删除结束 ");
        logg.warn("先清除之前的    start");
        ReadText.clearMdnList();
        logg.warn("清理结束   end");
        logg.warn("开始读取今天的   start");
        boolean flag = ReadText.readTxtFile();
        logg.warn("读取结束   end ");
        if(flag) {
            logg.warn("处理读取的数据   start");
            List<String> mdnList = doList();
            logg.warn("处理读取的数据   end");
            if(mdnList!=null && mdnList.size()>0) {
                IPFlush.setFlag(true);
                IPFlush.flush();
                logg.warn("start--爬爬爬爬爬爬爬爬爬");
                httpGet(mdnList);
                logg.warn("end--爬爬爬爬爬爬爬爬爬");
            }else{
                logg.warn("没有数据  关掉定时获取ip");
                IPFlush.setFlag(false);
            }
        }else{
            logg.warn("没有文件  关掉定时获取ip");
            IPFlush.setFlag(false);
        }
    }

    private static void httpGet(List<String> mdnList) {
        for (int i = 0; i < mdnList.size(); i++) {
            int finalI = i;
            if(i%10==0) {
                try {
                    Thread.currentThread().sleep(5*100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            executor.execute(new Thread(new Runnable() {
                public void run() {
                   doGet(mdnList.get(finalI));
                }
            }));
            logg.warn("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }


    }

    public static void doGet(String mdn) {
        try {
            String path = "http://127.0.0.1:16060/pagemark/mdn/check?who=dwsoft&mdn=" + mdn;
//            path = "http://127.0.0.1:8080/pagemark/mdn/check?mdn=" + mdn;
            logg.warn(path);
            URL url = new URL(path);
            //3). 打开连接, 得到HttpURLConnection对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //4). 设置请求方式,连接超时, 读取数据超时
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(3000);
            //5). 连接服务器
            connection.connect();
            //6). 发请求, 得到响应数据
            //得到响应码, 必须是200才读取
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //得到InputStream, 并读取成String
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String  result=baos.toString();
                result=new String(result.getBytes(),"UTF-8");
//                logg.warn(result);
                baos.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> doList() {
        List<String> mdnList = ReadText.getMdnList();
        if (mdnList == null || mdnList.size() == 0) {
            logg.warn("mdnList 没有数据或加载数据错误");
        } else {
            int size = mdnList.size();
            if (size > 1500000) mdnList = mdnList.subList(0, 1500000);

        }

        return mdnList;
    }

}
