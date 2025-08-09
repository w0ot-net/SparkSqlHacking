package org.bouncycastle.math.ec.endo;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPointMap;
import org.bouncycastle.math.ec.ScaleYNegateXPointMap;

public class GLVTypeAEndomorphism implements GLVEndomorphism {
   protected final GLVTypeAParameters parameters;
   protected final ECPointMap pointMap;

   public GLVTypeAEndomorphism(ECCurve var1, GLVTypeAParameters var2) {
      this.parameters = var2;
      this.pointMap = new ScaleYNegateXPointMap(var1.fromBigInteger(var2.getI()));
   }

   public BigInteger[] decomposeScalar(BigInteger var1) {
      return EndoUtil.decomposeScalar(this.parameters.getSplitParams(), var1);
   }

   public ECPointMap getPointMap() {
      return this.pointMap;
   }

   public boolean hasEfficientPointMap() {
      return true;
   }
}
