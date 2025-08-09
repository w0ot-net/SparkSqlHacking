package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.PrimaryKeyMetadata;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.PrimaryKeyMetaData;

public class PrimaryKeyMetadataImpl extends AbstractMetadataImpl implements PrimaryKeyMetadata {
   public PrimaryKeyMetadataImpl(PrimaryKeyMetaData internal) {
      super(internal);
   }

   public PrimaryKeyMetaData getInternal() {
      return (PrimaryKeyMetaData)this.internalMD;
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

   public String getName() {
      return this.getInternal().getName();
   }

   public int getNumberOfColumns() {
      ColumnMetaData[] colmds = this.getInternal().getColumnMetaData();
      return colmds != null ? colmds.length : 0;
   }

   public ColumnMetadata newColumnMetadata() {
      ColumnMetaData internalColmd = this.getInternal().newColumnMetadata();
      ColumnMetadataImpl colmd = new ColumnMetadataImpl(internalColmd);
      colmd.parent = this;
      return colmd;
   }

   public PrimaryKeyMetadata setColumn(String name) {
      this.getInternal().setColumnName(name);
      return this;
   }

   public PrimaryKeyMetadata setName(String name) {
      this.getInternal().setName(name);
      return this;
   }
}
