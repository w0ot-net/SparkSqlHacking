package org.glassfish.jaxb.core;

import java.util.logging.Logger;

public final class Utils {
   private Utils() {
   }

   public static Logger getClassLogger() {
      try {
         return Logger.getLogger(StackHelper.getCallerClassName());
      } catch (SecurityException var1) {
         return Logger.getLogger("org.glassfish.jaxb.core");
      }
   }

   public static String getSystemProperty(String name) {
      try {
         return System.getProperty(name);
      } catch (SecurityException var2) {
         return null;
      }
   }
}
