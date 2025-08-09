package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.List;

public class XmlSchemaSequence extends XmlSchemaGroupParticle implements XmlSchemaChoiceMember, XmlSchemaSequenceMember {
   private List items = new ArrayList();

   public List getItems() {
      return this.items;
   }
}
