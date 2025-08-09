package org.apache.ws.commons.schema.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NamespaceMap extends HashMap implements NamespacePrefixList {
   private static final long serialVersionUID = 1L;

   public NamespaceMap() {
   }

   public NamespaceMap(Map map) {
      super(map);
   }

   public void add(String prefix, String namespaceURI) {
      this.put(prefix, namespaceURI);
   }

   public String[] getDeclaredPrefixes() {
      Set<String> keys = this.keySet();
      return (String[])keys.toArray(new String[keys.size()]);
   }

   public String getNamespaceURI(String prefix) {
      if (prefix == null) {
         throw new IllegalArgumentException("The prefix must not be null.");
      } else if ("xml".equals(prefix)) {
         return "http://www.w3.org/XML/1998/namespace";
      } else if ("xmlns".equals(prefix)) {
         return "http://www.w3.org/2000/xmlns/";
      } else {
         Object namespaceURI = this.get(prefix);
         return namespaceURI == null ? "" : namespaceURI.toString();
      }
   }

   public String getPrefix(String namespaceURI) {
      for(Map.Entry entry : this.entrySet()) {
         if (entry.getValue().toString().equals(namespaceURI)) {
            return (String)entry.getKey();
         }
      }

      return null;
   }

   public Iterator getPrefixes(String namespaceURI) {
      List<String> list = new ArrayList();

      for(Map.Entry entry : this.entrySet()) {
         if (entry.getValue().toString().equals(namespaceURI)) {
            list.add(entry.getKey());
         }
      }

      return list.iterator();
   }
}
