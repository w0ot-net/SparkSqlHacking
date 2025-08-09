package com.google.common.reflect;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class ImmutableTypeToInstanceMap extends ForwardingMap implements TypeToInstanceMap {
   private final ImmutableMap delegate;

   public static ImmutableTypeToInstanceMap of() {
      return new ImmutableTypeToInstanceMap(ImmutableMap.of());
   }

   public static Builder builder() {
      return new Builder();
   }

   private ImmutableTypeToInstanceMap(ImmutableMap delegate) {
      this.delegate = delegate;
   }

   @CheckForNull
   public Object getInstance(TypeToken type) {
      return this.trustedGet(type.rejectTypeVariables());
   }

   @CheckForNull
   public Object getInstance(Class type) {
      return this.trustedGet(TypeToken.of(type));
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public Object putInstance(TypeToken type, Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public Object putInstance(Class type, Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public Object put(TypeToken key, Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public void putAll(Map map) {
      throw new UnsupportedOperationException();
   }

   protected Map delegate() {
      return this.delegate;
   }

   @CheckForNull
   private Object trustedGet(TypeToken type) {
      return this.delegate.get(type);
   }

   public static final class Builder {
      private final ImmutableMap.Builder mapBuilder;

      private Builder() {
         this.mapBuilder = ImmutableMap.builder();
      }

      @CanIgnoreReturnValue
      public Builder put(Class key, Object value) {
         this.mapBuilder.put(TypeToken.of(key), value);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder put(TypeToken key, Object value) {
         this.mapBuilder.put(key.rejectTypeVariables(), value);
         return this;
      }

      public ImmutableTypeToInstanceMap build() {
         return new ImmutableTypeToInstanceMap(this.mapBuilder.buildOrThrow());
      }
   }
}
