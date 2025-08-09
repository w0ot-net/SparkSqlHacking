package org.apache.commons.lang3.arch;

public class Processor {
   private final Arch arch;
   private final Type type;

   public Processor(Arch arch, Type type) {
      this.arch = arch;
      this.type = type;
   }

   public Arch getArch() {
      return this.arch;
   }

   public Type getType() {
      return this.type;
   }

   public boolean is32Bit() {
      return Processor.Arch.BIT_32 == this.arch;
   }

   public boolean is64Bit() {
      return Processor.Arch.BIT_64 == this.arch;
   }

   public boolean isAarch64() {
      return Processor.Type.AARCH_64 == this.type;
   }

   public boolean isIA64() {
      return Processor.Type.IA_64 == this.type;
   }

   public boolean isPPC() {
      return Processor.Type.PPC == this.type;
   }

   public boolean isRISCV() {
      return Processor.Type.RISC_V == this.type;
   }

   public boolean isX86() {
      return Processor.Type.X86 == this.type;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.type.getLabel()).append(' ').append(this.arch.getLabel());
      return builder.toString();
   }

   public static enum Arch {
      BIT_32("32-bit"),
      BIT_64("64-bit"),
      UNKNOWN("Unknown");

      private final String label;

      private Arch(String label) {
         this.label = label;
      }

      public String getLabel() {
         return this.label;
      }

      // $FF: synthetic method
      private static Arch[] $values() {
         return new Arch[]{BIT_32, BIT_64, UNKNOWN};
      }
   }

   public static enum Type {
      AARCH_64("AArch64"),
      X86("x86"),
      IA_64("IA-64"),
      PPC("PPC"),
      RISC_V("RISC-V"),
      UNKNOWN("Unknown");

      private final String label;

      private Type(String label) {
         this.label = label;
      }

      public String getLabel() {
         return this.label;
      }

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{AARCH_64, X86, IA_64, PPC, RISC_V, UNKNOWN};
      }
   }
}
