package org.glassfish.jersey.model.internal;

import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.glassfish.jersey.ExtendedConfig;
import org.glassfish.jersey.JerseyPriorities;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.internal.inject.Binder;
import org.glassfish.jersey.internal.inject.CompositeBinder;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.ProviderBinder;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;
import org.glassfish.jersey.internal.spi.ForcedAutoDiscoverable;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.process.Inflector;

public class CommonConfig implements FeatureContext, ExtendedConfig {
   private static final Logger LOGGER = Logger.getLogger(CommonConfig.class.getName());
   private static final Function CAST_TO_BINDER;
   private final RuntimeType type;
   private final Map properties;
   private final Map immutablePropertiesView;
   private final Collection immutablePropertyNames;
   private final ComponentBag componentBag;
   private final List newFeatureRegistrations;
   private final Set enabledFeatureClasses;
   private final Set enabledFeatures;
   private boolean disableMetaProviderConfiguration;

   public CommonConfig(RuntimeType type, Predicate registrationStrategy) {
      this.type = type;
      this.properties = new HashMap();
      this.immutablePropertiesView = Collections.unmodifiableMap(this.properties);
      this.immutablePropertyNames = Collections.unmodifiableCollection(this.properties.keySet());
      this.componentBag = ComponentBag.newInstance(registrationStrategy);
      this.newFeatureRegistrations = new LinkedList();
      this.enabledFeatureClasses = Collections.newSetFromMap(new IdentityHashMap());
      this.enabledFeatures = new HashSet();
      this.disableMetaProviderConfiguration = false;
   }

   public CommonConfig(CommonConfig config) {
      this.type = config.type;
      this.properties = new HashMap(config.properties.size());
      this.immutablePropertiesView = Collections.unmodifiableMap(this.properties);
      this.immutablePropertyNames = Collections.unmodifiableCollection(this.properties.keySet());
      this.componentBag = config.componentBag.copy();
      this.newFeatureRegistrations = new LinkedList();
      this.enabledFeatureClasses = Collections.newSetFromMap(new IdentityHashMap());
      this.enabledFeatures = new HashSet();
      this.copy(config, false);
   }

   private void copy(CommonConfig config, boolean loadComponentBag) {
      this.properties.clear();
      this.properties.putAll(config.properties);
      this.newFeatureRegistrations.clear();
      this.newFeatureRegistrations.addAll(config.newFeatureRegistrations);
      this.enabledFeatureClasses.clear();
      this.enabledFeatureClasses.addAll(config.enabledFeatureClasses);
      this.enabledFeatures.clear();
      this.enabledFeatures.addAll(config.enabledFeatures);
      this.disableMetaProviderConfiguration = config.disableMetaProviderConfiguration;
      if (loadComponentBag) {
         this.componentBag.loadFrom(config.componentBag);
      }

   }

   public ExtendedConfig getConfiguration() {
      return this;
   }

   public RuntimeType getRuntimeType() {
      return this.type;
   }

   public Map getProperties() {
      return this.immutablePropertiesView;
   }

   public Object getProperty(String name) {
      return this.properties.get(name);
   }

   public boolean isProperty(String name) {
      return PropertiesHelper.isProperty(this.getProperty(name));
   }

   public Collection getPropertyNames() {
      return this.immutablePropertyNames;
   }

   public boolean isEnabled(Class featureClass) {
      return this.enabledFeatureClasses.contains(featureClass);
   }

   public boolean isEnabled(Feature feature) {
      return this.enabledFeatures.contains(feature);
   }

   public boolean isRegistered(Object component) {
      return this.componentBag.getInstances().contains(component);
   }

   public boolean isRegistered(Class componentClass) {
      return this.componentBag.getRegistrations().contains(componentClass);
   }

   public Map getContracts(Class componentClass) {
      ContractProvider model = this.componentBag.getModel(componentClass);
      return model == null ? Collections.emptyMap() : model.getContractMap();
   }

   public Set getClasses() {
      return this.componentBag.getClasses();
   }

   public Set getInstances() {
      return this.componentBag.getInstances();
   }

   public final ComponentBag getComponentBag() {
      return this.componentBag;
   }

   protected Inflector getModelEnhancer(Class componentClass) {
      return ComponentBag.AS_IS;
   }

   public CommonConfig setProperties(Map properties) {
      this.properties.clear();
      if (properties != null) {
         this.properties.putAll(properties);
      }

      return this;
   }

   public CommonConfig addProperties(Map properties) {
      if (properties != null) {
         this.properties.putAll(properties);
      }

      return this;
   }

   public CommonConfig property(String name, Object value) {
      if (value == null) {
         this.properties.remove(name);
      } else {
         this.properties.put(name, value);
      }

      return this;
   }

   public CommonConfig register(Class componentClass) {
      this.checkComponentClassNotNull(componentClass);
      if (this.componentBag.register(componentClass, this.getModelEnhancer(componentClass))) {
         this.processFeatureRegistration((Object)null, componentClass, -1);
      }

      return this;
   }

   public CommonConfig register(Class componentClass, int bindingPriority) {
      this.checkComponentClassNotNull(componentClass);
      if (this.componentBag.register(componentClass, bindingPriority, this.getModelEnhancer(componentClass))) {
         this.processFeatureRegistration((Object)null, componentClass, bindingPriority);
      }

      return this;
   }

   public CommonConfig register(Class componentClass, Class... contracts) {
      this.checkComponentClassNotNull(componentClass);
      if (contracts != null && contracts.length != 0) {
         if (this.componentBag.register(componentClass, this.asNewIdentitySet(contracts), this.getModelEnhancer(componentClass))) {
            this.processFeatureRegistration((Object)null, componentClass, -1);
         }

         return this;
      } else {
         LOGGER.warning(LocalizationMessages.COMPONENT_CONTRACTS_EMPTY_OR_NULL(componentClass));
         return this;
      }
   }

   public CommonConfig register(Class componentClass, Map contracts) {
      this.checkComponentClassNotNull(componentClass);
      if (this.componentBag.register(componentClass, contracts, this.getModelEnhancer(componentClass))) {
         this.processFeatureRegistration((Object)null, componentClass, -1);
      }

      return this;
   }

   public CommonConfig register(Object component) {
      this.checkProviderNotNull(component);
      Class<?> componentClass = component.getClass();
      if (this.componentBag.register(component, this.getModelEnhancer(componentClass))) {
         this.processFeatureRegistration(component, componentClass, -1);
      }

      return this;
   }

   public CommonConfig register(Object component, int bindingPriority) {
      this.checkProviderNotNull(component);
      Class<?> componentClass = component.getClass();
      if (this.componentBag.register(component, bindingPriority, this.getModelEnhancer(componentClass))) {
         this.processFeatureRegistration(component, componentClass, bindingPriority);
      }

      return this;
   }

   public CommonConfig register(Object component, Class... contracts) {
      this.checkProviderNotNull(component);
      Class<?> componentClass = component.getClass();
      if (contracts != null && contracts.length != 0) {
         if (this.componentBag.register(component, this.asNewIdentitySet(contracts), this.getModelEnhancer(componentClass))) {
            this.processFeatureRegistration(component, componentClass, -1);
         }

         return this;
      } else {
         LOGGER.warning(LocalizationMessages.COMPONENT_CONTRACTS_EMPTY_OR_NULL(componentClass));
         return this;
      }
   }

   public CommonConfig register(Object component, Map contracts) {
      this.checkProviderNotNull(component);
      Class<?> componentClass = component.getClass();
      if (this.componentBag.register(component, contracts, this.getModelEnhancer(componentClass))) {
         this.processFeatureRegistration(component, componentClass, -1);
      }

      return this;
   }

   private void processFeatureRegistration(Object component, Class componentClass, int priority) {
      ContractProvider model = this.componentBag.getModel(componentClass);
      if (model.getContracts().contains(Feature.class)) {
         FeatureRegistration registration = component != null ? new FeatureRegistration((Feature)component, priority) : new FeatureRegistration(componentClass, priority);
         this.newFeatureRegistrations.add(registration);
      }

   }

   public CommonConfig loadFrom(Configuration config) {
      if (config instanceof CommonConfig) {
         CommonConfig commonConfig = (CommonConfig)config;
         this.copy(commonConfig, true);
         this.disableMetaProviderConfiguration = !commonConfig.enabledFeatureClasses.isEmpty();
      } else {
         this.setProperties(config.getProperties());
         this.enabledFeatures.clear();
         this.enabledFeatureClasses.clear();
         this.componentBag.clear();
         this.resetFeatureRegistrations();

         for(Class clazz : config.getClasses()) {
            if (Feature.class.isAssignableFrom(clazz) && config.isEnabled(clazz)) {
               this.disableMetaProviderConfiguration = true;
            }

            this.register(clazz, config.getContracts(clazz));
         }

         for(Object instance : config.getInstances()) {
            if (instance instanceof Feature && config.isEnabled((Feature)instance)) {
               this.disableMetaProviderConfiguration = true;
            }

            this.register(instance, config.getContracts(instance.getClass()));
         }
      }

      return this;
   }

   private Set asNewIdentitySet(Class... contracts) {
      Set<Class<?>> result = Collections.newSetFromMap(new IdentityHashMap());
      result.addAll(Arrays.asList(contracts));
      return result;
   }

   private void checkProviderNotNull(Object provider) {
      if (provider == null) {
         throw new IllegalArgumentException(LocalizationMessages.COMPONENT_CANNOT_BE_NULL());
      }
   }

   private void checkComponentClassNotNull(Class componentClass) {
      if (componentClass == null) {
         throw new IllegalArgumentException(LocalizationMessages.COMPONENT_CLASS_CANNOT_BE_NULL());
      }
   }

   public void configureAutoDiscoverableProviders(InjectionManager injectionManager, Collection autoDiscoverables, boolean forcedOnly) {
      if (!this.disableMetaProviderConfiguration) {
         Set<AutoDiscoverable> providers = new TreeSet((o1, o2) -> {
            int p1 = JerseyPriorities.getPriorityValue(o1.getClass(), 5000);
            int p2 = JerseyPriorities.getPriorityValue(o2.getClass(), 5000);
            return p1 >= p2 && p1 != p2 ? 1 : -1;
         });
         List<ForcedAutoDiscoverable> forcedAutoDiscroverables = new LinkedList();

         for(Class forcedADType : ServiceFinder.find(ForcedAutoDiscoverable.class, true).toClassArray()) {
            forcedAutoDiscroverables.add(injectionManager.createAndInitialize(forcedADType));
         }

         providers.addAll(forcedAutoDiscroverables);
         if (!forcedOnly) {
            providers.addAll(autoDiscoverables);
         }

         for(AutoDiscoverable autoDiscoverable : providers) {
            ConstrainedTo constrainedTo = (ConstrainedTo)autoDiscoverable.getClass().getAnnotation(ConstrainedTo.class);
            if (constrainedTo == null || this.type.equals(constrainedTo.value())) {
               try {
                  autoDiscoverable.configure(this);
               } catch (Exception e) {
                  LOGGER.log(Level.FINE, LocalizationMessages.AUTODISCOVERABLE_CONFIGURATION_FAILED(autoDiscoverable.getClass()), e);
               }
            }
         }
      }

   }

   public void configureMetaProviders(InjectionManager injectionManager, ManagedObjectsFinalizer finalizer) {
      Set<Object> configuredExternals = Collections.newSetFromMap(new IdentityHashMap());
      Set<Binder> configuredBinders = this.configureBinders(injectionManager, Collections.emptySet());
      if (!this.disableMetaProviderConfiguration) {
         this.configureExternalObjects(injectionManager, configuredExternals);
         this.configureFeatures(injectionManager, new HashSet(), this.resetFeatureRegistrations(), finalizer);
         this.configureExternalObjects(injectionManager, configuredExternals);
         this.configureBinders(injectionManager, configuredBinders);
      }

   }

   private Set configureBinders(InjectionManager injectionManager, Set configured) {
      Set<Binder> allConfigured = Collections.newSetFromMap(new IdentityHashMap());
      allConfigured.addAll(configured);
      Collection<Binder> binders = this.getBinder(configured);
      if (!binders.isEmpty()) {
         injectionManager.register((Binder)CompositeBinder.wrap(binders));
         allConfigured.addAll(binders);
      }

      return allConfigured;
   }

   private Collection getBinder(Set configured) {
      return (Collection)this.componentBag.getInstances(ComponentBag.BINDERS_ONLY).stream().map(CAST_TO_BINDER).filter((binder) -> !configured.contains(binder)).collect(Collectors.toList());
   }

   private void configureExternalObjects(InjectionManager injectionManager, Set externalObjects) {
      Consumer<Object> registerOnce = (o) -> {
         if (!externalObjects.contains(o)) {
            injectionManager.register(o);
            externalObjects.add(o);
         }

      };
      this.componentBag.getInstances((model) -> ComponentBag.EXTERNAL_ONLY.test(model, injectionManager)).forEach(registerOnce);
      this.componentBag.getClasses((model) -> ComponentBag.EXTERNAL_ONLY.test(model, injectionManager)).forEach(registerOnce);
   }

   private void configureFeatures(InjectionManager injectionManager, Set processed, List unprocessed, ManagedObjectsFinalizer managedObjectsFinalizer) {
      FeatureContextWrapper featureContextWrapper = null;

      for(FeatureRegistration registration : unprocessed) {
         if (processed.contains(registration)) {
            LOGGER.config(LocalizationMessages.FEATURE_HAS_ALREADY_BEEN_PROCESSED(registration.getFeatureClass()));
         } else {
            RuntimeType runtimeTypeConstraint = registration.getFeatureRuntimeType();
            if (runtimeTypeConstraint != null && !this.type.equals(runtimeTypeConstraint)) {
               LOGGER.config(LocalizationMessages.FEATURE_CONSTRAINED_TO_IGNORED(registration.getFeatureClass(), registration.runtimeType, this.type));
            } else {
               Feature feature = registration.getFeature();
               if (feature == null) {
                  feature = (Feature)injectionManager.createAndInitialize(registration.getFeatureClass());
                  managedObjectsFinalizer.registerForPreDestroyCall(feature);
               } else if (!RuntimeType.CLIENT.equals(this.type)) {
                  injectionManager.inject(feature);
               }

               if (this.enabledFeatures.contains(feature)) {
                  LOGGER.config(LocalizationMessages.FEATURE_HAS_ALREADY_BEEN_PROCESSED(feature));
               } else {
                  if (featureContextWrapper == null) {
                     featureContextWrapper = new FeatureContextWrapper(this, injectionManager);
                  }

                  boolean success = feature.configure(featureContextWrapper);
                  if (success) {
                     processed.add(registration);
                     ContractProvider providerModel = this.componentBag.getModel(feature.getClass());
                     if (providerModel != null) {
                        ProviderBinder.bindProvider((Object)feature, providerModel, injectionManager);
                     }

                     this.configureFeatures(injectionManager, processed, this.resetFeatureRegistrations(), managedObjectsFinalizer);
                     this.enabledFeatureClasses.add(registration.getFeatureClass());
                     this.enabledFeatures.add(feature);
                  }
               }
            }
         }
      }

   }

   private List resetFeatureRegistrations() {
      List<FeatureRegistration> result = new ArrayList(this.newFeatureRegistrations);
      this.newFeatureRegistrations.clear();
      Collections.sort(result, (o1, o2) -> o1.priority < o2.priority ? -1 : 1);
      return result;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof CommonConfig)) {
         return false;
      } else {
         CommonConfig that = (CommonConfig)o;
         if (this.type != that.type) {
            return false;
         } else if (!this.properties.equals(that.properties)) {
            return false;
         } else if (!this.componentBag.equals(that.componentBag)) {
            return false;
         } else if (!this.enabledFeatureClasses.equals(that.enabledFeatureClasses)) {
            return false;
         } else if (!this.enabledFeatures.equals(that.enabledFeatures)) {
            return false;
         } else {
            return this.newFeatureRegistrations.equals(that.newFeatureRegistrations);
         }
      }
   }

   public int hashCode() {
      int result = this.type.hashCode();
      result = 31 * result + this.properties.hashCode();
      result = 31 * result + this.componentBag.hashCode();
      result = 31 * result + this.newFeatureRegistrations.hashCode();
      result = 31 * result + this.enabledFeatures.hashCode();
      result = 31 * result + this.enabledFeatureClasses.hashCode();
      return result;
   }

   static {
      Binder.class.getClass();
      CAST_TO_BINDER = Binder.class::cast;
   }

   private static final class FeatureRegistration {
      private final Class featureClass;
      private final Feature feature;
      private final RuntimeType runtimeType;
      private final int priority;

      private FeatureRegistration(Class featureClass, int priority) {
         this.featureClass = featureClass;
         this.feature = null;
         ConstrainedTo runtimeTypeConstraint = (ConstrainedTo)featureClass.getAnnotation(ConstrainedTo.class);
         this.runtimeType = runtimeTypeConstraint == null ? null : runtimeTypeConstraint.value();
         this.priority = priority(featureClass, priority);
      }

      private FeatureRegistration(Feature feature, int priority) {
         this.featureClass = feature.getClass();
         this.feature = feature;
         ConstrainedTo runtimeTypeConstraint = (ConstrainedTo)this.featureClass.getAnnotation(ConstrainedTo.class);
         this.runtimeType = runtimeTypeConstraint == null ? null : runtimeTypeConstraint.value();
         this.priority = priority(this.featureClass, priority);
      }

      private static int priority(Class featureClass, int priority) {
         return priority != -1 ? priority : JerseyPriorities.getPriorityValue(featureClass, 5000);
      }

      private Class getFeatureClass() {
         return this.featureClass;
      }

      private Feature getFeature() {
         return this.feature;
      }

      private RuntimeType getFeatureRuntimeType() {
         return this.runtimeType;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof FeatureRegistration)) {
            return false;
         } else {
            FeatureRegistration other = (FeatureRegistration)obj;
            return this.featureClass == other.featureClass || this.feature != null && (this.feature == other.feature || this.feature.equals(other.feature));
         }
      }

      public int hashCode() {
         int hash = 47;
         hash = 13 * hash + (this.feature != null ? this.feature.hashCode() : 0);
         hash = 13 * hash + (this.featureClass != null ? this.featureClass.hashCode() : 0);
         return hash;
      }
   }
}
