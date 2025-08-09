package org.apache.derby.impl.services.bytecode;

class Conditional {
   private static final int BRANCH16LIMIT = 32767;
   private final Conditional parent;
   private final int if_pc;
   private Type[] stack;
   private int thenGoto_pc;

   Conditional(Conditional var1, CodeChunk var2, short var3, Type[] var4) {
      this.parent = var1;
      this.if_pc = var2.getPC();
      this.stack = var4;
      var2.addInstrU2(var3, 0);
   }

   Type[] startElse(BCMethod var1, CodeChunk var2, Type[] var3) {
      var2.addInstrU2((short)167, 0);
      this.fillIn(var1, var2, this.if_pc, var2.getPC());
      this.thenGoto_pc = var2.getPC() - 3;
      Type[] var4 = this.stack;
      this.stack = var3;
      return var4;
   }

   Conditional end(BCMethod var1, CodeChunk var2, Type[] var3, int var4) {
      int var5;
      if (this.thenGoto_pc == 0) {
         var5 = this.if_pc;
      } else {
         var5 = this.thenGoto_pc;
      }

      this.fillIn(var1, var2, var5, var2.getPC());
      return this.parent;
   }

   private void fillIn(BCMethod var1, CodeChunk var2, int var3, int var4) {
      int var5 = var4 - var3;
      short var6 = var2.getOpcode(var3);
      if (var5 <= 32767) {
         CodeChunk var14 = var2.insertCodeSpace(var3, 0);
         var14.addInstrU2(var6, var5);
      } else if (var6 == 167) {
         CodeChunk var12 = var2.insertCodeSpace(var3, 2);
         var5 += 2;
         var12.addInstrU4((short)200, var5);
         int var8 = var12.getPC();
         int var9 = var8 - this.if_pc;
         if (var9 <= 32769) {
            this.fillIn(var1, var2, this.if_pc, var12.getPC());
         } else {
            var12 = var2.insertCodeSpace(this.if_pc + 3, 0);
            var9 -= 3;
            var12.addInstrU4((short)200, var9);
         }
      } else {
         if (var4 + 5 >= 65535) {
            var1.cb.addLimitExceeded(var1, "branch_target", 65535, var4 + 5);
         }

         switch (var6) {
            case 153 -> var6 = 154;
            case 199 -> var6 = 198;
         }

         CodeChunk var7 = var2.insertCodeSpace(var3, 5);
         var7.addInstrU2(var6, 8);
         var5 += 2;
         var7.addInstrU4((short)200, var5);
      }
   }
}
