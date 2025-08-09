package org.apache.ws.commons.schema;

public enum XmlSchemaContentProcessing {
   LAX,
   NONE,
   SKIP,
   STRICT;

   public static XmlSchemaContentProcessing schemaValueOf(String name) {
      return (XmlSchemaContentProcessing)EnumUtil.valueOf(XmlSchemaContentProcessing.class, name);
   }

   public String toString() {
      return super.toString().toLowerCase();
   }
}
