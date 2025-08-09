package org.apache.derby.iapi.services.cache;

import java.util.Hashtable;

abstract class ClassSizeCatalog extends Hashtable {
   private static final ClassSizeCatalog INSTANCE;

   static ClassSizeCatalog getInstance() {
      return INSTANCE;
   }

   static {
      String var0 = ClassSizeCatalog.class.getName() + "Impl";

      try {
         Class var1 = Class.forName(var0);
         INSTANCE = (ClassSizeCatalog)var1.getConstructor().newInstance();
      } catch (Exception var2) {
         System.out.println("Got error while instantiating " + var0 + ": " + var2.getMessage());
         var2.printStackTrace();
         throw new ExceptionInInitializerError(var2);
      }
   }
}
