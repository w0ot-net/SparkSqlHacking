package org.datanucleus.metadata;

import org.datanucleus.util.StringUtils;

public class TableGeneratorMetaData extends MetaData {
   private static final long serialVersionUID = 6202716458802237130L;
   protected final String name;
   protected String tableName;
   protected String catalogName;
   protected String schemaName;
   protected String pkColumnName;
   protected String valueColumnName;
   protected String pkColumnValue;
   protected long initialValue = 0L;
   protected long allocationSize = 50L;

   TableGeneratorMetaData(String name) {
      if (StringUtils.isWhitespace(name)) {
         throw new InvalidMetaDataException("044155", new Object[0]);
      } else {
         this.name = name;
      }
   }

   public String getFullyQualifiedName() {
      PackageMetaData pmd = (PackageMetaData)this.getParent();
      return pmd.getName() + "." + this.name;
   }

   public String getName() {
      return this.name;
   }

   public String getTableName() {
      return this.tableName;
   }

   public TableGeneratorMetaData setTableName(String tableName) {
      this.tableName = StringUtils.isWhitespace(tableName) ? null : tableName;
      return this;
   }

   public String getCatalogName() {
      return this.catalogName;
   }

   public TableGeneratorMetaData setCatalogName(String catalogName) {
      this.catalogName = StringUtils.isWhitespace(catalogName) ? null : catalogName;
      return this;
   }

   public String getSchemaName() {
      return this.schemaName;
   }

   public TableGeneratorMetaData setSchemaName(String schemaName) {
      this.schemaName = StringUtils.isWhitespace(schemaName) ? null : schemaName;
      return this;
   }

   public String getPKColumnName() {
      return this.pkColumnName;
   }

   public TableGeneratorMetaData setPKColumnName(String pkColumnName) {
      this.pkColumnName = StringUtils.isWhitespace(pkColumnName) ? null : pkColumnName;
      return this;
   }

   public String getValueColumnName() {
      return this.valueColumnName;
   }

   public TableGeneratorMetaData setValueColumnName(String valueColumnName) {
      this.valueColumnName = StringUtils.isWhitespace(valueColumnName) ? null : valueColumnName;
      return this;
   }

   public String getPKColumnValue() {
      return this.pkColumnValue;
   }

   public TableGeneratorMetaData setPKColumnValue(String pkColumnValue) {
      this.pkColumnValue = StringUtils.isWhitespace(pkColumnValue) ? null : pkColumnValue;
      return this;
   }

   public long getInitialValue() {
      return this.initialValue;
   }

   public TableGeneratorMetaData setInitialValue(long initialValue) {
      this.initialValue = initialValue;
      return this;
   }

   public TableGeneratorMetaData setInitialValue(String initialValue) {
      if (!StringUtils.isWhitespace(initialValue)) {
         try {
            this.initialValue = (long)Integer.parseInt(initialValue);
         } catch (NumberFormatException var3) {
         }
      }

      return this;
   }

   public long getAllocationSize() {
      return this.allocationSize;
   }

   public TableGeneratorMetaData setAllocationSize(long allocationSize) {
      this.allocationSize = allocationSize;
      return this;
   }

   public TableGeneratorMetaData setAllocationSize(String allocationSize) {
      if (!StringUtils.isWhitespace(allocationSize)) {
         try {
            this.allocationSize = (long)Integer.parseInt(allocationSize);
         } catch (NumberFormatException var3) {
         }
      }

      return this;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<table-generator name=\"" + this.name + "\"\n");
      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix + "</table-generator>\n");
      return sb.toString();
   }
}
