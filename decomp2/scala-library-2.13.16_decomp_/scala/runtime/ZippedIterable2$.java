package scala.runtime;

import scala.collection.AbstractIterable;
import scala.collection.Iterable;
import scala.collection.Iterator;

/** @deprecated */
public final class ZippedIterable2$ {
   public static final ZippedIterable2$ MODULE$ = new ZippedIterable2$();

   public Iterable zippedIterable2ToIterable(final ZippedIterable2 zz) {
      return new AbstractIterable(zz) {
         private final ZippedIterable2 zz$1;

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

   private ZippedIterable2$() {
   }
}
