package org.apache.ws.commons.schema;

public enum XmlSchemaContentType {
   ELEMENT_ONLY,
   EMPTY,
   MIXED,
   TEXT_ONLY;

   public static XmlSchemaContentProcessing schemaValueOf(String name) {
      return (XmlSchemaContentProcessing)EnumUtil.valueOf(XmlSchemaContentProcessing.class, name);
   }

   public String toString() {
      return super.toString().toLowerCase();
   }
}
