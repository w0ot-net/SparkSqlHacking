package org.glassfish.jersey.internal.inject;

import jakarta.inject.Named;
import jakarta.ws.rs.core.GenericType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Binding {
   private final Set contracts = new HashSet();
   private final Set qualifiers = new HashSet();
   private final Set aliases = new HashSet();
   private Class scope = null;
   private String name = null;
   private Class implementationType = null;
   private String analyzer = null;
   private Boolean proxiable = null;
   private Boolean proxyForSameScope = null;
   private Integer ranked = null;

   public Boolean isProxiable() {
      return this.proxiable;
   }

   public Boolean isProxiedForSameScope() {
      return this.proxyForSameScope;
   }

   public Integer getRank() {
      return this.ranked;
   }

   public Set getContracts() {
      return this.contracts;
   }

   public Set getQualifiers() {
      return this.qualifiers;
   }

   public Class getScope() {
      return this.scope;
   }

   public String getName() {
      return this.name;
   }

   public Class getImplementationType() {
      return this.implementationType;
   }

   public String getAnalyzer() {
      return this.analyzer;
   }

   public Set getAliases() {
      return this.aliases;
   }

   public Binding analyzeWith(String analyzer) {
      this.analyzer = analyzer;
      return this;
   }

   public Binding to(Collection contracts) {
      if (contracts != null) {
         this.contracts.addAll(contracts);
      }

      return this;
   }

   public Binding to(Class contract) {
      this.contracts.add(contract);
      return this;
   }

   public Binding to(GenericType contract) {
      this.contracts.add(contract.getType());
      return this;
   }

   public Binding to(Type contract) {
      this.contracts.add(contract);
      return this;
   }

   public Binding qualifiedBy(Annotation annotation) {
      if (Named.class.equals(annotation.annotationType())) {
         this.name = ((Named)annotation).value();
      }

      this.qualifiers.add(annotation);
      return this;
   }

   public Binding in(Class scopeAnnotation) {
      this.scope = scopeAnnotation;
      return this;
   }

   public Binding named(String name) {
      this.name = name;
      return this;
   }

   public AliasBinding addAlias(Class contract) {
      AliasBinding alias = new AliasBinding(contract);
      this.aliases.add(alias);
      return alias;
   }

   public Binding proxy(boolean proxiable) {
      this.proxiable = proxiable;
      return this;
   }

   public Binding proxyForSameScope(boolean proxyForSameScope) {
      this.proxyForSameScope = proxyForSameScope;
      return this;
   }

   public void ranked(int rank) {
      this.ranked = rank;
   }

   Binding asType(Class type) {
      this.implementationType = type;
      return this;
   }
}
