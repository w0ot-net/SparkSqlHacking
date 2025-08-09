package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Registry;
import java.math.BigInteger;
import java.net.URI;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public final class Parameters {
   private Parameters() {
   }

   public static Parameter string(String id, String name) {
      return (Parameter)builder(String.class).setId(id).setName(name).build();
   }

   public static Parameter rfcDate(String id, String name) {
      return (Parameter)builder(Date.class).setConverter(JwtDateConverter.INSTANCE).setId(id).setName(name).build();
   }

   public static Parameter x509Chain(String id, String name) {
      return (Parameter)builder(X509Certificate.class).setConverter(Converters.X509_CERTIFICATE).list().setId(id).setName(name).build();
   }

   public static ParameterBuilder builder(Class type) {
      return new DefaultParameterBuilder(type);
   }

   public static Parameter stringSet(String id, String name) {
      return (Parameter)builder(String.class).set().setId(id).setName(name).build();
   }

   public static Parameter uri(String id, String name) {
      return (Parameter)builder(URI.class).setConverter(Converters.URI).setId(id).setName(name).build();
   }

   public static ParameterBuilder bytes(String id, String name) {
      return builder(byte[].class).setConverter(Converters.BASE64URL_BYTES).setId(id).setName(name);
   }

   public static ParameterBuilder bigInt(String id, String name) {
      return builder(BigInteger.class).setConverter(Converters.BIGINT).setId(id).setName(name);
   }

   public static Parameter secretBigInt(String id, String name) {
      return (Parameter)bigInt(id, name).setSecret(true).build();
   }

   public static Registry registry(Parameter... params) {
      return registry((Collection)Arrays.asList(params));
   }

   public static Registry registry(Collection params) {
      return new IdRegistry("Parameter", params, true);
   }

   public static Registry registry(Registry parent, Parameter... params) {
      Set<Parameter<?>> set = new LinkedHashSet(parent.size() + params.length);
      set.addAll(parent.values());
      set.addAll(Arrays.asList(params));
      return new IdRegistry("Parameter", set, true);
   }

   public static Registry replace(Registry registry, Parameter param) {
      Assert.notEmpty(registry, "Registry cannot be null or empty.");
      Assert.notNull(param, "Parameter cannot be null.");
      String id = (String)Assert.hasText(param.getId(), "Parameter id cannot be null or empty.");
      Map<String, Parameter<?>> newParams = new LinkedHashMap(registry);
      newParams.remove(id);
      newParams.put(id, param);
      return registry(newParams.values());
   }

   private static byte[] bytes(BigInteger i) {
      return i != null ? i.toByteArray() : null;
   }

   public static boolean bytesEquals(BigInteger a, BigInteger b) {
      if (a == b) {
         return true;
      } else if (a != null && b != null) {
         byte[] aBytes = bytes(a);
         byte[] bBytes = bytes(b);

         boolean var4;
         try {
            var4 = MessageDigest.isEqual(aBytes, bBytes);
         } finally {
            Bytes.clear(aBytes);
            Bytes.clear(bBytes);
         }

         return var4;
      } else {
         return false;
      }
   }

   private static boolean equals(Object a, Object b, Parameter param) {
      if (a == b) {
         return true;
      } else if (a != null && b != null) {
         if (param.isSecret()) {
            if (a instanceof byte[]) {
               return b instanceof byte[] && MessageDigest.isEqual((byte[])a, (byte[])b);
            }

            if (a instanceof BigInteger) {
               return b instanceof BigInteger && bytesEquals((BigInteger)a, (BigInteger)b);
            }
         }

         return Objects.nullSafeEquals(a, b);
      } else {
         return false;
      }
   }

   public static boolean equals(ParameterReadable a, Object o, Parameter param) {
      if (a == o) {
         return true;
      } else if (a != null && o instanceof ParameterReadable) {
         ParameterReadable b = (ParameterReadable)o;
         return equals(a.get(param), b.get(param), param);
      } else {
         return false;
      }
   }
}
