package org.apache.spark.util;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3Q\u0001C\u0005\u0001\u0017EA\u0001\"\n\u0001\u0003\u0002\u0004%\tA\n\u0005\t_\u0001\u0011\t\u0019!C\u0001a!Aa\u0007\u0001B\u0001B\u0003&q\u0005C\u0003<\u0001\u0011\u0005A\bC\u0003A\u0001\u0011\u0005a\u0005C\u0003B\u0001\u0011%!\tC\u0003L\u0001\u0011%AJ\u0001\nTKJL\u0017\r\\5{C\ndWMQ;gM\u0016\u0014(B\u0001\u0006\f\u0003\u0011)H/\u001b7\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c2\u0001\u0001\n\u0019!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0011\u0011D\t\b\u00035\u0001r!aG\u0010\u000e\u0003qQ!!\b\u0010\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!F\u0005\u0003CQ\tq\u0001]1dW\u0006<W-\u0003\u0002$I\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011\u0005F\u0001\u0007EV4g-\u001a:\u0016\u0003\u001d\u0002\"\u0001K\u0017\u000e\u0003%R!AK\u0016\u0002\u00079LwNC\u0001-\u0003\u0011Q\u0017M^1\n\u00059J#A\u0003\"zi\u0016\u0014UO\u001a4fe\u0006Q!-\u001e4gKJ|F%Z9\u0015\u0005E\"\u0004CA\n3\u0013\t\u0019DC\u0001\u0003V]&$\bbB\u001b\u0003\u0003\u0003\u0005\raJ\u0001\u0004q\u0012\n\u0014a\u00022vM\u001a,'\u000f\t\u0015\u0003\u0007a\u0002\"aE\u001d\n\u0005i\"\"!\u0003;sC:\u001c\u0018.\u001a8u\u0003\u0019a\u0014N\\5u}Q\u0011Qh\u0010\t\u0003}\u0001i\u0011!\u0003\u0005\u0006K\u0011\u0001\raJ\u0001\u0006m\u0006dW/Z\u0001\u000be\u0016\fGm\u00142kK\u000e$HCA\u0019D\u0011\u0015!e\u00011\u0001F\u0003\tIg\u000e\u0005\u0002G\u00136\tqI\u0003\u0002IW\u0005\u0011\u0011n\\\u0005\u0003\u0015\u001e\u0013\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1n\u0003-9(/\u001b;f\u001f\nTWm\u0019;\u0015\u0005Ej\u0005\"\u0002(\b\u0001\u0004y\u0015aA8viB\u0011a\tU\u0005\u0003#\u001e\u0013!c\u00142kK\u000e$x*\u001e;qkR\u001cFO]3b[\u0002"
)
public class SerializableBuffer implements Serializable {
   private transient ByteBuffer buffer;

   public ByteBuffer buffer() {
      return this.buffer;
   }

   public void buffer_$eq(final ByteBuffer x$1) {
      this.buffer = x$1;
   }

   public ByteBuffer value() {
      return this.buffer();
   }

   private void readObject(final ObjectInputStream in) {
      Utils$.MODULE$.tryOrIOException(() -> {
         int length = in.readInt();
         this.buffer_$eq(ByteBuffer.allocate(length));
         int amountRead = 0;

         int ret;
         for(ReadableByteChannel channel = Channels.newChannel(in); amountRead < length; amountRead += ret) {
            ret = channel.read(this.buffer());
            if (ret == -1) {
               throw new EOFException("End of file before fully reading buffer");
            }
         }

         return this.buffer().rewind();
      });
   }

   private void writeObject(final ObjectOutputStream out) {
      Utils$.MODULE$.tryOrIOException(() -> {
         out.writeInt(this.buffer().limit());
         if (Channels.newChannel(out).write(this.buffer()) != this.buffer().limit()) {
            throw new IOException("Could not fully write buffer to output stream");
         } else {
            return this.buffer().rewind();
         }
      });
   }

   public SerializableBuffer(final ByteBuffer buffer) {
      this.buffer = buffer;
      super();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
