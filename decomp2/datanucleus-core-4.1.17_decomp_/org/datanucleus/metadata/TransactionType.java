package org.datanucleus.metadata;

public enum TransactionType {
   JTA,
   RESOURCE_LOCAL;

   public static TransactionType getValue(String value) {
      if (value == null) {
         return null;
      } else if (JTA.toString().equalsIgnoreCase(value)) {
         return JTA;
      } else {
         return RESOURCE_LOCAL.toString().equalsIgnoreCase(value) ? RESOURCE_LOCAL : null;
      }
   }
}
