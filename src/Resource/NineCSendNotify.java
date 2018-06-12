package Resource;




import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NineCSendNotify {
    //定时器 每天九点  每小时轮询  若是时刻为九点  发送通知   也可以nodejs

    public void main(String[] args) {
        // TODO Auto-generated method stub
        long time = System.currentTimeMillis();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
       /*         String str = String.format("%1$tM:%1$tS:%1$1tL", System.currentTimeMillis() - time);
              System.out.println(str.substring(0, 7));*/
               Date date=new Date();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
                String str=simpleDateFormat.format(date);
               int i= Integer.valueOf(str.substring(0,2));
               if(i==9){
                   //websocket
                  //Socket socket =new Socket("http://192.168.1.102:3222");
                  Socket socket= initSocketIo("http://192.168.1.102:3222");//传值模块化 局部变量就是组合
                       socketIoU(socket);
                       closeSocket(socket);
                   try {
                       Thread.sleep(3600000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
                //System.out.println(str.substring(0,2));
            }
        };
        new Timer().schedule(task, 30, 2000000);//每隔一小时执行一次
    }
    public Socket initSocketIo(String url){
        Socket socket=null;
        try {
              socket = IO.socket(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public void closeSocket(Socket socket){
        socket.close();
    }

    public void socketIoU(Socket socket){

           //final  Socket socket = initSocketIo(url);
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    socket.emit("foo","你好 已经连接");
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {


                }//此处编译器晕了
            }).on(Resource.MagicStr.XX_S, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    //此处 hib 查数据库
                }
            });
            socket.connect();
            socket.emit(Resource.MagicStr.XX_S,new Emitter.Listener(){
                @Override
                public void call(Object... objects) {
                    JSONObject jsonObject=new JSONObject();
                 //   jsonObject.put("notify",notify)
                }
            });
    }


}
