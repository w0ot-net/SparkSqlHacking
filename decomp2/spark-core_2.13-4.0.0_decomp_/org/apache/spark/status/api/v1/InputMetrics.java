package org.apache.spark.status.api.v1;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2AAB\u0004\u0001)!A1\u0004\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005!\u0001\t\u0005\t\u0015!\u0003\u001e\u0011!\t\u0003A!b\u0001\n\u0003a\u0002\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011B\u000f\t\r\r\u0002A\u0011A\u0007%\u00051Ie\u000e];u\u001b\u0016$(/[2t\u0015\tA\u0011\"\u0001\u0002wc)\u0011!bC\u0001\u0004CBL'B\u0001\u0007\u000e\u0003\u0019\u0019H/\u0019;vg*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001)\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g-A\u0005csR,7OU3bIV\tQ\u0004\u0005\u0002\u0017=%\u0011qd\u0006\u0002\u0005\u0019>tw-\u0001\u0006csR,7OU3bI\u0002\n1B]3d_J$7OU3bI\u0006a!/Z2pe\u0012\u001c(+Z1eA\u00051A(\u001b8jiz\"2!J\u0014)!\t1\u0003!D\u0001\b\u0011\u0015YR\u00011\u0001\u001e\u0011\u0015\tS\u00011\u0001\u001e\u0001"
)
public class InputMetrics {
   private final long bytesRead;
   private final long recordsRead;

   public long bytesRead() {
      return this.bytesRead;
   }

   public long recordsRead() {
      return this.recordsRead;
   }

   public InputMetrics(final long bytesRead, final long recordsRead) {
      this.bytesRead = bytesRead;
      this.recordsRead = recordsRead;
   }
}
