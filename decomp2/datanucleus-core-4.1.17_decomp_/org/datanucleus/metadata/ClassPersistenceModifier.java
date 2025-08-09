package org.datanucleus.metadata;

public enum ClassPersistenceModifier {
   PERSISTENCE_CAPABLE("persistence-capable"),
   PERSISTENCE_AWARE("persistence-aware"),
   NON_PERSISTENT("non-persistent");

   String name;

   private ClassPersistenceModifier(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public static ClassPersistenceModifier getClassPersistenceModifier(String value) {
      if (value == null) {
         return PERSISTENCE_CAPABLE;
      } else if (PERSISTENCE_CAPABLE.toString().equalsIgnoreCase(value)) {
         return PERSISTENCE_CAPABLE;
      } else if (PERSISTENCE_AWARE.toString().equalsIgnoreCase(value)) {
         return PERSISTENCE_AWARE;
      } else {
         return NON_PERSISTENT.toString().equalsIgnoreCase(value) ? NON_PERSISTENT : null;
      }
   }
}
