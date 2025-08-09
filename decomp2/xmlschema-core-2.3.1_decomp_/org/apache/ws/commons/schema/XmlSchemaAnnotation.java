package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.List;

public class XmlSchemaAnnotation extends XmlSchemaObject {
   private List items = new ArrayList();

   public List getItems() {
      return this.items;
   }
}
