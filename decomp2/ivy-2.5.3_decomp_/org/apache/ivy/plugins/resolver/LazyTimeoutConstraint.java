package org.apache.ivy.plugins.resolver;

import org.apache.ivy.core.settings.TimeoutConstraint;

final class LazyTimeoutConstraint implements TimeoutConstraint {
   private final AbstractResolver resolver;

   public LazyTimeoutConstraint(AbstractResolver resolver) {
      this.resolver = resolver;
   }

   public int getConnectionTimeout() {
      TimeoutConstraint resolverTimeoutConstraint = this.resolver.getTimeoutConstraint();
      return resolverTimeoutConstraint == null ? -1 : resolverTimeoutConstraint.getConnectionTimeout();
   }

   public int getReadTimeout() {
      TimeoutConstraint resolverTimeoutConstraint = this.resolver.getTimeoutConstraint();
      return resolverTimeoutConstraint == null ? -1 : resolverTimeoutConstraint.getReadTimeout();
   }
}
