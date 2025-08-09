package org.apache.spark.storage;

import io.netty.channel.DefaultFileRegion;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import scala.Function1;
import scala.collection.mutable.ListBuffer;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%4AAD\b\u00051!A1\u0005\u0001B\u0001B\u0003%A\u0005\u0003\u0005(\u0001\t\u0005\t\u0015!\u0003%\u0011!A\u0003A!A!\u0002\u0013I\u0003\u0002C\u0019\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0013\t\u000bI\u0002A\u0011A\u001a\t\u000be\u0002A\u0011\t\u001e\t\u000by\u0002A\u0011I \t\u000b\u0001\u0003A\u0011I \t\u000b\u0005\u0003A\u0011\t\"\t\u000ba\u0003A\u0011I-\t\u000bi\u0003A\u0011I.\t\u000bq\u0003A\u0011I/\t\u000b\u0005\u0004A\u0011\u00022\u0003\u001b\u0011K7o\u001b\"m_\u000e\\G)\u0019;b\u0015\t\u0001\u0012#A\u0004ti>\u0014\u0018mZ3\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e\u001c\u0001aE\u0002\u00013}\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0007C\u0001\u0011\"\u001b\u0005y\u0011B\u0001\u0012\u0010\u0005%\u0011En\\2l\t\u0006$\u0018-A\tnS:lU-\\8ss6\u000b\u0007OQ=uKN\u0004\"AG\u0013\n\u0005\u0019Z\"\u0001\u0002'p]\u001e\f\u0011#\\1y\u001b\u0016lwN]=NCB\u0014\u0015\u0010^3t\u0003\u00111\u0017\u000e\\3\u0011\u0005)zS\"A\u0016\u000b\u00051j\u0013AA5p\u0015\u0005q\u0013\u0001\u00026bm\u0006L!\u0001M\u0016\u0003\t\u0019KG.Z\u0001\nE2|7m[*ju\u0016\fa\u0001P5oSRtD#\u0002\u001b6m]B\u0004C\u0001\u0011\u0001\u0011\u0015\u0019S\u00011\u0001%\u0011\u00159S\u00011\u0001%\u0011\u0015AS\u00011\u0001*\u0011\u0015\tT\u00011\u0001%\u00035!x.\u00138qkR\u001cFO]3b[R\t1\b\u0005\u0002+y%\u0011Qh\u000b\u0002\f\u0013:\u0004X\u000f^*ue\u0016\fW.A\u0004u_:+G\u000f^=\u0015\u0003e\tQ\u0002^8OKR$\u0018PR8s'Nd\u0017a\u0005;p\u0007\",hn[3e\u0005f$XMQ;gM\u0016\u0014HCA\"K!\t!\u0005*D\u0001F\u0015\tacI\u0003\u0002H#\u0005!Q\u000f^5m\u0013\tIUIA\tDQVt7.\u001a3CsR,')\u001e4gKJDQaS\u0005A\u00021\u000b\u0011\"\u00197m_\u000e\fGo\u001c:\u0011\tiiuJU\u0005\u0003\u001dn\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005i\u0001\u0016BA)\u001c\u0005\rIe\u000e\u001e\t\u0003'Zk\u0011\u0001\u0016\u0006\u0003+6\n1A\\5p\u0013\t9FK\u0001\u0006CsR,')\u001e4gKJ\fA\u0002^8CsR,')\u001e4gKJ$\u0012AU\u0001\u0005g&TX-F\u0001%\u0003\u001d!\u0017n\u001d9pg\u0016$\u0012A\u0018\t\u00035}K!\u0001Y\u000e\u0003\tUs\u0017\u000e^\u0001\u0005_B,g\u000eF\u0001d!\t!w-D\u0001f\u0015\t1G+\u0001\u0005dQ\u0006tg.\u001a7t\u0013\tAWMA\u0006GS2,7\t[1o]\u0016d\u0007"
)
public class DiskBlockData implements BlockData {
   private final long minMemoryMapBytes;
   private final long maxMemoryMapBytes;
   private final File file;
   private final long blockSize;

   public InputStream toInputStream() {
      return new FileInputStream(this.file);
   }

   public Object toNetty() {
      return new DefaultFileRegion(this.file, 0L, this.size());
   }

   public Object toNettyForSsl() {
      return this.toChunkedByteBuffer((x$1) -> $anonfun$toNettyForSsl$1(BoxesRunTime.unboxToInt(x$1))).toNettyForSsl();
   }

   public ChunkedByteBuffer toChunkedByteBuffer(final Function1 allocator) {
      return (ChunkedByteBuffer)Utils$.MODULE$.tryWithResource(() -> this.open(), (channel) -> {
         long remaining = this.blockSize;
         ListBuffer chunks = new ListBuffer();

         while(remaining > 0L) {
            long chunkSize = .MODULE$.min(remaining, this.maxMemoryMapBytes);
            ByteBuffer chunk = (ByteBuffer)allocator.apply(BoxesRunTime.boxToInteger((int)chunkSize));
            remaining -= chunkSize;
            JavaUtils.readFully(channel, chunk);
            chunk.flip();
            chunks.$plus$eq(chunk);
         }

         return new ChunkedByteBuffer((ByteBuffer[])chunks.toArray(scala.reflect.ClassTag..MODULE$.apply(ByteBuffer.class)));
      });
   }

   public ByteBuffer toByteBuffer() {
      scala.Predef..MODULE$.require(this.blockSize < this.maxMemoryMapBytes, () -> {
         long var10000 = this.blockSize;
         return "can't create a byte buffer of size " + var10000 + " since it exceeds " + Utils$.MODULE$.bytesToString(this.maxMemoryMapBytes) + ".";
      });
      return (ByteBuffer)Utils$.MODULE$.tryWithResource(() -> this.open(), (channel) -> {
         if (this.blockSize < this.minMemoryMapBytes) {
            ByteBuffer buf = ByteBuffer.allocate((int)this.blockSize);
            JavaUtils.readFully(channel, buf);
            buf.flip();
            return buf;
         } else {
            return channel.map(MapMode.READ_ONLY, 0L, this.file.length());
         }
      });
   }

   public long size() {
      return this.blockSize;
   }

   public void dispose() {
   }

   private FileChannel open() {
      return (new FileInputStream(this.file)).getChannel();
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$toNettyForSsl$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   public DiskBlockData(final long minMemoryMapBytes, final long maxMemoryMapBytes, final File file, final long blockSize) {
      this.minMemoryMapBytes = minMemoryMapBytes;
      this.maxMemoryMapBytes = maxMemoryMapBytes;
      this.file = file;
      this.blockSize = blockSize;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
