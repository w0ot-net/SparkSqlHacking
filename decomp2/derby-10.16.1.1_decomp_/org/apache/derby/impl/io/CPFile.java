package org.apache.derby.impl.io;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import org.apache.derby.io.StorageFile;

class CPFile extends InputStreamFile {
   CPFile(CPStorageFactory var1, String var2) {
      super((BaseStorageFactory)var1, var2);
   }

   CPFile(CPStorageFactory var1, String var2, String var3) {
      super(var1, var2, var3);
   }

   CPFile(CPFile var1, String var2) {
      super((InputStreamFile)var1, var2);
   }

   private CPFile(CPStorageFactory var1, String var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean exists() {
      return this.getURL() != null;
   }

   StorageFile getParentDir(int var1) {
      return new CPFile((CPStorageFactory)this.storageFactory, this.path, var1);
   }

   public InputStream getInputStream() throws FileNotFoundException {
      InputStream var1 = null;
      ClassLoader var2 = getContextClassLoader(Thread.currentThread());
      if (var2 != null) {
         var1 = getResourceAsStream(var2, this.path);
      }

      if (var1 == null) {
         var2 = this.getClass().getClassLoader();
         if (var2 != null) {
            var1 = getResourceAsStream(var2, this.path);
         } else {
            var1 = getSystemResourceAsStream(this.path);
         }
      }

      if (var1 == null) {
         throw new FileNotFoundException(this.toString());
      } else {
         return var1;
      }
   }

   private URL getURL() {
      ClassLoader var1 = getContextClassLoader(Thread.currentThread());
      if (var1 != null) {
         URL var2 = getResource(var1, this.path);
         if (var2 != null) {
            return var2;
         }
      }

      var1 = this.getClass().getClassLoader();
      return var1 != null ? getResource(var1, this.path) : getSystemResource(this.path);
   }

   private static ClassLoader getContextClassLoader(Thread var0) {
      return var0.getContextClassLoader();
   }

   private static URL getResource(ClassLoader var0, String var1) {
      return var0.getResource(var1);
   }

   private static URL getSystemResource(String var0) {
      return ClassLoader.getSystemResource(var0);
   }

   private static InputStream getResourceAsStream(ClassLoader var0, String var1) {
      return var0.getResourceAsStream(var1);
   }

   private static InputStream getSystemResourceAsStream(String var0) {
      return ClassLoader.getSystemResourceAsStream(var0);
   }
}
