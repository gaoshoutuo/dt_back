package Resource;

import Entity.FixMessageEntity;
import Entity.FixTestInsHistoryEntity;
import Entity.RegisterUserEntity;
import Util.ParseJson;
import org.json.JSONObject;
import Socket.*;
import java.net.Socket;

public class IDCFeature {
    private static String socketIoUrl="http://218.108.146.98:3222";
    public static IDCFeature idcFeature;
    public static IDCFeature getInstance(){
        if (idcFeature==null)idcFeature=new IDCFeature();

        return idcFeature;
    }

    public  void upsInsFeature(JSONObject jsonObject, Socket socket){
        JSONObject upsinsano=ParseJson.getSubjson(jsonObject,"another");
        //int upsInsStep=upsinsano.getInt("step");//这个地方不能把一些值弄成空的  要注意了  要注意了
        // if(upsInsStep==1){

        if (upsinsano.getInt("step")==1){
            String upsInsFileName=PDFmake.upsInsPdfMake(jsonObject);
            FixTestInsHistoryEntity fieUPSINS=new FixTestInsHistoryEntity();
            String[] jsonStrArrayUI=Utils.cutJson(jsonObject.toString());
            String[] upsINSAnother=Utils.parseAnother(jsonObject);
            Utils.makeSixKV(upsINSAnother,fieUPSINS);
            fieUPSINS.setFilepath(upsInsFileName);
            fieUPSINS.setJson1(jsonStrArrayUI[0]);
            fieUPSINS.setJson2(jsonStrArrayUI[1]);
            TISHistoryUtil.getInstance().add(fieUPSINS);
            JSONObject recpiptUI=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
            recpiptUI.put("timestamp",upsINSAnother[1]);recpiptUI.put("eng_name",upsINSAnother[5]); recpiptUI.put("business",upsINSAnother[3]);
            recpiptUI.put("reason",upsINSAnother[2]);recpiptUI.put("filename",upsInsFileName);recpiptUI.put("cus_id",upsINSAnother[0]);
            recpiptUI.put("title","提醒通知");recpiptUI.put("content","工程师"+upsINSAnother[5]+"的工作"+upsINSAnother[3]+"已经完成了，请您查收");
            recpiptUI.put("step",1);recpiptUI.put("blank1",jsonObject.getString("blank1"));
            // SocketIOClient.sendMessage(recpiptUI.toString());
            recpiptUI.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
            //socket.getOutputStream().write(recpiptUI.toString().getBytes("UTF-8"));
            SocketIOClient.setSocketUrl( socketIoUrl);
            SocketIOClient.sendMessage(recpiptUI.toString());
            jsonObject.put("first_filename",upsInsFileName);

            FixMessageEntity upsinsFme=FixMessageUtil.getInstance().makeFme(new String[]{
                    //upsinsano.getString("timestamp"),
                    jsonObject.getString("timestamp"),
                    upsinsano.getString("idc_id"),upsinsano.getString("idc_name"), upsinsano.getString("idc_location"),upsinsano.getString("idc_type"),
                    upsinsano.getString("cus_id"),upsinsano.getString("eng_id"),upsinsano.getString("business"),"0",upsinsano.getString("eng_name"),
                    null,null,null
            },1,Utils.cutJson(jsonObject.toString()));
            FixMessageUtil.getInstance().add(upsinsFme);


            //检查维保表
            CountDownUtil countDownUtil=new CountDownUtil();
            countDownUtil.queryLost(upsinsano.getString("idc_id"));
            countDownUtil.updateSingleUps(upsinsano.getString("idc_id"));
        }


        //这些真的有必要吗  表msg 的update不好吗
        //通知回执



        //离线消息添加
      if (jsonObject.getInt("step")==2){
            FixMessageEntity upsinsFme=new FixMessageEntity();//可以用同一个表 也就字段不同罢了
            upsinsFme.setBlank1(jsonObject.getString("blank1"));
            upsinsFme.setBlank2(jsonObject.getString("blank2"));
            //此时 jsonobj 要查表
            FixMessageEntity queryUI= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
            JSONObject jsonobj=new JSONObject(queryUI.getJson1()+queryUI.getJson2());

          jsonobj.put("blank1",jsonObject.getString("blank1"));
          jsonobj.put("blank2",jsonObject.getString("blank2"));
          jsonobj.put("step",2);
            String upsInsFileNameLast=PDFmake.upsInsPdfMake(jsonobj);
            upsinsFme.setBlank3(upsInsFileNameLast);

          jsonobj.put("last_filename",upsInsFileNameLast);
            upsinsFme.setIsWatch("2");
            upsinsFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(upsinsFme);


          // 嗯 email  姓名  文件名
          //先工程师
          String engId=upsinsFme.getEngId();
          RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(engId);
          initEmailJson(rue1.getEmail(),rue1.getName(),upsInsFileNameLast);


          String userId=upsinsFme.getUserId();
          RegisterUserEntity rue2=TableRegUtil.getInstance().queryInID(userId);
          initEmailJson(rue2.getEmail(),rue2.getName(),upsInsFileNameLast);


        }


                                       /* for (Socket sock:socketList){
                                            sock.getOutputStream().write(recpiptUI.toString().getBytes("UTF-8"));
                                        }*/
        System.out.println(socket.getLocalSocketAddress()+"----"+socket.getRemoteSocketAddress());

        //以下消息缓存
                                     /*   }else if (upsInsStep==2){
                                            String upsInsFileName=PDFmake.upsInsPdfMake(jsonObject);
                                            //update 后面三个字段

                                        }
*/

    }

    public void upsTestFeature(JSONObject jsonObject, Socket socket,int lengthMAX){
        JSONObject upstestano=ParseJson.getSubjson(jsonObject,"another");
        int step=upstestano.getInt("step");
        if (step==1){
        String upsTestFileName=PDFmake.upsTestPdfMake(jsonObject);
        FixTestInsHistoryEntity fieUPSTEST=new FixTestInsHistoryEntity();
        String[] jsonStrArrayUT=Utils.cutJson(jsonObject.toString());
        String[] upsTESTAnother=Utils.parseAnother(jsonObject);
        Utils.makeSixKV(upsTESTAnother,fieUPSTEST);
        fieUPSTEST.setFilepath(upsTestFileName);
        fieUPSTEST.setJson1(jsonStrArrayUT[0]);
        fieUPSTEST.setJson2(jsonStrArrayUT[1]);
        TISHistoryUtil.getInstance().add(fieUPSTEST);
        //通知回执
        JSONObject recpiptUT=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
        recpiptUT.put("timestamp",upsTESTAnother[1]);recpiptUT.put("eng_name",upsTESTAnother[5]); recpiptUT.put("business",upsTESTAnother[3]);
        recpiptUT.put("reason",upsTESTAnother[2]);recpiptUT.put("filename",upsTestFileName);recpiptUT.put("cus_id",upsTESTAnother[0]);
        recpiptUT.put("title","提醒通知");recpiptUT.put("content","工程师"+upsTESTAnother[5]+"的工作"+upsTESTAnother[3]+"已经完成了，请您查收");
            recpiptUT.put("step",1);recpiptUT.put("blank1",jsonObject.getString("blank1"));
            recpiptUT.put("reply","notify_reply");
        //SocketIOClient.sendMessage(recpiptUT.toString());
            SocketIOClient.setSocketUrl( socketIoUrl);
            SocketIOClient.sendMessage(recpiptUT.toString());
//离线消息添加
            jsonObject.put("first_filename",upsTestFileName);
            FixMessageEntity upstestFme=FixMessageUtil.getInstance().makeFme(new String[]{
                   // upstestano.getString("timestamp"),
                    jsonObject.getString("timestamp"),
                    upstestano.getString("idc_id"),upstestano.getString("idc_name"), upstestano.getString("idc_location"),upstestano.getString("idc_type"),
                    upstestano.getString("cus_id"),upstestano.getString("eng_id"),upstestano.getString("business"),"0",upstestano.getString("eng_name"),
                    null,null,null
            },1,Utils.cutJson(jsonObject.toString()));
            FixMessageUtil.getInstance().add(upstestFme);

       //很难受 不知为啥 socketio 不管用了
        //socket.getOutputStream().write(recpiptUT.toString().getBytes("UTF-8"));
        //先空着  因为first长度超过20000 last也必然超过


      /*  if (lengthMAX>20000){
            FixMessageEntity fme= FixMessageUtil.getInstance().makeFme(new String[]{},0,new String[]{});
            FixMessageUtil.getInstance().add(fme);
            Ss.addToEigth(jsonObject.toString());
        }else{
            //照常的缓存
            FixMessageUtil.getInstance().makeFme(null,0,null);
        }*/
        }

        if (jsonObject.getInt("step")==2){
            FixMessageEntity upstestFme=new FixMessageEntity();
            upstestFme.setBlank1(jsonObject.getString("blank1"));
            upstestFme.setBlank2(jsonObject.getString("blank2"));

            FixMessageEntity queryUT= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
            JSONObject jsonobj=new JSONObject(queryUT.getJson1()+queryUT.getJson2());
            jsonobj.put("blank1",jsonObject.getString("blank1"));
            jsonobj.put("blank2",jsonObject.getString("blank2"));
            jsonobj.put("step",2);
            String upsTestFileNameLast=PDFmake.upsTestPdfMake(jsonobj);
            jsonobj.put("last_filename",upsTestFileNameLast);
            upstestFme.setBlank3(upsTestFileNameLast);
            upstestFme.setIsWatch("2");
            upstestFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(upstestFme);



            // 嗯 email  姓名  文件名
            //先工程师
            String engId=queryUT.getEngId();
            RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(engId);
            initEmailJson(rue1.getEmail(),rue1.getName(),upsTestFileNameLast);


            String userId=queryUT.getUserId();
            RegisterUserEntity rue2=TableRegUtil.getInstance().queryInID(userId);
            initEmailJson(rue2.getEmail(),rue2.getName(),upsTestFileNameLast);


        }
    }

    public void upsFixFeature(JSONObject jsonObject, Socket socket){
        //1 生成pdf 2 存储 json 及filename path  3 notify n2odejs   数据表 应该6个字段 id cus_id  eng_id  json1(0-length/2) json2(length/2 +1-length)（ 很有可能超了） text timestamp  filename 加密传输
        //1 生成pdf 及获取文件名
        //2jsondata 存储
        //JSONObject upsFixJson=new JSONObject();
        // jsonObject.getString();
        /**
         *  ano.put("cus_id",mapService.get("h_custom_id"));
         *                     ano.put("timestamp",System.currentTimeMillis()+"");
         *                     ano.put("reason",mapService.get("h_reason"));
         *                     ano.put("business", HandlerFinal.BUSINESS_STR[7]);
         *                     ano.put("eng_id",HandlerFinal.userId);
         *                     ano.put("eng_name",HandlerFinal.userName);  固定拿着六个的 another
         */

        // 回执含有 时间 工程师 业务 reasontext filename  的json    8
       // System.out.println("123--------------------");
        JSONObject upsfixano=ParseJson.getSubjson(jsonObject,"another");
        int step=upsfixano.getInt("step");
        if (step==1){
        String upsFixFileName=PDFmake.upsFixPdfMake(jsonObject);
        System.out.println(upsFixFileName);
        FixTestInsHistoryEntity fieUPSFIX=new FixTestInsHistoryEntity();
        //String upsFixReason=jsonObject.getString("fix_reason");
        String[] jsonStrArrayUF=Utils.cutJson(jsonObject.toString()); //额外的 放在other another 里面
        String[] upsFixAnother=Utils.parseAnother(jsonObject);
                                     /*   fieUPSFIX.setUserId(upsFixAnother[0]);
                                        fieUPSFIX.setDateHistory(new Timestamp(System.currentTimeMillis()));//违心的 不这么弄 虽然timestamp 对于心跳 分布式的验证很重要
                                        fieUPSFIX.setTextHistory(upsFixAnother[2]);
                                        fieUPSFIX.setBusiness(upsFixAnother[3]);
                                        fieUPSFIX.setEngId(upsFixAnother[4]);
                                        fieUPSFIX.setHuman(upsFixAnother[5]);*/
        Utils.makeSixKV(upsFixAnother,fieUPSFIX);
        fieUPSFIX.setFilepath(upsFixFileName);
        fieUPSFIX.setJson1(jsonStrArrayUF[0]);
        fieUPSFIX.setJson2(jsonStrArrayUF[1]);
        TISHistoryUtil.getInstance().add(fieUPSFIX);
        //通知回执
        JSONObject recpiptUF=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
        recpiptUF.put("timestamp",upsFixAnother[1]);recpiptUF.put("eng_name",upsFixAnother[5]); recpiptUF.put("business",upsFixAnother[3]);
        recpiptUF.put("reason",upsFixAnother[2]);recpiptUF.put("filename",upsFixFileName);recpiptUF.put("cus_id",upsFixAnother[0]);
        recpiptUF.put("title","提醒通知");recpiptUF.put("content","工程师"+upsFixAnother[5]+"的工作"+upsFixAnother[3]+"已经完成了，请您查收");
            recpiptUF.put("step",1);recpiptUF.put("blank1",jsonObject.getString("blank1"));
        recpiptUF.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
        //socket.getOutputStream().write(recpiptUF.toString().getBytes("UTF-8"));

        SocketIOClient.setSocketUrl( socketIoUrl);
        SocketIOClient.sendMessage(recpiptUF.toString());
        System.out.println("7--------------------");
        // SocketIOClient.sendMessage(recpiptUF.toString());
            jsonObject.put("first_filename",upsFixFileName);
            FixMessageEntity upsfixFme=FixMessageUtil.getInstance().makeFme(new String[]{
                   // upsfixano.getString("timestamp"),
                    jsonObject.getString("timestamp"),
                    upsfixano.getString("idc_id"),upsfixano.getString("idc_name"), upsfixano.getString("idc_location"),upsfixano.getString("idc_type"),
                    upsfixano.getString("cus_id"),upsfixano.getString("eng_id"),upsfixano.getString("business"),"0",upsfixano.getString("eng_name"),
                    null,null,null
            },1,Utils.cutJson(jsonObject.toString()));
            FixMessageUtil.getInstance().add(upsfixFme);

        System.out.println("8--------------------");


        }

        if (jsonObject.getInt("step")==2){
            System.out.println("-1");
            FixMessageEntity upsfixFme=new FixMessageEntity();//可以用同一个表 也就字段不同罢了
            upsfixFme.setBlank1(jsonObject.getString("blank1"));
            upsfixFme.setBlank2(jsonObject.getString("blank2"));
            System.out.println("0");
            FixMessageEntity queryUF= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
            JSONObject jsonobj=new JSONObject(queryUF.getJson1()+queryUF.getJson2());
            jsonobj.put("blank1",jsonObject.getString("blank1"));
            jsonobj.put("blank2",jsonObject.getString("blank2"));
            jsonobj.put("step",2);
            System.out.println("1");
          /*  String upsFixFileNameLast=PDFmake.upsFixPdfMake(jsonobj);
            jsonObject.put("last_filename",upsFixFileNameLast);
            upsfixFme.setBlank3(upsFixFileNameLast);
            upsfixFme.setIsWatch("2");
            upsfixFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(upsfixFme);*/
            String upsFixFileNameLast=PDFmake.upsFixPdfMake(jsonobj);
            System.out.println("2");
            jsonObject.put("last_filename",upsFixFileNameLast);
            upsfixFme.setBlank3(upsFixFileNameLast);
            upsfixFme.setIsWatch("2");
            upsfixFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(upsfixFme);



            // 嗯 email  姓名  文件名
            //先工程师
            String engId=queryUF.getEngId();
            RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(engId);
            initEmailJson(rue1.getEmail(),rue1.getName(),upsFixFileNameLast);


            String userId=queryUF.getUserId();
            RegisterUserEntity rue2=TableRegUtil.getInstance().queryInID(userId);
            initEmailJson(rue2.getEmail(),rue2.getName(),upsFixFileNameLast);


        }
        //以下消息缓存

        // 816 socket 是哪个sb 给我关掉的
        System.out.println(socket.getLocalSocketAddress()+"----"+socket.getRemoteSocketAddress());
    }

    public void airInsFeature(JSONObject jsonObject, Socket socket){
        JSONObject airinsano=ParseJson.getSubjson(jsonObject,"another");
        int step=airinsano.getInt("step");
        if (step==1){

        String airInsFileName=PDFmake.airInsMakePdf(jsonObject);
        FixTestInsHistoryEntity fieAIRINS=new FixTestInsHistoryEntity();
        String[] jsonStrArrayAI=Utils.cutJson(jsonObject.toString());
        String[] airINSAnother=Utils.parseAnother(jsonObject);
        Utils.makeSixKV(airINSAnother,fieAIRINS);
        fieAIRINS.setFilepath(airInsFileName);
        fieAIRINS.setJson1(jsonStrArrayAI[0]);
        fieAIRINS.setJson2(jsonStrArrayAI[1]);
        TISHistoryUtil.getInstance().add(fieAIRINS);
        //通知回执
        JSONObject recpiptAI=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
        recpiptAI.put("timestamp",airINSAnother[1]);recpiptAI.put("eng_name",airINSAnother[5]); recpiptAI.put("business",airINSAnother[3]);
        recpiptAI.put("reason",airINSAnother[2]);recpiptAI.put("filename",airInsFileName);recpiptAI.put("cus_id",airINSAnother[0]);
        recpiptAI.put("title","提醒通知");recpiptAI.put("content","工程师"+airINSAnother[5]+"的工作"+airINSAnother[3]+"已经完成了，请您查收");
        //SocketIOClient.sendMessage(recpiptAI.toString());
            recpiptAI.put("step",1);recpiptAI.put("blank1",jsonObject.getString("blank1"));
        recpiptAI.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
        //socket.getOutputStream().write(recpiptAI.toString().getBytes("UTF-8"));
            SocketIOClient.setSocketUrl( socketIoUrl);
            SocketIOClient.sendMessage(recpiptAI.toString());
            jsonObject.put("first_filename",airInsFileName);
            FixMessageEntity airinsFme=FixMessageUtil.getInstance().makeFme(new String[]{
                    //airinsano.getString("timestamp"),
                    jsonObject.getString("timestamp"),
                    airinsano.getString("idc_id"),airinsano.getString("idc_name"), airinsano.getString("idc_location"),airinsano.getString("idc_type"),
                    airinsano.getString("cus_id"),airinsano.getString("eng_id"),airinsano.getString("business"),"0",airinsano.getString("eng_name"),
                    null,null,null
            },1,Utils.cutJson(jsonObject.toString()));
            FixMessageUtil.getInstance().add(airinsFme);

        }

        if (jsonObject.getInt("step")==2){
            FixMessageEntity airinsFme=new FixMessageEntity();//可以用同一个表 也就字段不同罢了
            airinsFme.setBlank1(jsonObject.getString("blank1"));
            airinsFme.setBlank2(jsonObject.getString("blank2"));

            FixMessageEntity queryAI= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
            JSONObject jsonobj=new JSONObject(queryAI.getJson1()+queryAI.getJson2());
            jsonobj.put("blank1",jsonObject.getString("blank1"));
            jsonobj.put("blank2",jsonObject.getString("blank2"));
            jsonobj.put("step",2);
            String airInsFileNameLast=PDFmake.airInsMakePdf(jsonobj);
            jsonObject.put("last_filename",airInsFileNameLast);
            airinsFme.setBlank3(airInsFileNameLast);
            airinsFme.setIsWatch("2");
            airinsFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(airinsFme);


            // 嗯 email  姓名  文件名
            //先工程师
            String engId=queryAI.getEngId();
            RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(engId);
            initEmailJson(rue1.getEmail(),rue1.getName(),airInsFileNameLast);


            String userId=queryAI.getUserId();
            RegisterUserEntity rue2=TableRegUtil.getInstance().queryInID(userId);
            initEmailJson(rue2.getEmail(),rue2.getName(),airInsFileNameLast);



        }
    }

    public void installFeature(JSONObject jsonObject, Socket socket){
        JSONObject installano=ParseJson.getSubjson(jsonObject,"another");
        int step=installano.getInt("step");
        if (step==1){

        String installFileName=PDFmake.installMakePdf(jsonObject);
        FixTestInsHistoryEntity fieINSTALL=new FixTestInsHistoryEntity();
        String[] jsonStrArrayIN=Utils.cutJson(jsonObject.toString());
        String[] installAnother=Utils.parseAnother(jsonObject);
        Utils.makeSixKV(installAnother,fieINSTALL);
        fieINSTALL.setFilepath(installFileName);
        fieINSTALL.setJson1(jsonStrArrayIN[0]);
        fieINSTALL.setJson2(jsonStrArrayIN[1]);
        TISHistoryUtil.getInstance().add(fieINSTALL);
        //通知回执
        JSONObject recpiptIN=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
        recpiptIN.put("timestamp",installAnother[1]);recpiptIN.put("eng_name",installAnother[5]); recpiptIN.put("business",installAnother[3]);
        recpiptIN.put("reason",installAnother[2]);recpiptIN.put("filename",installAnother);recpiptIN.put("cus_id",installAnother[0]);
        recpiptIN.put("title","提醒通知");recpiptIN.put("content","工程师"+installAnother[5]+"的工作"+installAnother[3]+"已经完成了，请您查收");
            recpiptIN.put("step",1);recpiptIN.put("blank1",jsonObject.getString("blank1"));
        //SocketIOClient.sendMessage(recpiptIN.toString());
        recpiptIN.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
        //socket.getOutputStream().write(recpiptIN.toString().getBytes("UTF-8"));
            SocketIOClient.setSocketUrl( socketIoUrl);
            SocketIOClient.sendMessage(recpiptIN.toString());
            jsonObject.put("first_filename",installFileName);
            FixMessageEntity installFme=FixMessageUtil.getInstance().makeFme(new String[]{
                    //installano.getString("timestamp"),
                    jsonObject.getString("timestamp"),
                    installano.getString("idc_id"),installano.getString("idc_name"), installano.getString("idc_location"),installano.getString("idc_type"),
                    installano.getString("cus_id"),installano.getString("eng_id"),installano.getString("business"),"0",installano.getString("eng_name"),
                    null,null,null
            },1,Utils.cutJson(jsonObject.toString()));
            FixMessageUtil.getInstance().add(installFme);

        }

        if (jsonObject.getInt("step")==2){
            FixMessageEntity installFme=new FixMessageEntity();//可以用同一个表 也就字段不同罢了
            installFme.setBlank1(jsonObject.getString("blank1"));
            installFme.setBlank2(jsonObject.getString("blank2"));

            FixMessageEntity queryIN= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
            JSONObject jsonobj=new JSONObject(queryIN.getJson1()+queryIN.getJson2());
            jsonobj.put("blank1",jsonObject.getString("blank1"));
            jsonobj.put("blank2",jsonObject.getString("blank2"));
            jsonobj.put("step",2);
            String installNameLast=PDFmake.installMakePdf(jsonobj);
            jsonObject.put("last_filename",installNameLast);
            installFme.setBlank3(installNameLast);
            installFme.setIsWatch("2");
            installFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(installFme);



            // 嗯 email  姓名  文件名
            //先工程师
            String engId=queryIN.getEngId();
            RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(engId);
            initEmailJson(rue1.getEmail(),rue1.getName(),installNameLast);


            String userId=queryIN.getUserId();
            RegisterUserEntity rue2=TableRegUtil.getInstance().queryInID(userId);
            initEmailJson(rue2.getEmail(),rue2.getName(),installNameLast);



        }
    }

    public void serviceFeature(JSONObject jsonObject, Socket socket){
        JSONObject serviceano=ParseJson.getSubjson(jsonObject,"another");
        int step=serviceano.getInt("step");
        if (step==1){
            String serviceFileName=PDFmake.serviceMakePdf(jsonObject);
            FixTestInsHistoryEntity fieSERVICE=new FixTestInsHistoryEntity();
            String[] jsonStrArraySE=Utils.cutJson(jsonObject.toString());
            String[] serviceAnother=Utils.parseAnother(jsonObject);
            Utils.makeSixKV(serviceAnother,fieSERVICE);
            fieSERVICE.setFilepath(serviceFileName);
            fieSERVICE.setJson1(jsonStrArraySE[0]);
            fieSERVICE.setJson2(jsonStrArraySE[1]);
            TISHistoryUtil.getInstance().add(fieSERVICE);
            //通知回执
            JSONObject recpiptSE=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
            recpiptSE.put("timestamp",serviceAnother[1]);recpiptSE.put("eng_name",serviceAnother[5]); recpiptSE.put("business",serviceAnother[3]);
            recpiptSE.put("reason",serviceAnother[2]);recpiptSE.put("filename",serviceFileName);recpiptSE.put("cus_id",serviceAnother[0]);
            recpiptSE.put("title","提醒通知");recpiptSE.put("content","工程师"+serviceAnother[5]+"的工作"+serviceAnother[3]+"已经完成了，请您查收");
            recpiptSE.put("step",1); recpiptSE.put("blank1",jsonObject.getString("blank1"));
            //SocketIOClient.sendMessage(recpiptSE.toString());
            recpiptSE.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
            SocketIOClient.setSocketUrl( socketIoUrl);
            SocketIOClient.sendMessage(recpiptSE.toString());
            //socket.getOutputStream().write(recpiptSE.toString().getBytes("UTF-8"));
            jsonObject.put("first_filename",serviceFileName);
            FixMessageEntity serviceFme=FixMessageUtil.getInstance().makeFme(new String[]{
                   // serviceano.getString("timestamp"),
                    jsonObject.getString("timestamp"),
                    serviceano.getString("idc_id"),serviceano.getString("idc_name"), serviceano.getString("idc_location"),serviceano.getString("idc_type"),
                    serviceano.getString("cus_id"),serviceano.getString("eng_id"),serviceano.getString("business"),"0",serviceano.getString("eng_name"),
                    null,null,null
            },1,Utils.cutJson(jsonObject.toString()));
            FixMessageUtil.getInstance().add(serviceFme);
        }

        if (jsonObject.getInt("step")==2){
            FixMessageEntity serviceFme=new FixMessageEntity();//可以用同一个表 也就字段不同罢了
            serviceFme.setBlank1(jsonObject.getString("blank1"));
            serviceFme.setBlank2(jsonObject.getString("blank2"));

            FixMessageEntity querySE= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
            JSONObject jsonobj=new JSONObject(querySE.getJson1()+querySE.getJson2());
            jsonobj.put("blank1",jsonObject.getString("blank1"));
            jsonobj.put("blank2",jsonObject.getString("blank2"));
            jsonobj.put("step",2);
            String serviceFileNameLast=PDFmake.serviceMakePdf(jsonobj);
            jsonObject.put("last_filename",serviceFileNameLast);
            serviceFme.setBlank3(serviceFileNameLast);
            serviceFme.setIsWatch("2");
            serviceFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(serviceFme);


            // 嗯 email  姓名  文件名
            //先工程师
            String engId=querySE.getEngId();
            RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(engId);
            initEmailJson(rue1.getEmail(),rue1.getName(),serviceFileNameLast);


            String userId=querySE.getUserId();
            RegisterUserEntity rue2=TableRegUtil.getInstance().queryInID(userId);
            initEmailJson(rue2.getEmail(),rue2.getName(),serviceFileNameLast);


        }





    }

    public static void airInspectionFeature(JSONObject jsonObject, Socket socket){

        JSONObject airInspectionano=ParseJson.getSubjson(jsonObject,"another");
        int step=airInspectionano.getInt("step");
        if (step==1){
            String airInspectionFileName=PDFmake.airNewInsPdfMake(jsonObject);
            FixTestInsHistoryEntity fieAirNewInsPdfMake=new FixTestInsHistoryEntity();
            String[] jsonStrArrayairInspection=Utils.cutJson(jsonObject.toString());
            String[] airInspectionAnother=Utils.parseAnother(jsonObject);
            Utils.makeSixKV(airInspectionAnother,fieAirNewInsPdfMake);
            fieAirNewInsPdfMake.setFilepath(airInspectionFileName);
            fieAirNewInsPdfMake.setJson1(jsonStrArrayairInspection[0]);
            fieAirNewInsPdfMake.setJson2(jsonStrArrayairInspection[1]);
            TISHistoryUtil.getInstance().add(fieAirNewInsPdfMake);
            //通知回执
            JSONObject recpiptairInspectionAnother=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
            recpiptairInspectionAnother.put("timestamp",airInspectionAnother[1]);recpiptairInspectionAnother.put("eng_name",airInspectionAnother[5]); recpiptairInspectionAnother.put("business",airInspectionAnother[3]);
            recpiptairInspectionAnother.put("reason",airInspectionAnother[2]);recpiptairInspectionAnother.put("filename",airInspectionFileName);recpiptairInspectionAnother.put("cus_id",airInspectionAnother[0]);
            recpiptairInspectionAnother.put("title","提醒通知");recpiptairInspectionAnother.put("content","工程师"+airInspectionAnother[5]+"的工作"+airInspectionAnother[3]+"已经完成了，请您查收");
            recpiptairInspectionAnother.put("step",1); recpiptairInspectionAnother.put("blank1",jsonObject.getString("blank1"));
            //SocketIOClient.sendMessage(recpiptSE.toString());
            recpiptairInspectionAnother.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
            SocketIOClient.setSocketUrl( socketIoUrl);
            SocketIOClient.sendMessage(recpiptairInspectionAnother.toString());
            //socket.getOutputStream().write(recpiptSE.toString().getBytes("UTF-8"));
            jsonObject.put("first_filename",airInspectionFileName);
            FixMessageEntity airInspectionFme=FixMessageUtil.getInstance().makeFme(new String[]{
                    //airInspectionano.getString("timestamp"),
                    jsonObject.getString("timestamp"),
                    airInspectionano.getString("idc_id"),airInspectionano.getString("idc_name"), airInspectionano.getString("idc_location"),airInspectionano.getString("idc_type"),
                    airInspectionano.getString("cus_id"),airInspectionano.getString("eng_id"),airInspectionano.getString("business"),"0",airInspectionano.getString("eng_name"),
                    null,null,null
            },1,Utils.cutJson(jsonObject.toString()));
            FixMessageUtil.getInstance().add(airInspectionFme);



            //检查维保表
            CountDownUtil countDownUtil=new CountDownUtil();
            countDownUtil.queryLost(airInspectionano.getString("idc_id"));
            countDownUtil.updateSingleAir(airInspectionano.getString("idc_id"));
        }

        if (jsonObject.getInt("step")==2){
            FixMessageEntity airInspectionFme=new FixMessageEntity();//可以用同一个表 也就字段不同罢了
            airInspectionFme.setBlank1(jsonObject.getString("blank1"));
            airInspectionFme.setBlank2(jsonObject.getString("blank2"));

            FixMessageEntity queryairInspection= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
            JSONObject jsonobj=new JSONObject(queryairInspection.getJson1()+queryairInspection.getJson2());
            jsonobj.put("blank1",jsonObject.getString("blank1"));
            jsonobj.put("blank2",jsonObject.getString("blank2"));
            jsonobj.put("step",2);
            String airInspectionFileNameLast=PDFmake.airNewInsPdfMake(jsonobj);
            jsonObject.put("last_filename",airInspectionFileNameLast);
            airInspectionFme.setBlank3(airInspectionFileNameLast);
            airInspectionFme.setIsWatch("2");
            airInspectionFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(airInspectionFme);


            // 嗯 email  姓名  文件名
            //先工程师
            String engId=queryairInspection.getEngId();
            RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(engId);
            initEmailJson(rue1.getEmail(),rue1.getName(),airInspectionFileNameLast);


            String userId=queryairInspection.getUserId();
            RegisterUserEntity rue2=TableRegUtil.getInstance().queryInID(userId);
            initEmailJson(rue2.getEmail(),rue2.getName(),airInspectionFileNameLast);



        }



       /* String filename=PDFmake.airNewInsPdfMake(jsonObject);
        System.out.println(filename+"=================");*/
    }

    public static void airInstallFeature(JSONObject jsonObject, Socket socket){


        JSONObject airInstallano=ParseJson.getSubjson(jsonObject,"another");
        int step=airInstallano.getInt("step");
        if (step==1){
            String airInstallFileName=PDFmake.airInstallPdfMake(jsonObject);
            FixTestInsHistoryEntity fieairInstallPdfMake=new FixTestInsHistoryEntity();
            String[] jsonStrArrayairInstall=Utils.cutJson(jsonObject.toString());
            String[] airInstallAnother=Utils.parseAnother(jsonObject);
            Utils.makeSixKV(airInstallAnother,fieairInstallPdfMake);
            fieairInstallPdfMake.setFilepath(airInstallFileName);
            fieairInstallPdfMake.setJson1(jsonStrArrayairInstall[0]);
            fieairInstallPdfMake.setJson2(jsonStrArrayairInstall[1]);
            TISHistoryUtil.getInstance().add(fieairInstallPdfMake);
            //通知回执
            JSONObject recpiptAirInstallAnother=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
            recpiptAirInstallAnother.put("timestamp",airInstallAnother[1]);recpiptAirInstallAnother.put("eng_name",airInstallAnother[5]); recpiptAirInstallAnother.put("business",airInstallAnother[3]);
            recpiptAirInstallAnother.put("reason",airInstallAnother[2]);recpiptAirInstallAnother.put("filename",airInstallFileName);recpiptAirInstallAnother.put("cus_id",airInstallAnother[0]);
            recpiptAirInstallAnother.put("title","提醒通知");recpiptAirInstallAnother.put("content","工程师"+airInstallAnother[5]+"的工作"+airInstallAnother[3]+"已经完成了，请您查收");
            recpiptAirInstallAnother.put("step",1); recpiptAirInstallAnother.put("blank1",jsonObject.getString("blank1"));
            //SocketIOClient.sendMessage(recpiptSE.toString());
            recpiptAirInstallAnother.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
            SocketIOClient.setSocketUrl( socketIoUrl);
            SocketIOClient.sendMessage(recpiptAirInstallAnother.toString());
            //socket.getOutputStream().write(recpiptSE.toString().getBytes("UTF-8"));
            jsonObject.put("first_filename",airInstallFileName);
            FixMessageEntity airInstallFme=FixMessageUtil.getInstance().makeFme(new String[]{
                    //airInstallano.getString("timestamp"),
                    jsonObject.getString("timestamp"),
                    airInstallano.getString("idc_id"),airInstallano.getString("idc_name"), airInstallano.getString("idc_location"),airInstallano.getString("idc_type"),
                    airInstallano.getString("cus_id"),airInstallano.getString("eng_id"),airInstallano.getString("business"),"0",airInstallano.getString("eng_name"),
                    null,null,null
            },1,Utils.cutJson(jsonObject.toString()));
            FixMessageUtil.getInstance().add(airInstallFme);
        }

        if (jsonObject.getInt("step")==2){
            FixMessageEntity airInstallFme=new FixMessageEntity();//可以用同一个表 也就字段不同罢了
            airInstallFme.setBlank1(jsonObject.getString("blank1"));
            airInstallFme.setBlank2(jsonObject.getString("blank2"));

            FixMessageEntity queryAirInstall= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
            JSONObject jsonobj=new JSONObject(queryAirInstall.getJson1()+queryAirInstall.getJson2());
            jsonobj.put("blank1",jsonObject.getString("blank1"));
            jsonobj.put("blank2",jsonObject.getString("blank2"));
            jsonobj.put("step",2);
            String airInstallFileNameLast=PDFmake.airInstallPdfMake(jsonobj);
            jsonObject.put("last_filename",airInstallFileNameLast);
            airInstallFme.setBlank3(airInstallFileNameLast);
            airInstallFme.setIsWatch("2");
            airInstallFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(airInstallFme);


            // 嗯 email  姓名  文件名
            //先工程师
            String engId=queryAirInstall.getEngId();
            RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(engId);
            initEmailJson(rue1.getEmail(),rue1.getName(),airInstallFileNameLast);


            String userId=queryAirInstall.getUserId();
            RegisterUserEntity rue2=TableRegUtil.getInstance().queryInID(userId);
            initEmailJson(rue2.getEmail(),rue2.getName(),airInstallFileNameLast);


        }



        /*String filename=PDFmake.airInstallPdfMake(jsonObject);
        System.out.println(filename+"=================");*/
    }

    public static void airFixFeature(JSONObject jsonObject, Socket socket){


        JSONObject airFixano=ParseJson.getSubjson(jsonObject,"another");
        int step=airFixano.getInt("step");
        if (step==1){
            String airFixFileName=PDFmake.airFixPdfMake(jsonObject);
            FixTestInsHistoryEntity fieairFixPdfMake=new FixTestInsHistoryEntity();
            String[] jsonStrArrayairFix=Utils.cutJson(jsonObject.toString());
            String[] airFixAnother=Utils.parseAnother(jsonObject);
            Utils.makeSixKV(airFixAnother,fieairFixPdfMake);
            fieairFixPdfMake.setFilepath(airFixFileName);
            fieairFixPdfMake.setJson1(jsonStrArrayairFix[0]);
            fieairFixPdfMake.setJson2(jsonStrArrayairFix[1]);
            TISHistoryUtil.getInstance().add(fieairFixPdfMake);
            //通知回执
            JSONObject recpiptAirFixAnother=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
            recpiptAirFixAnother.put("timestamp",airFixAnother[1]);recpiptAirFixAnother.put("eng_name",airFixAnother[5]); recpiptAirFixAnother.put("business",airFixAnother[3]);
            recpiptAirFixAnother.put("reason",airFixAnother[2]);recpiptAirFixAnother.put("filename",airFixFileName);recpiptAirFixAnother.put("cus_id",airFixAnother[0]);
            recpiptAirFixAnother.put("title","提醒通知");recpiptAirFixAnother.put("content","工程师"+airFixAnother[5]+"的工作"+airFixAnother[3]+"已经完成了，请您查收");
            recpiptAirFixAnother.put("step",1); recpiptAirFixAnother.put("blank1",jsonObject.getString("blank1"));
            //SocketIOClient.sendMessage(recpiptSE.toString());
            recpiptAirFixAnother.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
            SocketIOClient.setSocketUrl( socketIoUrl);
            SocketIOClient.sendMessage(recpiptAirFixAnother.toString());
            //socket.getOutputStream().write(recpiptSE.toString().getBytes("UTF-8"));
            jsonObject.put("first_filename",airFixFileName);
            FixMessageEntity airFixFme=FixMessageUtil.getInstance().makeFme(new String[]{
                    //airFixano.getString("timestamp"),
                    jsonObject.getString("timestamp"),
                    airFixano.getString("idc_id"),airFixano.getString("idc_name"), airFixano.getString("idc_location"),airFixano.getString("idc_type"),
                    airFixano.getString("cus_id"),airFixano.getString("eng_id"),airFixano.getString("business"),"0",airFixano.getString("eng_name"),
                    null,null,null
            },1,Utils.cutJson(jsonObject.toString()));
            FixMessageUtil.getInstance().add(airFixFme);
        }

        if (jsonObject.getInt("step")==2){
            FixMessageEntity airFixFme=new FixMessageEntity();//可以用同一个表 也就字段不同罢了
            airFixFme.setBlank1(jsonObject.getString("blank1"));
            airFixFme.setBlank2(jsonObject.getString("blank2"));

            FixMessageEntity queryAirFix= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
            JSONObject jsonobj=new JSONObject(queryAirFix.getJson1()+queryAirFix.getJson2());
            jsonobj.put("blank1",jsonObject.getString("blank1"));
            jsonobj.put("blank2",jsonObject.getString("blank2"));
            jsonobj.put("step",2);
            String airFixFileNameLast=PDFmake.airFixPdfMake(jsonobj);
            jsonObject.put("last_filename",airFixFileNameLast);
            airFixFme.setBlank3(airFixFileNameLast);
            airFixFme.setIsWatch("2");
            airFixFme.setTimeRecord(jsonObject.getString("timestamp"));
            FixMessageUtil.getInstance().update(airFixFme);


            // 嗯 email  姓名  文件名
            //先工程师
            String engId=queryAirFix.getEngId();
            RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(engId);
            initEmailJson(rue1.getEmail(),rue1.getName(),airFixFileNameLast);


            String userId=queryAirFix.getUserId();
            RegisterUserEntity rue2=TableRegUtil.getInstance().queryInID(userId);
            initEmailJson(rue2.getEmail(),rue2.getName(),airFixFileNameLast);
        }



       /* String filename=PDFmake.airFixPdfMake(jsonObject);
        System.out.println(filename+"=================");*/
    }



    /**

     *
     * json格式  （post_user_id 发件人id）（post_user_pwd 发件人密码）（receive_user_id 收件人id）（title 邮件标题）（content 邮件内容）（filename 文件地址名）
     */

   // public static void sendMailEnv(JSONObject object){
    public static void initEmailJson(String emailOther,String name,String filename){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("post_user_id","admin@zjdtst.com");
        jsonObject.put("post_user_pwd","4Gt5Sc7Ik2");
        jsonObject.put("receive_user_id",emailOther);
        jsonObject.put("title","迪特数据维保团队回执");
        jsonObject.put("content","尊敬的"+name+""+"您的业务已经完成请查看邮箱附件");
        jsonObject.put("filename",filename);
        Utils.sendMailEnv(jsonObject);
    }


}
