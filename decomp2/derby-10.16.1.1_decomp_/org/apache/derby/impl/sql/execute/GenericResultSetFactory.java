package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.conn.Authorizer;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.ResultSetFactory;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class GenericResultSetFactory implements ResultSetFactory {
   public ResultSet getInsertResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, int var4, String var5, String var6) throws StandardException {
      Activation var7 = var1.getActivation();
      getAuthorizer(var7).authorize(var7, 0);
      return new InsertResultSet(var1, var2, var3, var4, var5, var6, var7);
   }

   public ResultSet getInsertVTIResultSet(NoPutResultSet var1, NoPutResultSet var2) throws StandardException {
      Activation var3 = var1.getActivation();
      getAuthorizer(var3).authorize(var3, 0);
      return new InsertVTIResultSet(var1, var2, var3);
   }

   public ResultSet getDeleteVTIResultSet(NoPutResultSet var1) throws StandardException {
      Activation var2 = var1.getActivation();
      getAuthorizer(var2).authorize(var2, 0);
      return new DeleteVTIResultSet(var1, var2);
   }

   public ResultSet getDeleteResultSet(NoPutResultSet var1) throws StandardException {
      Activation var2 = var1.getActivation();
      getAuthorizer(var2).authorize(var2, 0);
      return new DeleteResultSet(var1, var2);
   }

   public ResultSet getMergeResultSet(NoPutResultSet var1) throws StandardException {
      Activation var2 = var1.getActivation();
      getAuthorizer(var2).authorize(var2, 0);
      return new MergeResultSet(var1, var2);
   }

   public ResultSet getDeleteCascadeResultSet(NoPutResultSet var1, int var2, ResultSet[] var3, String var4) throws StandardException {
      Activation var5 = var1.getActivation();
      getAuthorizer(var5).authorize(var5, 0);
      return new DeleteCascadeResultSet(var1, var5, var2, var3, var4);
   }

   public ResultSet getUpdateResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3) throws StandardException {
      Activation var4 = var1.getActivation();
      getAuthorizer(var4).authorize(var4, 0);
      return new UpdateResultSet(var1, var2, var3, var4);
   }

   public ResultSet getUpdateVTIResultSet(NoPutResultSet var1) throws StandardException {
      Activation var2 = var1.getActivation();
      getAuthorizer(var2).authorize(var2, 0);
      return new UpdateVTIResultSet(var1, var2);
   }

   public ResultSet getDeleteCascadeUpdateResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, int var4, int var5) throws StandardException {
      Activation var6 = var1.getActivation();
      getAuthorizer(var6).authorize(var6, 0);
      return new UpdateResultSet(var1, var2, var3, var6, var4, var5);
   }

   public ResultSet getCallStatementResultSet(GeneratedMethod var1, Activation var2) throws StandardException {
      getAuthorizer(var2).authorize(var2, 3);
      return new CallStatementResultSet(var1, var2);
   }

   public NoPutResultSet getProjectRestrictResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, int var4, GeneratedMethod var5, int var6, int var7, boolean var8, boolean var9, boolean var10, String var11, double var12, double var14) throws StandardException {
      UUID var16 = "NULL".equals(var11) ? null : var1.getActivation().getLanguageConnectionContext().getDataDictionary().getUUIDFactory().recreateUUID(var11);
      return new ProjectRestrictResultSet(var1, var1.getActivation(), var2, var3, var4, var5, var6, var7, var8, var9, var10, var16, var12, var14);
   }

   public NoPutResultSet getHashTableResultSet(NoPutResultSet var1, GeneratedMethod var2, Qualifier[][] var3, GeneratedMethod var4, int var5, int var6, boolean var7, int var8, boolean var9, long var10, int var12, float var13, double var14, double var16) throws StandardException {
      return new HashTableResultSet(var1, var1.getActivation(), var2, var3, var4, var5, var6, var7, var8, var9, var10, var12, var13, true, var14, var16);
   }

   public NoPutResultSet getSortResultSet(NoPutResultSet var1, boolean var2, boolean var3, int var4, int var5, int var6, int var7, double var8, double var10) throws StandardException {
      return new SortResultSet(var1, var2, var3, var4, var1.getActivation(), var5, var6, var7, var8, var10);
   }

   public NoPutResultSet getScalarAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, int var5, int var6, int var7, boolean var8, double var9, double var11) throws StandardException {
      return new ScalarAggregateResultSet(var1, var2, var3, var1.getActivation(), var5, var7, var8, var9, var11);
   }

   public NoPutResultSet getDistinctScalarAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, int var5, int var6, int var7, boolean var8, double var9, double var11) throws StandardException {
      return new DistinctScalarAggregateResultSet(var1, var2, var3, var4, var1.getActivation(), var5, var6, var7, var8, var9, var11);
   }

   public NoPutResultSet getGroupedAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, int var5, int var6, int var7, double var8, double var10, boolean var12) throws StandardException {
      return new GroupedAggregateResultSet(var1, var2, var3, var4, var1.getActivation(), var5, var6, var7, var8, var10, var12);
   }

   public NoPutResultSet getDistinctGroupedAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, int var5, int var6, int var7, double var8, double var10, boolean var12) throws StandardException {
      return new DistinctGroupedAggregateResultSet(var1, var2, var3, var4, var1.getActivation(), var5, var6, var7, var8, var10, var12);
   }

   public NoPutResultSet getAnyResultSet(NoPutResultSet var1, GeneratedMethod var2, int var3, int var4, int var5, double var6, double var8) throws StandardException {
      return new AnyResultSet(var1, var1.getActivation(), var2, var3, var4, var5, var6, var8);
   }

   public NoPutResultSet getOnceResultSet(NoPutResultSet var1, GeneratedMethod var2, int var3, int var4, int var5, int var6, double var7, double var9) throws StandardException {
      return new OnceResultSet(var1, var1.getActivation(), var2, var3, var4, var5, var6, var7, var9);
   }

   public NoPutResultSet getRowResultSet(Activation var1, GeneratedMethod var2, boolean var3, int var4, double var5, double var7) {
      return new RowResultSet(var1, var2, var3, var4, var5, var7);
   }

   public NoPutResultSet getVTIResultSet(Activation var1, int var2, int var3, GeneratedMethod var4, String var5, Qualifier[][] var6, int var7, boolean var8, boolean var9, int var10, boolean var11, int var12, double var13, double var15, boolean var17, int var18, int var19, int var20, String var21, String var22) throws StandardException {
      return new VTIResultSet(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var15, var17, var18, var19, var20, var21, var22);
   }

   public NoPutResultSet getHashScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, Qualifier[][] var13, int var14, float var15, int var16, int var17, String var18, String var19, String var20, boolean var21, boolean var22, int var23, int var24, int var25, boolean var26, int var27, double var28, double var30) throws StandardException {
      StaticCompiledOpenConglomInfo var32 = (StaticCompiledOpenConglomInfo)var1.getPreparedStatement().getSavedObject(var4);
      return new HashScanResultSet(var2, var32, var1, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var25, var26, var27, true, var28, var30);
   }

   public NoPutResultSet getDistinctScanResultSet(Activation var1, long var2, int var4, int var5, int var6, int var7, String var8, String var9, String var10, boolean var11, int var12, int var13, boolean var14, int var15, double var16, double var18) throws StandardException {
      StaticCompiledOpenConglomInfo var20 = (StaticCompiledOpenConglomInfo)var1.getPreparedStatement().getSavedObject(var4);
      return new DistinctScanResultSet(var2, var20, var1, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var18);
   }

   public NoPutResultSet getTableScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, boolean var23, double var24, double var26) throws StandardException {
      StaticCompiledOpenConglomInfo var28 = (StaticCompiledOpenConglomInfo)var1.getPreparedStatement().getSavedObject(var4);
      return new TableScanResultSet(var2, var28, var1, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, 1, var23, var24, var26);
   }

   public NoPutResultSet getValidateCheckConstraintResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, boolean var23, double var24, double var26) throws StandardException {
      StaticCompiledOpenConglomInfo var28 = (StaticCompiledOpenConglomInfo)var1.getPreparedStatement().getSavedObject(var4);
      return new ValidateCheckConstraintResultSet(var2, var28, var1, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, 1, var23, var24, var26);
   }

   public NoPutResultSet getBulkTableScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, int var23, boolean var24, boolean var25, double var26, double var28) throws StandardException {
      StaticCompiledOpenConglomInfo var30 = (StaticCompiledOpenConglomInfo)var1.getPreparedStatement().getSavedObject(var4);
      return new BulkTableScanResultSet(var2, var30, var1, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25, var26, var28);
   }

   public NoPutResultSet getMultiProbeTableScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, DataValueDescriptor[] var13, int var14, String var15, String var16, String var17, boolean var18, boolean var19, int var20, int var21, int var22, boolean var23, int var24, boolean var25, double var26, double var28) throws StandardException {
      StaticCompiledOpenConglomInfo var30 = (StaticCompiledOpenConglomInfo)var1.getPreparedStatement().getSavedObject(var4);
      return new MultiProbeTableScanResultSet(var2, var30, var1, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25, var26, var28);
   }

   public NoPutResultSet getIndexRowToBaseRowResultSet(long var1, int var3, NoPutResultSet var4, int var5, int var6, String var7, int var8, int var9, int var10, int var11, GeneratedMethod var12, boolean var13, double var14, double var16, int var18) throws StandardException {
      return new IndexRowToBaseRowResultSet(var1, var3, var4.getActivation(), var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var18);
   }

   public NoPutResultSet getWindowResultSet(Activation var1, NoPutResultSet var2, int var3, int var4, int var5, GeneratedMethod var6, double var7, double var9) throws StandardException {
      return new WindowResultSet(var1, var2, var3, var4, var5, var6, var7, var9);
   }

   public NoPutResultSet getNestedLoopJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, GeneratedMethod var5, int var6, boolean var7, boolean var8, double var9, double var11, String var13) throws StandardException {
      return new NestedLoopJoinResultSet(var1, var2, var3, var4, var1.getActivation(), var5, var6, var7, var8, var9, var11, var13);
   }

   public NoPutResultSet getHashJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, GeneratedMethod var5, int var6, boolean var7, boolean var8, double var9, double var11, String var13) throws StandardException {
      return new HashJoinResultSet(var1, var2, var3, var4, var1.getActivation(), var5, var6, var7, var8, var9, var11, var13);
   }

   public NoPutResultSet getNestedLoopLeftOuterJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, GeneratedMethod var5, int var6, GeneratedMethod var7, boolean var8, boolean var9, boolean var10, double var11, double var13, String var15) throws StandardException {
      return new NestedLoopLeftOuterJoinResultSet(var1, var2, var3, var4, var1.getActivation(), var5, var6, var7, var8, var9, var10, var11, var13, var15);
   }

   public NoPutResultSet getHashLeftOuterJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, GeneratedMethod var5, int var6, GeneratedMethod var7, boolean var8, boolean var9, boolean var10, double var11, double var13, String var15) throws StandardException {
      return new HashLeftOuterJoinResultSet(var1, var2, var3, var4, var1.getActivation(), var5, var6, var7, var8, var9, var10, var11, var13, var15);
   }

   public ResultSet getSetTransactionResultSet(Activation var1) throws StandardException {
      getAuthorizer(var1).authorize(var1, 2);
      return new SetTransactionResultSet(var1);
   }

   public NoPutResultSet getMaterializedResultSet(NoPutResultSet var1, int var2, double var3, double var5) throws StandardException {
      return new MaterializedResultSet(var1, var1.getActivation(), var2, var3, var5);
   }

   public NoPutResultSet getScrollInsensitiveResultSet(NoPutResultSet var1, Activation var2, int var3, int var4, boolean var5, double var6, double var8) throws StandardException {
      return (NoPutResultSet)(var5 ? new ScrollInsensitiveResultSet(var1, var2, var3, var4, var6, var8) : var1);
   }

   public NoPutResultSet getNormalizeResultSet(NoPutResultSet var1, int var2, int var3, double var4, double var6, boolean var8) throws StandardException {
      return new NormalizeResultSet(var1, var1.getActivation(), var2, var3, var4, var6, var8);
   }

   public NoPutResultSet getCurrentOfResultSet(String var1, Activation var2, int var3) {
      return new CurrentOfResultSet(var1, var2, var3);
   }

   public ResultSet getDDLResultSet(Activation var1) throws StandardException {
      getAuthorizer(var1).authorize(var1, 4);
      return this.getMiscResultSet(var1);
   }

   public ResultSet getMiscResultSet(Activation var1) throws StandardException {
      getAuthorizer(var1).authorize(var1, 2);
      return new MiscResultSet(var1);
   }

   public NoPutResultSet getUnionResultSet(NoPutResultSet var1, NoPutResultSet var2, int var3, double var4, double var6) throws StandardException {
      return new UnionResultSet(var1, var2, var1.getActivation(), var3, var4, var6);
   }

   public NoPutResultSet getSetOpResultSet(NoPutResultSet var1, NoPutResultSet var2, Activation var3, int var4, long var5, double var7, int var9, boolean var10, int var11, int var12, int var13) throws StandardException {
      return new SetOpResultSet(var1, var2, var3, var4, var5, var7, var9, var10, var11, var12, var13);
   }

   public NoPutResultSet getLastIndexKeyResultSet(Activation var1, int var2, int var3, long var4, String var6, String var7, String var8, int var9, int var10, boolean var11, int var12, double var13, double var15) throws StandardException {
      return new LastIndexKeyResultSet(var1, var2, var3, var4, var6, var7, var8, var9, var10, var11, var12, var13, var15);
   }

   public NoPutResultSet getRaDependentTableScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, boolean var23, double var24, double var26, String var28, long var29, int var31, int var32) throws StandardException {
      StaticCompiledOpenConglomInfo var33 = (StaticCompiledOpenConglomInfo)var1.getPreparedStatement().getSavedObject(var4);
      return new DependentResultSet(var2, var33, var1, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var20, var21, var22, 1, var23, var24, var26, var28, var29, var31, var32);
   }

   public NoPutResultSet getRowCountResultSet(NoPutResultSet var1, Activation var2, int var3, GeneratedMethod var4, GeneratedMethod var5, boolean var6, double var7, double var9) throws StandardException {
      return new RowCountResultSet(var1, var2, var3, var4, var5, var6, var7, var9);
   }

   private static Authorizer getAuthorizer(Activation var0) {
      LanguageConnectionContext var1 = var0.getLanguageConnectionContext();
      return var1.getAuthorizer();
   }
}
