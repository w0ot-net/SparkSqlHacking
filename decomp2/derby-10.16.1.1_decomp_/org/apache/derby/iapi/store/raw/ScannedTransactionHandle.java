package org.apache.derby.iapi.store.raw;

import java.io.InputStream;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.shared.common.error.StandardException;

public interface ScannedTransactionHandle {
   Loggable getNextRecord() throws StandardException;

   InputStream getOptionalData() throws StandardException;

   LogInstant getThisInstant() throws StandardException;

   LogInstant getLastInstant() throws StandardException;

   LogInstant getFirstInstant() throws StandardException;

   void close();
}
