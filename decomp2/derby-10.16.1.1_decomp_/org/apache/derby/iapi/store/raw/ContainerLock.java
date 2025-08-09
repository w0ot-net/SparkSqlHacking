package org.apache.derby.iapi.store.raw;

public final class ContainerLock {
   private final int type;
   private final int typeBit;
   private final int compat;
   public static final int C_NUMBER = 5;
   private static final boolean[][] C_COMPAT = new boolean[][]{{true, true, true, false, false}, {true, true, false, false, false}, {true, false, true, false, false}, {false, false, true, false, false}, {false, false, false, false, false}};
   private static String[] shortnames = new String[]{"IS", "IX", "S", "U", "X"};
   public static final ContainerLock CIS = new ContainerLock(0);
   public static final ContainerLock CIX = new ContainerLock(1);
   public static final ContainerLock CS = new ContainerLock(2);
   public static final ContainerLock CU = new ContainerLock(3);
   public static final ContainerLock CX = new ContainerLock(4);

   private ContainerLock(int var1) {
      this.type = var1;
      this.typeBit = 1 << var1;
      int var2 = 0;

      for(int var3 = 0; var3 < 5; ++var3) {
         if (C_COMPAT[var1][var3]) {
            var2 |= 1 << var3;
         }
      }

      this.compat = var2;
   }

   public int getType() {
      return this.type;
   }

   public boolean isCompatible(ContainerLock var1) {
      return (var1.typeBit & this.compat) != 0;
   }

   public String toString() {
      return shortnames[this.getType()];
   }
}
