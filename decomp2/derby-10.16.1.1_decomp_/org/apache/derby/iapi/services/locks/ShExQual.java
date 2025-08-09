package org.apache.derby.iapi.services.locks;

public class ShExQual {
   private int lockState;
   public static final int SHARED = 0;
   public static final int EXCLUSIVE = 1;
   public static final ShExQual SH = new ShExQual(0);
   public static final ShExQual EX = new ShExQual(1);

   private ShExQual(int var1) {
      this.lockState = var1;
   }

   public int getLockState() {
      return this.lockState;
   }

   public String toString() {
      return this.lockState == 0 ? "S" : "X";
   }
}
