package scala.runtime;

import scala.collection.AbstractIterable;
import scala.collection.Iterable;
import scala.collection.Iterator;

/** @deprecated */
public final class ZippedIterable3$ {
   public static final ZippedIterable3$ MODULE$ = new ZippedIterable3$();

   public Iterable zippedIterable3ToIterable(final ZippedIterable3 zz) {
      return new AbstractIterable(zz) {
         private final ZippedIterable3 zz$1;

         public Iterator iterator() {
            return this.zz$1.iterator();
         }

         public boolean isEmpty() {
            return this.zz$1.isEmpty();
         }

         public {
            this.zz$1 = zz$1;
         }
      };
   }

   private ZippedIterable3$() {
   }
}
