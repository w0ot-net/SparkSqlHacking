package org.apache.derby.impl.io;

import java.io.IOException;
import org.apache.derby.io.StorageFile;

public class URLStorageFactory extends BaseStorageFactory {
   StorageFile newPersistentFile(String var1) {
      return new URLFile(this, var1);
   }

   StorageFile newPersistentFile(String var1, String var2) {
      return (StorageFile)(var1 != null && var1.length() != 0 ? new URLFile(this, var1, var2) : this.newPersistentFile(var2));
   }

   StorageFile newPersistentFile(StorageFile var1, String var2) {
      return (StorageFile)(var1 == null ? this.newPersistentFile(var2) : new URLFile((URLFile)var1, var2));
   }

   void doInit() throws IOException {
      if (this.dataDirectory != null) {
         if (this.dataDirectory.endsWith("/")) {
            this.separatedDataDirectory = this.dataDirectory;
            this.dataDirectory = this.dataDirectory.substring(0, this.dataDirectory.length() - 1);
         } else {
            this.separatedDataDirectory = this.dataDirectory + "/";
         }

         this.canonicalName = this.dataDirectory;
         this.createTempDir();
      }

   }
}
