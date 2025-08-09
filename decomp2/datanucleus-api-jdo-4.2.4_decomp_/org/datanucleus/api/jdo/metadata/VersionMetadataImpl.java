package org.datanucleus.api.jdo.metadata;

import javax.jdo.annotations.VersionStrategy;
import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.IndexMetadata;
import javax.jdo.metadata.Indexed;
import javax.jdo.metadata.VersionMetadata;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.IndexedValue;
import org.datanucleus.metadata.VersionMetaData;

public class VersionMetadataImpl extends AbstractMetadataImpl implements VersionMetadata {
   public VersionMetadataImpl(VersionMetaData internal) {
      super(internal);
   }

   public VersionMetaData getInternal() {
      return (VersionMetaData)this.internalMD;
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

         for(int i = 0; i < colmds.length; ++i) {
            colmds[i] = new ColumnMetadataImpl(internalColmd);
            colmds[i].parent = this;
         }

         return colmds;
      }
   }

   public IndexMetadata getIndexMetadata() {
      IndexMetaData internalIdxmd = this.getInternal().getIndexMetaData();
      IndexMetadataImpl idxmd = new IndexMetadataImpl(internalIdxmd);
      idxmd.parent = this;
      return idxmd;
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
      ColumnMetaData colmd = this.getInternal().getColumnMetaData();
      return colmd != null ? 1 : 0;
   }

   public VersionStrategy getStrategy() {
      org.datanucleus.metadata.VersionStrategy strategy = this.getInternal().getVersionStrategy();
      if (strategy == org.datanucleus.metadata.VersionStrategy.DATE_TIME) {
         return VersionStrategy.DATE_TIME;
      } else if (strategy == org.datanucleus.metadata.VersionStrategy.VERSION_NUMBER) {
         return VersionStrategy.VERSION_NUMBER;
      } else if (strategy == org.datanucleus.metadata.VersionStrategy.STATE_IMAGE) {
         return VersionStrategy.STATE_IMAGE;
      } else {
         return strategy == org.datanucleus.metadata.VersionStrategy.NONE ? VersionStrategy.NONE : VersionStrategy.UNSPECIFIED;
      }
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

   public VersionMetadata setColumn(String name) {
      this.getInternal().setColumnName(name);
      return this;
   }

   public VersionMetadata setIndexed(Indexed idx) {
      if (idx == Indexed.FALSE) {
         this.getInternal().setIndexed(IndexedValue.FALSE);
      } else if (idx == Indexed.TRUE) {
         this.getInternal().setIndexed(IndexedValue.TRUE);
      } else if (idx == Indexed.UNIQUE) {
         this.getInternal().setIndexed(IndexedValue.UNIQUE);
      }

      return this;
   }

   public VersionMetadata setStrategy(VersionStrategy str) {
      if (str == VersionStrategy.DATE_TIME) {
         this.getInternal().setStrategy(org.datanucleus.metadata.VersionStrategy.DATE_TIME);
      } else if (str == VersionStrategy.VERSION_NUMBER) {
         this.getInternal().setStrategy(org.datanucleus.metadata.VersionStrategy.VERSION_NUMBER);
      } else if (str == VersionStrategy.STATE_IMAGE) {
         this.getInternal().setStrategy(org.datanucleus.metadata.VersionStrategy.STATE_IMAGE);
      } else if (str == VersionStrategy.NONE) {
         this.getInternal().setStrategy(org.datanucleus.metadata.VersionStrategy.NONE);
      }

      return this;
   }
}
