package org.apache.derby.impl.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.derby.impl.io.vfmem.DataStore;
import org.apache.derby.impl.io.vfmem.PathUtil;
import org.apache.derby.impl.io.vfmem.VirtualFile;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.WritableStorageFactory;

public class VFMemoryStorageFactory implements StorageFactory, WritableStorageFactory {
   private static final Map DATABASES = new HashMap();
   private static final DataStore DUMMY_STORE = new DataStore("::DUMMY::");
   private String canonicalName;
   private StorageFile dataDirectory;
   private StorageFile tempDir;
   private DataStore dbData;

   public void init(String var1, String var2, String var3, String var4) throws IOException {
      if (var2 != null) {
         if (var1 != null && !(new File(var2)).isAbsolute()) {
            this.canonicalName = (new File(var1, var2)).getCanonicalPath();
         } else {
            this.canonicalName = (new File(var2)).getCanonicalPath();
         }

         synchronized(DATABASES) {
            this.dbData = (DataStore)DATABASES.get(this.canonicalName);
            if (this.dbData != null && this.dbData.scheduledForDeletion()) {
               DATABASES.remove(this.canonicalName);
               this.dbData.purge();
               this.dbDropCleanupInDummy(this.canonicalName);
               this.dbData = null;
            }

            if (this.dbData == null) {
               if (var4 != null) {
                  this.dbData = new DataStore(this.canonicalName);
                  DATABASES.put(this.canonicalName, this.dbData);
               } else {
                  this.dbData = DUMMY_STORE;
               }
            }
         }

         this.dataDirectory = new VirtualFile(this.canonicalName, this.dbData);
         this.tempDir = new VirtualFile(this.normalizePath(this.canonicalName, "tmp"), this.dbData);
      } else if (var1 != null) {
         String var5 = (new File(var1)).getCanonicalPath();
         this.dbData = DUMMY_STORE;
         this.dataDirectory = new VirtualFile(var5, this.dbData);
         this.tempDir = new VirtualFile(this.getSeparator() + "tmp", this.dbData);
      }

      if (var4 != null && this.tempDir != null && !this.tempDir.exists()) {
         this.tempDir.mkdirs();
         this.tempDir.limitAccessToOwner();
      }

   }

   public void shutdown() {
      if (this.dbData.scheduledForDeletion()) {
         DataStore var1;
         synchronized(DATABASES) {
            var1 = (DataStore)DATABASES.remove(this.canonicalName);
            if (var1 != null && var1 == this.dbData) {
               this.dbDropCleanupInDummy(this.canonicalName);
            }
         }

         if (var1 != null && var1 == this.dbData) {
            this.dbData.purge();
            this.dbData = null;
         }
      }

   }

   public String getCanonicalName() {
      return this.canonicalName;
   }

   public void setCanonicalName(String var1) {
      this.canonicalName = var1;
   }

   public StorageFile newStorageFile(String var1) {
      return (StorageFile)(var1 == null ? this.dataDirectory : new VirtualFile(this.normalizePath(var1), this.dbData));
   }

   public StorageFile newStorageFile(String var1, String var2) {
      return new VirtualFile(this.normalizePath(var1, var2), this.dbData);
   }

   public StorageFile newStorageFile(StorageFile var1, String var2) {
      return this.newStorageFile(var1 == null ? null : var1.getPath(), var2);
   }

   public StorageFile getTempDir() {
      return this.tempDir;
   }

   public boolean isFast() {
      return true;
   }

   public boolean isReadOnlyDatabase() {
      return false;
   }

   public boolean supportsRandomAccess() {
      return true;
   }

   public int getStorageFactoryVersion() {
      return 1;
   }

   public StorageFile createTemporaryFile(String var1, String var2) {
      if (var2 == null) {
         var2 = ".tmp";
      }

      String var3;
      if (var1 == null) {
         long var10000 = this.dbData.getTempFileCounter();
         var3 = var10000 + var2;
      } else {
         var3 = var1 + this.dbData.getTempFileCounter() + var2;
      }

      return this.newStorageFile(this.tempDir, var3);
   }

   public char getSeparator() {
      return PathUtil.SEP;
   }

   public void sync(OutputStream var1, boolean var2) {
   }

   public boolean supportsWriteSync() {
      return true;
   }

   private String normalizePath(String var1, String var2) {
      if (var1 != null && var1.length() != 0) {
         if (!(new File(var1)).isAbsolute()) {
            var1 = (new File(this.dataDirectory.getPath(), var1)).getPath();
         }
      } else {
         var1 = this.dataDirectory.getPath();
      }

      return (new File(var1, var2)).getPath();
   }

   private String normalizePath(String var1) {
      if (var1 != null && var1.length() != 0) {
         return (new File(var1)).isAbsolute() ? var1 : (new File(this.dataDirectory.getPath(), var1)).getPath();
      } else {
         return this.dataDirectory.getPath();
      }
   }

   private void dbDropCleanupInDummy(String var1) {
      while(var1 != null && DUMMY_STORE.deleteEntry(var1)) {
         var1 = (new File(var1)).getParent();
      }

   }
}
