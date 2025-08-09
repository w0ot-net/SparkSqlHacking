package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface SortObserver {
   DataValueDescriptor[] insertNonDuplicateKey(DataValueDescriptor[] var1) throws StandardException;

   DataValueDescriptor[] insertDuplicateKey(DataValueDescriptor[] var1, DataValueDescriptor[] var2) throws StandardException;

   void addToFreeList(DataValueDescriptor[] var1, int var2);

   DataValueDescriptor[] getArrayClone() throws StandardException;

   boolean deferrable();

   boolean deferred();

   void rememberDuplicate(DataValueDescriptor[] var1) throws StandardException;
}
