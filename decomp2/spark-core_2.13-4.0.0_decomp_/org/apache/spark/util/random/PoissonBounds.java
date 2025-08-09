package org.apache.spark.util.random;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-:aAB\u0004\t\u0002-\tbAB\n\b\u0011\u0003YA\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0003\u001f\u0003\u0011\u0005q\u0004C\u0003&\u0003\u0011\u0005a\u0005C\u0003)\u0003\u0011%\u0011&A\u0007Q_&\u001c8o\u001c8C_VtGm\u001d\u0006\u0003\u0011%\taA]1oI>l'B\u0001\u0006\f\u0003\u0011)H/\u001b7\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u0004\"AE\u0001\u000e\u0003\u001d\u0011Q\u0002U8jgN|gNQ8v]\u0012\u001c8CA\u0001\u0016!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0012\u000359W\r\u001e'po\u0016\u0014(i\\;oIR\u0011\u0001e\t\t\u0003-\u0005J!AI\f\u0003\r\u0011{WO\u00197f\u0011\u0015!3\u00011\u0001!\u0003\u0005\u0019\u0018!D4fiV\u0003\b/\u001a:C_VtG\r\u0006\u0002!O!)A\u0005\u0002a\u0001A\u00051a.^7Ti\u0012$\"\u0001\t\u0016\t\u000b\u0011*\u0001\u0019\u0001\u0011"
)
public final class PoissonBounds {
   public static double getUpperBound(final double s) {
      return PoissonBounds$.MODULE$.getUpperBound(s);
   }

   public static double getLowerBound(final double s) {
      return PoissonBounds$.MODULE$.getLowerBound(s);
   }
}
