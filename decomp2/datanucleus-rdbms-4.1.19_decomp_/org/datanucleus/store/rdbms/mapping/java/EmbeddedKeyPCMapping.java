package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.table.Table;

public class EmbeddedKeyPCMapping extends EmbeddedMapping {
   public void initialize(AbstractMemberMetaData fmd, Table table, ClassLoaderResolver clr) {
      this.initialize(fmd, table, clr, fmd.getKeyMetaData().getEmbeddedMetaData(), fmd.getMap().getKeyType(), 3);
   }
}
