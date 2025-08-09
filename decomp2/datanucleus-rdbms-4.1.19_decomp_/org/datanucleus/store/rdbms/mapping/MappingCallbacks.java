package org.datanucleus.store.rdbms.mapping;

import org.datanucleus.state.ObjectProvider;

public interface MappingCallbacks {
   void insertPostProcessing(ObjectProvider var1);

   void postInsert(ObjectProvider var1);

   void postFetch(ObjectProvider var1);

   void postUpdate(ObjectProvider var1);

   void preDelete(ObjectProvider var1);
}
