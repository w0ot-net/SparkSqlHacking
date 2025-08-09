package org.apache.hive.service.cli;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Schema;
import org.apache.hadoop.hive.serde2.thrift.Type;
import org.apache.hive.service.rpc.thrift.TColumnDesc;
import org.apache.hive.service.rpc.thrift.TTableSchema;

public class TableSchema {
   private final List columns;

   public TableSchema() {
      this.columns = new ArrayList();
   }

   public TableSchema(int numColumns) {
      this.columns = new ArrayList();
   }

   public TableSchema(TTableSchema tTableSchema) {
      this.columns = new ArrayList();

      for(TColumnDesc tColumnDesc : tTableSchema.getColumns()) {
         this.columns.add(new ColumnDescriptor(tColumnDesc));
      }

   }

   public TableSchema(List fieldSchemas) {
      this.columns = new ArrayList();
      int pos = 1;

      for(FieldSchema field : fieldSchemas) {
         this.columns.add(new ColumnDescriptor(field.getName(), field.getComment(), new TypeDescriptor(field.getType()), pos++));
      }

   }

   public TableSchema(Schema schema) {
      this(schema.getFieldSchemas());
   }

   public List getColumnDescriptors() {
      return new ArrayList(this.columns);
   }

   public ColumnDescriptor getColumnDescriptorAt(int pos) {
      return (ColumnDescriptor)this.columns.get(pos);
   }

   public int getSize() {
      return this.columns.size();
   }

   public void clear() {
      this.columns.clear();
   }

   public TTableSchema toTTableSchema() {
      TTableSchema tTableSchema = new TTableSchema();

      for(ColumnDescriptor col : this.columns) {
         tTableSchema.addToColumns(col.toTColumnDesc());
      }

      return tTableSchema;
   }

   public TypeDescriptor[] toTypeDescriptors() {
      TypeDescriptor[] types = new TypeDescriptor[this.columns.size()];

      for(int i = 0; i < types.length; ++i) {
         types[i] = ((ColumnDescriptor)this.columns.get(i)).getTypeDescriptor();
      }

      return types;
   }

   public TableSchema addPrimitiveColumn(String columnName, Type columnType, String columnComment) {
      this.columns.add(ColumnDescriptor.newPrimitiveColumnDescriptor(columnName, columnComment, columnType, this.columns.size() + 1));
      return this;
   }

   public TableSchema addStringColumn(String columnName, String columnComment) {
      this.columns.add(ColumnDescriptor.newPrimitiveColumnDescriptor(columnName, columnComment, Type.STRING_TYPE, this.columns.size() + 1));
      return this;
   }
}
