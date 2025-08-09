package algebra.instances.list;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ!G\u0001\u0005\u0002i\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005!A.[:u\u0015\t9\u0001\"A\u0005j]N$\u0018M\\2fg*\t\u0011\"A\u0004bY\u001e,'M]1\u0004\u0001A\u0011A\"A\u0007\u0002\t\t9\u0001/Y2lC\u001e,7cA\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u0004\"AF\f\u000e\u0003\u0019I!\u0001\u0007\u0004\u0003\u001b1K7\u000f^%ogR\fgnY3t\u0003\u0019a\u0014N\\5u}Q\t1\u0002"
)
public final class package {
   public static Monoid catsKernelStdMonoidForList() {
      return package$.MODULE$.catsKernelStdMonoidForList();
   }

   public static Order catsKernelStdOrderForList(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForList(evidence$1);
   }

   public static Hash catsKernelStdHashForList(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForList(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForList(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForList(evidence$2);
   }

   public static Eq catsKernelStdEqForList(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForList(evidence$4);
   }
}
