package org.datanucleus.flush;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.scostore.Store;

public interface SCOOperation extends Operation {
   AbstractMemberMetaData getMemberMetaData();

   Store getStore();
}
