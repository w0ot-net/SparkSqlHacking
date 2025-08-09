package org.glassfish.hk2.utilities.binding;

import jakarta.inject.Named;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.FactoryDescriptors;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;
import org.glassfish.hk2.utilities.ActiveDescriptorBuilder;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.glassfish.hk2.utilities.FactoryDescriptorsImpl;
import org.glassfish.hk2.utilities.reflection.ParameterizedTypeImpl;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;
import org.jvnet.hk2.component.MultiMap;

abstract class AbstractBindingBuilder implements ServiceBindingBuilder, NamedBindingBuilder, ScopedBindingBuilder, ScopedNamedBindingBuilder {
   Set contracts = new HashSet();
   HK2Loader loader = null;
   final MultiMap metadata = new MultiMap();
   Set qualifiers = new HashSet();
   Annotation scopeAnnotation = null;
   Class scope = null;
   Integer ranked = null;
   String name = null;
   Boolean proxiable = null;
   Boolean proxyForSameScope = null;
   Type implementationType = null;
   String analyzer = null;

   public AbstractBindingBuilder proxy(boolean proxiable) {
      this.proxiable = proxiable;
      return this;
   }

   public AbstractBindingBuilder proxyForSameScope(boolean proxyForSameScope) {
      this.proxyForSameScope = proxyForSameScope;
      return this;
   }

   public AbstractBindingBuilder analyzeWith(String analyzer) {
      this.analyzer = analyzer;
      return this;
   }

   public AbstractBindingBuilder to(Class contract) {
      this.contracts.add(contract);
      return this;
   }

   public AbstractBindingBuilder to(TypeLiteral contract) {
      this.contracts.add(contract.getType());
      return this;
   }

   public AbstractBindingBuilder to(Type contract) {
      this.contracts.add(contract);
      return this;
   }

   public AbstractBindingBuilder loadedBy(HK2Loader loader) {
      this.loader = loader;
      return this;
   }

   public AbstractBindingBuilder withMetadata(String key, String value) {
      this.metadata.add(key, value);
      return this;
   }

   public AbstractBindingBuilder withMetadata(String key, List values) {
      for(String value : values) {
         this.metadata.add(key, value);
      }

      return this;
   }

   public AbstractBindingBuilder qualifiedBy(Annotation annotation) {
      if (Named.class.equals(annotation.annotationType())) {
         this.name = ((Named)annotation).value();
      }

      this.qualifiers.add(annotation);
      return this;
   }

   public AbstractBindingBuilder in(Annotation scopeAnnotation) {
      this.scopeAnnotation = scopeAnnotation;
      return this;
   }

   public AbstractBindingBuilder in(Class scopeAnnotation) {
      this.scope = scopeAnnotation;
      return this;
   }

   public AbstractBindingBuilder named(String name) {
      this.name = name;
      return this;
   }

   public void ranked(int rank) {
      this.ranked = rank;
   }

   public AbstractBindingBuilder asType(Type t) {
      this.implementationType = t;
      return this;
   }

   abstract void complete(DynamicConfiguration var1, HK2Loader var2);

   static AbstractBindingBuilder create(Class serviceType, boolean bindAsContract) {
      return new ClassBasedBindingBuilder(serviceType, bindAsContract ? serviceType : null);
   }

   static AbstractBindingBuilder create(Type serviceType, boolean bindAsContract) {
      return (new ClassBasedBindingBuilder(ReflectionHelper.getRawClass(serviceType), bindAsContract ? serviceType : null)).asType(serviceType);
   }

   static AbstractBindingBuilder create(TypeLiteral serviceType, boolean bindAsContract) {
      Type type = serviceType.getType();
      return (new ClassBasedBindingBuilder(serviceType.getRawType(), bindAsContract ? serviceType.getType() : null)).asType(type);
   }

   static AbstractBindingBuilder create(Object service) {
      return new InstanceBasedBindingBuilder(service);
   }

   static AbstractBindingBuilder createFactoryBinder(Factory factory) {
      return new FactoryInstanceBasedBindingBuilder(factory);
   }

   static AbstractBindingBuilder createFactoryBinder(Class factoryType, Class factoryScope) {
      return new FactoryTypeBasedBindingBuilder(factoryType, factoryScope);
   }

   private static class ClassBasedBindingBuilder extends AbstractBindingBuilder {
      private final Class service;

      public ClassBasedBindingBuilder(Class service, Type serviceContractType) {
         this.service = service;
         if (serviceContractType != null) {
            super.contracts.add(serviceContractType);
         }

      }

      void complete(DynamicConfiguration configuration, HK2Loader defaultLoader) {
         if (this.loader == null) {
            this.loader = defaultLoader;
         }

         ActiveDescriptorBuilder builder = BuilderHelper.activeLink(this.service).named(this.name).andLoadWith(this.loader).analyzeWith(this.analyzer);
         if (this.scopeAnnotation != null) {
            builder.in(this.scopeAnnotation);
         }

         if (this.scope != null) {
            builder.in(this.scope);
         }

         if (this.ranked != null) {
            builder.ofRank(this.ranked);
         }

         for(String key : this.metadata.keySet()) {
            for(String value : this.metadata.get(key)) {
               builder.has(key, value);
            }
         }

         for(Annotation annotation : this.qualifiers) {
            builder.qualifiedBy(annotation);
         }

         for(Type contract : this.contracts) {
            builder.to(contract);
         }

         if (this.proxiable != null) {
            builder.proxy(this.proxiable);
         }

         if (this.proxyForSameScope != null) {
            builder.proxyForSameScope(this.proxyForSameScope);
         }

         if (this.implementationType != null) {
            builder.asType(this.implementationType);
         }

         configuration.bind((Descriptor)builder.build(), false);
      }
   }

   private static class InstanceBasedBindingBuilder extends AbstractBindingBuilder {
      private final Object service;

      public InstanceBasedBindingBuilder(Object service) {
         if (service == null) {
            throw new IllegalArgumentException();
         } else {
            this.service = service;
         }
      }

      void complete(DynamicConfiguration configuration, HK2Loader defaultLoader) {
         if (this.loader == null) {
            this.loader = defaultLoader;
         }

         AbstractActiveDescriptor<?> descriptor = BuilderHelper.createConstantDescriptor(this.service);
         descriptor.setName(this.name);
         descriptor.setLoader(this.loader);
         descriptor.setClassAnalysisName(this.analyzer);
         if (this.scope != null) {
            descriptor.setScope(this.scope.getName());
         }

         if (this.ranked != null) {
            descriptor.setRanking(this.ranked);
         }

         for(String key : this.metadata.keySet()) {
            for(String value : this.metadata.get(key)) {
               descriptor.addMetadata(key, value);
            }
         }

         for(Annotation annotation : this.qualifiers) {
            descriptor.addQualifierAnnotation(annotation);
         }

         for(Type contract : this.contracts) {
            descriptor.addContractType(contract);
         }

         if (this.proxiable != null) {
            descriptor.setProxiable(this.proxiable);
         }

         if (this.proxyForSameScope != null) {
            descriptor.setProxyForSameScope(this.proxyForSameScope);
         }

         configuration.bind((Descriptor)descriptor, false);
      }
   }

   private static class FactoryInstanceBasedBindingBuilder extends AbstractBindingBuilder {
      private final Factory factory;

      public FactoryInstanceBasedBindingBuilder(Factory factory) {
         this.factory = factory;
      }

      void complete(DynamicConfiguration configuration, HK2Loader defaultLoader) {
         if (this.loader == null) {
            this.loader = defaultLoader;
         }

         AbstractActiveDescriptor<?> factoryContractDescriptor = BuilderHelper.createConstantDescriptor(this.factory);
         factoryContractDescriptor.addContractType(this.factory.getClass());
         factoryContractDescriptor.setLoader(this.loader);
         ActiveDescriptorBuilder descriptorBuilder = BuilderHelper.activeLink(this.factory.getClass()).named(this.name).andLoadWith(this.loader).analyzeWith(this.analyzer);
         if (this.scope != null) {
            descriptorBuilder.in(this.scope);
         }

         if (this.ranked != null) {
            descriptorBuilder.ofRank(this.ranked);
         }

         for(Annotation qualifier : this.qualifiers) {
            factoryContractDescriptor.addQualifierAnnotation(qualifier);
            descriptorBuilder.qualifiedBy(qualifier);
         }

         for(Type contract : this.contracts) {
            factoryContractDescriptor.addContractType(new ParameterizedTypeImpl(Factory.class, new Type[]{contract}));
            descriptorBuilder.to(contract);
         }

         for(String key : this.metadata.keySet()) {
            for(String value : this.metadata.get(key)) {
               factoryContractDescriptor.addMetadata(key, value);
               descriptorBuilder.has(key, value);
            }
         }

         if (this.proxiable != null) {
            descriptorBuilder.proxy(this.proxiable);
         }

         if (this.proxyForSameScope != null) {
            descriptorBuilder.proxyForSameScope(this.proxyForSameScope);
         }

         configuration.bind((FactoryDescriptors)(new FactoryDescriptorsImpl(factoryContractDescriptor, descriptorBuilder.buildProvideMethod())));
      }
   }

   private static class FactoryTypeBasedBindingBuilder extends AbstractBindingBuilder {
      private final Class factoryClass;
      private final Class factoryScope;

      public FactoryTypeBasedBindingBuilder(Class factoryClass, Class factoryScope) {
         this.factoryClass = factoryClass;
         this.factoryScope = factoryScope;
      }

      void complete(DynamicConfiguration configuration, HK2Loader defaultLoader) {
         if (this.loader == null) {
            this.loader = defaultLoader;
         }

         ActiveDescriptorBuilder factoryDescriptorBuilder = BuilderHelper.activeLink(this.factoryClass).named(this.name).andLoadWith(this.loader).analyzeWith(this.analyzer);
         if (this.factoryScope != null) {
            factoryDescriptorBuilder.in(this.factoryScope);
         }

         ActiveDescriptorBuilder descriptorBuilder = BuilderHelper.activeLink(this.factoryClass).named(this.name).andLoadWith(this.loader).analyzeWith(this.analyzer);
         if (this.scope != null) {
            descriptorBuilder.in(this.scope);
         }

         if (this.ranked != null) {
            descriptorBuilder.ofRank(this.ranked);
         }

         for(Annotation qualifier : this.qualifiers) {
            factoryDescriptorBuilder.qualifiedBy(qualifier);
            descriptorBuilder.qualifiedBy(qualifier);
         }

         for(Type contract : this.contracts) {
            factoryDescriptorBuilder.to(new ParameterizedTypeImpl(Factory.class, new Type[]{contract}));
            descriptorBuilder.to(contract);
         }

         for(String key : this.metadata.keySet()) {
            for(String value : this.metadata.get(key)) {
               factoryDescriptorBuilder.has(key, value);
               descriptorBuilder.has(key, value);
            }
         }

         if (this.proxiable != null) {
            descriptorBuilder.proxy(this.proxiable);
         }

         if (this.proxyForSameScope != null) {
            descriptorBuilder.proxyForSameScope(this.proxyForSameScope);
         }

         configuration.bind((FactoryDescriptors)(new FactoryDescriptorsImpl(factoryDescriptorBuilder.build(), descriptorBuilder.buildProvideMethod())));
      }
   }
}
