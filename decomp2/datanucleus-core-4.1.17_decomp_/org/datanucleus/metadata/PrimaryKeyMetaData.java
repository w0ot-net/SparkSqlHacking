package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.util.StringUtils;

public class PrimaryKeyMetaData extends MetaData implements ColumnMetaDataContainer {
   private static final long serialVersionUID = 6303979815375277900L;
   protected String name = null;
   protected String columnName = null;
   protected List columns = null;

   public String getName() {
      return this.name;
   }

   public PrimaryKeyMetaData setName(String name) {
      this.name = StringUtils.isWhitespace(name) ? null : name;
      return this;
   }

   public PrimaryKeyMetaData setColumnName(String name) {
      if (!StringUtils.isWhitespace(name)) {
         this.columnName = StringUtils.isWhitespace(name) ? null : name;
         if (this.columns == null) {
            ColumnMetaData colmd = this.newColumnMetadata();
            colmd.setName(this.columnName);
            this.addColumn(colmd);
         } else if (this.columns.size() == 1) {
            ((ColumnMetaData)this.columns.iterator().next()).setName(this.columnName);
         }
      } else {
         this.columnName = null;
      }

      return this;
   }

   public String getColumnName() {
      return this.columnName;
   }

   public void addColumn(ColumnMetaData colmd) {
      if (this.columns == null) {
         this.columns = new ArrayList();
      }

      this.columns.add(colmd);
      colmd.parent = this;
   }

   public ColumnMetaData newColumnMetadata() {
      ColumnMetaData colmd = new ColumnMetaData();
      this.addColumn(colmd);
      return colmd;
   }

   public final ColumnMetaData[] getColumnMetaData() {
      return this.columns == null ? null : (ColumnMetaData[])this.columns.toArray(new ColumnMetaData[this.columns.size()]);
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<primary-key" + (this.name != null ? " name=\"" + this.name + "\"" : "") + (this.columnName != null ? " column=\"" + this.columnName + "\"" : "") + ">\n");
      if (this.columns != null) {
         for(ColumnMetaData colmd : this.columns) {
            sb.append(colmd.toString(prefix + indent, indent));
         }
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</primary-key>\n");
      return sb.toString();
   }
}
