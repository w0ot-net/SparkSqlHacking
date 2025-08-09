package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.FactoryDescriptors;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.TwoPhaseResource;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.DescriptorImpl;

public abstract class AbstractBinder implements Binder, DynamicConfiguration {
   private transient DynamicConfiguration configuration;
   private transient AbstractBindingBuilder currentBuilder;
   private transient HK2Loader defaultLoader;

   public ServiceBindingBuilder bind(Class serviceType) {
      return this.resetBuilder(AbstractBindingBuilder.create(serviceType, false));
   }

   public ServiceBindingBuilder bindAsContract(Class serviceType) {
      return this.resetBuilder(AbstractBindingBuilder.create(serviceType, true));
   }

   public ServiceBindingBuilder bindAsContract(TypeLiteral serviceType) {
      return this.resetBuilder(AbstractBindingBuilder.create(serviceType, true));
   }

   public ServiceBindingBuilder bindAsContract(Type serviceType) {
      return this.resetBuilder(AbstractBindingBuilder.create(serviceType, true));
   }

   public ScopedBindingBuilder bind(Object service) {
      return this.resetBuilder(AbstractBindingBuilder.create(service));
   }

   public ServiceBindingBuilder bindFactory(Class factoryType, Class factoryScope) {
      return this.resetBuilder(AbstractBindingBuilder.createFactoryBinder(factoryType, factoryScope));
   }

   public ServiceBindingBuilder bindFactory(Class factoryType) {
      return this.resetBuilder(AbstractBindingBuilder.createFactoryBinder(factoryType, (Class)null));
   }

   public ServiceBindingBuilder bindFactory(Factory factory) {
      return this.resetBuilder(AbstractBindingBuilder.createFactoryBinder(factory));
   }

   public void bind(DynamicConfiguration configuration) {
      if (this.configuration != null) {
         throw new IllegalArgumentException("Recursive configuration call detected.");
      } else if (configuration == null) {
         throw new NullPointerException("configuration");
      } else {
         this.configuration = configuration;

         try {
            this.configure();
         } finally {
            this.complete();
         }

      }
   }

   private AbstractBindingBuilder resetBuilder(AbstractBindingBuilder newBuilder) {
      if (this.currentBuilder != null) {
         this.currentBuilder.complete(this.configuration(), this.getDefaultBinderLoader());
      }

      this.currentBuilder = newBuilder;
      return newBuilder;
   }

   private void complete() {
      try {
         this.resetBuilder((AbstractBindingBuilder)null);
      } finally {
         this.configuration = null;
      }

   }

   protected abstract void configure();

   private DynamicConfiguration configuration() {
      if (this.configuration == null) {
         throw new IllegalArgumentException("Dynamic configuration accessed from outside of an active binder configuration scope.");
      } else {
         return this.configuration;
      }
   }

   public ActiveDescriptor bind(Descriptor descriptor) {
      return this.bind(descriptor, true);
   }

   public ActiveDescriptor bind(Descriptor descriptor, boolean requiresDeepCopy) {
      this.setLoader(descriptor);
      return this.configuration().bind(descriptor, requiresDeepCopy);
   }

   public FactoryDescriptors bind(FactoryDescriptors factoryDescriptors) {
      return this.bind(factoryDescriptors, true);
   }

   public FactoryDescriptors bind(FactoryDescriptors factoryDescriptors, boolean requiresDeepCopy) {
      this.setLoader(factoryDescriptors.getFactoryAsAService());
      this.setLoader(factoryDescriptors.getFactoryAsAFactory());
      return this.configuration().bind(factoryDescriptors, requiresDeepCopy);
   }

   public ActiveDescriptor addActiveDescriptor(ActiveDescriptor activeDescriptor) throws IllegalArgumentException {
      return this.addActiveDescriptor(activeDescriptor, true);
   }

   public ActiveDescriptor addActiveDescriptor(ActiveDescriptor activeDescriptor, boolean requiresDeepCopy) throws IllegalArgumentException {
      return this.configuration().addActiveDescriptor(activeDescriptor, requiresDeepCopy);
   }

   public ActiveDescriptor addActiveDescriptor(Class rawClass) throws MultiException, IllegalArgumentException {
      return this.configuration().addActiveDescriptor(rawClass);
   }

   public FactoryDescriptors addActiveFactoryDescriptor(Class rawFactoryClass) throws MultiException, IllegalArgumentException {
      return this.configuration().addActiveFactoryDescriptor(rawFactoryClass);
   }

   public void addUnbindFilter(Filter unbindFilter) throws IllegalArgumentException {
      this.configuration().addUnbindFilter(unbindFilter);
   }

   public void addIdempotentFilter(Filter... unbindFilter) throws IllegalArgumentException {
      this.configuration().addIdempotentFilter(unbindFilter);
   }

   public void registerTwoPhaseResources(TwoPhaseResource... resources) {
      this.configuration().registerTwoPhaseResources(resources);
   }

   public void commit() throws MultiException {
      this.configuration().commit();
   }

   public final void install(Binder... binders) {
      for(Binder binder : binders) {
         binder.bind(this);
      }

   }

   private void setLoader(Descriptor descriptor) {
      if (descriptor.getLoader() == null && descriptor instanceof DescriptorImpl) {
         ((DescriptorImpl)descriptor).setLoader(this.getDefaultBinderLoader());
      }

   }

   private HK2Loader getDefaultBinderLoader() {
      if (this.defaultLoader == null) {
         final ClassLoader binderClassLoader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
            public ClassLoader run() {
               ClassLoader loader = this.getClass().getClassLoader();
               return loader == null ? ClassLoader.getSystemClassLoader() : loader;
            }
         });
         this.defaultLoader = new HK2Loader() {
            public Class loadClass(String className) throws MultiException {
               try {
                  return binderClassLoader.loadClass(className);
               } catch (ClassNotFoundException e) {
                  throw new MultiException(e);
               }
            }
         };
      }

      return this.defaultLoader;
   }
}
