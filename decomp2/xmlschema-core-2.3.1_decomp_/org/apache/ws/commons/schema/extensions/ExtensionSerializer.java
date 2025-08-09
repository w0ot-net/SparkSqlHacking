package org.apache.ws.commons.schema.extensions;

import org.apache.ws.commons.schema.XmlSchemaObject;
import org.w3c.dom.Node;

public interface ExtensionSerializer {
   void serialize(XmlSchemaObject var1, Class var2, Node var3);
}
