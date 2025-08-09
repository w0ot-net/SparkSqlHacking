package org.apache.derby.iapi.store.access;

import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface TransactionController extends PersistentSet {
   int MODE_RECORD = 6;
   int MODE_TABLE = 7;
   int ISOLATION_NOLOCK = 0;
   int ISOLATION_READ_UNCOMMITTED = 1;
   int ISOLATION_READ_COMMITTED = 2;
   int ISOLATION_READ_COMMITTED_NOHOLDLOCK = 3;
   int ISOLATION_REPEATABLE_READ = 4;
   int ISOLATION_SERIALIZABLE = 5;
   int OPENMODE_USE_UPDATE_LOCKS = 4096;
   int OPENMODE_SECONDARY_LOCKED = 8192;
   int OPENMODE_BASEROW_INSERT_LOCKED = 16384;
   int OPENMODE_FORUPDATE = 4;
   int OPENMODE_FOR_LOCK_ONLY = 64;
   int OPENMODE_LOCK_NOWAIT = 128;
   int OPENMODE_LOCK_ROW_NOWAIT = 32768;
   int OPEN_CONGLOMERATE = 1;
   int OPEN_SCAN = 2;
   int OPEN_CREATED_SORTS = 3;
   int OPEN_SORT = 4;
   int OPEN_TOTAL = 5;
   byte IS_DEFAULT = 0;
   byte IS_TEMPORARY = 1;
   byte IS_KEPT = 2;
   int RELEASE_LOCKS = 1;
   int KEEP_LOCKS = 2;
   int READONLY_TRANSACTION_INITIALIZATION = 4;

   AccessFactory getAccessManager();

   boolean conglomerateExists(long var1) throws StandardException;

   long createConglomerate(String var1, DataValueDescriptor[] var2, ColumnOrdering[] var3, int[] var4, Properties var5, int var6) throws StandardException;

   long createAndLoadConglomerate(String var1, DataValueDescriptor[] var2, ColumnOrdering[] var3, int[] var4, Properties var5, int var6, RowLocationRetRowSource var7, long[] var8) throws StandardException;

   long recreateAndLoadConglomerate(String var1, boolean var2, DataValueDescriptor[] var3, ColumnOrdering[] var4, int[] var5, Properties var6, int var7, long var8, RowLocationRetRowSource var10, long[] var11) throws StandardException;

   void addColumnToConglomerate(long var1, int var3, Storable var4, int var5) throws StandardException;

   void dropConglomerate(long var1) throws StandardException;

   long findConglomid(long var1) throws StandardException;

   long findContainerid(long var1) throws StandardException;

   TransactionController startNestedUserTransaction(boolean var1, boolean var2) throws StandardException;

   Properties getUserCreateConglomPropList();

   ConglomerateController openConglomerate(long var1, boolean var3, int var4, int var5, int var6) throws StandardException;

   ConglomerateController openCompiledConglomerate(boolean var1, int var2, int var3, int var4, StaticCompiledOpenConglomInfo var5, DynamicCompiledOpenConglomInfo var6) throws StandardException;

   BackingStoreHashtable createBackingStoreHashtableFromScan(long var1, int var3, int var4, int var5, FormatableBitSet var6, DataValueDescriptor[] var7, int var8, Qualifier[][] var9, DataValueDescriptor[] var10, int var11, long var12, int[] var14, boolean var15, long var16, long var18, int var20, float var21, boolean var22, boolean var23, boolean var24, boolean var25) throws StandardException;

   ScanController openScan(long var1, boolean var3, int var4, int var5, int var6, FormatableBitSet var7, DataValueDescriptor[] var8, int var9, Qualifier[][] var10, DataValueDescriptor[] var11, int var12) throws StandardException;

   ScanController openCompiledScan(boolean var1, int var2, int var3, int var4, FormatableBitSet var5, DataValueDescriptor[] var6, int var7, Qualifier[][] var8, DataValueDescriptor[] var9, int var10, StaticCompiledOpenConglomInfo var11, DynamicCompiledOpenConglomInfo var12) throws StandardException;

   GroupFetchScanController openGroupFetchScan(long var1, boolean var3, int var4, int var5, int var6, FormatableBitSet var7, DataValueDescriptor[] var8, int var9, Qualifier[][] var10, DataValueDescriptor[] var11, int var12) throws StandardException;

   GroupFetchScanController defragmentConglomerate(long var1, boolean var3, boolean var4, int var5, int var6, int var7) throws StandardException;

   void purgeConglomerate(long var1) throws StandardException;

   void compressConglomerate(long var1) throws StandardException;

   boolean fetchMaxOnBtree(long var1, int var3, int var4, int var5, FormatableBitSet var6, DataValueDescriptor[] var7) throws StandardException;

   StoreCostController openStoreCost(long var1) throws StandardException;

   int countOpens(int var1) throws StandardException;

   String debugOpened() throws StandardException;

   FileResource getFileHandler();

   CompatibilitySpace getLockSpace();

   void setNoLockWait(boolean var1);

   StaticCompiledOpenConglomInfo getStaticCompiledConglomInfo(long var1) throws StandardException;

   DynamicCompiledOpenConglomInfo getDynamicCompiledConglomInfo(long var1) throws StandardException;

   void logAndDo(Loggable var1) throws StandardException;

   long createSort(Properties var1, DataValueDescriptor[] var2, ColumnOrdering[] var3, SortObserver var4, boolean var5, long var6, int var8) throws StandardException;

   void dropSort(long var1) throws StandardException;

   SortController openSort(long var1) throws StandardException;

   SortCostController openSortCostController() throws StandardException;

   RowLocationRetRowSource openSortRowSource(long var1) throws StandardException;

   ScanController openSortScan(long var1, boolean var3) throws StandardException;

   boolean anyoneBlocked();

   void abort() throws StandardException;

   void commit() throws StandardException;

   DatabaseInstant commitNoSync(int var1) throws StandardException;

   void destroy();

   ContextManager getContextManager();

   String getTransactionIdString();

   String getActiveStateTxIdString();

   boolean isIdle();

   boolean isGlobal();

   boolean isPristine();

   int releaseSavePoint(String var1, Object var2) throws StandardException;

   int rollbackToSavePoint(String var1, boolean var2, Object var3) throws StandardException;

   int setSavePoint(String var1, Object var2) throws StandardException;

   Object createXATransactionFromLocalTransaction(int var1, byte[] var2, byte[] var3) throws StandardException;
}
