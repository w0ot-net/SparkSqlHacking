package org.apache.ws.commons.schema;

import javax.xml.namespace.QName;

public class XmlSchemaKeyref extends XmlSchemaIdentityConstraint {
   QName refer;

   public QName getRefer() {
      return this.refer;
   }

   public void setRefer(QName refer) {
      this.refer = refer;
   }
}
