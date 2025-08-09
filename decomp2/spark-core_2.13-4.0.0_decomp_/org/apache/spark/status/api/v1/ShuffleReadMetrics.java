package org.apache.spark.status.api.v1;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3A\u0001F\u000b\u0001E!A\u0011\u0006\u0001BC\u0002\u0013\u0005!\u0006\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003,\u0011!y\u0003A!b\u0001\n\u0003Q\u0003\u0002\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\u0011E\u0002!Q1A\u0005\u0002)B\u0001B\r\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tg\u0001\u0011)\u0019!C\u0001U!AA\u0007\u0001B\u0001B\u0003%1\u0006\u0003\u00056\u0001\t\u0015\r\u0011\"\u0001+\u0011!1\u0004A!A!\u0002\u0013Y\u0003\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011\u0001\u0016\t\u0011a\u0002!\u0011!Q\u0001\n-B\u0001\"\u000f\u0001\u0003\u0006\u0004%\tA\u000b\u0005\tu\u0001\u0011\t\u0011)A\u0005W!A1\b\u0001BC\u0002\u0013\u0005!\u0006\u0003\u0005=\u0001\t\u0005\t\u0015!\u0003,\u0011!i\u0004A!b\u0001\n\u0003q\u0004\u0002C\"\u0001\u0005\u0003\u0005\u000b\u0011B \t\r\u0011\u0003A\u0011A\u000eF\u0005I\u0019\u0006.\u001e4gY\u0016\u0014V-\u00193NKR\u0014\u0018nY:\u000b\u0005Y9\u0012A\u0001<2\u0015\tA\u0012$A\u0002ba&T!AG\u000e\u0002\rM$\u0018\r^;t\u0015\taR$A\u0003ta\u0006\u00148N\u0003\u0002\u001f?\u00051\u0011\r]1dQ\u0016T\u0011\u0001I\u0001\u0004_J<7\u0001A\n\u0003\u0001\r\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0017a\u0005:f[>$XM\u00117pG.\u001ch)\u001a;dQ\u0016$W#A\u0016\u0011\u0005\u0011b\u0013BA\u0017&\u0005\u0011auN\\4\u0002)I,Wn\u001c;f\u00052|7m[:GKR\u001c\u0007.\u001a3!\u0003IawnY1m\u00052|7m[:GKR\u001c\u0007.\u001a3\u0002'1|7-\u00197CY>\u001c7n\u001d$fi\u000eDW\r\u001a\u0011\u0002\u001b\u0019,Go\u00195XC&$H+[7f\u000391W\r^2i/\u0006LG\u000fV5nK\u0002\nqB]3n_R,')\u001f;fgJ+\u0017\rZ\u0001\u0011e\u0016lw\u000e^3CsR,7OU3bI\u0002\nQC]3n_R,')\u001f;fgJ+\u0017\r\u001a+p\t&\u001c8.\u0001\fsK6|G/\u001a\"zi\u0016\u001c(+Z1e)>$\u0015n]6!\u00039awnY1m\u0005f$Xm\u001d*fC\u0012\fq\u0002\\8dC2\u0014\u0015\u0010^3t%\u0016\fG\rI\u0001\fe\u0016\u001cwN\u001d3t%\u0016\fG-\u0001\u0007sK\u000e|'\u000fZ:SK\u0006$\u0007%\u0001\nsK6|G/\u001a*fcN$UO]1uS>t\u0017a\u0005:f[>$XMU3rg\u0012+(/\u0019;j_:\u0004\u0013AF:ik\u001a4G.\u001a)vg\"\u0014V-\u00193NKR\u0014\u0018nY:\u0016\u0003}\u0002\"\u0001Q!\u000e\u0003UI!AQ\u000b\u0003-MCWO\u001a4mKB+8\u000f\u001b*fC\u0012lU\r\u001e:jGN\fqc\u001d5vM\u001adW\rU;tQJ+\u0017\rZ'fiJL7m\u001d\u0011\u0002\rqJg.\u001b;?))1u\tS%K\u00172kej\u0014\t\u0003\u0001\u0002AQ!K\nA\u0002-BQaL\nA\u0002-BQ!M\nA\u0002-BQaM\nA\u0002-BQ!N\nA\u0002-BQaN\nA\u0002-BQ!O\nA\u0002-BQaO\nA\u0002-BQ!P\nA\u0002}\u0002"
)
public class ShuffleReadMetrics {
   private final long remoteBlocksFetched;
   private final long localBlocksFetched;
   private final long fetchWaitTime;
   private final long remoteBytesRead;
   private final long remoteBytesReadToDisk;
   private final long localBytesRead;
   private final long recordsRead;
   private final long remoteReqsDuration;
   private final ShufflePushReadMetrics shufflePushReadMetrics;

   public long remoteBlocksFetched() {
      return this.remoteBlocksFetched;
   }

   public long localBlocksFetched() {
      return this.localBlocksFetched;
   }

   public long fetchWaitTime() {
      return this.fetchWaitTime;
   }

   public long remoteBytesRead() {
      return this.remoteBytesRead;
   }

   public long remoteBytesReadToDisk() {
      return this.remoteBytesReadToDisk;
   }

   public long localBytesRead() {
      return this.localBytesRead;
   }

   public long recordsRead() {
      return this.recordsRead;
   }

   public long remoteReqsDuration() {
      return this.remoteReqsDuration;
   }

   public ShufflePushReadMetrics shufflePushReadMetrics() {
      return this.shufflePushReadMetrics;
   }

   public ShuffleReadMetrics(final long remoteBlocksFetched, final long localBlocksFetched, final long fetchWaitTime, final long remoteBytesRead, final long remoteBytesReadToDisk, final long localBytesRead, final long recordsRead, final long remoteReqsDuration, final ShufflePushReadMetrics shufflePushReadMetrics) {
      this.remoteBlocksFetched = remoteBlocksFetched;
      this.localBlocksFetched = localBlocksFetched;
      this.fetchWaitTime = fetchWaitTime;
      this.remoteBytesRead = remoteBytesRead;
      this.remoteBytesReadToDisk = remoteBytesReadToDisk;
      this.localBytesRead = localBytesRead;
      this.recordsRead = recordsRead;
      this.remoteReqsDuration = remoteReqsDuration;
      this.shufflePushReadMetrics = shufflePushReadMetrics;
   }
}
