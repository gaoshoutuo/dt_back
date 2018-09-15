package Socket;


import DAO.TestAI;
import DAO.TestInfo;
import Engineering.JsonUtil;
import Entity.*;
import Resource.*;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端 得到输入流 是对方发来的
 * 服务端  得到输出流是自己发给对方的
 * 后端java8语法冷静 冷静
 */
public class SsMix {
    public static boolean countdownSwitch=true;
    private static SsMix ss;
    private static String socketIoUrl="http://218.108.146.98:3222";

    public static SsMix getinstance(){
        if (ss==null)ss=new SsMix();
        return ss;
    }
    static int port = 3333;
   //static int port = 3330;
    public static ServerSocket distributeServer=null;
    public static ExecutorService threadPool;

    static {
        try {
             threadPool= Executors.newFixedThreadPool(20);
            distributeServer = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ;
    public static List<Socket>socketList=new ArrayList<>();
   // public static ExecutorService threadPool;


    public interface Callback{
        public void exec();
    }


    public static void sendMessage() throws IOException {// 这个类争取到最后不超过1000行
        threadPool= Executors.newFixedThreadPool(20); //线程池
        Socket socket = distributeServer.accept();//准备
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        InputStream is = null;
                        try {
                            is = socket.getInputStream();
                            byte[] bytes = new byte[1024];
                            int len;
                            StringBuffer sf = new StringBuffer();//不能静态 因为清空的话 会影响到其他线程的执行，虽然是线程安全的，所以只有方法内部生成最可靠
                            while ((len = (is.read(bytes))) != -1) {
                                sf.append(new String(bytes, 0, len, "UTF-8"));
                            }
                            String jsonData=sf.toString();
                            int lengthMAX=jsonData.length();
                            System.out.println("json的长度："+lengthMAX);
                            if(jsonData.length()>5){
                                JSONObject jsonObject=new JSONObject(jsonData);
                                System.out.printf(jsonData);
                                TestAI testA=new TestA();
                                TestInfo testInfo=new InfoUtil();
                                switch (jsonObject.getString("au")){
                                    case "add"://此处无奈 无中间变量
                                       /* System.out.printf(jsonObject.getString("age")+jsonObject.getString("name"));
                                        testA.add(new TesttableEntity(jsonObject.getString("age"),jsonObject.getString("name")));
                                        OutputStream outputStream = socket.getOutputStream();
                                        outputStream.write("hello get".getBytes("UTF-8"));*/
                                        InfoEntity infoEntity=new InfoEntity();
                                        infoEntity.setPwd(jsonObject.getString("pwd"));
                                        infoEntity.setName(jsonObject.getString("name"));
                                        infoEntity.setEmail(jsonObject.getString("email"));
                                        System.out.println(jsonObject+"...............");
                                            testInfo.add(infoEntity);
                                        break;
                                    case "select":
                                       List<TesttableEntity> list=testA.query();
                                       //System.out.println(list.toString());
                                        OutputStream outputStream = socket.getOutputStream();
                                        String name=list.get(list.size()-1).getName()+"123123123";
                                        outputStream.write(name.getBytes("UTF-8"));
                                        break;
                                    case "register":
                                        TableRegUtil tableRegUtil=new TableRegUtil();
                                        RegisterUserEntity registerUserEntity=new RegisterUserEntity();
                                   /*     jsonObject.put("au", HandlerFinal.AU_REGISTER);
                                        jsonObject.put("user",userText);
                                        jsonObject.put("pwd",password);
                                        jsonObject.put("identity",sData);
                                        jsonObject.put("name",name);*/
                                     //   List<RegisterUserEntity>registerUserEntities=tableRegUtil.query();
                                        String ide=jsonObject.getString("identity");
                                        byte b[]=ide.getBytes("UTF-8");
                                        registerUserEntity.setIdentity(new String(b,"UTF-8"));
                                        registerUserEntity.setName(jsonObject.getString("name"));
                                        registerUserEntity.setPwd(jsonObject.getString("pwd"));
                                        registerUserEntity.setUserId(jsonObject.getString("user"));
                                        registerUserEntity.setLocation(jsonObject.getString("location"));
                                        registerUserEntity.setCompany(jsonObject.getString("company"));
                                        registerUserEntity.setEmail(jsonObject.getString("email"));
                                        registerUserEntity.setIndustry(jsonObject.getString("hangye"));

                                        tableRegUtil.add(registerUserEntity);
                                        OutputStream outputStream2 = socket.getOutputStream();
                                        JSONObject registerJson=new JSONObject();
                                        registerJson.put("reply","register_reply");
                                        outputStream2.write(registerJson.toString().getBytes("UTF-8"));
                                        break;

                                    case "login"://d登录验证账号密码  并取到 地址 自己的userid  location  name  并且写入中登录记录表
                                        TableRegUtil login= new TableRegUtil();
                                        JSONObject loginReplyJson=login.query(jsonObject);
                                        OutputStream loginReplyOS = socket.getOutputStream();
                                        loginReplyOS.write(loginReplyJson.toString().getBytes("UTF-8"));
                                        LoginRecordUtil lrt=new LoginRecordUtil();
                                        LoginRecordEntity lre=new LoginRecordEntity();
                                        lre.setTs(new Timestamp(System.currentTimeMillis()));
                                        lre.setUserId(loginReplyJson.getString("userid"));
                                        lre.setUserName(loginReplyJson.getString("name"));
                                        lrt.add(lre);

                                        break;
                                /*    case "register2":
                                        TableRegUtil tableRegUtil2=new TableRegUtil();
                                        Test3Entity test3Entity=new Test3Entity();
                                        test3Entity.setData(jsonObject.getString("data"));
                                        tableRegUtil2.add(test3Entity);
                                        break;*/
                                    case "history_eng":
                                        TISHistoryUtil tisEng=new TISHistoryUtil();
                                        JSONObject engJson=(tisEng.queryEng(jsonObject));
                                        if(engJson!=null){
                                            socket.getOutputStream().write(engJson.toString().getBytes("UTF-8"));
                                        }else{
                                            System.out.println("123");
                                        }
                                        break;
                                    case "history_cus"://客户的历史查询接口  内存回收就是方便
                                        TISHistoryUtil tisCus=new TISHistoryUtil();
                                        JSONObject cusJson=(tisCus.queryCus(jsonObject));
                                        if(cusJson!=null){
                                            socket.getOutputStream().write(cusJson.toString().getBytes("UTF-8"));
                                        }else{
                                            System.out.println("456");
                                        }
                                        break;

                                    case "info"://从注册表 查询  id name location company
                                      /*  TableRegUtil info=new TableRegUtil();
                                        List<RegisterUserEntity> infoList=info.query();
                                        JSONObject infoJson=new JSONObject();
                                        infoJson.put("reply","info_reply");
                                        JSONArray infoArray=new JSONArray();
                                        for (int i=0;i<infoList.size();i++){
                                            RegisterUserEntity rue=infoList.get(i);
                                            String userId=rue.getUserId();
                                            String company=rue.getCompany();
                                            String infoname=rue.getName();
                                            String infoLocation=rue.getLocation();
                                            JSONObject infoA=new JSONObject();
                                            infoA.put("user_id",userId);
                                            infoA.put("company",company);
                                            infoA.put("info_name",infoname);
                                            infoA.put("info_location",infoLocation);
                                            infoArray.put(infoA);
                                        }
                                        infoJson.put("array",infoArray);
                                        OutputStream infoOutStream=socket.getOutputStream();
                                        infoOutStream.write(infoJson.toString().getBytes("UTF-8"));//这种全表扫描很花时间的  redis 了解一下*/

                                      //也不是这种解决方案
                                        //TableRegUtil info=new TableRegUtil();
                                       // info.queryMe(jsonObject,socket);

                                        InfoUpsAirUtil.getInstance().infoQuery(jsonObject,socket);
                                        break;
                                    case "dtsjwb":

                                        break;
                                    case "ups_fix"://ups 修理
                                        IDCFeature.getInstance().upsFixFeature(jsonObject,socket);
                                        break;

                                    case "ups_ins"://ups 检修
                                        IDCFeature.getInstance().upsInsFeature(jsonObject,socket);
                                        break;

                                    case "ups_test":// ups 测试
                                        IDCFeature.getInstance().upsTestFeature(jsonObject,socket,lengthMAX);
                                        break;

                                    case "air_ins":// 空调老检修
                                        IDCFeature.getInstance().airInsFeature(jsonObject,socket);
                                        //以下消息缓存
                                        break;

                                    case "air_inspection":// 空调新巡检air_inspection
                                        IDCFeature.getInstance().airInspectionFeature(jsonObject,socket);

                                        break;

                                    case "air_fix":// 空调维修
                                        IDCFeature.getInstance().airFixFeature(jsonObject,socket);


                                        break;

                                    case "air_install":// 空调安装 air_install  空调新巡检air_inspection  air_fix
                                        IDCFeature.getInstance().airInstallFeature(jsonObject,socket);

                                        break;

                                    case "install": // 安装battery_number
                                        IDCFeature.getInstance().installFeature(jsonObject,socket);
                                        //以下消息缓存
                                        break;
                                    case "service": // 服务
                                        IDCFeature.getInstance().serviceFeature(jsonObject,socket);
                                        //以下消息缓存

                                        break;


                                    case "double_msg":
                                        DoubleMsg doubleMsg=new DoubleMsg();
                                        doubleMsg.dmSendMsg(jsonObject,socket);

                                        break;

                                    case "json_1_2_msg":
                                        DoubleMsg json12Msg=new DoubleMsg();
                                        json12Msg.getJson12(jsonObject,socket);

                                        break;

                                    case "eng_history":

                                        break;

                                    case "tis_history":
                                      /*  FixTestInsHistoryEntity ftiHistory=new FixTestInsHistoryEntity();
                                        ftiHistory. 应该在每个业务中 都这么＋*/

                                        break;
                                    case "air_notify":

                                        break;
                                    case "fire_war":

                                        break;
                                    case "asset_query"://查询资产
                                        String asJsonReply=AssertUtil.getInstance().queryWithId(jsonObject);
                                        socket.getOutputStream().write(asJsonReply.getBytes("UTF-8"));
                                        break;

                                    case "check_identity"://检查身份
                                        RegisterUserEntity checkJson=new RegisterUserEntity();
                                        checkJson.setUserId(jsonObject.getString("user_id"));
                                        checkJson.setPwd(jsonObject.getString("password"));
                                        RegisterUserEntity checkReu=TableRegUtil.getInstance().checkQuery(checkJson);
                                        JSONObject checkJ=new JSONObject();
                                        checkJ.put("reply","check_u_i");
                                        if (checkReu==null){
                                            checkJ.put("ischecked","no");
                                            socket.getOutputStream().write(checkJ.toString().getBytes("UTF-8"));

                                        }else{
                                            checkJ.put("ischecked","yes");
                                            checkJ.put("iden",checkReu.getIdentity());
                                            checkJ.put("user_id",checkReu.getUserId());
                                            checkJ.put("pwd",checkReu.getPwd());
                                            checkJ.put("name", checkReu.getName());
                                            checkJ.put("company",checkReu.getCompany());
                                            checkJ.put("location",checkReu.getLocation());

                                            socket.getOutputStream().write(checkJ.toString().getBytes("UTF-8"));
                                            System.out.println("!!!!!!!!!!-----------------------------------------");
                                        }
                                        break;

                                    case "asset_json"://登记资产  其实要改一下  添加之前先查询
                                        String []assertJson=Utils.cutJson(jsonObject.toString());//分为4个
                                      /*  System.out.println(assertJson[0]);
                                        System.out.println(assertJson[1]);*/

                                        String []preJson=Utils.cutJson(assertJson[0]);
                                        String []afterJson=Utils.cutJson(assertJson[1]);


                                        // 四个json  以及 其他的解析放到  数据库里去  完全是野路子
                                        System.out.println("------------------------");
                                        AssertEntity ae=new AssertEntity();
                                        ae.setDateHistory(new Timestamp(System.currentTimeMillis()));
                                        ae.setJson1(preJson[0]);ae.setJson2(preJson[1]);ae.setJson3(afterJson[0]);ae.setJson4(afterJson[1]);
                                        ae.setCompany(jsonObject.getString("company"));ae.setCusId(jsonObject.getString("cus_id"));
                                        ae.setCusName(jsonObject.getString("username"));ae.setLocation(jsonObject.getString("location"));
                                        AssertUtil.getInstance().add(ae);
                                        break;
                                    case "create_idc":
                                        IdcInfoEntity iie= IdcInfoUtil.getInstance().reverse(jsonObject);
                                        IdcInfoUtil.getInstance().add(iie);
                                        //顺便给idcAsset 添加一条记录
//                                        IdcAssetEntity iaentity=new IdcAssetEntity();
//                                        iaentity.setIdcId();
                                        IdcAssetEntity iaentity=IdcAssetUtil.getInstance().makeInitInfo(jsonObject);
                                        IdcAssetUtil.getInstance().add(iaentity);

                                        //socket.getOutputStream().write( JsonUtil.makeSingleFieldJson("reply","create_idc_reply").toString().getBytes("UTF-8"));

                                        System.out.println("111111");
                                        JSONObject idcQuertJson22=IdcInfoUtil.getInstance().queryIdc(jsonObject);
                                        idcQuertJson22.put("reply","idc_query_reply");
                                        System.out.println("111111");
                                        socket.getOutputStream().write(idcQuertJson22.toString().getBytes("UTF-8"));
                                        System.out.println("111111");
                                        break;
                                    case "idc_query":
                                       // if(jsonObject.getString("ide").equals("维保人员")){
                                            JSONObject idcQuertJson=IdcInfoUtil.getInstance().queryIdc(jsonObject);
                                            idcQuertJson.put("reply","idc_query_reply");
                                            socket.getOutputStream().write(idcQuertJson.toString().getBytes("UTF-8"));
                                        //}


                                        break;

                                    case "battery_number"://7月
                                       // AssertUtil.getInstance().query();
                                       // String assetJson=AssertUtil.getInstance().queryWithId(jsonObject);
                                        JSONObject batteryJson=new JSONObject();
                                        if (jsonObject.get("auau").equals("query")){
                                            int numberBattery=BatteryUtil.getInstance().queryNumber(jsonObject);
                                            batteryJson.put("number",numberBattery);
                                            batteryJson.put("reply","battery_reply");
                                            System.out.println(batteryJson.toString());
                                            socket.getOutputStream().write(batteryJson.toString().getBytes("UTF-8"));
                                        }else if(jsonObject.get("auau").equals("add")) {
                                            BatteryNumberEntity be = new BatteryNumberEntity();
                                            //int numberBattery=BatteryUtil.getInstance().queryNumber(jsonObject);

                                            be.setBattNumber(Integer.parseInt(jsonObject.getString("device_num")));
                                            be.setCusId(jsonObject.getString("cus_id"));
                                            be.setDateHistory(new Timestamp(System.currentTimeMillis()));
                                            be.setIdcId(jsonObject.getString("idc_id"));
                                            //这里不能直接add 要先query 有没有这个cus_id  有事update 无是add
                                            int numberBattery=BatteryUtil.getInstance().queryNumber(jsonObject);
                                            if (numberBattery==0){
                                                BatteryUtil.getInstance().add(be);
                                            }else if(numberBattery<Integer.parseInt(jsonObject.getString("device_num"))){//永远取最大的做
                                                BatteryUtil.getInstance().update(be);
                                            }


                                        }else if(jsonObject.get("auau").equals("update")){

                                    }
                                        break;
//{"cus_id":"12345678901","au":"help_asset","eng_id":"18768349255","timestamp":"2018-07-09 10:59:18.493","business_type":"base"}
                                    case "help_asset":
                                        if (jsonObject.getString("instruction").equals("add_base")||jsonObject.getString("instruction").equals("add_it")){
                                            AgreeTableEntity agreeTableEntity=new AgreeTableEntity();
                                            String helpTs=jsonObject.getString("timestamp");
                                            Timestamp helpTMs=Timestamp.valueOf(helpTs);
                                            String helpEngId=jsonObject.getString("eng_id");
                                            String helpCusId=jsonObject.getString("cus_id");
                                            String helpWorkType=jsonObject.getString("business_type");
                                            agreeTableEntity.setDateHistory(helpTMs);agreeTableEntity.setCusId(helpCusId);agreeTableEntity.setEngId(helpEngId);
                                            agreeTableEntity.setWorkType(helpWorkType);
                                            AgreeUtil.getInstance().add(agreeTableEntity);

                                            if (helpWorkType.equals("base")){
                                                JSONObject helpReply=new JSONObject();
                                                helpReply.put("reply","help_reply");
                                                helpReply.put("cus_id",helpCusId);
                                                helpReply.put("instruction","added_base");
                                                //查名字  尊敬的 客户名 我司 工程师名 要帮您录入资产
                                                RegisterUserEntity rueHelpCus=TableRegUtil.getInstance().queryInID(helpCusId);//怎么调用的去这么慢
                                                RegisterUserEntity rueHelpEng=TableRegUtil.getInstance().queryInID(helpEngId);
                                                helpReply.put("title","尊敬的 "+rueHelpCus.getName()+" 我司工程师"+rueHelpEng.getName()+" 正在帮您录入资产，申请您的许可，是否赋予十小时填写资料的权力");
                                                helpReply.put("content","基础资产录入许可");
                                                socket.getOutputStream().write(helpReply.toString().getBytes("UTF-8"));
                                            }else if(helpWorkType.equals("it")){
                                                JSONObject helpReply=new JSONObject();
                                                helpReply.put("reply","help_reply");
                                                helpReply.put("cus_id",helpCusId);
                                                helpReply.put("instruction","added_it");
                                                RegisterUserEntity rueHelpCus=TableRegUtil.getInstance().queryInID(helpCusId);//怎么调用的去这么慢
                                                RegisterUserEntity rueHelpEng=TableRegUtil.getInstance().queryInID(helpEngId);
                                                helpReply.put("title","尊敬的 "+rueHelpCus.getName()+" 我司工程师"+rueHelpEng.getName()+" 正在帮您录入资产，申请您的许可，是否赋予十小时填写资料的权力");
                                                helpReply.put("content","IT资产录入许可");
                                                socket.getOutputStream().write(helpReply.toString().getBytes("UTF-8"));
                                            }
                                            //socket.getOutputStream().write();

                                           // agreeTableEntity.setDateHistory(new Timestamp(jsonObject.getString("timestamp")));
                                        }else if(jsonObject.getString("instruction").equals("update_it")||jsonObject.getString("instruction").equals("update_base")){//接到客户的同意
                                            //jsonObject.put("agree","同意");
                                            JSONObject helpReply=new JSONObject();
                                            helpReply.put("instruction","agree_it");
                                            helpReply.put("reply","help_reply");
                                            helpReply.put("title","申请回执");
                                            if (jsonObject.getString("instruction").equals("update_base")){
                                                helpReply.put("instruction","agree_base");
                                            }else if(jsonObject.getString("instruction").equals("update_it")){
                                                helpReply.put("instruction","agree_it");
                                            }

                                            if (jsonObject.getString("im_agree").equals("我同意")){
                                                helpReply.put("content","客户确认您的申请");
                                            }else if(jsonObject.getString("im_agree").equals("我不同意")){
                                                helpReply.put("content","客户拒绝您的申请");
                                            }
                                            AgreeUtil.getInstance().update(jsonObject);
                                            socket.getOutputStream().write(helpReply.toString().getBytes("UTF-8"));
                                        }else if(jsonObject.getString("instruction").equals("one_hour")){
                                            JSONObject jsonHelp=AgreeUtil.getInstance().query(jsonObject);
                                                    socket.getOutputStream().write(jsonHelp.toString().getBytes("UTF-8"));
                                        }
                                            break;
                                        case "sub_idc"://update 资产
                                             updateIdcInfo(jsonObject.getString("update"),jsonObject.getString("idcid"),jsonObject.getString("asset"));

                                        //以及添加到ups_air表里面去
                                            UpsAirUtil.getInstance().lastMe(jsonObject);
                                           // UpsAirUtil.getInstance().add();
                                        break;

                                    case "sub_query"://写9000行恶心的类 没办法 看起来功能还真是一回事  编译原理 真是太牛逼了
                                        String subQueryStr=IdcAssetUtil.getInstance().queryIDC(jsonObject.getString("query"),jsonObject.getString("idcid"));
                                        JSONObject subQueryjson=new JSONObject(subQueryStr);
                                        subQueryjson.put("type",jsonObject.getString("query"));
                                        subQueryjson.put("reply","query_sub_reply");
                                        System.out.println("++++++"+subQueryjson.toString());
                                        socketWrite(socket,subQueryjson.toString());
                                        break;

                                    case "out_eight":
                                        break;

                                    case "max_length":
                                        MaxLengthTestEntity mlte=new MaxLengthTestEntity();
                                        mlte.setJson1(jsonObject.getString("json"));

                                        MaxLengthUtil.getInstance().add(mlte);
                                        break;


                                    case "count_down_mission"://需要android 手机去触发一次它  这个任务就交给我了  给自己出一个simple版本
                                        Utils.countdown();

                                        break;

                                    case "count_down":
                                        CountDownUtil cdu=new CountDownUtil();
                                        cdu.query22(socket);
                                        break;

                                    case "234":

                                        break;

                                    case "345":

                                        break;

                                    case "456":

                                        break;
                                    default:break;
                                }
                            }
                                //这种方法一次就断了 不好   notify 也加入这里不就好了哈
                               // is.close();
                             //   outputStream.close();
                                socket.close();//不能close 并且socket 唯一试试看  难道前一个socket 不close 后面就没办法接上
                              //  serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                threadPool.submit(runnable);
            }

    public static String newSendMessage(JSONObject jsonObject) throws IOException {// 这个类争取到最后不超过1000行
        String jsonStr=jsonObject.toString();
        int lengthMAX=jsonStr.length();
        TestAI testA=new TestA();
        TestInfo testInfo=new InfoUtil();
        String lastStr=null;
        switch (jsonObject.getString("au")){
                            case "add"://此处无奈 无中间变量
                                       /* System.out.printf(jsonObject.getString("age")+jsonObject.getString("name"));
                                        testA.add(new TesttableEntity(jsonObject.getString("age"),jsonObject.getString("name")));
                                        OutputStream outputStream = socket.getOutputStream();
                                        outputStream.write("hello get".getBytes("UTF-8"));*/
                                InfoEntity infoEntity=new InfoEntity();
                                infoEntity.setPwd(jsonObject.getString("pwd"));
                                infoEntity.setName(jsonObject.getString("name"));
                                infoEntity.setEmail(jsonObject.getString("email"));
                                System.out.println(jsonObject+"...............");
                                testInfo.add(infoEntity);
                                break;
                            case "select":
                                List<TesttableEntity> list=testA.query();
                                //System.out.println(list.toString());
                                //OutputStream outputStream = socket.getOutputStream();
                                String name=list.get(list.size()-1).getName()+"123123123";
                                //outputStream.write(name.getBytes("UTF-8"));
                                lastStr=name;
                                break;
                            case "register":
                                TableRegUtil tableRegUtil=new TableRegUtil();
                                RegisterUserEntity registerUserEntity=new RegisterUserEntity();
                                   /*     jsonObject.put("au", HandlerFinal.AU_REGISTER);
                                        jsonObject.put("user",userText);
                                        jsonObject.put("pwd",password);
                                        jsonObject.put("identity",sData);
                                        jsonObject.put("name",name);*/
                                //   List<RegisterUserEntity>registerUserEntities=tableRegUtil.query();
                                String ide=jsonObject.getString("identity");
                                byte b[]=ide.getBytes("UTF-8");
                                registerUserEntity.setIdentity(new String(b,"UTF-8"));
                                registerUserEntity.setName(jsonObject.getString("name"));
                                registerUserEntity.setPwd(jsonObject.getString("pwd"));
                                registerUserEntity.setUserId(jsonObject.getString("user"));
                                registerUserEntity.setLocation(jsonObject.getString("location"));
                                registerUserEntity.setCompany(jsonObject.getString("company"));
                                tableRegUtil.add(registerUserEntity);
                                //OutputStream outputStream2 = socket.getOutputStream();
                                JSONObject registerJson=new JSONObject();
                                registerJson.put("reply","register_reply");
                                //outputStream2.write(registerJson.toString().getBytes("UTF-8"));
                                lastStr=registerJson.toString();
                                break;

                            case "login"://d登录验证账号密码  并取到 地址 自己的userid  location  name  并且写入中登录记录表
                                TableRegUtil login= new TableRegUtil();
                                JSONObject loginReplyJson=login.query(jsonObject);
                                //OutputStream loginReplyOS = socket.getOutputStream();
                                //loginReplyOS.write(loginReplyJson.toString().getBytes("UTF-8"));
                                lastStr=loginReplyJson.toString();
                                LoginRecordUtil lrt=new LoginRecordUtil();
                                LoginRecordEntity lre=new LoginRecordEntity();
                                lre.setTs(new Timestamp(System.currentTimeMillis()));
                                lre.setUserId(loginReplyJson.getString("userid"));
                                lre.setUserName(loginReplyJson.getString("name"));
                                lrt.add(lre);

                                break;
                                /*    case "register2":
                                        TableRegUtil tableRegUtil2=new TableRegUtil();
                                        Test3Entity test3Entity=new Test3Entity();
                                        test3Entity.setData(jsonObject.getString("data"));
                                        tableRegUtil2.add(test3Entity);
                                        break;*/
                            case "history_eng"://客户的历史查询接口  内存回收就是方便
                                TISHistoryUtil tisEng=new TISHistoryUtil();
                                JSONObject engJson=(tisEng.queryEng(jsonObject));
                                if(engJson!=null){
                                   // socket.getOutputStream().write(engJson.toString().getBytes("UTF-8"));
                                    lastStr=engJson.toString();
                                }else{
                                    System.out.println("123");
                                }
                                break;
                            case "history_cus":
                                TISHistoryUtil tisCus=new TISHistoryUtil();
                                JSONObject cusJson=(tisCus.queryCus(jsonObject));
                                if(cusJson!=null){
                                   // socket.getOutputStream().write(cusJson.toString().getBytes("UTF-8"));
                                    lastStr=cusJson.toString();
                                }else{
                                    System.out.println("456");
                                }
                                break;

                            case "info"://从注册表 查询  id name location company
                                TableRegUtil info=new TableRegUtil();
                                List<RegisterUserEntity> infoList=info.query();
                                JSONObject infoJson=new JSONObject();
                                infoJson.put("reply","info_reply");
                                JSONArray infoArray=new JSONArray();
                                for (int i=0;i<infoList.size();i++){
                                    RegisterUserEntity rue=infoList.get(i);
                                    String userId=rue.getUserId();
                                    String company=rue.getCompany();
                                    String infoname=rue.getName();
                                    String infoLocation=rue.getLocation();
                                    JSONObject infoA=new JSONObject();
                                    infoA.put("user_id",userId);
                                    infoA.put("company",company);
                                    infoA.put("info_name",infoname);
                                    infoA.put("info_location",infoLocation);
                                    infoArray.put(infoA);
                                }
                                infoJson.put("array",infoArray);
                               // OutputStream infoOutStream=socket.getOutputStream();
                                //infoOutStream.write(infoJson.toString().getBytes("UTF-8"));//这种全表扫描很花时间的  redis 了解一下
                                lastStr=infoJson.toString();
                                break;
                            case "dtsjwb":

                                break;
                            case "ups_fix"://ups 修理   1 生成pdf 2 存储 json 及filename path  3 notify n2odejs   数据表 应该6个字段 id cus_id  eng_id  json1(0-length/2) json2(length/2 +1-length)（ 很有可能超了） text timestamp  filename 加密传输
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
                                System.out.println("123--------------------");
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
                                recpiptUF.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
                               // socket.getOutputStream().write(recpiptUF.toString().getBytes("UTF-8"));
                                lastStr=recpiptUF.toString();
                                System.out.println("7--------------------");
                                // SocketIOClient.sendMessage(recpiptUF.toString());
                                System.out.println("8--------------------");

                                //以下消息缓存

                                // 816 socket 是哪个sb 给我关掉的

                                break;

                            case "ups_ins"://ups 检修
                                String upsInsFileName=PDFmake.upsInsPdfMake(jsonObject);
                                FixTestInsHistoryEntity fieUPSINS=new FixTestInsHistoryEntity();
                                String[] jsonStrArrayUI=Utils.cutJson(jsonObject.toString());
                                String[] upsINSAnother=Utils.parseAnother(jsonObject);
                                Utils.makeSixKV(upsINSAnother,fieUPSINS);
                                fieUPSINS.setFilepath(upsInsFileName);
                                fieUPSINS.setJson1(jsonStrArrayUI[0]);
                                fieUPSINS.setJson2(jsonStrArrayUI[1]);
                                TISHistoryUtil.getInstance().add(fieUPSINS);
                                //通知回执
                                JSONObject recpiptUI=new JSONObject();//json 也需要一个生成器  我自己制造数据结构
                                recpiptUI.put("timestamp",upsINSAnother[1]);recpiptUI.put("eng_name",upsINSAnother[5]); recpiptUI.put("business",upsINSAnother[3]);
                                recpiptUI.put("reason",upsINSAnother[2]);recpiptUI.put("filename",upsInsFileName);recpiptUI.put("cus_id",upsINSAnother[0]);
                                recpiptUI.put("title","提醒通知");recpiptUI.put("content","工程师"+upsINSAnother[5]+"的工作"+upsINSAnother[3]+"已经完成了，请您查收");
                                // SocketIOClient.sendMessage(recpiptUI.toString());
                                recpiptUI.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
                                //socket.getOutputStream().write(recpiptUI.toString().getBytes("UTF-8"));
                                lastStr=recpiptUI.toString();
                              /*  for (Socket sock:socketList){
                                    sock.getOutputStream().write(recpiptUI.toString().getBytes("UTF-8"));
                                }*/
                                //System.out.println(socket.getLocalSocketAddress()+"----"+socket.getRemoteSocketAddress());
                                lastStr=recpiptUI.toString();
                                //以下消息缓存

                                break;

                            case "ups_test":// ups 测试
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
                                //SocketIOClient.sendMessage(recpiptUT.toString());
                                recpiptUT.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
                                //socket.getOutputStream().write(recpiptUT.toString().getBytes("UTF-8"));
                                lastStr=recpiptUT.toString();

                                //以下消息缓存
                                if (lengthMAX>20000){

                                    FixMessageEntity fme= FixMessageUtil.getInstance().makeFme(new String[]{},0,new String[]{});
                                    FixMessageUtil.getInstance().add(fme);
                                    addToEigth(jsonObject.toString());
                                }else{
                                    //照常的缓存
                                    FixMessageUtil.getInstance().makeFme(null,0,null);
                                }

                                break;

                            case "air_ins":// 空调检修
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
                                recpiptAI.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
                               // socket.getOutputStream().write(recpiptAI.toString().getBytes("UTF-8"));
                                lastStr=recpiptAI.toString();


                                //以下消息缓存

                                break;

                            case "install": // 安装battery_number
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
                                //SocketIOClient.sendMessage(recpiptIN.toString());
                                recpiptIN.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
                                //socket.getOutputStream().write(recpiptIN.toString().getBytes("UTF-8"));
                                lastStr=recpiptIN.toString();
                                //以下消息缓存


                                break;
                            case "service": // 服务
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
                                //SocketIOClient.sendMessage(recpiptSE.toString());
                                recpiptSE.put("reply","notify_reply");//很难受 不知为啥 socketio 不管用了
                                //socket.getOutputStream().write(recpiptSE.toString().getBytes("UTF-8"));
                                lastStr=recpiptSE.toString();


                                //以下消息缓存


                                break;
                            case "tis_history":
                                      /*  FixTestInsHistoryEntity ftiHistory=new FixTestInsHistoryEntity();
                                        ftiHistory. 应该在每个业务中 都这么＋*/

                                break;
                            case "air_notify":

                                break;
                            case "fire_war":

                                break;
                            case "asset_query"://查询资产
                                String asJsonReply=AssertUtil.getInstance().queryWithId(jsonObject);
                                //socket.getOutputStream().write(asJsonReply.getBytes("UTF-8"));
                                lastStr=asJsonReply;
                                break;

                            case "check_identity"://检查身份
                                RegisterUserEntity checkJson=new RegisterUserEntity();
                                checkJson.setUserId(jsonObject.getString("user_id"));
                                checkJson.setPwd(jsonObject.getString("password"));
                                RegisterUserEntity checkReu=TableRegUtil.getInstance().checkQuery(checkJson);
                                JSONObject checkJ=new JSONObject();
                                checkJ.put("reply","check_u_i");
                                if (checkReu==null){
                                    checkJ.put("ischecked","no");
                                   // socket.getOutputStream().write(checkJ.toString().getBytes("UTF-8"));
                                    lastStr=checkJ.toString();

                                }else{
                                    checkJ.put("ischecked","yes");
                                    checkJ.put("iden",checkReu.getIdentity());
                                    checkJ.put("user_id",checkReu.getUserId());
                                    checkJ.put("pwd",checkReu.getPwd());
                                    checkJ.put("name", checkReu.getName());
                                    checkJ.put("company",checkReu.getCompany());
                                    checkJ.put("location",checkReu.getLocation());

                                    //socket.getOutputStream().write(checkJ.toString().getBytes("UTF-8"));
                                    lastStr=checkJ.toString();
                                    System.out.println("!!!!!!!!!!-----------------------------------------");
                                }
                                break;

                            case "asset_json"://登记资产  其实要改一下  添加之前先查询
                                String []assertJson=Utils.cutJson(jsonObject.toString());//分为4个
                                      /*  System.out.println(assertJson[0]);
                                        System.out.println(assertJson[1]);*/

                                String []preJson=Utils.cutJson(assertJson[0]);
                                String []afterJson=Utils.cutJson(assertJson[1]);


                                // 四个json  以及 其他的解析放到  数据库里去  完全是野路子
                                System.out.println("------------------------");
                                AssertEntity ae=new AssertEntity();
                                ae.setDateHistory(new Timestamp(System.currentTimeMillis()));
                                ae.setJson1(preJson[0]);ae.setJson2(preJson[1]);ae.setJson3(afterJson[0]);ae.setJson4(afterJson[1]);
                                ae.setCompany(jsonObject.getString("company"));ae.setCusId(jsonObject.getString("cus_id"));
                                ae.setCusName(jsonObject.getString("username"));ae.setLocation(jsonObject.getString("location"));
                                AssertUtil.getInstance().add(ae);
                                break;
                            case "create_idc":
                                IdcInfoEntity iie= IdcInfoUtil.getInstance().reverse(jsonObject);
                                IdcInfoUtil.getInstance().add(iie);
                                //顺便给idcAsset 添加一条记录
//                                        IdcAssetEntity iaentity=new IdcAssetEntity();
//                                        iaentity.setIdcId();
                                IdcAssetEntity iaentity=IdcAssetUtil.getInstance().makeInitInfo(jsonObject);
                                IdcAssetUtil.getInstance().add(iaentity);

                                //socket.getOutputStream().write( JsonUtil.makeSingleFieldJson("reply","create_idc_reply").toString().getBytes("UTF-8"));
                                lastStr=JsonUtil.makeSingleFieldJson("reply","create_idc_reply").toString();
                                break;
                            case "idc_query":
                                JSONObject idcQuertJson=IdcInfoUtil.getInstance().queryIdc(jsonObject);
                                idcQuertJson.put("reply","idc_query_reply");
                                //socket.getOutputStream().write(idcQuertJson.toString().getBytes("UTF-8"));
                                lastStr=idcQuertJson.toString();
                                break;

                            case "battery_number"://7月
                                // AssertUtil.getInstance().query();
                                // String assetJson=AssertUtil.getInstance().queryWithId(jsonObject);
                                JSONObject batteryJson=new JSONObject();
                                if (jsonObject.get("auau").equals("query")){
                                    int numberBattery=BatteryUtil.getInstance().queryNumber(jsonObject);
                                    batteryJson.put("number",numberBattery);
                                    batteryJson.put("reply","battery_reply");
                                    System.out.println(batteryJson.toString());
                                    //socket.getOutputStream().write(batteryJson.toString().getBytes("UTF-8"));
                                    lastStr=batteryJson.toString();
                                }else if(jsonObject.get("auau").equals("add")) {
                                    BatteryNumberEntity be = new BatteryNumberEntity();
                                    be.setBattNumber(Integer.parseInt(jsonObject.getString("device_num")));
                                    be.setCusId(jsonObject.getString("cus_id"));
                                    be.setDateHistory(new Timestamp(System.currentTimeMillis()));
                                    be.setIdcId(jsonObject.getString("idc_id"));
                                    //这里不能直接add 要先query 有没有这个cus_id  有事update 无是add
                                    int numberBattery=BatteryUtil.getInstance().queryNumber(jsonObject);
                                    if (numberBattery==0){
                                        BatteryUtil.getInstance().add(be);
                                    }else{
                                        BatteryUtil.getInstance().update(be);
                                    }


                                }else if(jsonObject.get("auau").equals("update")){

                                }
                                break;
//{"cus_id":"12345678901","au":"help_asset","eng_id":"18768349255","timestamp":"2018-07-09 10:59:18.493","business_type":"base"}
                            case "help_asset":
                                if (jsonObject.getString("instruction").equals("add_base")||jsonObject.getString("instruction").equals("add_it")){
                                    AgreeTableEntity agreeTableEntity=new AgreeTableEntity();
                                    String helpTs=jsonObject.getString("timestamp");
                                    Timestamp helpTMs=Timestamp.valueOf(helpTs);
                                    String helpEngId=jsonObject.getString("eng_id");
                                    String helpCusId=jsonObject.getString("cus_id");
                                    String helpWorkType=jsonObject.getString("business_type");
                                    agreeTableEntity.setDateHistory(helpTMs);agreeTableEntity.setCusId(helpCusId);agreeTableEntity.setEngId(helpEngId);
                                    agreeTableEntity.setWorkType(helpWorkType);
                                    AgreeUtil.getInstance().add(agreeTableEntity);

                                    if (helpWorkType.equals("base")){
                                        JSONObject helpReply=new JSONObject();
                                        helpReply.put("reply","help_reply");
                                        helpReply.put("cus_id",helpCusId);
                                        helpReply.put("instruction","added_base");
                                        //查名字  尊敬的 客户名 我司 工程师名 要帮您录入资产
                                        RegisterUserEntity rueHelpCus=TableRegUtil.getInstance().queryInID(helpCusId);//怎么调用的去这么慢
                                        RegisterUserEntity rueHelpEng=TableRegUtil.getInstance().queryInID(helpEngId);
                                        helpReply.put("title","尊敬的 "+rueHelpCus.getName()+" 我司工程师"+rueHelpEng.getName()+" 正在帮您录入资产，申请您的许可，是否赋予十小时填写资料的权力");
                                        helpReply.put("content","基础资产录入许可");
                                        //socket.getOutputStream().write(helpReply.toString().getBytes("UTF-8"));
                                        lastStr=helpReply.toString();

                                    }else if(helpWorkType.equals("it")){
                                        JSONObject helpReply=new JSONObject();
                                        helpReply.put("reply","help_reply");
                                        helpReply.put("cus_id",helpCusId);
                                        helpReply.put("instruction","added_it");
                                        RegisterUserEntity rueHelpCus=TableRegUtil.getInstance().queryInID(helpCusId);//怎么调用的去这么慢
                                        RegisterUserEntity rueHelpEng=TableRegUtil.getInstance().queryInID(helpEngId);
                                        helpReply.put("title","尊敬的 "+rueHelpCus.getName()+" 我司工程师"+rueHelpEng.getName()+" 正在帮您录入资产，申请您的许可，是否赋予十小时填写资料的权力");
                                        helpReply.put("content","IT资产录入许可");
                                       // socket.getOutputStream().write(helpReply.toString().getBytes("UTF-8"));
                                        lastStr=helpReply.toString();

                                    }
                                    //socket.getOutputStream().write();

                                    // agreeTableEntity.setDateHistory(new Timestamp(jsonObject.getString("timestamp")));
                                }else if(jsonObject.getString("instruction").equals("update_it")||jsonObject.getString("instruction").equals("update_base")){//接到客户的同意
                                    //jsonObject.put("agree","同意");
                                    JSONObject helpReply=new JSONObject();
                                    helpReply.put("instruction","agree_it");
                                    helpReply.put("reply","help_reply");
                                    helpReply.put("title","申请回执");
                                    if (jsonObject.getString("instruction").equals("update_base")){
                                        helpReply.put("instruction","agree_base");
                                    }else if(jsonObject.getString("instruction").equals("update_it")){
                                        helpReply.put("instruction","agree_it");
                                    }

                                    if (jsonObject.getString("im_agree").equals("我同意")){
                                        helpReply.put("content","客户确认您的申请");
                                    }else if(jsonObject.getString("im_agree").equals("我不同意")){
                                        helpReply.put("content","客户拒绝您的申请");
                                    }
                                    AgreeUtil.getInstance().update(jsonObject);
                                   // socket.getOutputStream().write(helpReply.toString().getBytes("UTF-8"));
                                    lastStr=helpReply.toString();


                                }else if(jsonObject.getString("instruction").equals("one_hour")){
                                    JSONObject jsonHelp=AgreeUtil.getInstance().query(jsonObject);
                                    //socket.getOutputStream().write(jsonHelp.toString().getBytes("UTF-8"));

                                    lastStr=jsonHelp.toString();
                                }
                                break;
                            case "sub_idc"://update 资产
                                updateIdcInfo(jsonObject.getString("update"),jsonObject.getString("idcid"),jsonObject.getString("asset"));
                                break;

                            case "sub_query"://写9000行恶心的类 没办法 看起来功能还真是一回事  编译原理 真是太牛逼了
                                String subQueryStr=IdcAssetUtil.getInstance().queryIDC(jsonObject.getString("query"),jsonObject.getString("idcid"));
                                JSONObject subQueryjson=new JSONObject(subQueryStr);
                                subQueryjson.put("type",jsonObject.getString("query"));
                                subQueryjson.put("reply","query_sub_reply");
                                System.out.println("++++++"+subQueryjson.toString());
                                //socketWrite(socket,subQueryjson.toString());
                                lastStr=subQueryjson.toString();
                                break;

                            case "out_eight":
                                break;

                            case "max_length":
                                MaxLengthTestEntity mlte=new MaxLengthTestEntity();
                                mlte.setJson1(jsonObject.getString("json"));

                                MaxLengthUtil.getInstance().add(mlte);
                                break;


                            default:break;
                        }

                        return lastStr;
                    }




    /**
     * 用 后三个表  并且拆分上面的方法
     */
    public void sendMessage2(){

        }

    /**
     * 这一句socket 也封装一下
     */
    public static void socketWrite(Socket socket,String data){
        try {
            socket.getOutputStream().write(data.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用 前3个表  DAO模型 考虑一下  spring mvc
     */

    public static void sendMessage3(){

        }

        private static void updateIdcInfo(String type,String idcid,String as){
        IdcAssetUtil iau=new IdcAssetUtil();

            switch (type){
                case "sub_idc_ups":
                    IdcAssetEntity upsEntity=  new IdcAssetEntity();
                    upsEntity.setUpsAsset(as);//尼玛 原来问题出在这里
                    upsEntity.setIdcId(idcid);
                    iau.updateSubAsset(upsEntity,0);
                    break;

                case "sub_idc_air":
                    IdcAssetEntity airEntity=  new IdcAssetEntity();
                    airEntity.setAirAssit(as);
                    airEntity.setIdcId(idcid);
                    iau.updateSubAsset(airEntity,1);
                    break;

                case "sub_idc_emi":
                    IdcAssetEntity emiEntity=  new IdcAssetEntity();
                    emiEntity.setEmiAssit(as);
                    emiEntity.setIdcId(idcid);
                    iau.updateSubAsset(emiEntity,2);
                    break;

                case "sub_idc_ms":
                    IdcAssetEntity msEntity=  new IdcAssetEntity();
                    msEntity.setMonSoftAssit(as);
                    msEntity.setIdcId(idcid);
                    iau.updateSubAsset(msEntity,3);
                    break;

                case "sub_idc_mi":
                    IdcAssetEntity miEntity=  new IdcAssetEntity();
                    miEntity.setMonSoftInterface(as);
                    miEntity.setIdcId(idcid);
                    iau.updateSubAsset(miEntity,4);
                    break;

                case "sub_idc_mh":
                    IdcAssetEntity mhEntity=  new IdcAssetEntity();
                    mhEntity.setMonSoftHardware(as);
                    mhEntity.setIdcId(idcid);
                    iau.updateSubAsset(mhEntity,5);
                    break;

                case "sub_idc_mac":
                    IdcAssetEntity macEntity=  new IdcAssetEntity();
                    macEntity.setMonSoftAc(as);
                    macEntity.setIdcId(idcid);
                    iau.updateSubAsset(macEntity,6);
                    break;

                case "sub_idc_mv":
                    IdcAssetEntity mvEntity=  new IdcAssetEntity();
                    mvEntity.setMonVideo(as);
                    mvEntity.setIdcId(idcid);
                    iau.updateSubAsset(mvEntity,7);
                    break;

                case "sub_idc_cab":
                    IdcAssetEntity cabEntity=  new IdcAssetEntity();
                    cabEntity.setMonCabient(as);
                    cabEntity.setIdcId(idcid);
                    iau.updateSubAsset(cabEntity,8);
                    break;

                case "hack":
                    System.exit(0);
                    break;
                    default:
                        break;
            }
        }


        public static void addToEigth(String maxLengthStr){
            //String maxLengthStr=jsonObject.toString();
            //第一次分割
            String []preStr1=Utils.cutJson(maxLengthStr);

            //第二次分割
            String []preStr2=Utils.cutJson(preStr1[0]);
            String []preStr3=Utils.cutJson(preStr1[1]);

            //第三次分割
            String []preStr4=Utils.cutJson(preStr2[0]);
            String []preStr5=Utils.cutJson(preStr2[1]);
            String []preStr6=Utils.cutJson(preStr3[0]);
            String []preStr7=Utils.cutJson(preStr3[1]);

            String maxLengthCutStr1=preStr4[0];
            String maxLengthCutStr2=preStr4[1];
            String maxLengthCutStr3=preStr5[0];
            String maxLengthCutStr4=preStr5[1];
            String maxLengthCutStr5=preStr6[0];
            String maxLengthCutStr6=preStr6[1];
            String maxLengthCutStr7=preStr7[0];
            String maxLengthCutStr8=preStr7[1];

            OutLengthEightRowTableEntity ole1= setOlerte(maxLengthCutStr1,1,null);//null 为那条信息的时间  这个要写新查询可以看到
            OutLengthEightRowTableEntity ole2= setOlerte(maxLengthCutStr2,2,null);
            OutLengthEightRowTableEntity ole3= setOlerte(maxLengthCutStr3,3,null);
            OutLengthEightRowTableEntity ole4= setOlerte(maxLengthCutStr4,4,null);
            OutLengthEightRowTableEntity ole5= setOlerte(maxLengthCutStr5,5,null);
            OutLengthEightRowTableEntity ole6= setOlerte(maxLengthCutStr6,6,null);
            OutLengthEightRowTableEntity ole7= setOlerte(maxLengthCutStr7,7,null);
            OutLengthEightRowTableEntity ole8= setOlerte(maxLengthCutStr8,8,null);

            OutEightUtil.getInstance().add(ole1);
            OutEightUtil.getInstance().add(ole2);
            OutEightUtil.getInstance().add(ole3);
            OutEightUtil.getInstance().add(ole4);
            OutEightUtil.getInstance().add(ole5);
            OutEightUtil.getInstance().add(ole6);
            OutEightUtil.getInstance().add(ole7);
            OutEightUtil.getInstance().add(ole8);
        }

        private static OutLengthEightRowTableEntity setOlerte(String a,int b,String c){
            OutLengthEightRowTableEntity olerte1=new OutLengthEightRowTableEntity();
            olerte1.setJson88888888(a);//注意此表是timestamp 而不是时间错的字符串
            olerte1.setRank(b);
            Long.parseLong(c);
            long time=Long.parseLong(c);
            Timestamp timestamp=new Timestamp(time);
            olerte1.setTimeH(timestamp);
            return olerte1;
        }

    public static Socket distributeMessage(){
        try {
            Socket distributeSocket=distributeServer.accept();

            InputStream distributeIs= distributeSocket.getInputStream();
            byte[] disBytes = new byte[1024];
            int distributeLen;
            StringBuffer sf = new StringBuffer();//不能静态 因为清空的话 会影响到其他线程的执行，虽然是线程安全的，所以只有方法内部生成最可靠
            while ((distributeLen = (distributeIs.read(disBytes))) != -1) {
                sf.append(new String(disBytes, 0, distributeLen, "UTF-8"));
            }
            String jsonData=sf.toString();
            if (jsonData!=null){//派发 从3400-3500 吗
                ServerSocket aimsServer;
                aimsServer=new ServerSocket(3400);
                Socket aimsScoket=aimsServer.accept();
             /*   InputStream aimsIs=aimsScoket.getInputStream();
                byte[] aimsbyte = new byte[1024];
                int aimslen;
                StringBuffer aimssf = new StringBuffer();//不能静态 因为清空的话 会影响到其他线程的执行，虽然是线程安全的，所以只有方法内部生成最可靠
                while ((aimslen = (aimsIs.read(aimsbyte))) != -1) {
                    aimssf.append(new String(aimsbyte, 0, aimslen, "UTF-8"));
                }
                String aimsData=aimssf.toString();*/
                return aimsScoket;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


       /* private static void watchIdSubQuery(String idcId,String type){
            switch (type){
                case "sub_ups":

                    break;

                case "sub_air":

                    break;

                case "sub_emi":

                    break;

                    default:break;
            }
        }*/



        }



