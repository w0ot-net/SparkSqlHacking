package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.table.Table;

public class EmbeddedElementPCMapping extends EmbeddedMapping {
   public void initialize(AbstractMemberMetaData fmd, Table table, ClassLoaderResolver clr) {
      this.initialize(fmd, table, clr, fmd.getElementMetaData().getEmbeddedMetaData(), fmd.getCollection().getElementType(), 2);
   }
}
