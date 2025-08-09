package org.apache.derby.impl.services.reflect;

import java.lang.reflect.Method;
import java.util.Hashtable;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.shared.common.error.StandardException;

public final class ReflectGeneratedClass extends LoadedGeneratedClass {
   private final Hashtable methodCache = new Hashtable();
   private static final GeneratedMethod[] directs = new GeneratedMethod[10];

   public ReflectGeneratedClass(ClassFactory var1, Class var2) {
      super(var1, var2);
   }

   public GeneratedMethod getMethod(String var1) throws StandardException {
      GeneratedMethod var2 = (GeneratedMethod)this.methodCache.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         try {
            if (var1.length() == 2 && var1.startsWith("e")) {
               int var6 = var1.charAt(1) - 48;
               var2 = directs[var6];
            } else {
               Method var3 = this.getJVMClass().getMethod(var1, (Class[])null);
               var2 = new ReflectMethod(var3);
            }

            this.methodCache.put(var1, var2);
            return var2;
         } catch (NoSuchMethodException var4) {
            throw StandardException.newException("XBCM3.S", var4, new Object[]{this.getName(), var1});
         }
      }
   }

   static {
      for(int var0 = 0; var0 < directs.length; ++var0) {
         directs[var0] = new DirectCall(var0);
      }

   }
}
