package org.apache.ws.commons.schema;

import org.apache.ws.commons.schema.utils.XmlSchemaRef;

public interface XmlSchemaItemWithRef extends XmlSchemaItemWithRefBase {
   XmlSchemaRef getRef();
}
