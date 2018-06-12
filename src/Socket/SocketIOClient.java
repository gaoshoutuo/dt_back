package Socket;

import Bean.Value;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


import java.net.URISyntaxException;

public class SocketIOClient {
    //java 的socketIO端
    public static void sendMessage(String json){
        try {
            final Socket socket= IO.socket("http://218.108.146.98:3222");
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
            socket.connect();
            socket.emit(Value.NOTIFY_SERVER,json);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
