package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.StringUtils;

public class JoinMetaData extends MetaData implements ColumnMetaDataContainer {
   private static final long serialVersionUID = -3132167406276575350L;
   protected ForeignKeyMetaData foreignKeyMetaData;
   protected IndexMetaData indexMetaData;
   protected UniqueMetaData uniqueMetaData;
   protected PrimaryKeyMetaData primaryKeyMetaData;
   protected boolean outer = false;
   protected String table;
   protected String catalog;
   protected String schema;
   protected IndexedValue indexed = null;
   protected boolean unique;
   protected String columnName;
   protected List columns = null;

   public JoinMetaData() {
   }

   public JoinMetaData(JoinMetaData joinmd) {
      this.table = joinmd.table;
      this.catalog = joinmd.catalog;
      this.schema = joinmd.schema;
      this.columnName = joinmd.columnName;
      this.outer = joinmd.outer;
      this.indexed = joinmd.indexed;
      this.unique = joinmd.unique;
      if (joinmd.columns != null) {
         for(ColumnMetaData colmd : joinmd.columns) {
            this.addColumn(new ColumnMetaData(colmd));
         }
      }

   }

   public void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.table != null && this.parent instanceof AbstractMemberMetaData) {
         AbstractMemberMetaData mmd = (AbstractMemberMetaData)this.parent;
         throw new InvalidMemberMetaDataException("044130", new Object[]{mmd.getClassName(), mmd.getFullFieldName()});
      } else {
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

         this.setInitialised();
      }
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

   public final boolean isOuter() {
      return this.outer;
   }

   public JoinMetaData setOuter(boolean outer) {
      this.outer = outer;
      return this;
   }

   public String getDeleteAction() {
      return this.foreignKeyMetaData != null ? this.foreignKeyMetaData.getDeleteAction().toString() : null;
   }

   public JoinMetaData setDeleteAction(String deleteAction) {
      if (!StringUtils.isWhitespace(deleteAction)) {
         this.foreignKeyMetaData = new ForeignKeyMetaData();
         this.foreignKeyMetaData.setDeleteAction(ForeignKeyAction.getForeignKeyAction(deleteAction));
      }

      return this;
   }

   public IndexedValue getIndexed() {
      return this.indexed;
   }

   public JoinMetaData setIndexed(IndexedValue indexed) {
      if (indexed != null) {
         this.indexed = indexed;
      }

      return this;
   }

   public boolean isUnique() {
      return this.unique;
   }

   public JoinMetaData setUnique(boolean unique) {
      this.unique = unique;
      return this;
   }

   public JoinMetaData setUnique(String unique) {
      this.unique = MetaDataUtils.getBooleanForString(unique, false);
      return this;
   }

   public final String getTable() {
      return this.table;
   }

   public JoinMetaData setTable(String table) {
      this.table = StringUtils.isWhitespace(table) ? null : table;
      return this;
   }

   public final String getCatalog() {
      return this.catalog;
   }

   public JoinMetaData setCatalog(String catalog) {
      this.catalog = StringUtils.isWhitespace(catalog) ? null : catalog;
      return this;
   }

   public final String getSchema() {
      return this.schema;
   }

   public JoinMetaData setSchema(String schema) {
      this.schema = StringUtils.isWhitespace(schema) ? null : schema;
      return this;
   }

   public final String getColumnName() {
      return this.columnName;
   }

   public JoinMetaData setColumnName(String columnName) {
      if (!StringUtils.isWhitespace(columnName)) {
         ColumnMetaData colmd = new ColumnMetaData();
         colmd.setName(columnName);
         colmd.parent = this;
         this.addColumn(colmd);
         this.columnName = columnName;
      } else {
         this.columnName = null;
      }

      return this;
   }

   public final ColumnMetaData[] getColumnMetaData() {
      return this.columns == null ? null : (ColumnMetaData[])this.columns.toArray(new ColumnMetaData[this.columns.size()]);
   }

   public final IndexMetaData getIndexMetaData() {
      return this.indexMetaData;
   }

   public final UniqueMetaData getUniqueMetaData() {
      return this.uniqueMetaData;
   }

   public final ForeignKeyMetaData getForeignKeyMetaData() {
      return this.foreignKeyMetaData;
   }

   public final PrimaryKeyMetaData getPrimaryKeyMetaData() {
      return this.primaryKeyMetaData;
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

   public final void setIndexMetaData(IndexMetaData indexMetaData) {
      this.indexMetaData = indexMetaData;
      indexMetaData.parent = this;
   }

   public IndexMetaData newIndexMetaData() {
      IndexMetaData idxmd = new IndexMetaData();
      this.setIndexMetaData(idxmd);
      return idxmd;
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

   public final void setPrimaryKeyMetaData(PrimaryKeyMetaData primaryKeyMetaData) {
      this.primaryKeyMetaData = primaryKeyMetaData;
      primaryKeyMetaData.parent = this;
   }

   public PrimaryKeyMetaData newPrimaryKeyMetaData() {
      PrimaryKeyMetaData pkmd = new PrimaryKeyMetaData();
      this.setPrimaryKeyMetaData(pkmd);
      return pkmd;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<join");
      if (this.table != null) {
         sb.append(" table=\"" + this.table + "\"");
      }

      if (this.columnName != null) {
         sb.append(" column=\"" + this.columnName + "\"");
      }

      sb.append(" outer=\"" + this.outer + "\"");
      sb.append(">\n");
      if (this.primaryKeyMetaData != null) {
         sb.append(this.primaryKeyMetaData.toString(prefix + indent, indent));
      }

      if (this.columns != null) {
         for(ColumnMetaData colmd : this.columns) {
            sb.append(colmd.toString(prefix + indent, indent));
         }
      }

      if (this.foreignKeyMetaData != null) {
         sb.append(this.foreignKeyMetaData.toString(prefix + indent, indent));
      }

      if (this.indexMetaData != null) {
         sb.append(this.indexMetaData.toString(prefix + indent, indent));
      }

      if (this.uniqueMetaData != null) {
         sb.append(this.uniqueMetaData.toString(prefix + indent, indent));
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</join>\n");
      return sb.toString();
   }
}
