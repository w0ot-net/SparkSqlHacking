package spire.optional;

import cats.kernel.Group;
import cats.kernel.Semigroup;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;
import spire.algebra.partial.Groupoid;
import spire.algebra.partial.Semigroupoid;

@ScalaSignature(
   bytes = "\u0006\u0005a9Qa\u0001\u0003\t\u0002%1Qa\u0003\u0003\t\u00021AQAF\u0001\u0005\u0002]\tq\u0002]1si&\fG.\u0013;fe\u0006\u0014G.\u001a\u0006\u0003\u000b\u0019\t\u0001b\u001c9uS>t\u0017\r\u001c\u0006\u0002\u000f\u0005)1\u000f]5sK\u000e\u0001\u0001C\u0001\u0006\u0002\u001b\u0005!!a\u00049beRL\u0017\r\\%uKJ\f'\r\\3\u0014\u0007\u0005i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0015QI!!\u0006\u0003\u0003!A\u000b'\u000f^5bY&#XM]1cY\u0016\f\u0014A\u0002\u001fj]&$h\bF\u0001\n\u0001"
)
public final class partialIterable {
   public static Groupoid IterableGroupoid(final Group evidence$2, final Factory cbf) {
      return partialIterable$.MODULE$.IterableGroupoid(evidence$2, cbf);
   }

   public static Semigroupoid IterableSemigroupoid(final Semigroup evidence$1, final Factory cbf) {
      return partialIterable$.MODULE$.IterableSemigroupoid(evidence$1, cbf);
   }
}
