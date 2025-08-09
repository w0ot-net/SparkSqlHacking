package org.apache.ws.commons.schema;

import javax.xml.namespace.QName;

public class XmlSchemaGroupRef extends XmlSchemaParticle implements XmlSchemaSequenceMember, XmlSchemaChoiceMember, XmlSchemaAllMember {
   private XmlSchemaGroupParticle particle;
   private QName refName;

   public XmlSchemaGroupParticle getParticle() {
      return this.particle;
   }

   public QName getRefName() {
      return this.refName;
   }

   public void setRefName(QName refName) {
      this.refName = refName;
   }

   void setParticle(XmlSchemaGroupParticle particle) {
      this.particle = particle;
   }
}
