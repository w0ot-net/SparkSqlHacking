package org.apache.ws.commons.schema;

public class XmlSchemaSimpleType extends XmlSchemaType {
   XmlSchemaSimpleTypeContent content;

   public XmlSchemaSimpleType(XmlSchema schema, boolean topLevel) {
      super(schema, topLevel);
   }

   public XmlSchemaSimpleTypeContent getContent() {
      return this.content;
   }

   public void setContent(XmlSchemaSimpleTypeContent content) {
      this.content = content;
   }
}
