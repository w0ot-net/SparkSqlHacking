package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.store.rdbms.table.Table;

public final class VersionLongMapping extends VersionMapping {
   public VersionLongMapping(Table datastoreContainer, JavaTypeMapping delegate) {
      super(datastoreContainer, delegate);
   }
}
