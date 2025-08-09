package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlSchemaRedefine extends XmlSchemaExternal {
   private Map attributeGroups = Collections.synchronizedMap(new HashMap());
   private Map groups = Collections.synchronizedMap(new HashMap());
   private Map schemaTypes = Collections.synchronizedMap(new HashMap());
   private List items = Collections.synchronizedList(new ArrayList());

   public XmlSchemaRedefine(XmlSchema parent) {
      super(parent);
   }

   public Map getAttributeGroups() {
      return this.attributeGroups;
   }

   public Map getGroups() {
      return this.groups;
   }

   public List getItems() {
      return this.items;
   }

   public Map getSchemaTypes() {
      return this.schemaTypes;
   }
}
