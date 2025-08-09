package org.apache.curator.shaded.com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.CompatibleWith;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use ImmutableMultimap, HashMultimap, or another implementation")
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Multimap {
   int size();

   boolean isEmpty();

   boolean containsKey(@CheckForNull @CompatibleWith("K") Object key);

   boolean containsValue(@CheckForNull @CompatibleWith("V") Object value);

   boolean containsEntry(@CheckForNull @CompatibleWith("K") Object key, @CheckForNull @CompatibleWith("V") Object value);

   @CanIgnoreReturnValue
   boolean put(@ParametricNullness Object key, @ParametricNullness Object value);

   @CanIgnoreReturnValue
   boolean remove(@CheckForNull @CompatibleWith("K") Object key, @CheckForNull @CompatibleWith("V") Object value);

   @CanIgnoreReturnValue
   boolean putAll(@ParametricNullness Object key, Iterable values);

   @CanIgnoreReturnValue
   boolean putAll(Multimap multimap);

   @CanIgnoreReturnValue
   Collection replaceValues(@ParametricNullness Object key, Iterable values);

   @CanIgnoreReturnValue
   Collection removeAll(@CheckForNull @CompatibleWith("K") Object key);

   void clear();

   Collection get(@ParametricNullness Object key);

   Set keySet();

   Multiset keys();

   Collection values();

   Collection entries();

   default void forEach(BiConsumer action) {
      Preconditions.checkNotNull(action);
      this.entries().forEach((entry) -> action.accept(entry.getKey(), entry.getValue()));
   }

   Map asMap();

   boolean equals(@CheckForNull Object obj);

   int hashCode();
}
