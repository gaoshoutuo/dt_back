package Socket;

import Bean.Value;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


import java.net.URISyntaxException;

public class SocketIOClient {
    private static Socket socket=null;
    private static String socketUrl;

    public static String getSocketUrl() {
        return socketUrl;
    }

    public static void setSocketUrl(String socketUrl) {
        SocketIOClient.socketUrl = socketUrl;
    }

    public SocketIOClient(String socketUrl) {
        this.socketUrl = socketUrl;//"http://218.108.146.98:3222"
    }

    public static void initSocket(){
        if (socket==null){
        try {
            socket= IO.socket(socketUrl);

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    socket.emit("foo","你好 已经连接");

                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {


                }
            }).on(Value.NOTIFY_OTHER, new Emitter.Listener() {
                @Override
                public void call(Object... args) {


                }
            });
            socket.connect();//保持连接的稳定
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        }
    }
    //java 的socketIO端
    public static void sendMessage(String json){
        initSocket();
        socket.emit(Value.NOTIFY_SERVER,json);
    }

}
