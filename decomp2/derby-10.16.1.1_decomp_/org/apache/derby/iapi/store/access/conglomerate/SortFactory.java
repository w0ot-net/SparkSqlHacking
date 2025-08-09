package org.apache.derby.iapi.store.access.conglomerate;

import java.util.Properties;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.SortCostController;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface SortFactory extends MethodFactory {
   String MODULE = "org.apache.derby.iapi.store.access.conglomerate.SortFactory";

   Sort createSort(TransactionController var1, int var2, Properties var3, DataValueDescriptor[] var4, ColumnOrdering[] var5, SortObserver var6, boolean var7, long var8, int var10) throws StandardException;

   SortCostController openSortCostController() throws StandardException;
}
