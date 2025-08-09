package org.apache.derby.impl.io;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;
import org.apache.derby.io.StorageFile;

public class JarStorageFactory extends BaseStorageFactory {
   ZipFile zipData;

   StorageFile newPersistentFile(String var1) {
      return new JarDBFile(this, var1);
   }

   StorageFile newPersistentFile(String var1, String var2) {
      return (StorageFile)(var1 != null && var1.length() != 0 ? new JarDBFile(this, var1, var2) : this.newPersistentFile(var2));
   }

   StorageFile newPersistentFile(StorageFile var1, String var2) {
      return (StorageFile)(var1 == null ? this.newPersistentFile(var2) : new JarDBFile((JarDBFile)var1, var2));
   }

   void doInit() throws IOException {
      if (this.dataDirectory != null) {
         int var1;
         for(var1 = 0; var1 < this.dataDirectory.length() && Character.isSpaceChar(this.dataDirectory.charAt(var1)); ++var1) {
         }

         int var2 = -1;
         int var3 = -1;
         if (var1 < this.dataDirectory.length()) {
            var2 = this.dataDirectory.indexOf(40, var1);
            if (var2 >= 0) {
               var3 = this.dataDirectory.lastIndexOf(41);
            }
         }

         Object var4 = null;
         File var6;
         if (var3 > 0) {
            var6 = this.getJarFile(this.dataDirectory.substring(var2 + 1, var3));

            for(var1 = var3 + 1; var1 < this.dataDirectory.length() && Character.isSpaceChar(this.dataDirectory.charAt(var1)); ++var1) {
            }

            this.dataDirectory = this.dataDirectory.substring(var1, this.dataDirectory.length());
         } else {
            var6 = this.getJarFile(this.dataDirectory);
            this.dataDirectory = "";
         }

         this.zipData = new ZipFile(var6);
         String var10001 = var6.getCanonicalPath();
         this.canonicalName = "(" + var10001 + ")" + this.dataDirectory;
         this.separatedDataDirectory = this.dataDirectory + "/";
         this.createTempDir();
      }
   }

   public void shutdown() {
      if (this.zipData != null) {
         try {
            this.zipData.close();
         } catch (IOException var2) {
         }

         this.zipData = null;
      }

   }

   private File getJarFile(String var1) {
      File var2 = new File(var1);
      if (this.home != null && !var2.isAbsolute()) {
         var2 = new File(this.home, var1);
      }

      return var2;
   }
}
