package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractMultiset extends AbstractCollection implements Multiset {
   @LazyInit
   @CheckForNull
   private transient Set elementSet;
   @LazyInit
   @CheckForNull
   private transient Set entrySet;

   public boolean isEmpty() {
      return this.entrySet().isEmpty();
   }

   public boolean contains(@CheckForNull Object element) {
      return this.count(element) > 0;
   }

   @CanIgnoreReturnValue
   public final boolean add(@ParametricNullness Object element) {
      this.add(element, 1);
      return true;
   }

   @CanIgnoreReturnValue
   public int add(@ParametricNullness Object element, int occurrences) {
      throw new UnsupportedOperationException();
   }

   @CanIgnoreReturnValue
   public final boolean remove(@CheckForNull Object element) {
      return this.remove(element, 1) > 0;
   }

   @CanIgnoreReturnValue
   public int remove(@CheckForNull Object element, int occurrences) {
      throw new UnsupportedOperationException();
   }

   @CanIgnoreReturnValue
   public int setCount(@ParametricNullness Object element, int count) {
      return Multisets.setCountImpl(this, element, count);
   }

   @CanIgnoreReturnValue
   public boolean setCount(@ParametricNullness Object element, int oldCount, int newCount) {
      return Multisets.setCountImpl(this, element, oldCount, newCount);
   }

   @CanIgnoreReturnValue
   public final boolean addAll(Collection elementsToAdd) {
      return Multisets.addAllImpl(this, (Collection)elementsToAdd);
   }

   @CanIgnoreReturnValue
   public final boolean removeAll(Collection elementsToRemove) {
      return Multisets.removeAllImpl(this, elementsToRemove);
   }

   @CanIgnoreReturnValue
   public final boolean retainAll(Collection elementsToRetain) {
      return Multisets.retainAllImpl(this, elementsToRetain);
   }

   public abstract void clear();

   public Set elementSet() {
      Set<E> result = this.elementSet;
      if (result == null) {
         this.elementSet = result = this.createElementSet();
      }

      return result;
   }

   Set createElementSet() {
      return new ElementSet();
   }

   abstract Iterator elementIterator();

   public Set entrySet() {
      Set<Multiset.Entry<E>> result = this.entrySet;
      if (result == null) {
         this.entrySet = result = this.createEntrySet();
      }

      return result;
   }

   Set createEntrySet() {
      return new EntrySet();
   }

   abstract Iterator entryIterator();

   abstract int distinctElements();

   public final boolean equals(@CheckForNull Object object) {
      return Multisets.equalsImpl(this, object);
   }

   public final int hashCode() {
      return this.entrySet().hashCode();
   }

   public final String toString() {
      return this.entrySet().toString();
   }

   class ElementSet extends Multisets.ElementSet {
      Multiset multiset() {
         return AbstractMultiset.this;
      }

      public Iterator iterator() {
         return AbstractMultiset.this.elementIterator();
      }
   }

   class EntrySet extends Multisets.EntrySet {
      Multiset multiset() {
         return AbstractMultiset.this;
      }

      public Iterator iterator() {
         return AbstractMultiset.this.entryIterator();
      }

      public int size() {
         return AbstractMultiset.this.distinctElements();
      }
   }
}
