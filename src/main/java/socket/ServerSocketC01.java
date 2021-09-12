package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class ServerSocketC01 {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port=8888;
        ByteBuffer byteBuffer=ByteBuffer.wrap("hello nio".getBytes());
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        serverSocketChannel.configureBlocking(false);

        while (true){
            System.out.println("等待链接");
            SocketChannel sc = serverSocketChannel.accept();
            if (sc  ==null){
                System.out.println("null");
                TimeUnit.SECONDS.sleep(2);
            }else {
                System.out.println(sc.socket().getRemoteSocketAddress());
                byteBuffer.rewind();//指针0
                sc.write(byteBuffer);
                sc.close();
            }
        }
    }
}
