package org.datanucleus.api.jdo.metadata;

import javax.jdo.annotations.ForeignKeyAction;
import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.FieldMetadata;
import javax.jdo.metadata.ForeignKeyMetadata;
import javax.jdo.metadata.MemberMetadata;
import javax.jdo.metadata.PropertyMetadata;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.PropertyMetaData;

public class ForeignKeyMetadataImpl extends AbstractMetadataImpl implements ForeignKeyMetadata {
   public ForeignKeyMetadataImpl(ForeignKeyMetaData internal) {
      super(internal);
   }

   public ForeignKeyMetaData getInternal() {
      return (ForeignKeyMetaData)this.internalMD;
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

   public Boolean getDeferred() {
      return this.getInternal().isDeferred();
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

   public String getName() {
      return this.getInternal().getName();
   }

   public int getNumberOfColumns() {
      ColumnMetaData[] colmds = this.getInternal().getColumnMetaData();
      return colmds != null ? colmds.length : 0;
   }

   public int getNumberOfMembers() {
      String[] internalMemberNames = this.getInternal().getMemberNames();
      return internalMemberNames != null ? internalMemberNames.length : 0;
   }

   public MemberMetadata[] getMembers() {
      String[] internalMemberNames = this.getInternal().getMemberNames();
      if (internalMemberNames == null) {
         return null;
      } else {
         MemberMetadataImpl[] mmds = new MemberMetadataImpl[internalMemberNames.length];

         for(int i = 0; i < mmds.length; ++i) {
            FieldMetaData fmd = new FieldMetaData(this.getInternal(), internalMemberNames[i]);
            mmds[i] = new FieldMetadataImpl(fmd);
            mmds[i].parent = this;
         }

         return mmds;
      }
   }

   public String getTable() {
      return this.getInternal().getTable();
   }

   public Boolean getUnique() {
      return this.getInternal().isUnique();
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

   public ColumnMetadata newColumnMetadata() {
      ColumnMetaData internalColmd = this.getInternal().newColumnMetaData();
      ColumnMetadataImpl colmd = new ColumnMetadataImpl(internalColmd);
      colmd.parent = this;
      return colmd;
   }

   public FieldMetadata newFieldMetadata(String name) {
      FieldMetaData internalFmd = new FieldMetaData(this.getInternal(), name);
      FieldMetadataImpl fmd = new FieldMetadataImpl(internalFmd);
      fmd.parent = this;
      return fmd;
   }

   public PropertyMetadata newPropertyMetadata(String name) {
      PropertyMetaData internalPmd = new PropertyMetaData(this.getInternal(), name);
      PropertyMetadataImpl pmd = new PropertyMetadataImpl(internalPmd);
      pmd.parent = this;
      return pmd;
   }

   public ForeignKeyMetadata setDeferred(boolean flag) {
      this.getInternal().setDeferred(flag);
      return this;
   }

   public ForeignKeyMetadata setDeleteAction(ForeignKeyAction fk) {
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

   public ForeignKeyMetadata setName(String name) {
      this.getInternal().setName(name);
      return this;
   }

   public ForeignKeyMetadata setTable(String name) {
      this.getInternal().setTable(name);
      return this;
   }

   public ForeignKeyMetadata setUnique(boolean flag) {
      this.getInternal().setUnique(flag);
      return this;
   }

   public ForeignKeyMetadata setUpdateAction(ForeignKeyAction fk) {
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
}
