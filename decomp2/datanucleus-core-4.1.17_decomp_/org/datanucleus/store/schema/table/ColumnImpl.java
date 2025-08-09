package org.datanucleus.store.schema.table;

import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.schema.naming.ColumnType;

public class ColumnImpl implements Column {
   Table table;
   ColumnType columnType;
   String identifier;
   boolean primaryKey;
   boolean nullable;
   boolean defaultable;
   Object defaultValue;
   boolean unique;
   JdbcType jdbcType;
   String typeName;
   int position;
   MemberColumnMapping mapping;
   ColumnMetaData colmd;
   boolean nested;

   public ColumnImpl(Table tbl, String identifier, ColumnType colType) {
      this.columnType = ColumnType.COLUMN;
      this.primaryKey = false;
      this.nullable = false;
      this.defaultable = false;
      this.defaultValue = null;
      this.unique = false;
      this.position = -1;
      this.mapping = null;
      this.colmd = null;
      this.nested = false;
      this.table = tbl;
      this.identifier = identifier;
      this.columnType = colType;
   }

   public boolean isNested() {
      return this.nested;
   }

   public void setNested(boolean nested) {
      this.nested = nested;
   }

   public Table getTable() {
      return this.table;
   }

   public MemberColumnMapping getMemberColumnMapping() {
      return this.mapping;
   }

   public void setMemberColumnMapping(MemberColumnMapping mapping) {
      this.mapping = mapping;
   }

   public String getName() {
      return this.identifier;
   }

   public boolean isPrimaryKey() {
      return this.primaryKey;
   }

   public Column setPrimaryKey() {
      this.primaryKey = true;
      return this;
   }

   public Column setNullable(boolean flag) {
      this.nullable = flag;
      return this;
   }

   public boolean isNullable() {
      return this.nullable;
   }

   public Column setDefaultable(Object defaultValue) {
      this.defaultable = true;
      this.defaultValue = defaultValue;
      return this;
   }

   public boolean isDefaultable() {
      return this.defaultable;
   }

   public Object getDefaultValue() {
      return this.defaultValue;
   }

   public Column setUnique(boolean flag) {
      this.unique = flag;
      return this;
   }

   public boolean isUnique() {
      return this.unique;
   }

   public ColumnType getColumnType() {
      return this.columnType;
   }

   public Column setJdbcType(JdbcType type) {
      this.jdbcType = type;
      return this;
   }

   public JdbcType getJdbcType() {
      return this.jdbcType;
   }

   public Column setTypeName(String type) {
      this.typeName = type;
      return this;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public Column setPosition(int pos) {
      this.position = pos;
      return this;
   }

   public int getPosition() {
      return this.position;
   }

   public Column setColumnMetaData(ColumnMetaData md) {
      this.colmd = md;
      if (md != null && md.getDefaultValue() != null) {
         this.setDefaultable(md.getDefaultValue());
      }

      return this;
   }

   public ColumnMetaData getColumnMetaData() {
      return this.colmd;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append("Column: ").append(this.identifier);
      if (this.mapping != null) {
         str.append(" member=").append(this.mapping.getMemberMetaData().getFullFieldName());
      }

      str.append(this.primaryKey ? " (PK)" : "");
      str.append(this.position >= 0 ? " [" + this.position + "]" : "");
      if (this.nested) {
         str.append(" [NESTED]");
      }

      return str.toString();
   }
}
