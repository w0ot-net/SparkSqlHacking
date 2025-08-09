package org.apache.ws.commons.schema.extensions;

import java.util.Iterator;
import java.util.Map;
import org.apache.ws.commons.schema.XmlSchemaObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DefaultExtensionSerializer implements ExtensionSerializer {
   public void serialize(XmlSchemaObject schemaObject, Class classOfType, Node node) {
      Map<Object, Object> metaInfoMap = schemaObject.getMetaInfoMap();
      Document parentDoc = node.getOwnerDocument();
      if (metaInfoMap.containsKey("EXTERNAL_ATTRIBUTES")) {
         Map<?, ?> attribMap = (Map)metaInfoMap.get("EXTERNAL_ATTRIBUTES");
         Iterator<?> it = attribMap.values().iterator();

         while(it.hasNext()) {
            if (node.getNodeType() == 1) {
               ((Element)node).setAttributeNodeNS((Attr)parentDoc.importNode((Node)it.next(), true));
            }
         }
      }

      if (metaInfoMap.containsKey("EXTERNAL_ELEMENTS")) {
         Map<?, ?> elementMap = (Map)metaInfoMap.get("EXTERNAL_ELEMENTS");
         Iterator<?> it = elementMap.values().iterator();

         while(it.hasNext()) {
            node.appendChild(parentDoc.importNode((Node)it.next(), true));
         }
      }

   }
}
