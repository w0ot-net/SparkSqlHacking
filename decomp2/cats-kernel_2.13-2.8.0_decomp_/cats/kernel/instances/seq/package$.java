package cats.kernel.instances.seq;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.instances.SeqInstances;
import cats.kernel.instances.SeqInstances1;
import cats.kernel.instances.SeqInstances2;

public final class package$ implements SeqInstances {
   public static final package$ MODULE$ = new package$();

   static {
      SeqInstances2.$init$(MODULE$);
      SeqInstances1.$init$(MODULE$);
      SeqInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForSeq(final Order evidence$1) {
      return SeqInstances.catsKernelStdOrderForSeq$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForSeq() {
      return SeqInstances.catsKernelStdMonoidForSeq$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForSeq(final PartialOrder evidence$2) {
      return SeqInstances1.catsKernelStdPartialOrderForSeq$(this, evidence$2);
   }

   public Hash catsKernelStdHashForSeq(final Hash evidence$3) {
      return SeqInstances1.catsKernelStdHashForSeq$(this, evidence$3);
   }

   public Eq catsKernelStdEqForSeq(final Eq evidence$4) {
      return SeqInstances2.catsKernelStdEqForSeq$(this, evidence$4);
   }

   private package$() {
   }
}
