package org.tukaani.xz;

abstract class BCJCoder implements FilterCoder {
   public static final long X86_FILTER_ID = 4L;
   public static final long POWERPC_FILTER_ID = 5L;
   public static final long IA64_FILTER_ID = 6L;
   public static final long ARM_FILTER_ID = 7L;
   public static final long ARMTHUMB_FILTER_ID = 8L;
   public static final long SPARC_FILTER_ID = 9L;
   public static final long ARM64_FILTER_ID = 10L;
   public static final long RISCV_FILTER_ID = 11L;

   public static boolean isBCJFilterID(long filterID) {
      return filterID >= 4L && filterID <= 11L;
   }

   public boolean changesSize() {
      return false;
   }

   public boolean nonLastOK() {
      return true;
   }

   public boolean lastOK() {
      return false;
   }
}
