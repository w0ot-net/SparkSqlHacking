package org.apache.derby.iapi.store.replication.master;

import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface MasterFactory {
   String MODULE = "org.apache.derby.iapi.store.replication.master.MasterFactory";
   String REPLICATION_MODE = "derby.__rt.replication.master.mode";
   String ASYNCHRONOUS_MODE = "derby.__rt.asynch";

   void startMaster(RawStoreFactory var1, DataFactory var2, LogFactory var3, String var4, int var5, String var6) throws StandardException;

   void stopMaster() throws StandardException;

   void startFailover() throws StandardException;

   void appendLog(long var1, byte[] var3, int var4, int var5);

   void flushedTo(long var1);

   void workToDo();
}
