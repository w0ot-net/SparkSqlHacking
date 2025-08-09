package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.FieldMetadata;
import javax.jdo.metadata.MemberMetadata;
import javax.jdo.metadata.PropertyMetadata;
import javax.jdo.metadata.UniqueMetadata;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.PropertyMetaData;
import org.datanucleus.metadata.UniqueMetaData;

public class UniqueMetadataImpl extends AbstractMetadataImpl implements UniqueMetadata {
   public UniqueMetadataImpl(UniqueMetaData internal) {
      super(internal);
   }

   public UniqueMetaData getInternal() {
      return (UniqueMetaData)this.internalMD;
   }

   public ColumnMetadata[] getColumns() {
      String[] internalColumnNames = this.getInternal().getColumnNames();
      if (internalColumnNames == null) {
         return null;
      } else {
         ColumnMetadataImpl[] colmds = new ColumnMetadataImpl[internalColumnNames.length];

         for(int i = 0; i < colmds.length; ++i) {
            ColumnMetaData internalColmd = new ColumnMetaData();
            internalColmd.setName(internalColumnNames[i]);
            colmds[i] = new ColumnMetadataImpl(internalColmd);
            colmds[i].parent = this;
         }

         return colmds;
      }
   }

   public Boolean getDeferred() {
      return this.getInternal().isDeferred();
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

   public String getName() {
      return this.getInternal().getName();
   }

   public int getNumberOfColumns() {
      return this.getInternal().getNumberOfColumns();
   }

   public int getNumberOfMembers() {
      return this.getInternal().getNumberOfMembers();
   }

   public String getTable() {
      return this.getInternal().getTable();
   }

   public ColumnMetadata newColumnMetadata() {
      ColumnMetaData internalColmd = new ColumnMetaData();
      internalColmd.setParent(this.getInternal());
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

   public UniqueMetadata setDeferred(boolean flag) {
      this.getInternal().setDeferred(flag);
      return this;
   }

   public UniqueMetadata setName(String name) {
      this.getInternal().setName(name);
      return this;
   }

   public UniqueMetadata setTable(String name) {
      this.getInternal().setTable(name);
      return this;
   }
}
