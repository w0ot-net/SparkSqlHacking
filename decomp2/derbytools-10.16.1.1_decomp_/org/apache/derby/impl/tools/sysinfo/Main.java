package org.apache.derby.impl.tools.sysinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.derby.iapi.tools.i18n.LocalizedOutput;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.shared.common.info.JVMInfo;
import org.apache.derby.shared.common.info.ProductVersionHolder;
import org.apache.derby.shared.common.reference.ModuleUtil;

public final class Main {
   private static final LocalizedResource LOCALIZED_RESOURCE = new LocalizedResource("org.apache.derby.loc.tools.sysinfoMessages");
   private static final String TEST_CLASS_NAME = "org.apache.derbyTesting.junit.XATestUtil";
   private static boolean setPause = false;
   private static boolean setLicense = false;
   private static boolean cptester = false;
   private static final String sep = "------------------------------------------------------";
   private static final String javaSep = getTextMessage("SIF01.L");
   private static final String jbmsSep = getTextMessage("SIF01.M");
   private static final String licSep = getTextMessage("SIF01.N");
   private static final String locSep = getTextMessage("SIF01.P");
   private static final String curLoc = getTextMessage("SIF01.T");
   private static final String EMBEDDED = "embedded";
   private static final String TOOLS = "tools";
   private static final String NET = "server";
   private static final String CLIENT = "client";
   private static final String MAINUSAGESTRING = "java org.apache.derby.tools.sysinfo -cp";
   private static final String USAGESTRINGPARTA = "java org.apache.derby.tools.sysinfo -cp [ [ embedded ][ server ][ client] [ tools ] [";
   private static final String USAGESTRINGPARTB = ".class ] ]";
   private static final String[] infoNames = new String[]{"org/apache/derby/info/engine/info.properties", "org/apache/derby/info/tools/info.properties", "org/apache/derby/info/net/info.properties", "org/apache/derby/info/client/info.properties", "org/apache/derby/info/shared/info.properties", "org/apache/derby/optional/info.properties"};
   private static final String[] jarNames = new String[]{"derby.jar", "derbyclient.jar", "derbynet.jar", "derbyoptionaltools.jar", "derbyrun.jar", "derbyshared.jar", "derbyTesting.jar", "derbytools.jar", "derbyLocale_cs.jar", "derbyLocale_de_DE.jar", "derbyLocale_es.jar", "derbyLocale_ja_JP.jar", "derbyLocale_ko_KR.jar", "derbyLocale_pl.jar", "derbyLocale_pt_BR.jar", "derbyLocale_ru.jar", "derbyLocale_fr.jar", "derbyLocale_zh_CN.jar", "derbyLocale_hu.jar", "derbyLocale_zh_TW.jar", "derbyLocale_it.jar"};

   public static void main(String[] var0) {
      LocalizedResource.getInstance().init();
      LocalizedOutput var1 = LocalizedResource.OutputWriter();
      parseArgs(var0);
      if (cptester) {
         getClasspathInfo(var0, var1);
      } else {
         getMainInfo(var1, setPause);
      }

   }

   public static void getMainInfo(PrintWriter var0, boolean var1) {
      var0.println(javaSep);
      reportJavaInfo(var0);
      var0.println(jbmsSep);
      reportDerby(var0);
      var0.println("------------------------------------------------------");

      try {
         reportLocales(var0);
      } catch (Exception var4) {
         var0.println(getTextMessage("SIF01.Q"));
         var0.println(getTextMessage("SIF01.B"));
      }

      try {
         reportTesting(var0);
      } catch (Exception var3) {
         var0.println("Exception in reporting version of derbyTesting.jar");
         var3.printStackTrace();
      }

      if (var1) {
         pause();
      }

   }

   private static void parseArgs(String[] var0) {
      if (var0 != null) {
         for(int var1 = 0; var1 < var0.length; ++var1) {
            if (var0[var1].equals("-pause")) {
               setPause = true;
            }

            if (var0[var1].equals("-cp")) {
               cptester = true;
            }
         }

      }
   }

   private static void pause() {
      try {
         System.out.print(getTextMessage("SIF01.C"));
         BufferedReader var0 = new BufferedReader(new InputStreamReader(System.in));
         var0.readLine();
      } catch (IOException var1) {
      }

   }

   private static void reportDerby(PrintWriter var0) {
      Object var1 = null;
      String var5;
      if (JVMInfo.isModuleAware()) {
         var5 = JVMInfo.getSystemModulePath();
      } else {
         var5 = System.getProperty("java.class.path");
      }

      ZipInfoProperties[] var2 = getAllInfo(var5);
      if (var2 != null) {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            String var10000 = var2[var3].getLocation();
            String var4 = "[" + var10000 + "] " + var2[var3].getVersionBuildInfo();
            var0.println(var4);
         }
      } else {
         var0.println(getTextMessage("SIF01.D"));
      }

   }

   private static void reportJavaInfo(PrintWriter var0) {
      var0.println(getTextMessage("SIF02.A", getJavaProperty("java.version")));
      var0.println(getTextMessage("SIF02.B", getJavaProperty("java.vendor")));
      var0.println(getTextMessage("SIF02.C", getJavaProperty("java.home")));
      var0.println(getTextMessage("SIF02.D", getJavaProperty("java.class.path")));
      var0.println(getTextMessage("SIF02.E", getJavaProperty("os.name")));
      var0.println(getTextMessage("SIF02.F", getJavaProperty("os.arch")));
      var0.println(getTextMessage("SIF02.G", getJavaProperty("os.version")));
      var0.println(getTextMessage("SIF02.H", getJavaProperty("user.name")));
      var0.println(getTextMessage("SIF02.I", getJavaProperty("user.home")));
      var0.println(getTextMessage("SIF02.J", getJavaProperty("user.dir")));
      var0.println("java.specification.name: " + getJavaProperty("java.specification.name"));
      var0.println("java.specification.version: " + getJavaProperty("java.specification.version"));
      printPropertyIfNotNull(var0, "java.runtime.version");
      printPropertyIfNotNull(var0, "java.fullversion");
   }

   private static void printPropertyIfNotNull(PrintWriter var0, String var1) {
      String var2 = getJavaProperty(var1, true);
      if (var2 != null) {
         var0.println(var1 + ": " + var2);
      }

   }

   private static String getJavaProperty(String var0) {
      return getJavaProperty(var0, false);
   }

   private static String getJavaProperty(String var0, boolean var1) {
      String var2 = var1 ? null : getTextMessage("SIF01.H");
      String var3 = System.getProperty(var0, var2);
      return var3;
   }

   private static String getCanonicalPath(File var0) throws IOException {
      return var0.getCanonicalPath();
   }

   private static void getClasspathInfo(String[] var0, PrintWriter var1) {
      useMe(var0, var1);
   }

   private static void reportLocales(PrintWriter var0) {
      boolean var1 = true;
      var0.println(locSep);
      Locale[] var2 = Locale.getAvailableLocales();
      Arrays.sort(var2, new LocaleSorter());
      Properties var3 = new Properties();

      for(int var4 = 0; var4 < var2.length; ++var4) {
         Locale var5 = var2[var4];
         String var6 = var5.toString();
         String var7 = "/org/apache/derby/info/locale_" + var6 + "/info.properties";

         try {
            Class var9 = Main.class;
            InputStream var10 = null;
            if (JVMInfo.isModuleAware()) {
               String var11 = ModuleUtil.localizationModuleName(var5.toString());
               Module var12 = ModuleUtil.derbyModule(var11);
               if (var12 != null) {
                  var10 = var12.getResourceAsStream(var7);
               }
            } else {
               var10 = var9.getResourceAsStream(var7);
            }

            InputStream var20 = var10;
            if (var10 != null) {
               try {
                  var3.clear();
                  var3.load(var20);
                  if (var1) {
                     Object var21 = null;
                     Locale var22 = Locale.getDefault();
                     String var10001 = getTextMessage("SIF01.T");
                     var0.println(var10001 + "  [" + var22.getDisplayLanguage() + "/" + var22.getDisplayCountry() + " [" + var22 + "]]");
                     var1 = false;
                  }

                  String var23 = var3.getProperty("derby.locale.external.name");
                  var23 = var23.substring(var23.indexOf("[") + 1);
                  var23 = var23.substring(0, var23.indexOf("]"));
                  var0.println(getTextMessage("SIF01.R", var23));
                  int var13 = Integer.parseInt(var3.getProperty("derby.locale.version.major"));
                  int var14 = Integer.parseInt(var3.getProperty("derby.locale.version.minor"));
                  int var15 = Integer.parseInt(var3.getProperty("derby.locale.version.maint"));
                  String var16 = var3.getProperty("derby.locale.build.number");
                  String var17 = ProductVersionHolder.fullVersionString(var13, var14, var15, false, var16);
                  var0.println(getTextMessage("SIF01.S", var17));
               } catch (IOException var18) {
                  var0.println("Could not get locale properties from : " + var10);
               }
            }
         } catch (Throwable var19) {
            var0.println("Could not load resource: " + var7);
            var0.println("Exception: " + var19);
         }
      }

      var0.println("------------------------------------------------------");
   }

   private static void reportTesting(PrintWriter var0) {
      String var1 = "org.apache.derbyTesting.*:";
      Properties var2 = new Properties();
      String var3 = "/org/apache/derby/info/tsting/info.properties";
      String var5 = var3;

      try {
         Class var6 = Main.class;
         InputStream var7 = null;
         if (JVMInfo.isModuleAware()) {
            String var8 = "org.apache.derby.tests";
            Module var9 = ModuleUtil.derbyModule(var8);
            if (var9 != null) {
               var7 = var9.getResourceAsStream(var5);
            }
         } else {
            var7 = var6.getResourceAsStream(var5);
         }

         if (var7 != null) {
            try {
               var2.clear();
               var2.load(var7);
               URL var17 = null;
               if (JVMInfo.isModuleAware()) {
                  try {
                     Module var18 = ModuleUtil.derbyModule("org.apache.derby.tests");
                     Class var10 = var18.getClassLoader().loadClass("org.apache.derbyTesting.junit.XATestUtil");
                     var17 = var10.getProtectionDomain().getCodeSource().getLocation();
                  } catch (Exception var14) {
                  }
               } else {
                  StringBuffer var19 = new StringBuffer(getTextMessage(crLf()));
                  String var10002 = crLf();
                  StringBuffer var21 = new StringBuffer(var10002 + getTextMessage("SIF08.E") + crLf());
                  tryTstingClasspath(var19, var21);
                  String var11 = var19.toString();
                  if (var11.isEmpty() || var11.length() <= 2) {
                     return;
                  }

                  var17 = new URL(var11);
               }

               if (var17 == null) {
                  return;
               }

               var0.println(var1);
               var0.print("\t ");
               var0.print("[");
               var0.print(formatURL(var17));
               var0.println("]");
               int var20 = Integer.parseInt(var2.getProperty("derby.tsting.version.major"));
               int var22 = Integer.parseInt(var2.getProperty("derby.tsting.version.minor"));
               int var23 = Integer.parseInt(var2.getProperty("derby.tsting.version.maint"));
               String var12 = var2.getProperty("derby.tsting.build.number");
               String var13 = ProductVersionHolder.fullVersionString(var20, var22, var23, false, var12);
               var0.println(getTextMessage("SIF01.S", var13));
            } catch (IOException var15) {
               var0.println("Could not get testing properties from : " + var7);
            }
         }

         var0.println("------------------------------------------------------");
      } catch (Throwable var16) {
         var0.println("Could not load resource: " + var3);
         var0.println("Exception: " + var16);
      }

   }

   static void useMe(String[] var0, PrintWriter var1) {
      PrintWriter var2 = var1;
      if (var1 == null) {
         var2 = new PrintWriter(System.out);
      }

      int var3 = var0.length;
      if (var3 == 1) {
         try {
            tryAllClasspaths(var2);
         } catch (Throwable var6) {
         }
      } else {
         try {
            trySomeClasspaths(var0, var2);
         } catch (Throwable var5) {
         }
      }

   }

   private static void tryAllClasspaths(PrintWriter var0) throws Throwable {
      var0.println(getTextMessage("SIF08.B"));
      var0.println(getTextMessage("SIF08.C", "java org.apache.derby.tools.sysinfo -cp args"));
      String var10002 = getTextMessage("SIF08.D");
      StringBuffer var1 = new StringBuffer(var10002 + crLf());
      var10002 = crLf();
      StringBuffer var2 = new StringBuffer(var10002 + getTextMessage("SIF08.E") + crLf());
      tryCoreClasspath(var1, var2);
      tryNetClasspath(var1, var2);
      tryClientClasspath(var1, var2);
      tryUtilsClasspath(var1, var2);
      var0.println(var1.toString());
      String var10000 = var2.toString();
      String var10001 = crLf();
      if (!var10000.equals(var10001 + getTextMessage("SIF08.E") + crLf())) {
         var0.println(var2.toString());
      } else {
         var0.println(getTextMessage("SIF08.F"));
      }

      var0.flush();
   }

   private static void trySomeClasspaths(String[] var0, PrintWriter var1) throws Throwable {
      boolean var2 = false;
      String var10002 = getTextMessage("SIF08.D");
      StringBuffer var3 = new StringBuffer(var10002 + crLf());
      var10002 = crLf();
      StringBuffer var4 = new StringBuffer(var10002 + getTextMessage("SIF08.E") + crLf());
      if (argumentsContain(var0, "embedded")) {
         tryCoreClasspath(var3, var4);
         var2 = true;
      }

      if (argumentsContain(var0, "server")) {
         tryNetClasspath(var3, var4);
         var2 = true;
      }

      if (argumentsContain(var0, "client")) {
         tryClientClasspath(var3, var4);
         var2 = true;
      }

      if (argumentsContain(var0, "tools") || argumentsContain(var0, "utils")) {
         tryUtilsClasspath(var3, var4);
         var2 = true;
      }

      String var5 = argumentMatches(var0, ".class");
      if (!var5.equals("")) {
         tryMyClasspath(argumentMatches(var0, ".class"), getTextMessage("SIF08.H", var5), var3, var4);
         var2 = true;
      }

      if (var2) {
         var1.println(var3.toString());
         String var10000 = var4.toString();
         String var10001 = crLf();
         if (!var10000.equals(var10001 + getTextMessage("SIF08.E") + crLf())) {
            var1.println(var4.toString());
         } else {
            var1.println(getTextMessage("SIF08.F"));
         }
      } else {
         var1.println(getTextMessage("SIF08.A", "java org.apache.derby.tools.sysinfo -cp [ [ embedded ][ server ][ client] [ tools ] [", ".class ] ]"));
      }

      var1.flush();
   }

   private static void tryCoreClasspath(StringBuffer var0, StringBuffer var1) {
      tryMyClasspath("org.apache.derby.database.Database", getTextMessage("SIF08.J", "derby.jar"), var0, var1);
   }

   private static void tryNetClasspath(StringBuffer var0, StringBuffer var1) {
      tryMyClasspath("org.apache.derby.database.Database", getTextMessage("SIF08.J", "derby.jar"), var0, var1);
      tryMyClasspath("org.apache.derby.drda.NetworkServerControl", getTextMessage("SIF08.I", "derbynet.jar"), var0, var1);
   }

   private static void tryClientClasspath(StringBuffer var0, StringBuffer var1) {
      tryMyClasspath("org.apache.derby.jdbc.ClientDriver", getTextMessage("SIF08.L", "derbyclient.jar"), var0, var1);
   }

   private static void tryUtilsClasspath(StringBuffer var0, StringBuffer var1) {
      tryMyClasspath("org.apache.derby.tools.ij", getTextMessage("SIF08.Q", "derbytools.jar"), var0, var1);
   }

   private static void tryTstingClasspath(StringBuffer var0, StringBuffer var1) {
      tryMyClasspath("org.apache.derbyTesting.junit.XATestUtil", "", var0, var1);
   }

   private static void tryMyClasspath(String var0, String var1, StringBuffer var2, StringBuffer var3) {
      try {
         Class var4 = Class.forName(var0);
         String var5 = getFileWhichLoadedClass(var4);
         var2.append(found(var0, var1, var5));
      } catch (Throwable var6) {
         var3.append(notFound(var0, var1));
      }

   }

   private static void tryAsResource(String var0, String var1, StringBuffer var2, StringBuffer var3) {
      try {
         InputStream var4 = var0.getClass().getResourceAsStream(var0);
         var4.close();
         String var5 = getFileWhichLoadedClass(var0.getClass());
         var2.append(found(var0, var1, var5));
      } catch (Throwable var6) {
         var3.append(notFound(var0, var1));
      }

   }

   private static String found(String var0, String var1, String var2) {
      StringBuffer var3 = new StringBuffer(crLf());
      var3.append("   " + var1);
      var3.append(crLf());
      if (var2 != null) {
         var3.append("   ").append(var2).append(crLf());
      }

      var3.append(crLf());
      return var3.toString();
   }

   private static String notFound(String var0, String var1) {
      StringBuffer var2 = new StringBuffer(crLf());
      var2.append("   " + var1);
      var2.append(crLf());
      Object[] var10002 = new Object[]{var0};
      var2.append("    " + getTextMessage("SIF08.U", var10002));
      var2.append(crLf());
      var2.append(crLf());
      return var2.toString();
   }

   private static String crLf() {
      return System.getProperty("line.separator");
   }

   private static String lookForMainArg(String[] var0, PrintWriter var1) {
      int var2 = var0.length;
      String[] var3 = new String[]{"embedded"};
      int var4 = 0;
      String var5 = "";

      for(int var6 = 0; var6 < var2; ++var6) {
         for(int var7 = 0; var7 < var3.length; ++var7) {
            if (var0[var6].toUpperCase(Locale.ENGLISH).equals(var3[var7].toUpperCase(Locale.ENGLISH))) {
               ++var4;
               var5 = var3[var7];
            }
         }
      }

      if (var4 <= 1 && var4 >= 1) {
         return var5;
      } else {
         var1.println(getTextMessage("SIF08.A", "java org.apache.derby.tools.sysinfo -cp [ [ embedded ][ server ][ client] [ tools ] [", ".class ] ]"));
         return "";
      }
   }

   private static boolean argumentsContain(String[] var0, String var1) {
      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var0[var2].equalsIgnoreCase(var1)) {
            return true;
         }
      }

      return false;
   }

   private static String argumentMatches(String[] var0, String var1) {
      String var2 = "";
      int var3 = var0.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         if (var0[var4].endsWith(var1)) {
            var2 = var0[var4].substring(0, var0[var4].length() - 6);
         }
      }

      return var2;
   }

   public static ZipInfoProperties[] getAllInfo(String var0) {
      ZipInfoProperties[] var1 = loadZipFromResource();
      if (var1 == null) {
         var1 = new ZipInfoProperties[1];
         ZipInfoProperties var2 = new ZipInfoProperties(ProductVersionHolder.getProductVersionHolderFromMyEnv("tools"));
         var2.setLocation(getFileWhichLoadedClass((new Main()).getClass()));
         var1[0] = var2;
      }

      if (var0 != null) {
         String[] var12 = parseClasspath(var0);
         List var3 = Arrays.asList(jarNames);
         Vector var4 = new Vector();

         for(int var5 = 0; var5 < var12.length; ++var5) {
            boolean var6 = false;
            String var7 = var12[var5];

            for(String var11 : jarNames) {
               if (var7.endsWith(var11)) {
                  var6 = true;
                  break;
               }
            }

            if (var6) {
               ZipInfoProperties var14 = checkForInfo(var12[var5]);
               if (var14 != null) {
                  var4.addElement(var14);
               }
            }
         }

         if (var4.size() > 0) {
            ZipInfoProperties[] var13 = new ZipInfoProperties[var4.size()];
            var4.copyInto(var13);
            return mergeZips(var1, var13);
         }
      }

      return mergeZips(var1, (ZipInfoProperties[])null);
   }

   private static ZipInfoProperties[] loadZipFromResource() {
      ArrayList var0 = new ArrayList();

      for(int var1 = 0; var1 < infoNames.length; ++var1) {
         String var2 = "/".concat(infoNames[var1]);
         InputStream var3 = (new Main()).getClass().getResourceAsStream(var2);
         if (var3 != null) {
            ZipInfoProperties var4 = new ZipInfoProperties(ProductVersionHolder.getProductVersionHolderFromMyEnv(var3));
            URL var5 = (new Main()).getClass().getResource(var2);
            var4.setLocation(formatURL(var5));
            var0.add(var4);
         }
      }

      if (var0.size() == 0) {
         return null;
      } else {
         ZipInfoProperties[] var6 = new ZipInfoProperties[var0.size()];
         var0.toArray(var6);
         return var6;
      }
   }

   private static String[] parseClasspath(String var0) {
      StringTokenizer var1 = new StringTokenizer(var0, File.pathSeparator);
      int var2 = var1.countTokens();
      if (var2 == 0) {
         return null;
      } else {
         String[] var3 = new String[var2];

         for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = var1.nextToken();
         }

         return var3;
      }
   }

   private static ZipInfoProperties checkForInfo(String var0) {
      File var1 = new File(var0);
      if (!var1.exists()) {
         return null;
      } else if (var1.isDirectory()) {
         ZipInfoProperties var3 = checkDirectory(var0);
         return var3;
      } else if (var1.isFile()) {
         ZipInfoProperties var2 = checkFile(var0);
         return var2;
      } else {
         return null;
      }
   }

   private static ZipInfoProperties checkDirectory(String var0) {
      boolean var1 = false;
      File var2 = null;

      for(int var3 = 0; var3 < infoNames.length; ++var3) {
         String var4 = infoNames[var3].replace('/', File.separatorChar);
         var2 = new File(var0, var4);
         if (var2.exists()) {
            var1 = true;
            break;
         }
      }

      if (var1 && var2 != null) {
         try {
            FileInputStream var6 = new FileInputStream(var2);
            ZipInfoProperties var7 = new ZipInfoProperties(ProductVersionHolder.getProductVersionHolderFromMyEnv(var6));
            var7.setLocation(getCanonicalPath(new File(var0)).replace('/', File.separatorChar));
            return var7;
         } catch (IOException var5) {
            return null;
         }
      } else {
         return null;
      }
   }

   private static ZipInfoProperties checkFile(String var0) {
      try {
         ZipFile var1 = new ZipFile(var0);
         ZipEntry var2 = null;

         for(int var3 = 0; var3 < infoNames.length; ++var3) {
            var2 = var1.getEntry(infoNames[var3]);
            if (var2 != null) {
               break;
            }
         }

         if (var2 == null) {
            return null;
         } else {
            InputStream var6 = var1.getInputStream(var2);
            if (var6 == null) {
               return null;
            } else {
               ZipInfoProperties var4 = new ZipInfoProperties(ProductVersionHolder.getProductVersionHolderFromMyEnv(var6));
               var4.setLocation(getCanonicalPath(new File(var0)).replace('/', File.separatorChar));
               return var4;
            }
         }
      } catch (IOException var5) {
         return null;
      }
   }

   public static String getTextMessage(String var0, Object... var1) {
      return LOCALIZED_RESOURCE.getTextMessage(var0, var1);
   }

   private static String getFileWhichLoadedClass(Class var0) {
      Object var1 = null;
      CodeSource var5 = var0.getProtectionDomain().getCodeSource();
      if (var5 == null) {
         return null;
      } else {
         URL var2 = var5.getLocation();

         try {
            return URLDecoder.decode(var2.toString(), "UTF-8");
         } catch (UnsupportedEncodingException var4) {
            return var4.getMessage();
         }
      }
   }

   private static ZipInfoProperties[] mergeZips(ZipInfoProperties[] var0, ZipInfoProperties[] var1) {
      Vector var2 = new Vector();
      boolean var3 = false;

      for(int var4 = 0; var4 < var0.length; ++var4) {
         if (var0[var4] != null && var0.length > 1) {
            for(int var5 = var4 + 1; var5 < var0.length; ++var5) {
               if (var0[var4].getLocation().equals(var0[var5].getLocation())) {
                  var0[var5] = null;
               }
            }
         }

         if (var0[var4] != null) {
            var2.addElement(var0[var4]);
         }
      }

      if (var1 != null) {
         for(int var7 = 0; var7 < var1.length; ++var7) {
            for(int var9 = 0; var9 < var2.size(); ++var9) {
               ZipInfoProperties var6 = (ZipInfoProperties)var2.get(var9);
               if (var1[var7].getLocation().equals(var6.getLocation())) {
                  var3 = true;
               }
            }

            if (!var3) {
               var2.addElement(var1[var7]);
            }

            var3 = false;
         }
      }

      ZipInfoProperties[] var8 = new ZipInfoProperties[var2.size()];
      var2.copyInto(var8);
      return var8;
   }

   private static String formatURL(URL var0) {
      String var1;
      try {
         var1 = URLDecoder.decode(var0.toString(), "UTF-8");
      } catch (UnsupportedEncodingException var5) {
         return null;
      }

      if (var1.startsWith("jar:")) {
         var1 = var1.substring(4);
      }

      if (var1.startsWith("file:")) {
         var1 = var1.substring(5);
      }

      if (var1.indexOf("!") > -1) {
         var1 = var1.substring(0, var1.indexOf("!"));
      }

      if (var1.indexOf("/org/apache/derby") > -1) {
         var1 = var1.substring(0, var1.indexOf("/org/apache/derby"));
      }

      if (var1.charAt(0) == '/' && Character.isLetter(var1.charAt(1)) && var1.charAt(2) == ':' && var1.charAt(2) == '/') {
         var1 = var1.substring(1);
      }

      String var2 = "";

      try {
         var2 = getCanonicalPath(new File(var1)).replace('/', File.separatorChar);
      } catch (IOException var4) {
         var2 = var4.getMessage();
      }

      return var2;
   }

   public static class LocaleSorter implements Comparator {
      public int compare(Locale var1, Locale var2) {
         return var1.toString().compareTo(var2.toString());
      }
   }
}
