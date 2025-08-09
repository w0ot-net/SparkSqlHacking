package org.apache.derby.iapi.store.access.conglomerate;

import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public interface Sort {
   SortController open(TransactionManager var1) throws StandardException;

   ScanManager openSortScan(TransactionManager var1, boolean var2) throws StandardException;

   ScanControllerRowSource openSortRowSource(TransactionManager var1) throws StandardException;

   void drop(TransactionController var1) throws StandardException;
}
