package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
final class ImmutableEnumMap extends ImmutableMap.IteratorBasedImmutableMap {
   private final transient EnumMap delegate;

   static ImmutableMap asImmutable(EnumMap map) {
      switch (map.size()) {
         case 0:
            return ImmutableMap.of();
         case 1:
            Map.Entry<K, V> entry = (Map.Entry)Iterables.getOnlyElement(map.entrySet());
            return ImmutableMap.of((Enum)entry.getKey(), entry.getValue());
         default:
            return new ImmutableEnumMap(map);
      }
   }

   private ImmutableEnumMap(EnumMap delegate) {
      this.delegate = delegate;
      Preconditions.checkArgument(!delegate.isEmpty());
   }

   UnmodifiableIterator keyIterator() {
      return Iterators.unmodifiableIterator(this.delegate.keySet().iterator());
   }

   Spliterator keySpliterator() {
      return this.delegate.keySet().spliterator();
   }

   public int size() {
      return this.delegate.size();
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.delegate.containsKey(key);
   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      return this.delegate.get(key);
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else {
         if (object instanceof ImmutableEnumMap) {
            object = ((ImmutableEnumMap)object).delegate;
         }

         return this.delegate.equals(object);
      }
   }

   UnmodifiableIterator entryIterator() {
      return Maps.unmodifiableEntryIterator(this.delegate.entrySet().iterator());
   }

   Spliterator entrySpliterator() {
      return CollectSpliterators.map(this.delegate.entrySet().spliterator(), Maps::unmodifiableEntry);
   }

   public void forEach(BiConsumer action) {
      this.delegate.forEach(action);
   }

   boolean isPartialView() {
      return false;
   }

   @J2ktIncompatible
   Object writeReplace() {
      return new EnumSerializedForm(this.delegate);
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use EnumSerializedForm");
   }

   @J2ktIncompatible
   private static class EnumSerializedForm implements Serializable {
      final EnumMap delegate;
      private static final long serialVersionUID = 0L;

      EnumSerializedForm(EnumMap delegate) {
         this.delegate = delegate;
      }

      Object readResolve() {
         return new ImmutableEnumMap(this.delegate);
      }
   }
}
