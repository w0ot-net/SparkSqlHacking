package org.datanucleus.store.rdbms.table;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;

public interface DatastoreMap extends Table {
   JavaTypeMapping getOwnerMapping();

   JavaTypeMapping getKeyMapping();

   JavaTypeMapping getValueMapping();
}
