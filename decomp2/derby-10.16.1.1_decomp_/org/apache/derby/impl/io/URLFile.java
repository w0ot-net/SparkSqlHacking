package org.apache.derby.impl.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.derby.io.StorageFile;

class URLFile extends InputStreamFile {
   URLFile(URLStorageFactory var1, String var2) {
      super((BaseStorageFactory)var1, var2);
   }

   URLFile(URLStorageFactory var1, String var2, String var3) {
      super(var1, var2, var3);
   }

   URLFile(URLFile var1, String var2) {
      super((InputStreamFile)var1, var2);
   }

   private URLFile(URLStorageFactory var1, String var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean exists() {
      try {
         InputStream var1 = this.getInputStream();
         if (var1 == null) {
            return false;
         } else {
            var1.close();
            return true;
         }
      } catch (IOException var2) {
         return false;
      }
   }

   StorageFile getParentDir(int var1) {
      return new URLFile((URLStorageFactory)this.storageFactory, this.path, var1);
   }

   public InputStream getInputStream() throws FileNotFoundException {
      try {
         URL var1 = new URL(this.path);
         return var1.openStream();
      } catch (IOException var2) {
         throw new FileNotFoundException(this.path);
      }
   }
}
