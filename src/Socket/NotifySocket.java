package Socket;

import Bean.JsonBean;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NotifySocket {
    static StringBuilder sb=null;
    static int port = 3334;// port
    static ServerSocket server;

    static {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(){
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        // 监听指定的端口
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("server将一直等待连接的到来");
                    Socket socket = server.accept();
                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    sb = new StringBuilder();
                    while ((len = inputStream.read(bytes)) != -1) {
                        //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                        sb.append(new String(bytes, 0, len,"UTF-8"));
                    }
                    JSONObject jsonObject=new JSONObject(sb.toString());
                    switch (jsonObject.getString("au")){
                        case "ups_notify":
                            break;

                            default:break;
                    }



                    System.out.println("get message from client: " + sb);
                    //  inputStream.close();
                    socket.close();
                    //server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // server将一直等待连接的到来
            }
        };
        threadPool.submit(runnable);
    }
}
