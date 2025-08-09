package spire.optional;

import cats.kernel.Group;
import cats.kernel.Semigroup;
import scala.collection.Factory;
import spire.algebra.partial.Groupoid;
import spire.algebra.partial.Semigroupoid;

public final class partialIterable$ implements PartialIterable1 {
   public static final partialIterable$ MODULE$ = new partialIterable$();

   static {
      PartialIterable0.$init$(MODULE$);
      PartialIterable1.$init$(MODULE$);
   }

   public Groupoid IterableGroupoid(final Group evidence$2, final Factory cbf) {
      return PartialIterable1.IterableGroupoid$(this, evidence$2, cbf);
   }

   public Semigroupoid IterableSemigroupoid(final Semigroup evidence$1, final Factory cbf) {
      return PartialIterable0.IterableSemigroupoid$(this, evidence$1, cbf);
   }

   private partialIterable$() {
   }
}
