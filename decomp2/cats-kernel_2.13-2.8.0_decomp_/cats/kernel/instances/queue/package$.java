package cats.kernel.instances.queue;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.instances.QueueInstances;
import cats.kernel.instances.QueueInstances1;
import cats.kernel.instances.QueueInstances2;

public final class package$ implements QueueInstances {
   public static final package$ MODULE$ = new package$();

   static {
      QueueInstances2.$init$(MODULE$);
      QueueInstances1.$init$(MODULE$);
      QueueInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForQueue(final Order evidence$1) {
      return QueueInstances.catsKernelStdOrderForQueue$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForQueue() {
      return QueueInstances.catsKernelStdMonoidForQueue$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForQueue(final PartialOrder evidence$2) {
      return QueueInstances1.catsKernelStdPartialOrderForQueue$(this, evidence$2);
   }

   public Hash catsKernelStdHashForQueue(final Hash evidence$3) {
      return QueueInstances1.catsKernelStdHashForQueue$(this, evidence$3);
   }

   public Eq catsKernelStdEqForQueue(final Eq evidence$4) {
      return QueueInstances2.catsKernelStdEqForQueue$(this, evidence$4);
   }

   private package$() {
   }
}
