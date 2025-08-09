package org.apache.ws.commons.schema;

public abstract class XmlSchemaContentModel extends XmlSchemaAnnotated {
   protected XmlSchemaContentModel() {
   }

   public abstract void setContent(XmlSchemaContent var1);

   public abstract XmlSchemaContent getContent();
}
