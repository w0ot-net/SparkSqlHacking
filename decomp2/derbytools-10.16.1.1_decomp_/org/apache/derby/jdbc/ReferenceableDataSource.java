package org.apache.derby.jdbc;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

public class ReferenceableDataSource extends BasicEmbeddedDataSource40 implements ObjectFactory {
   private static final long serialVersionUID = 1872877359127597176L;
   private static final Class[] STRING_ARG = new Class[]{"".getClass()};
   private static final Class[] INT_ARG;
   private static final Class[] BOOLEAN_ARG;
   private static final Class[] SHORT_ARG;

   public Object getObjectInstance(Object var1, Name var2, Context var3, Hashtable var4) throws Exception {
      Object var5 = null;
      if (var1 instanceof Reference var6) {
         String var7 = var6.getClassName();
         if (var7 != null && var7.startsWith("org.apache.derby.jdbc.Embedded")) {
            var5 = Class.forName(var7).getConstructor().newInstance();
            setBeanProperties(var5, var6);
         }
      }

      return var5;
   }

   private static void setBeanProperties(Object var0, Reference var1) throws Exception {
      Method var7;
      Object var8;
      for(Enumeration var2 = var1.getAll(); var2.hasMoreElements(); var7.invoke(var0, var8)) {
         RefAddr var3 = (RefAddr)var2.nextElement();
         String var4 = var3.getType();
         String var5 = (String)var3.getContent();
         String var10000 = var4.substring(0, 1).toUpperCase(Locale.ENGLISH);
         String var6 = "set" + var10000 + var4.substring(1);

         try {
            var7 = var0.getClass().getMethod(var6, STRING_ARG);
            var8 = var5;
         } catch (NoSuchMethodException var14) {
            try {
               var7 = var0.getClass().getMethod(var6, INT_ARG);
               var8 = Integer.valueOf(var5);
            } catch (NoSuchMethodException var13) {
               try {
                  var7 = var0.getClass().getMethod(var6, BOOLEAN_ARG);
                  var8 = Boolean.valueOf(var5);
               } catch (NoSuchMethodException var12) {
                  var7 = var0.getClass().getMethod(var6, SHORT_ARG);
                  var8 = Short.valueOf(var5);
               }
            }
         }
      }

   }

   static {
      INT_ARG = new Class[]{Integer.TYPE};
      BOOLEAN_ARG = new Class[]{Boolean.TYPE};
      SHORT_ARG = new Class[]{Short.TYPE};
   }
}
