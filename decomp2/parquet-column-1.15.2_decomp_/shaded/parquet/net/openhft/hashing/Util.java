package shaded.parquet.net.openhft.hashing;

import java.nio.ByteBuffer;
import org.jetbrains.annotations.NotNull;
import sun.nio.ch.DirectBuffer;

final class Util {
   @NotNull
   static final StringHash VALID_STRING_HASH;

   private static boolean isHotSpotVM(@NotNull String name) {
      return name.contains("HotSpot") || name.contains("OpenJDK");
   }

   private static boolean isJ9VM(@NotNull String name) {
      return name.contains("Eclipse OpenJ9") || name.contains("IBM J9");
   }

   private static boolean isZing(@NotNull String name) {
      return name.startsWith("Zing");
   }

   static void checkArrayOffs(int arrayLength, int off, int len) {
      if (len < 0 || off < 0 || off + len > arrayLength || off + len < 0) {
         throw new IndexOutOfBoundsException();
      }
   }

   static long getDirectBufferAddress(@NotNull ByteBuffer buff) {
      return ((DirectBuffer)buff).address();
   }

   static {
      StringHash stringHash = null;

      try {
         String vmName = System.getProperty("java.vm.name");
         if (!isHotSpotVM(vmName) && !isJ9VM(vmName) && !isZing(vmName)) {
            stringHash = HotSpotPrior7u6StringHash.INSTANCE;
         } else {
            String javaVersion = System.getProperty("java.version");
            if (javaVersion.compareTo("1.7.0_06") >= 0) {
               if (javaVersion.compareTo("1.9") >= 0) {
                  stringHash = ModernCompactStringHash.INSTANCE;
               } else {
                  stringHash = ModernHotSpotStringHash.INSTANCE;
               }
            } else {
               stringHash = HotSpotPrior7u6StringHash.INSTANCE;
            }
         }
      } catch (Throwable var6) {
      } finally {
         if (null == stringHash) {
            VALID_STRING_HASH = UnknownJvmStringHash.INSTANCE;
         } else {
            VALID_STRING_HASH = stringHash;
         }

      }

   }
}
