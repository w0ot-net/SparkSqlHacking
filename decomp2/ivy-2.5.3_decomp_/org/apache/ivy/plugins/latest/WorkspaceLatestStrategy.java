package org.apache.ivy.plugins.latest;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceLatestStrategy extends AbstractLatestStrategy {
   private LatestStrategy delegate;

   public WorkspaceLatestStrategy(LatestStrategy delegate) {
      this.delegate = delegate;
      this.setName("workspace-" + delegate.getName());
   }

   public List sort(ArtifactInfo[] infos) {
      List<ArtifactInfo> head = new ArrayList();
      List<ArtifactInfo> tail = new ArrayList();

      for(ArtifactInfo ai : this.delegate.sort(infos)) {
         String rev = ai.getRevision();
         boolean latestRev = rev.startsWith("latest") || rev.startsWith("working");
         if (latestRev) {
            head.add(ai);
         } else {
            tail.add(ai);
         }
      }

      head.addAll(tail);
      return head;
   }
}
