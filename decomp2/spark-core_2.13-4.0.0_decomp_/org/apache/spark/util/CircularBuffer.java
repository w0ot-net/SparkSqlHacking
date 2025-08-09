package org.apache.spark.util;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4Q!\u0005\n\u0001)iA\u0001b\t\u0001\u0003\u0002\u0003\u0006I!\n\u0005\u0006W\u0001!\t\u0001\f\u0005\ba\u0001\u0001\r\u0011\"\u00032\u0011\u001d\u0011\u0004\u00011A\u0005\nMBa!\u000f\u0001!B\u0013)\u0003b\u0002\u001e\u0001\u0001\u0004%Ia\u000f\u0005\b\u007f\u0001\u0001\r\u0011\"\u0003A\u0011\u0019\u0011\u0005\u0001)Q\u0005y!91\t\u0001b\u0001\n\u0013!\u0005BB&\u0001A\u0003%Q\tC\u0003M\u0001\u0011\u0005Q\nC\u0003Q\u0001\u0011\u0005\u0013k\u0002\u0005^%\u0005\u0005\t\u0012\u0001\u000b_\r!\t\"#!A\t\u0002Qy\u0006\"B\u0016\u000f\t\u0003\u0019\u0007b\u00023\u000f#\u0003%\t!\u001a\u0002\u000f\u0007&\u00148-\u001e7be\n+hMZ3s\u0015\t\u0019B#\u0001\u0003vi&d'BA\u000b\u0017\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0002$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0005\u0019qN]4\u0014\u0005\u0001Y\u0002C\u0001\u000f\"\u001b\u0005i\"B\u0001\u0010 \u0003\tIwNC\u0001!\u0003\u0011Q\u0017M^1\n\u0005\tj\"\u0001D(viB,Ho\u0015;sK\u0006l\u0017aC:ju\u0016LeNQ=uKN\u001c\u0001\u0001\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsEA\u0002J]R\fa\u0001P5oSRtDCA\u00170!\tq\u0003!D\u0001\u0013\u0011\u001d\u0019#\u0001%AA\u0002\u0015\n1\u0001]8t+\u0005)\u0013a\u00029pg~#S-\u001d\u000b\u0003i]\u0002\"AJ\u001b\n\u0005Y:#\u0001B+oSRDq\u0001\u000f\u0003\u0002\u0002\u0003\u0007Q%A\u0002yIE\nA\u0001]8tA\u0005a\u0011n\u001d\"vM\u001a,'OR;mYV\tA\b\u0005\u0002'{%\u0011ah\n\u0002\b\u0005>|G.Z1o\u0003AI7OQ;gM\u0016\u0014h)\u001e7m?\u0012*\u0017\u000f\u0006\u00025\u0003\"9\u0001hBA\u0001\u0002\u0004a\u0014!D5t\u0005V4g-\u001a:Gk2d\u0007%\u0001\u0004ck\u001a4WM]\u000b\u0002\u000bB\u0019aE\u0012%\n\u0005\u001d;#!B!se\u0006L\bC\u0001\u0014J\u0013\tQuE\u0001\u0003CsR,\u0017a\u00022vM\u001a,'\u000fI\u0001\u0006oJLG/\u001a\u000b\u0003i9CQaT\u0006A\u0002\u0015\nQ!\u001b8qkR\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002%B\u00111K\u0017\b\u0003)b\u0003\"!V\u0014\u000e\u0003YS!a\u0016\u0013\u0002\rq\u0012xn\u001c;?\u0013\tIv%\u0001\u0004Qe\u0016$WMZ\u0005\u00037r\u0013aa\u0015;sS:<'BA-(\u00039\u0019\u0015N]2vY\u0006\u0014()\u001e4gKJ\u0004\"A\f\b\u0014\u00059\u0001\u0007C\u0001\u0014b\u0013\t\u0011wE\u0001\u0004B]f\u0014VM\u001a\u000b\u0002=\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE*\u0012A\u001a\u0016\u0003K\u001d\\\u0013\u0001\u001b\t\u0003S:l\u0011A\u001b\u0006\u0003W2\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00055<\u0013AC1o]>$\u0018\r^5p]&\u0011qN\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class CircularBuffer extends OutputStream {
   private final int sizeInBytes;
   private int pos;
   private boolean isBufferFull;
   private final byte[] buffer;

   public static int $lessinit$greater$default$1() {
      return CircularBuffer$.MODULE$.$lessinit$greater$default$1();
   }

   private int pos() {
      return this.pos;
   }

   private void pos_$eq(final int x$1) {
      this.pos = x$1;
   }

   private boolean isBufferFull() {
      return this.isBufferFull;
   }

   private void isBufferFull_$eq(final boolean x$1) {
      this.isBufferFull = x$1;
   }

   private byte[] buffer() {
      return this.buffer;
   }

   public void write(final int input) {
      this.buffer()[this.pos()] = (byte)input;
      this.pos_$eq((this.pos() + 1) % this.buffer().length);
      this.isBufferFull_$eq(this.isBufferFull() || this.pos() == 0);
   }

   public String toString() {
      if (!this.isBufferFull()) {
         return new String(this.buffer(), 0, this.pos(), StandardCharsets.UTF_8);
      } else {
         byte[] nonCircularBuffer = new byte[this.sizeInBytes];
         System.arraycopy(this.buffer(), this.pos(), nonCircularBuffer, 0, this.buffer().length - this.pos());
         System.arraycopy(this.buffer(), 0, nonCircularBuffer, this.buffer().length - this.pos(), this.pos());
         return new String(nonCircularBuffer, StandardCharsets.UTF_8);
      }
   }

   public CircularBuffer(final int sizeInBytes) {
      this.sizeInBytes = sizeInBytes;
      this.pos = 0;
      this.isBufferFull = false;
      this.buffer = new byte[sizeInBytes];
   }
}
