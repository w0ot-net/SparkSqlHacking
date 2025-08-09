package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.StringUtils;

public abstract class AbstractElementMetaData extends MetaData implements ColumnMetaDataContainer {
   private static final long serialVersionUID = -6764719335323972803L;
   protected boolean unique;
   protected String mappedBy;
   protected IndexedValue indexed = null;
   protected IndexMetaData indexMetaData;
   protected UniqueMetaData uniqueMetaData;
   protected ForeignKeyMetaData foreignKeyMetaData;
   protected EmbeddedMetaData embeddedMetaData;
   protected String table;
   protected String columnName;
   protected List columns = null;

   public AbstractElementMetaData(AbstractElementMetaData aemd) {
      super((MetaData)null, aemd);
      this.table = aemd.table;
      this.columnName = aemd.columnName;
      this.unique = aemd.unique;
      this.indexed = aemd.indexed;
      this.mappedBy = aemd.mappedBy;
      if (aemd.indexMetaData != null) {
         this.setIndexMetaData(new IndexMetaData(aemd.indexMetaData));
      }

      if (aemd.uniqueMetaData != null) {
         this.setUniqueMetaData(new UniqueMetaData(aemd.uniqueMetaData));
      }

      if (aemd.foreignKeyMetaData != null) {
         this.setForeignKeyMetaData(new ForeignKeyMetaData(aemd.foreignKeyMetaData));
      }

      if (aemd.embeddedMetaData != null) {
         this.setEmbeddedMetaData(new EmbeddedMetaData(aemd.embeddedMetaData));
      }

      if (aemd.columns != null) {
         for(ColumnMetaData colmd : aemd.columns) {
            this.addColumn(new ColumnMetaData(colmd));
         }
      }

   }

   public AbstractElementMetaData() {
   }

   public void populate(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      if (this.embeddedMetaData != null) {
         this.embeddedMetaData.populate(clr, primary, mmgr);
      }

   }

   public void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.columns == null && this.columnName != null) {
         ColumnMetaData colmd = new ColumnMetaData();
         colmd.setName(this.columnName);
         colmd.parent = this;
         this.addColumn(colmd);
      }

      if (this.indexMetaData == null && this.indexed != null && this.indexed != IndexedValue.FALSE) {
         this.indexMetaData = new IndexMetaData();
         this.indexMetaData.setUnique(this.indexed == IndexedValue.UNIQUE);
         if (this.columns != null) {
            for(ColumnMetaData colmd : this.columns) {
               this.indexMetaData.addColumn(colmd.getName());
            }
         }
      }

      if (this.uniqueMetaData == null && this.unique) {
         this.uniqueMetaData = new UniqueMetaData();
         this.uniqueMetaData.setTable(this.columnName);
         if (this.columns != null) {
            for(ColumnMetaData colmd : this.columns) {
               this.uniqueMetaData.addColumn(colmd.getName());
            }
         }
      }

      if (this.embeddedMetaData != null) {
         this.embeddedMetaData.initialise(clr, mmgr);
      }

      this.setInitialised();
   }

   public String getTable() {
      return this.table;
   }

   public void setTable(String table) {
      this.table = table;
   }

   public String getMappedBy() {
      return this.mappedBy;
   }

   public void setMappedBy(String mappedBy) {
      this.mappedBy = StringUtils.isWhitespace(mappedBy) ? null : mappedBy;
   }

   public IndexedValue getIndexed() {
      return this.indexed;
   }

   public void setIndexed(IndexedValue indexed) {
      this.indexed = indexed;
   }

   public boolean isUnique() {
      return this.unique;
   }

   public void setUnique(boolean unique) {
      this.unique = unique;
   }

   public final ForeignKeyMetaData getForeignKeyMetaData() {
      return this.foreignKeyMetaData;
   }

   public ForeignKeyAction getDeleteAction() {
      return this.foreignKeyMetaData != null ? this.foreignKeyMetaData.getDeleteAction() : null;
   }

   public void setDeleteAction(String deleteAction) {
      if (!StringUtils.isWhitespace(deleteAction)) {
         this.foreignKeyMetaData = new ForeignKeyMetaData();
         this.foreignKeyMetaData.setDeleteAction(ForeignKeyAction.getForeignKeyAction(deleteAction));
      }

   }

   public void setDeleteAction(ForeignKeyAction deleteAction) {
      if (deleteAction != null) {
         this.foreignKeyMetaData = new ForeignKeyMetaData();
         this.foreignKeyMetaData.setDeleteAction(deleteAction);
      }

   }

   public ForeignKeyAction getUpdateAction() {
      return this.foreignKeyMetaData != null ? this.foreignKeyMetaData.getUpdateAction() : null;
   }

   public void setUpdateAction(String updateAction) {
      if (!StringUtils.isWhitespace(updateAction)) {
         this.foreignKeyMetaData = new ForeignKeyMetaData();
         this.foreignKeyMetaData.setUpdateAction(ForeignKeyAction.getForeignKeyAction(updateAction));
      }

   }

   public void setUpdateAction(ForeignKeyAction updateAction) {
      if (updateAction != null) {
         this.foreignKeyMetaData = new ForeignKeyMetaData();
         this.foreignKeyMetaData.setUpdateAction(updateAction);
      }

   }

   public final String getColumnName() {
      return this.columnName;
   }

   public void setColumnName(String columnName) {
      if (!StringUtils.isWhitespace(columnName)) {
         this.columnName = columnName;
         if (this.columns == null) {
            ColumnMetaData colmd = new ColumnMetaData();
            colmd.setName(columnName);
            colmd.parent = this;
            this.addColumn(colmd);
         } else if (this.columns.size() == 1) {
            ((ColumnMetaData)this.columns.iterator().next()).setName(columnName);
         }
      } else {
         this.columnName = null;
      }

   }

   public final ColumnMetaData[] getColumnMetaData() {
      return this.columns == null ? new ColumnMetaData[0] : (ColumnMetaData[])this.columns.toArray(new ColumnMetaData[this.columns.size()]);
   }

   public void addColumn(ColumnMetaData colmd) {
      if (this.columns == null) {
         this.columns = new ArrayList();
      }

      this.columns.add(colmd);
      colmd.parent = this;
   }

   public ColumnMetaData newColumnMetaData() {
      ColumnMetaData colmd = new ColumnMetaData();
      this.addColumn(colmd);
      return colmd;
   }

   public final EmbeddedMetaData getEmbeddedMetaData() {
      return this.embeddedMetaData;
   }

   public final void setEmbeddedMetaData(EmbeddedMetaData embeddedMetaData) {
      this.embeddedMetaData = embeddedMetaData;
      embeddedMetaData.parent = this;
   }

   public EmbeddedMetaData newEmbeddedMetaData() {
      EmbeddedMetaData embmd = new EmbeddedMetaData();
      this.setEmbeddedMetaData(embmd);
      return embmd;
   }

   public final void setForeignKeyMetaData(ForeignKeyMetaData foreignKeyMetaData) {
      this.foreignKeyMetaData = foreignKeyMetaData;
      foreignKeyMetaData.parent = this;
   }

   public ForeignKeyMetaData newForeignKeyMetaData() {
      ForeignKeyMetaData fkmd = new ForeignKeyMetaData();
      this.setForeignKeyMetaData(fkmd);
      return fkmd;
   }

   public final IndexMetaData getIndexMetaData() {
      return this.indexMetaData;
   }

   public final void setIndexMetaData(IndexMetaData indexMetaData) {
      this.indexMetaData = indexMetaData;
      indexMetaData.parent = this;
   }

   public IndexMetaData newIndexMetaData() {
      IndexMetaData idxmd = new IndexMetaData();
      this.setIndexMetaData(idxmd);
      return idxmd;
   }

   public final UniqueMetaData getUniqueMetaData() {
      return this.uniqueMetaData;
   }

   public final void setUniqueMetaData(UniqueMetaData uniqueMetaData) {
      this.uniqueMetaData = uniqueMetaData;
      uniqueMetaData.parent = this;
   }

   public UniqueMetaData newUniqueMetaData() {
      UniqueMetaData unimd = new UniqueMetaData();
      this.setUniqueMetaData(unimd);
      return unimd;
   }
}
