package org.datanucleus.store.scostore;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.StoreManager;

public interface Store {
   AbstractMemberMetaData getOwnerMemberMetaData();

   StoreManager getStoreManager();
}
