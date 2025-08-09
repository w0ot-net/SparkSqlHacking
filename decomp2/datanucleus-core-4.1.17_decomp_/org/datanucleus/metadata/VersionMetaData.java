package org.datanucleus.metadata;

import java.util.Date;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class VersionMetaData extends MetaData {
   private static final long serialVersionUID = 8277278092349220294L;
   protected VersionStrategy versionStrategy;
   protected String columnName;
   protected ColumnMetaData columnMetaData;
   protected IndexMetaData indexMetaData;
   protected IndexedValue indexed = null;
   protected String fieldName = null;

   public void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.hasExtension("field-name")) {
         String val = this.getValueForExtension("field-name");
         if (!StringUtils.isWhitespace(val)) {
            this.fieldName = val;
            this.columnName = null;
         }
      }

      if (this.fieldName == null) {
         if (this.columnMetaData == null && this.columnName != null) {
            this.columnMetaData = new ColumnMetaData();
            this.columnMetaData.setName(this.columnName);
            this.columnMetaData.parent = this;
         }

         if (this.indexMetaData == null && this.columnMetaData != null && this.indexed != null && this.indexed != IndexedValue.FALSE) {
            this.indexMetaData = new IndexMetaData();
            this.indexMetaData.setUnique(this.indexed == IndexedValue.UNIQUE);
            this.indexMetaData.addColumn(this.columnMetaData.getName());
            this.indexMetaData.parent = this;
         }
      } else if (this.getParent() instanceof AbstractClassMetaData) {
         AbstractMemberMetaData vermmd = ((AbstractClassMetaData)this.getParent()).getMetaDataForMember(this.fieldName);
         if (vermmd != null && Date.class.isAssignableFrom(vermmd.getType())) {
            NucleusLogger.GENERAL.debug("Setting version-strategy of field " + vermmd.getFullFieldName() + " to DATE_TIME since is Date-based");
            this.versionStrategy = VersionStrategy.DATE_TIME;
         }
      }

   }

   public final ColumnMetaData getColumnMetaData() {
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

   public final VersionStrategy getVersionStrategy() {
      return this.versionStrategy;
   }

   public VersionMetaData setStrategy(VersionStrategy strategy) {
      this.versionStrategy = strategy;
      return this;
   }

   public VersionMetaData setStrategy(String strategy) {
      if (!StringUtils.isWhitespace(strategy) && VersionStrategy.getVersionStrategy(strategy) != null) {
         this.versionStrategy = VersionStrategy.getVersionStrategy(strategy);
         return this;
      } else {
         throw new RuntimeException(Localiser.msg("044156"));
      }
   }

   public final IndexMetaData getIndexMetaData() {
      return this.indexMetaData;
   }

   public final void setIndexMetaData(IndexMetaData indexMetaData) {
      this.indexMetaData = indexMetaData;
   }

   public IndexMetaData newIndexMetaData() {
      IndexMetaData idxmd = new IndexMetaData();
      this.setIndexMetaData(idxmd);
      return idxmd;
   }

   public String getColumnName() {
      return this.columnName;
   }

   public VersionMetaData setColumnName(String columnName) {
      this.columnName = StringUtils.isWhitespace(columnName) ? null : columnName;
      return this;
   }

   public IndexedValue getIndexed() {
      return this.indexed;
   }

   public VersionMetaData setIndexed(IndexedValue indexed) {
      this.indexed = indexed;
      return this;
   }

   public final String getFieldName() {
      return this.fieldName;
   }

   public VersionMetaData setFieldName(String fieldName) {
      this.fieldName = fieldName;
      return this;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<version " + (this.versionStrategy != null ? "strategy=\"" + this.versionStrategy.toString() + "\"" : "") + (this.indexed != null ? " indexed=\"" + this.indexed.toString() + "\"" : ""));
      if (this.columnName != null && this.columnMetaData == null) {
         sb.append(" column=\"" + this.columnName + "\"");
      }

      sb.append(">\n");
      if (this.columnMetaData != null) {
         sb.append(this.columnMetaData.toString(prefix + indent, indent));
      }

      if (this.indexMetaData != null) {
         sb.append(this.indexMetaData.toString(prefix + indent, indent));
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</version>\n");
      return sb.toString();
   }
}
