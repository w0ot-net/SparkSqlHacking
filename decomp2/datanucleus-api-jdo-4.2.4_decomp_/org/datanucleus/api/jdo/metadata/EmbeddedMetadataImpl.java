package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.DiscriminatorMetadata;
import javax.jdo.metadata.EmbeddedMetadata;
import javax.jdo.metadata.FieldMetadata;
import javax.jdo.metadata.MemberMetadata;
import javax.jdo.metadata.PropertyMetadata;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.EmbeddedMetaData;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.PropertyMetaData;

public class EmbeddedMetadataImpl extends AbstractMetadataImpl implements EmbeddedMetadata {
   public EmbeddedMetadataImpl(EmbeddedMetaData internal) {
      super(internal);
   }

   public EmbeddedMetaData getInternal() {
      return (EmbeddedMetaData)this.internalMD;
   }

   public MemberMetadata[] getMembers() {
      AbstractMemberMetaData[] internalMmds = this.getInternal().getMemberMetaData();
      if (internalMmds == null) {
         return null;
      } else {
         MemberMetadataImpl[] mmds = new MemberMetadataImpl[internalMmds.length];

         for(int i = 0; i < mmds.length; ++i) {
            if (internalMmds[i] instanceof FieldMetaData) {
               mmds[i] = new FieldMetadataImpl((FieldMetaData)internalMmds[i]);
            } else {
               mmds[i] = new PropertyMetadataImpl((PropertyMetaData)internalMmds[i]);
            }
         }

         return mmds;
      }
   }

   public String getNullIndicatorColumn() {
      return this.getInternal().getNullIndicatorColumn();
   }

   public String getNullIndicatorValue() {
      return this.getInternal().getNullIndicatorValue();
   }

   public int getNumberOfMembers() {
      AbstractMemberMetaData[] mmds = this.getInternal().getMemberMetaData();
      return mmds != null ? mmds.length : 0;
   }

   public String getOwnerMember() {
      return this.getInternal().getOwnerMember();
   }

   public FieldMetadata newFieldMetadata(String name) {
      FieldMetaData internalFmd = this.getInternal().newFieldMetaData(name);
      FieldMetadataImpl fmd = new FieldMetadataImpl(internalFmd);
      fmd.parent = this;
      return fmd;
   }

   public PropertyMetadata newPropertyMetadata(String name) {
      PropertyMetaData internalPmd = this.getInternal().newPropertyMetaData(name);
      PropertyMetadataImpl pmd = new PropertyMetadataImpl(internalPmd);
      pmd.parent = this;
      return pmd;
   }

   public EmbeddedMetadata setNullIndicatorColumn(String col) {
      this.getInternal().setNullIndicatorColumn(col);
      return this;
   }

   public EmbeddedMetadata setNullIndicatorValue(String value) {
      this.getInternal().setNullIndicatorValue(value);
      return this;
   }

   public EmbeddedMetadata setOwnerMember(String member) {
      this.getInternal().setOwnerMember(member);
      return this;
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

   public DiscriminatorMetadata newDiscriminatorMetadata() {
      DiscriminatorMetaData internalDismd = this.getInternal().newDiscriminatorMetadata();
      DiscriminatorMetadataImpl dismd = new DiscriminatorMetadataImpl(internalDismd);
      dismd.parent = this;
      return dismd;
   }
}
