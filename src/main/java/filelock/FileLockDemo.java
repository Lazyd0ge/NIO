package filelock;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLockDemo {
    public static void main(String[] args) throws IOException {
        String data="lazydog";
        System.out.println(data);

        ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());

        String filePath="F:\\niofile\0.1txt";

        Path path=Paths.get(filePath);
        FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.APPEND);

        channel.position(channel.size()-1);

        FileLock lock = channel.lock();

        System.out.println(lock.isShared());

        channel.write(buffer);


        channel.close();
    }
}
