package io.vertx.core.shareddata.impl;

import io.vertx.core.impl.ClusterSerializableUtils;
import io.vertx.core.impl.SerializableUtils;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.shareddata.Shareable;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Checker {
   private static final Logger log = LoggerFactory.getLogger(Checker.class);
   private static final Set IMMUTABLE_TYPES = (Set)Stream.builder().add(String.class).add(Integer.class).add(Long.class).add(Boolean.class).add(Double.class).add(Float.class).add(Short.class).add(Byte.class).add(Character.class).add(BigInteger.class).add(BigDecimal.class).build().collect(Collectors.toSet());

   static void checkType(Object obj) {
      Objects.requireNonNull(obj, "null not allowed for shareddata data structure");
      if (!(obj instanceof Serializable) && !(obj instanceof Shareable) && !(obj instanceof ClusterSerializable)) {
         throw new IllegalArgumentException("Invalid type for shareddata data structure: " + obj.getClass().getName());
      }
   }

   static Object copyIfRequired(Object obj) {
      Object result;
      if (obj == null) {
         result = null;
      } else if (IMMUTABLE_TYPES.contains(obj.getClass())) {
         result = obj;
      } else if (obj instanceof byte[]) {
         result = copyByteArray((byte[])obj);
      } else if (obj instanceof Shareable) {
         result = ((Shareable)obj).copy();
      } else if (obj instanceof ClusterSerializable) {
         result = copyClusterSerializable((ClusterSerializable)obj);
      } else {
         if (!(obj instanceof Serializable)) {
            throw new IllegalStateException();
         }

         result = copySerializable(obj);
      }

      return result;
   }

   private static byte[] copyByteArray(byte[] bytes) {
      byte[] copy = new byte[bytes.length];
      System.arraycopy(bytes, 0, copy, 0, bytes.length);
      return copy;
   }

   private static ClusterSerializable copyClusterSerializable(ClusterSerializable obj) {
      logDeveloperInfo(obj);
      return ClusterSerializableUtils.copy(obj);
   }

   private static void logDeveloperInfo(Object obj) {
      if (log.isDebugEnabled()) {
         log.debug("Copying " + obj.getClass() + " for shared data. Consider implementing " + Shareable.class + " for better performance.");
      }

   }

   private static Object copySerializable(Object obj) {
      logDeveloperInfo(obj);
      return SerializableUtils.fromBytes(SerializableUtils.toBytes(obj), ObjectInputStream::new);
   }
}
