package org.apache.derby.impl.store.replication.master;

import java.io.IOException;
import org.apache.derby.shared.common.error.StandardException;

interface LogShipper {
   void flushedInstance(long var1);

   void forceFlush() throws IOException, StandardException;

   void flushBuffer() throws IOException, StandardException;

   void workToDo();
}
