package org.apache.spark.status.api.v1;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592A\u0001C\u0005\u0001-!AQ\u0004\u0001BC\u0002\u0013\u0005a\u0004\u0003\u0005#\u0001\t\u0005\t\u0015!\u0003 \u0011!\u0019\u0003A!b\u0001\n\u0003q\u0002\u0002\u0003\u0013\u0001\u0005\u0003\u0005\u000b\u0011B\u0010\t\u0011\u0015\u0002!Q1A\u0005\u0002yA\u0001B\n\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\u0007O\u0001!\ta\u0004\u0015\u0003'MCWO\u001a4mK^\u0013\u0018\u000e^3NKR\u0014\u0018nY:\u000b\u0005)Y\u0011A\u0001<2\u0015\taQ\"A\u0002ba&T!AD\b\u0002\rM$\u0018\r^;t\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7\u0001A\n\u0003\u0001]\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0017\u0001\u00042zi\u0016\u001cxK]5ui\u0016tW#A\u0010\u0011\u0005a\u0001\u0013BA\u0011\u001a\u0005\u0011auN\\4\u0002\u001b\tLH/Z:Xe&$H/\u001a8!\u0003%9(/\u001b;f)&lW-\u0001\u0006xe&$X\rV5nK\u0002\naB]3d_J$7o\u0016:jiR,g.A\bsK\u000e|'\u000fZ:Xe&$H/\u001a8!\u0003\u0019a\u0014N\\5u}Q!\u0011f\u000b\u0017.!\tQ\u0003!D\u0001\n\u0011\u0015ir\u00011\u0001 \u0011\u0015\u0019s\u00011\u0001 \u0011\u0015)s\u00011\u0001 \u0001"
)
public class ShuffleWriteMetrics {
   private final long bytesWritten;
   private final long writeTime;
   private final long recordsWritten;

   public long bytesWritten() {
      return this.bytesWritten;
   }

   public long writeTime() {
      return this.writeTime;
   }

   public long recordsWritten() {
      return this.recordsWritten;
   }

   public ShuffleWriteMetrics(final long bytesWritten, final long writeTime, final long recordsWritten) {
      this.bytesWritten = bytesWritten;
      this.writeTime = writeTime;
      this.recordsWritten = recordsWritten;
   }
}
