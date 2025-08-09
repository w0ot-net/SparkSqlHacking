package org.apache.derby.impl.store.raw.log;

import java.io.IOException;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.log.LogScan;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.shared.common.error.StandardException;

public interface StreamLogScan extends LogScan {
   LogRecord getNextRecord(ArrayInputStream var1, TransactionId var2, int var3) throws StandardException, IOException;

   long getInstant();

   long getLogRecordEnd();

   boolean isLogEndFuzzy();

   LogInstant getLogInstant();

   void resetPosition(LogInstant var1) throws IOException, StandardException;

   void close();
}
