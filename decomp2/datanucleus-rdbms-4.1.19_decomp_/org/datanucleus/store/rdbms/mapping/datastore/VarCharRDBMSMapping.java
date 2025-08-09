package org.datanucleus.store.rdbms.mapping.datastore;

import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.SingleFieldMapping;
import org.datanucleus.store.rdbms.table.Column;

public class VarCharRDBMSMapping extends CharRDBMSMapping {
   public VarCharRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
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

         this.column.checkString();
         if (this.getJavaTypeMapping() instanceof SingleFieldMapping) {
            Object[] validValues = ((SingleFieldMapping)this.getJavaTypeMapping()).getValidValues(0);
            if (validValues != null) {
               String constraints = this.storeMgr.getDatastoreAdapter().getCheckConstraintForValues(this.column.getIdentifier(), validValues, this.column.isNullable());
               this.column.setConstraints(constraints);
            }
         }
      }

      this.initTypeInfo();
   }

   public int getJDBCType() {
      return 12;
   }
}
