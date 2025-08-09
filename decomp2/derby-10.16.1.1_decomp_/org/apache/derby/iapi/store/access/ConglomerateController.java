package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface ConglomerateController extends ConglomPropertyQueryable {
   int ROWISDUPLICATE = 1;
   int LOCK_READ = 0;
   int LOCK_UPD = 1;
   int LOCK_INS = 2;
   int LOCK_INS_PREVKEY = 4;
   int LOCK_UPDATE_LOCKS = 8;

   void close() throws StandardException;

   boolean closeForEndTransaction(boolean var1) throws StandardException;

   void checkConsistency() throws StandardException;

   boolean delete(RowLocation var1) throws StandardException;

   boolean fetch(RowLocation var1, DataValueDescriptor[] var2, FormatableBitSet var3) throws StandardException;

   boolean fetch(RowLocation var1, DataValueDescriptor[] var2, FormatableBitSet var3, boolean var4) throws StandardException;

   int insert(DataValueDescriptor[] var1) throws StandardException;

   void insertAndFetchLocation(DataValueDescriptor[] var1, RowLocation var2) throws StandardException;

   boolean isKeyed();

   boolean lockRow(RowLocation var1, int var2, boolean var3, int var4) throws StandardException;

   boolean lockRow(long var1, int var3, int var4, boolean var5, int var6) throws StandardException;

   void unlockRowAfterRead(RowLocation var1, boolean var2, boolean var3) throws StandardException;

   RowLocation newRowLocationTemplate() throws StandardException;

   boolean replace(RowLocation var1, DataValueDescriptor[] var2, FormatableBitSet var3) throws StandardException;

   SpaceInfo getSpaceInfo() throws StandardException;

   void debugConglomerate() throws StandardException;
}
