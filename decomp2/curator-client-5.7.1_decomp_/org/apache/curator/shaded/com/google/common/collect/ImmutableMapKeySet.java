package org.apache.curator.shaded.com.google.common.collect;

import java.io.Serializable;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class ImmutableMapKeySet extends IndexedImmutableSet {
   private final ImmutableMap map;

   ImmutableMapKeySet(ImmutableMap map) {
      this.map = map;
   }

   public int size() {
      return this.map.size();
   }

   public UnmodifiableIterator iterator() {
      return this.map.keyIterator();
   }

   public Spliterator spliterator() {
      return this.map.keySpliterator();
   }

   public boolean contains(@CheckForNull Object object) {
      return this.map.containsKey(object);
   }

   Object get(int index) {
      return ((Map.Entry)this.map.entrySet().asList().get(index)).getKey();
   }

   public void forEach(Consumer action) {
      Preconditions.checkNotNull(action);
      this.map.forEach((k, v) -> action.accept(k));
   }

   boolean isPartialView() {
      return true;
   }

   @GwtIncompatible
   @J2ktIncompatible
   private static class KeySetSerializedForm implements Serializable {
      final ImmutableMap map;
      private static final long serialVersionUID = 0L;

      KeySetSerializedForm(ImmutableMap map) {
         this.map = map;
      }

      Object readResolve() {
         return this.map.keySet();
      }
   }
}
