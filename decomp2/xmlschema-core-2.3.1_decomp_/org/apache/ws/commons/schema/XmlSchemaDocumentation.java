package org.apache.ws.commons.schema;

import org.w3c.dom.NodeList;

public class XmlSchemaDocumentation extends XmlSchemaAnnotationItem {
   String source;
   String language;
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

   public String getLanguage() {
      return this.language;
   }

   public void setLanguage(String language) {
      this.language = language;
   }
}
