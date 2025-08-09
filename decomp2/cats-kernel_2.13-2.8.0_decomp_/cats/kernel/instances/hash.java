package cats.kernel.instances;

import cats.kernel.Hash;
import scala.reflect.ScalaSignature;
import scala.util.hashing.Hashing;

@ScalaSignature(
   bytes = "\u0006\u0005i9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ\u0001G\u0001\u0005\u0002e\tA\u0001[1tQ*\u0011QAB\u0001\nS:\u001cH/\u00198dKNT!a\u0002\u0005\u0002\r-,'O\\3m\u0015\u0005I\u0011\u0001B2biN\u001c\u0001\u0001\u0005\u0002\r\u00035\tAA\u0001\u0003iCND7cA\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u0004\"\u0001\u0004\f\n\u0005]!!!\u0004%bg\"Len\u001d;b]\u000e,7/\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017\u0001"
)
public final class hash {
   public static Hashing catsKernelHashToHashing(final Hash ev) {
      return hash$.MODULE$.catsKernelHashToHashing(ev);
   }
}
