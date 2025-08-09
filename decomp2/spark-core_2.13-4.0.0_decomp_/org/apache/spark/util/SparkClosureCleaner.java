package org.apache.spark.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]:aAB\u0004\t\u0002%yaAB\t\b\u0011\u0003I!\u0003C\u0003\u001a\u0003\u0011\u00051\u0004C\u0003\u001d\u0003\u0011\u0005Q\u0004C\u0004+\u0003E\u0005I\u0011A\u0016\t\u000fY\n\u0011\u0013!C\u0001W\u0005\u00192\u000b]1sW\u000ecwn];sK\u000ecW-\u00198fe*\u0011\u0001\"C\u0001\u0005kRLGN\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h!\t\u0001\u0012!D\u0001\b\u0005M\u0019\u0006/\u0019:l\u00072|7/\u001e:f\u00072,\u0017M\\3s'\t\t1\u0003\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq\"A\u0003dY\u0016\fg\u000e\u0006\u0003\u001fC\rB\u0003C\u0001\u000b \u0013\t\u0001SC\u0001\u0003V]&$\b\"\u0002\u0012\u0004\u0001\u0004\u0019\u0012aB2m_N,(/\u001a\u0005\bI\r\u0001\n\u00111\u0001&\u0003E\u0019\u0007.Z2l'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\t\u0003)\u0019J!aJ\u000b\u0003\u000f\t{w\u000e\\3b]\"9\u0011f\u0001I\u0001\u0002\u0004)\u0013!E2mK\u0006tGK]1og&$\u0018N^3ms\u0006y1\r\\3b]\u0012\"WMZ1vYR$#'F\u0001-U\t)SfK\u0001/!\tyC'D\u00011\u0015\t\t$'A\u0005v]\u000eDWmY6fI*\u00111'F\u0001\u000bC:tw\u000e^1uS>t\u0017BA\u001b1\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0010G2,\u0017M\u001c\u0013eK\u001a\fW\u000f\u001c;%g\u0001"
)
public final class SparkClosureCleaner {
   public static boolean clean$default$3() {
      return SparkClosureCleaner$.MODULE$.clean$default$3();
   }

   public static boolean clean$default$2() {
      return SparkClosureCleaner$.MODULE$.clean$default$2();
   }

   public static void clean(final Object closure, final boolean checkSerializable, final boolean cleanTransitively) {
      SparkClosureCleaner$.MODULE$.clean(closure, checkSerializable, cleanTransitively);
   }
}
