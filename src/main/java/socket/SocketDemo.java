package socket;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class SocketDemo {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));

        System.out.println(socketChannel.isOpen());


    }
}
