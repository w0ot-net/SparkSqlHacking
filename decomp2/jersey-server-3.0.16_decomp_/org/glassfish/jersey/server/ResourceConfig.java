package org.glassfish.jersey.server;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Configurable;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Feature;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.glassfish.jersey.ApplicationSupplier;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.config.ExternalPropertiesConfigurationFactory;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.Tokenizer;
import org.glassfish.jersey.model.internal.CommonConfig;
import org.glassfish.jersey.model.internal.ComponentBag;
import org.glassfish.jersey.model.internal.ManagedObjectsFinalizer;
import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.internal.scanning.AnnotationAcceptingListener;
import org.glassfish.jersey.server.internal.scanning.FilesScanner;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.uri.UriComponent;
import org.glassfish.jersey.uri.UriComponent.Type;

public class ResourceConfig extends Application implements Configurable, ServerConfig, ApplicationSupplier {
   private static final Logger LOGGER = Logger.getLogger(ResourceConfig.class.getName());
   private transient Set cachedClasses;
   private transient Set cachedClassesView;
   private transient Set cachedSingletons;
   private transient Set cachedSingletonsView;
   private transient boolean resetFinders;
   private volatile State state;

   public static ResourceConfig forApplication(Application application) {
      return (ResourceConfig)(application instanceof ResourceConfig ? (ResourceConfig)application : new WrappingResourceConfig(application, (Class)null, (Set)null));
   }

   public static ResourceConfig forApplicationClass(Class applicationClass) {
      return new WrappingResourceConfig((Application)null, applicationClass, (Set)null);
   }

   public static ResourceConfig forApplicationClass(Class applicationClass, Set defaultClasses) {
      return new WrappingResourceConfig((Application)null, applicationClass, defaultClasses);
   }

   public ResourceConfig() {
      this.cachedClasses = null;
      this.cachedClassesView = null;
      this.cachedSingletons = null;
      this.cachedSingletonsView = null;
      this.resetFinders = false;
      this.state = new State();
   }

   public ResourceConfig(Set classes) {
      this();
      this.registerClasses(classes);
   }

   public ResourceConfig(Class... classes) {
      this((Set)Arrays.stream(classes).collect(Collectors.toSet()));
   }

   public ResourceConfig(ResourceConfig original) {
      this.cachedClasses = null;
      this.cachedClassesView = null;
      this.cachedSingletons = null;
      this.cachedSingletonsView = null;
      this.resetFinders = false;
      this.state = new State(original.state);
   }

   public final ResourceConfig addProperties(Map properties) {
      this.state.addProperties(properties);
      return this;
   }

   public ResourceConfig setProperties(Map properties) {
      this.state.setProperties(properties);
      return this;
   }

   public ResourceConfig property(String name, Object value) {
      this.state.property(name, value);
      return this;
   }

   public ResourceConfig register(Class componentClass) {
      this.invalidateCache();
      this.state.register(componentClass);
      return this;
   }

   public ResourceConfig register(Class componentClass, int bindingPriority) {
      this.invalidateCache();
      this.state.register(componentClass, bindingPriority);
      return this;
   }

   public ResourceConfig register(Class componentClass, Class... contracts) {
      this.invalidateCache();
      this.state.register(componentClass, contracts);
      return this;
   }

   public ResourceConfig register(Class componentClass, Map contracts) {
      this.invalidateCache();
      this.state.register(componentClass, contracts);
      return this;
   }

   public ResourceConfig register(Object component) {
      this.invalidateCache();
      this.state.register(component);
      return this;
   }

   public ResourceConfig register(Object component, int bindingPriority) {
      this.invalidateCache();
      this.state.register(component, bindingPriority);
      return this;
   }

   public ResourceConfig register(Object component, Class... contracts) {
      this.invalidateCache();
      this.state.register(component, contracts);
      return this;
   }

   public ResourceConfig register(Object component, Map contracts) {
      this.invalidateCache();
      this.state.register(component, contracts);
      return this;
   }

   public final ResourceConfig registerClasses(Set classes) {
      if (classes == null) {
         return this;
      } else {
         for(Class cls : classes) {
            this.register(cls);
         }

         return this;
      }
   }

   public final ResourceConfig registerClasses(Class... classes) {
      return classes == null ? this : this.registerClasses((Set)Arrays.stream(classes).collect(Collectors.toSet()));
   }

   public final ResourceConfig registerInstances(Set instances) {
      if (instances == null) {
         return this;
      } else {
         for(Object instance : instances) {
            this.register(instance);
         }

         return this;
      }
   }

   public final ResourceConfig registerInstances(Object... instances) {
      return instances == null ? this : this.registerInstances((Set)Arrays.stream(instances).collect(Collectors.toSet()));
   }

   public final ResourceConfig registerResources(Resource... resources) {
      return resources == null ? this : this.registerResources((Set)Arrays.stream(resources).collect(Collectors.toSet()));
   }

   public final ResourceConfig registerResources(Set resources) {
      if (resources == null) {
         return this;
      } else {
         this.state.registerResources(resources);
         return this;
      }
   }

   public final ResourceConfig registerFinder(ResourceFinder resourceFinder) {
      if (resourceFinder == null) {
         return this;
      } else {
         this.invalidateCache();
         this.state.registerFinder(resourceFinder);
         return this;
      }
   }

   public final ResourceConfig setApplicationName(String applicationName) {
      this.state.setApplicationName(applicationName);
      return this;
   }

   public final ResourceConfig setClassLoader(ClassLoader classLoader) {
      this.state.setClassLoader(classLoader);
      return this;
   }

   public final ResourceConfig packages(String... packages) {
      return this.packages(true, packages);
   }

   public final ResourceConfig packages(boolean recursive, String... packages) {
      return packages != null && packages.length != 0 ? this.registerFinder(new PackageNamesScanner(packages, recursive)) : this;
   }

   public final ResourceConfig packages(boolean recursive, ClassLoader classLoader, String... packages) {
      return packages != null && packages.length != 0 ? this.registerFinder(new PackageNamesScanner(classLoader, packages, recursive)) : this;
   }

   public final ResourceConfig files(String... files) {
      return this.files(true, files);
   }

   public final ResourceConfig files(boolean recursive, String... files) {
      return files != null && files.length != 0 ? this.registerFinder(new FilesScanner(files, recursive)) : this;
   }

   final void invalidateCache() {
      this.cachedClasses = null;
      this.cachedClassesView = null;
      this.cachedSingletons = null;
      this.cachedSingletonsView = null;
      if (this.resetFinders) {
         for(ResourceFinder finder : this.state.resourceFinders) {
            finder.reset();
         }

         this.resetFinders = false;
      }

   }

   final void lock() {
      State current = this.state;
      if (!(current instanceof ImmutableState)) {
         this.setupApplicationName();
         ExternalPropertiesConfigurationFactory.configure(this.state);
         this.state = new ImmutableState(current);
      }

   }

   public final ServerConfig getConfiguration() {
      return this;
   }

   public final Map getProperties() {
      return this.state.getProperties();
   }

   public final Object getProperty(String name) {
      return this.state.getProperty(name);
   }

   public Collection getPropertyNames() {
      return this.state.getPropertyNames();
   }

   public final boolean isProperty(String name) {
      return this.state.isProperty(name);
   }

   public final Set getClasses() {
      if (this.cachedClassesView == null) {
         this.cachedClasses = this._getClasses();
         this.cachedClassesView = Collections.unmodifiableSet(this.cachedClasses);
      }

      return this.cachedClassesView;
   }

   public final Set getInstances() {
      return this.getSingletons();
   }

   public final Set getSingletons() {
      if (this.cachedSingletonsView == null) {
         this.cachedSingletons = this._getSingletons();
         this.cachedSingletonsView = Collections.unmodifiableSet((Set)(this.cachedSingletons == null ? new HashSet() : this.cachedSingletons));
      }

      return this.cachedSingletonsView;
   }

   final ComponentBag getComponentBag() {
      return this.state.getComponentBag();
   }

   final void configureAutoDiscoverableProviders(InjectionManager injectionManager, Collection autoDiscoverables) {
      this.state.configureAutoDiscoverableProviders(injectionManager, autoDiscoverables, false);
   }

   final void configureForcedAutoDiscoverableProviders(InjectionManager injectionManager) {
      this.state.configureAutoDiscoverableProviders(injectionManager, Collections.emptyList(), true);
   }

   final void configureMetaProviders(InjectionManager injectionManager, ManagedObjectsFinalizer finalizer) {
      this.state.configureMetaProviders(injectionManager, finalizer);
   }

   public RuntimeType getRuntimeType() {
      return this.state.getRuntimeType();
   }

   public boolean isEnabled(Feature feature) {
      return this.state.isEnabled(feature);
   }

   public boolean isEnabled(Class featureClass) {
      return this.state.isEnabled(featureClass);
   }

   public boolean isRegistered(Object component) {
      return this.state.isRegistered(component);
   }

   public boolean isRegistered(Class componentClass) {
      return this.state.isRegistered(componentClass);
   }

   public Map getContracts(Class componentClass) {
      return this.state.getContracts(componentClass);
   }

   Set _getClasses() {
      Set<Class<?>> result = this.scanClasses();
      result.addAll(this.state.getClasses());
      return result;
   }

   private Set scanClasses() {
      Set<Class<?>> result = new HashSet();
      State _state = this.state;
      Set<ResourceFinder> rfs = new HashSet(_state.getResourceFinders());
      this.resetFinders = true;
      String[] classNames = this.parsePropertyValue("jersey.config.server.provider.classnames");
      if (classNames != null) {
         for(String className : classNames) {
            try {
               result.add(_state.getClassLoader().loadClass(className));
            } catch (ClassNotFoundException var25) {
               LOGGER.log(Level.CONFIG, LocalizationMessages.UNABLE_TO_LOAD_CLASS(className));
            }
         }
      }

      String[] packageNames = this.parsePropertyValue("jersey.config.server.provider.packages");
      if (packageNames != null) {
         Object p = this.getProperty("jersey.config.server.provider.scanning.recursive");
         boolean recursive = p == null || PropertiesHelper.isProperty(p);
         rfs.add(new PackageNamesScanner(packageNames, recursive));
      }

      String[] classPathElements = this.parsePropertyValue("jersey.config.server.provider.classpath");
      if (classPathElements != null) {
         rfs.add(new FilesScanner(classPathElements, true));
      }

      AnnotationAcceptingListener parentAfl = AnnotationAcceptingListener.newJaxrsResourceAndProviderListener(_state.getClassLoader());

      for(ResourceFinder resourceFinder : rfs) {
         AnnotationAcceptingListener afl = parentAfl;
         if (resourceFinder instanceof PackageNamesScanner) {
            ClassLoader classLoader = ((PackageNamesScanner)resourceFinder).getClassloader();
            if (!this.getClassLoader().equals(classLoader)) {
               afl = AnnotationAcceptingListener.newJaxrsResourceAndProviderListener(classLoader);
            }
         }

         while(resourceFinder.hasNext()) {
            String next = (String)resourceFinder.next();
            if (afl.accept(next)) {
               InputStream in = resourceFinder.open();

               try {
                  afl.process(next, in);
               } catch (IOException var23) {
                  LOGGER.log(Level.WARNING, LocalizationMessages.RESOURCE_CONFIG_UNABLE_TO_PROCESS(next));
               } finally {
                  try {
                     in.close();
                  } catch (IOException ex) {
                     LOGGER.log(Level.FINER, "Error closing resource stream.", ex);
                  }

               }
            }
         }

         if (afl != parentAfl) {
            result.addAll(afl.getAnnotatedClasses());
         }
      }

      result.addAll(parentAfl.getAnnotatedClasses());
      return result;
   }

   private String[] parsePropertyValue(String propertyName) {
      String[] classNames = null;
      Object o = this.state.getProperties().get(propertyName);
      if (o != null) {
         if (o instanceof String) {
            classNames = Tokenizer.tokenize((String)o);
         } else if (o instanceof String[]) {
            classNames = Tokenizer.tokenize((String[])o);
         }
      }

      return classNames;
   }

   Set getRegisteredClasses() {
      return this.state.getComponentBag().getRegistrations();
   }

   Set _getSingletons() {
      Set<Object> result = new HashSet();
      result.addAll(this.state.getInstances());
      return result;
   }

   public final Set getResources() {
      return this.state.getResources();
   }

   public final ClassLoader getClassLoader() {
      return this.state.getClassLoader();
   }

   public final Application getApplication() {
      return this._getApplication();
   }

   public final String getApplicationPath() {
      Application application;
      if (ResourceConfig.class.isInstance(this._getApplication())) {
         Application unwrap = unwrapCustomRootApplication((ResourceConfig)this._getApplication());
         application = unwrap != null ? unwrap : this._getApplication();
      } else {
         application = this._getApplication();
      }

      ApplicationPath appPath = (ApplicationPath)application.getClass().getAnnotation(ApplicationPath.class);
      String value;
      if (appPath != null && !appPath.value().isEmpty() && !appPath.value().trim().equals("/")) {
         String val = appPath.value().trim();
         value = UriComponent.encode(val.startsWith("/") ? val.substring(1) : val, Type.PATH);
      } else {
         value = null;
      }

      return value;
   }

   Application _getApplication() {
      return this;
   }

   public String getApplicationName() {
      return this.state.getApplicationName();
   }

   Class getApplicationClass() {
      return null;
   }

   final ResourceConfig setApplication(Application app) {
      return this._setApplication(app);
   }

   ResourceConfig _setApplication(Application app) {
      throw new UnsupportedOperationException();
   }

   static ResourceConfig createRuntimeConfig(Application application) {
      return application instanceof ResourceConfig ? new RuntimeConfig((ResourceConfig)application) : new RuntimeConfig(application);
   }

   private static Application unwrapCustomRootApplication(ResourceConfig resourceConfig) {
      Application app;
      for(app = null; resourceConfig != null; resourceConfig = (ResourceConfig)app) {
         app = resourceConfig.getApplication();
         if (app == resourceConfig) {
            return null;
         }

         if (!(app instanceof ResourceConfig)) {
            break;
         }
      }

      return app;
   }

   static Application unwrapApplication(Application application) {
      while(true) {
         if (application instanceof ResourceConfig) {
            Application wrappedApplication = ((ResourceConfig)application).getApplication();
            if (wrappedApplication != application) {
               application = wrappedApplication;
               continue;
            }
         }

         return application;
      }
   }

   private void setupApplicationName() {
      String appName = (String)ServerProperties.getValue(this.getProperties(), "jersey.config.server.application.name", (Object)null, String.class);
      if (appName != null && this.getApplicationName() == null) {
         this.setApplicationName(appName);
      }

   }

   private static class State extends CommonConfig implements ServerConfig {
      private final Set resourceFinders;
      private final Set resources;
      private final Set resourcesView;
      private volatile String applicationName;
      private volatile ClassLoader classLoader = null;

      public State() {
         super(RuntimeType.SERVER, ComponentBag.INCLUDE_ALL);
         this.classLoader = (ClassLoader)AccessController.doPrivileged(ReflectionHelper.getContextClassLoaderPA());
         this.resourceFinders = new HashSet();
         this.resources = new HashSet();
         this.resourcesView = Collections.unmodifiableSet(this.resources);
      }

      public State(State original) {
         super(original);
         this.classLoader = original.classLoader;
         this.applicationName = original.applicationName;
         this.resources = new HashSet(original.resources);
         this.resourcesView = Collections.unmodifiableSet(this.resources);
         this.resourceFinders = new HashSet(original.resourceFinders);
      }

      public void setClassLoader(ClassLoader classLoader) {
         this.classLoader = classLoader;
      }

      public void setApplicationName(String applicationName) {
         this.applicationName = applicationName;
      }

      public void registerResources(Set resources) {
         this.resources.addAll(resources);
      }

      public void registerFinder(ResourceFinder resourceFinder) {
         this.resourceFinders.add(resourceFinder);
      }

      protected Inflector getModelEnhancer(Class componentClass) {
         return (builder) -> {
            if (builder.getScope() == null && builder.getContracts().isEmpty() && Resource.getPath(componentClass) != null) {
               builder.scope(RequestScoped.class);
            }

            return builder.build();
         };
      }

      public State loadFrom(Configuration config) {
         super.loadFrom(config);
         this.resourceFinders.clear();
         this.resources.clear();
         State other = null;
         if (config instanceof ResourceConfig) {
            other = ((ResourceConfig)config).state;
         }

         if (config instanceof State) {
            other = (State)config;
         }

         if (other != null) {
            this.resourceFinders.addAll(other.resourceFinders);
            this.resources.addAll(other.resources);
         }

         return this;
      }

      public final Set getResources() {
         return this.resourcesView;
      }

      public ServerConfig getConfiguration() {
         return this;
      }

      public Set getResourceFinders() {
         return this.resourceFinders;
      }

      public ClassLoader getClassLoader() {
         return this.classLoader;
      }

      private String getApplicationName() {
         return this.applicationName;
      }
   }

   private static final class ImmutableState extends State {
      private ImmutableState(State original) {
         super(original);
      }

      public void setClassLoader(ClassLoader classLoader) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public void registerResources(Set resources) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public void registerFinder(ResourceFinder resourceFinder) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State addProperties(Map properties) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State property(String name, Object value) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State register(Class componentClass) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State register(Class componentClass, int bindingPriority) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State register(Class componentClass, Class... contracts) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State register(Class componentClass, Map contracts) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State register(Object component) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State register(Object component, int bindingPriority) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State register(Object component, Class... contracts) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State register(Object component, Map contracts) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public State setProperties(Map properties) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public void configureAutoDiscoverableProviders(InjectionManager injectionManager, Collection autoDiscoverables, boolean forcedOnly) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }

      public void configureMetaProviders(InjectionManager injectionManager, ManagedObjectsFinalizer finalizer) {
         throw new IllegalStateException(LocalizationMessages.RC_NOT_MODIFIABLE());
      }
   }

   private static class WrappingResourceConfig extends ResourceConfig {
      private Application application;
      private Class applicationClass;
      private final Set defaultClasses = new HashSet();

      public WrappingResourceConfig(Application application, Class applicationClass, Set defaultClasses) {
         if (application == null && applicationClass == null) {
            throw new IllegalArgumentException(LocalizationMessages.RESOURCE_CONFIG_ERROR_NULL_APPLICATIONCLASS());
         } else {
            this.application = application;
            this.applicationClass = applicationClass;
            if (defaultClasses != null) {
               this.defaultClasses.addAll(defaultClasses);
            }

            this.mergeApplications(application);
         }
      }

      ResourceConfig _setApplication(Application application) {
         this.application = application;
         this.applicationClass = null;
         this.mergeApplications(application);
         return this;
      }

      Application _getApplication() {
         return this.application;
      }

      Class getApplicationClass() {
         return this.applicationClass;
      }

      private void mergeApplications(Application application) {
         if (application instanceof ResourceConfig) {
            ResourceConfig rc = (ResourceConfig)application;
            super.registerResources(rc.getResources());
            rc.invalidateCache();
            rc.addProperties(super.getProperties());
            super.addProperties(rc.getProperties());
            super.setApplicationName(rc.getApplicationName());
            super.setClassLoader(rc.getClassLoader());
            rc.lock();
         } else if (application != null) {
            super.addProperties(application.getProperties());
         }

      }

      Set _getClasses() {
         Set<Class<?>> result = new HashSet();
         Set<Class<?>> applicationClasses = this.application.getClasses();
         result.addAll((Collection)(applicationClasses == null ? new HashSet() : applicationClasses));
         if (result.isEmpty() && this.getSingletons().isEmpty()) {
            result.addAll(this.defaultClasses);
         }

         if (!(this.application instanceof ResourceConfig)) {
            result.addAll(super._getClasses());
         }

         return result;
      }

      Set _getSingletons() {
         return this.application.getSingletons();
      }
   }

   private static class RuntimeConfig extends ResourceConfig {
      private final Set originalRegistrations;
      private final Application application;

      private RuntimeConfig(ResourceConfig original) {
         super(original);
         this.application = original;
         Application customRootApp = ResourceConfig.unwrapCustomRootApplication(original);
         Set<Object> rootSingletons = customRootApp != null ? this.registerComponentsOf(customRootApp) : null;
         this.originalRegistrations = Collections.newSetFromMap(new IdentityHashMap());
         this.originalRegistrations.addAll(super.getRegisteredClasses());
         Set<Object> origSingletons = customRootApp != null ? rootSingletons : original.getSingletons();
         Set<Object> externalInstances = (Set)origSingletons.stream().filter((external) -> !this.originalRegistrations.contains(external.getClass())).collect(Collectors.toSet());
         this.registerInstances(externalInstances);
         Set<Class<?>> externalClasses = (Set)original.getClasses().stream().filter((external) -> !this.originalRegistrations.contains(external)).collect(Collectors.toSet());
         this.registerClasses(externalClasses);
      }

      private Set registerComponentsOf(final Application application) {
         return (Set)Errors.process(new Producer() {
            public Set call() {
               Set<Object> singletons = application.getSingletons();
               if (singletons != null) {
                  RuntimeConfig.this.registerInstances((Set)singletons.stream().filter((input) -> {
                     if (input == null) {
                        Errors.warning(application, LocalizationMessages.NON_INSTANTIABLE_COMPONENT((Object)null));
                     }

                     return input != null;
                  }).collect(Collectors.toSet()));
               }

               Set<Class<?>> classes = application.getClasses();
               if (classes != null) {
                  RuntimeConfig.this.registerClasses((Set)classes.stream().filter((input) -> {
                     if (input == null) {
                        Errors.warning(application, LocalizationMessages.NON_INSTANTIABLE_COMPONENT((Object)null));
                     }

                     return input != null;
                  }).collect(Collectors.toSet()));
               }

               return singletons;
            }
         });
      }

      private RuntimeConfig(Application application) {
         this.application = application;
         if (application != null) {
            this.registerComponentsOf(application);
            this.addProperties(application.getProperties());
         }

         this.originalRegistrations = super.getRegisteredClasses();
      }

      Set _getClasses() {
         return super.state.getClasses();
      }

      Set _getSingletons() {
         return super.state.getInstances();
      }

      Set getRegisteredClasses() {
         return this.originalRegistrations;
      }

      Application _getApplication() {
         return this.application;
      }
   }
}
