package org.apache.spark.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import org.apache.spark.SparkConf;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.security.CryptoStreamUtils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.sparkproject.guava.io.Closeables;
import scala.Function1;
import scala.collection.mutable.ListBuffer;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005i4QAD\b\u0001#]A\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tY\u0001\u0011\t\u0011)A\u0005[!A\u0001\u0007\u0001B\u0001B\u0003%\u0011\u0007\u0003\u00056\u0001\t\u0005\t\u0015!\u00037\u0011\u0015a\u0004\u0001\"\u0001>\u0011\u0015\u0019\u0005\u0001\"\u0011E\u0011\u0015A\u0005\u0001\"\u0011J\u0011\u0015\u0001\u0006\u0001\"\u0011R\u0011\u0015\u0011\u0006\u0001\"\u0011T\u0011\u0015I\u0007\u0001\"\u0011k\u0011\u0015Y\u0007\u0001\"\u0011m\u0011\u0015i\u0007\u0001\"\u0011o\u0011\u0015\u0011\b\u0001\"\u0003t\u0005I)en\u0019:zaR,GM\u00117pG.$\u0015\r^1\u000b\u0005A\t\u0012aB:u_J\fw-\u001a\u0006\u0003%M\tQa\u001d9be.T!\u0001F\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0012aA8sON\u0019\u0001\u0001\u0007\u0010\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PU3g!\ty\u0002%D\u0001\u0010\u0013\t\tsBA\u0005CY>\u001c7\u000eR1uC\u0006!a-\u001b7f\u0007\u0001\u0001\"!\n\u0016\u000e\u0003\u0019R!a\n\u0015\u0002\u0005%|'\"A\u0015\u0002\t)\fg/Y\u0005\u0003W\u0019\u0012AAR5mK\u0006I!\r\\8dWNK'0\u001a\t\u000339J!a\f\u000e\u0003\t1{gnZ\u0001\u0005G>tg\r\u0005\u00023g5\t\u0011#\u0003\u00025#\tI1\u000b]1sW\u000e{gNZ\u0001\u0004W\u0016L\bcA\r8s%\u0011\u0001H\u0007\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u00033iJ!a\u000f\u000e\u0003\t\tKH/Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000byz\u0004)\u0011\"\u0011\u0005}\u0001\u0001\"\u0002\u0012\u0006\u0001\u0004!\u0003\"\u0002\u0017\u0006\u0001\u0004i\u0003\"\u0002\u0019\u0006\u0001\u0004\t\u0004\"B\u001b\u0006\u0001\u00041\u0014!\u0004;p\u0013:\u0004X\u000f^*ue\u0016\fW\u000eF\u0001F!\t)c)\u0003\u0002HM\tY\u0011J\u001c9viN#(/Z1n\u0003\u001d!xNT3uif$\u0012A\u0013\t\u0003\u0017:k\u0011\u0001\u0014\u0006\u0003\u001b\"\nA\u0001\\1oO&\u0011q\n\u0014\u0002\u0007\u001f\nTWm\u0019;\u0002\u001bQ|g*\u001a;us\u001a{'oU:m)\u0005A\u0012a\u0005;p\u0007\",hn[3e\u0005f$XMQ;gM\u0016\u0014HC\u0001+\\!\t)\u0016,D\u0001W\u0015\t9sK\u0003\u0002Y#\u0005!Q\u000f^5m\u0013\tQfKA\tDQVt7.\u001a3CsR,')\u001e4gKJDQ\u0001X\u0005A\u0002u\u000b\u0011\"\u00197m_\u000e\fGo\u001c:\u0011\teq\u0006mY\u0005\u0003?j\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005e\t\u0017B\u00012\u001b\u0005\rIe\u000e\u001e\t\u0003I\u001el\u0011!\u001a\u0006\u0003M\"\n1A\\5p\u0013\tAWM\u0001\u0006CsR,')\u001e4gKJ\fA\u0002^8CsR,')\u001e4gKJ$\u0012aY\u0001\u0005g&TX-F\u0001.\u0003\u001d!\u0017n\u001d9pg\u0016$\u0012a\u001c\t\u00033AL!!\u001d\u000e\u0003\tUs\u0017\u000e^\u0001\u0005_B,g\u000eF\u0001u!\t)\b0D\u0001w\u0015\t9X-\u0001\u0005dQ\u0006tg.\u001a7t\u0013\tIhOA\nSK\u0006$\u0017M\u00197f\u0005f$Xm\u00115b]:,G\u000e"
)
public class EncryptedBlockData implements BlockData {
   private final File file;
   private final long blockSize;
   private final SparkConf conf;
   private final byte[] key;

   public InputStream toInputStream() {
      return Channels.newInputStream(this.open());
   }

   public Object toNetty() {
      return new ReadableChannelFileRegion(this.open(), this.blockSize);
   }

   public Object toNettyForSsl() {
      return this.toChunkedByteBuffer((x$1) -> $anonfun$toNettyForSsl$2(BoxesRunTime.unboxToInt(x$1))).toNettyForSsl();
   }

   public ChunkedByteBuffer toChunkedByteBuffer(final Function1 allocator) {
      ReadableByteChannel source = this.open();

      ChunkedByteBuffer var10000;
      try {
         long remaining = this.blockSize;
         ListBuffer chunks = new ListBuffer();

         while(remaining > 0L) {
            long chunkSize = .MODULE$.min(remaining, 2147483632L);
            ByteBuffer chunk = (ByteBuffer)allocator.apply(BoxesRunTime.boxToInteger((int)chunkSize));
            remaining -= chunkSize;
            JavaUtils.readFully(source, chunk);
            chunk.flip();
            chunks.$plus$eq(chunk);
         }

         var10000 = new ChunkedByteBuffer((ByteBuffer[])chunks.toArray(scala.reflect.ClassTag..MODULE$.apply(ByteBuffer.class)));
      } finally {
         source.close();
      }

      return var10000;
   }

   public ByteBuffer toByteBuffer() {
      scala.Predef..MODULE$.assert(this.blockSize <= 2147483632L, () -> "Block is too large to be wrapped in a byte buffer.");
      ByteBuffer dst = ByteBuffer.allocate((int)this.blockSize);
      ReadableByteChannel in = this.open();

      ByteBuffer var10000;
      try {
         JavaUtils.readFully(in, dst);
         dst.flip();
         var10000 = dst;
      } finally {
         Closeables.close(in, true);
      }

      return var10000;
   }

   public long size() {
      return this.blockSize;
   }

   public void dispose() {
   }

   private ReadableByteChannel open() {
      FileChannel channel = (new FileInputStream(this.file)).getChannel();

      try {
         return CryptoStreamUtils$.MODULE$.createReadableChannel(channel, this.conf, this.key);
      } catch (Exception var3) {
         Closeables.close(channel, true);
         throw var3;
      }
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$toNettyForSsl$2(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   public EncryptedBlockData(final File file, final long blockSize, final SparkConf conf, final byte[] key) {
      this.file = file;
      this.blockSize = blockSize;
      this.conf = conf;
      this.key = key;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
