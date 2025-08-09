package org.glassfish.jersey.internal.guava;

import java.util.Map;
import java.util.Objects;

abstract class AbstractMapEntry implements Map.Entry {
   public abstract Object getKey();

   public abstract Object getValue();

   public Object setValue(Object value) {
      throw new UnsupportedOperationException();
   }

   public boolean equals(Object object) {
      if (!(object instanceof Map.Entry)) {
         return false;
      } else {
         Map.Entry<?, ?> that = (Map.Entry)object;
         return Objects.equals(this.getKey(), that.getKey()) && Objects.equals(this.getValue(), that.getValue());
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
