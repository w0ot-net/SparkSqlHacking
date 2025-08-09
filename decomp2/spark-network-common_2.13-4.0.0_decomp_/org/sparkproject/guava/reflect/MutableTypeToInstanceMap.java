package org.sparkproject.guava.reflect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ForwardingMap;
import org.sparkproject.guava.collect.ForwardingMapEntry;
import org.sparkproject.guava.collect.ForwardingSet;
import org.sparkproject.guava.collect.Iterators;
import org.sparkproject.guava.collect.Maps;

@ElementTypesAreNonnullByDefault
public final class MutableTypeToInstanceMap extends ForwardingMap implements TypeToInstanceMap {
   private final Map backingMap = Maps.newHashMap();

   @CheckForNull
   public Object getInstance(Class type) {
      return this.trustedGet(TypeToken.of(type));
   }

   @CheckForNull
   public Object getInstance(TypeToken type) {
      return this.trustedGet(type.rejectTypeVariables());
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object putInstance(Class type, @ParametricNullness Object value) {
      return this.trustedPut(TypeToken.of(type), value);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object putInstance(TypeToken type, @ParametricNullness Object value) {
      return this.trustedPut(type.rejectTypeVariables(), value);
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public Object put(TypeToken key, @ParametricNullness Object value) {
      throw new UnsupportedOperationException("Please use putInstance() instead.");
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public void putAll(Map map) {
      throw new UnsupportedOperationException("Please use putInstance() instead.");
   }

   public Set entrySet() {
      return MutableTypeToInstanceMap.UnmodifiableEntry.transformEntries(super.entrySet());
   }

   protected Map delegate() {
      return this.backingMap;
   }

   @CheckForNull
   private Object trustedPut(TypeToken type, @ParametricNullness Object value) {
      return this.backingMap.put(type, value);
   }

   @CheckForNull
   private Object trustedGet(TypeToken type) {
      return this.backingMap.get(type);
   }

   private static final class UnmodifiableEntry extends ForwardingMapEntry {
      private final Map.Entry delegate;

      static Set transformEntries(final Set entries) {
         return new ForwardingSet() {
            protected Set delegate() {
               return entries;
            }

            public Iterator iterator() {
               return MutableTypeToInstanceMap.UnmodifiableEntry.transformEntries(super.iterator());
            }

            public Object[] toArray() {
               Object[] result = this.standardToArray();
               return result;
            }

            public Object[] toArray(Object[] array) {
               return this.standardToArray(array);
            }
         };
      }

      private static Iterator transformEntries(Iterator entries) {
         return Iterators.transform(entries, UnmodifiableEntry::new);
      }

      private UnmodifiableEntry(Map.Entry delegate) {
         this.delegate = (Map.Entry)Preconditions.checkNotNull(delegate);
      }

      protected Map.Entry delegate() {
         return this.delegate;
      }

      @ParametricNullness
      public Object setValue(@ParametricNullness Object value) {
         throw new UnsupportedOperationException();
      }
   }
}
