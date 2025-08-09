package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.Undoable;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

public class RemoveFileOperation implements Undoable {
   private String name;
   private long generationId;
   private boolean removeAtOnce;
   private transient StorageFile fileToGo;

   public RemoveFileOperation() {
   }

   RemoveFileOperation(String var1, long var2, boolean var4) {
      this.name = var1;
      this.generationId = var2;
      this.removeAtOnce = var4;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeUTF(this.name);
      var1.writeLong(this.generationId);
      var1.writeBoolean(this.removeAtOnce);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.name = var1.readUTF();
      this.generationId = var1.readLong();
      this.removeAtOnce = var1.readBoolean();
   }

   public int getTypeFormatId() {
      return 291;
   }

   public ByteArray getPreparedLog() {
      return null;
   }

   public void releaseResource(Transaction var1) {
   }

   public int group() {
      return 1280;
   }

   public void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException {
      if (this.fileToGo != null) {
         BaseDataFileFactory var4 = (BaseDataFileFactory)((RawTransaction)var1).getDataFactory();
         var4.fileToRemove(this.fileToGo, true);
      }
   }

   public boolean needsRedo(Transaction var1) throws StandardException {
      if (!this.removeAtOnce) {
         return false;
      } else {
         FileResource var2 = ((RawTransaction)var1).getDataFactory().getFileHandler();
         this.fileToGo = var2.getAsFile(this.name, this.generationId);
         return this.fileToGo == null ? false : this.fileToGo.exists();
      }
   }

   public Compensation generateUndo(Transaction var1, LimitObjectInput var2) throws StandardException, IOException {
      if (this.fileToGo != null) {
         BaseDataFileFactory var3 = (BaseDataFileFactory)((RawTransaction)var1).getDataFactory();
         var3.fileToRemove(this.fileToGo, false);
      }

      return null;
   }
}
