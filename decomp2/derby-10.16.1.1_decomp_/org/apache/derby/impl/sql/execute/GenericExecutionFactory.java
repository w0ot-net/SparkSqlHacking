package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.jdbc.ConnectionContext;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionContext;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.sql.execute.ResultSetFactory;
import org.apache.derby.iapi.sql.execute.ResultSetStatisticsFactory;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.sql.execute.ScanQualifier;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINFactoryIF;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.impl.sql.GenericColumnDescriptor;
import org.apache.derby.impl.sql.GenericResultDescription;
import org.apache.derby.shared.common.error.StandardException;

public class GenericExecutionFactory implements ModuleControl, ModuleSupportable, ExecutionFactory {
   private ResultSetStatisticsFactory rssFactory;
   private ResultSetFactory rsFactory;
   private GenericConstantActionFactory genericConstantActionFactory;
   private XPLAINFactoryIF xplainFactory;

   public boolean canSupport(Properties var1) {
      return Monitor.isDesiredType(var1, 130);
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
   }

   public void stop() {
   }

   public ResultSetFactory getResultSetFactory() {
      if (this.rsFactory == null) {
         this.rsFactory = new GenericResultSetFactory();
      }

      return this.rsFactory;
   }

   public GenericConstantActionFactory getConstantActionFactory() {
      if (this.genericConstantActionFactory == null) {
         this.genericConstantActionFactory = new GenericConstantActionFactory();
      }

      return this.genericConstantActionFactory;
   }

   public ResultSetStatisticsFactory getResultSetStatisticsFactory() throws StandardException {
      if (this.rssFactory == null) {
         this.rssFactory = (ResultSetStatisticsFactory)bootServiceModule(false, this, "org.apache.derby.iapi.sql.execute.ResultSetStatisticsFactory", (Properties)null);
      }

      return this.rssFactory;
   }

   public ExecutionContext newExecutionContext(ContextManager var1) {
      return new GenericExecutionContext(var1, this);
   }

   public ScanQualifier[][] getScanQualifier(int var1) {
      GenericScanQualifier[] var2 = new GenericScanQualifier[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = new GenericScanQualifier();
      }

      ScanQualifier[][] var4 = new ScanQualifier[][]{var2};
      return var4;
   }

   public ResultDescription getResultDescription(ResultColumnDescriptor[] var1, String var2) {
      return new GenericResultDescription(var1, var2);
   }

   public ResultColumnDescriptor getResultColumnDescriptor(ResultColumnDescriptor var1) {
      return new GenericColumnDescriptor(var1);
   }

   public void releaseScanQualifier(ScanQualifier[][] var1) {
   }

   public Qualifier getQualifier(int var1, int var2, GeneratedMethod var3, Activation var4, boolean var5, boolean var6, boolean var7, int var8) {
      return new GenericQualifier(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public RowChanger getRowChanger(long var1, StaticCompiledOpenConglomInfo var3, DynamicCompiledOpenConglomInfo var4, IndexRowGenerator[] var5, long[] var6, StaticCompiledOpenConglomInfo[] var7, DynamicCompiledOpenConglomInfo[] var8, int var9, TransactionController var10, int[] var11, int[] var12, Activation var13) throws StandardException {
      return new RowChangerImpl(var1, var3, var4, var5, var6, var7, var8, var9, var11, var10, (FormatableBitSet)null, var12, var13);
   }

   public RowChanger getRowChanger(long var1, StaticCompiledOpenConglomInfo var3, DynamicCompiledOpenConglomInfo var4, IndexRowGenerator[] var5, long[] var6, StaticCompiledOpenConglomInfo[] var7, DynamicCompiledOpenConglomInfo[] var8, int var9, TransactionController var10, int[] var11, FormatableBitSet var12, int[] var13, int[] var14, Activation var15) throws StandardException {
      return new RowChangerImpl(var1, var3, var4, var5, var6, var7, var8, var9, var11, var10, var12, var13, var15);
   }

   InternalTriggerExecutionContext getTriggerExecutionContext(LanguageConnectionContext var1, ConnectionContext var2, String var3, int var4, UUID var5, String var6, Vector var7) throws StandardException {
      return new InternalTriggerExecutionContext(var1, var2, var3, var4, var5, var6, var7);
   }

   public ExecRow getValueRow(int var1) {
      return new ValueRow(var1);
   }

   public ExecIndexRow getIndexableRow(int var1) {
      return new IndexRow(var1);
   }

   public ExecIndexRow getIndexableRow(ExecRow var1) {
      return (ExecIndexRow)(var1 instanceof ExecIndexRow ? (ExecIndexRow)var1 : new IndexValueRow(var1));
   }

   public XPLAINFactoryIF getXPLAINFactory() throws StandardException {
      if (this.xplainFactory == null) {
         this.xplainFactory = (XPLAINFactoryIF)bootServiceModule(false, this, "org.apache.derby.iapi.sql.execute.xplain.XPLAINFactoryIF", (Properties)null);
      }

      return this.xplainFactory;
   }

   private static Object bootServiceModule(boolean var0, Object var1, String var2, Properties var3) throws StandardException {
      return Monitor.bootServiceModule(var0, var1, var2, var3);
   }
}
