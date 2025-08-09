package org.apache.derby.iapi.store.access.conglomerate;

import java.util.Properties;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface ConglomerateFactory extends MethodFactory {
   int HEAP_FACTORY_ID = 0;
   int BTREE_FACTORY_ID = 1;

   int getConglomerateFactoryId();

   Conglomerate createConglomerate(TransactionManager var1, int var2, long var3, DataValueDescriptor[] var5, ColumnOrdering[] var6, int[] var7, Properties var8, int var9) throws StandardException;

   Conglomerate readConglomerate(TransactionManager var1, ContainerKey var2) throws StandardException;

   void insertUndoNotify(AccessFactory var1, Transaction var2, PageKey var3) throws StandardException;
}
