package channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWrite {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile=new RandomAccessFile("F:\\niofile\\001.txt", "rw");
        FileChannel channel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String dataenglish="hello nio";
        String data="你好啊，中文";
        buf.clear();
        buf.put(dataenglish.getBytes());
        buf.put(data.getBytes());

        buf.flip();

        while (buf.hasRemaining()){
            channel.write(buf);

        }

        channel.close();


    }

}
