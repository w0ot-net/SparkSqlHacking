package org.datanucleus.store.rdbms.table;

import org.datanucleus.metadata.JoinMetaData;

public interface SecondaryDatastoreClass extends DatastoreClass {
   DatastoreClass getPrimaryDatastoreClass();

   JoinMetaData getJoinMetaData();
}
