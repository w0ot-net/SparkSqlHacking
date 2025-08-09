package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;

public class XmlSchemaSimpleTypeUnion extends XmlSchemaSimpleTypeContent {
   private List baseTypes = Collections.synchronizedList(new ArrayList());
   private String memberTypesSource;
   private QName[] memberTypesQNames;

   public List getBaseTypes() {
      return this.baseTypes;
   }

   public void setMemberTypesSource(String memberTypesSources) {
      this.memberTypesSource = memberTypesSources;
   }

   public String getMemberTypesSource() {
      return this.memberTypesSource;
   }

   public QName[] getMemberTypesQNames() {
      return this.memberTypesQNames;
   }

   public void setMemberTypesQNames(QName[] memberTypesQNames) {
      this.memberTypesQNames = memberTypesQNames;
   }
}
