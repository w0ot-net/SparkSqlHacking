package org.datanucleus.api.jdo.metadata;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.DatastoreIdentityMetadata;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.IdentityMetaData;
import org.datanucleus.metadata.IdentityStrategy;

public class DatastoreIdentityMetadataImpl extends AbstractMetadataImpl implements DatastoreIdentityMetadata {
   public DatastoreIdentityMetadataImpl(IdentityMetaData idmd) {
      super(idmd);
   }

   public IdentityMetaData getInternal() {
      return (IdentityMetaData)this.internalMD;
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

   public String getCustomStrategy() {
      IdentityStrategy strategy = this.getInternal().getValueStrategy();
      return strategy != IdentityStrategy.IDENTITY && strategy != IdentityStrategy.INCREMENT && strategy != IdentityStrategy.NATIVE && strategy != IdentityStrategy.SEQUENCE && strategy != IdentityStrategy.UUIDHEX && strategy != IdentityStrategy.UUIDSTRING && strategy != null ? strategy.toString() : null;
   }

   public int getNumberOfColumns() {
      ColumnMetaData colmds = this.getInternal().getColumnMetaData();
      return colmds != null ? 1 : 0;
   }

   public String getSequence() {
      return this.getInternal().getSequence();
   }

   public IdGeneratorStrategy getStrategy() {
      IdentityStrategy strategy = this.getInternal().getValueStrategy();
      if (strategy == IdentityStrategy.IDENTITY) {
         return IdGeneratorStrategy.IDENTITY;
      } else if (strategy == IdentityStrategy.INCREMENT) {
         return IdGeneratorStrategy.INCREMENT;
      } else if (strategy == IdentityStrategy.NATIVE) {
         return IdGeneratorStrategy.NATIVE;
      } else if (strategy == IdentityStrategy.SEQUENCE) {
         return IdGeneratorStrategy.SEQUENCE;
      } else if (strategy == IdentityStrategy.UUIDHEX) {
         return IdGeneratorStrategy.UUIDHEX;
      } else {
         return strategy == IdentityStrategy.UUIDSTRING ? IdGeneratorStrategy.UUIDSTRING : IdGeneratorStrategy.UNSPECIFIED;
      }
   }

   public ColumnMetadata newColumnMetadata() {
      ColumnMetaData internalColmd = this.getInternal().newColumnMetaData();
      ColumnMetadataImpl colmd = new ColumnMetadataImpl(internalColmd);
      colmd.parent = this;
      return colmd;
   }

   public DatastoreIdentityMetadata setColumn(String name) {
      this.getInternal().setColumnName(name);
      return this;
   }

   public DatastoreIdentityMetadata setCustomStrategy(String strategy) {
      this.getInternal().setValueStrategy(IdentityStrategy.getIdentityStrategy(strategy));
      return this;
   }

   public DatastoreIdentityMetadata setSequence(String seq) {
      this.getInternal().setSequence(seq);
      return this;
   }

   public DatastoreIdentityMetadata setStrategy(IdGeneratorStrategy strategy) {
      if (strategy == IdGeneratorStrategy.IDENTITY) {
         this.getInternal().setValueStrategy(IdentityStrategy.IDENTITY);
      } else if (strategy == IdGeneratorStrategy.INCREMENT) {
         this.getInternal().setValueStrategy(IdentityStrategy.INCREMENT);
      } else if (strategy == IdGeneratorStrategy.NATIVE) {
         this.getInternal().setValueStrategy(IdentityStrategy.NATIVE);
      } else if (strategy == IdGeneratorStrategy.SEQUENCE) {
         this.getInternal().setValueStrategy(IdentityStrategy.SEQUENCE);
      } else if (strategy == IdGeneratorStrategy.UUIDHEX) {
         this.getInternal().setValueStrategy(IdentityStrategy.UUIDHEX);
      } else if (strategy == IdGeneratorStrategy.UUIDSTRING) {
         this.getInternal().setValueStrategy(IdentityStrategy.UUIDSTRING);
      }

      return this;
   }
}
