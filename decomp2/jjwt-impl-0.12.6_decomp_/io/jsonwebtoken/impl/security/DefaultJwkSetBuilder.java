package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.ParameterMap;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.JwkSet;
import io.jsonwebtoken.security.JwkSetBuilder;
import io.jsonwebtoken.security.KeyOperationPolicy;
import java.security.Provider;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class DefaultJwkSetBuilder extends AbstractSecurityBuilder implements JwkSetBuilder {
   private KeyOperationPolicy operationPolicy;
   private JwkSetConverter converter;
   private ParameterMap map;

   public DefaultJwkSetBuilder() {
      this.operationPolicy = AbstractJwkBuilder.DEFAULT_OPERATION_POLICY;
      this.converter = new JwkSetConverter();
      this.map = new ParameterMap(Parameters.registry(DefaultJwkSet.KEYS));
   }

   public JwkSetBuilder delete(String key) {
      this.map.remove(key);
      return this;
   }

   public JwkSetBuilder empty() {
      this.map.clear();
      return this;
   }

   public JwkSetBuilder add(String key, Object value) {
      this.map.put(key, value);
      return this;
   }

   public JwkSetBuilder add(Map m) {
      this.map.putAll(m);
      return this;
   }

   private JwkSetBuilder refresh() {
      JwkConverter<Jwk<?>> jwkConverter = new JwkConverter(new JwkBuilderSupplier(this.provider, this.operationPolicy));
      this.converter = new JwkSetConverter(jwkConverter, this.converter.isIgnoreUnsupported());
      Parameter<Set<Jwk<?>>> param = DefaultJwkSet.param(jwkConverter);
      this.map = new ParameterMap(Parameters.registry(param), this.map, true);
      Set<Jwk<?>> jwks = (Set)this.map.get(param);
      if (!Collections.isEmpty(jwks)) {
         for(Jwk jwk : jwks) {
            this.operationPolicy.validate(jwk.getOperations());
         }
      }

      return this;
   }

   public JwkSetBuilder provider(Provider provider) {
      super.provider(provider);
      return this.refresh();
   }

   public JwkSetBuilder operationPolicy(KeyOperationPolicy policy) throws IllegalArgumentException {
      this.operationPolicy = policy != null ? policy : AbstractJwkBuilder.DEFAULT_OPERATION_POLICY;
      return this.refresh();
   }

   Collection ensureKeys() {
      Collection<Jwk<?>> keys = (Collection)this.map.get(DefaultJwkSet.KEYS);
      return (Collection)(Collections.isEmpty(keys) ? new LinkedHashSet() : keys);
   }

   public JwkSetBuilder add(Jwk jwk) {
      if (jwk != null) {
         this.operationPolicy.validate(jwk.getOperations());
         Collection<Jwk<?>> keys = this.ensureKeys();
         keys.add(jwk);
         this.keys(keys);
      }

      return this;
   }

   public JwkSetBuilder add(Collection c) {
      if (!Collections.isEmpty(c)) {
         for(Jwk jwk : c) {
            this.add(jwk);
         }
      }

      return this;
   }

   public JwkSetBuilder keys(Collection c) {
      return this.add((String)DefaultJwkSet.KEYS.getId(), c);
   }

   public JwkSet build() {
      return this.converter.applyFrom(this.map);
   }
}
