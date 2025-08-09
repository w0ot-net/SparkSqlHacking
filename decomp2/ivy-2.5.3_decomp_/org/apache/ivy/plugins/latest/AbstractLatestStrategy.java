package org.apache.ivy.plugins.latest;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractLatestStrategy implements LatestStrategy {
   private String name;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public ArtifactInfo findLatest(ArtifactInfo[] infos, Date date) {
      List<ArtifactInfo> l = this.sort(infos);
      ListIterator<ArtifactInfo> iter = l.listIterator(l.size());

      while(iter.hasPrevious()) {
         ArtifactInfo info = (ArtifactInfo)iter.previous();
         if (date == null || info.getLastModified() < date.getTime()) {
            return info;
         }
      }

      return null;
   }
}
