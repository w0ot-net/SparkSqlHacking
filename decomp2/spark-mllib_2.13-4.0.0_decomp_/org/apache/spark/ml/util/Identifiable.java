package org.apache.spark.ml.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2q\u0001C\u0005\u0011\u0002\u0007\u0005A\u0003C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0004!\u0001\t\u0007i\u0011A\u0011\t\u000b5\u0002A\u0011\t\u0018\b\u000b=J\u0001\u0012\u0001\u0019\u0007\u000b!I\u0001\u0012\u0001\u001a\t\u000bM*A\u0011\u0001\u001b\t\u000bU*A\u0011\u0001\u001c\u0003\u0019%#WM\u001c;jM&\f'\r\\3\u000b\u0005)Y\u0011\u0001B;uS2T!\u0001D\u0007\u0002\u00055d'B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0004\u0001M\u0011\u0001!\u0006\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005i\u0002C\u0001\f\u001f\u0013\tyrC\u0001\u0003V]&$\u0018aA;jIV\t!\u0005\u0005\u0002$U9\u0011A\u0005\u000b\t\u0003K]i\u0011A\n\u0006\u0003OM\ta\u0001\u0010:p_Rt\u0014BA\u0015\u0018\u0003\u0019\u0001&/\u001a3fM&\u00111\u0006\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005%:\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\t\nA\"\u00133f]RLg-[1cY\u0016\u0004\"!M\u0003\u000e\u0003%\u0019\"!B\u000b\u0002\rqJg.\u001b;?)\u0005\u0001\u0014!\u0003:b]\u0012|W.V%E)\t\u0011s\u0007C\u00039\u000f\u0001\u0007!%\u0001\u0004qe\u00164\u0017\u000e\u001f"
)
public interface Identifiable {
   static String randomUID(final String prefix) {
      return Identifiable$.MODULE$.randomUID(prefix);
   }

   String uid();

   // $FF: synthetic method
   static String toString$(final Identifiable $this) {
      return $this.toString();
   }

   default String toString() {
      return this.uid();
   }

   static void $init$(final Identifiable $this) {
   }
}
