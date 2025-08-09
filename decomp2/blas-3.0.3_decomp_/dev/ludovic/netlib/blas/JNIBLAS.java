package dev.ludovic.netlib.blas;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermissions;
import org.netlib.util.doubleW;
import org.netlib.util.floatW;

final class JNIBLAS extends AbstractBLAS implements NativeBLAS {
   private static final JNIBLAS instance = new JNIBLAS();

   protected JNIBLAS() {
      String osName = System.getProperty("os.name");
      if (osName != null && !osName.isEmpty()) {
         String osArch = System.getProperty("os.arch");
         if (osArch != null && !osArch.isEmpty()) {
            Path temp;
            try {
               InputStream resource = this.getClass().getClassLoader().getResourceAsStream(String.format("resources/native/%s-%s/libnetlibblasjni.so", osName, osArch));

               try {
                  assert resource != null;

                  Files.copy(resource, temp = Files.createTempFile("libnetlibblasjni.so", "", PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxr-x---"))), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
                  temp.toFile().deleteOnExit();
               } catch (Throwable var8) {
                  if (resource != null) {
                     try {
                        resource.close();
                     } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                     }
                  }

                  throw var8;
               }

               if (resource != null) {
                  resource.close();
               }
            } catch (IOException e) {
               throw new RuntimeException("Unable to load native implementation", e);
            }

            System.load(temp.toString());
         } else {
            throw new RuntimeException("Unable to load native implementation");
         }
      } else {
         throw new RuntimeException("Unable to load native implementation");
      }
   }

   public static NativeBLAS getInstance() {
      return instance;
   }

   protected native double dasumK(int var1, double[] var2, int var3, int var4);

   protected native float sasumK(int var1, float[] var2, int var3, int var4);

   protected native void daxpyK(int var1, double var2, double[] var4, int var5, int var6, double[] var7, int var8, int var9);

   protected native void saxpyK(int var1, float var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8);

   protected native void dcopyK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7);

   protected native void scopyK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7);

   protected native double ddotK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7);

   protected native float sdotK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7);

   protected native float sdsdotK(int var1, float var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8);

   protected native void dgbmvK(String var1, int var2, int var3, int var4, int var5, double var6, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double var14, double[] var16, int var17, int var18);

   protected native void sgbmvK(String var1, int var2, int var3, int var4, int var5, float var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float var13, float[] var14, int var15, int var16);

   protected native void dgemmK(String var1, String var2, int var3, int var4, int var5, double var6, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double var14, double[] var16, int var17, int var18);

   protected native void sgemmK(String var1, String var2, int var3, int var4, int var5, float var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float var13, float[] var14, int var15, int var16);

   protected native void dgemvK(String var1, int var2, int var3, double var4, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double[] var14, int var15, int var16);

   protected native void sgemvK(String var1, int var2, int var3, float var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float var11, float[] var12, int var13, int var14);

   protected native void dgerK(int var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13);

   protected native void sgerK(int var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12);

   protected native double dnrm2K(int var1, double[] var2, int var3, int var4);

   protected native float snrm2K(int var1, float[] var2, int var3, int var4);

   protected native void drotK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double var8, double var10);

   protected native void srotK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float var8, float var9);

   protected native void drotmK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9);

   protected native void srotmK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9);

   protected native void drotmgK(doubleW var1, doubleW var2, doubleW var3, double var4, double[] var6, int var7);

   protected native void srotmgK(floatW var1, floatW var2, floatW var3, float var4, float[] var5, int var6);

   protected native void dsbmvK(String var1, int var2, int var3, double var4, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double[] var14, int var15, int var16);

   protected native void ssbmvK(String var1, int var2, int var3, float var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float var11, float[] var12, int var13, int var14);

   protected native void dscalK(int var1, double var2, double[] var4, int var5, int var6);

   protected native void sscalK(int var1, float var2, float[] var3, int var4, int var5);

   protected native void dspmvK(String var1, int var2, double var3, double[] var5, int var6, double[] var7, int var8, int var9, double var10, double[] var12, int var13, int var14);

   protected native void sspmvK(String var1, int var2, float var3, float[] var4, int var5, float[] var6, int var7, int var8, float var9, float[] var10, int var11, int var12);

   protected native void dsprK(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9);

   protected native void ssprK(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8);

   protected native void dspr2K(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12);

   protected native void sspr2K(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11);

   protected native void dswapK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7);

   protected native void sswapK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7);

   protected native void dsymmK(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double var13, double[] var15, int var16, int var17);

   protected native void ssymmK(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float[] var13, int var14, int var15);

   protected native void dsymvK(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double var11, double[] var13, int var14, int var15);

   protected native void ssymvK(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float var10, float[] var11, int var12, int var13);

   protected native void dsyrK(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   protected native void ssyrK(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9);

   protected native void dsyr2K(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13);

   protected native void ssyr2K(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12);

   protected native void dsyr2kK(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double var13, double[] var15, int var16, int var17);

   protected native void ssyr2kK(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float[] var13, int var14, int var15);

   protected native void dsyrkK(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, int var9, double var10, double[] var12, int var13, int var14);

   protected native void ssyrkK(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, int var8, float var9, float[] var10, int var11, int var12);

   protected native void dtbmvK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11);

   protected native void stbmvK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11);

   protected native void dtbsvK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11);

   protected native void stbsvK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11);

   protected native void dtpmvK(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9);

   protected native void stpmvK(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9);

   protected native void dtpsvK(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9);

   protected native void stpsvK(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9);

   protected native void dtrmmK(String var1, String var2, String var3, String var4, int var5, int var6, double var7, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   protected native void strmmK(String var1, String var2, String var3, String var4, int var5, int var6, float var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13);

   protected native void dtrmvK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   protected native void strmvK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10);

   protected native void dtrsmK(String var1, String var2, String var3, String var4, int var5, int var6, double var7, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   protected native void strsmK(String var1, String var2, String var3, String var4, int var5, int var6, float var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13);

   protected native void dtrsvK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   protected native void strsvK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10);

   protected native int idamaxK(int var1, double[] var2, int var3, int var4);

   protected native int isamaxK(int var1, float[] var2, int var3, int var4);
}
