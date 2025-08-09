package org.apache.derby.iapi.db;

import java.sql.SQLException;
import org.apache.derby.iapi.security.Securable;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

public class ConsistencyChecker {
   private ConsistencyChecker() {
   }

   public static boolean checkTable(String var0, String var1) throws SQLException {
      long var4 = -1L;
      RowLocation var11 = null;
      Object var12 = null;
      ScanController var13 = null;
      int var15 = 0;
      ConglomerateController var19 = null;
      ConglomerateController var20 = null;
      LanguageConnectionContext var23 = ConnectionUtil.getCurrentLCC();
      TransactionController var6 = var23.getTransactionExecute();

      boolean var25;
      try {
         SecurityUtil.authorize(Securable.CHECK_TABLE);
         DataDictionary var2 = var23.getDataDictionary();
         DataValueFactory var16 = var23.getDataValueFactory();
         ExecutionFactory var24 = var23.getLanguageConnectionFactory().getExecutionFactory();
         SchemaDescriptor var21 = var2.getSchemaDescriptor(var0, var6, true);
         TableDescriptor var3 = var2.getTableDescriptor(var1, var21, var6);
         if (var3 == null) {
            throw StandardException.newException("42X05", new Object[]{var0 + "." + var1});
         }

         if (var3.getTableType() != 2) {
            var19 = var6.openConglomerate(var3.getHeapConglomerateId(), false, 0, 7, 5);
            var19.checkConsistency();
            ConglomerateDescriptor var7 = var3.getConglomerateDescriptor(var3.getHeapConglomerateId());
            ExecRow var9 = var24.getValueRow(var3.getNumberOfColumns());
            ColumnDescriptorList var57 = var3.getColumnDescriptorList();
            int var26 = var57.size();

            for(int var27 = 0; var27 < var26; ++var27) {
               ColumnDescriptor var28 = var57.elementAt(var27);
               var9.setColumn(var28.getPosition(), var28.getType().getNull());
            }

            ConglomerateDescriptor[] var58 = var3.getConglomerateDescriptors();

            for(int var59 = 0; var59 < var58.length; ++var59) {
               ConglomerateDescriptor var8 = var58[var59];
               if (var8.isIndex()) {
                  var20 = var6.openConglomerate(var8.getConglomerateNumber(), false, 0, 7, 5);
                  var20.checkConsistency();
                  var20.close();
                  var20 = null;
                  if (var8.isConstraint()) {
                     ConstraintDescriptor var22 = var2.getConstraintDescriptor(var3, var8.getUUID());
                     if (var22 == null) {
                        throw StandardException.newException("42X94", new Object[]{"CONSTRAINT for INDEX", var8.getConglomerateName()});
                     }
                  }

                  if (var4 < 0L) {
                     var13 = var6.openScan(var7.getConglomerateNumber(), false, 0, 7, 5, RowUtil.EMPTY_ROW_BITSET, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
                     var11 = var13.newRowLocationTemplate();
                     RowLocation var48 = var13.newRowLocationTemplate();

                     for(var4 = 0L; var13.next(); ++var4) {
                     }

                     var13.close();
                     Object var50 = null;
                  }

                  int[] var14 = var8.getIndexDescriptor().baseColumnPositions();
                  var15 = var14.length;
                  FormatableBitSet var29 = new FormatableBitSet();

                  for(int var30 = 0; var30 < var15; ++var30) {
                     var29.grow(var14[var30]);
                     var29.set(var14[var30] - 1);
                  }

                  ExecRow var10 = var24.getValueRow(var15 + 1);

                  for(int var62 = 0; var62 < var15; ++var62) {
                     ColumnDescriptor var31 = var3.getColumnDescriptor(var14[var62]);
                     var10.setColumn(var62 + 1, var31.getType().getNull());
                  }

                  var10.setColumn(var15 + 1, var11);
                  var13 = var6.openScan(var8.getConglomerateNumber(), false, 0, 7, 5, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
                  DataValueDescriptor[] var63 = new DataValueDescriptor[var15];
                  DataValueDescriptor[] var65 = var9.getRowArray();

                  for(int var32 = 0; var32 < var15; ++var32) {
                     var63[var32] = var65[var14[var32] - 1];
                  }

                  long var17;
                  for(var17 = 0L; var13.fetchNext(var10.getRowArray()); ++var17) {
                     RowLocation var66 = (RowLocation)var10.getColumn(var15 + 1);
                     boolean var33 = var19.fetch(var66, var65, var29);
                     if (!var33) {
                        String var67 = var8.getConglomerateName();
                        throw StandardException.newException("X0X62.S", new Object[]{var0 + "." + var1, var67, var66.toString(), var10.toString()});
                     }

                     for(int var34 = 0; var34 < var15; ++var34) {
                        DataValueDescriptor var35 = var10.getColumn(var34 + 1);
                        DataValueDescriptor var36 = var63[var34];
                        if (var35.compare(var36) != 0) {
                           ColumnDescriptor var37 = var3.getColumnDescriptor(var14[var34]);
                           throw StandardException.newException("X0X61.S", new Object[]{var8.getConglomerateName(), var3.getSchemaName(), var3.getName(), var66.toString(), var37.getColumnName(), var35.toString(), var36.toString(), var10.toString()});
                        }
                     }
                  }

                  var13.close();
                  var13 = null;
                  if (var17 != var4) {
                     throw StandardException.newException("X0Y55.S", new Object[]{var8.getConglomerateName(), var3.getSchemaName(), var3.getName(), Long.toString(var17), Long.toString(var4)});
                  }
               }
            }

            ConstraintDescriptorList var60 = var2.getConstraintDescriptors(var3);

            for(int var61 = 0; var61 < var60.size(); ++var61) {
               ConstraintDescriptor var56 = var60.elementAt(var61);
               if (var56.hasBackingIndex()) {
                  ConglomerateDescriptor var64 = var3.getConglomerateDescriptor(var56.getConglomerateId());
                  if (var64 == null) {
                     throw StandardException.newException("42X94", new Object[]{"INDEX for CONSTRAINT", var56.getConstraintName()});
                  }
               }
            }

            return true;
         }

         var25 = true;
      } catch (StandardException var46) {
         throw PublicAPI.wrapStandardException(var46);
      } finally {
         try {
            if (var19 != null) {
               var19.close();
               Object var53 = null;
            }

            if (var20 != null) {
               var20.close();
               Object var55 = null;
            }

            if (var13 != null) {
               var13.close();
               Object var51 = null;
            }
         } catch (StandardException var45) {
            throw PublicAPI.wrapStandardException(var45);
         }

      }

      return var25;
   }
}
