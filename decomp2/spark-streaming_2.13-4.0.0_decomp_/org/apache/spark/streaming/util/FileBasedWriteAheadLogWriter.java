package org.apache.spark.streaming.util;

import java.io.Closeable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.spark.util.Utils.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]4Qa\u0004\t\u0001%iA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tq\u0001\u0011\t\u0011)A\u0005s!)\u0011\t\u0001C\u0001\u0005\"Aq\t\u0001EC\u0002\u0013%\u0001\nC\u0004P\u0001\u0001\u0007I\u0011\u0002)\t\u000fU\u0003\u0001\u0019!C\u0005-\"1A\f\u0001Q!\nECq!\u0018\u0001A\u0002\u0013%a\fC\u0004c\u0001\u0001\u0007I\u0011B2\t\r\u0015\u0004\u0001\u0015)\u0003`\u0011\u00151\u0007\u0001\"\u0001h\u0011\u0015\u0019\b\u0001\"\u0011u\u0011\u0015)\b\u0001\"\u0003u\u0011\u00151\b\u0001\"\u0003u\u0005q1\u0015\u000e\\3CCN,Gm\u0016:ji\u0016\f\u0005.Z1e\u0019><wK]5uKJT!!\u0005\n\u0002\tU$\u0018\u000e\u001c\u0006\u0003'Q\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001c2\u0001A\u000e$!\ta\u0012%D\u0001\u001e\u0015\tqr$\u0001\u0003mC:<'\"\u0001\u0011\u0002\t)\fg/Y\u0005\u0003Eu\u0011aa\u00142kK\u000e$\bC\u0001\u0013(\u001b\u0005)#B\u0001\u0014 \u0003\tIw.\u0003\u0002)K\tI1\t\\8tK\u0006\u0014G.Z\u0001\u0005a\u0006$\bn\u0001\u0001\u0011\u00051*dBA\u00174!\tq\u0013'D\u00010\u0015\t\u0001$&\u0001\u0004=e>|GO\u0010\u0006\u0002e\u0005)1oY1mC&\u0011A'M\u0001\u0007!J,G-\u001a4\n\u0005Y:$AB*ue&twM\u0003\u00025c\u0005Q\u0001.\u00193p_B\u001cuN\u001c4\u0011\u0005izT\"A\u001e\u000b\u0005qj\u0014\u0001B2p]\u001aT!A\u0010\f\u0002\r!\fGm\\8q\u0013\t\u00015HA\u0007D_:4\u0017nZ;sCRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\r+e\t\u0005\u0002E\u00015\t\u0001\u0003C\u0003*\u0007\u0001\u00071\u0006C\u00039\u0007\u0001\u0007\u0011(\u0001\u0004tiJ,\u0017-\\\u000b\u0002\u0013B\u0011!*T\u0007\u0002\u0017*\u0011A*P\u0001\u0003MNL!AT&\u0003%\u0019\u001bF)\u0019;b\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\u000b]\u0016DHo\u00144gg\u0016$X#A)\u0011\u0005I\u001bV\"A\u0019\n\u0005Q\u000b$\u0001\u0002'p]\u001e\faB\\3yi>3gm]3u?\u0012*\u0017\u000f\u0006\u0002X5B\u0011!\u000bW\u0005\u00033F\u0012A!\u00168ji\"91LBA\u0001\u0002\u0004\t\u0016a\u0001=%c\u0005Ya.\u001a=u\u001f\u001a47/\u001a;!\u0003\u0019\u0019Gn\\:fIV\tq\f\u0005\u0002SA&\u0011\u0011-\r\u0002\b\u0005>|G.Z1o\u0003)\u0019Gn\\:fI~#S-\u001d\u000b\u0003/\u0012DqaW\u0005\u0002\u0002\u0003\u0007q,A\u0004dY>\u001cX\r\u001a\u0011\u0002\u000b]\u0014\u0018\u000e^3\u0015\u0005!\\\u0007C\u0001#j\u0013\tQ\u0007CA\u000fGS2,')Y:fI^\u0013\u0018\u000e^3BQ\u0016\fG\rT8h'\u0016<W.\u001a8u\u0011\u0015a7\u00021\u0001n\u0003\u0011!\u0017\r^1\u0011\u00059\fX\"A8\u000b\u0005A|\u0012a\u00018j_&\u0011!o\u001c\u0002\u000b\u0005f$XMQ;gM\u0016\u0014\u0018!B2m_N,G#A,\u0002\u000b\u0019dWo\u001d5\u0002\u0015\u0005\u001c8/\u001a:u\u001fB,g\u000e"
)
public class FileBasedWriteAheadLogWriter implements Closeable {
   private FSDataOutputStream stream;
   private final String path;
   private Configuration hadoopConf;
   private long nextOffset;
   private boolean closed;
   private volatile boolean bitmap$0;

   private FSDataOutputStream stream$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.stream = HdfsUtils$.MODULE$.getOutputStream(this.path, this.hadoopConf);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this.hadoopConf = null;
      return this.stream;
   }

   private FSDataOutputStream stream() {
      return !this.bitmap$0 ? this.stream$lzycompute() : this.stream;
   }

   private long nextOffset() {
      return this.nextOffset;
   }

   private void nextOffset_$eq(final long x$1) {
      this.nextOffset = x$1;
   }

   private boolean closed() {
      return this.closed;
   }

   private void closed_$eq(final boolean x$1) {
      this.closed = x$1;
   }

   public synchronized FileBasedWriteAheadLogSegment write(final ByteBuffer data) {
      this.assertOpen();
      data.rewind();
      int lengthToWrite = data.remaining();
      FileBasedWriteAheadLogSegment segment = new FileBasedWriteAheadLogSegment(this.path, this.nextOffset(), lengthToWrite);
      this.stream().writeInt(lengthToWrite);
      .MODULE$.writeByteBuffer(data, this.stream());
      this.flush();
      this.nextOffset_$eq(this.stream().getPos());
      return segment;
   }

   public synchronized void close() {
      this.closed_$eq(true);
      this.stream().close();
   }

   private void flush() {
      this.stream().hflush();
      this.stream().getWrappedStream().flush();
   }

   private void assertOpen() {
      HdfsUtils$.MODULE$.checkState(!this.closed(), () -> "Stream is closed. Create a new Writer to write to file.");
   }

   public FileBasedWriteAheadLogWriter(final String path, final Configuration hadoopConf) {
      this.path = path;
      this.hadoopConf = hadoopConf;
      super();
      this.nextOffset = this.stream().getPos();
      this.closed = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
