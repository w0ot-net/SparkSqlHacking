package org.glassfish.jaxb.core.util;

import java.net.URL;

public class Which {
   private Which() {
   }

   public static String which(Class clazz) {
      return which(clazz.getName(), SecureLoader.getClassClassLoader(clazz));
   }

   public static String which(String classname, ClassLoader loader) {
      String classnameAsResource = classname.replace('.', '/') + ".class";
      if (loader == null) {
         loader = SecureLoader.getSystemClassLoader();
      }

      URL it = loader.getResource(classnameAsResource);
      return it != null ? it.toString() : null;
   }
}
