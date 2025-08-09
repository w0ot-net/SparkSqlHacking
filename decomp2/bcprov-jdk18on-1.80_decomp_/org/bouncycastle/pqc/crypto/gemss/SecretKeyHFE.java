package org.bouncycastle.pqc.crypto.gemss;

class SecretKeyHFE {
   complete_sparse_monic_gf2nx F_struct = new complete_sparse_monic_gf2nx();
   public Pointer F_HFEv;
   public Pointer S;
   public Pointer T;
   public Pointer sk_uncomp;

   public SecretKeyHFE(GeMSSEngine var1) {
      this.F_struct.L = new int[var1.NB_COEFS_HFEPOLY];
   }

   static class complete_sparse_monic_gf2nx {
      public Pointer poly;
      public int[] L;

      public complete_sparse_monic_gf2nx() {
      }
   }
}
