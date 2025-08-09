package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Predicate;

@ElementTypesAreNonnullByDefault
@GwtCompatible
class FilteredKeyMultimap extends AbstractMultimap implements FilteredMultimap {
   final Multimap unfiltered;
   final Predicate keyPredicate;

   FilteredKeyMultimap(Multimap unfiltered, Predicate keyPredicate) {
      this.unfiltered = (Multimap)Preconditions.checkNotNull(unfiltered);
      this.keyPredicate = (Predicate)Preconditions.checkNotNull(keyPredicate);
   }

   public Multimap unfiltered() {
      return this.unfiltered;
   }

   public Predicate entryPredicate() {
      return Maps.keyPredicateOnEntries(this.keyPredicate);
   }

   public int size() {
      int size = 0;

      for(Collection collection : this.asMap().values()) {
         size += collection.size();
      }

      return size;
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.unfiltered.containsKey(key) ? this.keyPredicate.apply(key) : false;
   }

   public Collection removeAll(@CheckForNull Object key) {
      return this.containsKey(key) ? this.unfiltered.removeAll(key) : this.unmodifiableEmptyCollection();
   }

   Collection unmodifiableEmptyCollection() {
      return (Collection)(this.unfiltered instanceof SetMultimap ? Collections.emptySet() : Collections.emptyList());
   }

   public void clear() {
      this.keySet().clear();
   }

   Set createKeySet() {
      return Sets.filter(this.unfiltered.keySet(), this.keyPredicate);
   }

   public Collection get(@ParametricNullness Object key) {
      if (this.keyPredicate.apply(key)) {
         return this.unfiltered.get(key);
      } else {
         return (Collection)(this.unfiltered instanceof SetMultimap ? new AddRejectingSet(key) : new AddRejectingList(key));
      }
   }

   Iterator entryIterator() {
      throw new AssertionError("should never be called");
   }

   Collection createEntries() {
      return new Entries();
   }

   Collection createValues() {
      return new FilteredMultimapValues(this);
   }

   Map createAsMap() {
      return Maps.filterKeys(this.unfiltered.asMap(), this.keyPredicate);
   }

   Multiset createKeys() {
      return Multisets.filter(this.unfiltered.keys(), this.keyPredicate);
   }

   static class AddRejectingSet extends ForwardingSet {
      @ParametricNullness
      final Object key;

      AddRejectingSet(@ParametricNullness Object key) {
         this.key = key;
      }

      public boolean add(@ParametricNullness Object element) {
         throw new IllegalArgumentException("Key does not satisfy predicate: " + this.key);
      }

      public boolean addAll(Collection collection) {
         Preconditions.checkNotNull(collection);
         throw new IllegalArgumentException("Key does not satisfy predicate: " + this.key);
      }

      protected Set delegate() {
         return Collections.emptySet();
      }
   }

   static class AddRejectingList extends ForwardingList {
      @ParametricNullness
      final Object key;

      AddRejectingList(@ParametricNullness Object key) {
         this.key = key;
      }

      public boolean add(@ParametricNullness Object v) {
         this.add(0, v);
         return true;
      }

      public void add(int index, @ParametricNullness Object element) {
         Preconditions.checkPositionIndex(index, 0);
         throw new IllegalArgumentException("Key does not satisfy predicate: " + this.key);
      }

      public boolean addAll(Collection collection) {
         this.addAll(0, collection);
         return true;
      }

      @CanIgnoreReturnValue
      public boolean addAll(int index, Collection elements) {
         Preconditions.checkNotNull(elements);
         Preconditions.checkPositionIndex(index, 0);
         throw new IllegalArgumentException("Key does not satisfy predicate: " + this.key);
      }

      protected List delegate() {
         return Collections.emptyList();
      }
   }

   class Entries extends ForwardingCollection {
      protected Collection delegate() {
         return Collections2.filter(FilteredKeyMultimap.this.unfiltered.entries(), FilteredKeyMultimap.this.entryPredicate());
      }

      public boolean remove(@CheckForNull Object o) {
         if (o instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            if (FilteredKeyMultimap.this.unfiltered.containsKey(entry.getKey()) && FilteredKeyMultimap.this.keyPredicate.apply(entry.getKey())) {
               return FilteredKeyMultimap.this.unfiltered.remove(entry.getKey(), entry.getValue());
            }
         }

         return false;
      }
   }
}
