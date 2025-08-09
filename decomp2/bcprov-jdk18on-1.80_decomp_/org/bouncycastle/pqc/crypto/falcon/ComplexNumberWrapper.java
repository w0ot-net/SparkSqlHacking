package org.bouncycastle.pqc.crypto.falcon;

class ComplexNumberWrapper {
   FalconFPR re;
   FalconFPR im;

   ComplexNumberWrapper(FalconFPR var1, FalconFPR var2) {
      this.re = var1;
      this.im = var2;
   }
}
