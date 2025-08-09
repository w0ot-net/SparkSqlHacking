package org.glassfish.jersey.server.internal;

import jakarta.inject.Scope;
import jakarta.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.ClassBinding;
import org.glassfish.jersey.internal.inject.CustomAnnotationLiteral;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ExtendedResourceContext;
import org.glassfish.jersey.server.model.ResourceModel;

public class JerseyResourceContext implements ExtendedResourceContext {
   private final Function getOrCreateInstance;
   private final Consumer injectInstance;
   private final Consumer registerBinding;
   private final Set bindingCache;
   private final Object bindingCacheLock;
   private volatile ResourceModel resourceModel;

   public JerseyResourceContext(Function getOrCreateInstance, Consumer injectInstance, Consumer registerBinding) {
      this.getOrCreateInstance = getOrCreateInstance;
      this.injectInstance = injectInstance;
      this.registerBinding = registerBinding;
      this.bindingCache = Collections.newSetFromMap(new IdentityHashMap());
      this.bindingCacheLock = new Object();
   }

   public Object getResource(Class resourceClass) {
      try {
         return this.getOrCreateInstance.apply(resourceClass);
      } catch (Exception ex) {
         Logger.getLogger(JerseyResourceContext.class.getName()).log(Level.WARNING, LocalizationMessages.RESOURCE_LOOKUP_FAILED(resourceClass), ex);
         return null;
      }
   }

   public Object initResource(Object resource) {
      this.injectInstance.accept(resource);
      return resource;
   }

   public void bindResource(Class resourceClass) {
      if (!this.bindingCache.contains(resourceClass)) {
         synchronized(this.bindingCacheLock) {
            if (!this.bindingCache.contains(resourceClass)) {
               this.unsafeBindResource((Class)resourceClass, (ContractProvider)null);
            }
         }
      }
   }

   public void bindResourceIfSingleton(Object resource) {
      Class<?> resourceClass = resource.getClass();
      if (!this.bindingCache.contains(resourceClass)) {
         synchronized(this.bindingCacheLock) {
            if (!this.bindingCache.contains(resourceClass)) {
               if (getScope(resourceClass) == Singleton.class) {
                  this.registerBinding.accept(Bindings.service(resource).to(resourceClass));
               }

               this.bindingCache.add(resourceClass);
            }
         }
      }
   }

   public void unsafeBindResource(Object resource, ContractProvider providerModel) {
      Class<?> resourceClass = resource.getClass();
      Binding binding;
      if (providerModel != null) {
         Class<? extends Annotation> scope = providerModel.getScope();
         binding = Bindings.service(resource).to(resourceClass);

         for(Class contract : Providers.getProviderContracts(resourceClass)) {
            binding.addAlias(contract).in(scope.getName()).qualifiedBy(CustomAnnotationLiteral.INSTANCE);
         }
      } else {
         binding = Bindings.serviceAsContract(resourceClass);
      }

      this.registerBinding.accept(binding);
      this.bindingCache.add(resourceClass);
   }

   private static Class getScope(Class resourceClass) {
      Collection<Class<? extends Annotation>> scopes = ReflectionHelper.getAnnotationTypes(resourceClass, Scope.class);
      return scopes.isEmpty() ? RequestScoped.class : (Class)scopes.iterator().next();
   }

   public void unsafeBindResource(Class resourceClass, ContractProvider providerModel) {
      ClassBinding<T> descriptor;
      if (providerModel != null) {
         Class<? extends Annotation> scope = providerModel.getScope();
         descriptor = (ClassBinding)Bindings.serviceAsContract(resourceClass).in(scope);

         for(Class contract : providerModel.getContracts()) {
            descriptor.addAlias(contract).in(scope.getName()).ranked(providerModel.getPriority(contract)).qualifiedBy(CustomAnnotationLiteral.INSTANCE);
         }
      } else {
         descriptor = (ClassBinding)Bindings.serviceAsContract(resourceClass).in(getScope(resourceClass));
      }

      this.registerBinding.accept(descriptor);
      this.bindingCache.add(resourceClass);
   }

   public ResourceModel getResourceModel() {
      return this.resourceModel;
   }

   public void setResourceModel(ResourceModel resourceModel) {
      this.resourceModel = resourceModel;
   }
}
