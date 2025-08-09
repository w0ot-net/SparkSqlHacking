package org.apache.derby.iapi.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;

public final class DoubleProperties extends Properties {
   private final Properties read;
   private final Properties write;

   public DoubleProperties(Properties var1, Properties var2) {
      this.read = var1;
      this.write = var2;
   }

   public Object put(Object var1, Object var2) {
      return this.write.put(var1, var2);
   }

   public String getProperty(String var1) {
      return this.read.getProperty(var1, this.write.getProperty(var1));
   }

   public String getProperty(String var1, String var2) {
      return this.read.getProperty(var1, this.write.getProperty(var1, var2));
   }

   public Enumeration propertyNames() {
      HashSet var1 = new HashSet();
      addAllNames(this.write, var1);
      addAllNames(this.read, var1);
      return Collections.enumeration(var1);
   }

   private static void addAllNames(Properties var0, HashSet var1) {
      if (var0 != null) {
         Enumeration var2 = var0.propertyNames();

         while(var2.hasMoreElements()) {
            var1.add(var2.nextElement());
         }
      }

   }
}
