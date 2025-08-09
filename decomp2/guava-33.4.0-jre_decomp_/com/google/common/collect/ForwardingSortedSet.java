package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingSortedSet extends ForwardingSet implements SortedSet {
   protected ForwardingSortedSet() {
   }

   protected abstract SortedSet delegate();

   @CheckForNull
   public Comparator comparator() {
      return this.delegate().comparator();
   }

   @ParametricNullness
   public Object first() {
      return this.delegate().first();
   }

   public SortedSet headSet(@ParametricNullness Object toElement) {
      return this.delegate().headSet(toElement);
   }

   @ParametricNullness
   public Object last() {
      return this.delegate().last();
   }

   public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
      return this.delegate().subSet(fromElement, toElement);
   }

   public SortedSet tailSet(@ParametricNullness Object fromElement) {
      return this.delegate().tailSet(fromElement);
   }

   protected boolean standardContains(@CheckForNull Object object) {
      try {
         Object ceiling = this.tailSet(object).first();
         return ForwardingSortedMap.unsafeCompare(this.comparator(), ceiling, object) == 0;
      } catch (NoSuchElementException | NullPointerException | ClassCastException var4) {
         return false;
      }
   }

   protected boolean standardRemove(@CheckForNull Object object) {
      try {
         Iterator<?> iterator = this.tailSet(object).iterator();
         if (iterator.hasNext()) {
            Object ceiling = iterator.next();
            if (ForwardingSortedMap.unsafeCompare(this.comparator(), ceiling, object) == 0) {
               iterator.remove();
               return true;
            }
         }

         return false;
      } catch (NullPointerException | ClassCastException var5) {
         return false;
      }
   }

   protected SortedSet standardSubSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
      return this.tailSet(fromElement).headSet(toElement);
   }
}
