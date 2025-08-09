package org.datanucleus.store.rdbms.key;

import java.util.ArrayList;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.ForeignKeyAction;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;

public class ForeignKey extends Key {
   private DatastoreAdapter dba;
   private boolean initiallyDeferred;
   private DatastoreClass refTable;
   private FKAction updateAction;
   private FKAction deleteAction;
   private ArrayList refColumns = new ArrayList();
   private String foreignKeyDefinition = null;

   public ForeignKey(boolean initiallyDeferred) {
      super((Table)null);
      this.initiallyDeferred = initiallyDeferred;
      this.refTable = null;
      this.dba = null;
   }

   public ForeignKey(JavaTypeMapping mapping, DatastoreAdapter dba, DatastoreClass refTable, boolean initiallyDeferred) {
      super(mapping.getTable());
      this.initiallyDeferred = initiallyDeferred;
      this.refTable = refTable;
      this.dba = dba;
      if (refTable.getIdMapping() == null) {
         throw (new NucleusException("ForeignKey ID mapping is not initilized for " + mapping + ". Table referenced: " + refTable.toString())).setFatal();
      } else {
         for(int i = 0; i < refTable.getIdMapping().getNumberOfDatastoreMappings(); ++i) {
            this.setColumn(i, mapping.getDatastoreMapping(i).getColumn(), refTable.getIdMapping().getDatastoreMapping(i).getColumn());
         }

      }
   }

   public void setForMetaData(ForeignKeyMetaData fkmd) {
      if (fkmd != null) {
         if (fkmd.getFkDefinitionApplies() && fkmd.getFkDefinition() != null) {
            this.foreignKeyDefinition = fkmd.getFkDefinition();
            this.name = fkmd.getName();
            this.refColumns = null;
            this.updateAction = null;
            this.deleteAction = null;
            this.refTable = null;
            this.refColumns = null;
         } else {
            if (fkmd.getName() != null) {
               this.setName(fkmd.getName());
            }

            ForeignKeyAction deleteAction = fkmd.getDeleteAction();
            if (deleteAction != null) {
               if (deleteAction.equals(ForeignKeyAction.CASCADE)) {
                  this.setDeleteAction(ForeignKey.FKAction.CASCADE);
               } else if (deleteAction.equals(ForeignKeyAction.RESTRICT)) {
                  this.setDeleteAction(ForeignKey.FKAction.RESTRICT);
               } else if (deleteAction.equals(ForeignKeyAction.NULL)) {
                  this.setDeleteAction(ForeignKey.FKAction.NULL);
               } else if (deleteAction.equals(ForeignKeyAction.DEFAULT)) {
                  this.setDeleteAction(ForeignKey.FKAction.DEFAULT);
               }
            }

            ForeignKeyAction updateAction = fkmd.getUpdateAction();
            if (updateAction != null) {
               if (updateAction.equals(ForeignKeyAction.CASCADE)) {
                  this.setUpdateAction(ForeignKey.FKAction.CASCADE);
               } else if (updateAction.equals(ForeignKeyAction.RESTRICT)) {
                  this.setUpdateAction(ForeignKey.FKAction.RESTRICT);
               } else if (updateAction.equals(ForeignKeyAction.NULL)) {
                  this.setUpdateAction(ForeignKey.FKAction.NULL);
               } else if (updateAction.equals(ForeignKeyAction.DEFAULT)) {
                  this.setUpdateAction(ForeignKey.FKAction.DEFAULT);
               }
            }

            if (fkmd.isDeferred()) {
               this.initiallyDeferred = true;
            }
         }

      }
   }

   public void setDeleteAction(FKAction deleteAction) {
      this.deleteAction = deleteAction;
   }

   public void setUpdateAction(FKAction updateAction) {
      this.updateAction = updateAction;
   }

   public void addColumn(Column col, Column refCol) {
      this.setColumn(this.columns.size(), col, refCol);
   }

   public DatastoreClass getRefTable() {
      return this.refTable;
   }

   public String getRefColumnList() {
      return getColumnList(this.refColumns);
   }

   public void setColumn(int seq, Column col, Column refCol) {
      if (this.table == null) {
         this.table = col.getTable();
         this.refTable = (DatastoreClass)refCol.getTable();
         this.dba = this.table.getStoreManager().getDatastoreAdapter();
      } else {
         if (!this.table.equals(col.getTable())) {
            throw (new NucleusException("Cannot add " + col + " as FK column for " + this.table)).setFatal();
         }

         if (!this.refTable.equals(refCol.getTable())) {
            throw (new NucleusException("Cannot add " + refCol + " as referenced FK column for " + this.refTable)).setFatal();
         }
      }

      setMinSize(this.columns, seq + 1);
      setMinSize(this.refColumns, seq + 1);
      this.columns.set(seq, col);
      this.refColumns.set(seq, refCol);
   }

   public int hashCode() {
      return this.foreignKeyDefinition != null ? this.foreignKeyDefinition.hashCode() : super.hashCode() ^ this.refColumns.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof ForeignKey)) {
         return false;
      } else {
         ForeignKey fk = (ForeignKey)obj;
         return this.refColumns != null && !this.refColumns.equals(fk.refColumns) ? false : super.equals(obj);
      }
   }

   public boolean isEqual(ForeignKey fk) {
      String colList = this.getColumnList();
      String refColList = this.getRefColumnList();
      if (colList == null && fk.getColumnList() != null) {
         return false;
      } else if (refColList == null && fk.getRefColumnList() != null) {
         return false;
      } else {
         return colList.equals(fk.getColumnList()) && this.getTable() == fk.getTable() && refColList.equals(fk.getRefColumnList()) && this.getRefTable().equals(fk.getRefTable());
      }
   }

   public String toString() {
      if (this.foreignKeyDefinition != null) {
         return this.foreignKeyDefinition;
      } else {
         StringBuilder s = new StringBuilder("FOREIGN KEY ");
         s.append(getColumnList(this.columns));
         if (this.refTable != null) {
            s.append(" REFERENCES ");
            s.append(this.refTable.toString());
            s.append(" ").append(getColumnList(this.refColumns));
         }

         if (this.deleteAction != null && (this.deleteAction == ForeignKey.FKAction.CASCADE && this.dba.supportsOption("FkDeleteActionCascade") || this.deleteAction == ForeignKey.FKAction.RESTRICT && this.dba.supportsOption("FkDeleteActionRestrict") || this.deleteAction == ForeignKey.FKAction.NULL && this.dba.supportsOption("FkDeleteActionNull") || this.deleteAction == ForeignKey.FKAction.DEFAULT && this.dba.supportsOption("FkDeleteActionDefault"))) {
            s.append(" ON DELETE ").append(this.deleteAction.toString());
         }

         if (this.updateAction != null && (this.updateAction == ForeignKey.FKAction.CASCADE && this.dba.supportsOption("FkUpdateActionCascade") || this.updateAction == ForeignKey.FKAction.RESTRICT && this.dba.supportsOption("FkUpdateActionRestrict") || this.updateAction == ForeignKey.FKAction.NULL && this.dba.supportsOption("FkUpdateActionNull") || this.updateAction == ForeignKey.FKAction.DEFAULT && this.dba.supportsOption("FkUpdateActionDefault"))) {
            s.append(" ON UPDATE ").append(this.updateAction.toString());
         }

         if (this.initiallyDeferred && this.dba.supportsOption("DeferredConstraints")) {
            s.append(" INITIALLY DEFERRED");
         }

         s.append(" ");
         return s.toString();
      }
   }

   public static enum FKAction {
      CASCADE("CASCADE"),
      RESTRICT("RESTRICT"),
      NULL("SET NULL"),
      DEFAULT("SET DEFAULT");

      String keyword;

      private FKAction(String word) {
         this.keyword = word;
      }

      public String toString() {
         return this.keyword;
      }
   }
}
