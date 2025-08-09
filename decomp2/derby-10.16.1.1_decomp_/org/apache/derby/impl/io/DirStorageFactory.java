package org.apache.derby.impl.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.SyncFailedException;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.WritableStorageFactory;

public class DirStorageFactory extends BaseStorageFactory implements WritableStorageFactory {
   public final StorageFile newStorageFile(String var1) {
      return this.newPersistentFile(var1);
   }

   public final StorageFile newStorageFile(String var1, String var2) {
      return this.newPersistentFile(var1, var2);
   }

   public final StorageFile newStorageFile(StorageFile var1, String var2) {
      return this.newPersistentFile(var1, var2);
   }

   StorageFile newPersistentFile(String var1) {
      return var1 == null ? new DirFile(this.dataDirectory) : new DirFile(this.dataDirectory, var1);
   }

   StorageFile newPersistentFile(String var1, String var2) {
      return new DirFile(this.separatedDataDirectory + var1, var2);
   }

   StorageFile newPersistentFile(StorageFile var1, String var2) {
      return new DirFile((DirFile)var1, var2);
   }

   public void sync(OutputStream var1, boolean var2) throws IOException, SyncFailedException {
      ((FileOutputStream)var1).getFD().sync();
   }

   public boolean supportsWriteSync() {
      return true;
   }

   public boolean isReadOnlyDatabase() {
      return false;
   }

   public boolean supportsRandomAccess() {
      return true;
   }

   void doInit() throws IOException {
      if (this.dataDirectory != null) {
         File var1 = new File(this.dataDirectory);
         Object var2 = null;
         File var4;
         if (var1.isAbsolute()) {
            var4 = var1;
         } else if (this.home != null && this.dataDirectory.startsWith(this.home)) {
            var4 = var1;
         } else {
            var4 = new File(this.home, this.dataDirectory);
            if (this.home != null) {
               String var10001 = this.home;
               this.dataDirectory = var10001 + this.getSeparator() + this.dataDirectory;
            }
         }

         this.canonicalName = var4.getCanonicalPath();
         this.createTempDir();
         String var5 = this.dataDirectory;
         this.separatedDataDirectory = var5 + this.getSeparator();
      } else if (this.home != null) {
         File var3 = new File(this.home);
         this.dataDirectory = var3.getCanonicalPath();
         String var6 = this.dataDirectory;
         this.separatedDataDirectory = var6 + this.getSeparator();
      }

   }
}
