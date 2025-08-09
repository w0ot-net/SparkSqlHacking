package cats.kernel.instances.arraySeq;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.instances.ArraySeqInstances;

public final class package$ implements ArraySeqInstances {
   public static final package$ MODULE$ = new package$();

   static {
      ArraySeqInstances.ArraySeqInstances2.$init$(MODULE$);
      ArraySeqInstances.ArraySeqInstances1.$init$(MODULE$);
      ArraySeqInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForArraySeq(final Order evidence$1) {
      return ArraySeqInstances.catsKernelStdOrderForArraySeq$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForArraySeq() {
      return ArraySeqInstances.catsKernelStdMonoidForArraySeq$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForArraySeq(final PartialOrder evidence$2) {
      return ArraySeqInstances.ArraySeqInstances1.catsKernelStdPartialOrderForArraySeq$(this, evidence$2);
   }

   public Hash catsKernelStdHashForArraySeq(final Hash evidence$3) {
      return ArraySeqInstances.ArraySeqInstances1.catsKernelStdHashForArraySeq$(this, evidence$3);
   }

   public Eq catsKernelStdEqForArraySeq(final Eq evidence$4) {
      return ArraySeqInstances.ArraySeqInstances2.catsKernelStdEqForArraySeq$(this, evidence$4);
   }

   private package$() {
   }
}
