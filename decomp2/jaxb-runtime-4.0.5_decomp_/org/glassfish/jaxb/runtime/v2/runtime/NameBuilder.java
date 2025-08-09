package org.glassfish.jaxb.runtime.v2.runtime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;

public final class NameBuilder {
   private Map uriIndexMap = new HashMap();
   private Set nonDefaultableNsUris = new HashSet();
   private Map localNameIndexMap = new HashMap();
   private QNameMap elementQNameIndexMap = new QNameMap();
   private QNameMap attributeQNameIndexMap = new QNameMap();

   public Name createElementName(QName name) {
      return this.createElementName(name.getNamespaceURI(), name.getLocalPart());
   }

   public Name createElementName(String nsUri, String localName) {
      return this.createName(nsUri, localName, false, this.elementQNameIndexMap);
   }

   public Name createAttributeName(QName name) {
      return this.createAttributeName(name.getNamespaceURI(), name.getLocalPart());
   }

   public Name createAttributeName(String nsUri, String localName) {
      assert nsUri.intern() == nsUri;

      assert localName.intern() == localName;

      if (nsUri.length() == 0) {
         return new Name(this.allocIndex(this.attributeQNameIndexMap, "", localName), -1, nsUri, this.allocIndex(this.localNameIndexMap, localName), localName, true);
      } else {
         this.nonDefaultableNsUris.add(nsUri);
         return this.createName(nsUri, localName, true, this.attributeQNameIndexMap);
      }
   }

   private Name createName(String nsUri, String localName, boolean isAttribute, QNameMap map) {
      assert nsUri.intern() == nsUri;

      assert localName.intern() == localName;

      return new Name(this.allocIndex(map, nsUri, localName), this.allocIndex(this.uriIndexMap, nsUri), nsUri, this.allocIndex(this.localNameIndexMap, localName), localName, isAttribute);
   }

   private int allocIndex(Map map, String str) {
      Integer i = (Integer)map.get(str);
      if (i == null) {
         i = map.size();
         map.put(str, i);
      }

      return i;
   }

   private int allocIndex(QNameMap map, String nsUri, String localName) {
      Integer i = (Integer)map.get(nsUri, localName);
      if (i == null) {
         i = map.size();
         map.put(nsUri, localName, i);
      }

      return i;
   }

   public NameList conclude() {
      boolean[] nsUriCannotBeDefaulted = new boolean[this.uriIndexMap.size()];

      for(Map.Entry e : this.uriIndexMap.entrySet()) {
         nsUriCannotBeDefaulted[(Integer)e.getValue()] = this.nonDefaultableNsUris.contains(e.getKey());
      }

      NameList r = new NameList(this.list(this.uriIndexMap), nsUriCannotBeDefaulted, this.list(this.localNameIndexMap), this.elementQNameIndexMap.size(), this.attributeQNameIndexMap.size());
      this.uriIndexMap = null;
      this.localNameIndexMap = null;
      return r;
   }

   private String[] list(Map map) {
      String[] r = new String[map.size()];

      for(Map.Entry e : map.entrySet()) {
         r[(Integer)e.getValue()] = (String)e.getKey();
      }

      return r;
   }
}
