package org.datanucleus.api.jdo.metadata;

import javax.jdo.AttributeConverter;
import javax.jdo.annotations.ForeignKeyAction;
import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.EmbeddedMetadata;
import javax.jdo.metadata.ForeignKeyMetadata;
import javax.jdo.metadata.IndexMetadata;
import javax.jdo.metadata.KeyMetadata;
import javax.jdo.metadata.UniqueMetadata;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.EmbeddedMetaData;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.KeyMetaData;
import org.datanucleus.metadata.UniqueMetaData;

public class KeyMetadataImpl extends AbstractMetadataImpl implements KeyMetadata {
   public KeyMetadataImpl(KeyMetaData internal) {
      super(internal);
   }

   public KeyMetaData getInternal() {
      return (KeyMetaData)this.internalMD;
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

   public int getNumberOfColumns() {
      ColumnMetaData[] colmds = this.getInternal().getColumnMetaData();
      return colmds != null ? colmds.length : 0;
   }

   public KeyMetadata setColumn(String name) {
      this.getInternal().setColumnName(name);
      return this;
   }

   public ColumnMetadata newColumnMetadata() {
      ColumnMetaData internalColmd = this.getInternal().newColumnMetaData();
      ColumnMetadataImpl colmd = new ColumnMetadataImpl(internalColmd);
      colmd.parent = this;
      return colmd;
   }

   public ForeignKeyAction getDeleteAction() {
      org.datanucleus.metadata.ForeignKeyAction fk = this.getInternal().getDeleteAction();
      if (fk == org.datanucleus.metadata.ForeignKeyAction.CASCADE) {
         return ForeignKeyAction.CASCADE;
      } else if (fk == org.datanucleus.metadata.ForeignKeyAction.DEFAULT) {
         return ForeignKeyAction.DEFAULT;
      } else if (fk == org.datanucleus.metadata.ForeignKeyAction.NONE) {
         return ForeignKeyAction.NONE;
      } else if (fk == org.datanucleus.metadata.ForeignKeyAction.NULL) {
         return ForeignKeyAction.NULL;
      } else {
         return fk == org.datanucleus.metadata.ForeignKeyAction.RESTRICT ? ForeignKeyAction.RESTRICT : ForeignKeyAction.UNSPECIFIED;
      }
   }

   public KeyMetadata setDeleteAction(ForeignKeyAction fk) {
      if (fk == ForeignKeyAction.CASCADE) {
         this.getInternal().setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.CASCADE);
      } else if (fk == ForeignKeyAction.DEFAULT) {
         this.getInternal().setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.DEFAULT);
      } else if (fk == ForeignKeyAction.NONE) {
         this.getInternal().setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.NONE);
      } else if (fk == ForeignKeyAction.NULL) {
         this.getInternal().setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.NULL);
      } else if (fk == ForeignKeyAction.RESTRICT) {
         this.getInternal().setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.RESTRICT);
      }

      return this;
   }

   public ForeignKeyAction getUpdateAction() {
      org.datanucleus.metadata.ForeignKeyAction fk = this.getInternal().getUpdateAction();
      if (fk == org.datanucleus.metadata.ForeignKeyAction.CASCADE) {
         return ForeignKeyAction.CASCADE;
      } else if (fk == org.datanucleus.metadata.ForeignKeyAction.DEFAULT) {
         return ForeignKeyAction.DEFAULT;
      } else if (fk == org.datanucleus.metadata.ForeignKeyAction.NONE) {
         return ForeignKeyAction.NONE;
      } else if (fk == org.datanucleus.metadata.ForeignKeyAction.NULL) {
         return ForeignKeyAction.NULL;
      } else {
         return fk == org.datanucleus.metadata.ForeignKeyAction.RESTRICT ? ForeignKeyAction.RESTRICT : ForeignKeyAction.UNSPECIFIED;
      }
   }

   public KeyMetadata setUpdateAction(ForeignKeyAction fk) {
      if (fk == ForeignKeyAction.CASCADE) {
         this.getInternal().setUpdateAction(org.datanucleus.metadata.ForeignKeyAction.CASCADE);
      } else if (fk == ForeignKeyAction.DEFAULT) {
         this.getInternal().setUpdateAction(org.datanucleus.metadata.ForeignKeyAction.DEFAULT);
      } else if (fk == ForeignKeyAction.NONE) {
         this.getInternal().setUpdateAction(org.datanucleus.metadata.ForeignKeyAction.NONE);
      } else if (fk == ForeignKeyAction.NULL) {
         this.getInternal().setUpdateAction(org.datanucleus.metadata.ForeignKeyAction.NULL);
      } else if (fk == ForeignKeyAction.RESTRICT) {
         this.getInternal().setUpdateAction(org.datanucleus.metadata.ForeignKeyAction.RESTRICT);
      }

      return this;
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

   public ForeignKeyMetadata newForeignKeyMetadata() {
      ForeignKeyMetaData internalFkmd = this.getInternal().newForeignKeyMetaData();
      ForeignKeyMetadataImpl fkmd = new ForeignKeyMetadataImpl(internalFkmd);
      fkmd.parent = this;
      return fkmd;
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

   public IndexMetadata newIndexMetadata() {
      IndexMetaData internalIdxmd = this.getInternal().newIndexMetaData();
      IndexMetadataImpl idxmd = new IndexMetadataImpl(internalIdxmd);
      idxmd.parent = this;
      return idxmd;
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

   public UniqueMetadata newUniqueMetadata() {
      UniqueMetaData internalUnimd = this.getInternal().newUniqueMetaData();
      UniqueMetadataImpl unimd = new UniqueMetadataImpl(internalUnimd);
      unimd.parent = this;
      return unimd;
   }

   public EmbeddedMetadata getEmbeddedMetadata() {
      EmbeddedMetaData internalEmbmd = this.getInternal().getEmbeddedMetaData();
      if (internalEmbmd == null) {
         return null;
      } else {
         EmbeddedMetadataImpl embmd = new EmbeddedMetadataImpl(internalEmbmd);
         embmd.parent = this;
         return embmd;
      }
   }

   public EmbeddedMetadata newEmbeddedMetadata() {
      EmbeddedMetaData internalEmbmd = this.getInternal().newEmbeddedMetaData();
      EmbeddedMetadataImpl embmd = new EmbeddedMetadataImpl(internalEmbmd);
      embmd.parent = this;
      return embmd;
   }

   public String getTable() {
      return this.getInternal().getTable();
   }

   public KeyMetadata setTable(String name) {
      this.getInternal().setTable(name);
      return this;
   }

   public AttributeConverter getConverter() {
      return null;
   }

   public KeyMetadata setConverter(AttributeConverter conv) {
      return null;
   }

   public Boolean getUseDefaultConversion() {
      return null;
   }

   public KeyMetadata setUseDefaultConversion(Boolean disable) {
      return null;
   }
}
