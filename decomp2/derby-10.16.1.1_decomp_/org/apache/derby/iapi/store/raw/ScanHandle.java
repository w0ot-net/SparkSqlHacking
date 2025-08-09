package org.apache.derby.iapi.store.raw;

import java.io.InputStream;
import org.apache.derby.iapi.store.access.DatabaseInstant;
import org.apache.derby.shared.common.error.StandardException;

public interface ScanHandle {
   boolean next() throws StandardException;

   int getGroup() throws StandardException;

   Loggable getLoggable() throws StandardException;

   InputStream getOptionalData() throws StandardException;

   DatabaseInstant getInstant() throws StandardException;

   Object getTransactionId() throws StandardException;

   void close();
}
