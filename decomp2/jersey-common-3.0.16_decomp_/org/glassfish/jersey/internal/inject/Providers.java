package org.glassfish.jersey.internal.inject;

import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.client.RxInvokerProvider;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.DynamicFeature;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.WriterInterceptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.glassfish.jersey.JerseyPriorities;
import org.glassfish.jersey.innate.inject.spi.ExternalRegistrables;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedProvider;
import org.glassfish.jersey.spi.Contract;

public final class Providers {
   private static final Logger LOGGER = Logger.getLogger(Providers.class.getName());
   private static final Map JAX_RS_PROVIDER_INTERFACE_WHITELIST = getJaxRsProviderInterfaces();
   private static final Map EXTERNAL_PROVIDER_INTERFACE_WHITELIST = getExternalProviderInterfaces();

   private Providers() {
   }

   private static Map getJaxRsProviderInterfaces() {
      Map<Class<?>, ProviderRuntime> interfaces = new HashMap();
      interfaces.put(ContextResolver.class, Providers.ProviderRuntime.BOTH);
      interfaces.put(ExceptionMapper.class, Providers.ProviderRuntime.BOTH);
      interfaces.put(MessageBodyReader.class, Providers.ProviderRuntime.BOTH);
      interfaces.put(MessageBodyWriter.class, Providers.ProviderRuntime.BOTH);
      interfaces.put(ReaderInterceptor.class, Providers.ProviderRuntime.BOTH);
      interfaces.put(WriterInterceptor.class, Providers.ProviderRuntime.BOTH);
      interfaces.put(ParamConverterProvider.class, Providers.ProviderRuntime.BOTH);
      interfaces.put(ContainerRequestFilter.class, Providers.ProviderRuntime.SERVER);
      interfaces.put(ContainerResponseFilter.class, Providers.ProviderRuntime.SERVER);
      interfaces.put(DynamicFeature.class, Providers.ProviderRuntime.SERVER);
      interfaces.put(ClientResponseFilter.class, Providers.ProviderRuntime.CLIENT);
      interfaces.put(ClientRequestFilter.class, Providers.ProviderRuntime.CLIENT);
      interfaces.put(RxInvokerProvider.class, Providers.ProviderRuntime.CLIENT);
      return interfaces;
   }

   private static Map getExternalProviderInterfaces() {
      Map<Class<?>, ProviderRuntime> interfaces = new HashMap();
      interfaces.putAll(JAX_RS_PROVIDER_INTERFACE_WHITELIST);
      interfaces.put(Feature.class, Providers.ProviderRuntime.BOTH);
      interfaces.put(Binder.class, Providers.ProviderRuntime.BOTH);

      try {
         ServiceFinder<ExternalRegistrables> registerables = ServiceFinder.find(ExternalRegistrables.class, true);
         registerables.forEach((regs) -> regs.registrableContracts().forEach((pair) -> {
               ProviderRuntime var10000 = (ProviderRuntime)interfaces.put(pair.getContract(), Providers.ProviderRuntime.fromRuntimeType(pair.getRuntimeType()));
            }));
      } catch (Throwable t) {
         LOGGER.warning(LocalizationMessages.ERROR_EXTERNAL_REGISTERABLES_IGNORED(t.getMessage()));
      }

      return interfaces;
   }

   public static Set getProviders(InjectionManager injectionManager, Class contract) {
      Collection<ServiceHolder<T>> providers = getServiceHolders(injectionManager, contract);
      return getProviderClasses(providers);
   }

   public static Set getCustomProviders(InjectionManager injectionManager, Class contract) {
      List<ServiceHolder<T>> providers = getServiceHolders(injectionManager, contract, Comparator.comparingInt(Providers::getPriority), CustomAnnotationLiteral.INSTANCE);
      return getProviderClasses(providers);
   }

   public static Iterable getAllProviders(InjectionManager injectionManager, Class contract) {
      return getAllProviders(injectionManager, contract, (Comparator)null);
   }

   public static Iterable getAllRankedProviders(InjectionManager injectionManager, Class contract) {
      List<ServiceHolder<T>> providers = getServiceHolders(injectionManager, contract, CustomAnnotationLiteral.INSTANCE);
      providers.addAll(getServiceHolders(injectionManager, contract));
      LinkedHashMap<Class<T>, RankedProvider<T>> providerMap = new LinkedHashMap();

      for(ServiceHolder provider : providers) {
         Class<T> implClass = getImplementationClass(contract, provider);
         if (!providerMap.containsKey(implClass)) {
            Set<Type> contracts = isProxyGenerated(contract, provider) ? provider.getContractTypes() : null;
            providerMap.put(implClass, new RankedProvider(provider.getInstance(), provider.getRank(), contracts));
         }
      }

      return providerMap.values();
   }

   public static Iterable sortRankedProviders(RankedComparator comparator, Iterable providers) {
      return (Iterable)StreamSupport.stream(providers.spliterator(), false).sorted(comparator).map(RankedProvider::getProvider).collect(Collectors.toList());
   }

   public static Iterable getAllRankedSortedProviders(InjectionManager injectionManager, Class contract) {
      Iterable<RankedProvider<T>> allRankedProviders = getAllRankedProviders(injectionManager, contract);
      return sortRankedProviders(new RankedComparator(), allRankedProviders);
   }

   public static Iterable mergeAndSortRankedProviders(RankedComparator comparator, Iterable providerIterables) {
      return (Iterable)StreamSupport.stream(providerIterables.spliterator(), false).flatMap((rankedProviders) -> StreamSupport.stream(rankedProviders.spliterator(), false)).sorted(comparator).map(RankedProvider::getProvider).collect(Collectors.toList());
   }

   public static Iterable getAllProviders(InjectionManager injectionManager, Class contract, RankedComparator comparator) {
      return sortRankedProviders(comparator, getAllRankedProviders(injectionManager, contract));
   }

   public static Collection getAllServiceHolders(InjectionManager injectionManager, Class contract) {
      List<ServiceHolder<T>> providers = getServiceHolders(injectionManager, contract, Comparator.comparingInt(Providers::getPriority), CustomAnnotationLiteral.INSTANCE);
      providers.addAll(getServiceHolders(injectionManager, contract));
      LinkedHashMap<Class<T>, ServiceHolder<T>> providerMap = new LinkedHashMap();

      for(ServiceHolder provider : providers) {
         Class<T> implClass = getImplementationClass(contract, provider);
         if (!providerMap.containsKey(implClass)) {
            providerMap.put(implClass, provider);
         }
      }

      return providerMap.values();
   }

   private static List getServiceHolders(InjectionManager bm, Class contract, Annotation... qualifiers) {
      return bm.getAllServiceHolders(contract, qualifiers);
   }

   private static List getServiceHolders(InjectionManager injectionManager, Class contract, Comparator objectComparator, Annotation... qualifiers) {
      List<ServiceHolder<T>> serviceHolders = injectionManager.getAllServiceHolders(contract, qualifiers);
      serviceHolders.sort((o1, o2) -> objectComparator.compare(getImplementationClass(contract, o1), getImplementationClass(contract, o2)));
      return serviceHolders;
   }

   public static boolean isJaxRsProvider(Class clazz) {
      for(Class providerType : JAX_RS_PROVIDER_INTERFACE_WHITELIST.keySet()) {
         if (providerType.isAssignableFrom(clazz)) {
            return true;
         }
      }

      return false;
   }

   public static Iterable getAllProviders(InjectionManager injectionManager, Class contract, Comparator comparator) {
      List<T> providerList = new ArrayList(getProviderClasses(getAllServiceHolders(injectionManager, contract)));
      if (comparator != null) {
         providerList.sort(comparator);
      }

      return providerList;
   }

   private static Set getProviderClasses(Collection providers) {
      return (Set)providers.stream().map(Providers::holder2service).collect(Collectors.toCollection(LinkedHashSet::new));
   }

   private static Object holder2service(ServiceHolder holder) {
      return holder != null ? holder.getInstance() : null;
   }

   private static int getPriority(Class serviceClass) {
      return JerseyPriorities.getPriorityValue(serviceClass, 5000);
   }

   private static Class getImplementationClass(Class contract, ServiceHolder serviceHolder) {
      return isProxyGenerated(contract, serviceHolder) ? (Class)serviceHolder.getContractTypes().stream().filter((a) -> Class.class.isInstance(a)).map((a) -> (Class)a).reduce(contract, (a, b) -> a.isAssignableFrom(b) ? b : a) : serviceHolder.getImplementationClass();
   }

   private static boolean isProxyGenerated(Class contract, ServiceHolder serviceHolder) {
      return !contract.isAssignableFrom(serviceHolder.getImplementationClass());
   }

   public static Set getProviderContracts(Class clazz) {
      Set<Class<?>> contracts = Collections.newSetFromMap(new IdentityHashMap());
      computeProviderContracts(clazz, contracts);
      return contracts;
   }

   private static void computeProviderContracts(Class clazz, Set contracts) {
      for(Class contract : getImplementedContracts(clazz)) {
         if (isSupportedContract(contract)) {
            contracts.add(contract);
         }

         computeProviderContracts(contract, contracts);
      }

   }

   public static boolean checkProviderRuntime(Class component, ContractProvider model, RuntimeType runtimeConstraint, boolean scanned, boolean isResource) {
      Set<Class<?>> contracts = model.getContracts();
      ConstrainedTo constrainedTo = (ConstrainedTo)component.getAnnotation(ConstrainedTo.class);
      RuntimeType componentConstraint = constrainedTo == null ? null : constrainedTo.value();
      if (Feature.class.isAssignableFrom(component)) {
         return true;
      } else {
         StringBuilder warnings = new StringBuilder();

         boolean isProviderRuntimeCompatible;
         try {
            boolean foundComponentCompatible = componentConstraint == null;
            boolean foundRuntimeCompatibleContract = isResource && runtimeConstraint == RuntimeType.SERVER;

            for(Class contract : contracts) {
               RuntimeType contractConstraint = getContractConstraint(contract, componentConstraint);
               foundRuntimeCompatibleContract |= contractConstraint == null || contractConstraint == runtimeConstraint;
               if (componentConstraint != null) {
                  if (contractConstraint != componentConstraint) {
                     warnings.append(LocalizationMessages.WARNING_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(component.getName(), componentConstraint.name(), contract.getName(), contractConstraint.name())).append(" ");
                  } else {
                     foundComponentCompatible = true;
                  }
               }
            }

            if (foundComponentCompatible) {
               isProviderRuntimeCompatible = componentConstraint == null || componentConstraint == runtimeConstraint;
               if (!isProviderRuntimeCompatible && !scanned) {
                  warnings.append(LocalizationMessages.ERROR_PROVIDER_CONSTRAINED_TO_WRONG_RUNTIME(component.getName(), componentConstraint.name(), runtimeConstraint.name())).append(" ");
                  logProviderSkipped(warnings, component, isResource);
               }

               if (!foundRuntimeCompatibleContract && !scanned) {
                  warnings.append(LocalizationMessages.ERROR_PROVIDER_REGISTERED_WRONG_RUNTIME(component.getName(), runtimeConstraint.name())).append(" ");
                  logProviderSkipped(warnings, component, isResource);
                  boolean var20 = false;
                  return var20;
               }

               boolean var19 = isProviderRuntimeCompatible && foundRuntimeCompatibleContract;
               return var19;
            }

            warnings.append(LocalizationMessages.ERROR_PROVIDER_CONSTRAINED_TO_WRONG_PACKAGE(component.getName(), componentConstraint.name())).append(" ");
            logProviderSkipped(warnings, component, isResource);
            isProviderRuntimeCompatible = false;
         } finally {
            if (warnings.length() > 0) {
               LOGGER.log(Level.WARNING, warnings.toString());
            }

         }

         return isProviderRuntimeCompatible;
      }
   }

   private static void logProviderSkipped(StringBuilder sb, Class provider, boolean alsoResourceClass) {
      sb.append(alsoResourceClass ? LocalizationMessages.ERROR_PROVIDER_AND_RESOURCE_CONSTRAINED_TO_IGNORED(provider.getName()) : LocalizationMessages.ERROR_PROVIDER_CONSTRAINED_TO_IGNORED(provider.getName())).append(" ");
   }

   public static boolean isSupportedContract(Class type) {
      return EXTERNAL_PROVIDER_INTERFACE_WHITELIST.get(type) != null || type.isAnnotationPresent(Contract.class);
   }

   private static RuntimeType getContractConstraint(Class clazz, RuntimeType defaultConstraint) {
      ProviderRuntime jaxRsProvider = (ProviderRuntime)EXTERNAL_PROVIDER_INTERFACE_WHITELIST.get(clazz);
      RuntimeType result = null;
      if (jaxRsProvider != null) {
         result = jaxRsProvider.getRuntime();
      } else if (clazz.getAnnotation(Contract.class) != null) {
         ConstrainedTo constrainedToAnnotation = (ConstrainedTo)clazz.getAnnotation(ConstrainedTo.class);
         if (constrainedToAnnotation != null) {
            result = constrainedToAnnotation.value();
         }
      }

      return result == null ? defaultConstraint : result;
   }

   private static Iterable getImplementedContracts(Class clazz) {
      Collection<Class<?>> list = new LinkedList();
      Collections.addAll(list, clazz.getInterfaces());
      Class<?> superclass = clazz.getSuperclass();
      if (superclass != null) {
         list.add(superclass);
      }

      return list;
   }

   public static boolean isProvider(Class clazz) {
      return findFirstProviderContract(clazz);
   }

   public static void ensureContract(Class contract, Class... implementations) {
      if (implementations != null && implementations.length > 0) {
         StringBuilder invalidClassNames = new StringBuilder();

         for(Class impl : implementations) {
            if (!contract.isAssignableFrom(impl)) {
               if (invalidClassNames.length() > 0) {
                  invalidClassNames.append(", ");
               }

               invalidClassNames.append(impl.getName());
            }
         }

         if (invalidClassNames.length() > 0) {
            throw new IllegalArgumentException(LocalizationMessages.INVALID_SPI_CLASSES(contract.getName(), invalidClassNames.toString()));
         }
      }
   }

   private static boolean findFirstProviderContract(Class clazz) {
      for(Class contract : getImplementedContracts(clazz)) {
         if (isSupportedContract(contract)) {
            return true;
         }

         if (findFirstProviderContract(contract)) {
            return true;
         }
      }

      return false;
   }

   private static enum ProviderRuntime {
      BOTH((RuntimeType)null),
      SERVER(RuntimeType.SERVER),
      CLIENT(RuntimeType.CLIENT);

      private final RuntimeType runtime;

      private ProviderRuntime(RuntimeType runtime) {
         this.runtime = runtime;
      }

      public RuntimeType getRuntime() {
         return this.runtime;
      }

      private static ProviderRuntime fromRuntimeType(RuntimeType type) {
         return type == null ? BOTH : (type == RuntimeType.SERVER ? SERVER : CLIENT);
      }
   }
}
