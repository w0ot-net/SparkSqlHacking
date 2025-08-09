package org.datanucleus.store;

import org.datanucleus.ExecutionContext;
import org.datanucleus.state.ObjectProvider;

public interface StorePersistenceHandler {
   void close();

   void batchStart(ExecutionContext var1, PersistenceBatchType var2);

   void batchEnd(ExecutionContext var1, PersistenceBatchType var2);

   void insertObject(ObjectProvider var1);

   void insertObjects(ObjectProvider... var1);

   void updateObject(ObjectProvider var1, int[] var2);

   void deleteObject(ObjectProvider var1);

   void deleteObjects(ObjectProvider... var1);

   void fetchObject(ObjectProvider var1, int[] var2);

   void locateObject(ObjectProvider var1);

   void locateObjects(ObjectProvider[] var1);

   Object findObject(ExecutionContext var1, Object var2);

   Object[] findObjects(ExecutionContext var1, Object[] var2);
}
