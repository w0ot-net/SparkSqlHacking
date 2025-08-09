package org.apache.derby.iapi.store.raw.log;

import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.shared.common.error.StandardException;

public interface Logger {
   LogInstant logAndDo(RawTransaction var1, Loggable var2) throws StandardException;

   LogInstant logAndUndo(RawTransaction var1, Compensation var2, LogInstant var3, LimitObjectInput var4) throws StandardException;

   void flush(LogInstant var1) throws StandardException;

   void flushAll() throws StandardException;

   void reprepare(RawTransaction var1, TransactionId var2, LogInstant var3, LogInstant var4) throws StandardException;

   void undo(RawTransaction var1, TransactionId var2, LogInstant var3, LogInstant var4) throws StandardException;
}
