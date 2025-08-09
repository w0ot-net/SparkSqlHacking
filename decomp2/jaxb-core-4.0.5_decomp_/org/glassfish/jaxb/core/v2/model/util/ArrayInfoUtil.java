package org.glassfish.jaxb.core.v2.model.util;

import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.TODO;

public class ArrayInfoUtil {
   private ArrayInfoUtil() {
   }

   public static QName calcArrayTypeName(QName n) {
      String uri;
      if (n.getNamespaceURI().equals("http://www.w3.org/2001/XMLSchema")) {
         TODO.checkSpec("this URI");
         uri = "http://jaxb.dev.java.net/array";
      } else {
         uri = n.getNamespaceURI();
      }

      return new QName(uri, n.getLocalPart() + "Array");
   }
}
