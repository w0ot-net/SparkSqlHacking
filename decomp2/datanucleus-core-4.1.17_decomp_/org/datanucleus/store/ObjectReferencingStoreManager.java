package org.datanucleus.store;

import org.datanucleus.state.ObjectProvider;

public interface ObjectReferencingStoreManager {
   void notifyObjectIsOutdated(ObjectProvider var1);
}
