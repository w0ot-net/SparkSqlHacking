package dev.ludovic.netlib.arpack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermissions;
import org.netlib.util.doubleW;
import org.netlib.util.floatW;
import org.netlib.util.intW;

final class JNIARPACK extends AbstractARPACK implements NativeARPACK {
   private static final JNIARPACK instance = new JNIARPACK();

   protected JNIARPACK() {
      String osName = System.getProperty("os.name");
      if (osName != null && !osName.isEmpty()) {
         String osArch = System.getProperty("os.arch");
         if (osArch != null && !osArch.isEmpty()) {
            Path temp;
            try {
               InputStream resource = this.getClass().getClassLoader().getResourceAsStream(String.format("resources/native/%s-%s/libnetlibarpackjni.so", osName, osArch));

               try {
                  assert resource != null;

                  Files.copy(resource, temp = Files.createTempFile("libnetlibarpackjni.so", "", PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxr-x---"))), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
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

   public static NativeARPACK getInstance() {
      return instance;
   }

   protected native void dmoutK(int var1, int var2, int var3, double[] var4, int var5, int var6, int var7, String var8);

   protected native void smoutK(int var1, int var2, int var3, float[] var4, int var5, int var6, int var7, String var8);

   protected native void dvoutK(int var1, int var2, double[] var3, int var4, int var5, String var6);

   protected native void svoutK(int var1, int var2, float[] var3, int var4, int var5, String var6);

   protected native void ivoutK(int var1, int var2, int[] var3, int var4, int var5, String var6);

   protected native void dgetv0K(intW var1, String var2, int var3, boolean var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, doubleW var12, int[] var13, int var14, double[] var15, int var16, intW var17);

   protected native void sgetv0K(intW var1, String var2, int var3, boolean var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, floatW var12, int[] var13, int var14, float[] var15, int var16, intW var17);

   protected native void dlaqrbK(boolean var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   protected native void slaqrbK(boolean var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   protected native void dnaitrK(intW var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, doubleW var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, double[] var18, int var19, intW var20);

   protected native void snaitrK(intW var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, floatW var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, float[] var18, int var19, intW var20);

   protected native void dnappsK(int var1, intW var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, double[] var21, int var22);

   protected native void snappsK(int var1, intW var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, float[] var21, int var22);

   protected native void dnaup2K(intW var1, String var2, int var3, String var4, intW var5, intW var6, double var7, double[] var9, int var10, int var11, int var12, int var13, intW var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, double[] var27, int var28, int var29, double[] var30, int var31, int[] var32, int var33, double[] var34, int var35, intW var36);

   protected native void snaup2K(intW var1, String var2, int var3, String var4, intW var5, intW var6, float var7, float[] var8, int var9, int var10, int var11, int var12, intW var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, float[] var26, int var27, int var28, float[] var29, int var30, int[] var31, int var32, float[] var33, int var34, intW var35);

   protected native void dnaupdK(intW var1, String var2, int var3, String var4, int var5, doubleW var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, intW var22);

   protected native void snaupdK(intW var1, String var2, int var3, String var4, int var5, floatW var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, intW var22);

   protected native void dnconvK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, intW var10);

   protected native void snconvK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, intW var9);

   protected native void dsconvK(int var1, double[] var2, int var3, double[] var4, int var5, double var6, intW var8);

   protected native void ssconvK(int var1, float[] var2, int var3, float[] var4, int var5, float var6, intW var7);

   protected native void dneighK(double var1, intW var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, intW var18);

   protected native void sneighK(float var1, intW var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   protected native void dneupdK(boolean var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double var12, double var14, double[] var16, int var17, String var18, int var19, String var20, intW var21, double var22, double[] var24, int var25, int var26, double[] var27, int var28, int var29, int[] var30, int var31, int[] var32, int var33, double[] var34, int var35, double[] var36, int var37, int var38, intW var39);

   protected native void sneupdK(boolean var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float var12, float var13, float[] var14, int var15, String var16, int var17, String var18, intW var19, float var20, float[] var21, int var22, int var23, float[] var24, int var25, int var26, int[] var27, int var28, int[] var29, int var30, float[] var31, int var32, float[] var33, int var34, int var35, intW var36);

   protected native void dngetsK(int var1, String var2, intW var3, intW var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14);

   protected native void sngetsK(int var1, String var2, intW var3, intW var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14);

   protected native void dsaitrK(intW var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, doubleW var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, double[] var18, int var19, intW var20);

   protected native void ssaitrK(intW var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, floatW var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, float[] var18, int var19, intW var20);

   protected native void dsappsK(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18);

   protected native void ssappsK(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18);

   protected native void dsaup2K(intW var1, String var2, int var3, String var4, intW var5, intW var6, double var7, double[] var9, int var10, int var11, int var12, int var13, intW var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, int var27, double[] var28, int var29, int[] var30, int var31, double[] var32, int var33, intW var34);

   protected native void ssaup2K(intW var1, String var2, int var3, String var4, intW var5, intW var6, float var7, float[] var8, int var9, int var10, int var11, int var12, intW var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int var26, float[] var27, int var28, int[] var29, int var30, float[] var31, int var32, intW var33);

   protected native void dseigtK(double var1, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, intW var13);

   protected native void sseigtK(float var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12);

   protected native void dsesrtK(String var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9);

   protected native void ssesrtK(String var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9);

   protected native void dsaupdK(intW var1, String var2, int var3, String var4, int var5, doubleW var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, intW var22);

   protected native void ssaupdK(intW var1, String var2, int var3, String var4, int var5, floatW var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, intW var22);

   protected native void dseupdK(boolean var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double var10, String var12, int var13, String var14, intW var15, double var16, double[] var18, int var19, int var20, double[] var21, int var22, int var23, int[] var24, int var25, int[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int var32, intW var33);

   protected native void sseupdK(boolean var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float var10, String var11, int var12, String var13, intW var14, float var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, int[] var22, int var23, int[] var24, int var25, float[] var26, int var27, float[] var28, int var29, int var30, intW var31);

   protected native void dsgetsK(int var1, String var2, intW var3, intW var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10);

   protected native void ssgetsK(int var1, String var2, intW var3, intW var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10);

   protected native void dsortcK(String var1, boolean var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9);

   protected native void ssortcK(String var1, boolean var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9);

   protected native void dsortrK(String var1, boolean var2, int var3, double[] var4, int var5, double[] var6, int var7);

   protected native void ssortrK(String var1, boolean var2, int var3, float[] var4, int var5, float[] var6, int var7);

   protected native void dstatnK();

   protected native void sstatnK();

   protected native void dstatsK();

   protected native void sstatsK();

   protected native void dstqrbK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   protected native void sstqrbK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   protected native int icnteqK(int var1, int[] var2, int var3, int var4);

   protected native void icopyK(int var1, int[] var2, int var3, int var4, int[] var5, int var6, int var7);

   protected native void isetK(int var1, int var2, int[] var3, int var4, int var5);

   protected native void iswapK(int var1, int[] var2, int var3, int var4, int[] var5, int var6, int var7);

   protected native void secondK(floatW var1);
}
