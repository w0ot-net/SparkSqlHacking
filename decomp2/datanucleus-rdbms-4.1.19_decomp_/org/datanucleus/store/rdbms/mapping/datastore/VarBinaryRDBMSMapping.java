package org.datanucleus.store.rdbms.mapping.datastore;

import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;

public class VarBinaryRDBMSMapping extends AbstractLargeBinaryRDBMSMapping {
   public VarBinaryRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   public int getJDBCType() {
      return -3;
   }
}
