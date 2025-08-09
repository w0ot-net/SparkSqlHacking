package spire.algebra.partial;

import cats.kernel.Semigroup;

public final class Semigroupoid$ implements SemigroupoidLowPriority {
   public static final Semigroupoid$ MODULE$ = new Semigroupoid$();

   static {
      SemigroupoidLowPriority.$init$(MODULE$);
   }

   public Semigroupoid fromSemigroup(final Semigroup semigroup) {
      return SemigroupoidLowPriority.fromSemigroup$(this, semigroup);
   }

   public final Semigroupoid apply(final Semigroupoid s) {
      return s;
   }

   private Semigroupoid$() {
   }
}
