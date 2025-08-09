package org.xerial.snappy;

public enum SnappyErrorCode {
   UNKNOWN(0),
   FAILED_TO_LOAD_NATIVE_LIBRARY(1),
   PARSING_ERROR(2),
   NOT_A_DIRECT_BUFFER(3),
   OUT_OF_MEMORY(4),
   FAILED_TO_UNCOMPRESS(5),
   EMPTY_INPUT(6),
   INCOMPATIBLE_VERSION(7),
   INVALID_CHUNK_SIZE(8),
   UNSUPPORTED_PLATFORM(9),
   TOO_LARGE_INPUT(10);

   public final int id;

   private SnappyErrorCode(int var3) {
      this.id = var3;
   }

   public static SnappyErrorCode getErrorCode(int var0) {
      for(SnappyErrorCode var4 : values()) {
         if (var4.id == var0) {
            return var4;
         }
      }

      return UNKNOWN;
   }

   public static String getErrorMessage(int var0) {
      return getErrorCode(var0).name();
   }
}
