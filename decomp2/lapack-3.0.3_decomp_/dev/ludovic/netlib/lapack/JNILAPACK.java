package dev.ludovic.netlib.lapack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermissions;
import org.netlib.util.StringW;
import org.netlib.util.booleanW;
import org.netlib.util.doubleW;
import org.netlib.util.floatW;
import org.netlib.util.intW;

final class JNILAPACK extends AbstractLAPACK implements NativeLAPACK {
   private static final JNILAPACK instance = new JNILAPACK();

   protected JNILAPACK() {
      String osName = System.getProperty("os.name");
      if (osName != null && !osName.isEmpty()) {
         String osArch = System.getProperty("os.arch");
         if (osArch != null && !osArch.isEmpty()) {
            Path temp;
            try {
               InputStream resource = this.getClass().getClassLoader().getResourceAsStream(String.format("resources/native/%s-%s/libnetliblapackjni.so", osName, osArch));

               try {
                  assert resource != null;

                  Files.copy(resource, temp = Files.createTempFile("libnetliblapackjni.so", "", PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxr-x---"))), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
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

   public static NativeLAPACK getInstance() {
      return instance;
   }

   protected native void dbdsdcK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int[] var16, int var17, double[] var18, int var19, int[] var20, int var21, intW var22);

   protected native void dbdsqrK(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, intW var21);

   protected native void ddisnaK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   protected native void dgbbrdK(String var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, intW var25);

   protected native void dgbconK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, double var10, doubleW var12, double[] var13, int var14, int[] var15, int var16, intW var17);

   protected native void dgbequK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, doubleW var12, doubleW var13, doubleW var14, intW var15);

   protected native void dgbrfsK(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, double[] var22, int var23, double[] var24, int var25, int[] var26, int var27, intW var28);

   protected native void dgbsvK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   protected native void dgbsvxK(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, StringW var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, doubleW var26, double[] var27, int var28, double[] var29, int var30, double[] var31, int var32, int[] var33, int var34, intW var35);

   protected native void dgbtf2K(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   protected native void dgbtrfK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   protected native void dgbtrsK(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int var8, int[] var9, int var10, double[] var11, int var12, int var13, intW var14);

   protected native void dgebakK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dgebalK(String var1, int var2, double[] var3, int var4, int var5, intW var6, intW var7, double[] var8, int var9, intW var10);

   protected native void dgebd2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, intW var16);

   protected native void dgebrdK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   protected native void dgeconK(String var1, int var2, double[] var3, int var4, int var5, double var6, doubleW var8, double[] var9, int var10, int[] var11, int var12, intW var13);

   protected native void dgeequK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, doubleW var10, doubleW var11, doubleW var12, intW var13);

   protected native void dgeesK(String var1, String var2, Object var3, int var4, double[] var5, int var6, int var7, intW var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, boolean[] var19, int var20, intW var21);

   protected native void dgeesxK(String var1, String var2, Object var3, String var4, int var5, double[] var6, int var7, int var8, intW var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, doubleW var17, doubleW var18, double[] var19, int var20, int var21, int[] var22, int var23, int var24, boolean[] var25, int var26, intW var27);

   protected native void dgeevK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, intW var20);

   protected native void dgeevxK(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, intW var19, intW var20, double[] var21, int var22, doubleW var23, double[] var24, int var25, double[] var26, int var27, double[] var28, int var29, int var30, int[] var31, int var32, intW var33);

   protected native void dgegsK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25);

   protected native void dgegvK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25);

   protected native void dgehd2K(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   protected native void dgehrdK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dgelq2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   protected native void dgelqfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dgelsK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, intW var14);

   protected native void dgelsdK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double var12, intW var14, double[] var15, int var16, int var17, int[] var18, int var19, intW var20);

   protected native void dgelssK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double var12, intW var14, double[] var15, int var16, int var17, intW var18);

   protected native void dgelsxK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double var12, intW var14, double[] var15, int var16, intW var17);

   protected native void dgelsyK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double var12, intW var14, double[] var15, int var16, int var17, intW var18);

   protected native void dgeql2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   protected native void dgeqlfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dgeqp3K(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   protected native void dgeqpfK(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, double[] var10, int var11, intW var12);

   protected native void dgeqr2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   protected native void dgeqrfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dgerfsK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, intW var26);

   protected native void dgerq2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   protected native void dgerqfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dgesc2K(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int[] var7, int var8, int[] var9, int var10, doubleW var11);

   protected native void dgesddK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, int[] var18, int var19, intW var20);

   protected native void dgesvK(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dgesvdK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, intW var19);

   protected native void dgesvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, int[] var11, int var12, StringW var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, doubleW var24, double[] var25, int var26, double[] var27, int var28, double[] var29, int var30, int[] var31, int var32, intW var33);

   protected native void dgetc2K(int var1, double[] var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, intW var9);

   protected native void dgetf2K(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   protected native void dgetrfK(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   protected native void dgetriK(int var1, double[] var2, int var3, int var4, int[] var5, int var6, double[] var7, int var8, int var9, intW var10);

   protected native void dgetrsK(String var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dggbakK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, intW var14);

   protected native void dggbalK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, int var8, intW var9, intW var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, intW var17);

   protected native void dggesK(String var1, String var2, String var3, Object var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, intW var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, boolean[] var28, int var29, intW var30);

   protected native void dggesxK(String var1, String var2, String var3, Object var4, String var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, intW var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int var32, int[] var33, int var34, int var35, boolean[] var36, int var37, intW var38);

   protected native void dggevK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25);

   protected native void dggevxK(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, intW var24, intW var25, double[] var26, int var27, double[] var28, int var29, doubleW var30, doubleW var31, double[] var32, int var33, double[] var34, int var35, double[] var36, int var37, int var38, int[] var39, int var40, boolean[] var41, int var42, intW var43);

   protected native void dggglmK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, intW var19);

   protected native void dgghrdK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   protected native void dgglseK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, intW var19);

   protected native void dggqrfK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   protected native void dggrqfK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   protected native void dggsvdK(String var1, String var2, String var3, int var4, int var5, int var6, intW var7, intW var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, double[] var28, int var29, int[] var30, int var31, intW var32);

   protected native void dggsvpK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double var13, double var15, intW var17, intW var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, int[] var28, int var29, double[] var30, int var31, double[] var32, int var33, intW var34);

   protected native void dgtconK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int[] var11, int var12, double var13, doubleW var15, double[] var16, int var17, int[] var18, int var19, intW var20);

   protected native void dgtrfsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int[] var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int[] var32, int var33, intW var34);

   protected native void dgtsvK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dgtsvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, int[] var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, int var26, doubleW var27, double[] var28, int var29, double[] var30, int var31, double[] var32, int var33, int[] var34, int var35, intW var36);

   protected native void dgttrfK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   protected native void dgttrsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   protected native void dgtts2K(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int[] var12, int var13, double[] var14, int var15, int var16);

   protected native void dhgeqzK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, intW var28);

   protected native void dhseinK(String var1, String var2, String var3, boolean[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, int var20, intW var21, double[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   protected native void dhseqrK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, intW var19);

   protected native boolean disnanK(double var1);

   protected native void dlabadK(doubleW var1, doubleW var2);

   protected native void dlabrdK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20);

   protected native void dlacn2K(int var1, double[] var2, int var3, double[] var4, int var5, int[] var6, int var7, doubleW var8, intW var9, int[] var10, int var11);

   protected native void dlaconK(int var1, double[] var2, int var3, double[] var4, int var5, int[] var6, int var7, doubleW var8, intW var9);

   protected native void dlacpyK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9);

   protected native void dladivK(double var1, double var3, double var5, double var7, doubleW var9, doubleW var10);

   protected native void dlae2K(double var1, double var3, double var5, doubleW var7, doubleW var8);

   protected native void dlaebzK(int var1, int var2, int var3, int var4, int var5, int var6, double var7, double var9, double var11, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, int[] var19, int var20, double[] var21, int var22, double[] var23, int var24, intW var25, int[] var26, int var27, double[] var28, int var29, int[] var30, int var31, intW var32);

   protected native void dlaed0K(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int[] var16, int var17, intW var18);

   protected native void dlaed1K(int var1, double[] var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, doubleW var9, int var10, double[] var11, int var12, int[] var13, int var14, intW var15);

   protected native void dlaed2K(intW var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, int[] var9, int var10, doubleW var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   protected native void dlaed3K(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, double var9, double[] var11, int var12, double[] var13, int var14, int[] var15, int var16, int[] var17, int var18, double[] var19, int var20, double[] var21, int var22, intW var23);

   protected native void dlaed4K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double var9, doubleW var11, intW var12);

   protected native void dlaed5K(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, doubleW var10);

   protected native void dlaed6K(int var1, boolean var2, double var3, double[] var5, int var6, double[] var7, int var8, double var9, doubleW var11, intW var12);

   protected native void dlaed7K(int var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, doubleW var14, int var15, double[] var16, int var17, int[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int[] var32, int var33, intW var34);

   protected native void dlaed8K(int var1, intW var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, doubleW var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int[] var23, int var24, intW var25, int[] var26, int var27, double[] var28, int var29, int[] var30, int var31, int[] var32, int var33, intW var34);

   protected native void dlaed9K(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double var10, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, intW var19);

   protected native void dlaedaK(int var1, int var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, int[] var9, int var10, int[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int[] var17, int var18, double[] var19, int var20, double[] var21, int var22, intW var23);

   protected native void dlaeinK(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double var7, double var9, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double var20, double var22, double var24, intW var26);

   protected native void dlaev2K(double var1, double var3, double var5, doubleW var7, doubleW var8, doubleW var9, doubleW var10);

   protected native void dlaexcK(boolean var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, int var8, int var9, int var10, int var11, double[] var12, int var13, intW var14);

   protected native void dlag2K(double[] var1, int var2, int var3, double[] var4, int var5, int var6, double var7, doubleW var9, doubleW var10, doubleW var11, doubleW var12, doubleW var13);

   protected native void dlag2sK(int var1, int var2, double[] var3, int var4, int var5, float[] var6, int var7, int var8, intW var9);

   protected native void dlags2K(boolean var1, double var2, double var4, double var6, double var8, double var10, double var12, doubleW var14, doubleW var15, doubleW var16, doubleW var17, doubleW var18, doubleW var19);

   protected native void dlagtfK(int var1, double[] var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double var10, double[] var12, int var13, int[] var14, int var15, intW var16);

   protected native void dlagtmK(String var1, int var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double var15, double[] var17, int var18, int var19);

   protected native void dlagtsK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int[] var11, int var12, double[] var13, int var14, doubleW var15, intW var16);

   protected native void dlagv2K(double[] var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, doubleW var13, doubleW var14, doubleW var15, doubleW var16);

   protected native void dlahqrK(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   protected native void dlahr2K(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   protected native void dlahrdK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   protected native void dlaic1K(int var1, int var2, double[] var3, int var4, double var5, double[] var7, int var8, double var9, doubleW var11, doubleW var12, doubleW var13);

   protected native boolean dlaisnanK(double var1, double var3);

   protected native void dlaln2K(boolean var1, int var2, int var3, double var4, double var6, double[] var8, int var9, int var10, double var11, double var13, double[] var15, int var16, int var17, double var18, double var20, double[] var22, int var23, int var24, doubleW var25, doubleW var26, intW var27);

   protected native void dlals0K(int var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, int var14, int[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, double[] var27, int var28, int var29, double var30, double var32, double[] var34, int var35, intW var36);

   protected native void dlalsaK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int[] var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, double[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int var30, int[] var31, int var32, double[] var33, int var34, double[] var35, int var36, double[] var37, int var38, double[] var39, int var40, int[] var41, int var42, intW var43);

   protected native void dlalsdK(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double var12, intW var14, double[] var15, int var16, int[] var17, int var18, intW var19);

   protected native void dlamrgK(int var1, int var2, double[] var3, int var4, int var5, int var6, int[] var7, int var8);

   protected native int dlanegK(int var1, double[] var2, int var3, double[] var4, int var5, double var6, double var8, int var10);

   protected native double dlangbK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9);

   protected native double dlangeK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8);

   protected native double dlangtK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8);

   protected native double dlanhsK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7);

   protected native double dlansbK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9);

   protected native double dlanspK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7);

   protected native double dlanstK(String var1, int var2, double[] var3, int var4, double[] var5, int var6);

   protected native double dlansyK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8);

   protected native double dlantbK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10);

   protected native double dlantpK(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8);

   protected native double dlantrK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10);

   protected native void dlanv2K(doubleW var1, doubleW var2, doubleW var3, doubleW var4, doubleW var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, doubleW var10);

   protected native void dlapllK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, doubleW var8);

   protected native void dlapmtK(boolean var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8);

   protected native double dlapy2K(double var1, double var3);

   protected native double dlapy3K(double var1, double var3, double var5);

   protected native void dlaqgbK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double var12, double var14, double var16, StringW var18);

   protected native void dlaqgeK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double var10, double var12, double var14, StringW var16);

   protected native void dlaqp2K(int var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16);

   protected native void dlaqpsK(int var1, int var2, int var3, int var4, intW var5, double[] var6, int var7, int var8, int[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21);

   protected native void dlaqr0K(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, intW var21);

   protected native void dlaqr1K(int var1, double[] var2, int var3, int var4, double var5, double var7, double var9, double var11, double[] var13, int var14);

   protected native void dlaqr2K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, int var10, int var11, double[] var12, int var13, int var14, intW var15, intW var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int var23, int var24, double[] var25, int var26, int var27, int var28, double[] var29, int var30, int var31, double[] var32, int var33, int var34);

   protected native void dlaqr3K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, int var10, int var11, double[] var12, int var13, int var14, intW var15, intW var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int var23, int var24, double[] var25, int var26, int var27, int var28, double[] var29, int var30, int var31, double[] var32, int var33, int var34);

   protected native void dlaqr4K(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, intW var21);

   protected native void dlaqr5K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, int var26, double[] var27, int var28, int var29, int var30, double[] var31, int var32, int var33);

   protected native void dlaqsbK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double var9, double var11, StringW var13);

   protected native void dlaqspK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double var7, double var9, StringW var11);

   protected native void dlaqsyK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double var8, double var10, StringW var12);

   protected native void dlaqtrK(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double var9, doubleW var11, double[] var12, int var13, double[] var14, int var15, intW var16);

   protected native void dlar1vK(int var1, int var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double var14, double var16, double[] var18, int var19, boolean var20, intW var21, doubleW var22, doubleW var23, intW var24, int[] var25, int var26, doubleW var27, doubleW var28, doubleW var29, double[] var30, int var31);

   protected native void dlar2vK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13);

   protected native void dlarfK(String var1, int var2, int var3, double[] var4, int var5, int var6, double var7, double[] var9, int var10, int var11, double[] var12, int var13);

   protected native void dlarfbK(String var1, String var2, String var3, String var4, int var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19);

   protected native void dlarfgK(int var1, doubleW var2, double[] var3, int var4, int var5, doubleW var6);

   protected native void dlarftK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   protected native void dlarfxK(String var1, int var2, int var3, double[] var4, int var5, double var6, double[] var8, int var9, int var10, double[] var11, int var12);

   protected native void dlargvK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   protected native void dlarnvK(int var1, int[] var2, int var3, int var4, double[] var5, int var6);

   protected native void dlarraK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, intW var12, int[] var13, int var14, intW var15);

   protected native void dlarrbK(int var1, double[] var2, int var3, double[] var4, int var5, int var6, int var7, double var8, double var10, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int[] var21, int var22, double var23, double var25, int var27, intW var28);

   protected native void dlarrcK(String var1, int var2, double var3, double var5, double[] var7, int var8, double[] var9, int var10, double var11, intW var13, intW var14, intW var15, intW var16);

   protected native void dlarrdK(String var1, String var2, int var3, double var4, double var6, int var8, int var9, double[] var10, int var11, double var12, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double var20, int var22, int[] var23, int var24, intW var25, double[] var26, int var27, double[] var28, int var29, doubleW var30, doubleW var31, int[] var32, int var33, int[] var34, int var35, double[] var36, int var37, int[] var38, int var39, intW var40);

   protected native void dlarreK(String var1, int var2, doubleW var3, doubleW var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double var13, double var15, double var17, intW var19, int[] var20, int var21, intW var22, double[] var23, int var24, double[] var25, int var26, double[] var27, int var28, int[] var29, int var30, int[] var31, int var32, double[] var33, int var34, doubleW var35, double[] var36, int var37, int[] var38, int var39, intW var40);

   protected native void dlarrfK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double var16, double var18, double var20, double var22, doubleW var24, double[] var25, int var26, double[] var27, int var28, double[] var29, int var30, intW var31);

   protected native void dlarrjK(int var1, double[] var2, int var3, double[] var4, int var5, int var6, int var7, double var8, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int[] var17, int var18, double var19, double var21, intW var23);

   protected native void dlarrkK(int var1, int var2, double var3, double var5, double[] var7, int var8, double[] var9, int var10, double var11, double var13, doubleW var15, doubleW var16, intW var17);

   protected native void dlarrrK(int var1, double[] var2, int var3, double[] var4, int var5, intW var6);

   protected native void dlarrvK(int var1, double var2, double var4, double[] var6, int var7, double[] var8, int var9, double var10, int[] var12, int var13, int var14, int var15, int var16, double var17, doubleW var19, doubleW var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, int[] var27, int var28, int[] var29, int var30, double[] var31, int var32, double[] var33, int var34, int var35, int[] var36, int var37, double[] var38, int var39, int[] var40, int var41, intW var42);

   protected native void dlartgK(double var1, double var3, doubleW var5, doubleW var6, doubleW var7);

   protected native void dlartvK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   protected native void dlaruvK(int[] var1, int var2, int var3, double[] var4, int var5);

   protected native void dlarzK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double var8, double[] var10, int var11, int var12, double[] var13, int var14);

   protected native void dlarzbK(String var1, String var2, String var3, String var4, int var5, int var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20);

   protected native void dlarztK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   protected native void dlas2K(double var1, double var3, double var5, doubleW var7, doubleW var8);

   protected native void dlasclK(String var1, int var2, int var3, double var4, double var6, int var8, int var9, double[] var10, int var11, int var12, intW var13);

   protected native void dlasd0K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int var13, int[] var14, int var15, double[] var16, int var17, intW var18);

   protected native void dlasd1K(int var1, int var2, int var3, double[] var4, int var5, doubleW var6, doubleW var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, int[] var14, int var15, int[] var16, int var17, double[] var18, int var19, intW var20);

   protected native void dlasd2K(int var1, int var2, int var3, intW var4, double[] var5, int var6, double[] var7, int var8, double var9, double var11, double[] var13, int var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int[] var29, int var30, int[] var31, int var32, int[] var33, int var34, int[] var35, int var36, intW var37);

   protected native void dlasd3K(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, int[] var24, int var25, int[] var26, int var27, double[] var28, int var29, intW var30);

   protected native void dlasd4K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double var9, doubleW var11, double[] var12, int var13, intW var14);

   protected native void dlasd5K(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, doubleW var10, double[] var11, int var12);

   protected native void dlasd6K(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, doubleW var11, doubleW var12, int[] var13, int var14, int[] var15, int var16, intW var17, int[] var18, int var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, double[] var26, int var27, double[] var28, int var29, double[] var30, int var31, intW var32, doubleW var33, doubleW var34, double[] var35, int var36, int[] var37, int var38, intW var39);

   protected native void dlasd7K(int var1, int var2, int var3, int var4, intW var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double var20, double var22, double[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int[] var30, int var31, int[] var32, int var33, intW var34, int[] var35, int var36, int var37, double[] var38, int var39, int var40, doubleW var41, doubleW var42, intW var43);

   protected native void dlasd8K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, intW var20);

   protected native void dlasdaK(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, int[] var26, int var27, int var28, int[] var29, int var30, double[] var31, int var32, double[] var33, int var34, double[] var35, int var36, double[] var37, int var38, int[] var39, int var40, intW var41);

   protected native void dlasdqK(String var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, intW var22);

   protected native void dlasdtK(int var1, intW var2, intW var3, int[] var4, int var5, int[] var6, int var7, int[] var8, int var9, int var10);

   protected native void dlasetK(String var1, int var2, int var3, double var4, double var6, double[] var8, int var9, int var10);

   protected native void dlasq1K(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   protected native void dlasq2K(int var1, double[] var2, int var3, intW var4);

   protected native void dlasq3K(int var1, intW var2, double[] var3, int var4, int var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, intW var10, intW var11, intW var12, boolean var13);

   protected native void dlasq4K(int var1, int var2, double[] var3, int var4, int var5, int var6, double var7, double var9, double var11, double var13, double var15, double var17, doubleW var19, intW var20);

   protected native void dlasq5K(int var1, int var2, double[] var3, int var4, int var5, double var6, doubleW var8, doubleW var9, doubleW var10, doubleW var11, doubleW var12, doubleW var13, boolean var14);

   protected native void dlasq6K(int var1, int var2, double[] var3, int var4, int var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, doubleW var10, doubleW var11);

   protected native void dlasrK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   protected native void dlasrtK(String var1, int var2, double[] var3, int var4, intW var5);

   protected native void dlassqK(int var1, double[] var2, int var3, int var4, doubleW var5, doubleW var6);

   protected native void dlasv2K(double var1, double var3, double var5, doubleW var7, doubleW var8, doubleW var9, doubleW var10, doubleW var11, doubleW var12);

   protected native void dlaswpK(int var1, double[] var2, int var3, int var4, int var5, int var6, int[] var7, int var8, int var9);

   protected native void dlasy2K(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, doubleW var15, double[] var16, int var17, int var18, doubleW var19, intW var20);

   protected native void dlasyfK(String var1, int var2, int var3, intW var4, double[] var5, int var6, int var7, int[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   protected native void dlatbsK(String var1, String var2, String var3, String var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, doubleW var12, double[] var13, int var14, intW var15);

   protected native void dlatdfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, doubleW var8, doubleW var9, int[] var10, int var11, int[] var12, int var13);

   protected native void dlatpsK(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, int var9, doubleW var10, double[] var11, int var12, intW var13);

   protected native void dlatrdK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13);

   protected native void dlatrsK(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, doubleW var11, double[] var12, int var13, intW var14);

   protected native void dlatrzK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10);

   protected native void dlatzmK(String var1, int var2, int var3, double[] var4, int var5, int var6, double var7, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15);

   protected native void dlauu2K(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   protected native void dlauumK(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   protected native void dlazq3K(int var1, intW var2, double[] var3, int var4, int var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, intW var10, intW var11, intW var12, boolean var13, intW var14, doubleW var15, doubleW var16, doubleW var17, doubleW var18, doubleW var19, doubleW var20);

   protected native void dlazq4K(int var1, int var2, double[] var3, int var4, int var5, int var6, double var7, double var9, double var11, double var13, double var15, double var17, doubleW var19, intW var20, doubleW var21);

   protected native void dopgtrK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   protected native void dopmtrK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, intW var15);

   protected native void dorg2lK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   protected native void dorg2rK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   protected native void dorgbrK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   protected native void dorghrK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dorgl2K(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   protected native void dorglqK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dorgqlK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dorgqrK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dorgr2K(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   protected native void dorgrqK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dorgtrK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dorm2lK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   protected native void dorm2rK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   protected native void dormbrK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   protected native void dormhrK(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   protected native void dorml2K(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   protected native void dormlqK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   protected native void dormqlK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   protected native void dormqrK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   protected native void dormr2K(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   protected native void dormr3K(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, intW var17);

   protected native void dormrqK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   protected native void dormrzK(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   protected native void dormtrK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   protected native void dpbconK(String var1, int var2, int var3, double[] var4, int var5, int var6, double var7, doubleW var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   protected native void dpbequK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, doubleW var9, doubleW var10, intW var11);

   protected native void dpbrfsK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int[] var23, int var24, intW var25);

   protected native void dpbstfK(String var1, int var2, int var3, double[] var4, int var5, int var6, intW var7);

   protected native void dpbsvK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dpbsvxK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, StringW var12, double[] var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, doubleW var21, double[] var22, int var23, double[] var24, int var25, double[] var26, int var27, int[] var28, int var29, intW var30);

   protected native void dpbtf2K(String var1, int var2, int var3, double[] var4, int var5, int var6, intW var7);

   protected native void dpbtrfK(String var1, int var2, int var3, double[] var4, int var5, int var6, intW var7);

   protected native void dpbtrsK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dpoconK(String var1, int var2, double[] var3, int var4, int var5, double var6, doubleW var8, double[] var9, int var10, int[] var11, int var12, intW var13);

   protected native void dpoequK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, doubleW var7, doubleW var8, intW var9);

   protected native void dporfsK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   protected native void dposvK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   protected native void dposvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, StringW var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, doubleW var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, int[] var27, int var28, intW var29);

   protected native void dpotf2K(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   protected native void dpotrfK(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   protected native void dpotriK(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   protected native void dpotrsK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   protected native void dppconK(String var1, int var2, double[] var3, int var4, double var5, doubleW var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   protected native void dppequK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, doubleW var7, doubleW var8, intW var9);

   protected native void dpprfsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int var21, intW var22);

   protected native void dppsvK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, intW var9);

   protected native void dppsvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, StringW var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, doubleW var18, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, int[] var25, int var26, intW var27);

   protected native void dpptrfK(String var1, int var2, double[] var3, int var4, intW var5);

   protected native void dpptriK(String var1, int var2, double[] var3, int var4, intW var5);

   protected native void dpptrsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, intW var9);

   protected native void dptconK(int var1, double[] var2, int var3, double[] var4, int var5, double var6, doubleW var8, double[] var9, int var10, intW var11);

   protected native void dpteqrK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   protected native void dptrfsK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, intW var23);

   protected native void dptsvK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, intW var10);

   protected native void dptsvxK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, doubleW var18, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, intW var25);

   protected native void dpttrfK(int var1, double[] var2, int var3, double[] var4, int var5, intW var6);

   protected native void dpttrsK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, intW var10);

   protected native void dptts2K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9);

   protected native void drsclK(int var1, double var2, double[] var4, int var5, int var6);

   protected native void dsbevK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, intW var15);

   protected native void dsbevdK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   protected native void dsbevxK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double var14, int var16, int var17, double var18, intW var20, double[] var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, int[] var28, int var29, int[] var30, int var31, intW var32);

   protected native void dsbgstK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, intW var17);

   protected native void dsbgvK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, intW var19);

   protected native void dsbgvdK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, int[] var20, int var21, int var22, intW var23);

   protected native void dsbgvxK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double var16, double var18, int var20, int var21, double var22, intW var24, double[] var25, int var26, double[] var27, int var28, int var29, double[] var30, int var31, int[] var32, int var33, int[] var34, int var35, intW var36);

   protected native void dsbtrdK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, intW var17);

   protected native void dsgesvK(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, float[] var16, int var17, intW var18, intW var19);

   protected native void dspconK(String var1, int var2, double[] var3, int var4, int[] var5, int var6, double var7, doubleW var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   protected native void dspevK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, intW var13);

   protected native void dspevdK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, int[] var14, int var15, int var16, intW var17);

   protected native void dspevxK(String var1, String var2, String var3, int var4, double[] var5, int var6, double var7, double var9, int var11, int var12, double var13, intW var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int[] var23, int var24, int[] var25, int var26, intW var27);

   protected native void dspgstK(int var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   protected native void dspgvK(int var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   protected native void dspgvdK(int var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, int[] var17, int var18, int var19, intW var20);

   protected native void dspgvxK(int var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, int var9, double var10, double var12, int var14, int var15, double var16, intW var18, double[] var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, int[] var26, int var27, int[] var28, int var29, intW var30);

   protected native void dsprfsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   protected native void dspsvK(String var1, int var2, int var3, double[] var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dspsvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, doubleW var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, intW var26);

   protected native void dsptrdK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   protected native void dsptrfK(String var1, int var2, double[] var3, int var4, int[] var5, int var6, intW var7);

   protected native void dsptriK(String var1, int var2, double[] var3, int var4, int[] var5, int var6, double[] var7, int var8, intW var9);

   protected native void dsptrsK(String var1, int var2, int var3, double[] var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dstebzK(String var1, String var2, int var3, double var4, double var6, int var8, int var9, double var10, double[] var12, int var13, double[] var14, int var15, intW var16, intW var17, double[] var18, int var19, int[] var20, int var21, int[] var22, int var23, double[] var24, int var25, int[] var26, int var27, intW var28);

   protected native void dstedcK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   protected native void dstegrK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, int[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   protected native void dsteinK(int var1, double[] var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int[] var9, int var10, int[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int[] var18, int var19, int[] var20, int var21, intW var22);

   protected native void dstemrK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, intW var14, double[] var15, int var16, double[] var17, int var18, int var19, int var20, int[] var21, int var22, booleanW var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   protected native void dsteqrK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   protected native void dsterfK(int var1, double[] var2, int var3, double[] var4, int var5, intW var6);

   protected native void dstevK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   protected native void dstevdK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   protected native void dstevrK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, int[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   protected native void dstevxK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   protected native void dsyconK(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double var8, doubleW var10, double[] var11, int var12, int[] var13, int var14, intW var15);

   protected native void dsyevK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dsyevdK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, int var14, intW var15);

   protected native void dsyevrK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, int[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   protected native void dsyevxK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, int[] var25, int var26, int[] var27, int var28, intW var29);

   protected native void dsygs2K(int var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   protected native void dsygstK(int var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   protected native void dsygvK(int var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, intW var16);

   protected native void dsygvdK(int var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   protected native void dsygvxK(int var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double var14, int var16, int var17, double var18, intW var20, double[] var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, int var28, int[] var29, int var30, int[] var31, int var32, intW var33);

   protected native void dsyrfsK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, intW var26);

   protected native void dsysvK(String var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, intW var15);

   protected native void dsysvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, int[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, doubleW var19, double[] var20, int var21, double[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   protected native void dsytd2K(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, intW var12);

   protected native void dsytf2K(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   protected native void dsytrdK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, intW var15);

   protected native void dsytrfK(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dsytriK(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, intW var10);

   protected native void dsytrsK(String var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dtbconK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, doubleW var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   protected native void dtbrfsK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   protected native void dtbtrsK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, intW var13);

   protected native void dtgevcK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, int var18, intW var19, double[] var20, int var21, intW var22);

   protected native void dtgex2K(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int var16, int var17, int var18, double[] var19, int var20, int var21, intW var22);

   protected native void dtgexcK(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, intW var16, intW var17, double[] var18, int var19, int var20, intW var21);

   protected native void dtgsenK(int var1, boolean var2, boolean var3, boolean[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25, doubleW var26, doubleW var27, double[] var28, int var29, double[] var30, int var31, int var32, int[] var33, int var34, int var35, intW var36);

   protected native void dtgsjaK(String var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double var15, double var17, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, int var28, double[] var29, int var30, int var31, double[] var32, int var33, intW var34, intW var35);

   protected native void dtgsnaK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double[] var20, int var21, int var22, intW var23, double[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   protected native void dtgsy2K(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, doubleW var23, doubleW var24, doubleW var25, int[] var26, int var27, intW var28, intW var29);

   protected native void dtgsylK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, doubleW var23, doubleW var24, double[] var25, int var26, int var27, int[] var28, int var29, intW var30);

   protected native void dtpconK(String var1, String var2, String var3, int var4, double[] var5, int var6, doubleW var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   protected native void dtprfsK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int var21, intW var22);

   protected native void dtptriK(String var1, String var2, int var3, double[] var4, int var5, intW var6);

   protected native void dtptrsK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native void dtrconK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, doubleW var8, double[] var9, int var10, int[] var11, int var12, intW var13);

   protected native void dtrevcK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, int var15, intW var16, double[] var17, int var18, intW var19);

   protected native void dtrexcK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, int var8, intW var9, intW var10, double[] var11, int var12, intW var13);

   protected native void dtrrfsK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int[] var21, int var22, intW var23);

   protected native void dtrsenK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, intW var16, doubleW var17, doubleW var18, double[] var19, int var20, int var21, int[] var22, int var23, int var24, intW var25);

   protected native void dtrsnaK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, double[] var17, int var18, int var19, intW var20, double[] var21, int var22, int var23, int[] var24, int var25, intW var26);

   protected native void dtrsylK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, doubleW var15, intW var16);

   protected native void dtrti2K(String var1, String var2, int var3, double[] var4, int var5, int var6, intW var7);

   protected native void dtrtriK(String var1, String var2, int var3, double[] var4, int var5, int var6, intW var7);

   protected native void dtrtrsK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, intW var12);

   protected native void dtzrqfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, intW var8);

   protected native void dtzrzfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   protected native int ieeeckK(int var1, float var2, float var3);

   protected native int ilaenvK(int var1, String var2, String var3, int var4, int var5, int var6, int var7);

   protected native void ilaverK(intW var1, intW var2, intW var3);

   protected native int iparmqK(int var1, String var2, String var3, int var4, int var5, int var6, int var7);

   protected native boolean lsamenK(int var1, String var2, String var3);

   protected native void sbdsdcK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int[] var16, int var17, float[] var18, int var19, int[] var20, int var21, intW var22);

   protected native void sbdsqrK(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, intW var21);

   protected native void sdisnaK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   protected native void sgbbrdK(String var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, intW var25);

   protected native void sgbconK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, float var10, floatW var11, float[] var12, int var13, int[] var14, int var15, intW var16);

   protected native void sgbequK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, floatW var12, floatW var13, floatW var14, intW var15);

   protected native void sgbrfsK(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int[] var26, int var27, intW var28);

   protected native void sgbsvK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   protected native void sgbsvxK(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, StringW var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, floatW var26, float[] var27, int var28, float[] var29, int var30, float[] var31, int var32, int[] var33, int var34, intW var35);

   protected native void sgbtf2K(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   protected native void sgbtrfK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   protected native void sgbtrsK(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int var8, int[] var9, int var10, float[] var11, int var12, int var13, intW var14);

   protected native void sgebakK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void sgebalK(String var1, int var2, float[] var3, int var4, int var5, intW var6, intW var7, float[] var8, int var9, intW var10);

   protected native void sgebd2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, intW var16);

   protected native void sgebrdK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   protected native void sgeconK(String var1, int var2, float[] var3, int var4, int var5, float var6, floatW var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   protected native void sgeequK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, floatW var10, floatW var11, floatW var12, intW var13);

   protected native void sgeesK(String var1, String var2, Object var3, int var4, float[] var5, int var6, int var7, intW var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, boolean[] var19, int var20, intW var21);

   protected native void sgeesxK(String var1, String var2, Object var3, String var4, int var5, float[] var6, int var7, int var8, intW var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, floatW var17, floatW var18, float[] var19, int var20, int var21, int[] var22, int var23, int var24, boolean[] var25, int var26, intW var27);

   protected native void sgeevK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, intW var20);

   protected native void sgeevxK(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, intW var19, intW var20, float[] var21, int var22, floatW var23, float[] var24, int var25, float[] var26, int var27, float[] var28, int var29, int var30, int[] var31, int var32, intW var33);

   protected native void sgegsK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25);

   protected native void sgegvK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25);

   protected native void sgehd2K(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   protected native void sgehrdK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void sgelq2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   protected native void sgelqfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void sgelsK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, intW var14);

   protected native void sgelsdK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float var12, intW var13, float[] var14, int var15, int var16, int[] var17, int var18, intW var19);

   protected native void sgelssK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float var12, intW var13, float[] var14, int var15, int var16, intW var17);

   protected native void sgelsxK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float var12, intW var13, float[] var14, int var15, intW var16);

   protected native void sgelsyK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float var12, intW var13, float[] var14, int var15, int var16, intW var17);

   protected native void sgeql2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   protected native void sgeqlfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void sgeqp3K(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   protected native void sgeqpfK(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12);

   protected native void sgeqr2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   protected native void sgeqrfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void sgerfsK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, intW var26);

   protected native void sgerq2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   protected native void sgerqfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void sgesc2K(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int[] var7, int var8, int[] var9, int var10, floatW var11);

   protected native void sgesddK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, int[] var18, int var19, intW var20);

   protected native void sgesvK(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void sgesvdK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, intW var19);

   protected native void sgesvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, int[] var11, int var12, StringW var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, floatW var24, float[] var25, int var26, float[] var27, int var28, float[] var29, int var30, int[] var31, int var32, intW var33);

   protected native void sgetc2K(int var1, float[] var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, intW var9);

   protected native void sgetf2K(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   protected native void sgetrfK(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   protected native void sgetriK(int var1, float[] var2, int var3, int var4, int[] var5, int var6, float[] var7, int var8, int var9, intW var10);

   protected native void sgetrsK(String var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void sggbakK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, intW var14);

   protected native void sggbalK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8, intW var9, intW var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, intW var17);

   protected native void sggesK(String var1, String var2, String var3, Object var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, intW var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, float[] var25, int var26, int var27, boolean[] var28, int var29, intW var30);

   protected native void sggesxK(String var1, String var2, String var3, Object var4, String var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, intW var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, float[] var26, int var27, float[] var28, int var29, float[] var30, int var31, int var32, int[] var33, int var34, int var35, boolean[] var36, int var37, intW var38);

   protected native void sggevK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25);

   protected native void sggevxK(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, intW var24, intW var25, float[] var26, int var27, float[] var28, int var29, floatW var30, floatW var31, float[] var32, int var33, float[] var34, int var35, float[] var36, int var37, int var38, int[] var39, int var40, boolean[] var41, int var42, intW var43);

   protected native void sggglmK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, intW var19);

   protected native void sgghrdK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   protected native void sgglseK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, intW var19);

   protected native void sggqrfK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   protected native void sggrqfK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   protected native void sggsvdK(String var1, String var2, String var3, int var4, int var5, int var6, intW var7, intW var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, float[] var25, int var26, int var27, float[] var28, int var29, int[] var30, int var31, intW var32);

   protected native void sggsvpK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float var13, float var14, intW var15, intW var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, int[] var26, int var27, float[] var28, int var29, float[] var30, int var31, intW var32);

   protected native void sgtconK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int[] var11, int var12, float var13, floatW var14, float[] var15, int var16, int[] var17, int var18, intW var19);

   protected native void sgtrfsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, float[] var26, int var27, float[] var28, int var29, float[] var30, int var31, int[] var32, int var33, intW var34);

   protected native void sgtsvK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void sgtsvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25, int var26, floatW var27, float[] var28, int var29, float[] var30, int var31, float[] var32, int var33, int[] var34, int var35, intW var36);

   protected native void sgttrfK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   protected native void sgttrsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   protected native void sgtts2K(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int[] var12, int var13, float[] var14, int var15, int var16);

   protected native void shgeqzK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, float[] var25, int var26, int var27, intW var28);

   protected native void shseinK(String var1, String var2, String var3, boolean[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, int var20, intW var21, float[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   protected native void shseqrK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, intW var19);

   protected native boolean sisnanK(float var1);

   protected native void slabadK(floatW var1, floatW var2);

   protected native void slabrdK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20);

   protected native void slacn2K(int var1, float[] var2, int var3, float[] var4, int var5, int[] var6, int var7, floatW var8, intW var9, int[] var10, int var11);

   protected native void slaconK(int var1, float[] var2, int var3, float[] var4, int var5, int[] var6, int var7, floatW var8, intW var9);

   protected native void slacpyK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9);

   protected native void sladivK(float var1, float var2, float var3, float var4, floatW var5, floatW var6);

   protected native void slae2K(float var1, float var2, float var3, floatW var4, floatW var5);

   protected native void slaebzK(int var1, int var2, int var3, int var4, int var5, int var6, float var7, float var8, float var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int[] var16, int var17, float[] var18, int var19, float[] var20, int var21, intW var22, int[] var23, int var24, float[] var25, int var26, int[] var27, int var28, intW var29);

   protected native void slaed0K(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int[] var16, int var17, intW var18);

   protected native void slaed1K(int var1, float[] var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, floatW var9, int var10, float[] var11, int var12, int[] var13, int var14, intW var15);

   protected native void slaed2K(intW var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, int[] var9, int var10, floatW var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   protected native void slaed3K(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, float var9, float[] var10, int var11, float[] var12, int var13, int[] var14, int var15, int[] var16, int var17, float[] var18, int var19, float[] var20, int var21, intW var22);

   protected native void slaed4K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float var9, floatW var10, intW var11);

   protected native void slaed5K(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, floatW var9);

   protected native void slaed6K(int var1, boolean var2, float var3, float[] var4, int var5, float[] var6, int var7, float var8, floatW var9, intW var10);

   protected native void slaed7K(int var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, floatW var14, int var15, float[] var16, int var17, int[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, float[] var28, int var29, float[] var30, int var31, int[] var32, int var33, intW var34);

   protected native void slaed8K(int var1, intW var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, floatW var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int[] var23, int var24, intW var25, int[] var26, int var27, float[] var28, int var29, int[] var30, int var31, int[] var32, int var33, intW var34);

   protected native void slaed9K(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int var17, intW var18);

   protected native void slaedaK(int var1, int var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, int[] var9, int var10, int[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int[] var17, int var18, float[] var19, int var20, float[] var21, int var22, intW var23);

   protected native void slaeinK(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float var7, float var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float var18, float var19, float var20, intW var21);

   protected native void slaev2K(float var1, float var2, float var3, floatW var4, floatW var5, floatW var6, floatW var7);

   protected native void slaexcK(boolean var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8, int var9, int var10, int var11, float[] var12, int var13, intW var14);

   protected native void slag2K(float[] var1, int var2, int var3, float[] var4, int var5, int var6, float var7, floatW var8, floatW var9, floatW var10, floatW var11, floatW var12);

   protected native void slag2dK(int var1, int var2, float[] var3, int var4, int var5, double[] var6, int var7, int var8, intW var9);

   protected native void slags2K(boolean var1, float var2, float var3, float var4, float var5, float var6, float var7, floatW var8, floatW var9, floatW var10, floatW var11, floatW var12, floatW var13);

   protected native void slagtfK(int var1, float[] var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   protected native void slagtmK(String var1, int var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float var14, float[] var15, int var16, int var17);

   protected native void slagtsK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int[] var11, int var12, float[] var13, int var14, floatW var15, intW var16);

   protected native void slagv2K(float[] var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, floatW var13, floatW var14, floatW var15, floatW var16);

   protected native void slahqrK(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   protected native void slahr2K(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14);

   protected native void slahrdK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14);

   protected native void slaic1K(int var1, int var2, float[] var3, int var4, float var5, float[] var6, int var7, float var8, floatW var9, floatW var10, floatW var11);

   protected native boolean slaisnanK(float var1, float var2);

   protected native void slaln2K(boolean var1, int var2, int var3, float var4, float var5, float[] var6, int var7, int var8, float var9, float var10, float[] var11, int var12, int var13, float var14, float var15, float[] var16, int var17, int var18, floatW var19, floatW var20, intW var21);

   protected native void slals0K(int var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, int var14, int[] var15, int var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, float[] var23, int var24, float[] var25, int var26, float[] var27, int var28, int var29, float var30, float var31, float[] var32, int var33, intW var34);

   protected native void slalsaK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int[] var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int var30, int[] var31, int var32, float[] var33, int var34, float[] var35, int var36, float[] var37, int var38, float[] var39, int var40, int[] var41, int var42, intW var43);

   protected native void slalsdK(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, int[] var16, int var17, intW var18);

   protected native void slamrgK(int var1, int var2, float[] var3, int var4, int var5, int var6, int[] var7, int var8);

   protected native int slanegK(int var1, float[] var2, int var3, float[] var4, int var5, float var6, float var7, int var8);

   protected native float slangbK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9);

   protected native float slangeK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8);

   protected native float slangtK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8);

   protected native float slanhsK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7);

   protected native float slansbK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9);

   protected native float slanspK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7);

   protected native float slanstK(String var1, int var2, float[] var3, int var4, float[] var5, int var6);

   protected native float slansyK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8);

   protected native float slantbK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10);

   protected native float slantpK(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8);

   protected native float slantrK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10);

   protected native void slanv2K(floatW var1, floatW var2, floatW var3, floatW var4, floatW var5, floatW var6, floatW var7, floatW var8, floatW var9, floatW var10);

   protected native void slapllK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, floatW var8);

   protected native void slapmtK(boolean var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8);

   protected native float slapy2K(float var1, float var2);

   protected native float slapy3K(float var1, float var2, float var3);

   protected native void slaqgbK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float var12, float var13, float var14, StringW var15);

   protected native void slaqgeK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float var10, float var11, float var12, StringW var13);

   protected native void slaqp2K(int var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16);

   protected native void slaqpsK(int var1, int var2, int var3, int var4, intW var5, float[] var6, int var7, int var8, int[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21);

   protected native void slaqr0K(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, intW var21);

   protected native void slaqr1K(int var1, float[] var2, int var3, int var4, float var5, float var6, float var7, float var8, float[] var9, int var10);

   protected native void slaqr2K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, int var10, int var11, float[] var12, int var13, int var14, intW var15, intW var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int var23, int var24, float[] var25, int var26, int var27, int var28, float[] var29, int var30, int var31, float[] var32, int var33, int var34);

   protected native void slaqr3K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, int var10, int var11, float[] var12, int var13, int var14, intW var15, intW var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int var23, int var24, float[] var25, int var26, int var27, int var28, float[] var29, int var30, int var31, float[] var32, int var33, int var34);

   protected native void slaqr4K(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, intW var21);

   protected native void slaqr5K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, int var26, float[] var27, int var28, int var29, int var30, float[] var31, int var32, int var33);

   protected native void slaqsbK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float var9, float var10, StringW var11);

   protected native void slaqspK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float var7, float var8, StringW var9);

   protected native void slaqsyK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float var8, float var9, StringW var10);

   protected native void slaqtrK(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float var9, floatW var10, float[] var11, int var12, float[] var13, int var14, intW var15);

   protected native void slar1vK(int var1, int var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float var13, float var14, float[] var15, int var16, boolean var17, intW var18, floatW var19, floatW var20, intW var21, int[] var22, int var23, floatW var24, floatW var25, floatW var26, float[] var27, int var28);

   protected native void slar2vK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13);

   protected native void slarfK(String var1, int var2, int var3, float[] var4, int var5, int var6, float var7, float[] var8, int var9, int var10, float[] var11, int var12);

   protected native void slarfbK(String var1, String var2, String var3, String var4, int var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19);

   protected native void slarfgK(int var1, floatW var2, float[] var3, int var4, int var5, floatW var6);

   protected native void slarftK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   protected native void slarfxK(String var1, int var2, int var3, float[] var4, int var5, float var6, float[] var7, int var8, int var9, float[] var10, int var11);

   protected native void slargvK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10);

   protected native void slarnvK(int var1, int[] var2, int var3, int var4, float[] var5, int var6);

   protected native void slarraK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, intW var10, int[] var11, int var12, intW var13);

   protected native void slarrbK(int var1, float[] var2, int var3, float[] var4, int var5, int var6, int var7, float var8, float var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, int[] var19, int var20, float var21, float var22, int var23, intW var24);

   protected native void slarrcK(String var1, int var2, float var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, intW var10, intW var11, intW var12, intW var13);

   protected native void slarrdK(String var1, String var2, int var3, float var4, float var5, int var6, int var7, float[] var8, int var9, float var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float var17, int var18, int[] var19, int var20, intW var21, float[] var22, int var23, float[] var24, int var25, floatW var26, floatW var27, int[] var28, int var29, int[] var30, int var31, float[] var32, int var33, int[] var34, int var35, intW var36);

   protected native void slarreK(String var1, int var2, floatW var3, floatW var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float var13, float var14, float var15, intW var16, int[] var17, int var18, intW var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int[] var26, int var27, int[] var28, int var29, float[] var30, int var31, floatW var32, float[] var33, int var34, int[] var35, int var36, intW var37);

   protected native void slarrfK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float var16, float var17, float var18, float var19, floatW var20, float[] var21, int var22, float[] var23, int var24, float[] var25, int var26, intW var27);

   protected native void slarrjK(int var1, float[] var2, int var3, float[] var4, int var5, int var6, int var7, float var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int[] var16, int var17, float var18, float var19, intW var20);

   protected native void slarrkK(int var1, int var2, float var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, float var10, floatW var11, floatW var12, intW var13);

   protected native void slarrrK(int var1, float[] var2, int var3, float[] var4, int var5, intW var6);

   protected native void slarrvK(int var1, float var2, float var3, float[] var4, int var5, float[] var6, int var7, float var8, int[] var9, int var10, int var11, int var12, int var13, float var14, floatW var15, floatW var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int[] var23, int var24, int[] var25, int var26, float[] var27, int var28, float[] var29, int var30, int var31, int[] var32, int var33, float[] var34, int var35, int[] var36, int var37, intW var38);

   protected native void slartgK(float var1, float var2, floatW var3, floatW var4, floatW var5);

   protected native void slartvK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   protected native void slaruvK(int[] var1, int var2, int var3, float[] var4, int var5);

   protected native void slarzK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float var8, float[] var9, int var10, int var11, float[] var12, int var13);

   protected native void slarzbK(String var1, String var2, String var3, String var4, int var5, int var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20);

   protected native void slarztK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   protected native void slas2K(float var1, float var2, float var3, floatW var4, floatW var5);

   protected native void slasclK(String var1, int var2, int var3, float var4, float var5, int var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void slasd0K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int var13, int[] var14, int var15, float[] var16, int var17, intW var18);

   protected native void slasd1K(int var1, int var2, int var3, float[] var4, int var5, floatW var6, floatW var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, int[] var14, int var15, int[] var16, int var17, float[] var18, int var19, intW var20);

   protected native void slasd2K(int var1, int var2, int var3, intW var4, float[] var5, int var6, float[] var7, int var8, float var9, float var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, int[] var25, int var26, int[] var27, int var28, int[] var29, int var30, int[] var31, int var32, int[] var33, int var34, intW var35);

   protected native void slasd3K(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int[] var26, int var27, float[] var28, int var29, intW var30);

   protected native void slasd4K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float var9, floatW var10, float[] var11, int var12, intW var13);

   protected native void slasd5K(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, floatW var9, float[] var10, int var11);

   protected native void slasd6K(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, floatW var11, floatW var12, int[] var13, int var14, int[] var15, int var16, intW var17, int[] var18, int var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25, float[] var26, int var27, float[] var28, int var29, float[] var30, int var31, intW var32, floatW var33, floatW var34, float[] var35, int var36, int[] var37, int var38, intW var39);

   protected native void slasd7K(int var1, int var2, int var3, int var4, intW var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, float var20, float var21, float[] var22, int var23, int[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int[] var30, int var31, intW var32, int[] var33, int var34, int var35, float[] var36, int var37, int var38, floatW var39, floatW var40, intW var41);

   protected native void slasd8K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, intW var20);

   protected native void slasdaK(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int[] var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, int[] var26, int var27, int var28, int[] var29, int var30, float[] var31, int var32, float[] var33, int var34, float[] var35, int var36, float[] var37, int var38, int[] var39, int var40, intW var41);

   protected native void slasdqK(String var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, intW var22);

   protected native void slasdtK(int var1, intW var2, intW var3, int[] var4, int var5, int[] var6, int var7, int[] var8, int var9, int var10);

   protected native void slasetK(String var1, int var2, int var3, float var4, float var5, float[] var6, int var7, int var8);

   protected native void slasq1K(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   protected native void slasq2K(int var1, float[] var2, int var3, intW var4);

   protected native void slasq3K(int var1, intW var2, float[] var3, int var4, int var5, floatW var6, floatW var7, floatW var8, floatW var9, intW var10, intW var11, intW var12, boolean var13);

   protected native void slasq4K(int var1, int var2, float[] var3, int var4, int var5, int var6, float var7, float var8, float var9, float var10, float var11, float var12, floatW var13, intW var14);

   protected native void slasq5K(int var1, int var2, float[] var3, int var4, int var5, float var6, floatW var7, floatW var8, floatW var9, floatW var10, floatW var11, floatW var12, boolean var13);

   protected native void slasq6K(int var1, int var2, float[] var3, int var4, int var5, floatW var6, floatW var7, floatW var8, floatW var9, floatW var10, floatW var11);

   protected native void slasrK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   protected native void slasrtK(String var1, int var2, float[] var3, int var4, intW var5);

   protected native void slassqK(int var1, float[] var2, int var3, int var4, floatW var5, floatW var6);

   protected native void slasv2K(float var1, float var2, float var3, floatW var4, floatW var5, floatW var6, floatW var7, floatW var8, floatW var9);

   protected native void slaswpK(int var1, float[] var2, int var3, int var4, int var5, int var6, int[] var7, int var8, int var9);

   protected native void slasy2K(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, floatW var15, float[] var16, int var17, int var18, floatW var19, intW var20);

   protected native void slasyfK(String var1, int var2, int var3, intW var4, float[] var5, int var6, int var7, int[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   protected native void slatbsK(String var1, String var2, String var3, String var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, floatW var12, float[] var13, int var14, intW var15);

   protected native void slatdfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, floatW var8, floatW var9, int[] var10, int var11, int[] var12, int var13);

   protected native void slatpsK(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, int var9, floatW var10, float[] var11, int var12, intW var13);

   protected native void slatrdK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13);

   protected native void slatrsK(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, floatW var11, float[] var12, int var13, intW var14);

   protected native void slatrzK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10);

   protected native void slatzmK(String var1, int var2, int var3, float[] var4, int var5, int var6, float var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14);

   protected native void slauu2K(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   protected native void slauumK(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   protected native void slazq3K(int var1, intW var2, float[] var3, int var4, int var5, floatW var6, floatW var7, floatW var8, floatW var9, intW var10, intW var11, intW var12, boolean var13, intW var14, floatW var15, floatW var16, floatW var17, floatW var18, floatW var19, floatW var20);

   protected native void slazq4K(int var1, int var2, float[] var3, int var4, int var5, int var6, float var7, float var8, float var9, float var10, float var11, float var12, floatW var13, intW var14, floatW var15);

   protected native void sopgtrK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   protected native void sopmtrK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, intW var15);

   protected native void sorg2lK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   protected native void sorg2rK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   protected native void sorgbrK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   protected native void sorghrK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void sorgl2K(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   protected native void sorglqK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void sorgqlK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void sorgqrK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void sorgr2K(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   protected native void sorgrqK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void sorgtrK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void sorm2lK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   protected native void sorm2rK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   protected native void sormbrK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   protected native void sormhrK(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   protected native void sorml2K(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   protected native void sormlqK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   protected native void sormqlK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   protected native void sormqrK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   protected native void sormr2K(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   protected native void sormr3K(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   protected native void sormrqK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   protected native void sormrzK(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   protected native void sormtrK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   protected native void spbconK(String var1, int var2, int var3, float[] var4, int var5, int var6, float var7, floatW var8, float[] var9, int var10, int[] var11, int var12, intW var13);

   protected native void spbequK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, floatW var9, floatW var10, intW var11);

   protected native void spbrfsK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int[] var23, int var24, intW var25);

   protected native void spbstfK(String var1, int var2, int var3, float[] var4, int var5, int var6, intW var7);

   protected native void spbsvK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void spbsvxK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, StringW var12, float[] var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, floatW var21, float[] var22, int var23, float[] var24, int var25, float[] var26, int var27, int[] var28, int var29, intW var30);

   protected native void spbtf2K(String var1, int var2, int var3, float[] var4, int var5, int var6, intW var7);

   protected native void spbtrfK(String var1, int var2, int var3, float[] var4, int var5, int var6, intW var7);

   protected native void spbtrsK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void spoconK(String var1, int var2, float[] var3, int var4, int var5, float var6, floatW var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   protected native void spoequK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, floatW var7, floatW var8, intW var9);

   protected native void sporfsK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int[] var22, int var23, intW var24);

   protected native void sposvK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   protected native void sposvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, StringW var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, floatW var20, float[] var21, int var22, float[] var23, int var24, float[] var25, int var26, int[] var27, int var28, intW var29);

   protected native void spotf2K(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   protected native void spotrfK(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   protected native void spotriK(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   protected native void spotrsK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   protected native void sppconK(String var1, int var2, float[] var3, int var4, float var5, floatW var6, float[] var7, int var8, int[] var9, int var10, intW var11);

   protected native void sppequK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, floatW var7, floatW var8, intW var9);

   protected native void spprfsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int var21, intW var22);

   protected native void sppsvK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, intW var9);

   protected native void sppsvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, StringW var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, floatW var18, float[] var19, int var20, float[] var21, int var22, float[] var23, int var24, int[] var25, int var26, intW var27);

   protected native void spptrfK(String var1, int var2, float[] var3, int var4, intW var5);

   protected native void spptriK(String var1, int var2, float[] var3, int var4, intW var5);

   protected native void spptrsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, intW var9);

   protected native void sptconK(int var1, float[] var2, int var3, float[] var4, int var5, float var6, floatW var7, float[] var8, int var9, intW var10);

   protected native void spteqrK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   protected native void sptrfsK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, intW var23);

   protected native void sptsvK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, intW var10);

   protected native void sptsvxK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, floatW var18, float[] var19, int var20, float[] var21, int var22, float[] var23, int var24, intW var25);

   protected native void spttrfK(int var1, float[] var2, int var3, float[] var4, int var5, intW var6);

   protected native void spttrsK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, intW var10);

   protected native void sptts2K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9);

   protected native void srsclK(int var1, float var2, float[] var3, int var4, int var5);

   protected native void ssbevK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, intW var15);

   protected native void ssbevdK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   protected native void ssbevxK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float var13, int var14, int var15, float var16, intW var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int[] var25, int var26, int[] var27, int var28, intW var29);

   protected native void ssbgstK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   protected native void ssbgvK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, intW var19);

   protected native void ssbgvdK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, int[] var20, int var21, int var22, intW var23);

   protected native void ssbgvxK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float var16, float var17, int var18, int var19, float var20, intW var21, float[] var22, int var23, float[] var24, int var25, int var26, float[] var27, int var28, int[] var29, int var30, int[] var31, int var32, intW var33);

   protected native void ssbtrdK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   protected native void sspconK(String var1, int var2, float[] var3, int var4, int[] var5, int var6, float var7, floatW var8, float[] var9, int var10, int[] var11, int var12, intW var13);

   protected native void sspevK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, intW var13);

   protected native void sspevdK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, int[] var14, int var15, int var16, intW var17);

   protected native void sspevxK(String var1, String var2, String var3, int var4, float[] var5, int var6, float var7, float var8, int var9, int var10, float var11, intW var12, float[] var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int[] var20, int var21, int[] var22, int var23, intW var24);

   protected native void sspgstK(int var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   protected native void sspgvK(int var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   protected native void sspgvdK(int var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, int[] var17, int var18, int var19, intW var20);

   protected native void sspgvxK(int var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, int var9, float var10, float var11, int var12, int var13, float var14, intW var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int[] var23, int var24, int[] var25, int var26, intW var27);

   protected native void ssprfsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int[] var22, int var23, intW var24);

   protected native void sspsvK(String var1, int var2, int var3, float[] var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void sspsvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, floatW var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, intW var26);

   protected native void ssptrdK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   protected native void ssptrfK(String var1, int var2, float[] var3, int var4, int[] var5, int var6, intW var7);

   protected native void ssptriK(String var1, int var2, float[] var3, int var4, int[] var5, int var6, float[] var7, int var8, intW var9);

   protected native void ssptrsK(String var1, int var2, int var3, float[] var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void sstebzK(String var1, String var2, int var3, float var4, float var5, int var6, int var7, float var8, float[] var9, int var10, float[] var11, int var12, intW var13, intW var14, float[] var15, int var16, int[] var17, int var18, int[] var19, int var20, float[] var21, int var22, int[] var23, int var24, intW var25);

   protected native void sstedcK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   protected native void sstegrK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int var26, intW var27);

   protected native void ssteinK(int var1, float[] var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int[] var9, int var10, int[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int[] var18, int var19, int[] var20, int var21, intW var22);

   protected native void sstemrK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, intW var12, float[] var13, int var14, float[] var15, int var16, int var17, int var18, int[] var19, int var20, booleanW var21, float[] var22, int var23, int var24, int[] var25, int var26, int var27, intW var28);

   protected native void ssteqrK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   protected native void ssterfK(int var1, float[] var2, int var3, float[] var4, int var5, intW var6);

   protected native void sstevK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   protected native void sstevdK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   protected native void sstevrK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int var26, intW var27);

   protected native void sstevxK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int[] var21, int var22, int[] var23, int var24, intW var25);

   protected native void ssyconK(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float var8, floatW var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   protected native void ssyevK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void ssyevdK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, int var14, intW var15);

   protected native void ssyevrK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int var26, intW var27);

   protected native void ssyevxK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, int[] var22, int var23, int[] var24, int var25, intW var26);

   protected native void ssygs2K(int var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   protected native void ssygstK(int var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   protected native void ssygvK(int var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, intW var16);

   protected native void ssygvdK(int var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   protected native void ssygvxK(int var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float var13, int var14, int var15, float var16, intW var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, int[] var26, int var27, int[] var28, int var29, intW var30);

   protected native void ssyrfsK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, intW var26);

   protected native void ssysvK(String var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, intW var15);

   protected native void ssysvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, int[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, floatW var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   protected native void ssytd2K(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12);

   protected native void ssytf2K(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   protected native void ssytrdK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, intW var15);

   protected native void ssytrfK(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void ssytriK(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, intW var10);

   protected native void ssytrsK(String var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void stbconK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, floatW var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   protected native void stbrfsK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int[] var22, int var23, intW var24);

   protected native void stbtrsK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, intW var13);

   protected native void stgevcK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, int var18, intW var19, float[] var20, int var21, intW var22);

   protected native void stgex2K(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int var16, int var17, int var18, float[] var19, int var20, int var21, intW var22);

   protected native void stgexcK(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, intW var16, intW var17, float[] var18, int var19, int var20, intW var21);

   protected native void stgsenK(int var1, boolean var2, boolean var3, boolean[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25, floatW var26, floatW var27, float[] var28, int var29, float[] var30, int var31, int var32, int[] var33, int var34, int var35, intW var36);

   protected native void stgsjaK(String var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float var15, float var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25, int var26, float[] var27, int var28, int var29, float[] var30, int var31, intW var32, intW var33);

   protected native void stgsnaK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, float[] var20, int var21, int var22, intW var23, float[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   protected native void stgsy2K(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, floatW var23, floatW var24, floatW var25, int[] var26, int var27, intW var28, intW var29);

   protected native void stgsylK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, floatW var23, floatW var24, float[] var25, int var26, int var27, int[] var28, int var29, intW var30);

   protected native void stpconK(String var1, String var2, String var3, int var4, float[] var5, int var6, floatW var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   protected native void stprfsK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int var21, intW var22);

   protected native void stptriK(String var1, String var2, int var3, float[] var4, int var5, intW var6);

   protected native void stptrsK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native void strconK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, floatW var8, float[] var9, int var10, int[] var11, int var12, intW var13);

   protected native void strevcK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, int var15, intW var16, float[] var17, int var18, intW var19);

   protected native void strexcK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8, intW var9, intW var10, float[] var11, int var12, intW var13);

   protected native void strrfsK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int[] var21, int var22, intW var23);

   protected native void strsenK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, intW var16, floatW var17, floatW var18, float[] var19, int var20, int var21, int[] var22, int var23, int var24, intW var25);

   protected native void strsnaK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, float[] var17, int var18, int var19, intW var20, float[] var21, int var22, int var23, int[] var24, int var25, intW var26);

   protected native void strsylK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, floatW var15, intW var16);

   protected native void strti2K(String var1, String var2, int var3, float[] var4, int var5, int var6, intW var7);

   protected native void strtriK(String var1, String var2, int var3, float[] var4, int var5, int var6, intW var7);

   protected native void strtrsK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, intW var12);

   protected native void stzrqfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, intW var8);

   protected native void stzrzfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   protected native double dlamchK(String var1);

   protected native void dlamc1K(intW var1, intW var2, booleanW var3, booleanW var4);

   protected native void dlamc2K(intW var1, intW var2, booleanW var3, doubleW var4, intW var5, doubleW var6, intW var7, doubleW var8);

   protected native double dlamc3K(double var1, double var3);

   protected native void dlamc4K(intW var1, double var2, int var4);

   protected native void dlamc5K(int var1, int var2, int var3, boolean var4, intW var5, doubleW var6);

   protected native double dsecndK();

   protected native boolean lsameK(String var1, String var2);

   protected native float secondK();

   protected native float slamchK(String var1);

   protected native void slamc1K(intW var1, intW var2, booleanW var3, booleanW var4);

   protected native void slamc2K(intW var1, intW var2, booleanW var3, floatW var4, intW var5, floatW var6, intW var7, floatW var8);

   protected native float slamc3K(float var1, float var2);

   protected native void slamc4K(intW var1, float var2, int var3);

   protected native void slamc5K(int var1, int var2, int var3, boolean var4, intW var5, floatW var6);
}
