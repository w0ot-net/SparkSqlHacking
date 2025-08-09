package org.datanucleus.metadata;

public enum VersionStrategy {
   NONE("none"),
   VERSION_NUMBER("version-number"),
   DATE_TIME("date-time"),
   STATE_IMAGE("state-image");

   String name;

   private VersionStrategy(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public static VersionStrategy getVersionStrategy(String value) {
      if (value == null) {
         return null;
      } else if (NONE.toString().equalsIgnoreCase(value)) {
         return NONE;
      } else if (STATE_IMAGE.toString().equalsIgnoreCase(value)) {
         return STATE_IMAGE;
      } else if (DATE_TIME.toString().equalsIgnoreCase(value)) {
         return DATE_TIME;
      } else {
         return VERSION_NUMBER.toString().equalsIgnoreCase(value) ? VERSION_NUMBER : null;
      }
   }
}
