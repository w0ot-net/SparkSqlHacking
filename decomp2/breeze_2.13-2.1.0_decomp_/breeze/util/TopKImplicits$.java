package breeze.util;

import scala.collection.Iterable;
import scala.collection.Iterator;

public final class TopKImplicits$ {
   public static final TopKImplicits$ MODULE$ = new TopKImplicits$();

   public TopKIterable iTopKIterable(final Iterable iterable) {
      return new TopKIterable(iterable);
   }

   public TopKIterator iTopKIterator(final Iterator iterator) {
      return new TopKIterator(iterator);
   }

   private TopKImplicits$() {
   }
}
