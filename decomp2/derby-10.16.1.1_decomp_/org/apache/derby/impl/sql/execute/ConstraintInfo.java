package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.ConsInfo;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class ConstraintInfo implements ConsInfo {
   private String tableName;
   private SchemaDescriptor tableSd;
   private UUID tableSchemaId;
   private String[] columnNames;
   private int raDeleteRule;
   private int raUpdateRule;

   public ConstraintInfo() {
   }

   public ConstraintInfo(String var1, SchemaDescriptor var2, String[] var3, int var4, int var5) {
      this.tableName = var1;
      this.tableSd = var2;
      this.columnNames = (String[])ArrayUtil.copy(var3);
      this.raDeleteRule = var4;
      this.raUpdateRule = var5;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.tableName);
      if (this.tableSd == null) {
         var1.writeBoolean(false);
      } else {
         var1.writeBoolean(true);
         var1.writeObject(this.tableSd.getUUID());
      }

      if (this.columnNames == null) {
         var1.writeBoolean(false);
      } else {
         var1.writeBoolean(true);
         ArrayUtil.writeArrayLength(var1, this.columnNames);
         ArrayUtil.writeArrayItems(var1, this.columnNames);
      }

      var1.writeInt(this.raDeleteRule);
      var1.writeInt(this.raUpdateRule);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.tableName = (String)var1.readObject();
      if (var1.readBoolean()) {
         this.tableSchemaId = (UUID)var1.readObject();
      }

      if (var1.readBoolean()) {
         this.columnNames = new String[ArrayUtil.readArrayLength(var1)];
         ArrayUtil.readArrayItems(var1, this.columnNames);
      }

      this.raDeleteRule = var1.readInt();
      this.raUpdateRule = var1.readInt();
   }

   public int getTypeFormatId() {
      return 278;
   }

   public String toString() {
      return "";
   }

   public SchemaDescriptor getReferencedTableSchemaDescriptor(DataDictionary var1) throws StandardException {
      return this.tableSd != null ? this.tableSd : var1.getSchemaDescriptor(this.tableSchemaId, (TransactionController)null);
   }

   public TableDescriptor getReferencedTableDescriptor(DataDictionary var1) throws StandardException {
      return this.tableName == null ? null : var1.getTableDescriptor(this.tableName, this.getReferencedTableSchemaDescriptor(var1), (TransactionController)null);
   }

   public String[] getReferencedColumnNames() {
      return (String[])ArrayUtil.copy(this.columnNames);
   }

   public String getReferencedTableName() {
      return this.tableName;
   }

   public int getReferentialActionUpdateRule() {
      return this.raUpdateRule;
   }

   public int getReferentialActionDeleteRule() {
      return this.raDeleteRule;
   }
}
