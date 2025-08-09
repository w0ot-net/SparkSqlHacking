package org.apache.spark;

import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005Q2A!\u0002\u0004\u0001\u001b!AA\u0004\u0001BC\u0002\u0013\u0005Q\u0004\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003\u001f\u0011\u00159\u0003\u0001\"\u0001)\u0011\u00159\u0003\u0001\"\u0001-\u0005M!\u0016m]6LS2dW\rZ#yG\u0016\u0004H/[8o\u0015\t9\u0001\"A\u0003ta\u0006\u00148N\u0003\u0002\n\u0015\u00051\u0011\r]1dQ\u0016T\u0011aC\u0001\u0004_J<7\u0001A\n\u0003\u00019\u0001\"aD\r\u000f\u0005A1bBA\t\u0015\u001b\u0005\u0011\"BA\n\r\u0003\u0019a$o\\8u}%\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u00181\u00059\u0001/Y2lC\u001e,'\"A\u000b\n\u0005iY\"\u0001\u0005*v]RLW.Z#yG\u0016\u0004H/[8o\u0015\t9\u0002$\u0001\u0004sK\u0006\u001cxN\\\u000b\u0002=A\u0011qd\t\b\u0003A\u0005\u0002\"!\u0005\r\n\u0005\tB\u0012A\u0002)sK\u0012,g-\u0003\u0002%K\t11\u000b\u001e:j]\u001eT!A\t\r\u0002\u000fI,\u0017m]8oA\u00051A(\u001b8jiz\"\"!K\u0016\u0011\u0005)\u0002Q\"\u0001\u0004\t\u000bq\u0019\u0001\u0019\u0001\u0010\u0015\u0003%B#\u0001\u0001\u0018\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0005E2\u0011AC1o]>$\u0018\r^5p]&\u00111\u0007\r\u0002\r\t\u00164X\r\\8qKJ\f\u0005/\u001b"
)
public class TaskKilledException extends RuntimeException {
   private final String reason;

   public String reason() {
      return this.reason;
   }

   public TaskKilledException(final String reason) {
      this.reason = reason;
   }

   public TaskKilledException() {
      this("unknown reason");
   }
}
