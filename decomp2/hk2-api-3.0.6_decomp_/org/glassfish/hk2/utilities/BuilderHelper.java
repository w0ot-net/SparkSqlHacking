package org.glassfish.hk2.utilities;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.DescriptorType;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.IndexedFilter;
import org.glassfish.hk2.api.Metadata;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.ProxyForSameScope;
import org.glassfish.hk2.api.Rank;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.UseProxy;
import org.glassfish.hk2.api.Visibility;
import org.glassfish.hk2.internal.ActiveDescriptorBuilderImpl;
import org.glassfish.hk2.internal.ConstantActiveDescriptor;
import org.glassfish.hk2.internal.DescriptorBuilderImpl;
import org.glassfish.hk2.internal.IndexedFilterImpl;
import org.glassfish.hk2.internal.SpecificFilterImpl;
import org.glassfish.hk2.internal.StarFilter;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;
import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.ContractsProvided;
import org.jvnet.hk2.annotations.Service;

public class BuilderHelper {
   public static final String NAME_KEY = "name";
   public static final String QUALIFIER_KEY = "qualifier";
   public static final String TOKEN_SEPARATOR = ";";

   public static IndexedFilter createContractFilter(String contract) {
      return new IndexedFilterImpl(contract, (String)null);
   }

   public static IndexedFilter createNameFilter(String name) {
      return new IndexedFilterImpl((String)null, name);
   }

   public static IndexedFilter createNameAndContractFilter(String contract, String name) {
      return new IndexedFilterImpl(contract, name);
   }

   public static IndexedFilter createTokenizedFilter(String tokenString) throws IllegalArgumentException {
      if (tokenString == null) {
         throw new IllegalArgumentException("null passed to createTokenizedFilter");
      } else {
         StringTokenizer st = new StringTokenizer(tokenString, ";");
         final String contract = null;
         final String name = null;
         final Set<String> qualifiers = new LinkedHashSet();
         boolean firstToken = true;
         if (tokenString.startsWith(";")) {
            firstToken = false;
         }

         while(st.hasMoreTokens()) {
            String token = st.nextToken();
            if (firstToken) {
               firstToken = false;
               if (token.length() > 0) {
                  contract = token;
               }
            } else {
               int index = token.indexOf(61);
               if (index < 0) {
                  throw new IllegalArgumentException("No = character found in token " + token);
               }

               String leftHandSide = token.substring(0, index);
               String rightHandSide = token.substring(index + 1);
               if (rightHandSide.length() <= 0) {
                  throw new IllegalArgumentException("No value found in token " + token);
               }

               if ("name".equals(leftHandSide)) {
                  name = rightHandSide;
               } else {
                  if (!"qualifier".equals(leftHandSide)) {
                     throw new IllegalArgumentException("Unknown key: " + leftHandSide);
                  }

                  qualifiers.add(rightHandSide);
               }
            }
         }

         return new IndexedFilter() {
            public boolean matches(Descriptor d) {
               return qualifiers.isEmpty() ? true : d.getQualifiers().containsAll(qualifiers);
            }

            public String getAdvertisedContract() {
               return contract;
            }

            public String getName() {
               return name;
            }

            public String toString() {
               String cField = contract == null ? "" : contract;
               String nField = name == null ? "" : ";name=" + name;
               StringBuffer sb = new StringBuffer();

               for(String q : qualifiers) {
                  sb.append(";qualifier=" + q);
               }

               return "TokenizedFilter(" + cField + nField + sb.toString() + ")";
            }
         };
      }
   }

   public static IndexedFilter createSpecificDescriptorFilter(Descriptor descriptor) {
      String contract = ServiceLocatorUtilities.getBestContract(descriptor);
      String name = descriptor.getName();
      if (descriptor.getServiceId() == null) {
         throw new IllegalArgumentException("The descriptor must have a specific service ID");
      } else if (descriptor.getLocatorId() == null) {
         throw new IllegalArgumentException("The descriptor must have a specific locator ID");
      } else {
         return new SpecificFilterImpl(contract, name, descriptor.getServiceId(), descriptor.getLocatorId());
      }
   }

   public static IndexedFilter createDescriptorFilter(Descriptor descriptorImpl, boolean deepCopy) {
      final Descriptor filterDescriptor = (Descriptor)(deepCopy ? new DescriptorImpl(descriptorImpl) : descriptorImpl);
      return new IndexedFilter() {
         public boolean matches(Descriptor d) {
            return DescriptorImpl.descriptorEquals(filterDescriptor, d);
         }

         public String getAdvertisedContract() {
            Set<String> contracts = filterDescriptor.getAdvertisedContracts();
            return contracts != null && !contracts.isEmpty() ? (String)contracts.iterator().next() : null;
         }

         public String getName() {
            return filterDescriptor.getName();
         }
      };
   }

   public static IndexedFilter createDescriptorFilter(Descriptor descriptorImpl) {
      return createDescriptorFilter(descriptorImpl, true);
   }

   public static Filter allFilter() {
      return StarFilter.getDescriptorFilter();
   }

   public static DescriptorBuilder link(String implementationClass, boolean addToContracts) throws IllegalArgumentException {
      if (implementationClass == null) {
         throw new IllegalArgumentException();
      } else {
         return new DescriptorBuilderImpl(implementationClass, addToContracts);
      }
   }

   public static DescriptorBuilder link(String implementationClass) throws IllegalArgumentException {
      return link(implementationClass, true);
   }

   public static DescriptorBuilder link(Class implementationClass, boolean addToContracts) throws IllegalArgumentException {
      if (implementationClass == null) {
         throw new IllegalArgumentException();
      } else {
         DescriptorBuilder builder = link(implementationClass.getName(), addToContracts);
         return builder;
      }
   }

   public static DescriptorBuilder link(Class implementationClass) throws IllegalArgumentException {
      if (implementationClass == null) {
         throw new IllegalArgumentException();
      } else {
         boolean isFactory = Factory.class.isAssignableFrom(implementationClass);
         DescriptorBuilder db = link(implementationClass, !isFactory);
         return db;
      }
   }

   public static ActiveDescriptorBuilder activeLink(Class implementationClass) throws IllegalArgumentException {
      if (implementationClass == null) {
         throw new IllegalArgumentException();
      } else {
         return new ActiveDescriptorBuilderImpl(implementationClass);
      }
   }

   public static AbstractActiveDescriptor createConstantDescriptor(Object constant) {
      if (constant == null) {
         throw new IllegalArgumentException();
      } else {
         Class<?> cClass = constant.getClass();
         ContractsProvided provided = (ContractsProvided)cClass.getAnnotation(ContractsProvided.class);
         Set<Type> contracts;
         if (provided != null) {
            contracts = new HashSet();

            for(Class specified : provided.value()) {
               contracts.add(specified);
            }
         } else {
            contracts = ReflectionHelper.getAdvertisedTypesFromObject(constant, Contract.class);
         }

         return createConstantDescriptor(constant, ReflectionHelper.getName(constant.getClass()), (Type[])contracts.toArray(new Type[contracts.size()]));
      }
   }

   public static int getRank(Class fromClass) {
      while(fromClass != null && !Object.class.equals(fromClass)) {
         Rank rank = (Rank)fromClass.getAnnotation(Rank.class);
         if (rank != null) {
            return rank.value();
         }

         fromClass = fromClass.getSuperclass();
      }

      return 0;
   }

   public static AbstractActiveDescriptor createConstantDescriptor(Object constant, String name, Type... contracts) {
      if (constant == null) {
         throw new IllegalArgumentException();
      } else {
         Annotation scope = ReflectionHelper.getScopeAnnotationFromObject(constant);
         Class<? extends Annotation> scopeClass = scope == null ? PerLookup.class : scope.annotationType();
         Set<Annotation> qualifiers = ReflectionHelper.getQualifiersFromObject(constant);
         Map<String, List<String>> metadata = new HashMap();
         if (scope != null) {
            getMetadataValues(scope, metadata);
         }

         for(Annotation qualifier : qualifiers) {
            getMetadataValues(qualifier, metadata);
         }

         Set<Type> contractsAsSet;
         if (contracts.length <= 0) {
            contractsAsSet = ReflectionHelper.getAdvertisedTypesFromObject(constant, Contract.class);
         } else {
            contractsAsSet = new LinkedHashSet();

            for(Type cType : contracts) {
               contractsAsSet.add(cType);
            }
         }

         Boolean proxy = null;
         UseProxy up = (UseProxy)constant.getClass().getAnnotation(UseProxy.class);
         if (up != null) {
            proxy = up.value();
         }

         Boolean proxyForSameScope = null;
         ProxyForSameScope pfss = (ProxyForSameScope)constant.getClass().getAnnotation(ProxyForSameScope.class);
         if (pfss != null) {
            proxyForSameScope = pfss.value();
         }

         DescriptorVisibility visibility = DescriptorVisibility.NORMAL;
         Visibility vi = (Visibility)constant.getClass().getAnnotation(Visibility.class);
         if (vi != null) {
            visibility = vi.value();
         }

         String classAnalysisName = null;
         Service service = (Service)constant.getClass().getAnnotation(Service.class);
         if (service != null) {
            classAnalysisName = service.analyzer();
         }

         int rank = getRank(constant.getClass());
         return new ConstantActiveDescriptor(constant, contractsAsSet, scopeClass, name, qualifiers, visibility, proxy, proxyForSameScope, classAnalysisName, metadata, rank);
      }
   }

   public static DescriptorImpl createDescriptorFromClass(Class clazz) {
      if (clazz == null) {
         return new DescriptorImpl();
      } else {
         Set<String> contracts = ReflectionHelper.getContractsFromClass(clazz, Contract.class);
         String name = ReflectionHelper.getName(clazz);
         String scope = ReflectionHelper.getScopeFromClass(clazz, ServiceLocatorUtilities.getPerLookupAnnotation()).annotationType().getName();
         Set<String> qualifiers = ReflectionHelper.getQualifiersFromClass(clazz);
         DescriptorType type = DescriptorType.CLASS;
         if (Factory.class.isAssignableFrom(clazz)) {
            type = DescriptorType.PROVIDE_METHOD;
         }

         Boolean proxy = null;
         UseProxy up = (UseProxy)clazz.getAnnotation(UseProxy.class);
         if (up != null) {
            proxy = up.value();
         }

         Boolean proxyForSameScope = null;
         ProxyForSameScope pfss = (ProxyForSameScope)clazz.getAnnotation(ProxyForSameScope.class);
         if (pfss != null) {
            proxyForSameScope = pfss.value();
         }

         DescriptorVisibility visibility = DescriptorVisibility.NORMAL;
         Visibility vi = (Visibility)clazz.getAnnotation(Visibility.class);
         if (vi != null) {
            visibility = vi.value();
         }

         int rank = getRank(clazz);
         return new DescriptorImpl(contracts, name, scope, clazz.getName(), new HashMap(), qualifiers, type, visibility, (HK2Loader)null, rank, proxy, proxyForSameScope, (String)null, (Long)null, (Long)null);
      }
   }

   public static DescriptorImpl deepCopyDescriptor(Descriptor copyMe) {
      return new DescriptorImpl(copyMe);
   }

   public static void getMetadataValues(Annotation annotation, Map metadata) {
      if (annotation != null && metadata != null) {
         final Class<? extends Annotation> annotationClass = annotation.annotationType();
         Method[] annotationMethods = (Method[])AccessController.doPrivileged(new PrivilegedAction() {
            public Method[] run() {
               return annotationClass.getDeclaredMethods();
            }
         });

         for(Method annotationMethod : annotationMethods) {
            Metadata metadataAnno = (Metadata)annotationMethod.getAnnotation(Metadata.class);
            if (metadataAnno != null) {
               String key = metadataAnno.value();

               Object addMe;
               try {
                  addMe = ReflectionHelper.invoke(annotation, annotationMethod, new Object[0], false);
               } catch (Throwable th) {
                  throw new MultiException(th);
               }

               if (addMe != null) {
                  String addMeString;
                  if (addMe instanceof Class) {
                     addMeString = ((Class)addMe).getName();
                  } else if (addMe.getClass().isArray()) {
                     int length = Array.getLength(addMe);

                     for(int lcv = 0; lcv < length; ++lcv) {
                        Object iValue = Array.get(addMe, lcv);
                        if (iValue != null) {
                           if (iValue instanceof Class) {
                              String cName = ((Class)iValue).getName();
                              ReflectionHelper.addMetadata(metadata, key, cName);
                           } else {
                              ReflectionHelper.addMetadata(metadata, key, iValue.toString());
                           }
                        }
                     }

                     addMeString = null;
                  } else {
                     addMeString = addMe.toString();
                  }

                  if (addMeString != null) {
                     ReflectionHelper.addMetadata(metadata, key, addMeString);
                  }
               }
            }
         }

      } else {
         throw new IllegalArgumentException();
      }
   }

   public static ServiceHandle createConstantServiceHandle(final Object obj) {
      return new ServiceHandle() {
         private Object serviceData;

         public Object getService() {
            return obj;
         }

         public ActiveDescriptor getActiveDescriptor() {
            return null;
         }

         public boolean isActive() {
            return true;
         }

         public void close() {
         }

         public synchronized void setServiceData(Object serviceData) {
            this.serviceData = serviceData;
         }

         public synchronized Object getServiceData() {
            return this.serviceData;
         }

         public List getSubHandles() {
            return Collections.emptyList();
         }
      };
   }

   public static boolean filterMatches(Descriptor baseDescriptor, Filter filter) {
      if (baseDescriptor == null) {
         throw new IllegalArgumentException();
      } else if (filter == null) {
         return true;
      } else {
         if (filter instanceof IndexedFilter) {
            IndexedFilter indexedFilter = (IndexedFilter)filter;
            String indexContract = indexedFilter.getAdvertisedContract();
            if (indexContract != null && !baseDescriptor.getAdvertisedContracts().contains(indexContract)) {
               return false;
            }

            String indexName = indexedFilter.getName();
            if (indexName != null) {
               if (baseDescriptor.getName() == null) {
                  return false;
               }

               if (!indexName.equals(baseDescriptor.getName())) {
                  return false;
               }
            }
         }

         return filter.matches(baseDescriptor);
      }
   }
}
