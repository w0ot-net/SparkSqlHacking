package org.apache.derby.iapi.store.replication.slave;

import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface SlaveFactory {
   String MODULE = "org.apache.derby.iapi.store.replication.slave.SlaveFactory";
   String SLAVE_DB = "replication.slave.dbname";
   String REPLICATION_MODE = "replication.slave.mode";
   String SLAVE_MODE = "slavemode";
   String SLAVE_PRE_MODE = "slavepremode";

   void startSlave(RawStoreFactory var1, LogFactory var2) throws StandardException;

   void stopSlave(boolean var1) throws StandardException;

   void failover() throws StandardException;

   boolean isStarted();
}
