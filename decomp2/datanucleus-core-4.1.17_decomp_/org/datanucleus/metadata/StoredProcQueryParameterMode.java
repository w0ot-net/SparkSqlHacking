package org.datanucleus.metadata;

public enum StoredProcQueryParameterMode {
   IN,
   OUT,
   INOUT,
   REF_CURSOR;

   public static StoredProcQueryParameterMode getMode(String value) {
      if (value == null) {
         return null;
      } else if (IN.toString().equalsIgnoreCase(value)) {
         return IN;
      } else if (OUT.toString().equalsIgnoreCase(value)) {
         return OUT;
      } else if (INOUT.toString().equalsIgnoreCase(value)) {
         return INOUT;
      } else {
         return REF_CURSOR.toString().equalsIgnoreCase(value) ? REF_CURSOR : null;
      }
   }
}
