package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingMultiset extends ForwardingCollection implements Multiset {
   protected ForwardingMultiset() {
   }

   protected abstract Multiset delegate();

   public int count(@CheckForNull Object element) {
      return this.delegate().count(element);
   }

   @CanIgnoreReturnValue
   public int add(@ParametricNullness Object element, int occurrences) {
      return this.delegate().add(element, occurrences);
   }

   @CanIgnoreReturnValue
   public int remove(@CheckForNull Object element, int occurrences) {
      return this.delegate().remove(element, occurrences);
   }

   public Set elementSet() {
      return this.delegate().elementSet();
   }

   public Set entrySet() {
      return this.delegate().entrySet();
   }

   public boolean equals(@CheckForNull Object object) {
      return object == this || this.delegate().equals(object);
   }

   public int hashCode() {
      return this.delegate().hashCode();
   }

   @CanIgnoreReturnValue
   public int setCount(@ParametricNullness Object element, int count) {
      return this.delegate().setCount(element, count);
   }

   @CanIgnoreReturnValue
   public boolean setCount(@ParametricNullness Object element, int oldCount, int newCount) {
      return this.delegate().setCount(element, oldCount, newCount);
   }

   protected boolean standardContains(@CheckForNull Object object) {
      return this.count(object) > 0;
   }

   protected void standardClear() {
      Iterators.clear(this.entrySet().iterator());
   }

   protected int standardCount(@CheckForNull Object object) {
      for(Multiset.Entry entry : this.entrySet()) {
         if (Objects.equal(entry.getElement(), object)) {
            return entry.getCount();
         }
      }

      return 0;
   }

   protected boolean standardAdd(@ParametricNullness Object element) {
      this.add(element, 1);
      return true;
   }

   protected boolean standardAddAll(Collection elementsToAdd) {
      return Multisets.addAllImpl(this, (Collection)elementsToAdd);
   }

   protected boolean standardRemove(@CheckForNull Object element) {
      return this.remove(element, 1) > 0;
   }

   protected boolean standardRemoveAll(Collection elementsToRemove) {
      return Multisets.removeAllImpl(this, elementsToRemove);
   }

   protected boolean standardRetainAll(Collection elementsToRetain) {
      return Multisets.retainAllImpl(this, elementsToRetain);
   }

   protected int standardSetCount(@ParametricNullness Object element, int count) {
      return Multisets.setCountImpl(this, element, count);
   }

   protected boolean standardSetCount(@ParametricNullness Object element, int oldCount, int newCount) {
      return Multisets.setCountImpl(this, element, oldCount, newCount);
   }

   protected Iterator standardIterator() {
      return Multisets.iteratorImpl(this);
   }

   protected int standardSize() {
      return Multisets.linearTimeSizeImpl(this);
   }

   protected boolean standardEquals(@CheckForNull Object object) {
      return Multisets.equalsImpl(this, object);
   }

   protected int standardHashCode() {
      return this.entrySet().hashCode();
   }

   protected String standardToString() {
      return this.entrySet().toString();
   }

   protected class StandardElementSet extends Multisets.ElementSet {
      public StandardElementSet() {
      }

      Multiset multiset() {
         return ForwardingMultiset.this;
      }

      public Iterator iterator() {
         return Multisets.elementIterator(this.multiset().entrySet().iterator());
      }
   }
}
