package cats.kernel.instances.either;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.instances.EitherInstances;
import cats.kernel.instances.EitherInstances0;
import cats.kernel.instances.EitherInstances1;

public final class package$ implements EitherInstances {
   public static final package$ MODULE$ = new package$();

   static {
      EitherInstances1.$init$(MODULE$);
      EitherInstances0.$init$(MODULE$);
      EitherInstances.$init$(MODULE$);
   }

   public Order catsStdOrderForEither(final Order A, final Order B) {
      return EitherInstances.catsStdOrderForEither$(this, A, B);
   }

   public Monoid catsDataMonoidForEither(final Monoid B) {
      return EitherInstances.catsDataMonoidForEither$(this, B);
   }

   public Semigroup catsDataSemigroupForEither(final Semigroup B) {
      return EitherInstances0.catsDataSemigroupForEither$(this, B);
   }

   public PartialOrder catsStdPartialOrderForEither(final PartialOrder A, final PartialOrder B) {
      return EitherInstances0.catsStdPartialOrderForEither$(this, A, B);
   }

   public Hash catsStdHashForEither(final Hash A, final Hash B) {
      return EitherInstances0.catsStdHashForEither$(this, A, B);
   }

   public Eq catsStdEqForEither(final Eq A, final Eq B) {
      return EitherInstances1.catsStdEqForEither$(this, A, B);
   }

   private package$() {
   }
}
