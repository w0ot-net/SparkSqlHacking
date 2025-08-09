package org.apache.spark.storage;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593AAC\u0006\u0005)!AQ\u0005\u0001B\u0001B\u0003%Q\u0004C\u0003'\u0001\u0011\u0005q\u0005C\u0004,\u0001\u0001\u0007I\u0011\u0002\u0017\t\u000fM\u0002\u0001\u0019!C\u0005i!1!\b\u0001Q!\n5BQa\u000f\u0001\u0005\u00021BQ\u0001\u0010\u0001\u0005BuBQa\u0012\u0001\u0005B!CQ\u0001\u0014\u0001\u0005B5\u0013qcQ8v]RLgnZ,sSR\f'\r\\3DQ\u0006tg.\u001a7\u000b\u00051i\u0011aB:u_J\fw-\u001a\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sO\u000e\u00011c\u0001\u0001\u0016;A\u0011acG\u0007\u0002/)\u0011\u0001$G\u0001\u0005Y\u0006twMC\u0001\u001b\u0003\u0011Q\u0017M^1\n\u0005q9\"AB(cU\u0016\u001cG\u000f\u0005\u0002\u001fG5\tqD\u0003\u0002!C\u0005A1\r[1o]\u0016d7O\u0003\u0002#3\u0005\u0019a.[8\n\u0005\u0011z\"aE,sSR\f'\r\\3CsR,7\t[1o]\u0016d\u0017\u0001B:j].\fa\u0001P5oSRtDC\u0001\u0015+!\tI\u0003!D\u0001\f\u0011\u0015)#\u00011\u0001\u001e\u0003\u0015\u0019w.\u001e8u+\u0005i\u0003C\u0001\u00182\u001b\u0005y#\"\u0001\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iz#\u0001\u0002'p]\u001e\f\u0011bY8v]R|F%Z9\u0015\u0005UB\u0004C\u0001\u00187\u0013\t9tF\u0001\u0003V]&$\bbB\u001d\u0005\u0003\u0003\u0005\r!L\u0001\u0004q\u0012\n\u0014AB2pk:$\b%\u0001\u0005hKR\u001cu.\u001e8u\u0003\u00159(/\u001b;f)\tq\u0014\t\u0005\u0002/\u007f%\u0011\u0001i\f\u0002\u0004\u0013:$\b\"\u0002\"\b\u0001\u0004\u0019\u0015aA:sGB\u0011A)R\u0007\u0002C%\u0011a)\t\u0002\u000b\u0005f$XMQ;gM\u0016\u0014\u0018AB5t\u001fB,g\u000eF\u0001J!\tq#*\u0003\u0002L_\t9!i\\8mK\u0006t\u0017!B2m_N,G#A\u001b"
)
public class CountingWritableChannel implements WritableByteChannel {
   private final WritableByteChannel sink;
   private long count;

   private long count() {
      return this.count;
   }

   private void count_$eq(final long x$1) {
      this.count = x$1;
   }

   public long getCount() {
      return this.count();
   }

   public int write(final ByteBuffer src) {
      int written = this.sink.write(src);
      if (written > 0) {
         this.count_$eq(this.count() + (long)written);
      }

      return written;
   }

   public boolean isOpen() {
      return this.sink.isOpen();
   }

   public void close() {
      this.sink.close();
   }

   public CountingWritableChannel(final WritableByteChannel sink) {
      this.sink = sink;
      this.count = 0L;
   }
}
