package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.AliasInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

class SYSALIASESRowFactory extends CatalogRowFactory {
   private static final int SYSALIASES_COLUMN_COUNT = 9;
   private static final int SYSALIASES_ALIASID = 1;
   private static final int SYSALIASES_ALIAS = 2;
   private static final int SYSALIASES_SCHEMAID = 3;
   private static final int SYSALIASES_JAVACLASSNAME = 4;
   private static final int SYSALIASES_ALIASTYPE = 5;
   private static final int SYSALIASES_NAMESPACE = 6;
   private static final int SYSALIASES_SYSTEMALIAS = 7;
   public static final int SYSALIASES_ALIASINFO = 8;
   private static final int SYSALIASES_SPECIFIC_NAME = 9;
   protected static final int SYSALIASES_INDEX1_ID = 0;
   protected static final int SYSALIASES_INDEX2_ID = 1;
   protected static final int SYSALIASES_INDEX3_ID = 2;
   private static final boolean[] uniqueness = null;
   private static int[][] indexColumnPositions = new int[][]{{3, 2, 6}, {1}, {3, 9}};
   private static final String[] uuids = new String[]{"c013800d-00d7-ddbd-08ce-000a0a411400", "c013800d-00d7-ddbd-75d4-000a0a411400", "c013800d-00d7-ddbe-b99d-000a0a411400", "c013800d-00d7-ddbe-c4e1-000a0a411400", "c013800d-00d7-ddbe-34ae-000a0a411400"};

   SYSALIASESRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(9, "SYSALIASES", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var4 = null;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      String var9 = null;
      char var10 = 'P';
      char var11 = 'P';
      boolean var12 = false;
      AliasInfo var13 = null;
      if (var1 != null) {
         AliasDescriptor var14 = (AliasDescriptor)var1;
         var7 = var14.getUUID().toString();
         var8 = var14.getDescriptorName();
         var4 = var14.getSchemaUUID().toString();
         var5 = var14.getJavaClassName();
         var10 = var14.getAliasType();
         var11 = var14.getNameSpace();
         var12 = var14.getSystemAlias();
         var13 = var14.getAliasInfo();
         var9 = var14.getSpecificName();
         char[] var15 = new char[]{var10};
         var6 = new String(var15);
      }

      ExecRow var17 = this.getExecutionFactory().getValueRow(9);
      var17.setColumn(1, new SQLChar(var7));
      var17.setColumn(2, new SQLVarchar(var8));
      var17.setColumn(3, new SQLChar(var4));
      var17.setColumn(4, this.dvf.getLongvarcharDataValue(var5));
      var17.setColumn(5, new SQLChar(var6));
      String var18 = new String(new char[]{var11});
      var17.setColumn(6, new SQLChar(var18));
      var17.setColumn(7, new SQLBoolean(var12));
      var17.setColumn(8, new UserType(var13));
      var17.setColumn(9, new SQLVarchar(var9));
      return var17;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      boolean var14 = false;
      Object var15 = null;
      DataValueDescriptor var6 = var1.getColumn(1);
      String var7 = var6.getString();
      UUID var8 = this.getUUIDFactory().recreateUUID(var7);
      var6 = var1.getColumn(2);
      String var9 = var6.getString();
      var6 = var1.getColumn(3);
      UUID var16 = var6.isNull() ? null : this.getUUIDFactory().recreateUUID(var6.getString());
      var6 = var1.getColumn(4);
      String var10 = var6.getString();
      var6 = var1.getColumn(5);
      String var11 = var6.getString();
      char var4 = var11.charAt(0);
      var6 = var1.getColumn(6);
      String var12 = var6.getString();
      char var5 = var12.charAt(0);
      var6 = var1.getColumn(7);
      var14 = var6.getBoolean();
      var6 = var1.getColumn(8);
      AliasInfo var27 = (AliasInfo)var6.getObject();
      var6 = var1.getColumn(9);
      String var17 = var6.getString();
      return new AliasDescriptor(var3, var8, var9, var16, var10, var4, var5, var14, var27, var17);
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("ALIASID", false), SystemColumnImpl.getIdentifierColumn("ALIAS", false), SystemColumnImpl.getUUIDColumn("SCHEMAID", true), SystemColumnImpl.getColumn("JAVACLASSNAME", -1, false, Integer.MAX_VALUE), SystemColumnImpl.getIndicatorColumn("ALIASTYPE"), SystemColumnImpl.getIndicatorColumn("NAMESPACE"), SystemColumnImpl.getColumn("SYSTEMALIAS", 16, false), SystemColumnImpl.getJavaColumn("ALIASINFO", "org.apache.derby.catalog.AliasInfo", true), SystemColumnImpl.getIdentifierColumn("SPECIFICNAME", false)};
   }
}
