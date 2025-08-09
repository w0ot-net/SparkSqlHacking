package cats.kernel.instances.lazyList;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u9Qa\u0001\u0003\t\u000251Qa\u0004\u0003\t\u0002AAQaG\u0001\u0005\u0002q\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005AA.\u0019>z\u0019&\u001cHO\u0003\u0002\b\u0011\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0013)\taa[3s]\u0016d'\"A\u0006\u0002\t\r\fGo]\u0002\u0001!\tq\u0011!D\u0001\u0005\u0005\u001d\u0001\u0018mY6bO\u0016\u001c2!A\t\u0018!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0011\u0001$G\u0007\u0002\r%\u0011!D\u0002\u0002\u0012\u0019\u0006T\u0018\u0010T5ti&s7\u000f^1oG\u0016\u001c\u0018A\u0002\u001fj]&$h\bF\u0001\u000e\u0001"
)
public final class package {
   public static Monoid catsKernelStdMonoidForLazyList() {
      return package$.MODULE$.catsKernelStdMonoidForLazyList();
   }

   public static Order catsKernelStdOrderForLazyList(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForLazyList(evidence$1);
   }

   public static Hash catsKernelStdHashForLazyList(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForLazyList(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForLazyList(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForLazyList(evidence$2);
   }

   public static Eq catsKernelStdEqForLazyList(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForLazyList(evidence$4);
   }
}
