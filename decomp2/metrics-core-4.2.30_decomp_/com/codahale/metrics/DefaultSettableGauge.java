package com.codahale.metrics;

public class DefaultSettableGauge implements SettableGauge {
   private volatile Object value;

   public DefaultSettableGauge() {
      this((Object)null);
   }

   public DefaultSettableGauge(Object defaultValue) {
      this.value = defaultValue;
   }

   public void setValue(Object value) {
      this.value = value;
   }

   public Object getValue() {
      return this.value;
   }
}
