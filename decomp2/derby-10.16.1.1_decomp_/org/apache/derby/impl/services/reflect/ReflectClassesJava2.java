package org.apache.derby.impl.services.reflect;

import java.util.HashMap;
import org.apache.derby.iapi.util.ByteArray;

public class ReflectClassesJava2 extends DatabaseClasses {
   private HashMap preCompiled;
   private int action = -1;

   synchronized LoadedGeneratedClass loadGeneratedClassFromData(String var1, ByteArray var2) {
      if (var2 != null && var2.getArray() != null) {
         int var7 = var1.lastIndexOf(".");
         String var8;
         if (var7 < 0) {
            var8 = "";
         } else {
            var8 = var1.substring(0, var7 + 1);
         }

         if (!"org.apache.derby.exe.".equals(var8)) {
            throw new IllegalArgumentException(var1);
         } else {
            this.action = 1;
            return ((ReflectLoaderJava2)this.run()).loadGeneratedClass(var1, var2);
         }
      } else {
         if (this.preCompiled == null) {
            this.preCompiled = new HashMap();
         } else {
            ReflectGeneratedClass var3 = (ReflectGeneratedClass)this.preCompiled.get(var1);
            if (var3 != null) {
               return var3;
            }
         }

         try {
            Class var6 = Class.forName(var1);
            ReflectGeneratedClass var4 = new ReflectGeneratedClass(this, var6);
            this.preCompiled.put(var1, var4);
            return var4;
         } catch (ClassNotFoundException var5) {
            throw new NoClassDefFoundError(var5.toString());
         }
      }
   }

   public final Object run() {
      try {
         switch (this.action) {
            case 1:
               ReflectLoaderJava2 var5 = new ReflectLoaderJava2(this.getClass().getClassLoader(), this);
               return var5;
            case 2:
               ClassLoader var1 = Thread.currentThread().getContextClassLoader();
               return var1;
            default:
               Object var6 = null;
               return var6;
         }
      } finally {
         this.action = -1;
      }
   }

   Class loadClassNotInDatabaseJar(String var1) throws ClassNotFoundException {
      Object var2 = null;

      try {
         ClassLoader var3;
         synchronized(this) {
            this.action = 2;
            var3 = (ClassLoader)this.run();
         }

         var8 = var3 != null ? var3.loadClass(var1) : Class.forName(var1);
      } catch (ClassNotFoundException var7) {
         var8 = Class.forName(var1);
      }

      return var8;
   }
}
