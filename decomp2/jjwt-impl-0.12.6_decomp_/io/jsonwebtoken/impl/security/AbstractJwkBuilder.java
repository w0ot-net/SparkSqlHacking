package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.DefaultNestedCollection;
import io.jsonwebtoken.impl.lang.DelegatingMapMutator;
import io.jsonwebtoken.impl.lang.IdRegistry;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.NestedCollection;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.security.HashAlgorithm;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.JwkBuilder;
import io.jsonwebtoken.security.KeyOperation;
import io.jsonwebtoken.security.KeyOperationPolicy;
import io.jsonwebtoken.security.MalformedKeyException;
import io.jsonwebtoken.security.SecretJwkBuilder;
import io.jsonwebtoken.security.Jwks.HASH;
import io.jsonwebtoken.security.Jwks.OP;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;

abstract class AbstractJwkBuilder extends DelegatingMapMutator implements JwkBuilder {
   protected final JwkFactory jwkFactory;
   static final KeyOperationPolicy DEFAULT_OPERATION_POLICY = (KeyOperationPolicy)OP.policy().build();
   protected KeyOperationPolicy opsPolicy;

   protected AbstractJwkBuilder(JwkContext jwkContext) {
      this(jwkContext, DispatchingJwkFactory.DEFAULT_INSTANCE);
   }

   protected AbstractJwkBuilder(JwkContext context, JwkFactory factory) {
      super(context);
      this.opsPolicy = DEFAULT_OPERATION_POLICY;
      this.jwkFactory = (JwkFactory)Assert.notNull(factory, "JwkFactory cannot be null.");
   }

   protected JwkContext newContext(Key key) {
      return this.jwkFactory.newContext((JwkContext)this.DELEGATE, key);
   }

   public JwkBuilder provider(Provider provider) {
      ((JwkContext)this.DELEGATE).setProvider(provider);
      return (JwkBuilder)this.self();
   }

   public JwkBuilder random(SecureRandom random) {
      ((JwkContext)this.DELEGATE).setRandom(random);
      return (JwkBuilder)this.self();
   }

   public JwkBuilder algorithm(String alg) {
      Assert.hasText(alg, "Algorithm cannot be null or empty.");
      ((JwkContext)this.DELEGATE).setAlgorithm(alg);
      return (JwkBuilder)this.self();
   }

   public JwkBuilder id(String id) {
      Assert.hasText(id, "Id cannot be null or empty.");
      ((JwkContext)this.DELEGATE).setIdThumbprintAlgorithm((HashAlgorithm)null);
      ((JwkContext)this.DELEGATE).setId(id);
      return (JwkBuilder)this.self();
   }

   public JwkBuilder idFromThumbprint() {
      return this.idFromThumbprint(HASH.SHA256);
   }

   public JwkBuilder idFromThumbprint(HashAlgorithm alg) {
      Assert.notNull(alg, "Thumbprint HashAlgorithm cannot be null.");
      Assert.notNull(alg.getId(), "Thumbprint HashAlgorithm ID cannot be null.");
      ((JwkContext)this.DELEGATE).setId((String)null);
      ((JwkContext)this.DELEGATE).setIdThumbprintAlgorithm(alg);
      return (JwkBuilder)this.self();
   }

   public NestedCollection operations() {
      // $FF: Couldn't be decompiled
   }

   public JwkBuilder operationPolicy(KeyOperationPolicy policy) throws IllegalArgumentException {
      Assert.notNull(policy, "Policy cannot be null.");
      Collection<KeyOperation> ops = policy.getOperations();
      Assert.notEmpty(ops, "Policy operations cannot be null or empty.");
      this.opsPolicy = policy;
      Registry<String, KeyOperation> registry = new IdRegistry("JSON Web Key Operation", ops);
      Parameter<Set<KeyOperation>> param = (Parameter)Parameters.builder(KeyOperation.class).setConverter(new KeyOperationConverter(registry)).set().setId(AbstractJwk.KEY_OPS.getId()).setName(AbstractJwk.KEY_OPS.getName()).build();
      this.setDelegate(((JwkContext)this.DELEGATE).parameter(param));
      return (JwkBuilder)this.self();
   }

   public Jwk build() {
      Assert.stateNotNull(this.DELEGATE, "JwkContext should always be non-null");
      K key = (K)((JwkContext)this.DELEGATE).getKey();
      if (key == null && this.isEmpty()) {
         String msg = "A " + Key.class.getName() + " or one or more name/value pairs must be provided to create a JWK.";
         throw new IllegalStateException(msg);
      } else {
         try {
            this.opsPolicy.validate((Collection)((JwkContext)this.DELEGATE).get(AbstractJwk.KEY_OPS));
            return this.jwkFactory.createJwk((JwkContext)this.DELEGATE);
         } catch (IllegalArgumentException iae) {
            String msg = "Unable to create JWK: " + iae.getMessage();
            throw new MalformedKeyException(msg, iae);
         }
      }
   }

   // $FF: synthetic method
   static Map access$000(AbstractJwkBuilder x0) {
      return x0.DELEGATE;
   }

   static class DefaultSecretJwkBuilder extends AbstractJwkBuilder implements SecretJwkBuilder {
      public DefaultSecretJwkBuilder(JwkContext ctx) {
         super(ctx);
         Key key = (Key)Assert.notNull(ctx.getKey(), "SecretKey cannot be null.");
         DefaultMacAlgorithm mac = DefaultMacAlgorithm.findByKey(key);
         if (mac != null) {
            this.algorithm(mac.getId());
         }

      }
   }
}
