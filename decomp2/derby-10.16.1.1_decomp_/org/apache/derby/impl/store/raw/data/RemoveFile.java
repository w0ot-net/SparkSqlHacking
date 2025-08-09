package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

final class RemoveFile implements Serviceable {
   private final StorageFile fileToGo;

   RemoveFile(StorageFile var1) {
      this.fileToGo = var1;
   }

   public int performWork(ContextManager var1) throws StandardException {
      this.run();
      return 1;
   }

   public boolean serviceASAP() {
      return false;
   }

   public boolean serviceImmediately() {
      return true;
   }

   public Object run() throws StandardException {
      if (this.fileToGo.exists()) {
         if (this.fileToGo.isDirectory()) {
            if (!this.fileToGo.deleteAll()) {
               throw StandardException.newException("XSDFK.S", new Object[]{this.fileToGo});
            }
         } else if (!this.fileToGo.delete()) {
            throw StandardException.newException("XSDFK.S", new Object[]{this.fileToGo});
         }
      }

      return null;
   }
}
