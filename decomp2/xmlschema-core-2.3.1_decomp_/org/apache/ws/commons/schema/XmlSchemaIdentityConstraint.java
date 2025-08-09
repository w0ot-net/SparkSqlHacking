package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmlSchemaIdentityConstraint extends XmlSchemaAnnotated {
   private List fields = Collections.synchronizedList(new ArrayList());
   private String name;
   private XmlSchemaXPath selector;

   public List getFields() {
      return this.fields;
   }

   public String getName() {
      return this.name;
   }

   public XmlSchemaXPath getSelector() {
      return this.selector;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setSelector(XmlSchemaXPath selector) {
      this.selector = selector;
   }

   void setFields(List fields) {
      this.fields = fields;
   }
}
