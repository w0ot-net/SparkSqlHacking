package jakarta.validation;

import jakarta.validation.bootstrap.GenericBootstrap;
import jakarta.validation.bootstrap.ProviderSpecificBootstrap;
import jakarta.validation.spi.BootstrapState;
import jakarta.validation.spi.ValidationProvider;
import java.lang.ref.SoftReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.WeakHashMap;

public class Validation {
   public static ValidatorFactory buildDefaultValidatorFactory() {
      return byDefaultProvider().configure().buildValidatorFactory();
   }

   public static GenericBootstrap byDefaultProvider() {
      return new GenericBootstrapImpl();
   }

   public static ProviderSpecificBootstrap byProvider(Class providerType) {
      return new ProviderSpecificBootstrapImpl(providerType);
   }

   private static void clearDefaultValidationProviderResolverCache() {
      Validation.GetValidationProviderListAction.clearCache();
   }

   private static class ProviderSpecificBootstrapImpl implements ProviderSpecificBootstrap {
      private final Class validationProviderClass;
      private ValidationProviderResolver resolver;

      public ProviderSpecificBootstrapImpl(Class validationProviderClass) {
         this.validationProviderClass = validationProviderClass;
      }

      public ProviderSpecificBootstrap providerResolver(ValidationProviderResolver resolver) {
         this.resolver = resolver;
         return this;
      }

      public Configuration configure() {
         if (this.validationProviderClass == null) {
            throw new ValidationException("builder is mandatory. Use Validation.byDefaultProvider() to use the generic provider discovery mechanism");
         } else {
            GenericBootstrapImpl state = new GenericBootstrapImpl();
            if (this.resolver == null) {
               U provider = (U)((ValidationProvider)this.run(Validation.NewProviderInstance.action(this.validationProviderClass)));
               return provider.createSpecializedConfiguration(state);
            } else {
               state.providerResolver(this.resolver);

               List<ValidationProvider<?>> resolvers;
               try {
                  resolvers = this.resolver.getValidationProviders();
               } catch (RuntimeException re) {
                  throw new ValidationException("Unable to get available provider resolvers.", re);
               }

               for(ValidationProvider provider : resolvers) {
                  if (this.validationProviderClass.isAssignableFrom(provider.getClass())) {
                     ValidationProvider<T> specificProvider = (ValidationProvider)this.validationProviderClass.cast(provider);
                     return specificProvider.createSpecializedConfiguration(state);
                  }
               }

               throw new ValidationException("Unable to find provider: " + this.validationProviderClass);
            }
         }
      }

      private Object run(PrivilegedAction action) {
         return System.getSecurityManager() != null ? AccessController.doPrivileged(action) : action.run();
      }
   }

   private static class GenericBootstrapImpl implements GenericBootstrap, BootstrapState {
      private ValidationProviderResolver resolver;
      private ValidationProviderResolver defaultResolver;

      private GenericBootstrapImpl() {
      }

      public GenericBootstrap providerResolver(ValidationProviderResolver resolver) {
         this.resolver = resolver;
         return this;
      }

      public ValidationProviderResolver getValidationProviderResolver() {
         return this.resolver;
      }

      public ValidationProviderResolver getDefaultValidationProviderResolver() {
         if (this.defaultResolver == null) {
            this.defaultResolver = new DefaultValidationProviderResolver();
         }

         return this.defaultResolver;
      }

      public Configuration configure() {
         ValidationProviderResolver resolver = this.resolver == null ? this.getDefaultValidationProviderResolver() : this.resolver;

         List<ValidationProvider<?>> validationProviders;
         try {
            validationProviders = resolver.getValidationProviders();
         } catch (ValidationException e) {
            throw e;
         } catch (RuntimeException re) {
            throw new ValidationException("Unable to get available provider resolvers.", re);
         }

         if (validationProviders.isEmpty()) {
            String msg = "Unable to create a Configuration, because no Jakarta Bean Validation provider could be found. Add a provider like Hibernate Validator (RI) to your classpath.";
            throw new NoProviderFoundException(msg);
         } else {
            try {
               Configuration<?> config = ((ValidationProvider)resolver.getValidationProviders().get(0)).createGenericConfiguration(this);
               return config;
            } catch (RuntimeException re) {
               throw new ValidationException("Unable to instantiate Configuration.", re);
            }
         }
      }
   }

   private static class DefaultValidationProviderResolver implements ValidationProviderResolver {
      private DefaultValidationProviderResolver() {
      }

      public List getValidationProviders() {
         return Validation.GetValidationProviderListAction.getValidationProviderList();
      }
   }

   private static class GetValidationProviderListAction implements PrivilegedAction {
      private static final GetValidationProviderListAction INSTANCE = new GetValidationProviderListAction();
      private final WeakHashMap providersPerClassloader = new WeakHashMap();

      public static synchronized List getValidationProviderList() {
         return System.getSecurityManager() != null ? (List)AccessController.doPrivileged(INSTANCE) : INSTANCE.run();
      }

      public static synchronized void clearCache() {
         INSTANCE.providersPerClassloader.clear();
      }

      public List run() {
         ClassLoader classloader = Thread.currentThread().getContextClassLoader();
         List<ValidationProvider<?>> cachedContextClassLoaderProviderList = this.getCachedValidationProviders(classloader);
         if (cachedContextClassLoaderProviderList != null) {
            return cachedContextClassLoaderProviderList;
         } else {
            List<ValidationProvider<?>> validationProviderList = this.loadProviders(classloader);
            if (validationProviderList.isEmpty()) {
               classloader = DefaultValidationProviderResolver.class.getClassLoader();
               List<ValidationProvider<?>> cachedCurrentClassLoaderProviderList = this.getCachedValidationProviders(classloader);
               if (cachedCurrentClassLoaderProviderList != null) {
                  return cachedCurrentClassLoaderProviderList;
               }

               validationProviderList = this.loadProviders(classloader);
            }

            this.cacheValidationProviders(classloader, validationProviderList);
            return validationProviderList;
         }
      }

      private List loadProviders(ClassLoader classloader) {
         ServiceLoader<ValidationProvider> loader = ServiceLoader.load(ValidationProvider.class, classloader);
         Iterator<ValidationProvider> providerIterator = loader.iterator();
         List<ValidationProvider<?>> validationProviderList = new ArrayList();

         while(providerIterator.hasNext()) {
            try {
               validationProviderList.add((ValidationProvider)providerIterator.next());
            } catch (ServiceConfigurationError var6) {
            }
         }

         return validationProviderList;
      }

      private synchronized List getCachedValidationProviders(ClassLoader classLoader) {
         SoftReference<List<ValidationProvider<?>>> ref = (SoftReference)this.providersPerClassloader.get(classLoader);
         return ref != null ? (List)ref.get() : null;
      }

      private synchronized void cacheValidationProviders(ClassLoader classLoader, List providers) {
         this.providersPerClassloader.put(classLoader, new SoftReference(providers));
      }
   }

   private static class NewProviderInstance implements PrivilegedAction {
      private final Class clazz;

      public static NewProviderInstance action(Class clazz) {
         return new NewProviderInstance(clazz);
      }

      private NewProviderInstance(Class clazz) {
         this.clazz = clazz;
      }

      public ValidationProvider run() {
         try {
            return (ValidationProvider)this.clazz.newInstance();
         } catch (IllegalAccessException | RuntimeException | InstantiationException e) {
            throw new ValidationException("Cannot instantiate provider type: " + this.clazz, e);
         }
      }
   }
}
