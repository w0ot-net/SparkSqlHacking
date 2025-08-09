package org.apache.curator.shaded.com.google.common.collect;

import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class AbstractSequentialIterator extends UnmodifiableIterator {
   @CheckForNull
   private Object nextOrNull;

   protected AbstractSequentialIterator(@CheckForNull Object firstOrNull) {
      this.nextOrNull = firstOrNull;
   }

   @CheckForNull
   protected abstract Object computeNext(Object previous);

   public final boolean hasNext() {
      return this.nextOrNull != null;
   }

   public final Object next() {
      if (this.nextOrNull == null) {
         throw new NoSuchElementException();
      } else {
         T oldNext = (T)this.nextOrNull;
         this.nextOrNull = this.computeNext(oldNext);
         return oldNext;
      }
   }
}
