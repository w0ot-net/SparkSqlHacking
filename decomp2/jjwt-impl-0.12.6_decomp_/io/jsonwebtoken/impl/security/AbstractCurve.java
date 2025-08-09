package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.Curve;
import io.jsonwebtoken.security.KeyPairBuilder;
import java.security.Key;

abstract class AbstractCurve implements Curve {
   private final String ID;
   private final String JCA_NAME;

   AbstractCurve(String id, String jcaName) {
      this.ID = (String)Assert.notNull(Strings.clean(id), "Curve ID cannot be null or empty.");
      this.JCA_NAME = (String)Assert.notNull(Strings.clean(jcaName), "Curve jcaName cannot be null or empty.");
   }

   public String getId() {
      return this.ID;
   }

   public String getJcaName() {
      return this.JCA_NAME;
   }

   public int hashCode() {
      return this.ID.hashCode();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj instanceof Curve) {
         Curve curve = (Curve)obj;
         return this.ID.equals(curve.getId());
      } else {
         return false;
      }
   }

   public String toString() {
      return this.ID;
   }

   public KeyPairBuilder keyPair() {
      return new DefaultKeyPairBuilder(this.JCA_NAME);
   }

   abstract boolean contains(Key var1);
}
