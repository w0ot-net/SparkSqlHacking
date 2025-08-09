package org.sparkproject.dmg.pmml;

public interface HasValue {
   default boolean hasValue() {
      Object value = this.getValue();
      return value != null;
   }

   Object requireValue();

   Object getValue();

   PMMLObject setValue(Object var1);
}
