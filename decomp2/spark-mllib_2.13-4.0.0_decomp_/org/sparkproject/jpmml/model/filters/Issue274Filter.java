package org.sparkproject.jpmml.model.filters;

import java.util.HashMap;
import java.util.Map;
import org.sparkproject.dmg.pmml.Version;
import org.xml.sax.XMLReader;

public class Issue274Filter extends NamespaceFilter {
   private static final Map mapping = new HashMap();

   public Issue274Filter() {
   }

   public Issue274Filter(XMLReader reader) {
      super(reader);
   }

   public String filterNamespaceURI(String namespaceURI) {
      if (namespaceURI.startsWith("https://")) {
         String standardizedNamespaceURI = (String)mapping.get(namespaceURI);
         if (standardizedNamespaceURI != null) {
            return standardizedNamespaceURI;
         }
      }

      return namespaceURI;
   }

   static {
      Version[] versions = Version.values();

      for(Version version : versions) {
         String namespaceURI = version.getNamespaceURI();
         if (version.isStandard()) {
            mapping.put(namespaceURI.replace("http://", "https://"), namespaceURI);
         }
      }

   }
}
