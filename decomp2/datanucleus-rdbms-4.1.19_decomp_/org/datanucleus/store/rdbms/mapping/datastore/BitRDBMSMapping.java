package org.datanucleus.store.rdbms.mapping.datastore;

import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;

public class BitRDBMSMapping extends BooleanRDBMSMapping {
   public BitRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   public boolean isBitBased() {
      return true;
   }

   public int getJDBCType() {
      return -7;
   }
}
