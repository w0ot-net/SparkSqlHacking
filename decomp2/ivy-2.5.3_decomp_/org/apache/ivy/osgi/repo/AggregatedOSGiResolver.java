package org.apache.ivy.osgi.repo;

import java.util.ArrayList;
import java.util.List;

public class AggregatedOSGiResolver extends AbstractOSGiResolver {
   private List resolvers = new ArrayList();

   public void add(AbstractOSGiResolver resolver) {
      this.resolvers.add(resolver);
   }

   protected void init() {
      List<RepoDescriptor> repos = new ArrayList();

      for(AbstractOSGiResolver resolver : this.resolvers) {
         repos.add(resolver.getRepoDescriptor());
      }

      this.setRepoDescriptor(new AggregatedRepoDescriptor(repos));
   }
}
