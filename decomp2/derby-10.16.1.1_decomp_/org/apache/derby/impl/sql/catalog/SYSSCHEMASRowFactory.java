package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSSCHEMASRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSSCHEMAS";
   public static final int SYSSCHEMAS_COLUMN_COUNT = 3;
   public static final int SYSSCHEMAS_SCHEMAID = 1;
   public static final int SYSSCHEMAS_SCHEMANAME = 2;
   public static final int SYSSCHEMAS_SCHEMAAID = 3;
   protected static final int SYSSCHEMAS_INDEX1_ID = 0;
   protected static final int SYSSCHEMAS_INDEX2_ID = 1;
   private static final int[][] indexColumnPositions = new int[][]{{2}, {1}};
   private static final boolean[] uniqueness = null;
   private static final String[] uuids = new String[]{"80000022-00d0-fd77-3ed8-000a0a0b1900", "8000002a-00d0-fd77-3ed8-000a0a0b1900", "80000024-00d0-fd77-3ed8-000a0a0b1900", "80000026-00d0-fd77-3ed8-000a0a0b1900"};

   SYSSCHEMASRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(3, "SYSSCHEMAS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var6 = null;
      Object var7 = null;
      String var8 = null;
      String var9 = null;
      if (var1 != null) {
         SchemaDescriptor var10 = (SchemaDescriptor)var1;
         var6 = var10.getSchemaName();
         UUID var11 = var10.getUUID();
         if (var11 == null) {
            var11 = this.getUUIDFactory().createUUID();
            var10.setUUID(var11);
         }

         var8 = var11.toString();
         var9 = var10.getAuthorizationId();
      }

      ExecRow var4 = this.getExecutionFactory().getValueRow(3);
      var4.setColumn(1, new SQLChar(var8));
      var4.setColumn(2, new SQLVarchar(var6));
      var4.setColumn(3, new SQLVarchar(var9));
      return var4;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      DataDescriptorGenerator var10 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var4 = var1.getColumn(1);
      String var9 = var4.getString();
      UUID var7 = this.getUUIDFactory().recreateUUID(var9);
      var4 = var1.getColumn(2);
      String var6 = var4.getString();
      var4 = var1.getColumn(3);
      String var8 = var4.getString();
      SchemaDescriptor var5 = var10.newSchemaDescriptor(var6, var8, var7);
      return var5;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("SCHEMAID", false), SystemColumnImpl.getIdentifierColumn("SCHEMANAME", false), SystemColumnImpl.getIdentifierColumn("AUTHORIZATIONID", false)};
   }
}
