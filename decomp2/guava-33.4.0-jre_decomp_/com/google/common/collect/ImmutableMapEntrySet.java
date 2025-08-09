package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
abstract class ImmutableMapEntrySet extends ImmutableSet.CachingAsList {
   abstract ImmutableMap map();

   public int size() {
      return this.map().size();
   }

   public boolean contains(@CheckForNull Object object) {
      if (!(object instanceof Map.Entry)) {
         return false;
      } else {
         Map.Entry<?, ?> entry = (Map.Entry)object;
         V value = (V)this.map().get(entry.getKey());
         return value != null && value.equals(entry.getValue());
      }
   }

   boolean isPartialView() {
      return this.map().isPartialView();
   }

   @GwtIncompatible
   boolean isHashCodeFast() {
      return this.map().isHashCodeFast();
   }

   public int hashCode() {
      return this.map().hashCode();
   }

   @GwtIncompatible
   @J2ktIncompatible
   Object writeReplace() {
      return new EntrySetSerializedForm(this.map());
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use EntrySetSerializedForm");
   }

   static final class RegularEntrySet extends ImmutableMapEntrySet {
      private final transient ImmutableMap map;
      private final transient ImmutableList entries;

      RegularEntrySet(ImmutableMap map, Map.Entry[] entries) {
         this(map, ImmutableList.asImmutableList(entries));
      }

      RegularEntrySet(ImmutableMap map, ImmutableList entries) {
         this.map = map;
         this.entries = entries;
      }

      ImmutableMap map() {
         return this.map;
      }

      @GwtIncompatible("not used in GWT")
      int copyIntoArray(@Nullable Object[] dst, int offset) {
         return this.entries.copyIntoArray(dst, offset);
      }

      public UnmodifiableIterator iterator() {
         return this.entries.iterator();
      }

      public Spliterator spliterator() {
         return this.entries.spliterator();
      }

      public void forEach(Consumer action) {
         this.entries.forEach(action);
      }

      ImmutableList createAsList() {
         return new RegularImmutableAsList(this, this.entries);
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }

   @GwtIncompatible
   @J2ktIncompatible
   private static class EntrySetSerializedForm implements Serializable {
      final ImmutableMap map;
      private static final long serialVersionUID = 0L;

      EntrySetSerializedForm(ImmutableMap map) {
         this.map = map;
      }

      Object readResolve() {
         return this.map.entrySet();
      }
   }
}
