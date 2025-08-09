package org.datanucleus.metadata;

public enum FieldPersistenceModifier {
   PERSISTENT,
   TRANSACTIONAL,
   NONE,
   DEFAULT;

   public static FieldPersistenceModifier getFieldPersistenceModifier(String value) {
      if (value == null) {
         return null;
      } else if (PERSISTENT.toString().equalsIgnoreCase(value)) {
         return PERSISTENT;
      } else if (TRANSACTIONAL.toString().equalsIgnoreCase(value)) {
         return TRANSACTIONAL;
      } else {
         return NONE.toString().equalsIgnoreCase(value) ? NONE : null;
      }
   }
}
