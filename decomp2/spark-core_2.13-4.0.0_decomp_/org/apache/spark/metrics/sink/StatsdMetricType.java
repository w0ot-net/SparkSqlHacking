package org.apache.spark.metrics.sink;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q:aa\u0003\u0007\t\u0002A1bA\u0002\r\r\u0011\u0003\u0001\u0012\u0004C\u0003!\u0003\u0011\u0005!\u0005C\u0004$\u0003\t\u0007I\u0011\u0001\u0013\t\r5\n\u0001\u0015!\u0003&\u0011\u001dq\u0013A1A\u0005\u0002\u0011BaaL\u0001!\u0002\u0013)\u0003b\u0002\u0019\u0002\u0005\u0004%\t\u0001\n\u0005\u0007c\u0005\u0001\u000b\u0011B\u0013\t\u000fI\n!\u0019!C\u0001I!11'\u0001Q\u0001\n\u0015\n\u0001c\u0015;biN$W*\u001a;sS\u000e$\u0016\u0010]3\u000b\u00055q\u0011\u0001B:j].T!a\u0004\t\u0002\u000f5,GO]5dg*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014x\r\u0005\u0002\u0018\u00035\tAB\u0001\tTi\u0006$8\u000fZ'fiJL7\rV=qKN\u0011\u0011A\u0007\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012AF\u0001\b\u0007>+f\nV#S+\u0005)\u0003C\u0001\u0014,\u001b\u00059#B\u0001\u0015*\u0003\u0011a\u0017M\\4\u000b\u0003)\nAA[1wC&\u0011Af\n\u0002\u0007'R\u0014\u0018N\\4\u0002\u0011\r{UK\u0014+F%\u0002\nQaR!V\u000f\u0016\u000baaR!V\u000f\u0016\u0003\u0013!\u0002+J\u001b\u0016\u0013\u0016A\u0002+J\u001b\u0016\u0013\u0006%A\u0002TKR\fAaU3uA\u0001"
)
public final class StatsdMetricType {
   public static String Set() {
      return StatsdMetricType$.MODULE$.Set();
   }

   public static String TIMER() {
      return StatsdMetricType$.MODULE$.TIMER();
   }

   public static String GAUGE() {
      return StatsdMetricType$.MODULE$.GAUGE();
   }

   public static String COUNTER() {
      return StatsdMetricType$.MODULE$.COUNTER();
   }
}
