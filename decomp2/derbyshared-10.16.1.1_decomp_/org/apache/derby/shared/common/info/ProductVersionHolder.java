package org.apache.derby.shared.common.info;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.reference.ModuleUtil;

public final class ProductVersionHolder {
   private static final int BAD_NUMBER = -1;
   private static final String ALPHA = "alpha";
   private static final String BETA = "beta";
   public static final int MAINT_ENCODING = 1000000;
   private String productVendorName;
   private String productName;
   private String productTechnologyName;
   private int majorVersion = -1;
   private int minorVersion = -1;
   private int maintVersion = -1;
   private int drdaMaintVersion = -1;
   private String buildNumber = "????";
   private Boolean isBeta;
   private String productGenus;

   private ProductVersionHolder() {
   }

   private ProductVersionHolder(String var1, String var2, String var3, int var4, int var5, int var6, int var7, String var8, Boolean var9) {
      if (var1 != null) {
         this.productVendorName = var1.trim();
      }

      if (var2 != null) {
         this.productName = var2.trim();
      }

      if (var3 != null) {
         this.productTechnologyName = var3.trim();
      }

      this.majorVersion = var4;
      this.minorVersion = var5;
      this.maintVersion = var6;
      this.drdaMaintVersion = var7;
      this.buildNumber = var8;
      this.isBeta = var9;
   }

   public static ProductVersionHolder getProductVersionHolder(String var0, String var1, String var2, int var3, int var4, int var5, int var6, String var7, Boolean var8) {
      ProductVersionHolder var9 = new ProductVersionHolder(var0, var1, var2, var3, var4, var5, var6, var7, var8);
      return var9;
   }

   public static ProductVersionHolder getProductVersionHolderFromMyEnv(String var0) {
      ProductVersionHolder var1 = new ProductVersionHolder();
      var1.productGenus = var0;
      Properties var2 = var1.run();
      return var2 == null ? null : getProductVersionHolder(var2);
   }

   public static ProductVersionHolder getProductVersionHolderFromMyEnv(InputStream var0) {
      if (var0 == null) {
         return null;
      } else {
         Properties var1 = new Properties();

         Object var3;
         try {
            var1.load(var0);
            return getProductVersionHolder(var1);
         } catch (IOException var13) {
            System.out.println("IOE " + var13.getMessage());
            var3 = null;
         } finally {
            try {
               var0.close();
            } catch (IOException var12) {
            }

         }

         return (ProductVersionHolder)var3;
      }
   }

   public static ProductVersionHolder getProductVersionHolder(Properties var0) {
      String var1 = var0.getProperty("derby.product.vendor");
      String var2 = var0.getProperty("derby.product.external.name");
      String var3 = var0.getProperty("derby.product.technology.name");
      int var4 = parseInt(var0.getProperty("derby.version.major"));
      int var5 = parseInt(var0.getProperty("derby.version.minor"));
      int var6 = parseInt(var0.getProperty("derby.version.maint"));
      int var7 = parseInt(var0.getProperty("derby.version.drdamaint"));
      String var8 = var0.getProperty("derby.build.number");
      Boolean var9 = Boolean.valueOf(var0.getProperty("derby.version.beta"));
      return getProductVersionHolder(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public String getProductVendorName() {
      return this.productVendorName;
   }

   public String getProductName() {
      return this.productName;
   }

   public String getProductTechnologyName() {
      return this.productTechnologyName;
   }

   public int getMajorVersion() {
      return this.majorVersion;
   }

   public int getMinorVersion() {
      return this.minorVersion;
   }

   public int getMaintVersion() {
      return this.maintVersion;
   }

   public int getDrdaMaintVersion() {
      return this.drdaMaintVersion;
   }

   public int getFixPackVersion() {
      return this.maintVersion / 1000000;
   }

   public boolean isBeta() {
      return this.isBeta;
   }

   public boolean isAlpha() {
      return this.majorVersion >= 5 && this.minorVersion > 2 && this.maintVersion / 1000000 == 0;
   }

   public String getBuildNumber() {
      return this.buildNumber;
   }

   public int getBuildNumberAsInt() {
      if (this.buildNumber == null) {
         return -1;
      } else {
         boolean var1 = false;
         int var2 = this.buildNumber.indexOf(77);
         if (var2 == -1) {
            var2 = this.buildNumber.indexOf(58);
         } else {
            var1 = true;
         }

         if (var2 == -1) {
            var2 = this.buildNumber.length();
         } else {
            var1 = true;
         }

         try {
            int var3 = Integer.parseInt(this.buildNumber.substring(0, var2));
            if (var1) {
               var3 = -var3;
            }

            return var3;
         } catch (NumberFormatException var4) {
            return -1;
         }
      }
   }

   private static int parseInt(String var0) {
      int var1 = -1;

      try {
         if (var0 != null) {
            var1 = Integer.parseInt(var0);
         }
      } catch (NumberFormatException var3) {
      }

      if (var1 < 0) {
         var1 = -1;
      }

      return var1;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      var1.append(this.getProductVendorName());
      var1.append(" - ");
      var1.append(this.getProductName());
      var1.append(" - ");
      var1.append(this.getVersionBuildString(true));
      return var1.toString();
   }

   public String getSimpleVersionString() {
      return simpleVersionString(this.majorVersion, this.minorVersion, this.isBeta());
   }

   public static String simpleVersionString(int var0, int var1, boolean var2) {
      StringBuffer var3 = new StringBuffer();
      var3.append(var0);
      var3.append('.');
      var3.append(var1);
      if (var2) {
         var3.append(' ');
         var3.append("beta");
      }

      return var3.toString();
   }

   public static String fullVersionString(int var0, int var1, int var2, boolean var3, String var4) {
      StringBuffer var5 = new StringBuffer();
      var5.append(var0);
      var5.append('.');
      var5.append(var1);
      var5.append('.');
      String var6 = null;
      if (var0 == 5 && var1 <= 2 && var2 < 1000000) {
         var5.append(var2);
         if (var3) {
            var6 = "beta";
         }
      } else {
         int var7 = var2 / 1000000;
         int var8 = var2 % 1000000;
         var5.append(var7);
         var5.append('.');
         var5.append(var8);
         if (var7 == 0) {
            var6 = "alpha";
         } else if (var3) {
            var6 = "beta";
         }
      }

      if (var6 != null) {
         var5.append(' ');
         var5.append(var6);
      }

      if (var4 != null) {
         var5.append(" - (");
         var5.append(var4);
         var5.append(')');
      }

      return var5.toString();
   }

   public String getVersionBuildString(boolean var1) {
      return fullVersionString(this.majorVersion, this.minorVersion, this.maintVersion, this.isBeta(), var1 ? this.buildNumber : null);
   }

   public final Properties run() {
      return this.loadProperties(this.productGenus);
   }

   private Properties loadProperties(String var1) {
      String var2 = "/org/apache/derby/info/" + var1 + "/info.properties";
      InputStream var3 = null;
      if (JVMInfo.isModuleAware()) {
         try {
            var3 = ModuleUtil.getResourceAsStream(var2);
         } catch (StandardException var7) {
            System.out.println(var7.getMessage());
         }
      } else {
         var3 = this.getClass().getResourceAsStream(var2);
      }

      if (var3 == null) {
         return null;
      } else {
         Properties var4 = new Properties();

         try {
            var4.load(var3);
            return var4;
         } catch (IOException var6) {
            return null;
         }
      }
   }
}
