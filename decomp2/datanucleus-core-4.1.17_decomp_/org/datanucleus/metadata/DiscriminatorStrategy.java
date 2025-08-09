package org.datanucleus.metadata;

public enum DiscriminatorStrategy {
   NONE("none"),
   VALUE_MAP("value-map"),
   CLASS_NAME("class-name");

   String name;

   private DiscriminatorStrategy(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public static DiscriminatorStrategy getDiscriminatorStrategy(String value) {
      if (value == null) {
         return null;
      } else if (NONE.toString().equals(value)) {
         return NONE;
      } else if (VALUE_MAP.toString().equals(value)) {
         return VALUE_MAP;
      } else {
         return CLASS_NAME.toString().equals(value) ? CLASS_NAME : null;
      }
   }
}
