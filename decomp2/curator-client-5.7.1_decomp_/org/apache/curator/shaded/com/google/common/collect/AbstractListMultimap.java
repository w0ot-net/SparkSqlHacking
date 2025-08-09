package org.apache.curator.shaded.com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractListMultimap extends AbstractMapBasedMultimap implements ListMultimap {
   private static final long serialVersionUID = 6588350623831699109L;

   protected AbstractListMultimap(Map map) {
      super(map);
   }

   abstract List createCollection();

   List createUnmodifiableEmptyCollection() {
      return Collections.emptyList();
   }

   Collection unmodifiableCollectionSubclass(Collection collection) {
      return Collections.unmodifiableList((List)collection);
   }

   Collection wrapCollection(@ParametricNullness Object key, Collection collection) {
      return this.wrapList(key, (List)collection, (AbstractMapBasedMultimap.WrappedCollection)null);
   }

   public List get(@ParametricNullness Object key) {
      return (List)super.get(key);
   }

   @CanIgnoreReturnValue
   public List removeAll(@CheckForNull Object key) {
      return (List)super.removeAll(key);
   }

   @CanIgnoreReturnValue
   public List replaceValues(@ParametricNullness Object key, Iterable values) {
      return (List)super.replaceValues(key, values);
   }

   @CanIgnoreReturnValue
   public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
      return super.put(key, value);
   }

   public Map asMap() {
      return super.asMap();
   }

   public boolean equals(@CheckForNull Object object) {
      return super.equals(object);
   }
}
