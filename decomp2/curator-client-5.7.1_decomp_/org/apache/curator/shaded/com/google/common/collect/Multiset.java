package org.apache.curator.shaded.com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.CompatibleWith;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Multiset extends Collection {
   int size();

   int count(@CheckForNull @CompatibleWith("E") Object element);

   @CanIgnoreReturnValue
   int add(@ParametricNullness Object element, int occurrences);

   @CanIgnoreReturnValue
   boolean add(@ParametricNullness Object element);

   @CanIgnoreReturnValue
   int remove(@CheckForNull @CompatibleWith("E") Object element, int occurrences);

   @CanIgnoreReturnValue
   boolean remove(@CheckForNull Object element);

   @CanIgnoreReturnValue
   int setCount(@ParametricNullness Object element, int count);

   @CanIgnoreReturnValue
   boolean setCount(@ParametricNullness Object element, int oldCount, int newCount);

   Set elementSet();

   Set entrySet();

   default void forEachEntry(ObjIntConsumer action) {
      Preconditions.checkNotNull(action);
      this.entrySet().forEach((entry) -> action.accept(entry.getElement(), entry.getCount()));
   }

   boolean equals(@CheckForNull Object object);

   int hashCode();

   String toString();

   Iterator iterator();

   boolean contains(@CheckForNull Object element);

   boolean containsAll(Collection elements);

   @CanIgnoreReturnValue
   boolean removeAll(Collection c);

   @CanIgnoreReturnValue
   boolean retainAll(Collection c);

   default void forEach(Consumer action) {
      Preconditions.checkNotNull(action);
      this.entrySet().forEach((entry) -> {
         E elem = (E)entry.getElement();
         int count = entry.getCount();

         for(int i = 0; i < count; ++i) {
            action.accept(elem);
         }

      });
   }

   default Spliterator spliterator() {
      return Multisets.spliteratorImpl(this);
   }

   public interface Entry {
      @ParametricNullness
      Object getElement();

      int getCount();

      boolean equals(@CheckForNull Object o);

      int hashCode();

      String toString();
   }
}
