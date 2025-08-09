package cats.kernel.instances.seq;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u9Qa\u0001\u0003\t\u000251Qa\u0004\u0003\t\u0002AAQaG\u0001\u0005\u0002q\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005\u00191/Z9\u000b\u0005\u001dA\u0011!C5ogR\fgnY3t\u0015\tI!\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0017\u0005!1-\u0019;t\u0007\u0001\u0001\"AD\u0001\u000e\u0003\u0011\u0011q\u0001]1dW\u0006<WmE\u0002\u0002#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\r\u001a\u001b\u00051\u0011B\u0001\u000e\u0007\u00051\u0019V-]%ogR\fgnY3t\u0003\u0019a\u0014N\\5u}Q\tQ\u0002"
)
public final class package {
   public static Monoid catsKernelStdMonoidForSeq() {
      return package$.MODULE$.catsKernelStdMonoidForSeq();
   }

   public static Order catsKernelStdOrderForSeq(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForSeq(evidence$1);
   }

   public static Hash catsKernelStdHashForSeq(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForSeq(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForSeq(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForSeq(evidence$2);
   }

   public static Eq catsKernelStdEqForSeq(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForSeq(evidence$4);
   }
}
