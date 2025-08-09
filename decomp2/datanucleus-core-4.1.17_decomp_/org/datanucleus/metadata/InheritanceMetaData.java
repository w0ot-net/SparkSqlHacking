package org.datanucleus.metadata;

import org.datanucleus.ClassLoaderResolver;

public class InheritanceMetaData extends MetaData {
   private static final long serialVersionUID = -3645685751605920718L;
   protected InheritanceStrategy strategy = null;
   protected JoinMetaData joinMetaData;
   protected DiscriminatorMetaData discriminatorMetaData;
   protected String strategyForTree = null;

   public void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.joinMetaData != null) {
         this.joinMetaData.initialise(clr, mmgr);
      }

      if (this.discriminatorMetaData != null) {
         this.discriminatorMetaData.initialise(clr, mmgr);
      }

      this.setInitialised();
   }

   public InheritanceMetaData setStrategyForTree(String strategy) {
      this.strategyForTree = strategy;
      return this;
   }

   public String getStrategyForTree() {
      return this.strategyForTree;
   }

   public InheritanceStrategy getStrategy() {
      return this.strategy;
   }

   public InheritanceMetaData setStrategy(InheritanceStrategy strategy) {
      this.strategy = strategy;
      return this;
   }

   public InheritanceMetaData setStrategy(String strategy) {
      this.strategy = InheritanceStrategy.getInheritanceStrategy(strategy);
      return this;
   }

   public JoinMetaData getJoinMetaData() {
      return this.joinMetaData;
   }

   public void setJoinMetaData(JoinMetaData joinMetaData) {
      this.joinMetaData = joinMetaData;
      if (this.joinMetaData != null) {
         this.joinMetaData.parent = this;
      }

   }

   public JoinMetaData newJoinMetadata() {
      JoinMetaData joinmd = new JoinMetaData();
      this.setJoinMetaData(joinmd);
      return joinmd;
   }

   public DiscriminatorMetaData getDiscriminatorMetaData() {
      return this.discriminatorMetaData;
   }

   public void setDiscriminatorMetaData(DiscriminatorMetaData discriminatorMetaData) {
      this.discriminatorMetaData = discriminatorMetaData;
      this.discriminatorMetaData.parent = this;
   }

   public DiscriminatorMetaData newDiscriminatorMetadata() {
      DiscriminatorMetaData dismd = new DiscriminatorMetaData();
      this.setDiscriminatorMetaData(dismd);
      return dismd;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<inheritance strategy=\"" + this.strategy + "\">\n");
      if (this.joinMetaData != null) {
         sb.append(this.joinMetaData.toString(prefix + indent, indent));
      }

      if (this.discriminatorMetaData != null) {
         sb.append(this.discriminatorMetaData.toString(prefix + indent, indent));
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</inheritance>\n");
      return sb.toString();
   }
}
