package org.bouncycastle.pqc.legacy.crypto.rainbow;

import org.bouncycastle.crypto.CipherParameters;

public class RainbowParameters implements CipherParameters {
   private final int[] DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
   private int[] vi;

   public RainbowParameters() {
      this.vi = this.DEFAULT_VI;
   }

   public RainbowParameters(int[] var1) {
      this.vi = var1;
      this.checkParams();
   }

   private void checkParams() {
      if (this.vi == null) {
         throw new IllegalArgumentException("no layers defined.");
      } else if (this.vi.length > 1) {
         for(int var1 = 0; var1 < this.vi.length - 1; ++var1) {
            if (this.vi[var1] >= this.vi[var1 + 1]) {
               throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");
            }
         }

      } else {
         throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
      }
   }

   public int getNumOfLayers() {
      return this.vi.length - 1;
   }

   public int getDocLength() {
      return this.vi[this.vi.length - 1] - this.vi[0];
   }

   public int[] getVi() {
      return this.vi;
   }
}
