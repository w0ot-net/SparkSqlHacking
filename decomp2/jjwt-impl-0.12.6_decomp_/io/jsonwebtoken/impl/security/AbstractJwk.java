package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.lang.Nameable;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.lang.Supplier;
import io.jsonwebtoken.security.HashAlgorithm;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.JwkThumbprint;
import io.jsonwebtoken.security.KeyOperation;
import io.jsonwebtoken.security.Jwks.HASH;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractJwk implements Jwk, ParameterReadable, Nameable {
   static final Parameter ALG = Parameters.string("alg", "Algorithm");
   public static final Parameter KID = Parameters.string("kid", "Key ID");
   static final Parameter KEY_OPS;
   static final Parameter KTY;
   static final Set PARAMS;
   public static final String IMMUTABLE_MSG = "JWKs are immutable and may not be modified.";
   protected final JwkContext context;
   private final List THUMBPRINT_PARAMS;
   private final int hashCode;

   AbstractJwk(JwkContext ctx, List thumbprintParams) {
      this.context = (JwkContext)Assert.notNull(ctx, "JwkContext cannot be null.");
      Assert.isTrue(!ctx.isEmpty(), "JwkContext cannot be empty.");
      Assert.hasText(ctx.getType(), "JwkContext type cannot be null or empty.");
      Assert.notNull(ctx.getKey(), "JwkContext key cannot be null.");
      this.THUMBPRINT_PARAMS = (List)Assert.notEmpty(thumbprintParams, "JWK Thumbprint parameters cannot be null or empty.");
      HashAlgorithm idThumbprintAlg = ctx.getIdThumbprintAlgorithm();
      if (!Strings.hasText(this.getId()) && idThumbprintAlg != null) {
         JwkThumbprint thumbprint = this.thumbprint(idThumbprintAlg);
         String kid = thumbprint.toString();
         ctx.setId(kid);
      }

      this.hashCode = this.computeHashCode();
   }

   private int computeHashCode() {
      List<Object> list = new ArrayList(this.THUMBPRINT_PARAMS.size() + 1);
      Key key = (Key)Assert.notNull(this.toKey(), "JWK toKey() value cannot be null.");
      if (key instanceof PublicKey) {
         list.add("Public");
      } else if (key instanceof PrivateKey) {
         list.add("Private");
      }

      for(Parameter param : this.THUMBPRINT_PARAMS) {
         Object val = Assert.notNull(this.get(param), "computeHashCode: Parameter idiomatic value cannot be null.");
         list.add(val);
      }

      return Objects.nullSafeHashCode(list.toArray());
   }

   private String getRequiredThumbprintValue(Parameter param) {
      Object value = this.get((Object)param.getId());
      if (value instanceof Supplier) {
         value = ((Supplier)value).get();
      }

      return (String)Assert.isInstanceOf(String.class, value, "Parameter canonical value is not a String.");
   }

   private String toThumbprintJson() {
      StringBuilder sb = (new StringBuilder()).append('{');
      Iterator<Parameter<?>> i = this.THUMBPRINT_PARAMS.iterator();

      while(i.hasNext()) {
         Parameter<?> param = (Parameter)i.next();
         String value = this.getRequiredThumbprintValue(param);
         sb.append('"').append(param.getId()).append("\":\"").append(value).append('"');
         if (i.hasNext()) {
            sb.append(",");
         }
      }

      sb.append('}');
      return sb.toString();
   }

   public JwkThumbprint thumbprint() {
      return this.thumbprint(HASH.SHA256);
   }

   public JwkThumbprint thumbprint(HashAlgorithm alg) {
      String json = this.toThumbprintJson();
      Assert.hasText(json, "Canonical JWK Thumbprint JSON cannot be null or empty.");
      byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
      InputStream in = Streams.of(bytes);
      byte[] digest = alg.digest(new DefaultRequest(in, this.context.getProvider(), this.context.getRandom()));
      return new DefaultJwkThumbprint(digest, alg);
   }

   public String getType() {
      return this.context.getType();
   }

   public String getName() {
      return this.context.getName();
   }

   public Set getOperations() {
      return Collections.immutable(this.context.getOperations());
   }

   public String getAlgorithm() {
      return this.context.getAlgorithm();
   }

   public String getId() {
      return this.context.getId();
   }

   public Key toKey() {
      return this.context.getKey();
   }

   public int size() {
      return this.context.size();
   }

   public boolean isEmpty() {
      return this.context.isEmpty();
   }

   public boolean containsKey(Object key) {
      return this.context.containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.context.containsValue(value);
   }

   public Object get(Object key) {
      Object val = this.context.get(key);
      if (val instanceof Map) {
         return Collections.immutable((Map)val);
      } else if (val instanceof Collection) {
         return Collections.immutable((Collection)val);
      } else {
         return Objects.isArray(val) ? Arrays.copy(val) : val;
      }
   }

   public Object get(Parameter param) {
      return this.context.get(param);
   }

   public Set keySet() {
      return Collections.immutable(this.context.keySet());
   }

   public Collection values() {
      return Collections.immutable(this.context.values());
   }

   public Set entrySet() {
      return Collections.immutable(this.context.entrySet());
   }

   private static Object immutable() {
      throw new UnsupportedOperationException("JWKs are immutable and may not be modified.");
   }

   public Object put(String s, Object o) {
      return immutable();
   }

   public Object remove(Object o) {
      return immutable();
   }

   public void putAll(Map m) {
      immutable();
   }

   public void clear() {
      immutable();
   }

   public String toString() {
      return this.context.toString();
   }

   public final int hashCode() {
      return this.hashCode;
   }

   public final boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Jwk)) {
         return false;
      } else {
         Jwk<?> other = (Jwk)obj;
         return this.getType().equals(other.getType()) && this.equals(other);
      }
   }

   protected abstract boolean equals(Jwk var1);

   static {
      KEY_OPS = (Parameter)Parameters.builder(KeyOperation.class).setConverter(KeyOperationConverter.DEFAULT).set().setId("key_ops").setName("Key Operations").build();
      KTY = Parameters.string("kty", "Key Type");
      PARAMS = Collections.setOf(new Parameter[]{ALG, KID, KEY_OPS, KTY});
   }
}
