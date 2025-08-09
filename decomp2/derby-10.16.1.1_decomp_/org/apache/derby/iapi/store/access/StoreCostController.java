package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface StoreCostController extends RowCountable {
   double BASE_CACHED_ROW_FETCH_COST = 0.17;
   double BASE_UNCACHED_ROW_FETCH_COST = (double)1.5F;
   double BASE_GROUPSCAN_ROW_COST = 0.12;
   double BASE_NONGROUPSCAN_ROW_FETCH_COST = (double)0.25F;
   double BASE_HASHSCAN_ROW_FETCH_COST = 0.14;
   double BASE_ROW_PER_BYTECOST = 0.004;
   int STORECOST_CLUSTERED = 1;
   int STORECOST_SCAN_SET = 1;
   int STORECOST_SCAN_NORMAL = 2;

   void close() throws StandardException;

   double getFetchFromRowLocationCost(FormatableBitSet var1, int var2) throws StandardException;

   double getFetchFromFullKeyCost(FormatableBitSet var1, int var2) throws StandardException;

   void getScanCost(int var1, long var2, int var4, boolean var5, FormatableBitSet var6, DataValueDescriptor[] var7, DataValueDescriptor[] var8, int var9, DataValueDescriptor[] var10, int var11, boolean var12, int var13, StoreCostResult var14) throws StandardException;

   RowLocation newRowLocationTemplate() throws StandardException;
}
