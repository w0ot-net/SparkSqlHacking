package org.apache.spark.shuffle;

import java.io.Closeable;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.util.zip.Checksum;
import org.apache.spark.SparkException.;
import org.apache.spark.io.MutableCheckedOutputStream;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.shuffle.api.ShufflePartitionWriter;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.TimeTrackingOutputStream;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.collection.PairsWriter;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=f!\u0002\u0014(\u0001%z\u0003\u0002\u0003$\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\u00119\u0003!\u0011!Q\u0001\n=C\u0001\"\u0016\u0001\u0003\u0002\u0003\u0006IA\u0016\u0005\t3\u0002\u0011\t\u0011)A\u00055\"A\u0001\r\u0001B\u0001B\u0003%\u0011\r\u0003\u0005f\u0001\t\u0005\t\u0015!\u0003g\u0011\u0015i\u0007\u0001\"\u0001o\u0011\u001d1\b\u00011A\u0005\n]Dqa\u001f\u0001A\u0002\u0013%A\u0010C\u0004\u0002\u0006\u0001\u0001\u000b\u0015\u0002=\t\u0017\u0005\u001d\u0001\u00011AA\u0002\u0013%\u0011\u0011\u0002\u0005\f\u0003#\u0001\u0001\u0019!a\u0001\n\u0013\t\u0019\u0002C\u0006\u0002\u0018\u0001\u0001\r\u0011!Q!\n\u0005-\u0001bCA\r\u0001\u0001\u0007\t\u0019!C\u0005\u0003\u0013A1\"a\u0007\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002\u001e!Y\u0011\u0011\u0005\u0001A\u0002\u0003\u0005\u000b\u0015BA\u0006\u0011-\t\u0019\u0003\u0001a\u0001\u0002\u0004%I!!\u0003\t\u0017\u0005\u0015\u0002\u00011AA\u0002\u0013%\u0011q\u0005\u0005\f\u0003W\u0001\u0001\u0019!A!B\u0013\tY\u0001C\u0006\u0002.\u0001\u0001\r\u00111A\u0005\n\u0005=\u0002bCA\u001c\u0001\u0001\u0007\t\u0019!C\u0005\u0003sA1\"!\u0010\u0001\u0001\u0004\u0005\t\u0015)\u0003\u00022!I\u0011q\b\u0001A\u0002\u0013%\u0011\u0011\t\u0005\n\u0003\u0013\u0002\u0001\u0019!C\u0005\u0003\u0017B\u0001\"a\u0014\u0001A\u0003&\u00111\t\u0005\n\u0003#\u0002\u0001\u0019!C\u0005\u0003'B\u0011\"a\u0017\u0001\u0001\u0004%I!!\u0018\t\u0011\u0005\u0005\u0004\u0001)Q\u0005\u0003+B1\"a\u0019\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002f!Y\u0011\u0011\u000f\u0001A\u0002\u0003\u0007I\u0011BA:\u0011-\t9\b\u0001a\u0001\u0002\u0003\u0006K!a\u001a\t\u000f\u0005e\u0004\u0001\"\u0011\u0002|!9\u00111\u0012\u0001\u0005\n\u00055\u0005bBAH\u0001\u0011\u0005\u0013Q\u0012\u0005\b\u0003#\u0003A\u0011BAJ\u0011\u001d\tY\u000b\u0001C\u0005\u0003\u001bCq!!,\u0001\t\u0013\tiIA\u000eTQV4g\r\\3QCJ$\u0018\u000e^5p]B\u000b\u0017N]:Xe&$XM\u001d\u0006\u0003Q%\nqa\u001d5vM\u001adWM\u0003\u0002+W\u0005)1\u000f]1sW*\u0011A&L\u0001\u0007CB\f7\r[3\u000b\u00039\n1a\u001c:h'\u0011\u0001\u0001G\u000e \u0011\u0005E\"T\"\u0001\u001a\u000b\u0003M\nQa]2bY\u0006L!!\u000e\u001a\u0003\r\u0005s\u0017PU3g!\t9D(D\u00019\u0015\tI$(\u0001\u0006d_2dWm\u0019;j_:T!aO\u0015\u0002\tU$\u0018\u000e\\\u0005\u0003{a\u00121\u0002U1jeN<&/\u001b;feB\u0011q\bR\u0007\u0002\u0001*\u0011\u0011IQ\u0001\u0003S>T\u0011aQ\u0001\u0005U\u00064\u0018-\u0003\u0002F\u0001\nI1\t\\8tK\u0006\u0014G.Z\u0001\u0010a\u0006\u0014H/\u001b;j_:<&/\u001b;fe\u000e\u0001\u0001CA%M\u001b\u0005Q%BA&(\u0003\r\t\u0007/[\u0005\u0003\u001b*\u0013ac\u00155vM\u001adW\rU1si&$\u0018n\u001c8Xe&$XM]\u0001\u0012g\u0016\u0014\u0018.\u00197ju\u0016\u0014X*\u00198bO\u0016\u0014\bC\u0001)T\u001b\u0005\t&B\u0001**\u0003)\u0019XM]5bY&TXM]\u0005\u0003)F\u0013\u0011cU3sS\u0006d\u0017N_3s\u001b\u0006t\u0017mZ3s\u0003I\u0019XM]5bY&TXM]%ogR\fgnY3\u0011\u0005A;\u0016B\u0001-R\u0005I\u0019VM]5bY&TXM]%ogR\fgnY3\u0002\u000f\tdwnY6JIB\u00111LX\u0007\u00029*\u0011Q,K\u0001\bgR|'/Y4f\u0013\tyFLA\u0004CY>\u001c7.\u00133\u0002\u0019]\u0014\u0018\u000e^3NKR\u0014\u0018nY:\u0011\u0005\t\u001cW\"A\u0014\n\u0005\u0011<#aG*ik\u001a4G.Z,sSR,W*\u001a;sS\u000e\u001c(+\u001a9peR,'/\u0001\u0005dQ\u0016\u001c7n];n!\t97.D\u0001i\u0015\tI'.A\u0002{SBT!a\u000f\"\n\u00051D'\u0001C\"iK\u000e\\7/^7\u0002\rqJg.\u001b;?)\u001dy\u0007/\u001d:tiV\u0004\"A\u0019\u0001\t\u000b\u0019;\u0001\u0019\u0001%\t\u000b9;\u0001\u0019A(\t\u000bU;\u0001\u0019\u0001,\t\u000be;\u0001\u0019\u0001.\t\u000b\u0001<\u0001\u0019A1\t\u000b\u0015<\u0001\u0019\u00014\u0002\u0011%\u001c8\t\\8tK\u0012,\u0012\u0001\u001f\t\u0003ceL!A\u001f\u001a\u0003\u000f\t{w\u000e\\3b]\u0006a\u0011n]\"m_N,Gm\u0018\u0013fcR\u0019Q0!\u0001\u0011\u0005Er\u0018BA@3\u0005\u0011)f.\u001b;\t\u0011\u0005\r\u0011\"!AA\u0002a\f1\u0001\u001f\u00132\u0003%I7o\u00117pg\u0016$\u0007%A\bqCJ$\u0018\u000e^5p]N#(/Z1n+\t\tY\u0001E\u0002@\u0003\u001bI1!a\u0004A\u00051yU\u000f\u001e9viN#(/Z1n\u0003M\u0001\u0018M\u001d;ji&|gn\u0015;sK\u0006lw\fJ3r)\ri\u0018Q\u0003\u0005\n\u0003\u0007a\u0011\u0011!a\u0001\u0003\u0017\t\u0001\u0003]1si&$\u0018n\u001c8TiJ,\u0017-\u001c\u0011\u0002%QLW.\u001a+sC\u000e\\\u0017N\\4TiJ,\u0017-\\\u0001\u0017i&lW\r\u0016:bG.LgnZ*ue\u0016\fWn\u0018\u0013fcR\u0019Q0a\b\t\u0013\u0005\rq\"!AA\u0002\u0005-\u0011a\u0005;j[\u0016$&/Y2lS:<7\u000b\u001e:fC6\u0004\u0013!D<sCB\u0004X\rZ*ue\u0016\fW.A\txe\u0006\u0004\b/\u001a3TiJ,\u0017-\\0%KF$2!`A\u0015\u0011%\t\u0019AEA\u0001\u0002\u0004\tY!\u0001\bxe\u0006\u0004\b/\u001a3TiJ,\u0017-\u001c\u0011\u0002\r=\u0014'nT;u+\t\t\t\u0004E\u0002Q\u0003gI1!!\u000eR\u0005M\u0019VM]5bY&T\u0018\r^5p]N#(/Z1n\u0003)y'M[(vi~#S-\u001d\u000b\u0004{\u0006m\u0002\"CA\u0002+\u0005\u0005\t\u0019AA\u0019\u0003\u001dy'M[(vi\u0002\n\u0011C\\;n%\u0016\u001cwN\u001d3t/JLG\u000f^3o+\t\t\u0019\u0005E\u00022\u0003\u000bJ1!a\u00123\u0005\rIe\u000e^\u0001\u0016]Vl'+Z2pe\u0012\u001cxK]5ui\u0016tw\fJ3r)\ri\u0018Q\n\u0005\n\u0003\u0007A\u0012\u0011!a\u0001\u0003\u0007\n!C\\;n%\u0016\u001cwN\u001d3t/JLG\u000f^3oA\u0005\u00112-\u001e:Ok6\u0014\u0015\u0010^3t/JLG\u000f^3o+\t\t)\u0006E\u00022\u0003/J1!!\u00173\u0005\u0011auN\\4\u0002-\r,(OT;n\u0005f$Xm],sSR$XM\\0%KF$2!`A0\u0011%\t\u0019aGA\u0001\u0002\u0004\t)&A\ndkJtU/\u001c\"zi\u0016\u001cxK]5ui\u0016t\u0007%\u0001\u000bdQ\u0016\u001c7n];n\u001fV$\b/\u001e;TiJ,\u0017-\\\u000b\u0003\u0003O\u0002B!!\u001b\u0002n5\u0011\u00111\u000e\u0006\u0003\u0003&JA!a\u001c\u0002l\tQR*\u001e;bE2,7\t[3dW\u0016$w*\u001e;qkR\u001cFO]3b[\u0006A2\r[3dWN,XnT;uaV$8\u000b\u001e:fC6|F%Z9\u0015\u0007u\f)\bC\u0005\u0002\u0004y\t\t\u00111\u0001\u0002h\u0005)2\r[3dWN,XnT;uaV$8\u000b\u001e:fC6\u0004\u0013!B<sSR,G#B?\u0002~\u0005\u001d\u0005bBA@A\u0001\u0007\u0011\u0011Q\u0001\u0004W\u0016L\bcA\u0019\u0002\u0004&\u0019\u0011Q\u0011\u001a\u0003\u0007\u0005s\u0017\u0010C\u0004\u0002\n\u0002\u0002\r!!!\u0002\u000bY\fG.^3\u0002\t=\u0004XM\u001c\u000b\u0002{\u0006)1\r\\8tK\u0006q1\r\\8tK&3gj\u001c8Ok2dW\u0003BAK\u00037#B!a&\u0002(B!\u0011\u0011TAN\u0019\u0001!q!!($\u0005\u0004\tyJA\u0001U#\r\t\tK\u0010\t\u0004c\u0005\r\u0016bAASe\t9aj\u001c;iS:<\u0007bBAUG\u0001\u0007\u0011qS\u0001\nG2|7/Z1cY\u0016\fQB]3d_J$wK]5ui\u0016t\u0017AE;qI\u0006$XMQ=uKN<&/\u001b;uK:\u0004"
)
public class ShufflePartitionPairsWriter implements PairsWriter, Closeable {
   private final ShufflePartitionWriter partitionWriter;
   private final SerializerManager serializerManager;
   private final SerializerInstance serializerInstance;
   private final BlockId blockId;
   private final ShuffleWriteMetricsReporter writeMetrics;
   private final Checksum checksum;
   private boolean isClosed;
   private OutputStream partitionStream;
   private OutputStream timeTrackingStream;
   private OutputStream wrappedStream;
   private SerializationStream objOut;
   private int numRecordsWritten;
   private long curNumBytesWritten;
   private MutableCheckedOutputStream checksumOutputStream;

   private boolean isClosed() {
      return this.isClosed;
   }

   private void isClosed_$eq(final boolean x$1) {
      this.isClosed = x$1;
   }

   private OutputStream partitionStream() {
      return this.partitionStream;
   }

   private void partitionStream_$eq(final OutputStream x$1) {
      this.partitionStream = x$1;
   }

   private OutputStream timeTrackingStream() {
      return this.timeTrackingStream;
   }

   private void timeTrackingStream_$eq(final OutputStream x$1) {
      this.timeTrackingStream = x$1;
   }

   private OutputStream wrappedStream() {
      return this.wrappedStream;
   }

   private void wrappedStream_$eq(final OutputStream x$1) {
      this.wrappedStream = x$1;
   }

   private SerializationStream objOut() {
      return this.objOut;
   }

   private void objOut_$eq(final SerializationStream x$1) {
      this.objOut = x$1;
   }

   private int numRecordsWritten() {
      return this.numRecordsWritten;
   }

   private void numRecordsWritten_$eq(final int x$1) {
      this.numRecordsWritten = x$1;
   }

   private long curNumBytesWritten() {
      return this.curNumBytesWritten;
   }

   private void curNumBytesWritten_$eq(final long x$1) {
      this.curNumBytesWritten = x$1;
   }

   private MutableCheckedOutputStream checksumOutputStream() {
      return this.checksumOutputStream;
   }

   private void checksumOutputStream_$eq(final MutableCheckedOutputStream x$1) {
      this.checksumOutputStream = x$1;
   }

   public void write(final Object key, final Object value) {
      if (this.isClosed()) {
         throw .MODULE$.internalError("Partition pairs writer is already closed.", "SHUFFLE");
      } else {
         if (this.objOut() == null) {
            this.open();
         }

         this.objOut().writeKey(key, scala.reflect.ClassTag..MODULE$.Any());
         this.objOut().writeValue(value, scala.reflect.ClassTag..MODULE$.Any());
         this.recordWritten();
      }
   }

   private void open() {
      try {
         this.partitionStream_$eq(this.partitionWriter.openStream());
         this.timeTrackingStream_$eq(new TimeTrackingOutputStream(this.writeMetrics, this.partitionStream()));
         if (this.checksum != null) {
            this.checksumOutputStream_$eq(new MutableCheckedOutputStream(this.timeTrackingStream()));
            this.checksumOutputStream().setChecksum(this.checksum);
         }

         this.wrappedStream_$eq(this.serializerManager.wrapStream(this.blockId, (OutputStream)(this.checksumOutputStream() != null ? this.checksumOutputStream() : this.timeTrackingStream())));
         this.objOut_$eq(this.serializerInstance.serializeStream(this.wrappedStream()));
      } catch (Exception var2) {
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.close());
         throw var2;
      }
   }

   public void close() {
      if (!this.isClosed()) {
         Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
            Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
               this.objOut_$eq((SerializationStream)this.closeIfNonNull(this.objOut()));
               this.wrappedStream_$eq((OutputStream)null);
               this.timeTrackingStream_$eq((OutputStream)null);
               this.partitionStream_$eq((OutputStream)null);
            }, (JFunction0.mcV.sp)() -> Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
                  this.wrappedStream_$eq((OutputStream)this.closeIfNonNull(this.wrappedStream()));
                  this.timeTrackingStream_$eq((OutputStream)null);
                  this.partitionStream_$eq((OutputStream)null);
               }, (JFunction0.mcV.sp)() -> Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
                     this.timeTrackingStream_$eq((OutputStream)this.closeIfNonNull(this.timeTrackingStream()));
                     this.partitionStream_$eq((OutputStream)null);
                  }, (JFunction0.mcV.sp)() -> this.partitionStream_$eq((OutputStream)this.closeIfNonNull(this.partitionStream())))));
            this.updateBytesWritten();
         }, (JFunction0.mcV.sp)() -> this.isClosed_$eq(true));
      }
   }

   private Closeable closeIfNonNull(final Closeable closeable) {
      if (closeable != null) {
         closeable.close();
      }

      return null;
   }

   private void recordWritten() {
      this.numRecordsWritten_$eq(this.numRecordsWritten() + 1);
      this.writeMetrics.incRecordsWritten(1L);
      if (this.numRecordsWritten() % 16384 == 0) {
         this.updateBytesWritten();
      }
   }

   private void updateBytesWritten() {
      long numBytesWritten = this.partitionWriter.getNumBytesWritten();
      long bytesWrittenDiff = numBytesWritten - this.curNumBytesWritten();
      this.writeMetrics.incBytesWritten(bytesWrittenDiff);
      this.curNumBytesWritten_$eq(numBytesWritten);
   }

   public ShufflePartitionPairsWriter(final ShufflePartitionWriter partitionWriter, final SerializerManager serializerManager, final SerializerInstance serializerInstance, final BlockId blockId, final ShuffleWriteMetricsReporter writeMetrics, final Checksum checksum) {
      this.partitionWriter = partitionWriter;
      this.serializerManager = serializerManager;
      this.serializerInstance = serializerInstance;
      this.blockId = blockId;
      this.writeMetrics = writeMetrics;
      this.checksum = checksum;
      this.isClosed = false;
      this.numRecordsWritten = 0;
      this.curNumBytesWritten = 0L;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
