package org.apache.derby.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Hashtable;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

public class EmbeddedDataSource extends ReferenceableDataSource implements Referenceable {
   private static final long serialVersionUID = -4945135214995641181L;

   public final Reference getReference() throws NamingException {
      Reference var1 = new Reference(this.getClass().getName(), "org.apache.derby.jdbc.ReferenceableDataSource", (String)null);
      addBeanProperties(this, var1);
      return var1;
   }

   private static void addBeanProperties(Object var0, Reference var1) {
      Method[] var2 = var0.getClass().getMethods();

      for(Method var6 : var2) {
         if (var6.getParameterTypes().length == 0 && !Modifier.isStatic(var6.getModifiers())) {
            String var7 = var6.getName();
            if (var7.length() >= 5 && var7.startsWith("get")) {
               Class var8 = var6.getReturnType();
               if (Integer.TYPE.equals(var8) || Short.TYPE.equals(var8) || String.class.equals(var8) || Boolean.TYPE.equals(var8)) {
                  String var9 = var7.substring(3, 4).toLowerCase(Locale.ENGLISH).concat(var7.substring(4));

                  try {
                     Object var10 = var6.invoke(var0, (Object[])null);
                     if (var10 != null) {
                        var1.add(new StringRefAddr(var9, var10.toString()));
                     }
                  } catch (IllegalAccessException var11) {
                  } catch (InvocationTargetException var12) {
                  }
               }
            }
         }
      }

   }

   public Object getObjectInstance(Object var1, Name var2, Context var3, Hashtable var4) throws Exception {
      return super.getObjectInstance(var1, var2, var3, var4);
   }
}
