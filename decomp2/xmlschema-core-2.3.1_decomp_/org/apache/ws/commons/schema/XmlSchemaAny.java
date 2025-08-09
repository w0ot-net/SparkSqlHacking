package org.apache.ws.commons.schema;

public class XmlSchemaAny extends XmlSchemaParticle implements XmlSchemaChoiceMember, XmlSchemaSequenceMember, XmlSchemaAllMember {
   private String namespace;
   private XmlSchemaContentProcessing processContent;
   private String targetNamespace;

   public XmlSchemaAny() {
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

   public String getTargetNamespace() {
      return this.targetNamespace;
   }

   public void setTargetNamespace(String namespace) {
      this.targetNamespace = namespace;
   }
}
