package org.apache.ws.commons.schema;

public class XmlSchemaImport extends XmlSchemaExternal {
   String namespace;

   public XmlSchemaImport(XmlSchema parent) {
      super(parent);
   }

   public String getNamespace() {
      return this.namespace;
   }

   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }
}
