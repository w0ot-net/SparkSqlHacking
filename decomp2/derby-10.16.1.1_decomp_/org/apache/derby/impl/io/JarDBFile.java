package org.apache.derby.impl.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import org.apache.derby.io.StorageFile;

class JarDBFile extends InputStreamFile {
   JarDBFile(JarStorageFactory var1, String var2) {
      super((BaseStorageFactory)var1, var2);
   }

   JarDBFile(JarStorageFactory var1, String var2, String var3) {
      super(var1, var2, var3);
   }

   JarDBFile(JarDBFile var1, String var2) {
      super((InputStreamFile)var1, var2);
   }

   private JarDBFile(JarStorageFactory var1, String var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean exists() {
      return this.getEntry() != null;
   }

   private ZipEntry getEntry() {
      return ((JarStorageFactory)this.storageFactory).zipData.getEntry(this.path);
   }

   StorageFile getParentDir(int var1) {
      return new JarDBFile((JarStorageFactory)this.storageFactory, this.path, var1);
   }

   public InputStream getInputStream() throws FileNotFoundException {
      ZipEntry var1 = this.getEntry();
      if (var1 == null) {
         throw new FileNotFoundException(this.path);
      } else {
         try {
            return ((JarStorageFactory)this.storageFactory).zipData.getInputStream(var1);
         } catch (IOException var3) {
            throw new FileNotFoundException(this.path);
         }
      }
   }

   public String toString() {
      return this.path;
   }
}
