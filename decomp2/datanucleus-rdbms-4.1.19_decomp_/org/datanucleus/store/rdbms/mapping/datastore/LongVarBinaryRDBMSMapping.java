package org.datanucleus.store.rdbms.mapping.datastore;

import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;

public class LongVarBinaryRDBMSMapping extends AbstractLargeBinaryRDBMSMapping {
   public LongVarBinaryRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   public int getJDBCType() {
      return -4;
   }
}
