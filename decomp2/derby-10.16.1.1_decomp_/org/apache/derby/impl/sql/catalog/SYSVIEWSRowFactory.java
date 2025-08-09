package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.dictionary.ViewDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSVIEWSRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSVIEWS";
   protected static final int SYSVIEWS_COLUMN_COUNT = 4;
   protected static final int SYSVIEWS_TABLEID = 1;
   protected static final int SYSVIEWS_VIEWDEFINITION = 2;
   protected static final int SYSVIEWS_CHECKOPTION = 3;
   protected static final int SYSVIEWS_COMPILATION_SCHEMAID = 4;
   protected static final int SYSVIEWS_TABLEID_WIDTH = 36;
   protected static final int SYSVIEWS_INDEX1_ID = 0;
   private static final int[][] indexColumnPositions = new int[][]{{1}};
   private static final boolean[] uniqueness = null;
   private static final String[] uuids = new String[]{"8000004d-00d0-fd77-3ed8-000a0a0b1900", "80000050-00d0-fd77-3ed8-000a0a0b1900", "8000004f-00d0-fd77-3ed8-000a0a0b1900"};

   SYSVIEWSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(4, "SYSVIEWS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      if (var1 != null) {
         ViewDescriptor var11 = (ViewDescriptor)var1;
         UUID var10 = var11.getUUID();
         if (var10 == null) {
            var10 = this.getUUIDFactory().createUUID();
            var11.setUUID(var10);
         }

         var5 = var10.toString();
         var7 = var11.getViewText();
         int var9 = var11.getCheckOptionType();
         var8 = "N";
         UUID var12 = var11.getCompSchemaId();
         var6 = var12 == null ? null : var12.toString();
      }

      ExecRow var4 = this.getExecutionFactory().getValueRow(4);
      var4.setColumn(1, new SQLChar(var5));
      var4.setColumn(2, this.dvf.getLongvarcharDataValue(var7));
      var4.setColumn(3, new SQLChar(var8));
      var4.setColumn(4, new SQLChar(var6));
      return var4;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      TupleDescriptor var4 = null;
      UUID var13 = null;
      DataDescriptorGenerator var6 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var5 = var1.getColumn(1);
      String var9 = var5.getString();
      UUID var12 = this.getUUIDFactory().recreateUUID(var9);
      var5 = var1.getColumn(2);
      String var11 = var5.getString();
      var5 = var1.getColumn(3);
      String var8 = var5.getString();
      byte var7 = 0;
      var5 = var1.getColumn(4);
      String var10 = var5.getString();
      if (var10 != null) {
         var13 = this.getUUIDFactory().recreateUUID(var10);
      }

      var4 = var6.newViewDescriptor(var12, (String)null, var11, var7, var13);
      return var4;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("TABLEID", false), SystemColumnImpl.getColumn("VIEWDEFINITION", -1, false, 32700), SystemColumnImpl.getIndicatorColumn("CHECKOPTION"), SystemColumnImpl.getUUIDColumn("COMPILATIONSCHEMAID", true)};
   }
}
