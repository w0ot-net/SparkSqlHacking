package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.table.Column;

public abstract class SingleFieldMultiMapping extends JavaTypeMapping {
   protected void addColumns(String typeName) {
      MappingManager mgr = this.storeMgr.getMappingManager();
      Column column = null;
      if (this.table != null) {
         column = mgr.createColumn(this, typeName, this.getNumberOfDatastoreMappings());
      }

      mgr.createDatastoreMapping(this, column, typeName);
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return this.datastoreMappings[index].getColumn().getStoredJavaType();
   }

   public boolean hasSimpleDatastoreRepresentation() {
      return false;
   }
}
