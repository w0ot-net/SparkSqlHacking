package org.datanucleus.store.rdbms.mapping.java;

public class CharacterMapping extends SingleFieldMapping {
   public Class getJavaType() {
      return Character.class;
   }

   public int getDefaultLength(int index) {
      return 1;
   }
}
