package cats.kernel.instances.queue;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u9Qa\u0001\u0003\t\u000251Qa\u0004\u0003\t\u0002AAQaG\u0001\u0005\u0002q\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005)\u0011/^3vK*\u0011q\u0001C\u0001\nS:\u001cH/\u00198dKNT!!\u0003\u0006\u0002\r-,'O\\3m\u0015\u0005Y\u0011\u0001B2biN\u001c\u0001\u0001\u0005\u0002\u000f\u00035\tAAA\u0004qC\u000e\\\u0017mZ3\u0014\u0007\u0005\tr\u0003\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VM\u001a\t\u00031ei\u0011AB\u0005\u00035\u0019\u0011a\"U;fk\u0016Len\u001d;b]\u000e,7/\u0001\u0004=S:LGO\u0010\u000b\u0002\u001b\u0001"
)
public final class package {
   public static Monoid catsKernelStdMonoidForQueue() {
      return package$.MODULE$.catsKernelStdMonoidForQueue();
   }

   public static Order catsKernelStdOrderForQueue(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForQueue(evidence$1);
   }

   public static Hash catsKernelStdHashForQueue(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForQueue(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForQueue(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForQueue(evidence$2);
   }

   public static Eq catsKernelStdEqForQueue(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForQueue(evidence$4);
   }
}
