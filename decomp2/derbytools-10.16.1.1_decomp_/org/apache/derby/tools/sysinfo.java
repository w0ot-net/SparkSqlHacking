package org.apache.derby.tools;

import java.io.PrintWriter;
import org.apache.derby.impl.tools.sysinfo.Main;
import org.apache.derby.shared.common.info.ProductVersionHolder;

public class sysinfo {
   public static final String DBMS = "engine";
   public static final String TOOLS = "tools";
   public static final String NET = "net";
   public static final String CLIENT = "client";
   public static final String OPTIONALTOOLS = "optionaltools";

   public static void main(String[] var0) {
      Main.main(var0);
   }

   private sysinfo() {
   }

   public static int getMajorVersion() {
      return getMajorVersion("engine");
   }

   public static int getMajorVersion(String var0) {
      ProductVersionHolder var1 = ProductVersionHolder.getProductVersionHolderFromMyEnv(var0);
      return var1 == null ? -1 : var1.getMajorVersion();
   }

   public static int getMinorVersion() {
      return getMinorVersion("engine");
   }

   public static int getMinorVersion(String var0) {
      ProductVersionHolder var1 = ProductVersionHolder.getProductVersionHolderFromMyEnv(var0);
      return var1 == null ? -1 : var1.getMinorVersion();
   }

   public static String getBuildNumber() {
      return getBuildNumber("DBMS");
   }

   public static String getBuildNumber(String var0) {
      ProductVersionHolder var1 = ProductVersionHolder.getProductVersionHolderFromMyEnv(var0);
      return var1 == null ? "????" : var1.getBuildNumber();
   }

   public static String getProductName() {
      return getProductName("DBMS");
   }

   public static String getProductName(String var0) {
      ProductVersionHolder var1 = ProductVersionHolder.getProductVersionHolderFromMyEnv(var0);
      return var1 == null ? Main.getTextMessage("SIF01.K") : var1.getProductName();
   }

   public static String getVersionString() {
      return getVersionString("engine");
   }

   public static String getVersionString(String var0) {
      ProductVersionHolder var1 = ProductVersionHolder.getProductVersionHolderFromMyEnv(var0);
      return var1 == null ? Main.getTextMessage("SIF01.K") : var1.getVersionBuildString(false);
   }

   public static void getInfo(PrintWriter var0) {
      Main.getMainInfo(var0, false);
   }
}
