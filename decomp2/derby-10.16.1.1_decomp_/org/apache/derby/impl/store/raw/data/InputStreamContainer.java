package org.apache.derby.impl.store.raw.data;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.iapi.services.io.InputStreamUtil;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

final class InputStreamContainer extends FileContainer {
   private StorageFile containerPath;

   InputStreamContainer(BaseDataFileFactory var1) {
      super(var1);
      this.canUpdate = false;
   }

   final boolean openContainer(ContainerKey var1) throws StandardException {
      DataInputStream var2 = null;

      try {
         Object var3 = null;
         this.containerPath = this.dataFactory.getContainerPath(var1, false);

         try {
            var22 = this.containerPath.getInputStream();
         } catch (IOException var19) {
            this.containerPath = this.dataFactory.getContainerPath(var1, true);

            try {
               var22 = this.getInputStream();
            } catch (IOException var18) {
               this.containerPath = null;
               boolean var6 = false;
               return var6;
            }
         }

         var2 = new DataInputStream(var22);
         this.readHeader(this.getEmbryonicPage(var2));
         boolean var4 = true;
         return var4;
      } catch (IOException var20) {
         throw StandardException.newException("XSDG3.D", var20, new Object[]{this.getIdentity().toString(), "open", var1.toString()});
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (IOException var17) {
            }
         }

      }
   }

   void closeContainer() {
      this.containerPath = null;
   }

   public final void clean(boolean var1) throws StandardException {
   }

   protected final int preAllocate(long var1, int var3) {
      return 0;
   }

   protected void truncatePages(long var1) {
   }

   void createContainer(ContainerKey var1) throws StandardException {
   }

   protected final void removeContainer(LogInstant var1, boolean var2) throws StandardException {
   }

   protected final void readPage(long var1, byte[] var3) throws IOException, StandardException {
      long var4 = var1 * (long)this.pageSize;
      this.readPositionedPage(var4, var3);
      if (this.dataFactory.databaseEncrypted() && var1 != 0L) {
         this.decryptPage(var3, this.pageSize);
      }

   }

   protected void readPositionedPage(long var1, byte[] var3) throws IOException {
      InputStream var4 = null;

      try {
         var4 = this.getInputStream();
         InputStreamUtil.skipFully(var4, var1);
         InputStreamUtil.readFully(var4, var3, 0, this.pageSize);
         var4.close();
         var4 = null;
      } finally {
         if (var4 != null) {
            try {
               var4.close();
            } catch (IOException var11) {
            }
         }

      }

   }

   protected final void writePage(long var1, byte[] var3, boolean var4) throws IOException, StandardException {
   }

   protected final void flushAll() {
   }

   protected InputStream getInputStream() throws IOException {
      return this.containerPath.getInputStream();
   }

   protected void backupContainer(BaseContainerHandle var1, String var2) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   protected void encryptOrDecryptContainer(BaseContainerHandle var1, String var2, boolean var3) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }
}
