package scala.collection.parallel;

import java.util.concurrent.ForkJoinPool;

public final class ForkJoinTaskSupport$ {
   public static final ForkJoinTaskSupport$ MODULE$ = new ForkJoinTaskSupport$();

   public ForkJoinPool $lessinit$greater$default$1() {
      return ForkJoinTasks$.MODULE$.defaultForkJoinPool();
   }

   private ForkJoinTaskSupport$() {
   }
}
