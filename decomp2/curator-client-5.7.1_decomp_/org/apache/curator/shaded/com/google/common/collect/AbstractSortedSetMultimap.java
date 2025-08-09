package org.apache.curator.shaded.com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractSortedSetMultimap extends AbstractSetMultimap implements SortedSetMultimap {
   private static final long serialVersionUID = 430848587173315748L;

   protected AbstractSortedSetMultimap(Map map) {
      super(map);
   }

   abstract SortedSet createCollection();

   SortedSet createUnmodifiableEmptyCollection() {
      return this.unmodifiableCollectionSubclass(this.createCollection());
   }

   SortedSet unmodifiableCollectionSubclass(Collection collection) {
      return (SortedSet)(collection instanceof NavigableSet ? Sets.unmodifiableNavigableSet((NavigableSet)collection) : Collections.unmodifiableSortedSet((SortedSet)collection));
   }

   Collection wrapCollection(@ParametricNullness Object key, Collection collection) {
      return (Collection)(collection instanceof NavigableSet ? new AbstractMapBasedMultimap.WrappedNavigableSet(key, (NavigableSet)collection, (AbstractMapBasedMultimap.WrappedCollection)null) : new AbstractMapBasedMultimap.WrappedSortedSet(key, (SortedSet)collection, (AbstractMapBasedMultimap.WrappedCollection)null));
   }

   public SortedSet get(@ParametricNullness Object key) {
      return (SortedSet)super.get(key);
   }

   @CanIgnoreReturnValue
   public SortedSet removeAll(@CheckForNull Object key) {
      return (SortedSet)super.removeAll(key);
   }

   @CanIgnoreReturnValue
   public SortedSet replaceValues(@ParametricNullness Object key, Iterable values) {
      return (SortedSet)super.replaceValues(key, values);
   }

   public Map asMap() {
      return super.asMap();
   }

   public Collection values() {
      return super.values();
   }
}
