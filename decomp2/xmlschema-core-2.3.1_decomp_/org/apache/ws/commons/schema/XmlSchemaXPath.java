package org.apache.ws.commons.schema;

public class XmlSchemaXPath extends XmlSchemaAnnotated {
   String xpath;

   public String getXPath() {
      return this.xpath;
   }

   public void setXPath(String xpathString) {
      this.xpath = xpathString;
   }
}
