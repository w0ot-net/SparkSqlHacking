package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.table.Column;

public class SerialisedValuePCMapping extends SerialisedPCMapping {
   protected void prepareDatastoreMapping() {
      MappingManager mmgr = this.storeMgr.getMappingManager();
      ColumnMetaData colmd = null;
      if (this.mmd.getValueMetaData() != null && this.mmd.getValueMetaData().getColumnMetaData() != null && this.mmd.getValueMetaData().getColumnMetaData().length > 0) {
         colmd = this.mmd.getValueMetaData().getColumnMetaData()[0];
      }

      Column col = mmgr.createColumn(this, this.getType(), colmd);
      mmgr.createDatastoreMapping(this, this.mmd, 0, col);
   }
}
