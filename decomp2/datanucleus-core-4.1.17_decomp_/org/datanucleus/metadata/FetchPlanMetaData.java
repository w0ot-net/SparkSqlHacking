package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.util.StringUtils;

public class FetchPlanMetaData extends MetaData {
   private static final long serialVersionUID = 4984221717334769574L;
   String name;
   protected int maxFetchDepth = -1;
   protected int fetchSize = -1;
   protected List fetchGroups = new ArrayList();

   public FetchPlanMetaData(String name) {
      this.name = name;
   }

   public final String getName() {
      return this.name;
   }

   public final int getMaxFetchDepth() {
      return this.maxFetchDepth;
   }

   public FetchPlanMetaData setMaxFetchDepth(int maxFetchDepth) {
      this.maxFetchDepth = maxFetchDepth;
      return this;
   }

   public FetchPlanMetaData setMaxFetchDepth(String maxFetchDepth) {
      if (StringUtils.isWhitespace(maxFetchDepth)) {
         return this;
      } else {
         try {
            int value = Integer.parseInt(maxFetchDepth);
            this.maxFetchDepth = value;
         } catch (NumberFormatException var3) {
         }

         return this;
      }
   }

   public final int getFetchSize() {
      return this.fetchSize;
   }

   public int getNumberOfFetchGroups() {
      return this.fetchGroups.size();
   }

   public FetchPlanMetaData setFetchSize(int fetchSize) {
      this.fetchSize = fetchSize;
      return this;
   }

   public FetchPlanMetaData setFetchSize(String fetchSize) {
      if (StringUtils.isWhitespace(fetchSize)) {
         return this;
      } else {
         try {
            int value = Integer.parseInt(fetchSize);
            this.fetchSize = value;
         } catch (NumberFormatException var3) {
         }

         return this;
      }
   }

   public final FetchGroupMetaData[] getFetchGroupMetaData() {
      return (FetchGroupMetaData[])this.fetchGroups.toArray(new FetchGroupMetaData[this.fetchGroups.size()]);
   }

   public void addFetchGroup(FetchGroupMetaData fgmd) {
      this.fetchGroups.add(fgmd);
      fgmd.parent = this;
   }

   public FetchGroupMetaData newFetchGroupMetaData(String name) {
      FetchGroupMetaData fgmd = new FetchGroupMetaData(name);
      this.addFetchGroup(fgmd);
      return fgmd;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<fetch-plan name=\"" + this.name + "\" max-fetch-depth=\"" + this.maxFetchDepth + "\" fetch-size=\"" + this.fetchSize + "\">\n");

      for(FetchGroupMetaData fgmd : this.fetchGroups) {
         sb.append(fgmd.toString(prefix + indent, indent));
      }

      sb.append(prefix + "</fetch-plan>\n");
      return sb.toString();
   }
}
