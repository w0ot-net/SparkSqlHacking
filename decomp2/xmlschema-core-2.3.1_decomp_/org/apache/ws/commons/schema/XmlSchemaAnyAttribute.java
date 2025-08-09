package org.apache.ws.commons.schema;

public class XmlSchemaAnyAttribute extends XmlSchemaAnnotated {
   String namespace;
   XmlSchemaContentProcessing processContent;

   public XmlSchemaAnyAttribute() {
      this.processContent = XmlSchemaContentProcessing.NONE;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }

   public XmlSchemaContentProcessing getProcessContent() {
      return this.processContent;
   }

   public void setProcessContent(XmlSchemaContentProcessing processContent) {
      this.processContent = processContent;
   }
}
