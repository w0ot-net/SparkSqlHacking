package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.DiscriminatorMetadata;
import javax.jdo.metadata.InheritanceMetadata;
import javax.jdo.metadata.JoinMetadata;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.InheritanceMetaData;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.metadata.JoinMetaData;

public class InheritanceMetadataImpl extends AbstractMetadataImpl implements InheritanceMetadata {
   public InheritanceMetadataImpl(InheritanceMetaData internal) {
      super(internal);
   }

   public InheritanceMetaData getInternal() {
      return (InheritanceMetaData)this.internalMD;
   }

   public String getCustomStrategy() {
      InheritanceStrategy str = this.getInternal().getStrategy();
      return str != InheritanceStrategy.NEW_TABLE && str != InheritanceStrategy.SUBCLASS_TABLE && str != InheritanceStrategy.SUPERCLASS_TABLE && str != null ? str.toString() : null;
   }

   public DiscriminatorMetadata getDiscriminatorMetadata() {
      DiscriminatorMetaData internalDismd = this.getInternal().getDiscriminatorMetaData();
      if (internalDismd == null) {
         return null;
      } else {
         DiscriminatorMetadataImpl dismd = new DiscriminatorMetadataImpl(internalDismd);
         dismd.parent = this;
         return dismd;
      }
   }

   public JoinMetadata getJoinMetadata() {
      JoinMetaData internalJoinmd = this.getInternal().getJoinMetaData();
      if (internalJoinmd == null) {
         return null;
      } else {
         JoinMetadataImpl joinmd = new JoinMetadataImpl(internalJoinmd);
         joinmd.parent = this;
         return joinmd;
      }
   }

   public javax.jdo.annotations.InheritanceStrategy getStrategy() {
      InheritanceStrategy str = this.getInternal().getStrategy();
      if (str == InheritanceStrategy.NEW_TABLE) {
         return javax.jdo.annotations.InheritanceStrategy.NEW_TABLE;
      } else if (str == InheritanceStrategy.SUBCLASS_TABLE) {
         return javax.jdo.annotations.InheritanceStrategy.SUBCLASS_TABLE;
      } else {
         return str == InheritanceStrategy.SUPERCLASS_TABLE ? javax.jdo.annotations.InheritanceStrategy.SUPERCLASS_TABLE : javax.jdo.annotations.InheritanceStrategy.UNSPECIFIED;
      }
   }

   public DiscriminatorMetadata newDiscriminatorMetadata() {
      DiscriminatorMetaData internalDismd = this.getInternal().newDiscriminatorMetadata();
      DiscriminatorMetadataImpl dismd = new DiscriminatorMetadataImpl(internalDismd);
      dismd.parent = this;
      return dismd;
   }

   public JoinMetadata newJoinMetadata() {
      JoinMetaData internalJoinmd = this.getInternal().newJoinMetadata();
      JoinMetadataImpl joinmd = new JoinMetadataImpl(internalJoinmd);
      joinmd.parent = this;
      return joinmd;
   }

   public InheritanceMetadata setCustomStrategy(String str) {
      this.getInternal().setStrategy(str);
      return this;
   }

   public InheritanceMetadata setStrategy(javax.jdo.annotations.InheritanceStrategy str) {
      if (str == javax.jdo.annotations.InheritanceStrategy.NEW_TABLE) {
         this.getInternal().setStrategy(InheritanceStrategy.NEW_TABLE);
      } else if (str == javax.jdo.annotations.InheritanceStrategy.SUBCLASS_TABLE) {
         this.getInternal().setStrategy(InheritanceStrategy.SUBCLASS_TABLE);
      } else if (str == javax.jdo.annotations.InheritanceStrategy.SUPERCLASS_TABLE) {
         this.getInternal().setStrategy(InheritanceStrategy.SUPERCLASS_TABLE);
      }

      return this;
   }
}
