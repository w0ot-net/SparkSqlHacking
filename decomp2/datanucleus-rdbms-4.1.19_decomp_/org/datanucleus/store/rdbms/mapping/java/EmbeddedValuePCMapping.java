package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.table.Table;

public class EmbeddedValuePCMapping extends EmbeddedMapping {
   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      this.initialize(mmd, table, clr, mmd.getValueMetaData().getEmbeddedMetaData(), mmd.getMap().getValueType(), 4);
   }
}
