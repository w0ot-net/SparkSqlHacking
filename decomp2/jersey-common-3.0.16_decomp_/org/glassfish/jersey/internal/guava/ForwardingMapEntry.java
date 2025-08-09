package org.glassfish.jersey.internal.guava;

import java.util.Map;
import java.util.Objects;

public abstract class ForwardingMapEntry extends ForwardingObject implements Map.Entry {
   ForwardingMapEntry() {
   }

   protected abstract Map.Entry delegate();

   public Object getKey() {
      return this.delegate().getKey();
   }

   public Object getValue() {
      return this.delegate().getValue();
   }

   public Object setValue(Object value) {
      return this.delegate().setValue(value);
   }

   public boolean equals(Object object) {
      return this.delegate().equals(object);
   }

   public int hashCode() {
      return this.delegate().hashCode();
   }

   boolean standardEquals(Object object) {
      if (!(object instanceof Map.Entry)) {
         return false;
      } else {
         Map.Entry<?, ?> that = (Map.Entry)object;
         return Objects.equals(this.getKey(), that.getKey()) && Objects.equals(this.getValue(), that.getValue());
      }
   }
}
