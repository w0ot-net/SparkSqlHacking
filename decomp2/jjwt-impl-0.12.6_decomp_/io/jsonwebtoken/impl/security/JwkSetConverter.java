package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Converter;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Supplier;
import io.jsonwebtoken.security.DynamicJwkBuilder;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.JwkSet;
import io.jsonwebtoken.security.KeyException;
import io.jsonwebtoken.security.MalformedKeySetException;
import io.jsonwebtoken.security.UnsupportedKeyException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class JwkSetConverter implements Converter {
   private final Converter JWK_CONVERTER;
   private final Parameter PARAM;
   private final boolean ignoreUnsupported;

   public JwkSetConverter() {
      this((Supplier)JwkBuilderSupplier.DEFAULT, true);
   }

   public JwkSetConverter(boolean ignoreUnsupported) {
      this((Supplier)JwkBuilderSupplier.DEFAULT, ignoreUnsupported);
   }

   public JwkSetConverter(Supplier supplier, boolean ignoreUnsupported) {
      this((Converter)(new JwkConverter(supplier)), ignoreUnsupported);
   }

   public JwkSetConverter(Converter jwkConverter, boolean ignoreUnsupported) {
      this.JWK_CONVERTER = (Converter)Assert.notNull(jwkConverter, "JWK converter cannot be null.");
      this.PARAM = DefaultJwkSet.param(jwkConverter);
      this.ignoreUnsupported = ignoreUnsupported;
   }

   public boolean isIgnoreUnsupported() {
      return this.ignoreUnsupported;
   }

   public Object applyTo(JwkSet jwkSet) {
      return jwkSet;
   }

   public JwkSet applyFrom(Object o) {
      Assert.notNull(o, "Value cannot be null.");
      if (o instanceof JwkSet) {
         return (JwkSet)o;
      } else if (!(o instanceof Map)) {
         String msg = "Value must be a Map<String,?> (JSON Object). Type found: " + o.getClass().getName() + ".";
         throw new IllegalArgumentException(msg);
      } else {
         Map<?, ?> m = Collections.immutable((Map)o);
         if (!Collections.isEmpty(m) && m.containsKey(this.PARAM.getId())) {
            Object val = m.get(this.PARAM.getId());
            if (val == null) {
               String msg = "JWK Set " + this.PARAM + " value cannot be null.";
               throw new MalformedKeySetException(msg);
            } else {
               if (val instanceof Supplier) {
                  val = ((Supplier)val).get();
               }

               if (!(val instanceof Collection)) {
                  String msg = "JWK Set " + this.PARAM + " value must be a Collection (JSON Array). Type found: " + val.getClass().getName();
                  throw new MalformedKeySetException(msg);
               } else {
                  int size = Collections.size((Collection)val);
                  if (size == 0) {
                     String msg = "JWK Set " + this.PARAM + " collection cannot be empty.";
                     throw new MalformedKeySetException(msg);
                  } else {
                     Map<String, Object> src = new LinkedHashMap(Collections.size((Map)o));

                     for(Map.Entry entry : ((Map)o).entrySet()) {
                        Object key = Assert.notNull(entry.getKey(), "JWK Set map key cannot be null.");
                        if (!(key instanceof String)) {
                           String msg = "JWK Set map keys must be Strings. Encountered key '" + key + "' of type " + key.getClass().getName();
                           throw new IllegalArgumentException(msg);
                        }

                        String skey = (String)key;
                        src.put(skey, entry.getValue());
                     }

                     Set<Jwk<?>> jwks = new LinkedHashSet(size);
                     int i = 0;

                     for(Object candidate : (Collection)val) {
                        try {
                           Jwk<?> jwk = (Jwk)this.JWK_CONVERTER.applyFrom(candidate);
                           jwks.add(jwk);
                        } catch (UnsupportedKeyException e) {
                           if (!this.ignoreUnsupported) {
                              String msg = "JWK Set keys[" + i + "]: " + e.getMessage();
                              throw new UnsupportedKeyException(msg, e);
                           }
                        } catch (KeyException | IllegalArgumentException e) {
                           if (!this.ignoreUnsupported) {
                              String msg = "JWK Set keys[" + i + "]: " + ((RuntimeException)e).getMessage();
                              throw new MalformedKeySetException(msg, e);
                           }
                        }

                        ++i;
                     }

                     src.remove(this.PARAM.getId());
                     src.put(this.PARAM.getId(), jwks);
                     return new DefaultJwkSet(this.PARAM, src);
                  }
               }
            }
         } else {
            String msg = "Missing required " + this.PARAM + " parameter.";
            throw new MalformedKeySetException(msg);
         }
      }
   }
}
