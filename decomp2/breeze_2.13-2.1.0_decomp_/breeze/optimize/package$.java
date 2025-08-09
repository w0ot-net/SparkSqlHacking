package breeze.optimize;

import scala.collection.Iterator;
import scala.collection.immutable.Seq;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public Object minimize(final Object fn, final Object init, final Seq options, final OptimizationPackage optimization) {
      return optimization.minimize(fn, init, options);
   }

   public Iterator iterations(final Object fn, final Object init, final Seq options, final IterableOptimizationPackage optimization) {
      return optimization.iterations(fn, init, options);
   }

   private package$() {
   }
}
