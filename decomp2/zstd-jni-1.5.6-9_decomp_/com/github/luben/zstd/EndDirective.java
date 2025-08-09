package com.github.luben.zstd;

public enum EndDirective {
   CONTINUE(0),
   FLUSH(1),
   END(2);

   private final int value;

   private EndDirective(int var3) {
      this.value = var3;
   }

   int value() {
      return this.value;
   }
}
