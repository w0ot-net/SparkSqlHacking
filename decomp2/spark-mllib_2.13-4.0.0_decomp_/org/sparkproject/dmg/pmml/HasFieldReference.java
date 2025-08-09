package org.sparkproject.dmg.pmml;

public interface HasFieldReference {
   default String requireField() {
      throw new UnsupportedOperationException();
   }

   String getField();

   PMMLObject setField(String var1);

   default PMMLObject setField(Field field) {
      return this.setField(field != null ? field.requireName() : null);
   }
}
