package org.apache.ws.commons.schema.utils;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DOMUtil {
   protected DOMUtil() {
   }

   public static Element getFirstChildElement(Node parent) {
      for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
         if (child.getNodeType() == 1) {
            return (Element)child;
         }
      }

      return null;
   }

   public static Element getLastChildElement(Node parent) {
      for(Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
         if (child.getNodeType() == 1) {
            return (Element)child;
         }
      }

      return null;
   }

   public static Element getNextSiblingElement(Node node) {
      for(Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
         if (sibling.getNodeType() == 1) {
            return (Element)sibling;
         }
      }

      return null;
   }

   public static Element getFirstChildElement(Node parent, String elemName) {
      for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
         if (child.getNodeType() == 1 && child.getLocalName().equals(elemName)) {
            return (Element)child;
         }
      }

      return null;
   }

   public static Element getLastChildElement(Node parent, String elemName) {
      for(Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
         if (child.getNodeType() == 1 && child.getLocalName().equals(elemName)) {
            return (Element)child;
         }
      }

      return null;
   }

   public static Element getNextSiblingElement(Node node, String elemName) {
      for(Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
         if (sibling.getNodeType() == 1 && sibling.getLocalName().equals(elemName)) {
            return (Element)sibling;
         }
      }

      return null;
   }

   public static Element getFirstChildElementNS(Node parent, String uri, String localpart) {
      for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
         if (child.getNodeType() == 1) {
            String childURI = child.getNamespaceURI();
            if (childURI != null && childURI.equals(uri) && child.getLocalName().equals(localpart)) {
               return (Element)child;
            }
         }
      }

      return null;
   }

   public static Element getLastChildElementNS(Node parent, String uri, String localpart) {
      for(Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
         if (child.getNodeType() == 1) {
            String childURI = child.getNamespaceURI();
            if (childURI != null && childURI.equals(uri) && child.getLocalName().equals(localpart)) {
               return (Element)child;
            }
         }
      }

      return null;
   }

   public static Element getNextSiblingElementNS(Node node, String uri, String localpart) {
      for(Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
         if (sibling.getNodeType() == 1) {
            String siblingURI = sibling.getNamespaceURI();
            if (siblingURI != null && siblingURI.equals(uri) && sibling.getLocalName().equals(localpart)) {
               return (Element)sibling;
            }
         }
      }

      return null;
   }

   public static Element getFirstChildElement(Node parent, String[] elemNames) {
      for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
         if (child.getNodeType() == 1) {
            for(String elemName : elemNames) {
               if (child.getLocalName().equals(elemName)) {
                  return (Element)child;
               }
            }
         }
      }

      return null;
   }

   public static Element getLastChildElement(Node parent, String[] elemNames) {
      for(Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
         if (child.getNodeType() == 1) {
            for(String elemName : elemNames) {
               if (child.getLocalName().equals(elemName)) {
                  return (Element)child;
               }
            }
         }
      }

      return null;
   }

   public static Element getNextSiblingElement(Node node, String[] elemNames) {
      for(Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
         if (sibling.getNodeType() == 1) {
            for(String elemName : elemNames) {
               if (sibling.getLocalName().equals(elemName)) {
                  return (Element)sibling;
               }
            }
         }
      }

      return null;
   }

   public static Element getFirstChildElementNS(Node parent, String[][] elemNames) {
      for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
         if (child.getNodeType() == 1) {
            for(String[] elemName : elemNames) {
               String uri = child.getNamespaceURI();
               if (uri != null && uri.equals(elemName[0]) && child.getLocalName().equals(elemName[1])) {
                  return (Element)child;
               }
            }
         }
      }

      return null;
   }

   public static Element getLastChildElementNS(Node parent, String[][] elemNames) {
      for(Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
         if (child.getNodeType() == 1) {
            for(String[] elemName : elemNames) {
               String uri = child.getNamespaceURI();
               if (uri != null && uri.equals(elemName[0]) && child.getLocalName().equals(elemName[1])) {
                  return (Element)child;
               }
            }
         }
      }

      return null;
   }

   public static Element getNextSiblingElementNS(Node node, String[][] elemNames) {
      for(Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
         if (sibling.getNodeType() == 1) {
            for(String[] elemName : elemNames) {
               String uri = sibling.getNamespaceURI();
               if (uri != null && uri.equals(elemName[0]) && sibling.getLocalName().equals(elemName[1])) {
                  return (Element)sibling;
               }
            }
         }
      }

      return null;
   }

   public static Element getFirstChildElement(Node parent, String elemName, String attrName, String attrValue) {
      for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
         if (child.getNodeType() == 1) {
            Element element = (Element)child;
            if (element.getLocalName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
               return element;
            }
         }
      }

      return null;
   }

   public static Element getLastChildElement(Node parent, String elemName, String attrName, String attrValue) {
      for(Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
         if (child.getNodeType() == 1) {
            Element element = (Element)child;
            if (element.getLocalName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
               return element;
            }
         }
      }

      return null;
   }

   public static Element getNextSiblingElement(Node node, String elemName, String attrName, String attrValue) {
      for(Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
         if (sibling.getNodeType() == 1) {
            Element element = (Element)sibling;
            if (element.getLocalName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
               return element;
            }
         }
      }

      return null;
   }

   public static String getChildText(Node node) {
      if (node == null) {
         return null;
      } else {
         StringBuilder str = new StringBuilder();

         for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            short type = child.getNodeType();
            if (type == 3) {
               str.append(child.getNodeValue());
            } else if (type == 4) {
               str.append(getChildText(child));
            }
         }

         return str.toString();
      }
   }

   public static String getName(Node node) {
      return node.getLocalName();
   }

   public static String getLocalName(Node node) {
      String name = node.getLocalName();
      return name != null ? name : node.getLocalName();
   }

   public static Element getParent(Element elem) {
      Node parent = elem.getParentNode();
      return parent instanceof Element ? (Element)parent : null;
   }

   public static Document getDocument(Node node) {
      return node.getOwnerDocument();
   }

   public static Element getRoot(Document doc) {
      return doc.getDocumentElement();
   }

   public static Attr getAttr(Element elem, String name) {
      return elem.getAttributeNode(name);
   }

   public static Attr getAttrNS(Element elem, String nsUri, String localName) {
      return elem.getAttributeNodeNS(nsUri, localName);
   }

   public static Attr[] getAttrs(Element elem) {
      NamedNodeMap attrMap = elem.getAttributes();
      Attr[] attrArray = new Attr[attrMap.getLength()];

      for(int i = 0; i < attrMap.getLength(); ++i) {
         attrArray[i] = (Attr)attrMap.item(i);
      }

      return attrArray;
   }

   public static String getValue(Attr attribute) {
      return attribute.getValue();
   }

   public static String getAttrValue(Element elem, String name) {
      return elem.getAttribute(name);
   }

   public static String getAttrValueNS(Element elem, String nsUri, String localName) {
      return elem.getAttributeNS(nsUri, localName);
   }

   public static String getNamespaceURI(Node node) {
      return node.getNamespaceURI();
   }
}
