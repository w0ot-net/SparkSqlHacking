package org.apache.derby.impl.store.raw.log;

import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.store.access.DatabaseInstant;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.ScanHandle;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.shared.common.error.StandardException;

public class FlushedScanHandle implements ScanHandle {
   LogFactory lf;
   StreamLogScan fs;
   LogRecord lr = null;
   boolean readOptionalData = false;
   int groupsIWant;
   ArrayInputStream rawInput = new ArrayInputStream(new byte[4096]);

   FlushedScanHandle(LogToFile var1, DatabaseInstant var2, int var3) throws StandardException {
      this.lf = var1;
      this.fs = new FlushedScan(var1, ((LogCounter)var2).getValueAsLong());
      this.groupsIWant = var3;
   }

   public boolean next() throws StandardException {
      this.readOptionalData = false;
      this.lr = null;

      try {
         this.lr = this.fs.getNextRecord(this.rawInput, (TransactionId)null, this.groupsIWant);
         return this.lr != null;
      } catch (IOException var2) {
         var2.printStackTrace();
         this.fs.close();
         this.fs = null;
         throw this.lf.markCorrupt(StandardException.newException("XSLA2.D", var2, new Object[0]));
      }
   }

   public int getGroup() throws StandardException {
      return this.lr.group();
   }

   public Loggable getLoggable() throws StandardException {
      try {
         return this.lr.getLoggable();
      } catch (IOException var2) {
         var2.printStackTrace();
         this.fs.close();
         this.fs = null;
         throw this.lf.markCorrupt(StandardException.newException("XSLA2.D", var2, new Object[0]));
      } catch (ClassNotFoundException var3) {
         this.fs.close();
         this.fs = null;
         throw this.lf.markCorrupt(StandardException.newException("XSLA3.D", var3, new Object[0]));
      }
   }

   public InputStream getOptionalData() throws StandardException {
      if (this.lr == null) {
         return null;
      } else {
         try {
            int var1 = this.rawInput.readInt();
            this.readOptionalData = true;
            this.rawInput.setLimit(var1);
            return this.rawInput;
         } catch (IOException var2) {
            this.fs.close();
            this.fs = null;
            throw this.lf.markCorrupt(StandardException.newException("XSLA2.D", var2, new Object[0]));
         }
      }
   }

   public DatabaseInstant getInstant() throws StandardException {
      return this.fs.getLogInstant();
   }

   public Object getTransactionId() throws StandardException {
      try {
         return this.lr.getTransactionId();
      } catch (IOException var2) {
         var2.printStackTrace();
         this.fs.close();
         this.fs = null;
         throw this.lf.markCorrupt(StandardException.newException("XSLA2.D", var2, new Object[0]));
      } catch (ClassNotFoundException var3) {
         this.fs.close();
         this.fs = null;
         throw this.lf.markCorrupt(StandardException.newException("XSLA3.D", var3, new Object[0]));
      }
   }

   public void close() {
      if (this.fs != null) {
         this.fs.close();
      }

      this.fs = null;
   }
}
