package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.table.Column;

public class SerialisedKeyPCMapping extends SerialisedPCMapping {
   protected void prepareDatastoreMapping() {
      MappingManager mmgr = this.storeMgr.getMappingManager();
      ColumnMetaData colmd = null;
      if (this.mmd.getKeyMetaData() != null && this.mmd.getKeyMetaData().getColumnMetaData() != null && this.mmd.getKeyMetaData().getColumnMetaData().length > 0) {
         colmd = this.mmd.getKeyMetaData().getColumnMetaData()[0];
      }

      Column col = mmgr.createColumn(this, this.getType(), colmd);
      mmgr.createDatastoreMapping(this, this.mmd, 0, col);
   }
}
