package org.apache.avro.path;

public class LocationStep implements PathElement {
   private final String selector;
   private final String propertyName;

   public LocationStep(String selector, String propertyName) {
      this.selector = selector;
      this.propertyName = propertyName;
   }

   public String toString() {
      return this.propertyName != null && !this.propertyName.isEmpty() ? this.selector + this.propertyName : this.selector;
   }
}
