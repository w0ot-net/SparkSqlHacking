package org.apache.derby.impl.sql.execute;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.SQLSessionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.ForeignKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.KeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLRef;
import org.apache.derby.shared.common.error.StandardException;

public final class DeferredConstraintsMemory {
   public static BackingStoreHashtable rememberDuplicate(LanguageConnectionContext var0, BackingStoreHashtable var1, UUID var2, DataValueDescriptor[] var3) throws StandardException {
      int var4 = var3.length - 1;
      if (var1 == null) {
         HashMap var5 = var0.getDeferredHashTables();
         ValidationInfo var6 = (ValidationInfo)var5.get(var2);
         if (var6 == null) {
            var1 = makeDeferredHashTable(var0.getTransactionExecute(), var4);
            var5.put(var2, new UniquePkInfo(var1, var2));
         } else {
            var1 = var6.infoRows;
         }
      }

      DataValueDescriptor[] var7 = new DataValueDescriptor[var4];
      System.arraycopy(var3, 0, var7, 0, var4);
      var1.putRow(true, var7, (RowLocation)null);
      return var1;
   }

   public static BackingStoreHashtable rememberCheckViolations(LanguageConnectionContext var0, UUID var1, String var2, String var3, BackingStoreHashtable var4, List var5, RowLocation var6, CheckInfo[] var7) throws StandardException {
      if (var5.isEmpty()) {
         return null;
      } else {
         if (var4 == null) {
            HashMap var8 = var0.getDeferredHashTables();
            CheckInfo var9 = (CheckInfo)var8.get(var1);
            if (var9 == null) {
               var4 = makeDeferredHashTable(var0.getTransactionExecute(), 1);
               CheckInfo var10 = new CheckInfo(var4, var2, var3, var5);
               var8.put(var1, var10);
               var7[0] = var10;
            } else {
               var9.addCulprits(var5);
               var4 = var9.infoRows;
               var7[0] = var9;
            }
         }

         DataValueDescriptor[] var11 = new DataValueDescriptor[]{(new SQLRef(var6)).cloneValue(true)};
         var4.putRow(true, var11, (RowLocation)null);
         return var4;
      }
   }

   public static Enumeration getDeferredCheckConstraintLocations(Activation var0, UUID var1) throws StandardException {
      CheckInfo var2 = (CheckInfo)var0.getLanguageConnectionContext().getDeferredHashTables().get(var1);
      return var2.infoRows.elements();
   }

   public static BackingStoreHashtable rememberFKViolation(LanguageConnectionContext var0, BackingStoreHashtable var1, UUID var2, DataValueDescriptor[] var3, String var4, String var5) throws StandardException {
      if (var1 == null) {
         HashMap var6 = var0.getDeferredHashTables();
         ValidationInfo var7 = (ValidationInfo)var6.get(var2);
         if (var7 == null) {
            var1 = makeDeferredHashTable(var0.getTransactionExecute(), var3.length);
            var6.put(var2, new ForeignKeyInfo(var1, var2, var4, var5));
         } else {
            var1 = var7.infoRows;
         }
      }

      DataValueDescriptor[] var8 = new DataValueDescriptor[var3.length];
      System.arraycopy(var3, 0, var8, 0, var3.length);
      var1.putRow(true, var8, (RowLocation)null);
      return var1;
   }

   private static BackingStoreHashtable makeDeferredHashTable(TransactionController var0, int var1) throws StandardException {
      int[] var2 = new int[var1];

      for(int var3 = 0; var3 < var1; var2[var3] = var3++) {
      }

      return new BackingStoreHashtable(var0, (RowSource)null, var2, true, -1L, -1L, -1, -1.0F, false, false);
   }

   public static void compressOrTruncate(LanguageConnectionContext var0, UUID var1, String var2) throws StandardException {
      HashMap var3 = var0.getDeferredHashTables();
      TableDescriptor var4 = var0.getDataDictionary().getTableDescriptor(var1);
      ValidationInfo var5 = (ValidationInfo)var3.get(var1);
      if (var4 == null) {
         throw StandardException.newException("X0X05.S", new Object[]{var2});
      } else {
         if (var5 != null && var5 instanceof CheckInfo) {
            ((CheckInfo)var5).setInvalidatedRowLocations();
         }

      }
   }

   public abstract static class ValidationInfo {
      public final BackingStoreHashtable infoRows;

      public ValidationInfo(BackingStoreHashtable var1) {
         this.infoRows = var1;
      }

      public abstract void possiblyValidateOnReturn(LanguageConnectionContext var1, SQLSessionContext var2, SQLSessionContext var3) throws StandardException;

      public abstract void validateConstraint(LanguageConnectionContext var1, UUID var2, boolean var3) throws StandardException;
   }

   private static class UniquePkInfo extends ValidationInfo {
      private final UUID constraintId;

      public UniquePkInfo(BackingStoreHashtable var1, UUID var2) {
         super(var1);
         this.constraintId = var2;
      }

      public final void possiblyValidateOnReturn(LanguageConnectionContext var1, SQLSessionContext var2, SQLSessionContext var3) throws StandardException {
         if (!var1.isEffectivelyDeferred(var3, this.constraintId)) {
            this.validateUniquePK(var1, this.infoRows, true);
         }
      }

      public final void validateConstraint(LanguageConnectionContext var1, UUID var2, boolean var3) throws StandardException {
         this.validateUniquePK(var1, this.infoRows, var3);
      }

      private void validateUniquePK(LanguageConnectionContext var1, BackingStoreHashtable var2, boolean var3) throws StandardException {
         TransactionController var4 = var1.getTransactionExecute();
         Enumeration var5 = var2.elements();
         DataDictionary var6 = var1.getDataDictionary();
         KeyConstraintDescriptor var7 = (KeyConstraintDescriptor)var6.getConstraintDescriptor(this.constraintId);
         if (var7 != null) {
            long var8 = var7.getIndexConglomerateDescriptor(var6).getConglomerateNumber();

            while(var5.hasMoreElements()) {
               DataValueDescriptor[] var10 = (DataValueDescriptor[])var5.nextElement();
               ScanController var11 = null;
               boolean var12 = false;

               try {
                  var11 = var4.openScan(var8, false, 0, 6, 3, (FormatableBitSet)null, var10, 1, (Qualifier[][])null, var10, -1);
                  if (var11.next() && var11.next()) {
                     throw StandardException.newException(var3 ? "23506.T.1" : "23507.S.1", new Object[]{var7.getConstraintName(), var7.getTableDescriptor().getName()});
                  }
               } catch (StandardException var20) {
                  var12 = true;
                  throw var20;
               } finally {
                  try {
                     if (var11 != null) {
                        var11.close();
                     }
                  } catch (StandardException var21) {
                     if (!var12) {
                        throw var21;
                     }
                  }

               }
            }

         }
      }
   }

   public static class CheckInfo extends ValidationInfo {
      private final String schemaName;
      private final String tableName;
      private List culprits;
      private boolean invalidatedDueToCompress;

      public CheckInfo(BackingStoreHashtable var1, String var2, String var3, List var4) {
         super(var1);
         this.schemaName = var2;
         this.tableName = var3;
         this.culprits = new ArrayList(var4);
      }

      public void setInvalidatedRowLocations() {
         this.invalidatedDueToCompress = true;
      }

      public boolean isInvalidated() {
         return this.invalidatedDueToCompress;
      }

      public void addCulprits(List var1) {
         HashSet var2 = new HashSet(this.culprits);
         var2.addAll(var1);
         this.culprits = new ArrayList(var2);
      }

      public List getCulprints() {
         return this.culprits;
      }

      public void possiblyValidateOnReturn(LanguageConnectionContext var1, SQLSessionContext var2, SQLSessionContext var3) throws StandardException {
         boolean var4 = true;

         for(UUID var6 : this.getCulprints()) {
            if (!var1.isEffectivelyDeferred(var3, var6) && var1.isEffectivelyDeferred(var2, var6)) {
               var4 = false;
               break;
            }
         }

         if (!var4) {
            this.validateCheck(var1, (UUID)null, true);
         }
      }

      public final void validateConstraint(LanguageConnectionContext var1, UUID var2, boolean var3) throws StandardException {
         this.validateCheck(var1, var2, var3);
      }

      private void validateCheck(LanguageConnectionContext var1, UUID var2, boolean var3) throws StandardException {
         TransactionController var4 = var1.getTransactionExecute();
         DataDictionary var5 = var1.getDataDictionary();
         SchemaDescriptor var6 = var5.getSchemaDescriptor(this.schemaName, var4, true);
         if (var6 != null) {
            TableDescriptor var7 = var5.getTableDescriptor(this.tableName, var6, var4);
            if (var7 != null) {
               String var8 = var7.getUUID().toString();

               for(UUID var10 : this.culprits) {
                  if (var2 == null || var2.equals(var10)) {
                     ConstraintDescriptor var11 = var5.getConstraintDescriptor(var10);
                     if (var11 == null) {
                        break;
                     }

                     StringBuilder var12 = new StringBuilder();
                     var12.append("SELECT 1 FROM ");
                     var12.append(var7.getQualifiedName());
                     if (!this.isInvalidated()) {
                        var12.append(" --DERBY-PROPERTIES joinStrategy=nestedLoop,                     index=null,                     validateCheckConstraint=");
                        var12.append(var8);
                        var12.append('\n');
                     }

                     var12.append(" WHERE NOT(");
                     var12.append(var11.getConstraintText());
                     var12.append(')');
                     BasicNoPutResultSetImpl var13 = null;
                     PreparedStatement var14 = var1.prepareInternalStatement(var1.getDefaultSchema(), var12.toString(), true, true);
                     StatementContext var15 = null;

                     try {
                        var15 = var1.pushStatementContext(true, true, var12.toString(), (ParameterValueSet)null, false, 0L);
                        var13 = (BasicNoPutResultSetImpl)var14.execute(var14.getActivation(var1, false), false, 0L);
                        ExecRow var16 = var13.getNextRowCore();
                        if (var16 != null) {
                           throw StandardException.newException(var3 ? "23514.T.1" : "23515.S.1", new Object[]{var11.getConstraintName(), var7.getQualifiedName(), var11.getConstraintText()});
                        }
                     } finally {
                        if (var15 != null) {
                           var1.popStatementContext(var15, (Throwable)null);
                        }

                        if (var13 != null) {
                           try {
                              var13.close();
                           } catch (StandardException var22) {
                           }
                        }

                     }
                  }
               }
            }

         }
      }
   }

   private static class ForeignKeyInfo extends ValidationInfo {
      private final UUID fkId;
      private final String schemaName;
      private final String tableName;

      public ForeignKeyInfo(BackingStoreHashtable var1, UUID var2, String var3, String var4) {
         super(var1);
         this.fkId = var2;
         this.tableName = var4;
         this.schemaName = var3;
      }

      public UUID getFkId() {
         return this.fkId;
      }

      public void possiblyValidateOnReturn(LanguageConnectionContext var1, SQLSessionContext var2, SQLSessionContext var3) throws StandardException {
         if (!var1.isEffectivelyDeferred(var3, this.getFkId())) {
            this.validateForeignKey(var1, true);
         }
      }

      public final void validateConstraint(LanguageConnectionContext var1, UUID var2, boolean var3) throws StandardException {
         this.validateForeignKey(var1, var3);
      }

      private void validateForeignKey(LanguageConnectionContext var1, boolean var2) throws StandardException {
         TransactionController var3 = var1.getTransactionExecute();
         DataDictionary var4 = var1.getDataDictionary();
         ForeignKeyConstraintDescriptor var5 = (ForeignKeyConstraintDescriptor)var4.getConstraintDescriptor(this.fkId);
         if (var5 != null) {
            ReferencedKeyConstraintDescriptor var6 = var5.getReferencedConstraint();
            long[] var7 = new long[]{var5.getIndexConglomerateDescriptor(var4).getConglomerateNumber(), var6.getIndexConglomerateDescriptor(var4).getConglomerateNumber()};
            Enumeration var8 = this.infoRows.elements();

            while(var8.hasMoreElements()) {
               DataValueDescriptor[] var9 = (DataValueDescriptor[])var8.nextElement();
               ScanController var10 = null;
               boolean var11 = false;

               for(int var12 = 0; var12 < 2; ++var12) {
                  boolean var13 = false;

                  try {
                     var10 = var3.openScan(var7[var12], false, 0, 6, 2, (FormatableBitSet)null, var9, 1, (Qualifier[][])null, var9, -1);
                     if (var12 == 0) {
                        if (!var10.next()) {
                           break;
                        }
                     } else if (!var10.next()) {
                        var11 = true;
                     }
                  } catch (StandardException var23) {
                     var13 = true;
                     throw var23;
                  } finally {
                     try {
                        if (var10 != null) {
                           var10.close();
                        }
                     } catch (StandardException var22) {
                        if (!var13) {
                           throw var22;
                        }
                     }

                  }
               }

               if (var11) {
                  SchemaDescriptor var25 = var4.getSchemaDescriptor(this.schemaName, var3, true);
                  TableDescriptor var26 = var4.getTableDescriptor(this.tableName, var25, var3);
                  TableDescriptor var14 = var6.getTableDescriptor();
                  throw StandardException.newException(var2 ? "23516.T.1" : "23517.S.1", new Object[]{var5.getConstraintName(), var26.getQualifiedName(), var6.getConstraintName(), var14.getQualifiedName(), RowUtil.toString((Object[])var9)});
               }
            }

         }
      }
   }
}
