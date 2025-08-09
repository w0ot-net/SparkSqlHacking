package org.apache.ws.commons.schema;

import javax.xml.namespace.QName;

public class XmlSchemaSimpleTypeList extends XmlSchemaSimpleTypeContent {
   XmlSchemaSimpleType itemType;
   QName itemTypeName;

   public XmlSchemaSimpleType getItemType() {
      return this.itemType;
   }

   public void setItemType(XmlSchemaSimpleType itemType) {
      this.itemType = itemType;
   }

   public QName getItemTypeName() {
      return this.itemTypeName;
   }

   public void setItemTypeName(QName itemTypeName) {
      this.itemTypeName = itemTypeName;
   }
}
