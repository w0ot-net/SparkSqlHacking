package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

class RFResource implements FileResource {
   private final BaseDataFileFactory factory;

   RFResource(BaseDataFileFactory var1) {
      this.factory = var1;
   }

   public long add(String var1, InputStream var2) throws StandardException {
      OutputStream var3 = null;
      if (this.factory.isReadOnly()) {
         throw StandardException.newException("XSDFB.S", new Object[0]);
      } else {
         long var4 = this.factory.getNextId();

         try {
            StorageFile var6 = this.getAsFile(var1, var4);
            if (var6.exists()) {
               throw StandardException.newException("XSDF0.S", new Object[]{var6});
            }

            ContextManager var7 = FileContainer.getContextService().getCurrentContextManager();
            RawTransaction var8 = this.factory.getRawStoreFactory().getXactFactory().findUserTransaction(this.factory.getRawStoreFactory(), var7, "UserTransaction");
            var8.blockBackup(true);
            StorageFile var9 = var6.getParentDir();
            StorageFile var10 = var9.getParentDir();
            boolean var11 = var10.exists();
            if (!var9.exists()) {
               if (!var9.mkdirs()) {
                  throw StandardException.newException("XSDF3.S", new Object[]{var9});
               }

               var9.limitAccessToOwner();
               if (!var11) {
                  var10.limitAccessToOwner();
               }
            }

            var3 = var6.getOutputStream();
            byte[] var12 = new byte[4096];
            this.factory.writeInProgress();

            try {
               int var13;
               while((var13 = var2.read(var12)) != -1) {
                  var3.write(var12, 0, var13);
               }

               this.factory.writableStorageFactory.sync(var3, false);
            } finally {
               this.factory.writeFinished();
            }
         } catch (IOException var32) {
            throw StandardException.newException("XSDFF.S", var32, new Object[0]);
         } finally {
            try {
               if (var3 != null) {
                  var3.close();
               }
            } catch (IOException var30) {
            }

            try {
               if (var2 != null) {
                  var2.close();
               }
            } catch (IOException var29) {
            }

         }

         return var4;
      }
   }

   public void removeJarDir(String var1) throws StandardException {
      if (this.factory.isReadOnly()) {
         throw StandardException.newException("XSDFB.S", new Object[0]);
      } else {
         ContextManager var2 = FileContainer.getContextService().getCurrentContextManager();
         RawTransaction var3 = this.factory.getRawStoreFactory().getXactFactory().findUserTransaction(this.factory.getRawStoreFactory(), var2, "UserTransaction");
         StorageFile var4 = this.factory.storageFactory.newStorageFile(var1);
         RemoveFile var5 = new RemoveFile(var4);
         var3.addPostCommitWork(var5);
      }
   }

   public void remove(String var1, long var2) throws StandardException {
      if (this.factory.isReadOnly()) {
         throw StandardException.newException("XSDFB.S", new Object[0]);
      } else {
         ContextManager var4 = FileContainer.getContextService().getCurrentContextManager();
         RawTransaction var5 = this.factory.getRawStoreFactory().getXactFactory().findUserTransaction(this.factory.getRawStoreFactory(), var4, "UserTransaction");
         var5.blockBackup(true);
         var5.logAndDo(new RemoveFileOperation(var1, var2, true));
         RemoveFile var6 = new RemoveFile(this.getAsFile(var1, var2));
         var5.addPostCommitWork(var6);
      }
   }

   public long replace(String var1, long var2, InputStream var4) throws StandardException {
      if (this.factory.isReadOnly()) {
         throw StandardException.newException("XSDFB.S", new Object[0]);
      } else {
         this.remove(var1, var2);
         long var5 = this.add(var1, var4);
         return var5;
      }
   }

   public StorageFile getAsFile(String var1, long var2) {
      String var4 = this.factory.getVersionedName(var1, var2);
      return this.factory.storageFactory.newStorageFile(var4);
   }

   public char getSeparatorChar() {
      return this.factory.storageFactory.getSeparator();
   }
}
