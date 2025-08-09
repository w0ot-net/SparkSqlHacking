package org.apache.curator.shaded.com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractSetMultimap extends AbstractMapBasedMultimap implements SetMultimap {
   private static final long serialVersionUID = 7431625294878419160L;

   protected AbstractSetMultimap(Map map) {
      super(map);
   }

   abstract Set createCollection();

   Set createUnmodifiableEmptyCollection() {
      return Collections.emptySet();
   }

   Collection unmodifiableCollectionSubclass(Collection collection) {
      return Collections.unmodifiableSet((Set)collection);
   }

   Collection wrapCollection(@ParametricNullness Object key, Collection collection) {
      return new AbstractMapBasedMultimap.WrappedSet(key, (Set)collection);
   }

   public Set get(@ParametricNullness Object key) {
      return (Set)super.get(key);
   }

   public Set entries() {
      return (Set)super.entries();
   }

   @CanIgnoreReturnValue
   public Set removeAll(@CheckForNull Object key) {
      return (Set)super.removeAll(key);
   }

   @CanIgnoreReturnValue
   public Set replaceValues(@ParametricNullness Object key, Iterable values) {
      return (Set)super.replaceValues(key, values);
   }

   public Map asMap() {
      return super.asMap();
   }

   @CanIgnoreReturnValue
   public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
      return super.put(key, value);
   }

   public boolean equals(@CheckForNull Object object) {
      return super.equals(object);
   }
}
