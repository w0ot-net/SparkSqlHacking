package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DependencyDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

public class SYSDEPENDSRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSDEPENDS";
   protected static final int SYSDEPENDS_COLUMN_COUNT = 4;
   protected static final int SYSDEPENDS_DEPENDENTID = 1;
   protected static final int SYSDEPENDS_DEPENDENTTYPE = 2;
   protected static final int SYSDEPENDS_PROVIDERID = 3;
   protected static final int SYSDEPENDS_PROVIDERTYPE = 4;
   protected static final int SYSDEPENDS_INDEX1_ID = 0;
   protected static final int SYSDEPENDS_INDEX2_ID = 1;
   private static final boolean[] uniqueness = new boolean[]{false, false};
   private static final int[][] indexColumnPositions = new int[][]{{1}, {3}};
   private static final String[] uuids = new String[]{"8000003e-00d0-fd77-3ed8-000a0a0b1900", "80000043-00d0-fd77-3ed8-000a0a0b1900", "80000040-00d0-fd77-3ed8-000a0a0b1900", "80000042-00d0-fd77-3ed8-000a0a0b1900"};

   SYSDEPENDSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(4, "SYSDEPENDS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var5 = null;
      DependableFinder var6 = null;
      String var7 = null;
      DependableFinder var8 = null;
      if (var1 != null) {
         DependencyDescriptor var9 = (DependencyDescriptor)var1;
         var5 = var9.getUUID().toString();
         var6 = var9.getDependentFinder();
         if (var6 == null) {
            throw StandardException.newException("XD004.S", new Object[0]);
         }

         var7 = var9.getProviderID().toString();
         var8 = var9.getProviderFinder();
         if (var8 == null) {
            throw StandardException.newException("XD004.S", new Object[0]);
         }
      }

      ExecRow var4 = this.getExecutionFactory().getValueRow(4);
      var4.setColumn(1, new SQLChar(var5));
      var4.setColumn(2, new UserType(var6));
      var4.setColumn(3, new SQLChar(var7));
      var4.setColumn(4, new UserType(var8));
      return var4;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      Object var4 = null;
      DataValueDescriptor var5 = var1.getColumn(1);
      String var6 = var5.getString();
      UUID var7 = this.getUUIDFactory().recreateUUID(var6);
      var5 = var1.getColumn(2);
      DependableFinder var8 = (DependableFinder)var5.getObject();
      var5 = var1.getColumn(3);
      String var9 = var5.getString();
      UUID var10 = this.getUUIDFactory().recreateUUID(var9);
      var5 = var1.getColumn(4);
      DependableFinder var11 = (DependableFinder)var5.getObject();
      return new DependencyDescriptor(var7, var8, var10, var11);
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("DEPENDENTID", false), SystemColumnImpl.getJavaColumn("DEPENDENTFINDER", "org.apache.derby.catalog.DependableFinder", false), SystemColumnImpl.getUUIDColumn("PROVIDERID", false), SystemColumnImpl.getJavaColumn("PROVIDERFINDER", "org.apache.derby.catalog.DependableFinder", false)};
   }
}
