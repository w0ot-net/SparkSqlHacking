package io.vertx.core.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class IsolatingClassLoader extends URLClassLoader {
   private volatile boolean closed;
   private List isolatedClasses;

   public IsolatingClassLoader(URL[] urls, ClassLoader parent, List isolatedClasses) {
      super(urls, parent);
      this.isolatedClasses = isolatedClasses;
   }

   protected Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
      synchronized(this.getClassLoadingLock(name)) {
         Class<?> c = this.findLoadedClass(name);
         if (c == null) {
            if (this.isIsolatedClass(name)) {
               if (this.isVertxOrSystemClass(name)) {
                  try {
                     c = this.getParent().loadClass(name);
                  } catch (ClassNotFoundException var8) {
                  }
               }

               if (c == null) {
                  try {
                     c = this.findClass(name);
                  } catch (ClassNotFoundException var7) {
                     c = this.getParent().loadClass(name);
                  }
               }

               if (resolve) {
                  this.resolveClass(c);
               }
            } else {
               c = super.loadClass(name, resolve);
            }
         }

         return c;
      }
   }

   private boolean isIsolatedClass(String name) {
      if (this.isolatedClasses != null) {
         for(String isolated : this.isolatedClasses) {
            if (isolated.endsWith(".*")) {
               String isolatedPackage = isolated.substring(0, isolated.length() - 1);
               String paramPackage = name.substring(0, name.lastIndexOf(46) + 1);
               if (paramPackage.startsWith(isolatedPackage)) {
                  return true;
               }
            } else if (isolated.equals(name)) {
               return true;
            }
         }
      }

      return false;
   }

   public URL getResource(String name) {
      URL url = this.findResource(name);
      if (url == null) {
         url = super.getResource(name);
      }

      return url;
   }

   public Enumeration getResources(String name) throws IOException {
      List<URL> resources = Collections.list(this.findResources(name));
      if (this.getParent() != null) {
         Enumeration<URL> parentResources = this.getParent().getResources(name);
         if (parentResources.hasMoreElements()) {
            resources.addAll(Collections.list(parentResources));
         }
      }

      return Collections.enumeration(resources);
   }

   public void close() throws IOException {
      this.closed = true;
      super.close();
   }

   public boolean isClosed() {
      return this.closed;
   }

   private boolean isVertxOrSystemClass(String name) {
      return name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("sun.*") || name.startsWith("com.sun.") || name.startsWith("io.vertx.core") || name.startsWith("io.netty.") || name.startsWith("com.fasterxml.jackson");
   }
}
