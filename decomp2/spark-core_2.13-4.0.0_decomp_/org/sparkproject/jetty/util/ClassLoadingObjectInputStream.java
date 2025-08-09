package org.sparkproject.jetty.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Proxy;

public class ClassLoadingObjectInputStream extends ObjectInputStream {
   private ThreadLocal _classloader = new ClassLoaderThreadLocal();

   public ClassLoadingObjectInputStream(InputStream in) throws IOException {
      super(in);
   }

   public ClassLoadingObjectInputStream() throws IOException {
   }

   public Object readObject(ClassLoader loader) throws IOException, ClassNotFoundException {
      Object var2;
      try {
         this._classloader.set(loader);
         var2 = this.readObject();
      } finally {
         this._classloader.set(ClassLoadingObjectInputStream.ClassLoaderThreadLocal.UNSET);
      }

      return var2;
   }

   public Class resolveClass(ObjectStreamClass cl) throws IOException, ClassNotFoundException {
      try {
         ClassLoader loader = (ClassLoader)this._classloader.get();
         if (ClassLoadingObjectInputStream.ClassLoaderThreadLocal.UNSET == loader) {
            loader = Thread.currentThread().getContextClassLoader();
         }

         return Class.forName(cl.getName(), false, loader);
      } catch (ClassNotFoundException var3) {
         return super.resolveClass(cl);
      }
   }

   protected Class resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      ClassLoader nonPublicLoader = null;
      boolean hasNonPublicInterface = false;
      Class<?>[] classObjs = new Class[interfaces.length];

      for(int i = 0; i < interfaces.length; ++i) {
         Class<?> cl = Class.forName(interfaces[i], false, loader);
         if ((cl.getModifiers() & 1) == 0) {
            if (hasNonPublicInterface) {
               if (nonPublicLoader != cl.getClassLoader()) {
                  throw new IllegalAccessError("conflicting non-public interface class loaders");
               }
            } else {
               nonPublicLoader = cl.getClassLoader();
               hasNonPublicInterface = true;
            }
         }

         classObjs[i] = cl;
      }

      try {
         return Proxy.getProxyClass(hasNonPublicInterface ? nonPublicLoader : loader, classObjs);
      } catch (IllegalArgumentException e) {
         throw new ClassNotFoundException((String)null, e);
      }
   }

   protected static class ClassLoaderThreadLocal extends ThreadLocal {
      protected static final ClassLoader UNSET = new ClassLoader() {
      };

      protected ClassLoader initialValue() {
         return UNSET;
      }
   }
}
