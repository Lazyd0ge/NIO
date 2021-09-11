package channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo1 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile=new RandomAccessFile("F:\\niofile\\01.txt", "rw");
        FileChannel channel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        int bytesRead = channel.read(buf);

        while (bytesRead!=-1){
            System.out.println("读取了"+bytesRead);
            buf.flip();
            while (buf.hasRemaining()){
                System.out.println((char)buf.get());
            }
            buf.clear();
            bytesRead=channel.read(buf);
        }
        aFile.close();
        System.out.println("操作结束");

    }

}
