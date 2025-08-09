package org.apache.derby.iapi.store.access.conglomerate;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface Conglomerate extends Storable, DataValueDescriptor {
   void addColumn(TransactionManager var1, int var2, Storable var3, int var4) throws StandardException;

   void drop(TransactionManager var1) throws StandardException;

   boolean fetchMaxOnBTree(TransactionManager var1, Transaction var2, long var3, int var5, int var6, LockingPolicy var7, int var8, FormatableBitSet var9, DataValueDescriptor[] var10) throws StandardException;

   long getContainerid();

   ContainerKey getId();

   StaticCompiledOpenConglomInfo getStaticCompiledConglomInfo(TransactionController var1, long var2) throws StandardException;

   DynamicCompiledOpenConglomInfo getDynamicCompiledConglomInfo() throws StandardException;

   boolean isTemporary();

   long load(TransactionManager var1, boolean var2, RowLocationRetRowSource var3) throws StandardException;

   ConglomerateController open(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, StaticCompiledOpenConglomInfo var7, DynamicCompiledOpenConglomInfo var8) throws StandardException;

   ScanManager openScan(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, int var7, FormatableBitSet var8, DataValueDescriptor[] var9, int var10, Qualifier[][] var11, DataValueDescriptor[] var12, int var13, StaticCompiledOpenConglomInfo var14, DynamicCompiledOpenConglomInfo var15) throws StandardException;

   ScanManager defragmentConglomerate(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, int var7) throws StandardException;

   void purgeConglomerate(TransactionManager var1, Transaction var2) throws StandardException;

   void compressConglomerate(TransactionManager var1, Transaction var2) throws StandardException;

   StoreCostController openStoreCost(TransactionManager var1, Transaction var2) throws StandardException;
}
