package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.ParameterMap;
import io.jsonwebtoken.impl.lang.Converter;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.JwkSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DefaultJwkSet extends ParameterMap implements JwkSet {
   private static final String NAME = "JWK Set";
   static final Parameter KEYS;

   static Parameter param(Converter converter) {
      return (Parameter)Parameters.builder(JwkConverter.JWK_CLASS).setConverter(converter).set().setId("keys").setName("JSON Web Keys").setSecret(true).build();
   }

   public DefaultJwkSet(Parameter param, Map src) {
      super(Parameters.registry(param), src);
   }

   public String getName() {
      return "JWK Set";
   }

   public Set getKeys() {
      Set<Jwk<?>> jwks = (Set)this.get(KEYS);
      return Collections.isEmpty(jwks) ? Collections.emptySet() : Collections.immutable(jwks);
   }

   public Iterator iterator() {
      return this.getKeys().iterator();
   }

   static {
      KEYS = param(JwkConverter.ANY);
   }
}
