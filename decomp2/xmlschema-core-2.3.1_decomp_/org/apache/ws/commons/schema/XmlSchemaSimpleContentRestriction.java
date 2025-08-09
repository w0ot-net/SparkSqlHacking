package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;

public class XmlSchemaSimpleContentRestriction extends XmlSchemaContent {
   XmlSchemaAnyAttribute anyAttribute;
   private List attributes = Collections.synchronizedList(new ArrayList());
   private XmlSchemaSimpleType baseType;
   private QName baseTypeName;
   private List facets = Collections.synchronizedList(new ArrayList());

   public void setAnyAttribute(XmlSchemaAnyAttribute anyAttribute) {
      this.anyAttribute = anyAttribute;
   }

   public XmlSchemaAnyAttribute getAnyAttribute() {
      return this.anyAttribute;
   }

   public List getAttributes() {
      return this.attributes;
   }

   public void setBaseType(XmlSchemaSimpleType baseType) {
      this.baseType = baseType;
   }

   public XmlSchemaSimpleType getBaseType() {
      return this.baseType;
   }

   public void setBaseTypeName(QName baseTypeName) {
      this.baseTypeName = baseTypeName;
   }

   public QName getBaseTypeName() {
      return this.baseTypeName;
   }

   public List getFacets() {
      return this.facets;
   }
}
