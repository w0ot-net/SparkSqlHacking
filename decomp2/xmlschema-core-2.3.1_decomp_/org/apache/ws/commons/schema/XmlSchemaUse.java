package org.apache.ws.commons.schema;

public enum XmlSchemaUse {
   NONE,
   OPTIONAL,
   PROHIBITED,
   REQUIRED;

   public static XmlSchemaUse schemaValueOf(String name) {
      return (XmlSchemaUse)EnumUtil.valueOf(XmlSchemaUse.class, name);
   }

   public String toString() {
      return super.toString().toLowerCase();
   }
}
