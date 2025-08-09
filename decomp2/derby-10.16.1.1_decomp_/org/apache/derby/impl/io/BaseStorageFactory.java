package org.apache.derby.impl.io;

import java.io.File;
import java.io.IOException;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;

abstract class BaseStorageFactory implements StorageFactory {
   String home;
   protected StorageFile tempDir;
   protected String tempDirPath;
   protected String dataDirectory;
   protected String separatedDataDirectory;
   protected String uniqueName;
   protected String canonicalName;
   private static final String TEMP_DIR_PREFIX = "derbytmp_";

   public void init(String var1, String var2, String var3, String var4) throws IOException {
      if (var2 != null) {
         this.dataDirectory = var2;
         this.separatedDataDirectory = var2 + this.getSeparator();
      }

      this.home = var1;
      this.uniqueName = var4;
      this.tempDirPath = var3;
      this.doInit();
   }

   abstract void doInit() throws IOException;

   public void shutdown() {
   }

   public String getCanonicalName() throws IOException {
      return this.canonicalName;
   }

   public void setCanonicalName(String var1) {
      this.canonicalName = var1;
   }

   public StorageFile newStorageFile(String var1) {
      return (StorageFile)(var1 != null && this.tempDirPath != null && var1.startsWith(this.tempDirPath) ? new DirFile(var1) : this.newPersistentFile(var1));
   }

   public StorageFile newStorageFile(String var1, String var2) {
      if (var1 == null) {
         return this.newStorageFile(var2);
      } else {
         return (StorageFile)(this.tempDirPath != null && var1.startsWith(this.tempDirPath) ? new DirFile(var1, var2) : this.newPersistentFile(var1, var2));
      }
   }

   public StorageFile newStorageFile(StorageFile var1, String var2) {
      if (var1 == null) {
         return this.newStorageFile(var2);
      } else if (var2 == null) {
         return var1;
      } else {
         return (StorageFile)(this.tempDirPath != null && var1.getPath().startsWith(this.tempDirPath) ? new DirFile((DirFile)var1, var2) : this.newPersistentFile(var1, var2));
      }
   }

   abstract StorageFile newPersistentFile(String var1);

   abstract StorageFile newPersistentFile(String var1, String var2);

   abstract StorageFile newPersistentFile(StorageFile var1, String var2);

   public char getSeparator() {
      return File.separatorChar;
   }

   public StorageFile getTempDir() {
      return this.tempDir;
   }

   public boolean isFast() {
      return false;
   }

   public boolean isReadOnlyDatabase() {
      return true;
   }

   public boolean supportsRandomAccess() {
      return false;
   }

   void createTempDir() throws IOException {
      if (this.uniqueName != null) {
         if (this.tempDirPath != null) {
            this.tempDir = new DirFile(this.tempDirPath, "derbytmp_".concat(this.uniqueName));
         } else if (this.isReadOnlyDatabase()) {
            this.tempDir = new DirFile(this.readOnlyTempRoot(), "derbytmp_".concat(this.uniqueName));
         } else {
            this.tempDir = new DirFile(this.canonicalName, "tmp");
         }

         this.tempDir.deleteAll();
         this.tempDir.mkdirs();
         this.tempDir.limitAccessToOwner();
         this.tempDirPath = this.tempDir.getPath();
      }
   }

   private String readOnlyTempRoot() throws IOException {
      File var1 = File.createTempFile("derby", "tmp");
      String var2 = var1.getParent();
      var1.delete();
      return var2;
   }

   public int getStorageFactoryVersion() {
      return 1;
   }

   public StorageFile createTemporaryFile(String var1, String var2) throws IOException {
      File var3 = File.createTempFile(var1, var2, new File(this.getTempDir().getPath()));
      return this.newStorageFile(this.getTempDir(), var3.getName());
   }
}
