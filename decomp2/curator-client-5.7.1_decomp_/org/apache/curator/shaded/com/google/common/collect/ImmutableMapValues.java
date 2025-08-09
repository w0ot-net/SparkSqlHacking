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
final class ImmutableMapValues extends ImmutableCollection {
   private final ImmutableMap map;

   ImmutableMapValues(ImmutableMap map) {
      this.map = map;
   }

   public int size() {
      return this.map.size();
   }

   public UnmodifiableIterator iterator() {
      return new UnmodifiableIterator() {
         final UnmodifiableIterator entryItr;

         {
            this.entryItr = ImmutableMapValues.this.map.entrySet().iterator();
         }

         public boolean hasNext() {
            return this.entryItr.hasNext();
         }

         public Object next() {
            return ((Map.Entry)this.entryItr.next()).getValue();
         }
      };
   }

   public Spliterator spliterator() {
      return CollectSpliterators.map(this.map.entrySet().spliterator(), Map.Entry::getValue);
   }

   public boolean contains(@CheckForNull Object object) {
      return object != null && Iterators.contains(this.iterator(), object);
   }

   boolean isPartialView() {
      return true;
   }

   public ImmutableList asList() {
      final ImmutableList<Map.Entry<K, V>> entryList = this.map.entrySet().asList();
      return new ImmutableAsList() {
         public Object get(int index) {
            return ((Map.Entry)entryList.get(index)).getValue();
         }

         ImmutableCollection delegateCollection() {
            return ImmutableMapValues.this;
         }
      };
   }

   @GwtIncompatible
   public void forEach(Consumer action) {
      Preconditions.checkNotNull(action);
      this.map.forEach((k, v) -> action.accept(v));
   }

   @GwtIncompatible
   @J2ktIncompatible
   private static class SerializedForm implements Serializable {
      final ImmutableMap map;
      private static final long serialVersionUID = 0L;

      SerializedForm(ImmutableMap map) {
         this.map = map;
      }

      Object readResolve() {
         return this.map.values();
      }
   }
}
