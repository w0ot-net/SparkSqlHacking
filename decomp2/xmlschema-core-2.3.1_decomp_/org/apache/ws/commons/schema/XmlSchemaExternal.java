package org.apache.ws.commons.schema;

import org.apache.ws.commons.schema.utils.CollectionFactory;

public abstract class XmlSchemaExternal extends XmlSchemaAnnotated {
   XmlSchema schema;
   String schemaLocation;

   protected XmlSchemaExternal(final XmlSchema parent) {
      CollectionFactory.withSchemaModifiable(new Runnable() {
         public void run() {
            parent.getExternals().add(XmlSchemaExternal.this);
            parent.getItems().add(XmlSchemaExternal.this);
         }
      });
   }

   public XmlSchema getSchema() {
      return this.schema;
   }

   public void setSchema(XmlSchema sc) {
      this.schema = sc;
   }

   public String getSchemaLocation() {
      return this.schemaLocation;
   }

   public void setSchemaLocation(String schemaLocation) {
      this.schemaLocation = schemaLocation;
   }
}
