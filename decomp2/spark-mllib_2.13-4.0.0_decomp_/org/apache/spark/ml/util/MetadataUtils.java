package org.apache.spark.ml.util;

import org.apache.spark.sql.types.StructField;
import scala.Option;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E;aa\u0002\u0005\t\u00021\u0011bA\u0002\u000b\t\u0011\u0003aQ\u0003C\u0003\u001d\u0003\u0011\u0005a\u0004C\u0003 \u0003\u0011\u0005\u0001\u0005C\u00032\u0003\u0011\u0005!\u0007C\u00036\u0003\u0011\u0005a\u0007C\u0003E\u0003\u0011\u0005Q)A\u0007NKR\fG-\u0019;b+RLGn\u001d\u0006\u0003\u0013)\tA!\u001e;jY*\u00111\u0002D\u0001\u0003[2T!!\u0004\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0001\u0012AB1qC\u000eDWMC\u0001\u0012\u0003\ry'o\u001a\t\u0003'\u0005i\u0011\u0001\u0003\u0002\u000e\u001b\u0016$\u0018\rZ1uCV#\u0018\u000e\\:\u0014\u0005\u00051\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\u0011\u0012!D4fi:+Xn\u00117bgN,7\u000f\u0006\u0002\"OA\u0019qC\t\u0013\n\u0005\rB\"AB(qi&|g\u000e\u0005\u0002\u0018K%\u0011a\u0005\u0007\u0002\u0004\u0013:$\b\"\u0002\u0015\u0004\u0001\u0004I\u0013a\u00037bE\u0016d7k\u00195f[\u0006\u0004\"AK\u0018\u000e\u0003-R!\u0001L\u0017\u0002\u000bQL\b/Z:\u000b\u00059b\u0011aA:rY&\u0011\u0001g\u000b\u0002\f'R\u0014Xo\u0019;GS\u0016dG-\u0001\bhKRtU/\u001c$fCR,(/Z:\u0015\u0005\u0005\u001a\u0004\"\u0002\u001b\u0005\u0001\u0004I\u0013\u0001\u0004<fGR|'oU2iK6\f\u0017AF4fi\u000e\u000bG/Z4pe&\u001c\u0017\r\u001c$fCR,(/Z:\u0015\u0005]\u0012\u0005\u0003\u0002\u001d@I\u0011r!!O\u001f\u0011\u0005iBR\"A\u001e\u000b\u0005qj\u0012A\u0002\u001fs_>$h(\u0003\u0002?1\u00051\u0001K]3eK\u001aL!\u0001Q!\u0003\u00075\u000b\u0007O\u0003\u0002?1!)1)\u0002a\u0001S\u0005qa-Z1ukJ,7oU2iK6\f\u0017AG4fi\u001a+\u0017\r^;sK&sG-[2fg\u001a\u0013x.\u001c(b[\u0016\u001cHc\u0001$J\u0017B\u0019qc\u0012\u0013\n\u0005!C\"!B!se\u0006L\b\"\u0002&\u0007\u0001\u0004I\u0013aA2pY\")AJ\u0002a\u0001\u001b\u0006)a.Y7fgB\u0019qc\u0012(\u0011\u0005az\u0015B\u0001)B\u0005\u0019\u0019FO]5oO\u0002"
)
public final class MetadataUtils {
   public static int[] getFeatureIndicesFromNames(final StructField col, final String[] names) {
      return MetadataUtils$.MODULE$.getFeatureIndicesFromNames(col, names);
   }

   public static Map getCategoricalFeatures(final StructField featuresSchema) {
      return MetadataUtils$.MODULE$.getCategoricalFeatures(featuresSchema);
   }

   public static Option getNumFeatures(final StructField vectorSchema) {
      return MetadataUtils$.MODULE$.getNumFeatures(vectorSchema);
   }

   public static Option getNumClasses(final StructField labelSchema) {
      return MetadataUtils$.MODULE$.getNumClasses(labelSchema);
   }
}
