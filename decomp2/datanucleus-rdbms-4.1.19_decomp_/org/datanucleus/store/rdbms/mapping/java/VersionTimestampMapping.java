package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.store.rdbms.table.Table;

public final class VersionTimestampMapping extends VersionMapping {
   public VersionTimestampMapping(Table table, JavaTypeMapping delegate) {
      super(table, delegate);
   }
}
