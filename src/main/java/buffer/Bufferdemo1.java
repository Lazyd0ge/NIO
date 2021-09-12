package buffer;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class Bufferdemo1 {
    @Test
    public void buffer01() throws IOException {
        RandomAccessFile aFile=new RandomAccessFile("F:\\niofile\\01.txt", "rw");
        FileChannel channel = aFile.getChannel();


        ByteBuffer buffer=ByteBuffer.allocate(1024);

        int bytesRead = channel.read(buffer);

        while (bytesRead!=-1){
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println((char)buffer.get());
            }
            buffer.clear();
           bytesRead= channel.read(buffer);
        }
        aFile.close();
    }
    @Test
    public void buffer02() throws IOException{
        IntBuffer buffer=IntBuffer.allocate(8);
        for (int i = 0; i < buffer.capacity(); i++) {
            int j=2*(i+1);
            buffer.put(j);
        }

        buffer.flip();

        while (buffer.hasRemaining()){
            int value = buffer.get();
            System.out.println(value+" ");
        }
    }

    @Test
    void b01(){
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }

        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *=10;
            slice.put(i,b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining()>0){
            System.out.println(buffer.get());
        }
    }
}
