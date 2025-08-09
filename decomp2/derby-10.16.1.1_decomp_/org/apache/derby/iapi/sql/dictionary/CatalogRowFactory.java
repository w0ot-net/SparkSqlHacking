package org.apache.derby.iapi.sql.dictionary;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public abstract class CatalogRowFactory {
   protected String[] indexNames;
   protected int[][] indexColumnPositions;
   protected boolean[] indexUniqueness;
   protected UUID tableUUID;
   protected UUID heapUUID;
   protected UUID[] indexUUID;
   protected DataValueFactory dvf;
   private final ExecutionFactory ef;
   private UUIDFactory uuidf;
   private int indexCount;
   private int columnCount;
   private String catalogName;

   public CatalogRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      this.uuidf = var1;
      this.dvf = var3;
      this.ef = var2;
   }

   public ExecutionFactory getExecutionFactory() {
      return this.ef;
   }

   public UUIDFactory getUUIDFactory() {
      return this.uuidf;
   }

   public UUID getCanonicalTableUUID() {
      return this.tableUUID;
   }

   public UUID getCanonicalHeapUUID() {
      return this.heapUUID;
   }

   public UUID getCanonicalIndexUUID(int var1) {
      return this.indexUUID[var1];
   }

   public int getIndexColumnCount(int var1) {
      return this.indexColumnPositions[var1].length;
   }

   public String getCanonicalHeapName() {
      return this.catalogName + "_HEAP";
   }

   public String getIndexName(int var1) {
      return this.indexNames[var1];
   }

   public boolean isIndexUnique(int var1) {
      return this.indexUniqueness != null ? this.indexUniqueness[var1] : true;
   }

   public DataValueFactory getDataValueFactory() {
      return this.dvf;
   }

   public String generateIndexName(int var1) {
      ++var1;
      return this.catalogName + "_INDEX" + var1;
   }

   public int getNumIndexes() {
      return this.indexCount;
   }

   public String getCatalogName() {
      return this.catalogName;
   }

   public void initInfo(int var1, String var2, int[][] var3, boolean[] var4, String[] var5) {
      this.indexCount = var3 != null ? var3.length : 0;
      this.catalogName = var2;
      this.columnCount = var1;
      UUIDFactory var6 = this.getUUIDFactory();
      this.tableUUID = var6.recreateUUID(var5[0]);
      this.heapUUID = var6.recreateUUID(var5[1]);
      if (this.indexCount > 0) {
         this.indexNames = new String[this.indexCount];
         this.indexUUID = new UUID[this.indexCount];

         for(int var7 = 0; var7 < this.indexCount; ++var7) {
            this.indexNames[var7] = this.generateIndexName(var7);
            this.indexUUID[var7] = var6.recreateUUID(var5[var7 + 2]);
         }

         this.indexColumnPositions = ArrayUtil.copy2(var3);
         this.indexUniqueness = ArrayUtil.copy(var4);
      }

   }

   public Properties getCreateHeapProperties() {
      Properties var1 = new Properties();
      var1.put("derby.storage.pageSize", "1024");
      var1.put("derby.storage.pageReservedSpace", "0");
      var1.put("derby.storage.minimumRecordSize", "1");
      return var1;
   }

   public Properties getCreateIndexProperties(int var1) {
      Properties var2 = new Properties();
      var2.put("derby.storage.pageSize", "1024");
      return var2;
   }

   public int getPrimaryKeyIndexNumber() {
      return 0;
   }

   public int getHeapColumnCount() throws StandardException {
      return this.columnCount;
   }

   public ExecRow makeEmptyRow() throws StandardException {
      return this.makeRow((TupleDescriptor)null, (TupleDescriptor)null);
   }

   public ExecRow makeEmptyRowForCurrentVersion() throws StandardException {
      return this.makeEmptyRow();
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      return null;
   }

   public abstract TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException;

   public abstract SystemColumn[] buildColumnList() throws StandardException;

   public int[] getIndexColumnPositions(int var1) {
      return this.indexColumnPositions[var1];
   }
}
