package org.datanucleus.metadata;

import org.datanucleus.util.StringUtils;

public class IdentityMetaData extends MetaData {
   private static final long serialVersionUID = 4740941674001139996L;
   protected String columnName;
   protected ColumnMetaData columnMetaData;
   protected IdentityStrategy strategy;
   protected String sequence;
   protected String valueGeneratorName;

   public IdentityMetaData() {
      this.strategy = IdentityStrategy.NATIVE;
   }

   public ColumnMetaData getColumnMetaData() {
      return this.columnMetaData;
   }

   public void setColumnMetaData(ColumnMetaData columnMetaData) {
      this.columnMetaData = columnMetaData;
      this.columnMetaData.parent = this;
   }

   public ColumnMetaData newColumnMetaData() {
      ColumnMetaData colmd = new ColumnMetaData();
      this.setColumnMetaData(colmd);
      return colmd;
   }

   public String getColumnName() {
      return this.columnName;
   }

   public IdentityMetaData setColumnName(String columnName) {
      if (!StringUtils.isWhitespace(columnName)) {
         this.columnName = columnName;
         if (this.columnMetaData == null) {
            this.columnMetaData = new ColumnMetaData();
            this.columnMetaData.setName(columnName);
            this.columnMetaData.parent = this;
         } else {
            this.columnMetaData.setName(columnName);
         }
      } else {
         this.columnName = null;
      }

      return this;
   }

   public IdentityStrategy getValueStrategy() {
      return this.strategy;
   }

   public IdentityMetaData setValueStrategy(IdentityStrategy strategy) {
      this.strategy = strategy;
      return this;
   }

   public String getSequence() {
      return this.sequence;
   }

   public IdentityMetaData setSequence(String sequence) {
      this.sequence = StringUtils.isWhitespace(sequence) ? null : sequence;
      if (this.sequence != null && this.strategy == null) {
         this.strategy = IdentityStrategy.SEQUENCE;
      }

      return this;
   }

   public String getValueGeneratorName() {
      return this.valueGeneratorName;
   }

   public IdentityMetaData setValueGeneratorName(String generator) {
      this.valueGeneratorName = StringUtils.isWhitespace(generator) ? null : generator;
      return this;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      if (this.strategy != null) {
         sb.append(prefix).append("<datastore-identity strategy=\"" + this.strategy + "\"");
      } else {
         sb.append(prefix).append("<datastore-identity");
      }

      if (this.columnName != null) {
         sb.append("\n").append(prefix).append("        column=\"" + this.columnName + "\"");
      }

      if (this.sequence != null) {
         sb.append("\n").append(prefix).append("        sequence=\"" + this.sequence + "\"");
      }

      if (this.columnMetaData == null && this.getNoOfExtensions() <= 0) {
         sb.append("/>\n");
      } else {
         sb.append(">\n");
         if (this.columnMetaData != null) {
            sb.append(this.columnMetaData.toString(prefix + indent, indent));
         }

         sb.append(super.toString(prefix + indent, indent));
         sb.append(prefix).append("</datastore-identity>\n");
      }

      return sb.toString();
   }
}
