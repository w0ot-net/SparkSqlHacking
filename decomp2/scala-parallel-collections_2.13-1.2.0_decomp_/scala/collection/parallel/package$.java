package scala.collection.parallel;

import scala.Function1;
import scala.math.package.;
import scala.runtime.BoxedUnit;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final int MIN_FOR_COPY = 512;
   private static final int CHECK_RATE = 512;
   private static final double SQRT2;
   private static final int availableProcessors;
   private static final TaskSupport defaultTaskSupport;

   static {
      SQRT2 = .MODULE$.sqrt((double)2.0F);
      availableProcessors = Runtime.getRuntime().availableProcessors();
      defaultTaskSupport = new ExecutionContextTaskSupport(ExecutionContextTaskSupport$.MODULE$.$lessinit$greater$default$1());
   }

   public int MIN_FOR_COPY() {
      return MIN_FOR_COPY;
   }

   public int CHECK_RATE() {
      return CHECK_RATE;
   }

   public double SQRT2() {
      return SQRT2;
   }

   public int availableProcessors() {
      return availableProcessors;
   }

   public int thresholdFromSize(final int sz, final int parallelismLevel) {
      return parallelismLevel > 1 ? 1 + sz / (8 * parallelismLevel) : sz;
   }

   public TaskSupport defaultTaskSupport() {
      return defaultTaskSupport;
   }

   public Object setTaskSupport(final Object c, final TaskSupport t) {
      if (c instanceof ParIterableLike) {
         ParIterableLike var5 = (ParIterableLike)c;
         var5.tasksupport_$eq(t);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      return c;
   }

   public package.CollectionsHaveToParArray CollectionsHaveToParArray(final Object c, final Function1 asGto) {
      return new package.CollectionsHaveToParArray(c, asGto);
   }

   private package$() {
   }
}
