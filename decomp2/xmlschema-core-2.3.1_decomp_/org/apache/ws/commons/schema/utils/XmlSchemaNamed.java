package org.apache.ws.commons.schema.utils;

import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.XmlSchema;

public interface XmlSchemaNamed extends XmlSchemaObjectBase {
   String getName();

   boolean isAnonymous();

   void setName(String var1);

   XmlSchema getParent();

   QName getQName();

   boolean isTopLevel();
}
