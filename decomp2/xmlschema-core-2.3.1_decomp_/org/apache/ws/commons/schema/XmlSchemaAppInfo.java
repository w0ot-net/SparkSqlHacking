package org.apache.ws.commons.schema;

import org.w3c.dom.NodeList;

public class XmlSchemaAppInfo extends XmlSchemaAnnotationItem {
   String source;
   NodeList markup;

   public String getSource() {
      return this.source;
   }

   public void setSource(String source) {
      this.source = source;
   }

   public NodeList getMarkup() {
      return this.markup;
   }

   public void setMarkup(NodeList markup) {
      this.markup = markup;
   }
}
