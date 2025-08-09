package io.vertx.ext.auth.authorization.impl;

import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.Authorizations;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AuthorizationsImpl implements Authorizations {
   private final Map authorizations = new ConcurrentHashMap();

   public Authorizations add(String providerId, Authorization authorization) {
      Objects.requireNonNull(authorization);
      return this.add(providerId, Collections.singleton(authorization));
   }

   public Authorizations add(String providerId, Set authorizations) {
      Objects.requireNonNull(providerId);
      Objects.requireNonNull(authorizations);
      ConcurrentHashMap.KeySetView<Authorization, Boolean> concurrentAuthorizations = ConcurrentHashMap.newKeySet();
      concurrentAuthorizations.addAll(authorizations);
      this.getOrCreateAuthorizations(providerId).addAll(concurrentAuthorizations);
      return this;
   }

   public Authorizations clear(String providerId) {
      Objects.requireNonNull(providerId);
      this.authorizations.remove(providerId);
      return this;
   }

   public Authorizations clear() {
      this.authorizations.clear();
      return this;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof AuthorizationsImpl)) {
         return false;
      } else {
         AuthorizationsImpl other = (AuthorizationsImpl)obj;
         return this.authorizations.equals(other.authorizations);
      }
   }

   public Set get(String providerId) {
      Objects.requireNonNull(providerId);
      Set<Authorization> set = (Set)this.authorizations.get(providerId);
      return set == null ? Collections.emptySet() : set;
   }

   private Set getOrCreateAuthorizations(String providerId) {
      return (Set)this.authorizations.computeIfAbsent(providerId, (k) -> ConcurrentHashMap.newKeySet());
   }

   public Set getProviderIds() {
      return this.authorizations.keySet();
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + this.authorizations.hashCode();
      return result;
   }
}
