package org.glassfish.jersey.internal.inject;

public class InjectionResolverBinding extends Binding {
   private final InjectionResolver resolver;

   InjectionResolverBinding(InjectionResolver resolver) {
      this.resolver = resolver;
   }

   public InjectionResolver getResolver() {
      return this.resolver;
   }
}
