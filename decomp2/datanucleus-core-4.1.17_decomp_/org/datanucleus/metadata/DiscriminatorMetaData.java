package org.datanucleus.metadata;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class DiscriminatorMetaData extends MetaData {
   private static final long serialVersionUID = 4983675266122550860L;
   protected DiscriminatorStrategy strategy = null;
   protected String columnName = null;
   protected String value = null;
   protected IndexedValue indexed = null;
   protected ColumnMetaData columnMetaData = null;
   protected IndexMetaData indexMetaData;

   public DiscriminatorMetaData() {
   }

   public DiscriminatorMetaData(DiscriminatorMetaData dmd) {
      super((MetaData)null, dmd);
      this.columnName = dmd.columnName;
      this.value = dmd.value;
      this.strategy = dmd.strategy;
      this.indexed = dmd.indexed;
      if (dmd.columnMetaData != null) {
         this.setColumnMetaData(new ColumnMetaData(dmd.columnMetaData));
      }

      if (dmd.indexMetaData != null) {
         this.setIndexMetaData(new IndexMetaData(dmd.indexMetaData));
      }

   }

   public void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.value != null && this.strategy == null) {
         this.strategy = DiscriminatorStrategy.VALUE_MAP;
      } else if (this.strategy == null) {
         this.strategy = DiscriminatorStrategy.CLASS_NAME;
      }

      if (this.strategy == DiscriminatorStrategy.VALUE_MAP && this.value == null) {
         AbstractClassMetaData cmd = (AbstractClassMetaData)this.parent.getParent();
         if (cmd instanceof InterfaceMetaData || cmd instanceof ClassMetaData && !((ClassMetaData)cmd).isAbstract()) {
            String className = cmd.getFullClassName();
            NucleusLogger.METADATA.warn(Localiser.msg("044103", className));
            this.value = className;
         }
      }

      if (this.indexMetaData == null && this.columnMetaData != null && this.indexed != null && this.indexed != IndexedValue.FALSE) {
         this.indexMetaData = new IndexMetaData();
         this.indexMetaData.setUnique(this.indexed == IndexedValue.UNIQUE);
         this.indexMetaData.addColumn(this.columnMetaData.getName());
         this.indexMetaData.parent = this;
      }

      this.setInitialised();
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

   public final IndexMetaData getIndexMetaData() {
      return this.indexMetaData;
   }

   public final void setIndexMetaData(IndexMetaData indexMetaData) {
      this.indexMetaData = indexMetaData;
      this.indexMetaData.parent = this;
   }

   public IndexMetaData newIndexMetaData() {
      IndexMetaData idxmd = new IndexMetaData();
      this.setIndexMetaData(idxmd);
      return idxmd;
   }

   public String getValue() {
      return this.value;
   }

   public DiscriminatorMetaData setValue(String value) {
      if (!StringUtils.isWhitespace(value)) {
         this.value = value;
      }

      return this;
   }

   public String getColumnName() {
      return this.columnMetaData != null && this.columnMetaData.getName() != null ? this.columnMetaData.getName() : this.columnName;
   }

   public DiscriminatorMetaData setColumnName(String columnName) {
      if (!StringUtils.isWhitespace(columnName)) {
         if (this.columnMetaData == null) {
            this.columnMetaData = new ColumnMetaData();
            this.columnMetaData.setName(columnName);
            this.columnMetaData.parent = this;
         } else {
            this.columnMetaData.setName(columnName);
         }

         this.columnName = columnName;
      } else {
         this.columnName = null;
      }

      return this;
   }

   public final DiscriminatorStrategy getStrategy() {
      return this.strategy;
   }

   public DiscriminatorMetaData setStrategy(DiscriminatorStrategy strategy) {
      this.strategy = strategy;
      return this;
   }

   public DiscriminatorMetaData setStrategy(String strategy) {
      this.strategy = DiscriminatorStrategy.getDiscriminatorStrategy(strategy);
      return this;
   }

   public IndexedValue getIndexed() {
      return this.indexed;
   }

   public DiscriminatorMetaData setIndexed(IndexedValue indexed) {
      this.indexed = indexed;
      return this;
   }

   public DiscriminatorMetaData setIndexed(String indexed) {
      this.indexed = IndexedValue.getIndexedValue(indexed);
      return this;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<discriminator");
      if (this.strategy != null) {
         sb.append(" strategy=\"" + this.strategy.toString() + "\"");
      }

      if (this.columnName != null && this.columnMetaData == null) {
         sb.append(" column=\"" + this.columnName + "\"");
      }

      if (this.value != null) {
         sb.append(" value=\"" + this.value + "\"");
      }

      if (this.indexed != null) {
         sb.append(" indexed=\"" + this.indexed.toString() + "\"");
      }

      sb.append(">\n");
      if (this.columnMetaData != null) {
         sb.append(this.columnMetaData.toString(prefix + indent, indent));
      }

      if (this.indexMetaData != null) {
         sb.append(this.indexMetaData.toString(prefix + indent, indent));
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</discriminator>\n");
      return sb.toString();
   }
}
