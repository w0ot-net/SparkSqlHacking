package org.glassfish.hk2.internal;

import jakarta.inject.Named;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glassfish.hk2.api.DescriptorType;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;
import org.glassfish.hk2.utilities.ActiveDescriptorBuilder;

public class ActiveDescriptorBuilderImpl implements ActiveDescriptorBuilder {
   private String name;
   private final HashSet contracts = new HashSet();
   private Annotation scopeAnnotation = null;
   private Class scope = PerLookup.class;
   private final HashSet qualifiers = new HashSet();
   private final HashMap metadatas = new HashMap();
   private final Class implementation;
   private HK2Loader loader = null;
   private int rank = 0;
   private Boolean proxy = null;
   private Boolean proxyForSameScope = null;
   private DescriptorVisibility visibility;
   private String classAnalysisName;
   private Type implementationType;

   public ActiveDescriptorBuilderImpl(Class implementation) {
      this.visibility = DescriptorVisibility.NORMAL;
      this.classAnalysisName = null;
      this.implementation = implementation;
   }

   public ActiveDescriptorBuilder named(String name) throws IllegalArgumentException {
      this.name = name;
      return this;
   }

   public ActiveDescriptorBuilder to(Type contract) throws IllegalArgumentException {
      if (contract != null) {
         this.contracts.add(contract);
      }

      return this;
   }

   public ActiveDescriptorBuilder in(Annotation scopeAnnotation) throws IllegalArgumentException {
      if (scopeAnnotation == null) {
         throw new IllegalArgumentException();
      } else {
         this.scopeAnnotation = scopeAnnotation;
         this.scope = scopeAnnotation.annotationType();
         return this;
      }
   }

   public ActiveDescriptorBuilder in(Class scope) throws IllegalArgumentException {
      this.scope = scope;
      if (scope == null) {
         this.scopeAnnotation = null;
      } else if (this.scopeAnnotation != null && !scope.equals(this.scopeAnnotation.annotationType())) {
         String var10002 = scope.getName();
         throw new IllegalArgumentException("Scope set to different class (" + var10002 + ") from the scope annotation (" + this.scopeAnnotation.annotationType().getName());
      }

      return this;
   }

   public ActiveDescriptorBuilder qualifiedBy(Annotation annotation) throws IllegalArgumentException {
      if (annotation != null) {
         if (Named.class.equals(annotation.annotationType())) {
            this.name = ((Named)annotation).value();
         }

         this.qualifiers.add(annotation);
      }

      return this;
   }

   public ActiveDescriptorBuilder has(String key, String value) throws IllegalArgumentException {
      return this.has(key, Collections.singletonList(value));
   }

   public ActiveDescriptorBuilder has(String key, List values) throws IllegalArgumentException {
      if (key != null && values != null && values.size() > 0) {
         this.metadatas.put(key, values);
         return this;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public ActiveDescriptorBuilder ofRank(int rank) {
      this.rank = rank;
      return this;
   }

   public ActiveDescriptorBuilder proxy() {
      return this.proxy(true);
   }

   public ActiveDescriptorBuilder proxy(boolean forceProxy) {
      if (forceProxy) {
         this.proxy = Boolean.TRUE;
      } else {
         this.proxy = Boolean.FALSE;
      }

      return this;
   }

   public ActiveDescriptorBuilder proxyForSameScope() {
      return this.proxy(true);
   }

   public ActiveDescriptorBuilder proxyForSameScope(boolean forceProxyForSameScope) {
      if (forceProxyForSameScope) {
         this.proxyForSameScope = Boolean.TRUE;
      } else {
         this.proxyForSameScope = Boolean.FALSE;
      }

      return this;
   }

   public ActiveDescriptorBuilder andLoadWith(HK2Loader loader) throws IllegalArgumentException {
      this.loader = loader;
      return this;
   }

   public ActiveDescriptorBuilder analyzeWith(String serviceName) {
      this.classAnalysisName = serviceName;
      return this;
   }

   public ActiveDescriptorBuilder localOnly() {
      this.visibility = DescriptorVisibility.LOCAL;
      return this;
   }

   public ActiveDescriptorBuilder visibility(DescriptorVisibility visibility) {
      if (visibility == null) {
         throw new IllegalArgumentException();
      } else {
         this.visibility = visibility;
         return this;
      }
   }

   public ActiveDescriptorBuilder asType(Type t) {
      if (t == null) {
         throw new IllegalArgumentException();
      } else {
         this.implementationType = t;
         return this;
      }
   }

   public AbstractActiveDescriptor build() throws IllegalArgumentException {
      return new BuiltActiveDescriptor(this.implementation, this.contracts, this.scopeAnnotation, this.scope, this.name, this.qualifiers, DescriptorType.CLASS, this.visibility, this.rank, this.proxy, this.proxyForSameScope, this.classAnalysisName, this.metadatas, this.loader, this.implementationType);
   }

   /** @deprecated */
   @Deprecated
   public AbstractActiveDescriptor buildFactory() throws IllegalArgumentException {
      return this.buildProvideMethod();
   }

   public AbstractActiveDescriptor buildProvideMethod() throws IllegalArgumentException {
      return new BuiltActiveDescriptor(this.implementation, this.contracts, this.scopeAnnotation, this.scope, this.name, this.qualifiers, DescriptorType.PROVIDE_METHOD, this.visibility, this.rank, this.proxy, this.proxyForSameScope, this.classAnalysisName, this.metadatas, this.loader, this.implementationType);
   }

   private static class BuiltActiveDescriptor extends AbstractActiveDescriptor {
      private static final long serialVersionUID = 2434137639270026082L;
      private Class implementationClass;
      private Type implementationType;

      public BuiltActiveDescriptor() {
      }

      private BuiltActiveDescriptor(Class implementationClass, Set advertisedContracts, Annotation scopeAnnotation, Class scope, String name, Set qualifiers, DescriptorType descriptorType, DescriptorVisibility descriptorVisibility, int ranking, Boolean proxy, Boolean proxyForSameScope, String classAnalysisName, Map metadata, HK2Loader loader, Type implementationType) {
         super(advertisedContracts, scope, name, qualifiers, descriptorType, descriptorVisibility, ranking, proxy, proxyForSameScope, classAnalysisName, metadata);
         super.setReified(false);
         super.setLoader(loader);
         super.setScopeAsAnnotation(scopeAnnotation);
         this.implementationClass = implementationClass;
         super.setImplementation(implementationClass.getName());
         if (implementationType == null) {
            implementationType = implementationClass;
         }

         this.implementationType = implementationType;
      }

      public Class getImplementationClass() {
         return this.implementationClass;
      }

      public Type getImplementationType() {
         return this.implementationType;
      }

      public Object create(ServiceHandle root) {
         throw new AssertionError("Should not be called directly");
      }

      public void setImplementationType(Type t) {
         this.implementationType = t;
      }
   }
}
