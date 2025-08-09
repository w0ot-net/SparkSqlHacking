package org.datanucleus;

import org.datanucleus.store.StoreManager;

public interface StoreNucleusContext extends NucleusContext {
   StoreManager getStoreManager();
}
