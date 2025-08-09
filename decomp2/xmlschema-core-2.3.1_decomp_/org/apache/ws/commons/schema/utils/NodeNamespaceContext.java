package org.apache.ws.commons.schema.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public final class NodeNamespaceContext implements NamespacePrefixList, Serializable {
   private static final long serialVersionUID = 1L;
   private static final boolean DOM_LEVEL_3;
   private static final Collection XML_NS_PREFIX_COLLECTION = Collections.singletonList("xml");
   private static final Collection XMLNS_ATTRIBUTE_COLLECTION = Collections.singletonList("xmlns");
   private final Map declarations;
   private String[] prefixes;

   private NodeNamespaceContext(Map decls) {
      this.declarations = decls;
   }

   public static String getNamespacePrefix(Element el, String ns) {
      return DOM_LEVEL_3 ? getNamespacePrefixDomLevel3(el, ns) : getNamespaceContext(el).getPrefix(ns);
   }

   private static String getNamespacePrefixDomLevel3(Element el, String ns) {
      return el.lookupPrefix(ns);
   }

   public static String getNamespaceURI(Element el, String pfx) {
      if ("xml".equals(pfx)) {
         return "http://www.w3.org/XML/1998/namespace";
      } else if ("xmlns".equals(pfx)) {
         return "http://www.w3.org/2000/xmlns/";
      } else {
         return DOM_LEVEL_3 ? getNamespaceURIDomLevel3(el, pfx) : getNamespaceContext(el).getNamespaceURI(pfx);
      }
   }

   private static String getNamespaceURIDomLevel3(Element el, String pfx) {
      if ("".equals(pfx)) {
         pfx = null;
      }

      return el.lookupNamespaceURI(pfx);
   }

   public static NodeNamespaceContext getNamespaceContext(Node pNode) {
      final Map<String, String> declarations = new HashMap();
      (new PrefixCollector() {
         protected void declare(String pPrefix, String pNamespaceURI) {
            declarations.put(pPrefix, pNamespaceURI);
         }
      }).searchAllPrefixDeclarations(pNode);
      return new NodeNamespaceContext(declarations);
   }

   public String[] getDeclaredPrefixes() {
      if (this.prefixes == null) {
         Collection<String> keys = this.declarations.keySet();
         this.prefixes = (String[])keys.toArray(new String[keys.size()]);
      }

      return this.prefixes;
   }

   public String getNamespaceURI(String pPrefix) {
      if (pPrefix == null) {
         throw new IllegalArgumentException("The prefix must not be null.");
      } else if ("xml".equals(pPrefix)) {
         return "http://www.w3.org/XML/1998/namespace";
      } else if ("xmlns".equals(pPrefix)) {
         return "http://www.w3.org/2000/xmlns/";
      } else {
         String uri = (String)this.declarations.get(pPrefix);
         return uri == null ? "" : uri;
      }
   }

   public String getPrefix(String pNamespaceURI) {
      if (pNamespaceURI == null) {
         throw new IllegalArgumentException("The namespace URI must not be null.");
      } else if ("http://www.w3.org/XML/1998/namespace".equals(pNamespaceURI)) {
         return "xml";
      } else if ("http://www.w3.org/2000/xmlns/".equals(pNamespaceURI)) {
         return "xmlns";
      } else {
         for(Map.Entry entry : this.declarations.entrySet()) {
            if (pNamespaceURI.equals(entry.getValue())) {
               return (String)entry.getKey();
            }
         }

         return null;
      }
   }

   public Iterator getPrefixes(String pNamespaceURI) {
      if (pNamespaceURI == null) {
         throw new IllegalArgumentException("The namespace URI must not be null.");
      } else if ("http://www.w3.org/XML/1998/namespace".equals(pNamespaceURI)) {
         return XML_NS_PREFIX_COLLECTION.iterator();
      } else if ("http://www.w3.org/2000/xmlns/".equals(pNamespaceURI)) {
         return XMLNS_ATTRIBUTE_COLLECTION.iterator();
      } else {
         List<String> list = new ArrayList();

         for(Map.Entry entry : this.declarations.entrySet()) {
            if (pNamespaceURI.equals(entry.getValue())) {
               list.add(entry.getKey());
            }
         }

         return list.iterator();
      }
   }

   static {
      boolean level3 = false;

      try {
         Class<?> cls = Class.forName("org.w3c.dom.UserDataHandler", false, Node.class.getClassLoader());
         Node.class.getMethod("getUserData", String.class);
         Node.class.getMethod("setUserData", String.class, Object.class, cls);
         level3 = true;
      } catch (Throwable var2) {
         level3 = false;
      }

      DOM_LEVEL_3 = level3;
   }
}
