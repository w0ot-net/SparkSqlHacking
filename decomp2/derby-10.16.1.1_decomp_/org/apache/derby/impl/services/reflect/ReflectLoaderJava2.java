package org.apache.derby.impl.services.reflect;

import org.apache.derby.iapi.util.ByteArray;

final class ReflectLoaderJava2 extends ClassLoader {
   private final DatabaseClasses cf;

   ReflectLoaderJava2(ClassLoader var1, DatabaseClasses var2) {
      super(var1);
      this.cf = var2;
   }

   protected Class findClass(String var1) throws ClassNotFoundException {
      return this.cf.loadApplicationClass(var1);
   }

   LoadedGeneratedClass loadGeneratedClass(String var1, ByteArray var2) {
      Class var3 = this.defineClass(var1, var2.getArray(), var2.getOffset(), var2.getLength());
      this.resolveClass(var3);
      return new ReflectGeneratedClass(this.cf, var3);
   }
}
