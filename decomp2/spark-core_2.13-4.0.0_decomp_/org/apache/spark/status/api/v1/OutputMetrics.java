package org.apache.spark.status.api.v1;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2AAB\u0004\u0001)!A1\u0004\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005!\u0001\t\u0005\t\u0015!\u0003\u001e\u0011!\t\u0003A!b\u0001\n\u0003a\u0002\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011B\u000f\t\r\r\u0002A\u0011A\u0007%\u00055yU\u000f\u001e9vi6+GO]5dg*\u0011\u0001\"C\u0001\u0003mFR!AC\u0006\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\r\u001b\u000511\u000f^1ukNT!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\u0002\u0001'\t\u0001Q\u0003\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VMZ\u0001\rEf$Xm],sSR$XM\\\u000b\u0002;A\u0011aCH\u0005\u0003?]\u0011A\u0001T8oO\u0006i!-\u001f;fg^\u0013\u0018\u000e\u001e;f]\u0002\naB]3d_J$7o\u0016:jiR,g.A\bsK\u000e|'\u000fZ:Xe&$H/\u001a8!\u0003\u0019a\u0014N\\5u}Q\u0019Qe\n\u0015\u0011\u0005\u0019\u0002Q\"A\u0004\t\u000bm)\u0001\u0019A\u000f\t\u000b\u0005*\u0001\u0019A\u000f"
)
public class OutputMetrics {
   private final long bytesWritten;
   private final long recordsWritten;

   public long bytesWritten() {
      return this.bytesWritten;
   }

   public long recordsWritten() {
      return this.recordsWritten;
   }

   public OutputMetrics(final long bytesWritten, final long recordsWritten) {
      this.bytesWritten = bytesWritten;
      this.recordsWritten = recordsWritten;
   }
}
