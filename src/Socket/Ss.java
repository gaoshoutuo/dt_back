package Socket;


import DAO.TestAI;
import DAO.TestInfo;
import Entity.InfoEntity;
import Entity.RegisterUserEntity;
import Entity.Test3Entity;
import Entity.TesttableEntity;
import Resource.InfoUtil;
import Resource.TableRegUtil;
import Resource.TestA;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端 得到输入流 是对方发来的
 * 服务端  得到输出流是自己发给对方的
 * 后端java8语法冷静 冷静
 */
public class Ss   {
    static int port = 3333;
    public static ServerSocket erverSocket;

    static {
        try {
            erverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public interface Callback{
        public void exec();
    }

    public static void sendMessage() throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);//线程池
                Socket socket = erverSocket.accept();//准备
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
                          //  {"age":"15","name","helloworld"}
                            if(sf.toString().length()>5){

                                //JSONObject jsonObject=new JSONObject(sf.toString());这句api 不行
                                //JSONObject jsonObject=JSONObject.
                                JSONObject jsonObject=new JSONObject(sf.toString());
                                System.out.printf(jsonObject.toString());

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
                                        tableRegUtil.add(registerUserEntity);
                                        OutputStream outputStream2 = socket.getOutputStream();
                                        outputStream2.write("注册成功".getBytes("UTF-8"));
                                        break;
                                    case "register2":
                                        TableRegUtil tableRegUtil2=new TableRegUtil();
                                        Test3Entity test3Entity=new Test3Entity();
                                        test3Entity.setData(jsonObject.getString("data"));
                                        tableRegUtil2.add(test3Entity);
                                        break;
                                    case "update":

                                        break;
                                    case "delete":

                                        break;
                                    case "dtsjwb":

                                        break;
                                    case "ups_fix":

                                        break;
                                    case "ups_notify":

                                        break;
                                    case "air_notify":

                                        break;
                                    case "fire_war":

                                        break;
                                    default:break;
                                }
                            }


                                //这种方法一次就断了 不好   notify 也加入这里不就好了哈
                               // is.close();
                             //   outputStream.close();
                                socket.close();
                              //  serverSocket.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                threadPool.submit(runnable);
            }

    /**
     * 用 后三个表  并且拆分上面的方法
     */
    public void sendMessage2(){

        }

    /**
     * 用 前3个表  DAO模型 考虑一下  spring mvc
     */

    public void sendMessage3(){

        }

        }



