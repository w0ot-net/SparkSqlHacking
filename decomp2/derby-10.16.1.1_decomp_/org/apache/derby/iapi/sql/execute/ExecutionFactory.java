package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINFactoryIF;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public interface ExecutionFactory {
   String MODULE = "org.apache.derby.iapi.sql.execute.ExecutionFactory";

   ResultSetFactory getResultSetFactory();

   ResultSetStatisticsFactory getResultSetStatisticsFactory() throws StandardException;

   XPLAINFactoryIF getXPLAINFactory() throws StandardException;

   ExecutionContext newExecutionContext(ContextManager var1);

   ResultColumnDescriptor getResultColumnDescriptor(ResultColumnDescriptor var1);

   ResultDescription getResultDescription(ResultColumnDescriptor[] var1, String var2);

   ScanQualifier[][] getScanQualifier(int var1);

   void releaseScanQualifier(ScanQualifier[][] var1);

   Qualifier getQualifier(int var1, int var2, GeneratedMethod var3, Activation var4, boolean var5, boolean var6, boolean var7, int var8);

   RowChanger getRowChanger(long var1, StaticCompiledOpenConglomInfo var3, DynamicCompiledOpenConglomInfo var4, IndexRowGenerator[] var5, long[] var6, StaticCompiledOpenConglomInfo[] var7, DynamicCompiledOpenConglomInfo[] var8, int var9, TransactionController var10, int[] var11, int[] var12, Activation var13) throws StandardException;

   RowChanger getRowChanger(long var1, StaticCompiledOpenConglomInfo var3, DynamicCompiledOpenConglomInfo var4, IndexRowGenerator[] var5, long[] var6, StaticCompiledOpenConglomInfo[] var7, DynamicCompiledOpenConglomInfo[] var8, int var9, TransactionController var10, int[] var11, FormatableBitSet var12, int[] var13, int[] var14, Activation var15) throws StandardException;

   ExecRow getValueRow(int var1);

   ExecIndexRow getIndexableRow(int var1);

   ExecIndexRow getIndexableRow(ExecRow var1);
}
