package cats.kernel.instances.either;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u9Qa\u0001\u0003\t\u000251Qa\u0004\u0003\t\u0002AAQaG\u0001\u0005\u0002q\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u00051Q-\u001b;iKJT!a\u0002\u0005\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0005\u000b\u0003\u0019YWM\u001d8fY*\t1\"\u0001\u0003dCR\u001c8\u0001\u0001\t\u0003\u001d\u0005i\u0011\u0001\u0002\u0002\ba\u0006\u001c7.Y4f'\r\t\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005aIR\"\u0001\u0004\n\u0005i1!aD#ji\",'/\u00138ti\u0006t7-Z:\u0002\rqJg.\u001b;?)\u0005i\u0001"
)
public final class package {
   public static Monoid catsDataMonoidForEither(final Monoid B) {
      return package$.MODULE$.catsDataMonoidForEither(B);
   }

   public static Order catsStdOrderForEither(final Order A, final Order B) {
      return package$.MODULE$.catsStdOrderForEither(A, B);
   }

   public static Hash catsStdHashForEither(final Hash A, final Hash B) {
      return package$.MODULE$.catsStdHashForEither(A, B);
   }

   public static PartialOrder catsStdPartialOrderForEither(final PartialOrder A, final PartialOrder B) {
      return package$.MODULE$.catsStdPartialOrderForEither(A, B);
   }

   public static Semigroup catsDataSemigroupForEither(final Semigroup B) {
      return package$.MODULE$.catsDataSemigroupForEither(B);
   }

   public static Eq catsStdEqForEither(final Eq A, final Eq B) {
      return package$.MODULE$.catsStdEqForEither(A, B);
   }
}
