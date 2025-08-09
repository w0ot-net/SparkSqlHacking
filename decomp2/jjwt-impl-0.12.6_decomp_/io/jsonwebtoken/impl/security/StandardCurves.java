package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.IdRegistry;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Curve;
import java.security.Key;

public final class StandardCurves extends IdRegistry {
   public StandardCurves() {
      super("Elliptic Curve", Collections.of(new Curve[]{ECCurve.P256, ECCurve.P384, ECCurve.P521, EdwardsCurve.X25519, EdwardsCurve.X448, EdwardsCurve.Ed25519, EdwardsCurve.Ed448}));
   }

   public static Curve findByKey(Key key) {
      if (key == null) {
         return null;
      } else {
         Curve curve = ECCurve.findByKey(key);
         if (curve == null) {
            curve = EdwardsCurve.findByKey(key);
         }

         return curve;
      }
   }
}
