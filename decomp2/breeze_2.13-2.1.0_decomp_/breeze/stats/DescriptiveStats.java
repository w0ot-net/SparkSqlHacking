package breeze.stats;

import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.math.Fractional;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001<Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQaF\u0001\u0005\u0002aAQ!G\u0001\u0005\u0002iAQAL\u0001\u0005\u0002=BQAN\u0001\u0005\u0002]BQAU\u0001\u0005\u0002M\u000b\u0001\u0003R3tGJL\u0007\u000f^5wKN#\u0018\r^:\u000b\u0005%Q\u0011!B:uCR\u001c(\"A\u0006\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AD\u0001\u000e\u0003!\u0011\u0001\u0003R3tGJL\u0007\u000f^5wKN#\u0018\r^:\u0014\u0005\u0005\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002\u001b\u0005Q\u0001/\u001a:dK:$\u0018\u000e\\3\u0015\u0007mqB\u0006\u0005\u0002\u00139%\u0011Qd\u0005\u0002\u0007\t>,(\r\\3\t\u000b}\u0019\u0001\u0019\u0001\u0011\u0002\u0005%$\bcA\u0011*79\u0011!e\n\b\u0003G\u0019j\u0011\u0001\n\u0006\u0003K1\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000b\n\u0005!\u001a\u0012a\u00029bG.\fw-Z\u0005\u0003U-\u0012q\u0002\u0016:bm\u0016\u00148/\u00192mK>s7-\u001a\u0006\u0003QMAQ!L\u0002A\u0002m\t\u0011\u0001]\u0001\u0012a\u0016\u00148-\u001a8uS2,\u0017J\u001c)mC\u000e,GcA\u000e1k!)\u0011\u0007\u0002a\u0001e\u0005\u0019\u0011M\u001d:\u0011\u0007I\u00194$\u0003\u00025'\t)\u0011I\u001d:bs\")Q\u0006\u0002a\u00017\u0005QQ.Z1o\u0003:$7i\u001c<\u0016\u0005azDcA\u001dN!R\u0011!\b\u0013\t\u0006%mjT(R\u0005\u0003yM\u0011a\u0001V;qY\u0016\u001c\u0004C\u0001 @\u0019\u0001!Q\u0001Q\u0003C\u0002\u0005\u0013\u0011\u0001V\t\u0003\u0005\u0016\u0003\"AE\"\n\u0005\u0011\u001b\"a\u0002(pi\"Lgn\u001a\t\u0003%\u0019K!aR\n\u0003\u0007\u0005s\u0017\u0010C\u0003J\u000b\u0001\u000f!*\u0001\u0003ge\u0006\u001c\u0007cA\u0011L{%\u0011Aj\u000b\u0002\u000b\rJ\f7\r^5p]\u0006d\u0007\"\u0002(\u0006\u0001\u0004y\u0015aA5ucA\u0019\u0011%K\u001f\t\u000bE+\u0001\u0019A(\u0002\u0007%$('A\u0002d_Z,\"\u0001\u0016.\u0015\u0007U[v\f\u0006\u0002F-\")qK\u0002a\u00021\u0006\ta\u000eE\u0002\"\u0017f\u0003\"A\u0010.\u0005\u000b\u00013!\u0019A!\t\u000b93\u0001\u0019\u0001/\u0011\u0007\u0005j\u0016,\u0003\u0002_W\tA\u0011\n^3sC\ndW\rC\u0003R\r\u0001\u0007A\f"
)
public final class DescriptiveStats {
   public static Object cov(final Iterable it1, final Iterable it2, final Fractional n) {
      return DescriptiveStats$.MODULE$.cov(it1, it2, n);
   }

   public static Tuple3 meanAndCov(final IterableOnce it1, final IterableOnce it2, final Fractional frac) {
      return DescriptiveStats$.MODULE$.meanAndCov(it1, it2, frac);
   }

   public static double percentileInPlace(final double[] arr, final double p) {
      return DescriptiveStats$.MODULE$.percentileInPlace(arr, p);
   }

   public static double percentile(final IterableOnce it, final double p) {
      return DescriptiveStats$.MODULE$.percentile(it, p);
   }
}
