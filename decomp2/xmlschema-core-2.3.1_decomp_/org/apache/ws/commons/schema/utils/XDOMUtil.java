package org.apache.ws.commons.schema.utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public final class XDOMUtil extends DOMUtil {
   private XDOMUtil() {
   }

   public static Element getFirstChildElementNS(Node parent, String uri) {
      for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
         if (child.getNodeType() == 1) {
            String childURI = child.getNamespaceURI();
            if (childURI != null && childURI.equals(uri)) {
               return (Element)child;
            }
         }
      }

      return null;
   }

   public static Element getNextSiblingElementNS(Node node, String uri) {
      for(Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
         if (sibling.getNodeType() == 1) {
            String siblingURI = sibling.getNamespaceURI();
            if (siblingURI != null && siblingURI.equals(uri)) {
               return (Element)sibling;
            }
         }
      }

      return null;
   }

   public static boolean anyElementsWithNameNS(Element element, String uri, String name) {
      for(Element el = getFirstChildElementNS(element, uri); el != null; el = getNextSiblingElementNS(el, uri)) {
         if (el.getLocalName().equals(name) && el.getNamespaceURI().equals(uri)) {
            return true;
         }
      }

      return false;
   }
}
