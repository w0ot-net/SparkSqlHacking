package org.glassfish.jersey.inject.hk2;

import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.Binder;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.InstanceBinding;

public class DelayedHk2InjectionManager extends AbstractHk2InjectionManager {
   private final AbstractBinder bindings = new AbstractBinder() {
      protected void configure() {
      }
   };
   private final List providers = new ArrayList();
   private boolean completed = false;

   DelayedHk2InjectionManager(Object parent) {
      super(parent);
   }

   public void register(Binding binding) {
      if (!this.completed || binding.getScope() != Singleton.class && !(binding instanceof InstanceBinding)) {
         this.bindings.bind(binding);
      } else {
         Hk2Helper.bind(this.getServiceLocator(), binding);
      }

   }

   public void register(Iterable bindings) {
      for(Binding binding : bindings) {
         this.bindings.bind(binding);
      }

   }

   public void register(Binder binder) {
      for(Binding binding : Bindings.getBindings(this, binder)) {
         this.bindings.bind(binding);
      }

   }

   public void register(Object provider) throws IllegalArgumentException {
      if (this.isRegistrable(provider.getClass())) {
         this.providers.add((org.glassfish.hk2.utilities.Binder)provider);
      } else {
         if (!Class.class.isInstance(provider) || !this.isRegistrable((Class)provider)) {
            throw new IllegalArgumentException(LocalizationMessages.HK_2_PROVIDER_NOT_REGISTRABLE(provider.getClass()));
         }

         this.providers.add((org.glassfish.hk2.utilities.Binder)this.createAndInitialize((Class)provider));
      }

   }

   public void completeRegistration() throws IllegalStateException {
      Hk2Helper.bind((AbstractHk2InjectionManager)this, (Binder)this.bindings);
      ServiceLocatorUtilities.bind(this.getServiceLocator(), (org.glassfish.hk2.utilities.Binder[])this.providers.toArray(new org.glassfish.hk2.utilities.Binder[0]));
      this.completed = true;
   }
}
