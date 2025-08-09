package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingMapEntry extends ForwardingObject implements Map.Entry {
   protected ForwardingMapEntry() {
   }

   protected abstract Map.Entry delegate();

   @ParametricNullness
   public Object getKey() {
      return this.delegate().getKey();
   }

   @ParametricNullness
   public Object getValue() {
      return this.delegate().getValue();
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object setValue(@ParametricNullness Object value) {
      return this.delegate().setValue(value);
   }

   public boolean equals(@CheckForNull Object object) {
      return this.delegate().equals(object);
   }

   public int hashCode() {
      return this.delegate().hashCode();
   }

   protected boolean standardEquals(@CheckForNull Object object) {
      if (!(object instanceof Map.Entry)) {
         return false;
      } else {
         Map.Entry<?, ?> that = (Map.Entry)object;
         return Objects.equal(this.getKey(), that.getKey()) && Objects.equal(this.getValue(), that.getValue());
      }
   }

   protected int standardHashCode() {
      K k = (K)this.getKey();
      V v = (V)this.getValue();
      return (k == null ? 0 : k.hashCode()) ^ (v == null ? 0 : v.hashCode());
   }

   protected String standardToString() {
      return this.getKey() + "=" + this.getValue();
   }
}
