package org.datanucleus.store.rdbms.mapping.datastore;

import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.SingleFieldMapping;
import org.datanucleus.store.rdbms.table.Column;

public class TimesTenVarBinaryRDBMSMapping extends VarBinaryRDBMSMapping {
   public TimesTenVarBinaryRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   protected void initialize() {
      if (this.column != null) {
         if (this.getJavaTypeMapping() instanceof SingleFieldMapping && this.column.getColumnMetaData().getLength() == null) {
            SingleFieldMapping m = (SingleFieldMapping)this.getJavaTypeMapping();
            if (m.getDefaultLength(0) > 0) {
               this.column.getColumnMetaData().setLength(m.getDefaultLength(0));
            }
         }

         if (this.column.getColumnMetaData().getLength() == null) {
            this.column.getColumnMetaData().setLength(1024);
         }
      }

      super.initialize();
   }
}
