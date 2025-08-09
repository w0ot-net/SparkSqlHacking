package org.glassfish.jersey.model.internal;

import jakarta.annotation.Priority;
import jakarta.inject.Scope;
import jakarta.ws.rs.NameBinding;
import jakarta.ws.rs.core.Feature;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.glassfish.jersey.Severity;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.inject.Binder;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.ClassBinding;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.InstanceBinding;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.spi.ExecutorServiceProvider;
import org.glassfish.jersey.spi.ScheduledExecutorServiceProvider;

public class ComponentBag {
   private static final Predicate EXCLUDE_META_PROVIDERS = (model) -> {
      Set<Class<?>> contracts = model.getContracts();
      if (contracts.isEmpty()) {
         return true;
      } else {
         byte count = 0;
         if (contracts.contains(Feature.class)) {
            ++count;
         }

         if (contracts.contains(Binder.class)) {
            ++count;
         }

         return contracts.size() > count;
      }
   };
   private static final Function CAST_TO_BINDER;
   public static final BiPredicate EXTERNAL_ONLY;
   public static final Predicate BINDERS_ONLY;
   public static final Predicate EXECUTOR_SERVICE_PROVIDER_ONLY;
   public static final Predicate SCHEDULED_EXECUTOR_SERVICE_PROVIDER_ONLY;
   public static final Predicate EXCLUDE_EMPTY;
   public static final Predicate INCLUDE_ALL;
   static final Inflector AS_IS;
   private final Predicate registrationStrategy;
   private final Set classes;
   private final Set classesView;
   private final Set instances;
   private final Set instancesView;
   private final Map models;
   private final Set modelKeysView;

   public static Predicate excludeMetaProviders(InjectionManager injectionManager) {
      return EXCLUDE_META_PROVIDERS.and((model) -> !injectionManager.isRegistrable(model.getImplementationClass()));
   }

   public static ComponentBag newInstance(Predicate registrationStrategy) {
      return new ComponentBag(registrationStrategy);
   }

   public static List getFromBinders(InjectionManager injectionManager, ComponentBag componentBag, Function cast, Predicate filter) {
      Function<Binding, Object> bindingToObject = (binding) -> {
         if (binding instanceof ClassBinding) {
            ClassBinding classBinding = (ClassBinding)binding;
            return injectionManager.createAndInitialize(classBinding.getService());
         } else {
            InstanceBinding instanceBinding = (InstanceBinding)binding;
            return instanceBinding.getService();
         }
      };
      return (List)componentBag.getInstances(BINDERS_ONLY).stream().map(CAST_TO_BINDER).flatMap((binder) -> Bindings.getBindings(injectionManager, binder).stream()).filter(filter).map(bindingToObject).map(cast).collect(Collectors.toList());
   }

   private ComponentBag(Predicate registrationStrategy) {
      this.registrationStrategy = registrationStrategy;
      this.classes = new LinkedHashSet();
      this.instances = new LinkedHashSet();
      this.models = new IdentityHashMap();
      this.classesView = Collections.unmodifiableSet(this.classes);
      this.instancesView = Collections.unmodifiableSet(this.instances);
      this.modelKeysView = Collections.unmodifiableSet(this.models.keySet());
   }

   private ComponentBag(Predicate registrationStrategy, Set classes, Set instances, Map models) {
      this.registrationStrategy = registrationStrategy;
      this.classes = classes;
      this.instances = instances;
      this.models = models;
      this.classesView = Collections.unmodifiableSet(classes);
      this.instancesView = Collections.unmodifiableSet(instances);
      this.modelKeysView = Collections.unmodifiableSet(models.keySet());
   }

   public boolean register(Class componentClass, Inflector modelEnhancer) {
      boolean result = this.registerModel(componentClass, -1, (Map)null, modelEnhancer);
      if (result) {
         this.classes.add(componentClass);
      }

      return result;
   }

   public boolean register(Class componentClass, int priority, Inflector modelEnhancer) {
      boolean result = this.registerModel(componentClass, priority, (Map)null, modelEnhancer);
      if (result) {
         this.classes.add(componentClass);
      }

      return result;
   }

   public boolean register(Class componentClass, Set contracts, Inflector modelEnhancer) {
      boolean result = this.registerModel(componentClass, -1, asMap(contracts), modelEnhancer);
      if (result) {
         this.classes.add(componentClass);
      }

      return result;
   }

   public boolean register(Class componentClass, Map contracts, Inflector modelEnhancer) {
      boolean result = this.registerModel(componentClass, -1, contracts, modelEnhancer);
      if (result) {
         this.classes.add(componentClass);
      }

      return result;
   }

   public boolean register(Object component, Inflector modelEnhancer) {
      Class<?> componentClass = component.getClass();
      boolean result = this.registerModel(componentClass, -1, (Map)null, modelEnhancer);
      if (result) {
         this.instances.add(component);
      }

      return result;
   }

   public boolean register(Object component, int priority, Inflector modelEnhancer) {
      Class<?> componentClass = component.getClass();
      boolean result = this.registerModel(componentClass, priority, (Map)null, modelEnhancer);
      if (result) {
         this.instances.add(component);
      }

      return result;
   }

   public boolean register(Object component, Set contracts, Inflector modelEnhancer) {
      Class<?> componentClass = component.getClass();
      boolean result = this.registerModel(componentClass, -1, asMap(contracts), modelEnhancer);
      if (result) {
         this.instances.add(component);
      }

      return result;
   }

   public boolean register(Object component, Map contracts, Inflector modelEnhancer) {
      Class<?> componentClass = component.getClass();
      boolean result = this.registerModel(componentClass, -1, contracts, modelEnhancer);
      if (result) {
         this.instances.add(component);
      }

      return result;
   }

   private boolean registerModel(Class componentClass, int defaultPriority, Map contractMap, Inflector modelEnhancer) {
      return (Boolean)Errors.process((Producer)(() -> {
         if (this.models.containsKey(componentClass)) {
            Errors.error(LocalizationMessages.COMPONENT_TYPE_ALREADY_REGISTERED(componentClass), Severity.HINT);
            return false;
         } else {
            ContractProvider model = modelFor(componentClass, defaultPriority, contractMap, modelEnhancer);
            if (!this.registrationStrategy.test(model)) {
               return false;
            } else {
               this.models.put(componentClass, model);
               return true;
            }
         }
      }));
   }

   public static ContractProvider modelFor(Class componentClass) {
      return modelFor(componentClass, -1, (Map)null, AS_IS);
   }

   private static ContractProvider modelFor(Class componentClass, int defaultPriority, Map contractMap, Inflector modelEnhancer) {
      Map<Class<?>, Integer> contracts;
      if (contractMap == null) {
         contracts = asMap(Providers.getProviderContracts(componentClass));
      } else {
         contracts = new HashMap(contractMap);
         Iterator<Class<?>> it = contracts.keySet().iterator();

         while(it.hasNext()) {
            Class<?> contract = (Class)it.next();
            if (contract == null) {
               it.remove();
            } else {
               boolean failed = false;
               if (!Providers.isSupportedContract(contract)) {
                  Errors.error(LocalizationMessages.CONTRACT_NOT_SUPPORTED(contract, componentClass), Severity.WARNING);
                  failed = true;
               }

               if (!contract.isAssignableFrom(componentClass)) {
                  Errors.error(LocalizationMessages.CONTRACT_NOT_ASSIGNABLE(contract, componentClass), Severity.WARNING);
                  failed = true;
               }

               if (failed) {
                  it.remove();
               }
            }
         }
      }

      ContractProvider.Builder builder = ContractProvider.builder(componentClass).addContracts(contracts).defaultPriority(defaultPriority);
      boolean useAnnotationPriority = defaultPriority == -1;

      for(Annotation annotation : componentClass.getAnnotations()) {
         if (annotation instanceof Priority) {
            if (useAnnotationPriority) {
               builder.defaultPriority(((Priority)annotation).value());
            }
         } else {
            for(Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
               if (metaAnnotation instanceof NameBinding) {
                  builder.addNameBinding(annotation.annotationType());
               }

               if (metaAnnotation instanceof Scope) {
                  builder.scope(annotation.annotationType());
               }
            }
         }
      }

      return (ContractProvider)modelEnhancer.apply(builder);
   }

   private static Map asMap(Set contractSet) {
      Map<Class<?>, Integer> contracts = new IdentityHashMap();

      for(Class contract : contractSet) {
         contracts.put(contract, -1);
      }

      return contracts;
   }

   public Set getClasses() {
      return this.classesView;
   }

   public Set getInstances() {
      return this.instancesView;
   }

   public Set getClasses(Predicate filter) {
      return (Set)this.classesView.stream().filter((input) -> {
         ContractProvider model = this.getModel(input);
         return filter.test(model);
      }).collect(Collectors.toCollection(LinkedHashSet::new));
   }

   public Set getInstances(Predicate filter) {
      return (Set)this.instancesView.stream().filter((input) -> {
         ContractProvider model = this.getModel(input.getClass());
         return filter.test(model);
      }).collect(Collectors.toCollection(LinkedHashSet::new));
   }

   public Set getRegistrations() {
      return this.modelKeysView;
   }

   public ContractProvider getModel(Class componentClass) {
      return (ContractProvider)this.models.get(componentClass);
   }

   public ComponentBag copy() {
      return new ComponentBag(this.registrationStrategy, new LinkedHashSet(this.classes), new LinkedHashSet(this.instances), new IdentityHashMap(this.models));
   }

   public ComponentBag immutableCopy() {
      return new ImmutableComponentBag(this);
   }

   public void clear() {
      this.classes.clear();
      this.instances.clear();
      this.models.clear();
   }

   void loadFrom(ComponentBag bag) {
      this.clear();
      this.classes.addAll(bag.classes);
      this.instances.addAll(bag.instances);
      this.models.putAll(bag.models);
   }

   static {
      Binder.class.getClass();
      CAST_TO_BINDER = Binder.class::cast;
      EXTERNAL_ONLY = (model, injectionManager) -> model.getImplementationClass() != null && injectionManager.isRegistrable(model.getImplementationClass());
      BINDERS_ONLY = (model) -> model.getContracts().contains(Binder.class);
      EXECUTOR_SERVICE_PROVIDER_ONLY = (model) -> model.getContracts().contains(ExecutorServiceProvider.class) && !model.getContracts().contains(ScheduledExecutorServiceProvider.class);
      SCHEDULED_EXECUTOR_SERVICE_PROVIDER_ONLY = (model) -> model.getContracts().contains(ScheduledExecutorServiceProvider.class);
      EXCLUDE_EMPTY = (model) -> !model.getContracts().isEmpty();
      INCLUDE_ALL = (contractProvider) -> true;
      AS_IS = ContractProvider.Builder::build;
   }

   private static class ImmutableComponentBag extends ComponentBag {
      ImmutableComponentBag(ComponentBag original) {
         super(original.registrationStrategy, new LinkedHashSet(original.classes), new LinkedHashSet(original.instances), new IdentityHashMap(original.models), null);
      }

      public boolean register(Class componentClass, Inflector modelEnhancer) {
         throw new IllegalStateException("This instance is read-only.");
      }

      public boolean register(Class componentClass, int priority, Inflector modelEnhancer) {
         throw new IllegalStateException("This instance is read-only.");
      }

      public boolean register(Class componentClass, Set contracts, Inflector modelEnhancer) {
         throw new IllegalStateException("This instance is read-only.");
      }

      public boolean register(Class componentClass, Map contracts, Inflector modelEnhancer) {
         throw new IllegalStateException("This instance is read-only.");
      }

      public boolean register(Object component, Inflector modelEnhancer) {
         throw new IllegalStateException("This instance is read-only.");
      }

      public boolean register(Object component, int priority, Inflector modelEnhancer) {
         throw new IllegalStateException("This instance is read-only.");
      }

      public boolean register(Object component, Set contracts, Inflector modelEnhancer) {
         throw new IllegalStateException("This instance is read-only.");
      }

      public boolean register(Object component, Map contracts, Inflector modelEnhancer) {
         throw new IllegalStateException("This instance is read-only.");
      }

      public ComponentBag copy() {
         return this;
      }

      public ComponentBag immutableCopy() {
         return this;
      }

      public void clear() {
         throw new IllegalStateException("This instance is read-only.");
      }
   }
}
