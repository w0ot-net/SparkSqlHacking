package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;

public final class ColumnDescriptor extends TupleDescriptor {
   private DefaultInfo columnDefaultInfo;
   private TableDescriptor table;
   private String columnName;
   private int columnPosition;
   private DataTypeDescriptor columnType;
   private DataValueDescriptor columnDefault;
   private UUID uuid;
   private UUID defaultUUID;
   private long autoincStart;
   private long autoincInc;
   private long autoincValue;
   private boolean autoincCycle;
   long autoinc_create_or_modify_Start_Increment;

   public ColumnDescriptor(String var1, int var2, DataTypeDescriptor var3, DataValueDescriptor var4, DefaultInfo var5, TableDescriptor var6, UUID var7, long var8, long var10, long var12, boolean var14) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var10, var14);
      this.autoinc_create_or_modify_Start_Increment = var12;
   }

   public ColumnDescriptor(String var1, int var2, DataTypeDescriptor var3, DataValueDescriptor var4, DefaultInfo var5, TableDescriptor var6, UUID var7, long var8, long var10, boolean var12) {
      this.autoinc_create_or_modify_Start_Increment = -1L;
      this.columnName = var1;
      this.columnPosition = var2;
      this.columnType = var3;
      this.columnDefault = var4;
      this.columnDefaultInfo = var5;
      this.defaultUUID = var7;
      if (var6 != null) {
         this.table = var6;
         this.uuid = var6.getUUID();
      }

      assertAutoinc(var10 != 0L, var10, var5);
      this.autoincStart = var8;
      this.autoincValue = var8;
      this.autoincInc = var10;
      this.autoincCycle = var12;
   }

   public ColumnDescriptor(String var1, int var2, DataTypeDescriptor var3, DataValueDescriptor var4, DefaultInfo var5, UUID var6, UUID var7, long var8, long var10, long var12, boolean var14) {
      this.autoinc_create_or_modify_Start_Increment = -1L;
      this.columnName = var1;
      this.columnPosition = var2;
      this.columnType = var3;
      this.columnDefault = var4;
      this.columnDefaultInfo = var5;
      this.uuid = var6;
      this.defaultUUID = var7;
      assertAutoinc(var10 != 0L, var10, var5);
      this.autoincStart = var8;
      this.autoincValue = var12;
      this.autoincInc = var10;
      this.autoincCycle = var14;
   }

   public UUID getReferencingUUID() {
      return this.uuid;
   }

   public TableDescriptor getTableDescriptor() {
      return this.table;
   }

   public String getColumnName() {
      return this.columnName;
   }

   public void setColumnName(String var1) {
      this.columnName = var1;
   }

   public void setTableDescriptor(TableDescriptor var1) {
      this.table = var1;
   }

   public int getPosition() {
      return this.columnPosition;
   }

   public DataTypeDescriptor getType() {
      return this.columnType;
   }

   public boolean hasNonNullDefault() {
      if (this.columnDefault != null && !this.columnDefault.isNull()) {
         return true;
      } else {
         return this.columnDefaultInfo != null;
      }
   }

   public DataValueDescriptor getDefaultValue() {
      return this.columnDefault;
   }

   public DefaultInfo getDefaultInfo() {
      return this.columnDefaultInfo;
   }

   public UUID getDefaultUUID() {
      return this.defaultUUID;
   }

   public DefaultDescriptor getDefaultDescriptor(DataDictionary var1) {
      DefaultDescriptor var2 = null;
      if (this.defaultUUID != null) {
         var2 = new DefaultDescriptor(var1, this.defaultUUID, this.uuid, this.columnPosition);
      }

      return var2;
   }

   public boolean isAutoincrement() {
      return this.autoincInc != 0L;
   }

   public boolean updatableByCursor() {
      return false;
   }

   public boolean hasGenerationClause() {
      return this.columnDefaultInfo == null ? false : this.columnDefaultInfo.isGeneratedColumn();
   }

   public boolean isAutoincAlways() {
      return this.columnDefaultInfo == null && this.isAutoincrement();
   }

   public long getAutoincStart() {
      return this.autoincStart;
   }

   public boolean getAutoincCycle() {
      return this.autoincCycle;
   }

   public long getAutoincInc() {
      return this.autoincInc;
   }

   public long getAutoincValue() {
      return this.autoincValue;
   }

   public long getAutoinc_create_or_modify_Start_Increment() {
      return this.autoinc_create_or_modify_Start_Increment;
   }

   public void setAutoinc_create_or_modify_Start_Increment(int var1) {
      this.autoinc_create_or_modify_Start_Increment = (long)var1;
   }

   public void setPosition(int var1) {
      this.columnPosition = var1;
   }

   public String toString() {
      return "";
   }

   public String getDescriptorName() {
      return this.columnName;
   }

   public String getDescriptorType() {
      return "Column";
   }

   private static void assertAutoinc(boolean var0, long var1, DefaultInfo var3) {
   }
}
