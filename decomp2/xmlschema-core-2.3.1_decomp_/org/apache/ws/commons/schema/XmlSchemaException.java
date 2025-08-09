package org.apache.ws.commons.schema;

public class XmlSchemaException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public XmlSchemaException() {
   }

   public XmlSchemaException(String message) {
      super(message);
   }

   public XmlSchemaException(String message, Throwable cause) {
      super(message, cause);
   }

   public int getLineNumer() {
      return 1;
   }

   public int getLinePosition() {
      return 1;
   }

   public XmlSchemaObject getSourceSchemaObject() {
      return null;
   }

   public String getSourceUri() {
      return null;
   }
}
