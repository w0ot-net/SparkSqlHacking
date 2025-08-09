package org.datanucleus.store.rdbms.table;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;

public interface DatastoreElementContainer extends Table {
   JavaTypeMapping getOwnerMapping();

   JavaTypeMapping getElementMapping();
}
