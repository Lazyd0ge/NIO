package socket;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class DatagramCd {
    @Test
    void send() throws IOException, InterruptedException {
        DatagramChannel send=DatagramChannel.open();
        InetSocketAddress sendAddress=new InetSocketAddress("127.0.0.1", 9999);
        while (true){
            ByteBuffer buffer=ByteBuffer.wrap("发送nio".getBytes("utf-8"));
            send.send(buffer,sendAddress);
            System.out.println("发送完成");
            Thread.sleep(10000);
        }
    }

    @Test
    void receive()throws IOException {
        DatagramChannel recv=DatagramChannel.open();
        InetSocketAddress recvAddress=new InetSocketAddress(9999  );
        recv.bind(recvAddress);

        ByteBuffer buffer=ByteBuffer.allocate(1024);
        while (true){
            buffer.clear();
            SocketAddress receive = recv.receive(buffer);
            buffer.flip();
            System.out.println(receive.toString());
            System.out.println(Charset.forName("UTF-8").decode(buffer));
        }


    }

}
