package org.apache.spark.ml.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M:a!\u0002\u0004\t\u0002!\u0001bA\u0002\n\u0007\u0011\u0003A1\u0003C\u0003\u001b\u0003\u0011\u0005A\u0004C\u0003\u001e\u0003\u0011\u0005a\u0004C\u00030\u0003\u0011\u0005\u0001'A\nKg>tg+Z2u_J\u001cuN\u001c<feR,'O\u0003\u0002\b\u0011\u00051A.\u001b8bY\u001eT!!\u0003\u0006\u0002\u00055d'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0011\u0005E\tQ\"\u0001\u0004\u0003')\u001bxN\u001c,fGR|'oQ8om\u0016\u0014H/\u001a:\u0014\u0005\u0005!\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\u0001\u0012\u0001\u00034s_6T5o\u001c8\u0015\u0005}\u0011\u0003CA\t!\u0013\t\tcA\u0001\u0004WK\u000e$xN\u001d\u0005\u0006G\r\u0001\r\u0001J\u0001\u0005UN|g\u000e\u0005\u0002&Y9\u0011aE\u000b\t\u0003OYi\u0011\u0001\u000b\u0006\u0003Sm\ta\u0001\u0010:p_Rt\u0014BA\u0016\u0017\u0003\u0019\u0001&/\u001a3fM&\u0011QF\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005-2\u0012A\u0002;p\u0015N|g\u000e\u0006\u0002%c!)!\u0007\u0002a\u0001?\u0005\ta\u000f"
)
public final class JsonVectorConverter {
   public static String toJson(final Vector v) {
      return JsonVectorConverter$.MODULE$.toJson(v);
   }

   public static Vector fromJson(final String json) {
      return JsonVectorConverter$.MODULE$.fromJson(json);
   }
}
