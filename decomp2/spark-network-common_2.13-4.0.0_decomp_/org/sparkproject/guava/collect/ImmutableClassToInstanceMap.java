package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.primitives.Primitives;

@Immutable(
   containerOf = {"B"}
)
@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class ImmutableClassToInstanceMap extends ForwardingMap implements ClassToInstanceMap, Serializable {
   private static final ImmutableClassToInstanceMap EMPTY = new ImmutableClassToInstanceMap(ImmutableMap.of());
   private final ImmutableMap delegate;

   public static ImmutableClassToInstanceMap of() {
      return EMPTY;
   }

   public static ImmutableClassToInstanceMap of(Class type, Object value) {
      ImmutableMap<Class<? extends B>, B> map = ImmutableMap.of(type, value);
      return new ImmutableClassToInstanceMap(map);
   }

   public static Builder builder() {
      return new Builder();
   }

   public static ImmutableClassToInstanceMap copyOf(Map map) {
      if (map instanceof ImmutableClassToInstanceMap) {
         ImmutableClassToInstanceMap<B> cast = (ImmutableClassToInstanceMap)map;
         return cast;
      } else {
         return (new Builder()).putAll(map).build();
      }
   }

   private ImmutableClassToInstanceMap(ImmutableMap delegate) {
      this.delegate = delegate;
   }

   protected Map delegate() {
      return this.delegate;
   }

   @CheckForNull
   public Object getInstance(Class type) {
      return this.delegate.get(Preconditions.checkNotNull(type));
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public Object putInstance(Class type, Object value) {
      throw new UnsupportedOperationException();
   }

   Object readResolve() {
      return this.isEmpty() ? of() : this;
   }

   public static final class Builder {
      private final ImmutableMap.Builder mapBuilder = ImmutableMap.builder();

      @CanIgnoreReturnValue
      public Builder put(Class key, Object value) {
         this.mapBuilder.put(key, value);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(Map map) {
         for(Map.Entry entry : map.entrySet()) {
            Class<? extends T> type = (Class)entry.getKey();
            T value = (T)entry.getValue();
            this.mapBuilder.put(type, cast(type, value));
         }

         return this;
      }

      private static Object cast(Class type, Object value) {
         return Primitives.wrap(type).cast(value);
      }

      public ImmutableClassToInstanceMap build() {
         ImmutableMap<Class<? extends B>, B> map = this.mapBuilder.buildOrThrow();
         return map.isEmpty() ? ImmutableClassToInstanceMap.of() : new ImmutableClassToInstanceMap(map);
      }
   }
}
