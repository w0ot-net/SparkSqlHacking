package org.apache.spark.util;

import java.io.ByteArrayOutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3Qa\u0003\u0007\u0001\u001dQA\u0001\"\b\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\u0006K\u0001!\tA\n\u0005\u0006K\u0001!\tA\u000b\u0005\u0006W\u0001!\t\u0001\f\u0005\u0007[\u0001\u0001\u000b\u0015\u0002\u0018\t\u000bE\u0002A\u0011\t\u001a\t\u000bE\u0002A\u0011\t\u001d\t\u000b\u0011\u0003A\u0011I#\t\u000b\u0019\u0003A\u0011I#\t\u000b\u001d\u0003A\u0011\u0001%\u0003-\tKH/\u001a\"vM\u001a,'oT;uaV$8\u000b\u001e:fC6T!!\u0004\b\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sON\u0011\u0001!\u0006\t\u0003-mi\u0011a\u0006\u0006\u00031e\t!![8\u000b\u0003i\tAA[1wC&\u0011Ad\u0006\u0002\u0016\u0005f$X-\u0011:sCf|U\u000f\u001e9viN#(/Z1n\u0003!\u0019\u0017\r]1dSRL8\u0001\u0001\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0004\u0013:$\u0018A\u0002\u001fj]&$h\b\u0006\u0002(SA\u0011\u0001\u0006A\u0007\u0002\u0019!)QD\u0001a\u0001?Q\tq%\u0001\u0005hKR\u001cu.\u001e8u)\u0005y\u0012AB2m_N,G\r\u0005\u0002!_%\u0011\u0001'\t\u0002\b\u0005>|G.Z1o\u0003\u00159(/\u001b;f)\t\u0019d\u0007\u0005\u0002!i%\u0011Q'\t\u0002\u0005+:LG\u000fC\u00038\r\u0001\u0007q$A\u0001c)\u0011\u0019\u0014\b\u0011\"\t\u000b]:\u0001\u0019\u0001\u001e\u0011\u0007\u0001ZT(\u0003\u0002=C\t)\u0011I\u001d:bsB\u0011\u0001EP\u0005\u0003\u007f\u0005\u0012AAQ=uK\")\u0011i\u0002a\u0001?\u0005\u0019qN\u001a4\t\u000b\r;\u0001\u0019A\u0010\u0002\u00071,g.A\u0003sKN,G\u000fF\u00014\u0003\u0015\u0019Gn\\:f\u00031!xNQ=uK\n+hMZ3s+\u0005I\u0005C\u0001&N\u001b\u0005Y%B\u0001'\u001a\u0003\rq\u0017n\\\u0005\u0003\u001d.\u0013!BQ=uK\n+hMZ3s\u0001"
)
public class ByteBufferOutputStream extends ByteArrayOutputStream {
   private boolean closed;

   public int getCount() {
      return this.count;
   }

   public void write(final int b) {
      .MODULE$.require(!this.closed, () -> "cannot write to a closed ByteBufferOutputStream");
      super.write(b);
   }

   public void write(final byte[] b, final int off, final int len) {
      .MODULE$.require(!this.closed, () -> "cannot write to a closed ByteBufferOutputStream");
      super.write(b, off, len);
   }

   public void reset() {
      .MODULE$.require(!this.closed, () -> "cannot reset a closed ByteBufferOutputStream");
      super.reset();
   }

   public void close() {
      if (!this.closed) {
         super.close();
         this.closed = true;
      }
   }

   public ByteBuffer toByteBuffer() {
      .MODULE$.require(this.closed, () -> "can only call toByteBuffer() after ByteBufferOutputStream has been closed");
      return ByteBuffer.wrap(this.buf, 0, this.count);
   }

   public ByteBufferOutputStream(final int capacity) {
      super(capacity);
      this.closed = false;
   }

   public ByteBufferOutputStream() {
      this(32);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
