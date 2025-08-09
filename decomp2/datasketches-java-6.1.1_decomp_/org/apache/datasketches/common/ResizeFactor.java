package org.apache.datasketches.common;

public enum ResizeFactor {
   X1(0),
   X2(1),
   X4(2),
   X8(3);

   private int lg_;

   private ResizeFactor(int lg) {
      this.lg_ = lg;
   }

   public int lg() {
      return this.lg_;
   }

   public static ResizeFactor getRF(int lg) {
      if (X1.lg() == lg) {
         return X1;
      } else if (X2.lg() == lg) {
         return X2;
      } else {
         return X4.lg() == lg ? X4 : X8;
      }
   }

   public int getValue() {
      return 1 << this.lg_;
   }
}
