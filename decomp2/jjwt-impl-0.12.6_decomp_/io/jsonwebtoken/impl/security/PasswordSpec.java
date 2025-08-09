package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.security.Password;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordSpec implements Password, KeySpec {
   private static final String NONE_ALGORITHM = "NONE";
   private static final String DESTROYED_MSG = "Password has been destroyed. Password character array may not be obtained.";
   private static final String ENCODED_DISABLED_MSG = "getEncoded() is disabled for Password instances as they are intended to be used with key derivation algorithms only. Because passwords rarely have the length or entropy necessary for secure cryptographic operations such as authenticated hashing or encryption, they are disabled as direct inputs for these operations to help avoid accidental misuse; if you see this exception message, it is likely that the associated Password instance is being used incorrectly.";
   private volatile boolean destroyed;
   private final char[] password;

   public PasswordSpec(char[] password) {
      this.password = Assert.notEmpty(password, "Password character array cannot be null or empty.");
   }

   private void assertActive() {
      if (this.destroyed) {
         throw new IllegalStateException("Password has been destroyed. Password character array may not be obtained.");
      }
   }

   public char[] toCharArray() {
      this.assertActive();
      return (char[])this.password.clone();
   }

   public String getAlgorithm() {
      return "NONE";
   }

   public String getFormat() {
      return null;
   }

   public byte[] getEncoded() {
      throw new UnsupportedOperationException("getEncoded() is disabled for Password instances as they are intended to be used with key derivation algorithms only. Because passwords rarely have the length or entropy necessary for secure cryptographic operations such as authenticated hashing or encryption, they are disabled as direct inputs for these operations to help avoid accidental misuse; if you see this exception message, it is likely that the associated Password instance is being used incorrectly.");
   }

   public void destroy() {
      this.destroyed = true;
      Arrays.fill(this.password, '\u0000');
   }

   public boolean isDestroyed() {
      return this.destroyed;
   }

   public int hashCode() {
      return Objects.nullSafeHashCode(this.password);
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj instanceof PasswordSpec) {
         PasswordSpec other = (PasswordSpec)obj;
         return Objects.nullSafeEquals(this.password, other.password);
      } else {
         return false;
      }
   }

   public final String toString() {
      return "<redacted>";
   }
}
