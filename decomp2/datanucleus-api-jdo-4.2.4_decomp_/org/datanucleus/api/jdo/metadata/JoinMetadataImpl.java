package org.datanucleus.api.jdo.metadata;

import javax.jdo.annotations.ForeignKeyAction;
import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.ForeignKeyMetadata;
import javax.jdo.metadata.IndexMetadata;
import javax.jdo.metadata.Indexed;
import javax.jdo.metadata.JoinMetadata;
import javax.jdo.metadata.PrimaryKeyMetadata;
import javax.jdo.metadata.UniqueMetadata;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.IndexedValue;
import org.datanucleus.metadata.JoinMetaData;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.metadata.UniqueMetaData;

public class JoinMetadataImpl extends AbstractMetadataImpl implements JoinMetadata {
   public JoinMetadataImpl(JoinMetaData internal) {
      super(internal);
   }

   public JoinMetaData getInternal() {
      return (JoinMetaData)this.internalMD;
   }

   public String getColumn() {
      return this.getInternal().getColumnName();
   }

   public ColumnMetadata[] getColumns() {
      ColumnMetaData[] internalColmds = this.getInternal().getColumnMetaData();
      if (internalColmds == null) {
         return null;
      } else {
         ColumnMetadataImpl[] colmds = new ColumnMetadataImpl[internalColmds.length];

         for(int i = 0; i < colmds.length; ++i) {
            colmds[i] = new ColumnMetadataImpl(internalColmds[i]);
            colmds[i].parent = this;
         }

         return colmds;
      }
   }

   public ForeignKeyAction getDeleteAction() {
      ForeignKeyMetaData fkmd = this.getInternal().getForeignKeyMetaData();
      if (fkmd != null) {
         org.datanucleus.metadata.ForeignKeyAction fk = fkmd.getDeleteAction();
         if (fk == org.datanucleus.metadata.ForeignKeyAction.CASCADE) {
            return ForeignKeyAction.CASCADE;
         }

         if (fk == org.datanucleus.metadata.ForeignKeyAction.DEFAULT) {
            return ForeignKeyAction.DEFAULT;
         }

         if (fk == org.datanucleus.metadata.ForeignKeyAction.NONE) {
            return ForeignKeyAction.NONE;
         }

         if (fk == org.datanucleus.metadata.ForeignKeyAction.NULL) {
            return ForeignKeyAction.NULL;
         }

         if (fk == org.datanucleus.metadata.ForeignKeyAction.RESTRICT) {
            return ForeignKeyAction.RESTRICT;
         }
      }

      return ForeignKeyAction.UNSPECIFIED;
   }

   public ForeignKeyMetadata getForeignKeyMetadata() {
      ForeignKeyMetaData internalFkmd = this.getInternal().getForeignKeyMetaData();
      if (internalFkmd == null) {
         return null;
      } else {
         ForeignKeyMetadataImpl fkmd = new ForeignKeyMetadataImpl(internalFkmd);
         fkmd.parent = this;
         return fkmd;
      }
   }

   public IndexMetadata getIndexMetadata() {
      IndexMetaData internalIdxmd = this.getInternal().getIndexMetaData();
      if (internalIdxmd == null) {
         return null;
      } else {
         IndexMetadataImpl idxmd = new IndexMetadataImpl(internalIdxmd);
         idxmd.parent = this;
         return idxmd;
      }
   }

   public Indexed getIndexed() {
      IndexedValue idxVal = this.getInternal().getIndexed();
      if (idxVal == IndexedValue.TRUE) {
         return Indexed.TRUE;
      } else if (idxVal == IndexedValue.FALSE) {
         return Indexed.FALSE;
      } else {
         return idxVal == IndexedValue.UNIQUE ? Indexed.UNIQUE : Indexed.UNSPECIFIED;
      }
   }

   public int getNumberOfColumns() {
      ColumnMetaData[] colmds = this.getInternal().getColumnMetaData();
      return colmds != null ? colmds.length : 0;
   }

   public boolean getOuter() {
      return this.getInternal().isOuter();
   }

   public PrimaryKeyMetadata getPrimaryKeyMetadata() {
      PrimaryKeyMetaData internalPkmd = this.getInternal().getPrimaryKeyMetaData();
      if (internalPkmd == null) {
         return null;
      } else {
         PrimaryKeyMetadataImpl pkmd = new PrimaryKeyMetadataImpl(internalPkmd);
         pkmd.parent = this;
         return pkmd;
      }
   }

   public String getTable() {
      return this.getInternal().getTable();
   }

   public Boolean getUnique() {
      return this.getInternal().isUnique();
   }

   public UniqueMetadata getUniqueMetadata() {
      UniqueMetaData internalUnimd = this.getInternal().getUniqueMetaData();
      if (internalUnimd == null) {
         return null;
      } else {
         UniqueMetadataImpl unimd = new UniqueMetadataImpl(internalUnimd);
         unimd.parent = this;
         return unimd;
      }
   }

   public ColumnMetadata newColumnMetadata() {
      ColumnMetaData internalColmd = this.getInternal().newColumnMetaData();
      ColumnMetadataImpl colmd = new ColumnMetadataImpl(internalColmd);
      colmd.parent = this;
      return colmd;
   }

   public ForeignKeyMetadata newForeignKeyMetadata() {
      ForeignKeyMetaData internalFkmd = this.getInternal().newForeignKeyMetaData();
      ForeignKeyMetadataImpl fkmd = new ForeignKeyMetadataImpl(internalFkmd);
      fkmd.parent = this;
      return fkmd;
   }

   public IndexMetadata newIndexMetadata() {
      IndexMetaData internalIdxmd = this.getInternal().newIndexMetaData();
      IndexMetadataImpl idxmd = new IndexMetadataImpl(internalIdxmd);
      idxmd.parent = this;
      return idxmd;
   }

   public PrimaryKeyMetadata newPrimaryKeyMetadata() {
      PrimaryKeyMetaData internalPkmd = this.getInternal().newPrimaryKeyMetaData();
      PrimaryKeyMetadataImpl pkmd = new PrimaryKeyMetadataImpl(internalPkmd);
      pkmd.parent = this;
      return pkmd;
   }

   public UniqueMetadata newUniqueMetadata() {
      UniqueMetaData internalUnimd = this.getInternal().newUniqueMetaData();
      UniqueMetadataImpl unimd = new UniqueMetadataImpl(internalUnimd);
      unimd.parent = this;
      return unimd;
   }

   public JoinMetadata setColumn(String name) {
      this.getInternal().setColumnName(name);
      return this;
   }

   public JoinMetadata setDeleteAction(ForeignKeyAction fk) {
      ForeignKeyMetaData fkmd = this.getInternal().getForeignKeyMetaData();
      if (fk == ForeignKeyAction.CASCADE) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.CASCADE);
      } else if (fk == ForeignKeyAction.DEFAULT) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.DEFAULT);
      } else if (fk == ForeignKeyAction.NONE) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.NONE);
      } else if (fk == ForeignKeyAction.NULL) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.NULL);
      } else if (fk == ForeignKeyAction.RESTRICT) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.RESTRICT);
      }

      return this;
   }

   public JoinMetadata setIndexed(Indexed val) {
      if (val == Indexed.TRUE) {
         this.getInternal().setIndexed(IndexedValue.TRUE);
      } else if (val == Indexed.FALSE) {
         this.getInternal().setIndexed(IndexedValue.FALSE);
      } else if (val == Indexed.UNIQUE) {
         this.getInternal().setIndexed(IndexedValue.UNIQUE);
      }

      return this;
   }

   public JoinMetadata setOuter(boolean flag) {
      this.getInternal().setOuter(flag);
      return this;
   }

   public JoinMetadata setTable(String table) {
      this.getInternal().setTable(table);
      return this;
   }

   public JoinMetadata setUnique(boolean flag) {
      this.getInternal().setUnique(flag);
      return this;
   }
}
