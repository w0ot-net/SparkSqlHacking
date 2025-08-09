package org.apache.log4j.builders;

/** @deprecated */
@Deprecated
public class Holder {
   private Object value;

   public Holder() {
   }

   public Holder(final Object defaultValue) {
      this.value = defaultValue;
   }

   public void set(final Object value) {
      this.value = value;
   }

   public Object get() {
      return this.value;
   }
}
