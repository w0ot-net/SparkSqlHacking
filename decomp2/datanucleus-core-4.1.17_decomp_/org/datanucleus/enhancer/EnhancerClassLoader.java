package org.datanucleus.enhancer;

import java.net.URL;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;

public final class EnhancerClassLoader extends ClassLoader {
   ClassLoaderResolver delegate;
   boolean loadingClass = false;
   boolean loadingResource = false;

   public EnhancerClassLoader() {
      super(Thread.currentThread().getContextClassLoader());
   }

   public EnhancerClassLoader(ClassLoaderResolver iDelegate) {
      this.delegate = iDelegate;
   }

   public synchronized void defineClass(String fullClassName, byte[] bytes, ClassLoaderResolver clr) {
      ClassLoaderResolver oldDelegate = this.delegate;
      this.delegate = clr;

      try {
         this.defineClass(fullClassName, bytes, 0, bytes.length);
      } finally {
         this.delegate = oldDelegate;
      }

   }

   public synchronized Class loadClass(String name) throws ClassNotFoundException {
      if (this.loadingClass) {
         throw new ClassNotFoundException("Class " + name + " not found");
      } else {
         this.loadingClass = true;

         Class cnrex;
         try {
            if (this.delegate == null) {
               cnrex = super.loadClass(name);
               return cnrex;
            }

            try {
               cnrex = this.delegate.classForName(name);
            } catch (ClassNotResolvedException cnrex) {
               throw new ClassNotFoundException(cnrex.toString(), cnrex);
            }
         } catch (ClassNotFoundException ex) {
            if (this.delegate == null) {
               throw ex;
            }

            try {
               Class var3 = this.delegate.classForName(name);
               return var3;
            } catch (ClassNotResolvedException cnrex) {
               throw new ClassNotFoundException(cnrex.toString(), cnrex);
            }
         } finally {
            this.loadingClass = false;
         }

         return cnrex;
      }
   }

   protected synchronized URL findResource(String name) {
      if (this.loadingResource) {
         return null;
      } else {
         this.loadingResource = true;

         URL var3;
         try {
            URL url = super.findResource(name);
            if (url == null && this.delegate != null) {
               url = this.delegate.getResource(name, (ClassLoader)null);
            }

            var3 = url;
         } finally {
            this.loadingResource = false;
         }

         return var3;
      }
   }
}
