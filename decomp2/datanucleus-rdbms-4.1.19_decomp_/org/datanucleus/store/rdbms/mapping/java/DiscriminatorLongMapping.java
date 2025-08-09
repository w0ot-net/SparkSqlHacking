package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.store.rdbms.table.Table;

public final class DiscriminatorLongMapping extends DiscriminatorMapping {
   public DiscriminatorLongMapping(Table table, JavaTypeMapping delegate, DiscriminatorMetaData dismd) {
      super(table, delegate, dismd);
   }
}
