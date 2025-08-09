package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.AbstractX509Context;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.HashAlgorithm;
import io.jsonwebtoken.security.KeyOperation;
import io.jsonwebtoken.security.Jwks.OP;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class DefaultJwkContext extends AbstractX509Context implements JwkContext {
   private static final Set DEFAULT_PARAMS;
   private Key key;
   private PublicKey publicKey;
   private Provider provider;
   private SecureRandom random;
   private HashAlgorithm idThumbprintAlgorithm;

   public DefaultJwkContext() {
      this(DEFAULT_PARAMS);
   }

   public DefaultJwkContext(Set params) {
      super(params);
   }

   public DefaultJwkContext(Set params, JwkContext other) {
      this(params, other, true);
   }

   public DefaultJwkContext(Set params, JwkContext other, Key key) {
      this(params, other, key == null || key instanceof PublicKey);
      this.key = (Key)Assert.notNull(key, "Key cannot be null.");
   }

   public DefaultJwkContext(Set params, JwkContext other, boolean removePrivate) {
      super((Set)Assert.notEmpty(params, "Parameters cannot be null or empty."));
      Assert.notNull(other, "JwkContext cannot be null.");
      Assert.isInstanceOf(DefaultJwkContext.class, other, "JwkContext must be a DefaultJwkContext instance.");
      DefaultJwkContext<?> src = (DefaultJwkContext)other;
      this.provider = other.getProvider();
      this.random = other.getRandom();
      this.idThumbprintAlgorithm = other.getIdThumbprintAlgorithm();
      this.values.putAll(src.values);

      for(Map.Entry entry : src.idiomaticValues.entrySet()) {
         String id = (String)entry.getKey();
         Object value = entry.getValue();
         Parameter<?> param = (Parameter)this.PARAMS.get(id);
         if (param != null && !param.supports(value)) {
            value = this.values.get(param.getId());
            this.put(param, value);
         } else {
            this.idiomaticValues.put(id, value);
         }
      }

      if (removePrivate) {
         for(Parameter param : src.PARAMS.values()) {
            if (param.isSecret()) {
               this.remove(param.getId());
            }
         }
      }

   }

   public JwkContext parameter(Parameter param) {
      Registry<String, ? extends Parameter<?>> registry = Parameters.replace(this.PARAMS, param);
      Set<Parameter<?>> params = new LinkedHashSet(registry.values());
      return this.key != null ? new DefaultJwkContext(params, this, this.key) : new DefaultJwkContext(params, this, false);
   }

   public String getName() {
      String value = (String)this.get(AbstractJwk.KTY);
      if ("oct".equals(value)) {
         value = "Secret";
      } else if ("OKP".equals(value)) {
         value = "Octet";
      }

      StringBuilder sb = value != null ? new StringBuilder(value) : new StringBuilder();
      K key = (K)this.getKey();
      if (key instanceof PublicKey) {
         Strings.nespace(sb).append("Public");
      } else if (key instanceof PrivateKey) {
         Strings.nespace(sb).append("Private");
      }

      Strings.nespace(sb).append("JWK");
      return sb.toString();
   }

   public void putAll(Map m) {
      Assert.notEmpty(m, "JWK values cannot be null or empty.");
      super.putAll(m);
   }

   public String getAlgorithm() {
      return (String)this.get(AbstractJwk.ALG);
   }

   public JwkContext setAlgorithm(String algorithm) {
      this.put(AbstractJwk.ALG, algorithm);
      return this;
   }

   public String getId() {
      return (String)this.get(AbstractJwk.KID);
   }

   public JwkContext setId(String id) {
      this.put(AbstractJwk.KID, id);
      return this;
   }

   public JwkContext setIdThumbprintAlgorithm(HashAlgorithm alg) {
      this.idThumbprintAlgorithm = alg;
      return this;
   }

   public HashAlgorithm getIdThumbprintAlgorithm() {
      return this.idThumbprintAlgorithm;
   }

   public Set getOperations() {
      return (Set)this.get(AbstractJwk.KEY_OPS);
   }

   public JwkContext setOperations(Collection ops) {
      this.put(AbstractJwk.KEY_OPS, ops);
      return this;
   }

   public String getType() {
      return (String)this.get(AbstractJwk.KTY);
   }

   public JwkContext setType(String type) {
      this.put(AbstractJwk.KTY, type);
      return this;
   }

   public String getPublicKeyUse() {
      return (String)this.get(AbstractAsymmetricJwk.USE);
   }

   public JwkContext setPublicKeyUse(String use) {
      this.put(AbstractAsymmetricJwk.USE, use);
      return this;
   }

   public boolean isSigUse() {
      if ("sig".equals(this.getPublicKeyUse())) {
         return true;
      } else {
         Set<KeyOperation> ops = this.getOperations();
         if (Collections.isEmpty(ops)) {
            return false;
         } else {
            return ops.contains(OP.SIGN) || ops.contains(OP.VERIFY);
         }
      }
   }

   public Key getKey() {
      return this.key;
   }

   public JwkContext setKey(Key key) {
      this.key = key;
      return this;
   }

   public PublicKey getPublicKey() {
      return this.publicKey;
   }

   public JwkContext setPublicKey(PublicKey publicKey) {
      this.publicKey = publicKey;
      return this;
   }

   public Provider getProvider() {
      return this.provider;
   }

   public JwkContext setProvider(Provider provider) {
      this.provider = provider;
      return this;
   }

   public SecureRandom getRandom() {
      return this.random;
   }

   public JwkContext setRandom(SecureRandom random) {
      this.random = random;
      return this;
   }

   static {
      Set<Parameter<?>> set = new LinkedHashSet();
      set.addAll(DefaultSecretJwk.PARAMS);
      set.addAll(DefaultEcPrivateJwk.PARAMS);
      set.addAll(DefaultRsaPrivateJwk.PARAMS);
      set.addAll(DefaultOctetPrivateJwk.PARAMS);
      set.remove(DefaultEcPublicJwk.X);
      set.remove(DefaultEcPrivateJwk.D);
      set.add(Parameters.string(DefaultEcPublicJwk.X.getId(), "Elliptic Curve public key X coordinate"));
      set.add(Parameters.builder(String.class).setSecret(true).setId(DefaultEcPrivateJwk.D.getId()).setName("Elliptic Curve private key").build());
      DEFAULT_PARAMS = Collections.immutable(set);
   }
}
