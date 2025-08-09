package org.apache.spark.ml.stat;

import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a;Q!\u0002\u0004\t\u0002E1Qa\u0005\u0004\t\u0002QAQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0002yAQ!H\u0001\u0005\u0002)\u000bQb\u00115j'F,\u0018M]3UKN$(BA\u0004\t\u0003\u0011\u0019H/\u0019;\u000b\u0005%Q\u0011AA7m\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<7\u0001\u0001\t\u0003%\u0005i\u0011A\u0002\u0002\u000e\u0007\"L7+];be\u0016$Vm\u001d;\u0014\u0005\u0005)\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002#\u0005!A/Z:u)\u0011y2'N \u0011\u0005\u0001\u0002dBA\u0011.\u001d\t\u00113F\u0004\u0002$U9\u0011A%\u000b\b\u0003K!j\u0011A\n\u0006\u0003OA\ta\u0001\u0010:p_Rt\u0014\"A\b\n\u00055q\u0011BA\u0006\r\u0013\ta#\"A\u0002tc2L!AL\u0018\u0002\u000fA\f7m[1hK*\u0011AFC\u0005\u0003cI\u0012\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u00059z\u0003\"\u0002\u001b\u0004\u0001\u0004y\u0012a\u00023bi\u0006\u001cX\r\u001e\u0005\u0006m\r\u0001\raN\u0001\fM\u0016\fG/\u001e:fg\u000e{G\u000e\u0005\u00029y9\u0011\u0011H\u000f\t\u0003K]I!aO\f\u0002\rA\u0013X\rZ3g\u0013\tidH\u0001\u0004TiJLgn\u001a\u0006\u0003w]AQ\u0001Q\u0002A\u0002]\n\u0001\u0002\\1cK2\u001cu\u000e\u001c\u0015\u0004\u0007\tC\u0005CA\"G\u001b\u0005!%BA#\u000b\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u000f\u0012\u0013QaU5oG\u0016\f\u0013!S\u0001\u0006e9\u0012d\u0006\r\u000b\u0006?-cUJ\u0014\u0005\u0006i\u0011\u0001\ra\b\u0005\u0006m\u0011\u0001\ra\u000e\u0005\u0006\u0001\u0012\u0001\ra\u000e\u0005\u0006\u001f\u0012\u0001\r\u0001U\u0001\bM2\fG\u000f^3o!\t1\u0012+\u0003\u0002S/\t9!i\\8mK\u0006t\u0007f\u0001\u0003C)\u0006\nQ+A\u00034]Er\u0003\u0007K\u0002\u0002\u0005\"C3\u0001\u0001\"I\u0001"
)
public final class ChiSquareTest {
   public static Dataset test(final Dataset dataset, final String featuresCol, final String labelCol, final boolean flatten) {
      return ChiSquareTest$.MODULE$.test(dataset, featuresCol, labelCol, flatten);
   }

   public static Dataset test(final Dataset dataset, final String featuresCol, final String labelCol) {
      return ChiSquareTest$.MODULE$.test(dataset, featuresCol, labelCol);
   }
}
