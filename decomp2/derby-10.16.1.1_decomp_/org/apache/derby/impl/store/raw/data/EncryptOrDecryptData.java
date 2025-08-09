package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

public class EncryptOrDecryptData {
   private BaseDataFileFactory dataFactory;
   private StorageFactory storageFactory;
   private static final int STORAGE_FILE_EXISTS_ACTION = 1;
   private static final int STORAGE_FILE_DELETE_ACTION = 2;
   private static final int STORAGE_FILE_RENAME_ACTION = 3;
   private int actionCode;
   private StorageFile actionStorageFile;
   private StorageFile actionDestStorageFile;

   public EncryptOrDecryptData(BaseDataFileFactory var1) {
      this.dataFactory = var1;
      this.storageFactory = var1.getStorageFactory();
   }

   public void decryptAllContainers(RawTransaction var1) throws StandardException {
      this.encryptOrDecryptAllContainers(var1, false);
   }

   public void encryptAllContainers(RawTransaction var1) throws StandardException {
      this.encryptOrDecryptAllContainers(var1, true);
   }

   private void encryptOrDecryptAllContainers(RawTransaction var1, boolean var2) throws StandardException {
      String[] var3 = this.dataFactory.getContainerNames();
      if (var3 != null) {
         long var4 = 0L;

         for(int var6 = var3.length - 1; var6 >= 0; --var6) {
            long var7;
            try {
               var7 = Long.parseLong(var3[var6].substring(1, var3[var6].length() - 4), 16);
            } catch (Throwable var10) {
               continue;
            }

            ContainerKey var9 = new ContainerKey(var4, var7);
            this.encryptOrDecryptContainer(var1, var9, var2);
         }
      }

   }

   private void encryptOrDecryptContainer(RawTransaction var1, ContainerKey var2, boolean var3) throws StandardException {
      LockingPolicy var4 = var1.newLockingPolicy(2, 5, true);
      RawContainerHandle var5 = (RawContainerHandle)var1.openContainer(var2, var4, 4);
      EncryptContainerOperation var6 = new EncryptContainerOperation(var5);
      var1.logAndDo(var6);
      this.dataFactory.flush(var1.getLastLogInstant());
      String var7 = this.getFilePath(var2, false);
      StorageFile var8 = this.storageFactory.newStorageFile(var7);
      var5.encryptOrDecryptContainer(var7, var3);
      var5.close();
      if (!this.dataFactory.getPageCache().discard(var2)) {
      }

      if (!this.dataFactory.getContainerCache().discard(var2)) {
      }

      StorageFile var9 = this.dataFactory.getContainerPath(var2, false);
      StorageFile var10 = this.getFile(var2, true);
      if (!this.privRename(var9, var10)) {
         throw StandardException.newException("XSRS4.S", new Object[]{var9, var10});
      } else if (!this.privRename(var8, var9)) {
         throw StandardException.newException("XSRS4.S", new Object[]{var8, var9});
      }
   }

   private StorageFile getFile(ContainerKey var1, boolean var2) {
      return this.storageFactory.newStorageFile(this.getFilePath(var1, var2));
   }

   private String getFilePath(ContainerKey var1, boolean var2) {
      StringBuffer var3 = new StringBuffer("seg");
      var3.append(var1.getSegmentId());
      var3.append(this.storageFactory.getSeparator());
      var3.append((char)(var2 ? 'o' : 'n'));
      var3.append(Long.toHexString(var1.getContainerId()));
      var3.append(".dat");
      return var3.toString();
   }

   private boolean isOldContainerFile(String var1) {
      return var1.startsWith("o") && var1.endsWith(".dat");
   }

   private StorageFile getFile(String var1) {
      long var2 = 0L;
      StringBuffer var4 = new StringBuffer("seg");
      var4.append(var2);
      var4.append(this.storageFactory.getSeparator());
      var4.append(var1);
      return this.storageFactory.newStorageFile(var4.toString());
   }

   void restoreContainer(ContainerKey var1) throws StandardException {
      if (!this.dataFactory.getContainerCache().discard(var1)) {
      }

      StorageFile var2 = this.dataFactory.getContainerPath(var1, false);
      StorageFile var3 = this.getFile(var1, true);
      StorageFile var4 = this.getFile(var1, false);
      if (this.privExists(var3)) {
         if (this.privExists(var2) && !this.privRename(var2, var4)) {
            throw StandardException.newException("XSRS4.S", new Object[]{var2, var4});
         }

         if (!this.privRename(var3, var2)) {
            throw StandardException.newException("XSRS4.S", new Object[]{var3, var2});
         }
      }

      if (this.privExists(var4) && !this.privDelete(var4)) {
         throw StandardException.newException("XBM0R.D", new Object[]{var4});
      }
   }

   public void removeOldVersionOfContainers() throws StandardException {
      String[] var1 = this.dataFactory.getContainerNames();
      if (var1 != null) {
         for(int var2 = var1.length - 1; var2 >= 0; --var2) {
            if (this.isOldContainerFile(var1[var2])) {
               StorageFile var3 = this.getFile(var1[var2]);
               if (!this.privDelete(var3)) {
                  throw StandardException.newException("XSDFJ.S", new Object[]{var3});
               }
            }
         }
      }

   }

   private synchronized boolean privExists(StorageFile var1) {
      this.actionCode = 1;
      this.actionStorageFile = var1;
      Boolean var2 = this.run();
      this.actionStorageFile = null;
      return var2;
   }

   private synchronized boolean privDelete(StorageFile var1) {
      this.actionCode = 2;
      this.actionStorageFile = var1;
      Boolean var2 = this.run();
      this.actionStorageFile = null;
      return var2;
   }

   private synchronized boolean privRename(StorageFile var1, StorageFile var2) {
      this.actionCode = 3;
      this.actionStorageFile = var1;
      this.actionDestStorageFile = var2;
      Boolean var3 = this.run();
      this.actionStorageFile = null;
      this.actionDestStorageFile = null;
      return var3;
   }

   public Boolean run() {
      switch (this.actionCode) {
         case 1 -> {
            return this.actionStorageFile.exists();
         }
         case 2 -> {
            return this.actionStorageFile.delete();
         }
         case 3 -> {
            return this.actionStorageFile.renameTo(this.actionDestStorageFile);
         }
         default -> {
            return null;
         }
      }
   }
}
