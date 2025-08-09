package org.apache.ivy.plugins.resolver;

import org.apache.ivy.plugins.repository.url.URLRepository;

public class URLResolver extends RepositoryResolver {
   public URLResolver() {
      this.setRepository(new URLRepository(new LazyTimeoutConstraint(this)));
   }

   public String getTypeName() {
      return "url";
   }
}
