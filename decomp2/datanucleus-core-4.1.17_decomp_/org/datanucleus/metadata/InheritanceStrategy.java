package org.datanucleus.metadata;

public enum InheritanceStrategy {
   SUBCLASS_TABLE("subclass-table"),
   NEW_TABLE("new-table"),
   SUPERCLASS_TABLE("superclass-table"),
   COMPLETE_TABLE("complete-table");

   String name;

   private InheritanceStrategy(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public static InheritanceStrategy getInheritanceStrategy(String value) {
      if (value == null) {
         return null;
      } else if (SUBCLASS_TABLE.toString().equals(value)) {
         return SUBCLASS_TABLE;
      } else if (NEW_TABLE.toString().equals(value)) {
         return NEW_TABLE;
      } else if (SUPERCLASS_TABLE.toString().equals(value)) {
         return SUPERCLASS_TABLE;
      } else {
         return COMPLETE_TABLE.toString().equals(value) ? COMPLETE_TABLE : NEW_TABLE;
      }
   }
}
