package org.apache.ws.commons.schema;

import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.utils.XmlSchemaRef;
import org.apache.ws.commons.schema.utils.XmlSchemaRefBase;

public class XmlSchemaAttributeGroupRef extends XmlSchemaAttributeOrGroupRef implements XmlSchemaAttributeGroupMember, XmlSchemaItemWithRef {
   private XmlSchemaRef ref;

   public XmlSchemaAttributeGroupRef(XmlSchema parent) {
      this.ref = new XmlSchemaRef(parent, XmlSchemaAttributeGroup.class);
   }

   public XmlSchemaRef getRef() {
      return this.ref;
   }

   public boolean isRef() {
      return this.ref.getTargetQName() != null;
   }

   public QName getTargetQName() {
      return this.ref.getTargetQName();
   }

   public XmlSchemaRefBase getRefBase() {
      return this.ref;
   }
}
