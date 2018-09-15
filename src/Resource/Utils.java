package Resource;

import Bean.JsonBean;
import Entity.FixTestInsHistoryEntity;


import Socket.SsMix;
import org.json.JSONObject;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.activation.DataSource;

public class Utils {



    public static String[] cutJson(String o){

        int length=o.length();
        System.out.println(length);
        String a=o.substring(0,length/2);
        String b=o.substring(length/2 ,length);//左闭右开
        return new String[]{a,b};
    }
    /**
     *  ano.put("cus_id",mapService.get("h_custom_id"));
     *                     ano.put("timestamp",System.currentTimeMillis()+"");
     *                     ano.put("reason",mapService.get("h_reason"));
     *                     ano.put("business", HandlerFinal.BUSINESS_STR[7]);
     *                     ano.put("eng_id",HandlerFinal.userId);
     *                     ano.put("eng_name",HandlerFinal.userName);  固定拿着六个的 another
     */
    public static String[] parseAnother(JSONObject jsonObject){
        JSONObject ano=jsonObject.getJSONObject("another");
        String cusId=ano.getString("cus_id");

        //String timestamp=ano.getString("timestamp");
        String timestamp=jsonObject.getString("timestamp");
        String reason=ano.getString("reason");
        String business=ano.getString("business");
        String eng_id=ano.getString("eng_id");
        String eng_name=ano.getString("eng_name");
        return new String[]{cusId,timestamp,reason,business,eng_id,eng_name};
    }

    public static String[] parseJsonArray(JSONObject jsonObject){

        return null;
    }
    public static void makeSixKV(String[]k ,FixTestInsHistoryEntity fie){
        fie.setUserId(k[0]);
        //fie.setDateHistory(new Timestamp(System.currentTimeMillis()));//万恶之源
        fie.setDateHistory(new Timestamp(Long.parseLong(k[1])));
        fie.setTextHistory(k[2]);
        fie.setBusiness(k[3]);
        fie.setEngId(k[4]);
        fie.setHuman(k[5]);
    }
    public static JSONObject JsonObjFac(String[]key,String[]value){
        JSONObject jsonObject=new JSONObject();
        for (int i=0;i<key.length;i++){
            jsonObject.put(key[i],value[i]);
        }
        return jsonObject;
    }


    /**
     26      * @param args
     27      * @throws Exception

     ojbk 邮箱服务器 终于成功了
     28      */
     public static void main(String[] args) throws Exception {

                 Properties prop = new Properties();
                 prop.setProperty("mail.host", "smtp.163.com");
         //prop.setProperty("mail.host", "smtp.hm.qiye.163.com");
                 prop.setProperty("mail.transport.protocol", "smtp");
                 prop.setProperty("mail.smtp.auth", "true");
                 //使用JavaMail发送邮件的5个步骤
                 //1、创建session
         javax.mail.Session session = Session.getInstance(prop);
                 //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
                session.setDebug(true);
                 //2、通过session得到transport对象
         javax.mail.Transport ts = session.getTransport("smtp");
                 //3、连上邮件服务器  管理员邮箱 账号:admin@zjdtst.com  密码:4Gt5Sc7Ik2
                 ts.connect("smtphm.qiye.163.com", "admin@zjdtst.com", "4Gt5Sc7Ik2");//smtphm.qiye.163.com
                //4、创建邮件
                Message message = createAttachMail(session);


        System.out.println(MimeBodyPart.class.getProtectionDomain().toString());
                 //5、发送邮件
                 ts.sendMessage(message, message.getAllRecipients());
                 ts.close();

                //countdown();
             }


    //  这里来给主scoket 分担一些负担


    public static void parseSub(){

    }

    /**
     * 解析资产分给所有的子模块
     * @param object
     * json格式  （post_user_id 发件人id）（post_user_pwd 发件人密码）（receive_user_id 收件人id）（title 邮件标题）（content 邮件内容）（filename 文件地址名）
     */
    public static void sendMailPerson(JSONObject object){
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.163.com");//不知道这个地方要不要改
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            //使用JavaMail发送邮件的5个步骤
            //1、创建session
            javax.mail.Session session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = null;
            ts = session.getTransport("smtp");
            //3、连上邮件服务器
            //ts.connect("smtp.163.com", "18768349255@163.com", "qq715686280");
            ts.connect("smtphm.qiye.163.com", object.getString("post_user_id"), object.getString("post_user_pwd"));//smtp.163.com
            //4、创建邮件
            Message message = createAttachMail(session,object);


            System.out.println(MimeBodyPart.class.getProtectionDomain().toString());
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 解析资产分给所有的子模块
     * @param object
     * json格式  （post_user_id 发件人id）（post_user_pwd 发件人密码）（receive_user_id 收件人id）（title 邮件标题）（content 邮件内容）（filename 文件地址名）
     */

    public static void sendMailEnv(JSONObject object){
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.163.com");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            //使用JavaMail发送邮件的5个步骤
            //1、创建session
            javax.mail.Session session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = null;
            ts = session.getTransport("smtp");
            //3、连上邮件服务器
            //ts.connect("smtp.163.com", "18768349255@163.com", "qq715686280");
            ts.connect("smtphm.qiye.163.com", object.getString("post_user_id"), object.getString("post_user_pwd"));//smtp.163.com
            //4、创建邮件
            Message message = createAttachMail(session,object);


            System.out.println(MimeBodyPart.class.getProtectionDomain().toString());
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     52     * @Method: createAttachMail
     53     * @Description: 创建一封带附件的邮件
     54     * @Anthor:孤傲苍狼
     55     *
     56     * @param session
     57     * @return
     58     * @throws Exception
     59     */
     public static MimeMessage createAttachMail(javax.mail.Session session,JSONObject jsonObject) throws Exception{
                 MimeMessage message = new MimeMessage(session);

                 //设置邮件的基本信息
                 //发件人
                 //message.setFrom(new InternetAddress("18768349255@163.com"));
                 message.setFrom(new InternetAddress(jsonObject.getString("post_user_id")));
                 //收件人
                 //message.setRecipient(Message.RecipientType.TO, new InternetAddress("715686280@qq.com"));
                  message.setRecipient(Message.RecipientType.TO, new InternetAddress(jsonObject.getString("receive_user_id")));
                //邮件标题
                //message.setSubject("JavaMail邮件发送测试");
                   message.setSubject(jsonObject.getString("title"));
                //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
                 MimeBodyPart text = new MimeBodyPart();
                 //text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");
                   text.setContent(jsonObject.getString("content"), "text/html;charset=UTF-8");
                //创建邮件附件
                MimeBodyPart attach = new MimeBodyPart();
               // DataHandler dh = new DataHandler(new FileDataSource("C:\\Users\\Administrator\\IdeaProjects\\DTSJ\\src\\架构.txt"));
             DataHandler dh = new DataHandler(new FileDataSource(jsonObject.getString("filename")));
                attach.setDataHandler(dh);
                 attach.setFileName(dh.getName());  //

                 //创建容器描述数据关系
                 MimeMultipart mp = new MimeMultipart();
                 mp.addBodyPart(text);
                mp.addBodyPart(attach);
                 mp.setSubType("mixed");

               message.setContent(mp);
               message.saveChanges();
                //将创建的Email写入到E盘存储
                 //message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
               //返回生成的邮件
                return message;
             }

    public static MimeMessage createAttachMail(javax.mail.Session session) throws Exception{
        MimeMessage message = new MimeMessage(session);

        //设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("admin@zjdtst.com"));

        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("715686280@qq.com"));

        //邮件标题
        message.setSubject("JavaMail邮件发送测试");

        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");

        //创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
         DataHandler dh = new DataHandler(new FileDataSource("C:\\Users\\Administrator\\IdeaProjects\\DTSJ\\src\\架构.txt"));

        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());  //

        //创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        mp.addBodyPart(attach);
        mp.setSubType("mixed");

        message.setContent(mp);
        message.saveChanges();
        //将创建的Email写入到E盘存储
        //message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
        //返回生成的邮件

        return message;
    }
    public static void countdown(){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                //执行任务

            System.out.println("定时任务"+SsMix.countdownSwitch);
            CountDownUtil cdu=new CountDownUtil();
            cdu.updateAll();

                //SsMix.countdownSwitch=true;
                //SsMix.countdownSwitch=! SsMix.countdownSwitch;
            }
        },0,3600*24*1000+5000);//86400000
    }

}
