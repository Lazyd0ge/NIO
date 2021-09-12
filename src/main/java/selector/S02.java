package selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class S02 {

    @Test
    void client() throws IOException {
        SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",8080));

        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(new Date().toString().getBytes());

        buffer.flip();

        socketChannel.write(buffer);

        socketChannel.close();
    }


    @Test
    void Server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select()>0){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()){
                SelectionKey next = selectionKeyIterator.next();
                if(next.isAcceptable()) {
                    SocketChannel accept=serverSocketChannel.accept();

                    accept.configureBlocking(false);

                    accept.register(selector,SelectionKey.OP_READ);

                    // a connection was accepted by a ServerSocketChannel.
                } else if (next.isConnectable()) {


                    // a connection was established with a remote server.
                } else if (next.isReadable()) {
                    SocketChannel channel = (SocketChannel) next.channel();

//                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    //读取数据
                    int length = 0;
                    while((length = channel.read(byteBuffer))>0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,length));
                        byteBuffer.clear();
                    }
                    // a channel is ready for reading
                } else if (next.isWritable()) {

                    // a channel is ready for writing
                }
                selectionKeyIterator.remove();
            }
        }


    }
}
