package org.glassfish.jersey.internal.util;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;

public class SimpleNamespaceResolver implements NamespaceContext {
   private final String prefix;
   private final String nsURI;

   public SimpleNamespaceResolver(String prefix, String nsURI) {
      this.prefix = prefix;
      this.nsURI = nsURI;
   }

   public String getNamespaceURI(String prefix) {
      return prefix.equals(this.prefix) ? this.nsURI : "";
   }

   public String getPrefix(String namespaceURI) {
      return namespaceURI.equals(this.nsURI) ? this.prefix : null;
   }

   public Iterator getPrefixes(String namespaceURI) {
      return null;
   }
}
