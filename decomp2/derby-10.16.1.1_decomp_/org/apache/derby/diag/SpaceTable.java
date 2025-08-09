package org.apache.derby.diag;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.SpaceInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.VTICosting;
import org.apache.derby.vti.VTIEnvironment;
import org.apache.derby.vti.VTITemplate;

public class SpaceTable extends VTITemplate implements VTICosting {
   private ConglomInfo[] conglomTable;
   boolean initialized;
   int currentRow;
   private boolean wasNull;
   private String schemaName;
   private String tableName;
   private SpaceInfo spaceInfo;
   private TransactionController tc;
   private static final ResultColumnDescriptor[] columnInfo = new ResultColumnDescriptor[]{EmbedResultSetMetaData.getResultColumnDescriptor("CONGLOMERATENAME", 12, true, 128), EmbedResultSetMetaData.getResultColumnDescriptor("ISINDEX", 5, false), EmbedResultSetMetaData.getResultColumnDescriptor("NUMALLOCATEDPAGES", -5, false), EmbedResultSetMetaData.getResultColumnDescriptor("NUMFREEPAGES", -5, false), EmbedResultSetMetaData.getResultColumnDescriptor("NUMUNFILLEDPAGES", -5, false), EmbedResultSetMetaData.getResultColumnDescriptor("PAGESIZE", 4, false), EmbedResultSetMetaData.getResultColumnDescriptor("ESTIMSPACESAVING", -5, false), EmbedResultSetMetaData.getResultColumnDescriptor("TABLEID", 1, false, 36)};
   private static final ResultSetMetaData metadata;

   public SpaceTable() {
   }

   public SpaceTable(String var1, String var2) {
      this.schemaName = var1;
      this.tableName = var2;
   }

   public SpaceTable(String var1) {
      this.tableName = var1;
   }

   private void getConglomInfo(LanguageConnectionContext var1) throws StandardException {
      DataDictionary var2 = var1.getDataDictionary();
      if (this.schemaName == null) {
         this.schemaName = var1.getCurrentSchemaName();
      }

      ConglomerateDescriptor[] var3;
      if (this.tableName != null) {
         SchemaDescriptor var4 = var2.getSchemaDescriptor(this.schemaName, this.tc, true);
         TableDescriptor var5 = var2.getTableDescriptor(this.tableName, var4, this.tc);
         if (var5 == null) {
            this.conglomTable = new ConglomInfo[0];
            return;
         }

         var3 = var5.getConglomerateDescriptors();
      } else {
         var3 = var2.getConglomerateDescriptors((UUID)null);
      }

      this.conglomTable = new ConglomInfo[var3.length];

      for(int var6 = 0; var6 < var3.length; ++var6) {
         String var7;
         if (var3[var6].isIndex()) {
            var7 = var3[var6].getConglomerateName();
         } else if (this.tableName != null) {
            var7 = this.tableName;
         } else {
            var7 = var2.getTableDescriptor(var3[var6].getTableID()).getName();
         }

         this.conglomTable[var6] = new ConglomInfo(var3[var6].getTableID().toString(), var3[var6].getConglomerateNumber(), var7, var3[var6].isIndex());
      }

   }

   private void getSpaceInfo(int var1) throws StandardException {
      ConglomerateController var2 = this.tc.openConglomerate(this.conglomTable[var1].getConglomId(), false, 0, 6, 2);
      this.spaceInfo = var2.getSpaceInfo();
      var2.close();
      Object var3 = null;
   }

   public ResultSetMetaData getMetaData() {
      return metadata;
   }

   public boolean next() throws SQLException {
      try {
         if (!this.initialized) {
            LanguageConnectionContext var1 = ConnectionUtil.getCurrentLCC();
            this.tc = var1.getTransactionExecute();
            this.getConglomInfo(var1);
            this.initialized = true;
            this.currentRow = -1;
         }

         if (this.conglomTable == null) {
            return false;
         } else {
            ++this.currentRow;
            if (this.currentRow >= this.conglomTable.length) {
               return false;
            } else {
               this.spaceInfo = null;
               this.getSpaceInfo(this.currentRow);
               return true;
            }
         }
      } catch (StandardException var2) {
         throw PublicAPI.wrapStandardException(var2);
      }
   }

   public void close() {
      this.conglomTable = null;
      this.spaceInfo = null;
      this.tc = null;
   }

   public String getString(int var1) {
      ConglomInfo var2 = this.conglomTable[this.currentRow];
      String var3 = null;
      switch (var1) {
         case 1 -> var3 = var2.getConglomName();
         case 8 -> var3 = var2.getTableID();
      }

      this.wasNull = var3 == null;
      return var3;
   }

   public long getLong(int var1) {
      ConglomInfo var10000 = this.conglomTable[this.currentRow];
      long var2;
      switch (var1) {
         case 3:
            var2 = this.spaceInfo.getNumAllocatedPages();
            break;
         case 4:
            var2 = this.spaceInfo.getNumFreePages();
            break;
         case 5:
            var2 = this.spaceInfo.getNumUnfilledPages();
            break;
         case 6:
         default:
            var2 = -1L;
            break;
         case 7:
            int var5 = this.spaceInfo.getPageSize();
            var2 = this.spaceInfo.getNumFreePages() * (long)var5;
      }

      this.wasNull = false;
      return var2;
   }

   public short getShort(int var1) {
      ConglomInfo var2 = this.conglomTable[this.currentRow];
      this.wasNull = false;
      return (short)(var2.getIsIndex() ? 1 : 0);
   }

   public int getInt(int var1) {
      return this.spaceInfo.getPageSize();
   }

   public boolean wasNull() {
      return this.wasNull;
   }

   public double getEstimatedRowCount(VTIEnvironment var1) {
      return (double)10000.0F;
   }

   public double getEstimatedCostPerInstantiation(VTIEnvironment var1) {
      return (double)100000.0F;
   }

   public boolean supportsMultipleInstantiations(VTIEnvironment var1) {
      return true;
   }

   static {
      metadata = new EmbedResultSetMetaData(columnInfo);
   }
}
