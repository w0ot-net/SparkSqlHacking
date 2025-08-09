package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;

public class XmlSchemaSimpleContentExtension extends XmlSchemaContent {
   private XmlSchemaAnyAttribute anyAttribute;
   private List attributes = Collections.synchronizedList(new ArrayList());
   private QName baseTypeName;

   public XmlSchemaAnyAttribute getAnyAttribute() {
      return this.anyAttribute;
   }

   public List getAttributes() {
      return this.attributes;
   }

   public QName getBaseTypeName() {
      return this.baseTypeName;
   }

   public void setAnyAttribute(XmlSchemaAnyAttribute anyAttribute) {
      this.anyAttribute = anyAttribute;
   }

   public void setBaseTypeName(QName baseTypeName) {
      this.baseTypeName = baseTypeName;
   }

   public void setAttributes(List attributes) {
      this.attributes = attributes;
   }
}
