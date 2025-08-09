package org.apache.ws.commons.schema;

import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.utils.XmlSchemaRefBase;

public interface XmlSchemaItemWithRefBase {
   boolean isRef();

   QName getTargetQName();

   XmlSchemaRefBase getRefBase();
}
