package org.datanucleus.store;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.scostore.Store;

public interface BackedSCOStoreManager {
   Store getBackingStoreForField(ClassLoaderResolver var1, AbstractMemberMetaData var2, Class var3);
}
