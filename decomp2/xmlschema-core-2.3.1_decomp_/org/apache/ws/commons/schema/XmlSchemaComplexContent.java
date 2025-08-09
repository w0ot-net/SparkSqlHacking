package org.apache.ws.commons.schema;

public class XmlSchemaComplexContent extends XmlSchemaContentModel {
   XmlSchemaContent content;
   private boolean mixed;

   public XmlSchemaContent getContent() {
      return this.content;
   }

   public void setContent(XmlSchemaContent content) {
      this.content = content;
   }

   public boolean isMixed() {
      return this.mixed;
   }

   public void setMixed(boolean mixed) {
      this.mixed = mixed;
   }
}
