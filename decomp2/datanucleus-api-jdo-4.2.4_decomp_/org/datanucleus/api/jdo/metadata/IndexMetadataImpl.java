package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.FieldMetadata;
import javax.jdo.metadata.IndexMetadata;
import javax.jdo.metadata.MemberMetadata;
import javax.jdo.metadata.PropertyMetadata;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.PropertyMetaData;

public class IndexMetadataImpl extends AbstractMetadataImpl implements IndexMetadata {
   public IndexMetadataImpl(IndexMetaData internal) {
      super(internal);
   }

   public IndexMetaData getInternal() {
      return (IndexMetaData)this.internalMD;
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

   public boolean getUnique() {
      return this.getInternal().isUnique();
   }

   public ColumnMetadata newColumn() {
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

   public IndexMetadata setName(String name) {
      this.getInternal().setName(name);
      return this;
   }

   public IndexMetadata setTable(String name) {
      this.getInternal().setTable(name);
      return this;
   }

   public IndexMetadata setUnique(boolean flag) {
      this.getInternal().setUnique(flag);
      return this;
   }
}
