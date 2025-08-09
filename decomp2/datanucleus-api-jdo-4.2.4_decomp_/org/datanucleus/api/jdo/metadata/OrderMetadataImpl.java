package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.IndexMetadata;
import javax.jdo.metadata.OrderMetadata;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.OrderMetaData;

public class OrderMetadataImpl extends AbstractMetadataImpl implements OrderMetadata {
   public OrderMetadataImpl(OrderMetaData internal) {
      super(internal);
   }

   public OrderMetaData getInternal() {
      return (OrderMetaData)this.internalMD;
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

   public String getMappedBy() {
      return this.getInternal().getMappedBy();
   }

   public int getNumberOfColumns() {
      ColumnMetaData[] colmds = this.getInternal().getColumnMetaData();
      return colmds != null ? colmds.length : 0;
   }

   public ColumnMetadata newColumnMetadata() {
      ColumnMetaData internalColmd = this.getInternal().newColumnMetaData();
      ColumnMetadataImpl colmd = new ColumnMetadataImpl(internalColmd);
      colmd.parent = this;
      return colmd;
   }

   public IndexMetadata newIndexMetadata() {
      IndexMetaData internalIdxmd = this.getInternal().newIndexMetaData();
      IndexMetadataImpl idxmd = new IndexMetadataImpl(internalIdxmd);
      idxmd.parent = this;
      return idxmd;
   }

   public OrderMetadata setColumn(String name) {
      this.getInternal().setColumnName(name);
      return this;
   }

   public OrderMetadata setMappedBy(String mappedBy) {
      this.getInternal().setMappedBy(mappedBy);
      return this;
   }
}
