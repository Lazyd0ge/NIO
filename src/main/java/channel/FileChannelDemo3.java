package channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/*
通道互传
 */
public class FileChannelDemo3 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile=new RandomAccessFile("F:\\niofile\\02.txt", "rw");
        FileChannel tochannel = aFile.getChannel();

        RandomAccessFile bFile=new RandomAccessFile("F:\\niofile\\01.txt", "rw");
        FileChannel fromchannel2 = bFile.getChannel();

        long position=0;
        long size = fromchannel2.size();
        tochannel.transferFrom(fromchannel2,position,size);

        tochannel.close();
        fromchannel2.close();
        System.out.println("============");

    }

}
