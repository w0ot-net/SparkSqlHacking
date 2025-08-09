package org.datanucleus.api.jdo.metadata;

import java.util.Set;
import javax.jdo.metadata.FetchGroupMetadata;
import javax.jdo.metadata.FieldMetadata;
import javax.jdo.metadata.MemberMetadata;
import javax.jdo.metadata.PropertyMetadata;
import org.datanucleus.metadata.FetchGroupMemberMetaData;
import org.datanucleus.metadata.FetchGroupMetaData;

public class FetchGroupMetadataImpl extends AbstractMetadataImpl implements FetchGroupMetadata {
   public FetchGroupMetadataImpl(FetchGroupMetaData fgmd) {
      super(fgmd);
   }

   public FetchGroupMetaData getInternal() {
      return (FetchGroupMetaData)this.internalMD;
   }

   public String getName() {
      return this.getInternal().getName();
   }

   public MemberMetadata[] getMembers() {
      Set<FetchGroupMemberMetaData> internalMmds = this.getInternal().getMembers();
      if (internalMmds == null) {
         return null;
      } else {
         MemberMetadataImpl[] mmds = new MemberMetadataImpl[internalMmds.size()];
         int i = 0;

         for(FetchGroupMemberMetaData fgmmd : internalMmds) {
            if (fgmmd.isProperty()) {
               mmds[i] = new PropertyMetadataImpl(fgmmd);
            } else {
               mmds[i] = new FieldMetadataImpl(fgmmd);
            }

            mmds[i].parent = this;
            ++i;
         }

         return mmds;
      }
   }

   public int getNumberOfMembers() {
      return this.getInternal().getNumberOfMembers();
   }

   public FieldMetadata newFieldMetadata(String name) {
      FetchGroupMemberMetaData internalFgMmd = this.getInternal().newMemberMetaData(name);
      FieldMetadataImpl fmd = new FieldMetadataImpl(internalFgMmd);
      fmd.parent = this;
      return fmd;
   }

   public PropertyMetadata newPropertyMetadata(String name) {
      FetchGroupMemberMetaData internalFgMmd = this.getInternal().newMemberMetaData(name);
      PropertyMetadataImpl pmd = new PropertyMetadataImpl(internalFgMmd);
      internalFgMmd.setProperty();
      pmd.parent = this;
      return pmd;
   }

   public Boolean getPostLoad() {
      return this.getInternal().getPostLoad();
   }

   public FetchGroupMetadata setPostLoad(boolean load) {
      this.getInternal().setPostLoad(load);
      return this;
   }
}
