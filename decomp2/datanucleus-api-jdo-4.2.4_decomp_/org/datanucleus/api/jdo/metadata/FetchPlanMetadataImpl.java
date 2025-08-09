package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.FetchGroupMetadata;
import javax.jdo.metadata.FetchPlanMetadata;
import org.datanucleus.metadata.FetchGroupMetaData;
import org.datanucleus.metadata.FetchPlanMetaData;

public class FetchPlanMetadataImpl extends AbstractMetadataImpl implements FetchPlanMetadata {
   public FetchPlanMetadataImpl(FetchPlanMetaData fpmd) {
      super(fpmd);
   }

   public FetchPlanMetaData getInternal() {
      return (FetchPlanMetaData)this.internalMD;
   }

   public FetchGroupMetadata[] getFetchGroups() {
      FetchGroupMetaData[] baseData = this.getInternal().getFetchGroupMetaData();
      if (baseData == null) {
         return null;
      } else {
         FetchGroupMetadataImpl[] fgs = new FetchGroupMetadataImpl[baseData.length];

         for(int i = 0; i < fgs.length; ++i) {
            fgs[i] = new FetchGroupMetadataImpl(baseData[i]);
            fgs[i].parent = this;
         }

         return fgs;
      }
   }

   public int getNumberOfFetchGroups() {
      return this.getInternal().getNumberOfFetchGroups();
   }

   public FetchGroupMetadata newFetchGroupMetadata(String name) {
      FetchGroupMetaData internalFgmd = this.getInternal().newFetchGroupMetaData(name);
      FetchGroupMetadataImpl fgmd = new FetchGroupMetadataImpl(internalFgmd);
      fgmd.parent = this;
      return fgmd;
   }

   public String getName() {
      return this.getInternal().getName();
   }

   public int getFetchSize() {
      return this.getInternal().getFetchSize();
   }

   public FetchPlanMetadata setFetchSize(int size) {
      this.getInternal().setFetchSize(size);
      return this;
   }

   public int getMaxFetchDepth() {
      return this.getInternal().getMaxFetchDepth();
   }

   public FetchPlanMetadata setMaxFetchDepth(int depth) {
      this.getInternal().setMaxFetchDepth(depth);
      return this;
   }
}
