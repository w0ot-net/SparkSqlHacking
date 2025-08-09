package org.xerial.snappy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;

public class OSInfo {
   private static HashMap archMapping = new HashMap();
   public static final String X86 = "x86";
   public static final String X86_64 = "x86_64";
   public static final String IA64_32 = "ia64_32";
   public static final String IA64 = "ia64";
   public static final String PPC = "ppc";
   public static final String PPC64 = "ppc64";
   public static final String IBMZ = "s390";
   public static final String IBMZ_64 = "s390x";
   public static final String AARCH_64 = "aarch64";
   public static final String RISCV_64 = "riscv64";
   public static final String LOONGARCH_64 = "loongarch64";

   public static void main(String[] var0) {
      if (var0.length >= 1) {
         if ("--os".equals(var0[0])) {
            System.out.print(getOSName());
            return;
         }

         if ("--arch".equals(var0[0])) {
            System.out.print(getArchName());
            return;
         }
      }

      System.out.print(getNativeLibFolderPathForCurrentOS());
   }

   public static String getNativeLibFolderPathForCurrentOS() {
      return getOSName() + "/" + getArchName();
   }

   public static String getOSName() {
      return translateOSNameToFolderName(System.getProperty("os.name"));
   }

   public static boolean isAndroid() {
      return System.getProperty("java.runtime.name", "").toLowerCase().contains("android");
   }

   static String getHardwareName() {
      try {
         Process var0 = Runtime.getRuntime().exec("uname -m");
         var0.waitFor();
         InputStream var1 = var0.getInputStream();

         String var5;
         try {
            int var2 = 0;
            ByteArrayOutputStream var3 = new ByteArrayOutputStream();
            byte[] var4 = new byte[32];

            while((var2 = var1.read(var4, 0, var4.length)) >= 0) {
               var3.write(var4, 0, var2);
            }

            var5 = var3.toString();
         } finally {
            if (var1 != null) {
               var1.close();
            }

         }

         return var5;
      } catch (Throwable var10) {
         System.err.println("Error while running uname -m: " + var10.getMessage());
         return "unknown";
      }
   }

   static String resolveArmArchType() {
      if (System.getProperty("os.name").contains("Linux")) {
         String var0 = getHardwareName();
         if (var0.startsWith("armv6")) {
            return "armv6";
         }

         if (var0.startsWith("armv7")) {
            return "armv7";
         }

         String var1 = System.getProperty("sun.arch.abi");
         if (var1 != null && var1.startsWith("gnueabihf")) {
            return "armv7";
         }

         try {
            int var2 = Runtime.getRuntime().exec("which readelf").waitFor();
            if (var2 == 0) {
               String var3 = System.getProperty("java.home");
               String[] var4 = new String[]{"/bin/sh", "-c", "find '" + var3 + "' -name 'libjvm.so' | head -1 | xargs readelf -A | grep 'Tag_ABI_VFP_args: VFP registers'"};
               var2 = Runtime.getRuntime().exec(var4).waitFor();
               if (var2 == 0) {
                  return "armv7";
               }
            } else {
               System.err.println("WARNING! readelf not found. Cannot check if running on an armhf system, armel architecture will be presumed.");
            }
         } catch (IOException var5) {
         } catch (InterruptedException var6) {
         }
      }

      return "arm";
   }

   public static String getArchName() {
      String var0 = System.getProperty("os.arch");
      if (isAndroid()) {
         return "android-arm";
      } else {
         if (var0.startsWith("arm")) {
            var0 = resolveArmArchType();
         } else {
            String var1 = var0.toLowerCase(Locale.US);
            if (archMapping.containsKey(var1)) {
               return (String)archMapping.get(var1);
            }
         }

         return translateArchNameToFolderName(var0);
      }
   }

   static String translateOSNameToFolderName(String var0) {
      if (var0.contains("Windows")) {
         return "Windows";
      } else if (var0.contains("Mac")) {
         return "Mac";
      } else if (var0.contains("Linux")) {
         return "Linux";
      } else {
         return var0.contains("AIX") ? "AIX" : var0.replaceAll("\\W", "");
      }
   }

   static String translateArchNameToFolderName(String var0) {
      return var0.replaceAll("\\W", "");
   }

   static {
      archMapping.put("x86", "x86");
      archMapping.put("i386", "x86");
      archMapping.put("i486", "x86");
      archMapping.put("i586", "x86");
      archMapping.put("i686", "x86");
      archMapping.put("pentium", "x86");
      archMapping.put("x86_64", "x86_64");
      archMapping.put("amd64", "x86_64");
      archMapping.put("em64t", "x86_64");
      archMapping.put("universal", "x86_64");
      archMapping.put("ia64", "ia64");
      archMapping.put("ia64w", "ia64");
      archMapping.put("ia64_32", "ia64_32");
      archMapping.put("ia64n", "ia64_32");
      archMapping.put("ppc", "ppc");
      archMapping.put("power", "ppc");
      archMapping.put("powerpc", "ppc");
      archMapping.put("power_pc", "ppc");
      archMapping.put("power_rs", "ppc");
      archMapping.put("ppc64", "ppc64");
      archMapping.put("power64", "ppc64");
      archMapping.put("powerpc64", "ppc64");
      archMapping.put("power_pc64", "ppc64");
      archMapping.put("power_rs64", "ppc64");
      archMapping.put("s390", "s390");
      archMapping.put("s390x", "s390x");
      archMapping.put("aarch64", "aarch64");
      archMapping.put("riscv64", "riscv64");
      archMapping.put("loongarch64", "loongarch64");
   }
}
