package org.apache.ws.commons.schema;

public enum XmlSchemaForm {
   NONE,
   QUALIFIED,
   UNQUALIFIED;

   public static XmlSchemaForm schemaValueOf(String name) {
      return (XmlSchemaForm)EnumUtil.valueOf(XmlSchemaForm.class, name);
   }

   public String toString() {
      switch (this) {
         case QUALIFIED:
            return "qualified";
         case UNQUALIFIED:
            return "unqualified";
         default:
            return "none";
      }
   }
}
