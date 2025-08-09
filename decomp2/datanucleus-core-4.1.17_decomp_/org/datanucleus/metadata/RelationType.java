package org.datanucleus.metadata;

public enum RelationType {
   NONE,
   ONE_TO_ONE_UNI,
   ONE_TO_ONE_BI,
   ONE_TO_MANY_UNI,
   ONE_TO_MANY_BI,
   MANY_TO_MANY_BI,
   MANY_TO_ONE_BI,
   MANY_TO_ONE_UNI;

   public static boolean isRelationSingleValued(RelationType type) {
      return type == ONE_TO_ONE_UNI || type == ONE_TO_ONE_BI || type == MANY_TO_ONE_UNI || type == MANY_TO_ONE_BI;
   }

   public static boolean isRelationMultiValued(RelationType type) {
      return type == ONE_TO_MANY_UNI || type == ONE_TO_MANY_BI || type == MANY_TO_MANY_BI;
   }

   public static boolean isBidirectional(RelationType type) {
      return type == ONE_TO_ONE_BI || type == ONE_TO_MANY_BI || type == MANY_TO_MANY_BI || type == MANY_TO_ONE_BI;
   }
}
