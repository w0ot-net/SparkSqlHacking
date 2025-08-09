package org.apache.derby.impl.tools.ij;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.derby.iapi.tools.i18n.LocalizedInput;
import org.apache.derby.iapi.tools.i18n.LocalizedOutput;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.tools.JDBCDisplayUtil;

public class Main {
   private utilMain utilInstance;

   public static void main(String[] var0) throws IOException {
      mainCore(var0, new Main(true));
   }

   public static void mainCore(String[] var0, Main var1) throws IOException {
      LocalizedInput var2 = null;
      Object var3 = null;
      LocalizedResource var8 = LocalizedResource.getInstance();
      LocalizedOutput var9 = var8.getNewOutput(System.out);
      if (util.invalidArgs(var0)) {
         util.Usage(var9);
      } else {
         boolean var7 = util.getPropertyArg(var0);
         var8.init();
         var9 = var8.getNewOutput(System.out);
         var1.initAppUI();
         String var5 = util.getFileArg(var0);
         String var6 = util.getInputResourceNameArg(var0);
         if (var6 != null) {
            var2 = var8.getNewInput(util.getResourceAsStream(var6));
            if (var2 == null) {
               var9.println(var8.getTextMessage("IJ_IjErroResoNo", var6));
               return;
            }
         } else if (var5 == null) {
            var2 = var8.getNewInput(System.in);
            var9.flush();
         } else {
            try {
               FileInputStream var17 = new FileInputStream(var5);
               if (var17 != null) {
                  BufferedInputStream var18 = new BufferedInputStream(var17, 2048);
                  var2 = var8.getNewInput(var18);
               }
            } catch (Exception var16) {
               if (Boolean.getBoolean("ij.searchClassPath")) {
                  var2 = var8.getNewInput(util.getResourceAsStream(var5));
               }

               if (var2 == null) {
                  var9.println(var8.getTextMessage("IJ_IjErroFileNo", var5));
                  return;
               }
            }
         }

         String var10 = util.getSystemProperty("ij.outfile");
         if (var10 != null && var10.length() > 0) {
            LocalizedOutput var11 = var9;
            FileOutputStream var12 = null;

            try {
               var12 = new FileOutputStream(var10);
            } catch (FileNotFoundException var15) {
            }

            var9 = var8.getNewOutput(var12);
            if (var9 == null) {
               var11.println(var8.getTextMessage("IJ_IjErroUnabTo", var10));
            }
         }

         String var20 = util.getSystemProperty("maximumDisplayWidth");
         if (var20 == null) {
            var20 = util.getSystemProperty("ij.maximumDisplayWidth");
         }

         if (var20 != null && var20.length() > 0) {
            try {
               int var21 = Integer.parseInt(var20);
               JDBCDisplayUtil.setMaxDisplayWidth(var21);
            } catch (NumberFormatException var14) {
               var9.println(var8.getTextMessage("IJ_IjErroMaxiVa", var20));
            }
         }

         Main var4 = var1.getMain(var9);
         var4.go(var2, var9);
         var2.close();
         var9.close();
      }
   }

   public Main getMain(LocalizedOutput var1) {
      return new Main(var1);
   }

   public utilMain getutilMain(int var1, LocalizedOutput var2) {
      return new utilMain(var1, var2);
   }

   public utilMain getutilMain(int var1, LocalizedOutput var2, boolean var3) {
      return new utilMain(var1, var2, var3);
   }

   private void go(LocalizedInput var1, LocalizedOutput var2) {
      LocalizedInput[] var3 = new LocalizedInput[]{var1};
      this.utilInstance.go(var3, var2);
   }

   public Main() {
      this((LocalizedOutput)null);
   }

   public Main(LocalizedOutput var1) {
      if (var1 == null) {
         var1 = LocalizedResource.getInstance().getNewOutput(System.out);
      }

      this.utilInstance = this.getutilMain(1, var1);
      this.utilInstance.initFromEnvironment();
   }

   public Main(boolean var1) {
   }

   private void initAppUI() {
      LocalizedResource.getInstance();
   }
}
