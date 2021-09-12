package pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeDemo {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("lazydog".getBytes());
        buffer.flip();

        sinkChannel.write(buffer);

        Pipe.SourceChannel sourceChannel = pipe.source();
//        ByteBuffer buffer1 = ByteBuffer.allocate(1024);

        buffer.flip();

        int read = sourceChannel.read(buffer);

        System.out.println(new String(buffer.array(),0,read));

        sinkChannel.close();
        sourceChannel.close();
    }
}
