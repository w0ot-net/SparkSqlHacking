package algebra.instances.option;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ!G\u0001\u0005\u0002i\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u00051q\u000e\u001d;j_:T!a\u0002\u0005\u0002\u0013%t7\u000f^1oG\u0016\u001c(\"A\u0005\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001\u0001C\u0001\u0007\u0002\u001b\u0005!!a\u00029bG.\fw-Z\n\u0004\u0003=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\r\u0005\u0002\u0017/5\ta!\u0003\u0002\u0019\r\tyq\n\u001d;j_:Len\u001d;b]\u000e,7/\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017\u0001"
)
public final class package {
   public static Monoid catsKernelStdMonoidForOption(final Semigroup evidence$3) {
      return package$.MODULE$.catsKernelStdMonoidForOption(evidence$3);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForOption(final CommutativeSemigroup evidence$2) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForOption(evidence$2);
   }

   public static Order catsKernelStdOrderForOption(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForOption(evidence$1);
   }

   public static PartialOrder catsKernelStdPartialOrderForOption(final PartialOrder evidence$4) {
      return package$.MODULE$.catsKernelStdPartialOrderForOption(evidence$4);
   }

   public static Hash catsKernelStdHashForOption(final Hash evidence$5) {
      return package$.MODULE$.catsKernelStdHashForOption(evidence$5);
   }

   public static Eq catsKernelStdEqForOption(final Eq evidence$6) {
      return package$.MODULE$.catsKernelStdEqForOption(evidence$6);
   }
}
