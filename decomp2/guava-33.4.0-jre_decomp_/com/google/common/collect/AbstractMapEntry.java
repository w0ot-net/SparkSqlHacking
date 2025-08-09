package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractMapEntry implements Map.Entry {
   @ParametricNullness
   public abstract Object getKey();

   @ParametricNullness
   public abstract Object getValue();

   @ParametricNullness
   public Object setValue(@ParametricNullness Object value) {
      throw new UnsupportedOperationException();
   }

   public boolean equals(@CheckForNull Object object) {
      if (!(object instanceof Map.Entry)) {
         return false;
      } else {
         Map.Entry<?, ?> that = (Map.Entry)object;
         return Objects.equal(this.getKey(), that.getKey()) && Objects.equal(this.getValue(), that.getValue());
      }
   }

   public int hashCode() {
      K k = (K)this.getKey();
      V v = (V)this.getValue();
      return (k == null ? 0 : k.hashCode()) ^ (v == null ? 0 : v.hashCode());
   }

   public String toString() {
      return this.getKey() + "=" + this.getValue();
   }
}
