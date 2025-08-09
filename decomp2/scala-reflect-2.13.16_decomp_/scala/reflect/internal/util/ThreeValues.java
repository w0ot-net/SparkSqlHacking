package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001;Q\u0001D\u0007\t\u0002Y1Q\u0001G\u0007\t\u0002eAQAH\u0001\u0005\u0002})A\u0001I\u0001\u0001C!9A%\u0001b\u0001\n\u000b)\u0003B\u0002\u0015\u0002A\u00035a\u0005C\u0004*\u0003\t\u0007IQ\u0001\u0016\t\r5\n\u0001\u0015!\u0004,\u0011\u001dq\u0013A1A\u0005\u0006=BaAM\u0001!\u0002\u001b\u0001\u0004\"B\u001a\u0002\t\u0003!\u0004\"\u0002\u001f\u0002\t\u0003i\u0014a\u0003+ie\u0016,g+\u00197vKNT!AD\b\u0002\tU$\u0018\u000e\u001c\u0006\u0003!E\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003%M\tqA]3gY\u0016\u001cGOC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"aF\u0001\u000e\u00035\u00111\u0002\u00165sK\u00164\u0016\r\\;fgN\u0011\u0011A\u0007\t\u00037qi\u0011aE\u0005\u0003;M\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0017\u0005)!\u0006N]3f-\u0006dW/\u001a\t\u00037\tJ!aI\n\u0003\t\tKH/Z\u0001\u00043\u0016\u001bV#\u0001\u0014\u0010\u0003\u001dj\u0012!A\u0001\u00053\u0016\u001b\u0006%\u0001\u0002O\u001fV\t1fD\u0001-;\u0005y a\u0001(PA\u00059QKT&O\u001f^sU#\u0001\u0019\u0010\u0003Ej\u0012\u0001A\u0001\t+:[ejT,OA\u0005YaM]8n\u0005>|G.Z1o)\t)t\u0007\u0005\u00027\u00075\t\u0011\u0001C\u00039\u0015\u0001\u0007\u0011(A\u0001c!\tY\"(\u0003\u0002<'\t9!i\\8mK\u0006t\u0017!\u0003;p\u0005>|G.Z1o)\tId\bC\u0003@\u0017\u0001\u0007Q'A\u0001y\u0001"
)
public final class ThreeValues {
   public static boolean toBoolean(final byte x) {
      return ThreeValues$.MODULE$.toBoolean(x);
   }

   public static byte fromBoolean(final boolean b) {
      return ThreeValues$.MODULE$.fromBoolean(b);
   }

   public static int UNKNOWN() {
      return ThreeValues$.MODULE$.UNKNOWN();
   }

   public static int NO() {
      return ThreeValues$.MODULE$.NO();
   }

   public static int YES() {
      return ThreeValues$.MODULE$.YES();
   }
}
