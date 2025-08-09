package org.glassfish.hk2.internal;

import jakarta.inject.Named;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glassfish.hk2.api.DescriptorType;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.FactoryDescriptors;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.utilities.DescriptorBuilder;
import org.glassfish.hk2.utilities.DescriptorImpl;
import org.glassfish.hk2.utilities.FactoryDescriptorsImpl;

public class DescriptorBuilderImpl implements DescriptorBuilder {
   private String name;
   private final HashSet contracts = new HashSet();
   private String scope;
   private final HashSet qualifiers = new HashSet();
   private final HashMap metadatas = new HashMap();
   private String implementation;
   private HK2Loader loader = null;
   private int rank = 0;
   private Boolean proxy = null;
   private Boolean proxyForSameScope = null;
   private DescriptorVisibility visibility;
   private String analysisName;

   public DescriptorBuilderImpl() {
      this.visibility = DescriptorVisibility.NORMAL;
      this.analysisName = null;
   }

   public DescriptorBuilderImpl(String implementation, boolean addToContracts) {
      this.visibility = DescriptorVisibility.NORMAL;
      this.analysisName = null;
      this.implementation = implementation;
      if (addToContracts) {
         this.contracts.add(implementation);
      }

   }

   public DescriptorBuilder named(String name) throws IllegalArgumentException {
      if (this.name != null) {
         throw new IllegalArgumentException();
      } else {
         this.name = name;
         this.qualifiers.add(Named.class.getName());
         return this;
      }
   }

   public DescriptorBuilder to(Class contract) throws IllegalArgumentException {
      if (contract == null) {
         throw new IllegalArgumentException();
      } else {
         return this.to(contract.getName());
      }
   }

   public DescriptorBuilder to(String contract) throws IllegalArgumentException {
      if (contract == null) {
         throw new IllegalArgumentException();
      } else {
         this.contracts.add(contract);
         return this;
      }
   }

   public DescriptorBuilder in(Class scope) throws IllegalArgumentException {
      if (scope == null) {
         throw new IllegalArgumentException();
      } else {
         return this.in(scope.getName());
      }
   }

   public DescriptorBuilder in(String scope) throws IllegalArgumentException {
      if (scope == null) {
         throw new IllegalArgumentException();
      } else {
         this.scope = scope;
         return this;
      }
   }

   public DescriptorBuilder qualifiedBy(Annotation annotation) throws IllegalArgumentException {
      if (annotation == null) {
         throw new IllegalArgumentException();
      } else {
         if (Named.class.equals(annotation.annotationType())) {
            this.name = ((Named)annotation).value();
         }

         return this.qualifiedBy(annotation.annotationType().getName());
      }
   }

   public DescriptorBuilder qualifiedBy(String annotation) throws IllegalArgumentException {
      if (annotation == null) {
         throw new IllegalArgumentException();
      } else {
         this.qualifiers.add(annotation);
         return this;
      }
   }

   public DescriptorBuilder has(String key, String value) throws IllegalArgumentException {
      if (key != null && value != null) {
         LinkedList<String> values = new LinkedList();
         values.add(value);
         return this.has(key, (List)values);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public DescriptorBuilder has(String key, List values) throws IllegalArgumentException {
      if (key != null && values != null && values.size() > 0) {
         this.metadatas.put(key, values);
         return this;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public DescriptorBuilder ofRank(int rank) {
      this.rank = rank;
      return this;
   }

   public DescriptorBuilder proxy() {
      return this.proxy(true);
   }

   public DescriptorBuilder proxy(boolean forceProxy) {
      if (forceProxy) {
         this.proxy = Boolean.TRUE;
      } else {
         this.proxy = Boolean.FALSE;
      }

      return this;
   }

   public DescriptorBuilder proxyForSameScope() {
      return this.proxyForSameScope(true);
   }

   public DescriptorBuilder proxyForSameScope(boolean proxyForSameScope) {
      if (proxyForSameScope) {
         this.proxyForSameScope = Boolean.TRUE;
      } else {
         this.proxyForSameScope = Boolean.FALSE;
      }

      return this;
   }

   public DescriptorBuilder localOnly() {
      this.visibility = DescriptorVisibility.LOCAL;
      return this;
   }

   public DescriptorBuilder visibility(DescriptorVisibility visibility) {
      if (visibility == null) {
         throw new IllegalArgumentException();
      } else {
         this.visibility = visibility;
         return this;
      }
   }

   public DescriptorBuilder andLoadWith(HK2Loader loader) throws IllegalArgumentException {
      if (this.loader != null) {
         throw new IllegalArgumentException();
      } else {
         this.loader = loader;
         return this;
      }
   }

   public DescriptorBuilder analyzeWith(String serviceName) {
      this.analysisName = serviceName;
      return this;
   }

   public DescriptorImpl build() throws IllegalArgumentException {
      return new DescriptorImpl(this.contracts, this.name, this.scope, this.implementation, this.metadatas, this.qualifiers, DescriptorType.CLASS, this.visibility, this.loader, this.rank, this.proxy, this.proxyForSameScope, this.analysisName, (Long)null, (Long)null);
   }

   public FactoryDescriptors buildFactory(String factoryScope) throws IllegalArgumentException {
      Set<String> factoryContracts = new HashSet();
      factoryContracts.add(this.implementation);
      factoryContracts.add(Factory.class.getName());
      Set<String> factoryQualifiers = Collections.emptySet();
      Map<String, List<String>> factoryMetadata = Collections.emptyMap();
      DescriptorImpl asService = new DescriptorImpl(factoryContracts, (String)null, factoryScope, this.implementation, factoryMetadata, factoryQualifiers, DescriptorType.CLASS, DescriptorVisibility.NORMAL, this.loader, this.rank, (Boolean)null, (Boolean)null, this.analysisName, (Long)null, (Long)null);
      Set<String> serviceContracts = new HashSet(this.contracts);
      if (this.implementation != null) {
         serviceContracts.remove(this.implementation);
      }

      DescriptorImpl asFactory = new DescriptorImpl(serviceContracts, this.name, this.scope, this.implementation, this.metadatas, this.qualifiers, DescriptorType.PROVIDE_METHOD, this.visibility, this.loader, this.rank, this.proxy, this.proxyForSameScope, (String)null, (Long)null, (Long)null);
      return new FactoryDescriptorsImpl(asService, asFactory);
   }

   public FactoryDescriptors buildFactory() throws IllegalArgumentException {
      return this.buildFactory(PerLookup.class.getName());
   }

   public FactoryDescriptors buildFactory(Class factoryScope) throws IllegalArgumentException {
      if (factoryScope == null) {
         factoryScope = PerLookup.class;
      }

      return this.buildFactory(factoryScope.getName());
   }
}
