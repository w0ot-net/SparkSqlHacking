package org.glassfish.jersey.model;

import jakarta.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class ContractProvider implements Scoped, NameBound {
   public static final int NO_PRIORITY = -1;
   private final Class implementationClass;
   private final Map contracts;
   private final int defaultPriority;
   private final Set nameBindings;
   private final Class scope;

   public static Builder builder(Class implementationClass) {
      return new Builder(implementationClass);
   }

   public static Builder builder(ContractProvider original) {
      return new Builder(original);
   }

   private ContractProvider(Class implementationClass, Class scope, Map contracts, int defaultPriority, Set nameBindings) {
      this.implementationClass = implementationClass;
      this.scope = scope;
      this.contracts = contracts;
      this.defaultPriority = defaultPriority;
      this.nameBindings = nameBindings;
   }

   public Class getScope() {
      return this.scope;
   }

   public Class getImplementationClass() {
      return this.implementationClass;
   }

   public Set getContracts() {
      return this.contracts.keySet();
   }

   public Map getContractMap() {
      return this.contracts;
   }

   public boolean isNameBound() {
      return !this.nameBindings.isEmpty();
   }

   public int getPriority(Class contract) {
      return this.contracts.containsKey(contract) ? (Integer)this.contracts.get(contract) : this.defaultPriority;
   }

   public Set getNameBindings() {
      return this.nameBindings;
   }

   public static final class Builder {
      private static final ContractProvider EMPTY_MODEL = new ContractProvider((Class)null, Singleton.class, Collections.emptyMap(), -1, Collections.emptySet());
      private Class implementationClass;
      private Class scope;
      private Map contracts;
      private int defaultPriority;
      private Set nameBindings;

      private Builder(Class implementationClass) {
         this.implementationClass = null;
         this.scope = null;
         this.contracts = new HashMap();
         this.defaultPriority = -1;
         this.nameBindings = Collections.newSetFromMap(new IdentityHashMap());
         this.implementationClass = implementationClass;
      }

      private Builder(ContractProvider original) {
         this.implementationClass = null;
         this.scope = null;
         this.contracts = new HashMap();
         this.defaultPriority = -1;
         this.nameBindings = Collections.newSetFromMap(new IdentityHashMap());
         this.implementationClass = original.implementationClass;
         this.scope = original.scope;
         this.contracts.putAll(original.contracts);
         this.defaultPriority = original.defaultPriority;
         this.nameBindings.addAll(original.nameBindings);
      }

      public Builder scope(Class scope) {
         this.scope = scope;
         return this;
      }

      public Builder addContract(Class contract) {
         return this.addContract(contract, this.defaultPriority);
      }

      public Builder addContract(Class contract, int priority) {
         this.contracts.put(contract, priority);
         return this;
      }

      public Builder addContracts(Map contracts) {
         this.contracts.putAll(contracts);
         return this;
      }

      public Builder addContracts(Collection contracts) {
         for(Class contract : contracts) {
            this.addContract(contract, this.defaultPriority);
         }

         return this;
      }

      public Builder defaultPriority(int defaultPriority) {
         this.defaultPriority = defaultPriority;
         return this;
      }

      public Builder addNameBinding(Class binding) {
         this.nameBindings.add(binding);
         return this;
      }

      public Class getScope() {
         return this.scope;
      }

      public Map getContracts() {
         return this.contracts;
      }

      public int getDefaultPriority() {
         return this.defaultPriority;
      }

      public Set getNameBindings() {
         return this.nameBindings;
      }

      public ContractProvider build() {
         if (this.scope == null) {
            this.scope = Singleton.class;
         }

         Map<Class<?>, Integer> _contracts = this.contracts.isEmpty() ? Collections.emptyMap() : (Map)this.contracts.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (classIntegerEntry) -> {
            Integer priority = (Integer)classIntegerEntry.getValue();
            return priority != -1 ? priority : this.defaultPriority;
         }));
         Set<Class<? extends Annotation>> bindings = this.nameBindings.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(this.nameBindings);
         return this.implementationClass == null && this.scope == Singleton.class && _contracts.isEmpty() && this.defaultPriority == -1 && bindings.isEmpty() ? EMPTY_MODEL : new ContractProvider(this.implementationClass, this.scope, _contracts, this.defaultPriority, bindings);
      }
   }
}
