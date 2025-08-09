package org.glassfish.jersey.internal.guava;

import java.io.Serializable;

class ImmutableEntry extends AbstractMapEntry implements Serializable {
   private static final long serialVersionUID = 0L;
   private final Object key;
   private final Object value;

   ImmutableEntry(Object key, Object value) {
      this.key = key;
      this.value = value;
   }

   public final Object getKey() {
      return this.key;
   }

   public final Object getValue() {
      return this.value;
   }

   public final Object setValue(Object value) {
      throw new UnsupportedOperationException();
   }
}
