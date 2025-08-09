package org.datanucleus.api.jdo.metadata;

import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.DiscriminatorMetadata;
import javax.jdo.metadata.IndexMetadata;
import javax.jdo.metadata.Indexed;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.IndexedValue;

public class DiscriminatorMetadataImpl extends AbstractMetadataImpl implements DiscriminatorMetadata {
   public DiscriminatorMetadataImpl(DiscriminatorMetaData internal) {
      super(internal);
   }

   public DiscriminatorMetaData getInternal() {
      return (DiscriminatorMetaData)this.internalMD;
   }

   public String getColumn() {
      return this.getInternal().getColumnName();
   }

   public ColumnMetadata[] getColumns() {
      ColumnMetaData internalColmd = this.getInternal().getColumnMetaData();
      if (internalColmd == null) {
         return null;
      } else {
         ColumnMetadataImpl[] colmds = new ColumnMetadataImpl[1];
         colmds[0] = new ColumnMetadataImpl(internalColmd);
         colmds[0].parent = this;
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

   public Indexed getIndexed() {
      IndexedValue val = this.getInternal().getIndexed();
      if (val == IndexedValue.FALSE) {
         return Indexed.FALSE;
      } else if (val == IndexedValue.TRUE) {
         return Indexed.TRUE;
      } else {
         return val == IndexedValue.UNIQUE ? Indexed.UNIQUE : Indexed.UNSPECIFIED;
      }
   }

   public int getNumberOfColumns() {
      return 1;
   }

   public DiscriminatorStrategy getStrategy() {
      org.datanucleus.metadata.DiscriminatorStrategy str = this.getInternal().getStrategy();
      if (str == org.datanucleus.metadata.DiscriminatorStrategy.CLASS_NAME) {
         return DiscriminatorStrategy.CLASS_NAME;
      } else if (str == org.datanucleus.metadata.DiscriminatorStrategy.VALUE_MAP) {
         return DiscriminatorStrategy.VALUE_MAP;
      } else {
         return str == org.datanucleus.metadata.DiscriminatorStrategy.NONE ? DiscriminatorStrategy.NONE : DiscriminatorStrategy.UNSPECIFIED;
      }
   }

   public String getValue() {
      return this.getInternal().getValue();
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

   public DiscriminatorMetadata setColumn(String name) {
      this.getInternal().setColumnName(name);
      return this;
   }

   public DiscriminatorMetadata setIndexed(Indexed idx) {
      if (idx == Indexed.FALSE) {
         this.getInternal().setIndexed(IndexedValue.FALSE);
      } else if (idx == Indexed.TRUE) {
         this.getInternal().setIndexed(IndexedValue.TRUE);
      } else if (idx == Indexed.UNIQUE) {
         this.getInternal().setIndexed(IndexedValue.UNIQUE);
      }

      return this;
   }

   public DiscriminatorMetadata setStrategy(DiscriminatorStrategy str) {
      if (str == DiscriminatorStrategy.CLASS_NAME) {
         this.getInternal().setStrategy(org.datanucleus.metadata.DiscriminatorStrategy.CLASS_NAME);
      } else if (str == DiscriminatorStrategy.VALUE_MAP) {
         this.getInternal().setStrategy(org.datanucleus.metadata.DiscriminatorStrategy.VALUE_MAP);
      } else if (str == DiscriminatorStrategy.NONE) {
         this.getInternal().setStrategy(org.datanucleus.metadata.DiscriminatorStrategy.NONE);
      }

      return this;
   }

   public DiscriminatorMetadata setValue(String val) {
      this.getInternal().setValue(val);
      return this;
   }
}
