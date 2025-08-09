package org.apache.spark.status.api.v1;

import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a3A\u0001F\u000b\u0001E!A\u0011\u0006\u0001BC\u0002\u0013\u0005!\u0006\u0003\u0005;\u0001\t\u0005\t\u0015!\u0003,\u0011!Y\u0004A!b\u0001\n\u0003Q\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\u0011u\u0002!Q1A\u0005\u0002)B\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\t\u007f\u0001\u0011)\u0019!C\u0001U!A\u0001\t\u0001B\u0001B\u0003%1\u0006\u0003\u0005B\u0001\t\u0015\r\u0011\"\u0001+\u0011!\u0011\u0005A!A!\u0002\u0013Y\u0003\u0002C\"\u0001\u0005\u000b\u0007I\u0011\u0001\u0016\t\u0011\u0011\u0003!\u0011!Q\u0001\n-B\u0001\"\u0012\u0001\u0003\u0006\u0004%\tA\u000b\u0005\t\r\u0002\u0011\t\u0011)A\u0005W!Aq\t\u0001BC\u0002\u0013\u0005!\u0006\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003,\u0011!I\u0005A!b\u0001\n\u0003Q\u0003\u0002\u0003&\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\r-\u0003A\u0011A\u000eM\u0005\t\u001a\u0006.\u001e4gY\u0016\u0004Vo\u001d5SK\u0006$W*\u001a;sS\u000e$\u0015n\u001d;sS\n,H/[8og*\u0011acF\u0001\u0003mFR!\u0001G\r\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u001b7\u000511\u000f^1ukNT!\u0001H\u000f\u0002\u000bM\u0004\u0018M]6\u000b\u0005yy\u0012AB1qC\u000eDWMC\u0001!\u0003\ry'oZ\u0002\u0001'\t\u00011\u0005\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VMZ\u0001\u0019G>\u0014(/\u001e9u\u001b\u0016\u0014x-\u001a3CY>\u001c7n\u00115v].\u001cX#A\u0016\u0011\u00071\"tG\u0004\u0002.e9\u0011a&M\u0007\u0002_)\u0011\u0001'I\u0001\u0007yI|w\u000e\u001e \n\u0003\u0019J!aM\u0013\u0002\u000fA\f7m[1hK&\u0011QG\u000e\u0002\u000b\u0013:$W\r_3e'\u0016\f(BA\u001a&!\t!\u0003(\u0003\u0002:K\t1Ai\\;cY\u0016\f\u0011dY8seV\u0004H/T3sO\u0016$'\t\\8dW\u000eCWO\\6tA\u0005AR.\u001a:hK\u00124U\r^2i\r\u0006dGNY1dW\u000e{WO\u001c;\u000235,'oZ3e\r\u0016$8\r\u001b$bY2\u0014\u0017mY6D_VtG\u000fI\u0001\u001ae\u0016lw\u000e^3NKJ<W\r\u001a\"m_\u000e\\7OR3uG\",G-\u0001\u000esK6|G/Z'fe\u001e,GM\u00117pG.\u001ch)\u001a;dQ\u0016$\u0007%\u0001\rm_\u000e\fG.T3sO\u0016$'\t\\8dWN4U\r^2iK\u0012\f\u0011\u0004\\8dC2lUM]4fI\ncwnY6t\r\u0016$8\r[3eA\u0005I\"/Z7pi\u0016lUM]4fI\u000eCWO\\6t\r\u0016$8\r[3e\u0003i\u0011X-\\8uK6+'oZ3e\u0007\",hn[:GKR\u001c\u0007.\u001a3!\u0003aawnY1m\u001b\u0016\u0014x-\u001a3DQVt7n\u001d$fi\u000eDW\rZ\u0001\u001aY>\u001c\u0017\r\\'fe\u001e,Gm\u00115v].\u001ch)\u001a;dQ\u0016$\u0007%A\u000bsK6|G/Z'fe\u001e,GMQ=uKN\u0014V-\u00193\u0002-I,Wn\u001c;f\u001b\u0016\u0014x-\u001a3CsR,7OU3bI\u0002\nA\u0003\\8dC2lUM]4fI\nKH/Z:SK\u0006$\u0017!\u00067pG\u0006dW*\u001a:hK\u0012\u0014\u0015\u0010^3t%\u0016\fG\rI\u0001\u0019e\u0016lw\u000e^3NKJ<W\r\u001a*fcN$UO]1uS>t\u0017!\u0007:f[>$X-T3sO\u0016$'+Z9t\tV\u0014\u0018\r^5p]\u0002\na\u0001P5oSRtDCC'P!F\u00136\u000bV+W/B\u0011a\nA\u0007\u0002+!)\u0011f\u0005a\u0001W!)1h\u0005a\u0001W!)Qh\u0005a\u0001W!)qh\u0005a\u0001W!)\u0011i\u0005a\u0001W!)1i\u0005a\u0001W!)Qi\u0005a\u0001W!)qi\u0005a\u0001W!)\u0011j\u0005a\u0001W\u0001"
)
public class ShufflePushReadMetricDistributions {
   private final IndexedSeq corruptMergedBlockChunks;
   private final IndexedSeq mergedFetchFallbackCount;
   private final IndexedSeq remoteMergedBlocksFetched;
   private final IndexedSeq localMergedBlocksFetched;
   private final IndexedSeq remoteMergedChunksFetched;
   private final IndexedSeq localMergedChunksFetched;
   private final IndexedSeq remoteMergedBytesRead;
   private final IndexedSeq localMergedBytesRead;
   private final IndexedSeq remoteMergedReqsDuration;

   public IndexedSeq corruptMergedBlockChunks() {
      return this.corruptMergedBlockChunks;
   }

   public IndexedSeq mergedFetchFallbackCount() {
      return this.mergedFetchFallbackCount;
   }

   public IndexedSeq remoteMergedBlocksFetched() {
      return this.remoteMergedBlocksFetched;
   }

   public IndexedSeq localMergedBlocksFetched() {
      return this.localMergedBlocksFetched;
   }

   public IndexedSeq remoteMergedChunksFetched() {
      return this.remoteMergedChunksFetched;
   }

   public IndexedSeq localMergedChunksFetched() {
      return this.localMergedChunksFetched;
   }

   public IndexedSeq remoteMergedBytesRead() {
      return this.remoteMergedBytesRead;
   }

   public IndexedSeq localMergedBytesRead() {
      return this.localMergedBytesRead;
   }

   public IndexedSeq remoteMergedReqsDuration() {
      return this.remoteMergedReqsDuration;
   }

   public ShufflePushReadMetricDistributions(final IndexedSeq corruptMergedBlockChunks, final IndexedSeq mergedFetchFallbackCount, final IndexedSeq remoteMergedBlocksFetched, final IndexedSeq localMergedBlocksFetched, final IndexedSeq remoteMergedChunksFetched, final IndexedSeq localMergedChunksFetched, final IndexedSeq remoteMergedBytesRead, final IndexedSeq localMergedBytesRead, final IndexedSeq remoteMergedReqsDuration) {
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
