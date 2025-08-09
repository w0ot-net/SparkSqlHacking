package org.apache.derby.impl.sql.execute;

import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.AliasInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.conn.Authorizer;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.sql.compile.TableName;
import org.apache.derby.shared.common.error.StandardException;

public class GenericConstantActionFactory {
   public ConstantAction getSetConstraintsConstantAction(List var1, boolean var2) {
      return new SetConstraintsConstantAction(var1, var2);
   }

   public ConstantAction getAlterTableConstantAction(SchemaDescriptor var1, String var2, UUID var3, long var4, int var6, ColumnInfo[] var7, ConstraintConstantAction[] var8, char var9, boolean var10, int var11, boolean var12, boolean var13, boolean var14, boolean var15, boolean var16, boolean var17, boolean var18, boolean var19, boolean var20, String var21) {
      return new AlterTableConstantAction(var1, var2, var3, var4, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21);
   }

   public CreateConstraintConstantAction getCreateConstraintConstantAction(String var1, int var2, boolean[] var3, boolean var4, String var5, UUID var6, String var7, String[] var8, IndexConstantAction var9, String var10, ConstraintInfo var11, ProviderInfo[] var12) {
      return new CreateConstraintConstantAction(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }

   public IndexConstantAction getCreateIndexConstantAction(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, int var6, String var7, String var8, String var9, String var10, UUID var11, String[] var12, boolean[] var13, boolean var14, UUID var15, Properties var16) {
      return new CreateIndexConstantAction(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16);
   }

   public ConstantAction getCreateAliasConstantAction(String var1, String var2, String var3, AliasInfo var4, char var5) {
      return new CreateAliasConstantAction(var1, var2, var3, var4, var5);
   }

   public ConstantAction getCreateSchemaConstantAction(String var1, String var2) {
      return new CreateSchemaConstantAction(var1, var2);
   }

   public ConstantAction getCreateRoleConstantAction(String var1) {
      return new CreateRoleConstantAction(var1);
   }

   public ConstantAction getSetRoleConstantAction(String var1, int var2) {
      return new SetRoleConstantAction(var1, var2);
   }

   public ConstantAction getCreateSequenceConstantAction(TableName var1, DataTypeDescriptor var2, long var3, long var5, long var7, long var9, boolean var11) {
      return new CreateSequenceConstantAction(var1.getSchemaName(), var1.getTableName(), var2, var3, var5, var7, var9, var11);
   }

   public ConstantAction getCreateTableConstantAction(String var1, String var2, int var3, ColumnInfo[] var4, CreateConstraintConstantAction[] var5, Properties var6, char var7, boolean var8, boolean var9) {
      return new CreateTableConstantAction(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public ConstantAction getSavepointConstantAction(String var1, int var2) {
      return new SavepointConstantAction(var1, var2);
   }

   public ConstantAction getCreateViewConstantAction(String var1, String var2, int var3, String var4, int var5, ColumnInfo[] var6, ProviderInfo[] var7, UUID var8) {
      return new CreateViewConstantAction(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public ConstantAction getDeleteConstantAction(long var1, int var3, StaticCompiledOpenConglomInfo var4, IndexRowGenerator[] var5, long[] var6, StaticCompiledOpenConglomInfo[] var7, boolean var8, boolean var9, UUID var10, int var11, Object var12, Object var13, int[] var14, long var15, String var17, String var18, ResultDescription var19, FKInfo[] var20, TriggerInfo var21, FormatableBitSet var22, int[] var23, int[] var24, int var25, UUID var26, boolean var27, ConstantAction[] var28, boolean var29) throws StandardException {
      return new DeleteConstantAction(var1, var4, var5, var6, var7, var8, var10, var11, var20, var21, var22, var23, var24, var25, var27, var19, var28, var29);
   }

   public ConstraintConstantAction getDropConstraintConstantAction(String var1, String var2, String var3, UUID var4, String var5, IndexConstantAction var6, int var7, int var8) {
      return new DropConstraintConstantAction(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public ConstraintConstantAction getAlterConstraintConstantAction(String var1, String var2, boolean[] var3, String var4, UUID var5, String var6, IndexConstantAction var7) {
      return new AlterConstraintConstantAction(var1, var2, var3, var4, var5, var6, var7);
   }

   public IndexConstantAction getDropIndexConstantAction(String var1, String var2, String var3, String var4, UUID var5, long var6) {
      return new DropIndexConstantAction(var1, var2, var3, var4, var5, var6);
   }

   public ConstantAction getDropAliasConstantAction(SchemaDescriptor var1, String var2, char var3) {
      return new DropAliasConstantAction(var1, var2, var3);
   }

   public ConstantAction getDropRoleConstantAction(String var1) {
      return new DropRoleConstantAction(var1);
   }

   public ConstantAction getDropSequenceConstantAction(SchemaDescriptor var1, String var2) {
      return new DropSequenceConstantAction(var1, var2);
   }

   public ConstantAction getDropSchemaConstantAction(String var1) {
      return new DropSchemaConstantAction(var1);
   }

   public ConstantAction getDropTableConstantAction(String var1, String var2, SchemaDescriptor var3, long var4, UUID var6, int var7) {
      return new DropTableConstantAction(var1, var2, var3, var4, var6, var7);
   }

   public ConstantAction getDropViewConstantAction(String var1, String var2, SchemaDescriptor var3) {
      return new DropViewConstantAction(var1, var2, var3);
   }

   public ConstantAction getRenameConstantAction(String var1, String var2, String var3, String var4, SchemaDescriptor var5, UUID var6, boolean var7, int var8) {
      return new RenameConstantAction(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public ConstantAction getInsertConstantAction(TableDescriptor var1, long var2, StaticCompiledOpenConglomInfo var4, IndexRowGenerator[] var5, long[] var6, StaticCompiledOpenConglomInfo[] var7, String[] var8, boolean var9, boolean var10, boolean var11, UUID var12, int var13, Object var14, Object var15, Properties var16, FKInfo[] var17, TriggerInfo var18, int[] var19, boolean[] var20, UUID var21, Object[] var22, Object[] var23, boolean var24, RowLocation[] var25, boolean var26, String var27) throws StandardException {
      return new InsertConstantAction(var1, var2, var4, var5, var6, var7, var8, var9, var11, var16, var12, var13, var17, var18, var19, var20, var24, var25, var26, var27);
   }

   public ConstantAction getUpdatableVTIConstantAction(int var1, boolean var2) throws StandardException {
      return new UpdatableVTIConstantAction(var1, var2, (int[])null);
   }

   public ConstantAction getUpdatableVTIConstantAction(int var1, boolean var2, int[] var3) throws StandardException {
      return new UpdatableVTIConstantAction(var1, var2, var3);
   }

   public ConstantAction getLockTableConstantAction(String var1, long var2, boolean var4) {
      return new LockTableConstantAction(var1, var2, var4);
   }

   public ConstantAction getSetSchemaConstantAction(String var1, int var2) {
      return new SetSchemaConstantAction(var1, var2);
   }

   public ConstantAction getSetTransactionIsolationConstantAction(int var1) {
      return new SetTransactionIsolationConstantAction(var1);
   }

   public UpdateConstantAction getUpdateConstantAction(TableDescriptor var1, StaticCompiledOpenConglomInfo var2, IndexRowGenerator[] var3, long[] var4, StaticCompiledOpenConglomInfo[] var5, String[] var6, boolean var7, UUID var8, int var9, boolean var10, int[] var11, int[] var12, Object var13, FKInfo[] var14, TriggerInfo var15, FormatableBitSet var16, int[] var17, int[] var18, int var19, boolean var20, boolean var21, RowLocation[] var22, boolean var23, String var24) throws StandardException {
      return new UpdateConstantAction(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24);
   }

   protected static Authorizer getAuthorizer() {
      LanguageConnectionContext var0 = (LanguageConnectionContext)getContext("LanguageConnectionContext");
      return var0.getAuthorizer();
   }

   public ConstantAction getCreateTriggerConstantAction(String var1, String var2, int var3, boolean var4, boolean var5, boolean var6, TableDescriptor var7, UUID var8, String var9, UUID var10, String var11, UUID var12, int[] var13, int[] var14, String var15, String var16, boolean var17, boolean var18, String var19, String var20, ProviderInfo[] var21) {
      return new CreateTriggerConstantAction(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21);
   }

   public ConstantAction getDropTriggerConstantAction(SchemaDescriptor var1, String var2, UUID var3) {
      return new DropTriggerConstantAction(var1, var2, var3);
   }

   public ConstantAction getDropStatisticsConstantAction(SchemaDescriptor var1, String var2, String var3, boolean var4) {
      return new DropStatisticsConstantAction(var1, var2, var3, var4);
   }

   public ConstantAction getGrantConstantAction(PrivilegeInfo var1, List var2) {
      return new GrantRevokeConstantAction(true, var1, var2);
   }

   public ConstantAction getGrantRoleConstantAction(List var1, List var2) {
      return new GrantRoleConstantAction(var1, var2);
   }

   public ConstantAction getRevokeConstantAction(PrivilegeInfo var1, List var2) {
      return new GrantRevokeConstantAction(false, var1, var2);
   }

   public ConstantAction getRevokeRoleConstantAction(List var1, List var2) {
      return new RevokeRoleConstantAction(var1, var2);
   }

   public ConstantAction getMatchingClauseConstantAction(int var1, String var2, ResultDescription var3, String var4, String var5, String var6, ConstantAction var7) {
      return new MatchingClauseConstantAction(var1, var2, var3, var4, var5, var6, var7);
   }

   public MergeConstantAction getMergeConstantAction(ConstantAction[] var1) {
      return new MergeConstantAction(var1);
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
