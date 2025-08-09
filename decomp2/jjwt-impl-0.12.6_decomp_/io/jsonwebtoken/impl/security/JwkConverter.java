package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Converter;
import io.jsonwebtoken.impl.lang.Nameable;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.lang.Supplier;
import io.jsonwebtoken.security.DynamicJwkBuilder;
import io.jsonwebtoken.security.EcPrivateJwk;
import io.jsonwebtoken.security.EcPublicJwk;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.MalformedKeyException;
import io.jsonwebtoken.security.OctetPrivateJwk;
import io.jsonwebtoken.security.OctetPublicJwk;
import io.jsonwebtoken.security.PrivateJwk;
import io.jsonwebtoken.security.PublicJwk;
import io.jsonwebtoken.security.RsaPrivateJwk;
import io.jsonwebtoken.security.RsaPublicJwk;
import io.jsonwebtoken.security.SecretJwk;
import java.util.Map;

public final class JwkConverter implements Converter {
   public static final Class JWK_CLASS = Jwk.class;
   public static final Class PUBLIC_JWK_CLASS = PublicJwk.class;
   public static final JwkConverter ANY;
   public static final JwkConverter PUBLIC_JWK;
   private final Class desiredType;
   private final Supplier supplier;

   public JwkConverter(Class desiredType) {
      this(desiredType, JwkBuilderSupplier.DEFAULT);
   }

   public JwkConverter(Supplier supplier) {
      this(JWK_CLASS, supplier);
   }

   public JwkConverter(Class desiredType, Supplier supplier) {
      this.desiredType = (Class)Assert.notNull(desiredType, "desiredType cannot be null.");
      this.supplier = (Supplier)Assert.notNull(supplier, "supplier cannot be null.");
   }

   public Object applyTo(Jwk jwk) {
      return this.desiredType.cast(jwk);
   }

   private static String articleFor(String s) {
      switch (s.charAt(0)) {
         case 'E':
         case 'R':
            return "an";
         default:
            return "a";
      }
   }

   private static String typeString(Jwk jwk) {
      Assert.isInstanceOf(Nameable.class, jwk, "All JWK implementations must implement Nameable.");
      return ((Nameable)jwk).getName();
   }

   private static String typeString(Class clazz) {
      StringBuilder sb = new StringBuilder();
      if (SecretJwk.class.isAssignableFrom(clazz)) {
         sb.append("Secret");
      } else if (!RsaPublicJwk.class.isAssignableFrom(clazz) && !RsaPrivateJwk.class.isAssignableFrom(clazz)) {
         if (!EcPublicJwk.class.isAssignableFrom(clazz) && !EcPrivateJwk.class.isAssignableFrom(clazz)) {
            if (OctetPublicJwk.class.isAssignableFrom(clazz) || OctetPrivateJwk.class.isAssignableFrom(clazz)) {
               sb.append("Edwards Curve");
            }
         } else {
            sb.append("EC");
         }
      } else {
         sb.append("RSA");
      }

      return typeString(sb, clazz);
   }

   private static String typeString(StringBuilder sb, Class clazz) {
      if (PublicJwk.class.isAssignableFrom(clazz)) {
         Strings.nespace(sb).append("Public");
      } else if (PrivateJwk.class.isAssignableFrom(clazz)) {
         Strings.nespace(sb).append("Private");
      }

      Strings.nespace(sb).append("JWK");
      return sb.toString();
   }

   private IllegalArgumentException unexpectedIAE(Jwk jwk) {
      String desired = typeString(this.desiredType);
      String jwkType = typeString(jwk);
      String msg = "Value must be " + articleFor(desired) + " " + desired + ", not " + articleFor(jwkType) + " " + jwkType + ".";
      return new IllegalArgumentException(msg);
   }

   public Jwk applyFrom(Object o) {
      Assert.notNull(o, "JWK cannot be null.");
      if (this.desiredType.isInstance(o)) {
         return (Jwk)this.desiredType.cast(o);
      } else if (o instanceof Jwk) {
         throw this.unexpectedIAE((Jwk)o);
      } else if (!(o instanceof Map)) {
         String msg = "JWK must be a Map<String,?> (JSON Object). Type found: " + o.getClass().getName() + ".";
         throw new IllegalArgumentException(msg);
      } else {
         Map<?, ?> map = Collections.immutable((Map)o);
         Parameter<String> param = AbstractJwk.KTY;
         if (!Collections.isEmpty(map) && map.containsKey(param.getId())) {
            Object val = map.get(param.getId());
            if (val == null) {
               String msg = "JWK " + param + " value cannot be null.";
               throw new MalformedKeyException(msg);
            } else if (!(val instanceof String)) {
               String msg = "JWK " + param + " value must be a String. Type found: " + val.getClass().getName();
               throw new MalformedKeyException(msg);
            } else {
               String kty = (String)val;
               if (!Strings.hasText(kty)) {
                  String msg = "JWK " + param + " value cannot be empty.";
                  throw new MalformedKeyException(msg);
               } else {
                  DynamicJwkBuilder<?, ?> builder = (DynamicJwkBuilder)this.supplier.get();

                  for(Map.Entry entry : map.entrySet()) {
                     Object key = entry.getKey();
                     Assert.notNull(key, "JWK map key cannot be null.");
                     if (!(key instanceof String)) {
                        String msg = "JWK map keys must be Strings. Encountered key '" + key + "' of type " + key.getClass().getName() + ".";
                        throw new IllegalArgumentException(msg);
                     }

                     String skey = (String)key;
                     builder.add(skey, entry.getValue());
                  }

                  Jwk<?> jwk = (Jwk)builder.build();
                  if (this.desiredType.isInstance(jwk)) {
                     return (Jwk)this.desiredType.cast(jwk);
                  } else {
                     throw this.unexpectedIAE(jwk);
                  }
               }
            }
         } else {
            String msg = "JWK is missing required " + param + " parameter.";
            throw new MalformedKeyException(msg);
         }
      }
   }

   static {
      ANY = new JwkConverter(JWK_CLASS);
      PUBLIC_JWK = new JwkConverter(PUBLIC_JWK_CLASS);
   }
}
