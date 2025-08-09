package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SubCheckConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SubConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SubKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLInteger;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSCONSTRAINTSRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSCONSTRAINTS";
   protected static final int SYSCONSTRAINTS_COLUMN_COUNT = 7;
   protected static final int SYSCONSTRAINTS_CONSTRAINTID = 1;
   protected static final int SYSCONSTRAINTS_TABLEID = 2;
   protected static final int SYSCONSTRAINTS_CONSTRAINTNAME = 3;
   protected static final int SYSCONSTRAINTS_TYPE = 4;
   protected static final int SYSCONSTRAINTS_SCHEMAID = 5;
   public static final int SYSCONSTRAINTS_STATE = 6;
   protected static final int SYSCONSTRAINTS_REFERENCECOUNT = 7;
   protected static final int SYSCONSTRAINTS_INDEX1_ID = 0;
   protected static final int SYSCONSTRAINTS_INDEX2_ID = 1;
   protected static final int SYSCONSTRAINTS_INDEX3_ID = 2;
   private static final boolean[] uniqueness = new boolean[]{true, true, false};
   private static final int[][] indexColumnPositions = new int[][]{{1}, {3, 5}, {2}};
   private static final String[] uuids = new String[]{"8000002f-00d0-fd77-3ed8-000a0a0b1900", "80000036-00d0-fd77-3ed8-000a0a0b1900", "80000031-00d0-fd77-3ed8-000a0a0b1900", "80000033-00d0-fd77-3ed8-000a0a0b1900", "80000035-00d0-fd77-3ed8-000a0a0b1900"};

   SYSCONSTRAINTSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(7, "SYSCONSTRAINTS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      String var11 = null;
      boolean var12 = false;
      boolean var13 = false;
      boolean var14 = true;
      int var15 = 0;
      if (var1 != null) {
         ConstraintDescriptor var16 = (ConstraintDescriptor)var1;
         UUID var6 = var16.getUUID();
         var8 = var6.toString();
         var6 = var16.getTableId();
         var9 = var6.toString();
         var10 = var16.getConstraintName();
         int var5 = var16.getConstraintType();
         switch (var5) {
            case 2:
               var7 = "P";
               break;
            case 3:
               var7 = "U";
               break;
            case 4:
               var7 = "C";
            case 5:
            default:
               break;
            case 6:
               var7 = "F";
         }

         var11 = var16.getSchemaDescriptor().getUUID().toString();
         var12 = var16.deferrable();
         var13 = var16.initiallyDeferred();
         var14 = var16.enforced();
         var15 = var16.getReferenceCount();
      }

      ExecRow var4 = this.getExecutionFactory().getValueRow(7);
      var4.setColumn(1, new SQLChar(var8));
      var4.setColumn(2, new SQLChar(var9));
      var4.setColumn(3, new SQLVarchar(var10));
      var4.setColumn(4, new SQLChar(var7));
      var4.setColumn(5, new SQLChar(var11));
      var4.setColumn(6, new SQLChar(this.encodeCharacteristics(var12, var13, var14)));
      var4.setColumn(7, new SQLInteger(var15));
      return var4;
   }

   private String encodeCharacteristics(boolean var1, boolean var2, boolean var3) {
      char var4;
      if (var1) {
         if (var2) {
            if (var3) {
               var4 = 'e';
            } else {
               var4 = 'd';
            }
         } else if (var3) {
            var4 = 'i';
         } else {
            var4 = 'j';
         }
      } else if (var2) {
         var4 = 'E';
      } else if (var3) {
         var4 = 'E';
      } else {
         var4 = 'D';
      }

      return String.valueOf(var4);
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      Object var4 = null;
      TableDescriptor var8 = null;
      byte var9 = -1;
      int[] var10 = null;
      UUID var14 = null;
      boolean var20 = false;
      boolean var21 = false;
      boolean var22 = true;
      SubConstraintDescriptor var26 = (SubConstraintDescriptor)var2;
      DataDescriptorGenerator var7 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var5 = var1.getColumn(1);
      String var24 = var5.getString();
      UUID var11 = this.getUUIDFactory().recreateUUID(var24);
      var5 = var1.getColumn(2);
      String var16 = var5.getString();
      UUID var13 = this.getUUIDFactory().recreateUUID(var16);
      if (var26 != null) {
         var8 = var26.getTableDescriptor();
      }

      if (var8 == null) {
         var8 = var3.getTableDescriptor(var13);
      }

      var5 = var1.getColumn(3);
      String var17 = var5.getString();
      var5 = var1.getColumn(4);
      String var18 = var5.getString();
      boolean var27 = false;
      switch (var18.charAt(0)) {
         case 'C':
            var9 = 4;
            break;
         case 'P':
            var9 = 2;
            var27 = true;
         case 'U':
            if (!var27) {
               var9 = 3;
               var27 = true;
            }
         case 'F':
            if (!var27) {
               var9 = 6;
            }

            ConglomerateDescriptor var6 = var8.getConglomerateDescriptor(((SubKeyConstraintDescriptor)var2).getIndexId());
            if (var6 == null) {
               var8 = var3.getTableDescriptor(var13);
               if (var26 != null) {
                  var26.setTableDescriptor(var8);
               }

               var6 = var8.getConglomerateDescriptor(((SubKeyConstraintDescriptor)var2).getIndexId());
            }

            var10 = var6.getIndexDescriptor().baseColumnPositions();
            var14 = ((SubKeyConstraintDescriptor)var2).getKeyConstraintId();
            var10 = var6.getIndexDescriptor().baseColumnPositions();
      }

      var5 = var1.getColumn(5);
      String var25 = var5.getString();
      UUID var12 = this.getUUIDFactory().recreateUUID(var25);
      SchemaDescriptor var15 = var3.getSchemaDescriptor(var12, (TransactionController)null);
      var5 = var1.getColumn(6);
      String var19 = var5.getString();
      switch (var19.charAt(0)) {
         case 'D':
            var20 = false;
            var21 = false;
            var22 = false;
            break;
         case 'E':
            var20 = false;
            var21 = false;
            var22 = true;
            break;
         case 'd':
            var20 = true;
            var21 = true;
            var22 = false;
            break;
         case 'e':
            var20 = true;
            var21 = true;
            var22 = true;
            break;
         case 'i':
            var20 = true;
            var21 = false;
            var22 = true;
            break;
         case 'j':
            var20 = true;
            var21 = false;
            var22 = false;
      }

      var5 = var1.getColumn(7);
      int var23 = var5.getInt();
      switch (var9) {
         case 2:
            var4 = var7.newPrimaryKeyConstraintDescriptor(var8, var17, var20, var21, var10, var11, ((SubKeyConstraintDescriptor)var2).getIndexId(), var15, var22, var23);
            break;
         case 3:
            var4 = var7.newUniqueConstraintDescriptor(var8, var17, var20, var21, var10, var11, ((SubKeyConstraintDescriptor)var2).getIndexId(), var15, var22, var23);
            break;
         case 4:
            var4 = var7.newCheckConstraintDescriptor(var8, var17, var20, var21, var11, ((SubCheckConstraintDescriptor)var2).getConstraintText(), ((SubCheckConstraintDescriptor)var2).getReferencedColumnsDescriptor(), var15, var22);
         case 5:
         default:
            break;
         case 6:
            var4 = var7.newForeignKeyConstraintDescriptor(var8, var17, var20, var21, var10, var11, ((SubKeyConstraintDescriptor)var2).getIndexId(), var15, var14, var22, ((SubKeyConstraintDescriptor)var2).getRaDeleteRule(), ((SubKeyConstraintDescriptor)var2).getRaUpdateRule());
      }

      return (TupleDescriptor)var4;
   }

   protected UUID getConstraintId(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(1);
      String var3 = var2.getString();
      return this.getUUIDFactory().recreateUUID(var3);
   }

   protected String getConstraintName(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(3);
      String var3 = var2.getString();
      return var3;
   }

   protected UUID getSchemaId(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(5);
      String var3 = var2.getString();
      return this.getUUIDFactory().recreateUUID(var3);
   }

   protected UUID getTableId(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(2);
      String var3 = var2.getString();
      return this.getUUIDFactory().recreateUUID(var3);
   }

   protected int getConstraintType(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(4);
      String var4 = var2.getString();
      byte var3;
      switch (var4.charAt(0)) {
         case 'C' -> var3 = 4;
         case 'F' -> var3 = 6;
         case 'P' -> var3 = 2;
         case 'U' -> var3 = 3;
         default -> var3 = -1;
      }

      return var3;
   }

   public SystemColumn[] buildColumnList() {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("CONSTRAINTID", false), SystemColumnImpl.getUUIDColumn("TABLEID", false), SystemColumnImpl.getIdentifierColumn("CONSTRAINTNAME", false), SystemColumnImpl.getIndicatorColumn("TYPE"), SystemColumnImpl.getUUIDColumn("SCHEMAID", false), SystemColumnImpl.getIndicatorColumn("STATE"), SystemColumnImpl.getColumn("REFERENCECOUNT", 4, false)};
   }
}
