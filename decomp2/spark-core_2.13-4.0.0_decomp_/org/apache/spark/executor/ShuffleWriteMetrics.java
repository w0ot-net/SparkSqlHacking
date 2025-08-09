package org.apache.spark.executor;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;
import org.apache.spark.util.LongAccumulator;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00154A\u0001E\t\u00015!11\u0007\u0001C\u0001'QB\u0001b\u000e\u0001C\u0002\u0013\u0005\u0011\u0003\u000f\u0005\u0007\u007f\u0001\u0001\u000b\u0011B\u001d\t\u0011\u0001\u0003!\u0019!C\u0001#aBa!\u0011\u0001!\u0002\u0013I\u0004\u0002\u0003\"\u0001\u0005\u0004%\t!\u0005\u001d\t\r\r\u0003\u0001\u0015!\u0003:\u0011\u0015!\u0005\u0001\"\u0001F\u0011\u0015I\u0005\u0001\"\u0001F\u0011\u0015Q\u0005\u0001\"\u0001F\u0011\u0019Y\u0005\u0001\"\u0011\u0014\u0019\"1!\u000b\u0001C!'MCa!\u0016\u0001\u0005BM1\u0006B\u0002-\u0001\t\u0003\u001a\u0012\f\u0003\u0004\\\u0001\u0011\u00053\u0003\u0018\u0002\u0014'\",hM\u001a7f/JLG/Z'fiJL7m\u001d\u0006\u0003%M\t\u0001\"\u001a=fGV$xN\u001d\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sO\u000e\u00011\u0003\u0002\u0001\u001cC\u001d\u0002\"\u0001H\u0010\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011a!\u00118z%\u00164\u0007C\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\u0014\u0003\u001d\u0019\b.\u001e4gY\u0016L!AJ\u0012\u00037MCWO\u001a4mK^\u0013\u0018\u000e^3NKR\u0014\u0018nY:SKB|'\u000f^3s!\tA\u0003G\u0004\u0002*]9\u0011!&L\u0007\u0002W)\u0011A&G\u0001\u0007yI|w\u000e\u001e \n\u0003yI!aL\u000f\u0002\u000fA\f7m[1hK&\u0011\u0011G\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003_u\ta\u0001P5oSRtD#A\u001b\u0011\u0005Y\u0002Q\"A\t\u0002\u001b}\u0013\u0017\u0010^3t/JLG\u000f^3o+\u0005I\u0004C\u0001\u001e>\u001b\u0005Y$B\u0001\u001f\u0014\u0003\u0011)H/\u001b7\n\u0005yZ$a\u0004'p]\u001e\f5mY;nk2\fGo\u001c:\u0002\u001d}\u0013\u0017\u0010^3t/JLG\u000f^3oA\u0005yqL]3d_J$7o\u0016:jiR,g.\u0001\t`e\u0016\u001cwN\u001d3t/JLG\u000f^3oA\u0005Qql\u001e:ji\u0016$\u0016.\\3\u0002\u0017};(/\u001b;f)&lW\rI\u0001\rEf$Xm],sSR$XM\\\u000b\u0002\rB\u0011AdR\u0005\u0003\u0011v\u0011A\u0001T8oO\u0006q!/Z2pe\u0012\u001cxK]5ui\u0016t\u0017!C<sSR,G+[7f\u0003=Ign\u0019\"zi\u0016\u001cxK]5ui\u0016tGCA'Q!\tab*\u0003\u0002P;\t!QK\\5u\u0011\u0015\t6\u00021\u0001G\u0003\u00051\u0018!E5oGJ+7m\u001c:eg^\u0013\u0018\u000e\u001e;f]R\u0011Q\n\u0016\u0005\u0006#2\u0001\rAR\u0001\rS:\u001cwK]5uKRKW.\u001a\u000b\u0003\u001b^CQ!U\u0007A\u0002\u0019\u000bq\u0002Z3d\u0005f$Xm],sSR$XM\u001c\u000b\u0003\u001bjCQ!\u0015\bA\u0002\u0019\u000b\u0011\u0003Z3d%\u0016\u001cwN\u001d3t/JLG\u000f^3o)\tiU\fC\u0003R\u001f\u0001\u0007a\t\u000b\u0002\u0001?B\u0011\u0001mY\u0007\u0002C*\u0011!mE\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00013b\u00051!UM^3m_B,'/\u00119j\u0001"
)
public class ShuffleWriteMetrics implements ShuffleWriteMetricsReporter, Serializable {
   private final LongAccumulator _bytesWritten = new LongAccumulator();
   private final LongAccumulator _recordsWritten = new LongAccumulator();
   private final LongAccumulator _writeTime = new LongAccumulator();

   public LongAccumulator _bytesWritten() {
      return this._bytesWritten;
   }

   public LongAccumulator _recordsWritten() {
      return this._recordsWritten;
   }

   public LongAccumulator _writeTime() {
      return this._writeTime;
   }

   public long bytesWritten() {
      return this._bytesWritten().sum();
   }

   public long recordsWritten() {
      return this._recordsWritten().sum();
   }

   public long writeTime() {
      return this._writeTime().sum();
   }

   public void incBytesWritten(final long v) {
      this._bytesWritten().add(v);
   }

   public void incRecordsWritten(final long v) {
      this._recordsWritten().add(v);
   }

   public void incWriteTime(final long v) {
      this._writeTime().add(v);
   }

   public void decBytesWritten(final long v) {
      this._bytesWritten().setValue(this.bytesWritten() - v);
   }

   public void decRecordsWritten(final long v) {
      this._recordsWritten().setValue(this.recordsWritten() - v);
   }
}
