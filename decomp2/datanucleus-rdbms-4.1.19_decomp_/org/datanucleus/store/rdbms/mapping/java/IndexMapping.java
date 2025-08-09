package org.datanucleus.store.rdbms.mapping.java;

public final class IndexMapping extends SingleFieldMapping {
   public boolean includeInFetchStatement() {
      return false;
   }

   public Class getJavaType() {
      return Integer.class;
   }
}
