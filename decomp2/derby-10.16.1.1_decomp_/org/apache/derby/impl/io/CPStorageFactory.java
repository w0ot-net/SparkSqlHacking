package org.apache.derby.impl.io;

import java.io.IOException;
import org.apache.derby.io.StorageFile;

public class CPStorageFactory extends BaseStorageFactory {
   StorageFile newPersistentFile(String var1) {
      return new CPFile(this, var1);
   }

   StorageFile newPersistentFile(String var1, String var2) {
      return (StorageFile)(var1 != null && var1.length() != 0 ? new CPFile(this, var1, var2) : this.newPersistentFile(var2));
   }

   StorageFile newPersistentFile(StorageFile var1, String var2) {
      return (StorageFile)(var1 == null ? this.newPersistentFile(var2) : new CPFile((CPFile)var1, var2));
   }

   void doInit() throws IOException {
      if (this.dataDirectory != null) {
         this.separatedDataDirectory = this.dataDirectory + "/";
         this.canonicalName = this.dataDirectory;
         this.createTempDir();
      }

   }
}
