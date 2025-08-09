package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;

public final class KeyPairs {
   private KeyPairs() {
   }

   private static String familyPrefix(Class clazz) {
      if (RSAKey.class.isAssignableFrom(clazz)) {
         return "RSA ";
      } else {
         return ECKey.class.isAssignableFrom(clazz) ? "EC " : "";
      }
   }

   public static Object getKey(KeyPair pair, Class clazz) {
      Assert.notNull(pair, "KeyPair cannot be null.");
      String prefix = familyPrefix(clazz) + "KeyPair ";
      boolean isPrivate = PrivateKey.class.isAssignableFrom(clazz);
      Key key = (Key)(isPrivate ? pair.getPrivate() : pair.getPublic());
      return assertKey(key, clazz, prefix);
   }

   public static Object assertKey(Key key, Class clazz, String msgPrefix) {
      Assert.notNull(key, "Key argument cannot be null.");
      Assert.notNull(clazz, "Class argument cannot be null.");
      String type = key instanceof PrivateKey ? "private" : "public";
      if (!clazz.isInstance(key)) {
         String msg = msgPrefix + type + " key must be an instance of " + clazz.getName() + ". Type found: " + key.getClass().getName();
         throw new IllegalArgumentException(msg);
      } else {
         return clazz.cast(key);
      }
   }
}
