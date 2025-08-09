package org.apache.derby.impl.sql;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.io.FormatableHashtable;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;

public final class GenericColumnDescriptor implements ResultColumnDescriptor, Formatable {
   private String name;
   private String schemaName;
   private String tableName;
   private int columnPos;
   private DataTypeDescriptor type;
   private boolean isAutoincrement;
   private boolean updatableByCursor;
   private boolean hasGenerationClause;

   public GenericColumnDescriptor() {
   }

   public GenericColumnDescriptor(String var1, DataTypeDescriptor var2) {
      this.name = var1;
      this.type = var2;
   }

   public GenericColumnDescriptor(ResultColumnDescriptor var1) {
      this.name = var1.getName();
      this.tableName = var1.getSourceTableName();
      this.schemaName = var1.getSourceSchemaName();
      this.columnPos = var1.getColumnPosition();
      this.type = var1.getType();
      this.isAutoincrement = var1.isAutoincrement();
      this.updatableByCursor = var1.updatableByCursor();
      this.hasGenerationClause = var1.hasGenerationClause();
   }

   public DataTypeDescriptor getType() {
      return this.type;
   }

   public String getName() {
      return this.name;
   }

   public String getSourceSchemaName() {
      return this.schemaName;
   }

   public String getSourceTableName() {
      return this.tableName;
   }

   public int getColumnPosition() {
      return this.columnPos;
   }

   public boolean isAutoincrement() {
      return this.isAutoincrement;
   }

   public boolean updatableByCursor() {
      return this.updatableByCursor;
   }

   public boolean hasGenerationClause() {
      return this.hasGenerationClause;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      FormatableHashtable var2 = new FormatableHashtable();
      var2.put("name", this.name);
      var2.put("tableName", this.tableName);
      var2.put("schemaName", this.schemaName);
      var2.putInt("columnPos", this.columnPos);
      var2.put("type", this.type);
      var2.putBoolean("isAutoincrement", this.isAutoincrement);
      var2.putBoolean("updatableByCursor", this.updatableByCursor);
      var1.writeObject(var2);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      FormatableHashtable var2 = (FormatableHashtable)var1.readObject();
      this.name = (String)var2.get("name");
      this.tableName = (String)var2.get("tableName");
      this.schemaName = (String)var2.get("schemaName");
      this.columnPos = var2.getInt("columnPos");
      this.type = this.getStoredDataTypeDescriptor(var2.get("type"));
      this.isAutoincrement = var2.getBoolean("isAutoincrement");
      this.updatableByCursor = var2.getBoolean("updatableByCursor");
   }

   public int getTypeFormatId() {
      return 383;
   }

   public String toString() {
      return "";
   }

   private DataTypeDescriptor getStoredDataTypeDescriptor(Object var1) {
      return var1 instanceof DataTypeDescriptor ? (DataTypeDescriptor)var1 : DataTypeDescriptor.getType(RoutineAliasInfo.getStoredType(var1));
   }
}
