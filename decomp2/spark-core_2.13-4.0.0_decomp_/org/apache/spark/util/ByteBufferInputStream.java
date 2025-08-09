package org.apache.spark.util;

import java.io.InputStream;
import java.nio.ByteBuffer;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3QAC\u0006\u0001\u001bMA\u0001\u0002\b\u0001\u0003\u0002\u0004%IA\b\u0005\tK\u0001\u0011\t\u0019!C\u0005M!Aq\u0006\u0001B\u0001B\u0003&q\u0004C\u00031\u0001\u0011\u0005\u0011\u0007C\u00036\u0001\u0011\u0005c\u0007C\u00036\u0001\u0011\u0005#\bC\u00036\u0001\u0011\u00053\tC\u0003J\u0001\u0011\u0005#\nC\u0003Q\u0001\u0011%\u0011KA\u000bCsR,')\u001e4gKJLe\u000e];u'R\u0014X-Y7\u000b\u00051i\u0011\u0001B;uS2T!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\n\u0003\u0001Q\u0001\"!\u0006\u000e\u000e\u0003YQ!a\u0006\r\u0002\u0005%|'\"A\r\u0002\t)\fg/Y\u0005\u00037Y\u00111\"\u00138qkR\u001cFO]3b[\u00061!-\u001e4gKJ\u001c\u0001!F\u0001 !\t\u00013%D\u0001\"\u0015\t\u0011\u0003$A\u0002oS>L!\u0001J\u0011\u0003\u0015\tKH/\u001a\"vM\u001a,'/\u0001\u0006ck\u001a4WM]0%KF$\"aJ\u0017\u0011\u0005!ZS\"A\u0015\u000b\u0003)\nQa]2bY\u0006L!\u0001L\u0015\u0003\tUs\u0017\u000e\u001e\u0005\b]\t\t\t\u00111\u0001 \u0003\rAH%M\u0001\bEV4g-\u001a:!\u0003\u0019a\u0014N\\5u}Q\u0011!\u0007\u000e\t\u0003g\u0001i\u0011a\u0003\u0005\u00069\u0011\u0001\raH\u0001\u0005e\u0016\fG\rF\u00018!\tA\u0003(\u0003\u0002:S\t\u0019\u0011J\u001c;\u0015\u0005]Z\u0004\"\u0002\u001f\u0007\u0001\u0004i\u0014\u0001\u00023fgR\u00042\u0001\u000b A\u0013\ty\u0014FA\u0003BeJ\f\u0017\u0010\u0005\u0002)\u0003&\u0011!)\u000b\u0002\u0005\u0005f$X\r\u0006\u00038\t\u0016;\u0005\"\u0002\u001f\b\u0001\u0004i\u0004\"\u0002$\b\u0001\u00049\u0014AB8gMN,G\u000fC\u0003I\u000f\u0001\u0007q'\u0001\u0004mK:<G\u000f[\u0001\u0005g.L\u0007\u000f\u0006\u0002L\u001dB\u0011\u0001\u0006T\u0005\u0003\u001b&\u0012A\u0001T8oO\")q\n\u0003a\u0001\u0017\u0006)!-\u001f;fg\u000691\r\\3b]V\u0003H#A\u0014"
)
public class ByteBufferInputStream extends InputStream {
   private ByteBuffer buffer;

   private ByteBuffer buffer() {
      return this.buffer;
   }

   private void buffer_$eq(final ByteBuffer x$1) {
      this.buffer = x$1;
   }

   public int read() {
      if (this.buffer() != null && this.buffer().remaining() != 0) {
         return this.buffer().get() & 255;
      } else {
         this.cleanUp();
         return -1;
      }
   }

   public int read(final byte[] dest) {
      return this.read(dest, 0, dest.length);
   }

   public int read(final byte[] dest, final int offset, final int length) {
      if (this.buffer() != null && this.buffer().remaining() != 0) {
         int amountToGet = .MODULE$.min(this.buffer().remaining(), length);
         this.buffer().get(dest, offset, amountToGet);
         return amountToGet;
      } else {
         this.cleanUp();
         return -1;
      }
   }

   public long skip(final long bytes) {
      if (this.buffer() != null) {
         int amountToSkip = (int).MODULE$.min(bytes, (long)this.buffer().remaining());
         this.buffer().position(this.buffer().position() + amountToSkip);
         if (this.buffer().remaining() == 0) {
            this.cleanUp();
         }

         return (long)amountToSkip;
      } else {
         return 0L;
      }
   }

   private void cleanUp() {
      if (this.buffer() != null) {
         this.buffer_$eq((ByteBuffer)null);
      }
   }

   public ByteBufferInputStream(final ByteBuffer buffer) {
      this.buffer = buffer;
      super();
   }
}
