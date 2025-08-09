package org.sparkproject.guava.collect;

import java.io.Serializable;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

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

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
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
