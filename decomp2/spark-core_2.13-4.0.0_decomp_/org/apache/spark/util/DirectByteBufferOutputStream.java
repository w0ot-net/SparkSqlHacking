package org.apache.spark.util;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.spark.SparkException.;
import org.apache.spark.storage.StorageUtils$;
import org.apache.spark.unsafe.Platform;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]3QAD\b\u0001#]A\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006IA\t\u0005\u0006Q\u0001!\t!\u000b\u0005\u0007[\u0001\u0001\u000b\u0015\u0002\u0018\t\u000b!\u0002A\u0011\u0001\u001b\t\u000bU\u0002A\u0011\t\u001c\t\u000bU\u0002A\u0011\t\u001f\t\u000b!\u0003A\u0011B%\t\u000b1\u0003A\u0011B'\t\u000b=\u0003A\u0011\u0002)\t\u000bE\u0003A\u0011\u0001)\t\u000bI\u0003A\u0011A*\t\u000bQ\u0003A\u0011A+\t\u000bY\u0003A\u0011\t)\u00039\u0011K'/Z2u\u0005f$XMQ;gM\u0016\u0014x*\u001e;qkR\u001cFO]3b[*\u0011\u0001#E\u0001\u0005kRLGN\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h'\t\u0001\u0001\u0004\u0005\u0002\u001a=5\t!D\u0003\u0002\u001c9\u0005\u0011\u0011n\u001c\u0006\u0002;\u0005!!.\u0019<b\u0013\ty\"D\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW.\u0001\u0005dCB\f7-\u001b;z\u0007\u0001\u0001\"a\t\u0014\u000e\u0003\u0011R\u0011!J\u0001\u0006g\u000e\fG.Y\u0005\u0003O\u0011\u00121!\u00138u\u0003\u0019a\u0014N\\5u}Q\u0011!\u0006\f\t\u0003W\u0001i\u0011a\u0004\u0005\u0006A\t\u0001\rAI\u0001\u0007EV4g-\u001a:\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0005Eb\u0012a\u00018j_&\u00111\u0007\r\u0002\u000b\u0005f$XMQ;gM\u0016\u0014H#\u0001\u0016\u0002\u000b]\u0014\u0018\u000e^3\u0015\u0005]R\u0004CA\u00129\u0013\tIDE\u0001\u0003V]&$\b\"B\u001e\u0006\u0001\u0004\u0011\u0013!\u00012\u0015\t]jDI\u0012\u0005\u0006w\u0019\u0001\rA\u0010\t\u0004G}\n\u0015B\u0001!%\u0005\u0015\t%O]1z!\t\u0019#)\u0003\u0002DI\t!!)\u001f;f\u0011\u0015)e\u00011\u0001#\u0003\rygM\u001a\u0005\u0006\u000f\u001a\u0001\rAI\u0001\u0004Y\u0016t\u0017AD3ogV\u0014XmQ1qC\u000eLG/\u001f\u000b\u0003o)CQaS\u0004A\u0002\t\n1\"\\5o\u0007\u0006\u0004\u0018mY5us\u0006!qM]8x)\t9d\nC\u0003L\u0011\u0001\u0007!%\u0001\bdQ\u0016\u001c7NT8u\u00072|7/\u001a3\u0015\u0003]\nQA]3tKR\fAa]5{KR\t!%\u0001\u0007u_\nKH/\u001a\"vM\u001a,'/F\u0001/\u0003\u0015\u0019Gn\\:f\u0001"
)
public class DirectByteBufferOutputStream extends OutputStream {
   private ByteBuffer buffer;

   public void write(final int b) {
      this.checkNotClosed();
      this.ensureCapacity(this.buffer.position() + 1);
      this.buffer.put((byte)b);
   }

   public void write(final byte[] b, final int off, final int len) {
      this.checkNotClosed();
      this.ensureCapacity(this.buffer.position() + len);
      this.buffer.put(b, off, len);
   }

   private void ensureCapacity(final int minCapacity) {
      if (minCapacity > this.buffer.capacity()) {
         this.grow(minCapacity);
      }
   }

   private void grow(final int minCapacity) {
      int oldCapacity = this.buffer.capacity();
      int newCapacity = oldCapacity << 1;
      if (newCapacity < minCapacity) {
         newCapacity = minCapacity;
      }

      ByteBuffer oldBuffer = this.buffer;
      oldBuffer.flip();
      ByteBuffer newBuffer = Platform.allocateDirectBuffer(newCapacity);
      newBuffer.put(oldBuffer);
      StorageUtils$.MODULE$.dispose(oldBuffer);
      this.buffer = newBuffer;
   }

   private void checkNotClosed() {
      if (this.buffer == null) {
         throw .MODULE$.internalError("Cannot call methods on a closed DirectByteBufferOutputStream");
      }
   }

   public void reset() {
      this.checkNotClosed();
      this.buffer.clear();
   }

   public int size() {
      this.checkNotClosed();
      return this.buffer.position();
   }

   public ByteBuffer toByteBuffer() {
      this.checkNotClosed();
      ByteBuffer outputBuffer = this.buffer.duplicate();
      outputBuffer.flip();
      return outputBuffer;
   }

   public void close() {
      StorageUtils$.MODULE$.dispose(this.buffer);
      this.buffer = null;
   }

   public DirectByteBufferOutputStream(final int capacity) {
      this.buffer = Platform.allocateDirectBuffer(capacity);
   }

   public DirectByteBufferOutputStream() {
      this(32);
   }
}
