package org.apache.spark.executor;

import org.apache.spark.shuffle.ShuffleReadMetricsReporter;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc!B\u001b7\u0001ar\u0004\"B&\u0001\t\u0003i\u0005B\u0002)\u0001A\u0003&\u0011\u000b\u0003\u0004U\u0001\u0001\u0006K!\u0015\u0005\u0007+\u0002\u0001\u000b\u0015B)\t\rY\u0003\u0001\u0015)\u0003R\u0011\u00199\u0006\u0001)Q\u0005#\"1\u0001\f\u0001Q!\nECa!\u0017\u0001!B\u0013\t\u0006B\u0002.\u0001A\u0003&\u0011\u000b\u0003\u0004\\\u0001\u0001\u0006K!\u0015\u0005\u00079\u0002\u0001\u000b\u0015B)\t\ru\u0003\u0001\u0015)\u0003R\u0011\u0019q\u0006\u0001)Q\u0005#\"1q\f\u0001Q!\nECa\u0001\u0019\u0001!B\u0013\t\u0006BB1\u0001A\u0003&\u0011\u000b\u0003\u0004c\u0001\u0001\u0006K!\u0015\u0005\u0007G\u0002\u0001\u000b\u0015B)\t\u000b\u0011\u0004A\u0011I3\t\u000b-\u0004A\u0011\t7\t\u000b9\u0004A\u0011I8\t\u000bE\u0004A\u0011\t:\t\u000bQ\u0004A\u0011I;\t\u000b]\u0004A\u0011\t=\t\u000bi\u0004A\u0011I>\t\u000bu\u0004A\u0011\t@\t\u000f\u0005\u0005\u0001\u0001\"\u0011\u0002\u0004!9\u0011q\u0001\u0001\u0005B\u0005%\u0001bBA\u0007\u0001\u0011\u0005\u0013q\u0002\u0005\b\u0003'\u0001A\u0011IA\u000b\u0011\u001d\tI\u0002\u0001C!\u00037Aq!a\b\u0001\t\u0003\n\t\u0003C\u0004\u0002&\u0001!\t%a\n\t\u000f\u0005-\u0002\u0001\"\u0011\u0002.!9\u0011\u0011\u0007\u0001\u0005B\u0005M\u0002bBA\u001c\u0001\u0011\u0005\u0011\u0011\b\u0005\b\u0003w\u0001A\u0011AA\u001d\u0011\u001d\ti\u0004\u0001C\u0001\u0003sAq!a\u0010\u0001\t\u0003\tI\u0004C\u0004\u0002B\u0001!\t!!\u000f\t\u000f\u0005\r\u0003\u0001\"\u0001\u0002:!9\u0011Q\t\u0001\u0005\u0002\u0005e\u0002bBA$\u0001\u0011\u0005\u0011\u0011\b\u0005\b\u0003\u0013\u0002A\u0011AA\u001d\u0011\u001d\tY\u0005\u0001C\u0001\u0003sAq!!\u0014\u0001\t\u0003\tI\u0004C\u0004\u0002P\u0001!\t!!\u000f\t\u000f\u0005E\u0003\u0001\"\u0001\u0002:!9\u00111\u000b\u0001\u0005\u0002\u0005e\u0002bBA+\u0001\u0011\u0005\u0011\u0011\b\u0005\b\u0003/\u0002A\u0011AA\u001d\u0011\u001d\tI\u0006\u0001C\u0001\u0003s\u0011a\u0003V3naNCWO\u001a4mKJ+\u0017\rZ'fiJL7m\u001d\u0006\u0003oa\n\u0001\"\u001a=fGV$xN\u001d\u0006\u0003si\nQa\u001d9be.T!a\u000f\u001f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0014aA8sON\u0019\u0001aP#\u0011\u0005\u0001\u001bU\"A!\u000b\u0003\t\u000bQa]2bY\u0006L!\u0001R!\u0003\r\u0005s\u0017PU3g!\t1\u0015*D\u0001H\u0015\tA\u0005(A\u0004tQV4g\r\\3\n\u0005);%AG*ik\u001a4G.\u001a*fC\u0012lU\r\u001e:jGN\u0014V\r]8si\u0016\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u00039\u0003\"a\u0014\u0001\u000e\u0003Y\nAc\u0018:f[>$XM\u00117pG.\u001ch)\u001a;dQ\u0016$\u0007C\u0001!S\u0013\t\u0019\u0016I\u0001\u0003M_:<\u0017aE0m_\u000e\fGN\u00117pG.\u001ch)\u001a;dQ\u0016$\u0017\u0001E0sK6|G/\u001a\"zi\u0016\u001c(+Z1e\u0003Yy&/Z7pi\u0016\u0014\u0015\u0010^3t%\u0016\fG\rV8ESN\\\u0017aD0m_\u000e\fGNQ=uKN\u0014V-\u00193\u0002\u001d}3W\r^2i/\u0006LG\u000fV5nK\u0006aqL]3d_J$7OU3bI\u0006IrlY8seV\u0004H/T3sO\u0016$'\t\\8dW\u000eCWO\\6t\u0003eyV.\u001a:hK\u00124U\r^2i\r\u0006dGNY1dW\u000e{WO\u001c;\u00025}\u0013X-\\8uK6+'oZ3e\u00052|7m[:GKR\u001c\u0007.\u001a3\u00023}cwnY1m\u001b\u0016\u0014x-\u001a3CY>\u001c7n\u001d$fi\u000eDW\rZ\u0001\u001b?J,Wn\u001c;f\u001b\u0016\u0014x-\u001a3DQVt7n\u001d$fi\u000eDW\rZ\u0001\u001a?2|7-\u00197NKJ<W\rZ\"ik:\\7OR3uG\",G-\u0001\f`e\u0016lw\u000e^3NKJ<W\r\u001a\"zi\u0016\u001c(+Z1e\u0003UyFn\\2bY6+'oZ3e\u0005f$Xm\u001d*fC\u0012\f1c\u0018:f[>$XMU3rg\u0012+(/\u0019;j_:\f\u0011d\u0018:f[>$X-T3sO\u0016$'+Z9t\tV\u0014\u0018\r^5p]\u00061\u0012N\\2SK6|G/\u001a\"m_\u000e\\7OR3uG\",G\r\u0006\u0002gSB\u0011\u0001iZ\u0005\u0003Q\u0006\u0013A!\u00168ji\")!n\u0005a\u0001#\u0006\ta/A\u000bj]\u000edunY1m\u00052|7m[:GKR\u001c\u0007.\u001a3\u0015\u0005\u0019l\u0007\"\u00026\u0015\u0001\u0004\t\u0016AE5oGJ+Wn\u001c;f\u0005f$Xm\u001d*fC\u0012$\"A\u001a9\t\u000b),\u0002\u0019A)\u00021%t7MU3n_R,')\u001f;fgJ+\u0017\r\u001a+p\t&\u001c8\u000e\u0006\u0002gg\")!N\u0006a\u0001#\u0006\t\u0012N\\2M_\u000e\fGNQ=uKN\u0014V-\u00193\u0015\u0005\u00194\b\"\u00026\u0018\u0001\u0004\t\u0016\u0001E5oG\u001a+Go\u00195XC&$H+[7f)\t1\u0017\u0010C\u0003k1\u0001\u0007\u0011+\u0001\bj]\u000e\u0014VmY8sIN\u0014V-\u00193\u0015\u0005\u0019d\b\"\u00026\u001a\u0001\u0004\t\u0016aG5oG\u000e{'O];qi6+'oZ3e\u00052|7m[\"ik:\\7\u000f\u0006\u0002g\u007f\")!N\u0007a\u0001#\u0006Y\u0012N\\2NKJ<W\r\u001a$fi\u000eDg)\u00197mE\u0006\u001c7nQ8v]R$2AZA\u0003\u0011\u0015Q7\u00041\u0001R\u0003qIgn\u0019*f[>$X-T3sO\u0016$'\t\\8dWN4U\r^2iK\u0012$2AZA\u0006\u0011\u0015QG\u00041\u0001R\u0003mIgn\u0019'pG\u0006dW*\u001a:hK\u0012\u0014En\\2lg\u001a+Go\u00195fIR\u0019a-!\u0005\t\u000b)l\u0002\u0019A)\u00029%t7MU3n_R,W*\u001a:hK\u0012\u001c\u0005.\u001e8lg\u001a+Go\u00195fIR\u0019a-a\u0006\t\u000b)t\u0002\u0019A)\u00027%t7\rT8dC2lUM]4fI\u000eCWO\\6t\r\u0016$8\r[3e)\r1\u0017Q\u0004\u0005\u0006U~\u0001\r!U\u0001\u0019S:\u001c'+Z7pi\u0016lUM]4fI\nKH/Z:SK\u0006$Gc\u00014\u0002$!)!\u000e\ta\u0001#\u00069\u0012N\\2M_\u000e\fG.T3sO\u0016$')\u001f;fgJ+\u0017\r\u001a\u000b\u0004M\u0006%\u0002\"\u00026\"\u0001\u0004\t\u0016!F5oGJ+Wn\u001c;f%\u0016\f8\u000fR;sCRLwN\u001c\u000b\u0004M\u0006=\u0002\"\u00026#\u0001\u0004\t\u0016aG5oGJ+Wn\u001c;f\u001b\u0016\u0014x-\u001a3SKF\u001cH)\u001e:bi&|g\u000eF\u0002g\u0003kAQA[\u0012A\u0002E\u000b1C]3n_R,'\t\\8dWN4U\r^2iK\u0012,\u0012!U\u0001\u0013Y>\u001c\u0017\r\u001c\"m_\u000e\\7OR3uG\",G-A\bsK6|G/\u001a\"zi\u0016\u001c(+Z1e\u0003U\u0011X-\\8uK\nKH/Z:SK\u0006$Gk\u001c#jg.\fa\u0002\\8dC2\u0014\u0015\u0010^3t%\u0016\fG-A\u0007gKR\u001c\u0007nV1jiRKW.Z\u0001\fe\u0016\u001cwN\u001d3t%\u0016\fG-\u0001\rd_J\u0014X\u000f\u001d;NKJ<W\r\u001a\"m_\u000e\\7\t[;oWN\f\u0001$\\3sO\u0016$g)\u001a;dQ\u001a\u000bG\u000e\u001c2bG.\u001cu.\u001e8u\u0003e\u0011X-\\8uK6+'oZ3e\u00052|7m[:GKR\u001c\u0007.\u001a3\u000211|7-\u00197NKJ<W\r\u001a\"m_\u000e\\7OR3uG\",G-A\rsK6|G/Z'fe\u001e,Gm\u00115v].\u001ch)\u001a;dQ\u0016$\u0017\u0001\u00077pG\u0006dW*\u001a:hK\u0012\u001c\u0005.\u001e8lg\u001a+Go\u00195fI\u0006)\"/Z7pi\u0016lUM]4fI\nKH/Z:SK\u0006$\u0017\u0001\u00067pG\u0006dW*\u001a:hK\u0012\u0014\u0015\u0010^3t%\u0016\fG-\u0001\nsK6|G/\u001a*fcN$UO]1uS>t\u0017\u0001\u0007:f[>$X-T3sO\u0016$'+Z9t\tV\u0014\u0018\r^5p]\u0002"
)
public class TempShuffleReadMetrics implements ShuffleReadMetricsReporter {
   private long _remoteBlocksFetched = 0L;
   private long _localBlocksFetched = 0L;
   private long _remoteBytesRead = 0L;
   private long _remoteBytesReadToDisk = 0L;
   private long _localBytesRead = 0L;
   private long _fetchWaitTime = 0L;
   private long _recordsRead = 0L;
   private long _corruptMergedBlockChunks = 0L;
   private long _mergedFetchFallbackCount = 0L;
   private long _remoteMergedBlocksFetched = 0L;
   private long _localMergedBlocksFetched = 0L;
   private long _remoteMergedChunksFetched = 0L;
   private long _localMergedChunksFetched = 0L;
   private long _remoteMergedBytesRead = 0L;
   private long _localMergedBytesRead = 0L;
   private long _remoteReqsDuration = 0L;
   private long _remoteMergedReqsDuration = 0L;

   public void incRemoteBlocksFetched(final long v) {
      this._remoteBlocksFetched += v;
   }

   public void incLocalBlocksFetched(final long v) {
      this._localBlocksFetched += v;
   }

   public void incRemoteBytesRead(final long v) {
      this._remoteBytesRead += v;
   }

   public void incRemoteBytesReadToDisk(final long v) {
      this._remoteBytesReadToDisk += v;
   }

   public void incLocalBytesRead(final long v) {
      this._localBytesRead += v;
   }

   public void incFetchWaitTime(final long v) {
      this._fetchWaitTime += v;
   }

   public void incRecordsRead(final long v) {
      this._recordsRead += v;
   }

   public void incCorruptMergedBlockChunks(final long v) {
      this._corruptMergedBlockChunks += v;
   }

   public void incMergedFetchFallbackCount(final long v) {
      this._mergedFetchFallbackCount += v;
   }

   public void incRemoteMergedBlocksFetched(final long v) {
      this._remoteMergedBlocksFetched += v;
   }

   public void incLocalMergedBlocksFetched(final long v) {
      this._localMergedBlocksFetched += v;
   }

   public void incRemoteMergedChunksFetched(final long v) {
      this._remoteMergedChunksFetched += v;
   }

   public void incLocalMergedChunksFetched(final long v) {
      this._localMergedChunksFetched += v;
   }

   public void incRemoteMergedBytesRead(final long v) {
      this._remoteMergedBytesRead += v;
   }

   public void incLocalMergedBytesRead(final long v) {
      this._localMergedBytesRead += v;
   }

   public void incRemoteReqsDuration(final long v) {
      this._remoteReqsDuration += v;
   }

   public void incRemoteMergedReqsDuration(final long v) {
      this._remoteMergedReqsDuration += v;
   }

   public long remoteBlocksFetched() {
      return this._remoteBlocksFetched;
   }

   public long localBlocksFetched() {
      return this._localBlocksFetched;
   }

   public long remoteBytesRead() {
      return this._remoteBytesRead;
   }

   public long remoteBytesReadToDisk() {
      return this._remoteBytesReadToDisk;
   }

   public long localBytesRead() {
      return this._localBytesRead;
   }

   public long fetchWaitTime() {
      return this._fetchWaitTime;
   }

   public long recordsRead() {
      return this._recordsRead;
   }

   public long corruptMergedBlockChunks() {
      return this._corruptMergedBlockChunks;
   }

   public long mergedFetchFallbackCount() {
      return this._mergedFetchFallbackCount;
   }

   public long remoteMergedBlocksFetched() {
      return this._remoteMergedBlocksFetched;
   }

   public long localMergedBlocksFetched() {
      return this._localMergedBlocksFetched;
   }

   public long remoteMergedChunksFetched() {
      return this._remoteMergedChunksFetched;
   }

   public long localMergedChunksFetched() {
      return this._localMergedChunksFetched;
   }

   public long remoteMergedBytesRead() {
      return this._remoteMergedBytesRead;
   }

   public long localMergedBytesRead() {
      return this._localMergedBytesRead;
   }

   public long remoteReqsDuration() {
      return this._remoteReqsDuration;
   }

   public long remoteMergedReqsDuration() {
      return this._remoteMergedReqsDuration;
   }
}
