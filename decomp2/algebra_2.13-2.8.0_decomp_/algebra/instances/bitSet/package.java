package algebra.instances.bitSet;

import algebra.instances.BitSetAlgebra;
import cats.kernel.BoundedSemilattice;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ!G\u0001\u0005\u0002i\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u00051!-\u001b;TKRT!a\u0002\u0005\u0002\u0013%t7\u000f^1oG\u0016\u001c(\"A\u0005\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001\u0001C\u0001\u0007\u0002\u001b\u0005!!a\u00029bG.\fw-Z\n\u0004\u0003=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\r\u0005\u0002\u0017/5\ta!\u0003\u0002\u0019\r\ty!)\u001b;TKRLen\u001d;b]\u000e,7/\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017\u0001"
)
public final class package {
   public static BitSetAlgebra bitSetAlgebra() {
      return package$.MODULE$.bitSetAlgebra();
   }

   public static BoundedSemilattice catsKernelStdSemilatticeForBitSet() {
      return package$.MODULE$.catsKernelStdSemilatticeForBitSet();
   }

   public static PartialOrder catsKernelStdOrderForBitSet() {
      return package$.MODULE$.catsKernelStdOrderForBitSet();
   }
}
