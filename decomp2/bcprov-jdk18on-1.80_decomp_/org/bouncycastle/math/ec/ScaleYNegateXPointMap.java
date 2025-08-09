package org.bouncycastle.math.ec;

public class ScaleYNegateXPointMap implements ECPointMap {
   protected final ECFieldElement scale;

   public ScaleYNegateXPointMap(ECFieldElement var1) {
      this.scale = var1;
   }

   public ECPoint map(ECPoint var1) {
      return var1.scaleYNegateX(this.scale);
   }
}
