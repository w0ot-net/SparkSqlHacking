package org.apache.spark.status.api.v1;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513A\u0001F\u000b\u0001E!A\u0011\u0006\u0001BC\u0002\u0013\u0005!\u0006\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003,\u0011!y\u0003A!b\u0001\n\u0003Q\u0003\u0002\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\u0011E\u0002!Q1A\u0005\u0002)B\u0001B\r\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tg\u0001\u0011)\u0019!C\u0001U!AA\u0007\u0001B\u0001B\u0003%1\u0006\u0003\u00056\u0001\t\u0015\r\u0011\"\u0001+\u0011!1\u0004A!A!\u0002\u0013Y\u0003\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011\u0001\u0016\t\u0011a\u0002!\u0011!Q\u0001\n-B\u0001\"\u000f\u0001\u0003\u0006\u0004%\tA\u000b\u0005\tu\u0001\u0011\t\u0011)A\u0005W!A1\b\u0001BC\u0002\u0013\u0005!\u0006\u0003\u0005=\u0001\t\u0005\t\u0015!\u0003,\u0011!i\u0004A!b\u0001\n\u0003Q\u0003\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\r}\u0002A\u0011A\u000eA\u0005Y\u0019\u0006.\u001e4gY\u0016\u0004Vo\u001d5SK\u0006$W*\u001a;sS\u000e\u001c(B\u0001\f\u0018\u0003\t1\u0018G\u0003\u0002\u00193\u0005\u0019\u0011\r]5\u000b\u0005iY\u0012AB:uCR,8O\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h\u0007\u0001\u0019\"\u0001A\u0012\u0011\u0005\u0011:S\"A\u0013\u000b\u0003\u0019\nQa]2bY\u0006L!\u0001K\u0013\u0003\r\u0005s\u0017PU3g\u0003a\u0019wN\u001d:vaRlUM]4fI\ncwnY6DQVt7n]\u000b\u0002WA\u0011A\u0005L\u0005\u0003[\u0015\u0012A\u0001T8oO\u0006I2m\u001c:skB$X*\u001a:hK\u0012\u0014En\\2l\u0007\",hn[:!\u0003aiWM]4fI\u001a+Go\u00195GC2d'-Y2l\u0007>,h\u000e^\u0001\u001a[\u0016\u0014x-\u001a3GKR\u001c\u0007NR1mY\n\f7m[\"pk:$\b%A\rsK6|G/Z'fe\u001e,GM\u00117pG.\u001ch)\u001a;dQ\u0016$\u0017A\u0007:f[>$X-T3sO\u0016$'\t\\8dWN4U\r^2iK\u0012\u0004\u0013\u0001\u00077pG\u0006dW*\u001a:hK\u0012\u0014En\\2lg\u001a+Go\u00195fI\u0006IBn\\2bY6+'oZ3e\u00052|7m[:GKR\u001c\u0007.\u001a3!\u0003e\u0011X-\\8uK6+'oZ3e\u0007\",hn[:GKR\u001c\u0007.\u001a3\u00025I,Wn\u001c;f\u001b\u0016\u0014x-\u001a3DQVt7n\u001d$fi\u000eDW\r\u001a\u0011\u000211|7-\u00197NKJ<W\rZ\"ik:\\7OR3uG\",G-A\rm_\u000e\fG.T3sO\u0016$7\t[;oWN4U\r^2iK\u0012\u0004\u0013!\u0006:f[>$X-T3sO\u0016$')\u001f;fgJ+\u0017\rZ\u0001\u0017e\u0016lw\u000e^3NKJ<W\r\u001a\"zi\u0016\u001c(+Z1eA\u0005!Bn\\2bY6+'oZ3e\u0005f$Xm\u001d*fC\u0012\fQ\u0003\\8dC2lUM]4fI\nKH/Z:SK\u0006$\u0007%\u0001\rsK6|G/Z'fe\u001e,GMU3rg\u0012+(/\u0019;j_:\f\u0011D]3n_R,W*\u001a:hK\u0012\u0014V-]:EkJ\fG/[8oA\u00051A(\u001b8jiz\"\"\"Q\"E\u000b\u001a;\u0005*\u0013&L!\t\u0011\u0005!D\u0001\u0016\u0011\u0015I3\u00031\u0001,\u0011\u0015y3\u00031\u0001,\u0011\u0015\t4\u00031\u0001,\u0011\u0015\u00194\u00031\u0001,\u0011\u0015)4\u00031\u0001,\u0011\u001594\u00031\u0001,\u0011\u0015I4\u00031\u0001,\u0011\u0015Y4\u00031\u0001,\u0011\u0015i4\u00031\u0001,\u0001"
)
public class ShufflePushReadMetrics {
   private final long corruptMergedBlockChunks;
   private final long mergedFetchFallbackCount;
   private final long remoteMergedBlocksFetched;
   private final long localMergedBlocksFetched;
   private final long remoteMergedChunksFetched;
   private final long localMergedChunksFetched;
   private final long remoteMergedBytesRead;
   private final long localMergedBytesRead;
   private final long remoteMergedReqsDuration;

   public long corruptMergedBlockChunks() {
      return this.corruptMergedBlockChunks;
   }

   public long mergedFetchFallbackCount() {
      return this.mergedFetchFallbackCount;
   }

   public long remoteMergedBlocksFetched() {
      return this.remoteMergedBlocksFetched;
   }

   public long localMergedBlocksFetched() {
      return this.localMergedBlocksFetched;
   }

   public long remoteMergedChunksFetched() {
      return this.remoteMergedChunksFetched;
   }

   public long localMergedChunksFetched() {
      return this.localMergedChunksFetched;
   }

   public long remoteMergedBytesRead() {
      return this.remoteMergedBytesRead;
   }

   public long localMergedBytesRead() {
      return this.localMergedBytesRead;
   }

   public long remoteMergedReqsDuration() {
      return this.remoteMergedReqsDuration;
   }

   public ShufflePushReadMetrics(final long corruptMergedBlockChunks, final long mergedFetchFallbackCount, final long remoteMergedBlocksFetched, final long localMergedBlocksFetched, final long remoteMergedChunksFetched, final long localMergedChunksFetched, final long remoteMergedBytesRead, final long localMergedBytesRead, final long remoteMergedReqsDuration) {
      this.corruptMergedBlockChunks = corruptMergedBlockChunks;
      this.mergedFetchFallbackCount = mergedFetchFallbackCount;
      this.remoteMergedBlocksFetched = remoteMergedBlocksFetched;
      this.localMergedBlocksFetched = localMergedBlocksFetched;
      this.remoteMergedChunksFetched = remoteMergedChunksFetched;
      this.localMergedChunksFetched = localMergedChunksFetched;
      this.remoteMergedBytesRead = remoteMergedBytesRead;
      this.localMergedBytesRead = localMergedBytesRead;
      this.remoteMergedReqsDuration = remoteMergedReqsDuration;
   }
}
