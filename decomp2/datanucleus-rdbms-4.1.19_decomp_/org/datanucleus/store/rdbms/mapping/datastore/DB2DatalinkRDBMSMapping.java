package org.datanucleus.store.rdbms.mapping.datastore;

import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;

public class DB2DatalinkRDBMSMapping extends CharRDBMSMapping {
   public DB2DatalinkRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   protected void initialize() {
      if (this.column != null && this.mapping.getMemberMetaData().getValueForExtension("select-function") == null) {
         this.column.setWrapperFunction("DLURLCOMPLETEONLY(?)", 0);
      }

      this.initTypeInfo();
   }

   public int getJDBCType() {
      return 70;
   }

   public String getInsertionInputParameter() {
      return "DLVALUE(? || '')";
   }

   public boolean includeInFetchStatement() {
      return true;
   }

   public String getUpdateInputParameter() {
      return "DLVALUE(? || '')";
   }
}
