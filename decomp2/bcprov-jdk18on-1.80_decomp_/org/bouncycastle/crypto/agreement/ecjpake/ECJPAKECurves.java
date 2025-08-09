package org.bouncycastle.crypto.agreement.ecjpake;

import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.math.ec.ECCurve;

public class ECJPAKECurves {
   public static final ECJPAKECurve NIST_P256 = fromX9ECParameters(NISTNamedCurves.getByName("P-256"));
   public static final ECJPAKECurve NIST_P384 = fromX9ECParameters(NISTNamedCurves.getByName("P-384"));
   public static final ECJPAKECurve NIST_P521 = fromX9ECParameters(NISTNamedCurves.getByName("P-521"));

   private static ECJPAKECurve fromX9ECParameters(X9ECParameters var0) {
      return new ECJPAKECurve((ECCurve.Fp)var0.getCurve(), var0.getG());
   }
}
