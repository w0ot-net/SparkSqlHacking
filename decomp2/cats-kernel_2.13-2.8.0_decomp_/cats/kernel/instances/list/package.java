package cats.kernel.instances.list;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u9Qa\u0001\u0003\t\u000251Qa\u0004\u0003\t\u0002AAQaG\u0001\u0005\u0002q\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005!A.[:u\u0015\t9\u0001\"A\u0005j]N$\u0018M\\2fg*\u0011\u0011BC\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003-\tAaY1ug\u000e\u0001\u0001C\u0001\b\u0002\u001b\u0005!!a\u00029bG.\fw-Z\n\u0004\u0003E9\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\r\u0005\u0002\u001935\ta!\u0003\u0002\u001b\r\tiA*[:u\u0013:\u001cH/\u00198dKN\fa\u0001P5oSRtD#A\u0007"
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
