package org.datanucleus.metadata;

public enum IdentityType {
   APPLICATION,
   DATASTORE,
   NONDURABLE;

   public static IdentityType getIdentityType(String value) {
      if (value == null) {
         return null;
      } else if (APPLICATION.toString().equalsIgnoreCase(value)) {
         return APPLICATION;
      } else if (DATASTORE.toString().equalsIgnoreCase(value)) {
         return DATASTORE;
      } else {
         return NONDURABLE.toString().equalsIgnoreCase(value) ? NONDURABLE : null;
      }
   }
}
