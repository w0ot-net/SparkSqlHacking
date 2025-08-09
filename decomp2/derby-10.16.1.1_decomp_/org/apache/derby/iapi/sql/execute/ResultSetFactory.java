package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface ResultSetFactory {
   String MODULE = "org.apache.derby.iapi.sql.execute.ResultSetFactory";

   ResultSet getDDLResultSet(Activation var1) throws StandardException;

   ResultSet getMiscResultSet(Activation var1) throws StandardException;

   ResultSet getSetTransactionResultSet(Activation var1) throws StandardException;

   ResultSet getInsertResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, int var4, String var5, String var6) throws StandardException;

   ResultSet getInsertVTIResultSet(NoPutResultSet var1, NoPutResultSet var2) throws StandardException;

   ResultSet getDeleteVTIResultSet(NoPutResultSet var1) throws StandardException;

   ResultSet getDeleteResultSet(NoPutResultSet var1) throws StandardException;

   ResultSet getMergeResultSet(NoPutResultSet var1) throws StandardException;

   ResultSet getDeleteCascadeResultSet(NoPutResultSet var1, int var2, ResultSet[] var3, String var4) throws StandardException;

   ResultSet getUpdateResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3) throws StandardException;

   ResultSet getUpdateVTIResultSet(NoPutResultSet var1) throws StandardException;

   ResultSet getDeleteCascadeUpdateResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, int var4, int var5) throws StandardException;

   ResultSet getCallStatementResultSet(GeneratedMethod var1, Activation var2) throws StandardException;

   NoPutResultSet getProjectRestrictResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, int var4, GeneratedMethod var5, int var6, int var7, boolean var8, boolean var9, boolean var10, String var11, double var12, double var14) throws StandardException;

   NoPutResultSet getHashTableResultSet(NoPutResultSet var1, GeneratedMethod var2, Qualifier[][] var3, GeneratedMethod var4, int var5, int var6, boolean var7, int var8, boolean var9, long var10, int var12, float var13, double var14, double var16) throws StandardException;

   NoPutResultSet getSortResultSet(NoPutResultSet var1, boolean var2, boolean var3, int var4, int var5, int var6, int var7, double var8, double var10) throws StandardException;

   NoPutResultSet getScalarAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, int var5, int var6, int var7, boolean var8, double var9, double var11) throws StandardException;

   NoPutResultSet getDistinctScalarAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, int var5, int var6, int var7, boolean var8, double var9, double var11) throws StandardException;

   NoPutResultSet getGroupedAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, int var5, int var6, int var7, double var8, double var10, boolean var12) throws StandardException;

   NoPutResultSet getDistinctGroupedAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, int var5, int var6, int var7, double var8, double var10, boolean var12) throws StandardException;

   NoPutResultSet getAnyResultSet(NoPutResultSet var1, GeneratedMethod var2, int var3, int var4, int var5, double var6, double var8) throws StandardException;

   NoPutResultSet getOnceResultSet(NoPutResultSet var1, GeneratedMethod var2, int var3, int var4, int var5, int var6, double var7, double var9) throws StandardException;

   NoPutResultSet getRowResultSet(Activation var1, GeneratedMethod var2, boolean var3, int var4, double var5, double var7) throws StandardException;

   NoPutResultSet getVTIResultSet(Activation var1, int var2, int var3, GeneratedMethod var4, String var5, Qualifier[][] var6, int var7, boolean var8, boolean var9, int var10, boolean var11, int var12, double var13, double var15, boolean var17, int var18, int var19, int var20, String var21, String var22) throws StandardException;

   NoPutResultSet getHashScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, Qualifier[][] var13, int var14, float var15, int var16, int var17, String var18, String var19, String var20, boolean var21, boolean var22, int var23, int var24, int var25, boolean var26, int var27, double var28, double var30) throws StandardException;

   NoPutResultSet getDistinctScanResultSet(Activation var1, long var2, int var4, int var5, int var6, int var7, String var8, String var9, String var10, boolean var11, int var12, int var13, boolean var14, int var15, double var16, double var18) throws StandardException;

   NoPutResultSet getValidateCheckConstraintResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, boolean var23, double var24, double var26) throws StandardException;

   NoPutResultSet getTableScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, boolean var23, double var24, double var26) throws StandardException;

   NoPutResultSet getBulkTableScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, int var23, boolean var24, boolean var25, double var26, double var28) throws StandardException;

   NoPutResultSet getMultiProbeTableScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, DataValueDescriptor[] var13, int var14, String var15, String var16, String var17, boolean var18, boolean var19, int var20, int var21, int var22, boolean var23, int var24, boolean var25, double var26, double var28) throws StandardException;

   NoPutResultSet getIndexRowToBaseRowResultSet(long var1, int var3, NoPutResultSet var4, int var5, int var6, String var7, int var8, int var9, int var10, int var11, GeneratedMethod var12, boolean var13, double var14, double var16, int var18) throws StandardException;

   NoPutResultSet getWindowResultSet(Activation var1, NoPutResultSet var2, int var3, int var4, int var5, GeneratedMethod var6, double var7, double var9) throws StandardException;

   NoPutResultSet getNestedLoopJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, GeneratedMethod var5, int var6, boolean var7, boolean var8, double var9, double var11, String var13) throws StandardException;

   NoPutResultSet getHashJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, GeneratedMethod var5, int var6, boolean var7, boolean var8, double var9, double var11, String var13) throws StandardException;

   NoPutResultSet getNestedLoopLeftOuterJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, GeneratedMethod var5, int var6, GeneratedMethod var7, boolean var8, boolean var9, boolean var10, double var11, double var13, String var15) throws StandardException;

   NoPutResultSet getHashLeftOuterJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, GeneratedMethod var5, int var6, GeneratedMethod var7, boolean var8, boolean var9, boolean var10, double var11, double var13, String var15) throws StandardException;

   NoPutResultSet getMaterializedResultSet(NoPutResultSet var1, int var2, double var3, double var5) throws StandardException;

   NoPutResultSet getScrollInsensitiveResultSet(NoPutResultSet var1, Activation var2, int var3, int var4, boolean var5, double var6, double var8) throws StandardException;

   NoPutResultSet getNormalizeResultSet(NoPutResultSet var1, int var2, int var3, double var4, double var6, boolean var8) throws StandardException;

   NoPutResultSet getCurrentOfResultSet(String var1, Activation var2, int var3);

   NoPutResultSet getUnionResultSet(NoPutResultSet var1, NoPutResultSet var2, int var3, double var4, double var6) throws StandardException;

   NoPutResultSet getSetOpResultSet(NoPutResultSet var1, NoPutResultSet var2, Activation var3, int var4, long var5, double var7, int var9, boolean var10, int var11, int var12, int var13) throws StandardException;

   NoPutResultSet getLastIndexKeyResultSet(Activation var1, int var2, int var3, long var4, String var6, String var7, String var8, int var9, int var10, boolean var11, int var12, double var13, double var15) throws StandardException;

   NoPutResultSet getRaDependentTableScanResultSet(Activation var1, long var2, int var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, boolean var23, double var24, double var26, String var28, long var29, int var31, int var32) throws StandardException;

   NoPutResultSet getRowCountResultSet(NoPutResultSet var1, Activation var2, int var3, GeneratedMethod var4, GeneratedMethod var5, boolean var6, double var7, double var9) throws StandardException;
}
