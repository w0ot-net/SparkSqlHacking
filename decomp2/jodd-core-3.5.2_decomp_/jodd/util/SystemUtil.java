package jodd.util;

import java.io.File;
import java.util.ArrayList;

public class SystemUtil {
   public static final String USER_DIR = "user.dir";
   public static final String USER_NAME = "user.name";
   public static final String USER_HOME = "user.home";
   public static final String JAVA_HOME = "java.home";
   public static final String TEMP_DIR = "java.io.tmpdir";
   public static final String OS_NAME = "os.name";
   public static final String OS_VERSION = "os.version";
   public static final String JAVA_VERSION = "java.version";
   public static final String JAVA_SPECIFICATION_VERSION = "java.specification.version";
   public static final String JAVA_VENDOR = "java.vendor";
   public static final String JAVA_CLASSPATH = "java.class.path";
   public static final String PATH_SEPARATOR = "path.separator";
   public static final String HTTP_PROXY_HOST = "http.proxyHost";
   public static final String HTTP_PROXY_PORT = "http.proxyPort";
   public static final String HTTP_PROXY_USER = "http.proxyUser";
   public static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
   public static final String FILE_ENCODING = "file.encoding";
   public static final String SUN_BOOT_CLASS_PATH = "sun.boot.class.path";
   private static int javaVersionNumber;
   private static String[] jrePackages;

   public static String[] getJrePackages() {
      if (jrePackages == null) {
         buildJrePackages();
      }

      return jrePackages;
   }

   private static void buildJrePackages() {
      ArrayList<String> packages = new ArrayList();
      switch (javaVersionNumber) {
         case 15:
         case 16:
         case 17:
         case 18:
            packages.add("com.sun.org.apache");
         case 14:
            if (javaVersionNumber == 14) {
               packages.add("org.apache.crimson");
               packages.add("org.apache.xalan");
               packages.add("org.apache.xml");
               packages.add("org.apache.xpath");
            }

            packages.add("org.ietf.jgss");
            packages.add("org.w3c.dom");
            packages.add("org.xml.sax");
         case 13:
            packages.add("org.omg");
            packages.add("com.sun.corba");
            packages.add("com.sun.jndi");
            packages.add("com.sun.media");
            packages.add("com.sun.naming");
            packages.add("com.sun.org.omg");
            packages.add("com.sun.rmi");
            packages.add("sunw.io");
            packages.add("sunw.util");
         case 12:
            packages.add("com.sun.java");
            packages.add("com.sun.image");
         case 11:
         default:
            packages.add("sun");
            packages.add("java");
            packages.add("javax");
            jrePackages = (String[])packages.toArray(new String[packages.size()]);
      }
   }

   public static String getUserDir() {
      return System.getProperty("user.dir");
   }

   public static String getUserName() {
      return System.getProperty("user.name");
   }

   public static String getUserHome() {
      return System.getProperty("user.home");
   }

   public static String getWorkingFolder() {
      return System.getProperty("user.dir");
   }

   public static String getJavaJreHome() {
      return System.getProperty("java.home");
   }

   public static String getJavaHome() {
      String home = System.getProperty("java.home");
      if (home == null) {
         return null;
      } else {
         int i = home.lastIndexOf(92);
         int j = home.lastIndexOf(47);
         if (j > i) {
            i = j;
         }

         return home.substring(0, i);
      }
   }

   public static String getTempDir() {
      return System.getProperty("java.io.tmpdir");
   }

   public static String getOsName() {
      return System.getProperty("os.name");
   }

   public static String getOsVersion() {
      return System.getProperty("os.version");
   }

   public static String getJavaVersion() {
      return System.getProperty("java.version");
   }

   public static String getJavaSpecificationVersion() {
      return System.getProperty("java.specification.version");
   }

   public static int getJavaVersionNumber() {
      return javaVersionNumber;
   }

   public static String getJavaVendor() {
      return System.getProperty("java.vendor");
   }

   public static boolean isAtLeastJavaVersion(int version) {
      return javaVersionNumber >= version;
   }

   public static boolean isJavaVersion(int version) {
      return javaVersionNumber == version;
   }

   public static String getClassPath() {
      return System.getProperty("java.class.path");
   }

   public static String getPathSeparator() {
      return System.getProperty("path.separator");
   }

   public static String getFileEncoding() {
      return System.getProperty("file.encoding");
   }

   public static boolean isHostWindows() {
      return getOsName().toUpperCase().startsWith("WINDOWS");
   }

   public static boolean isHostLinux() {
      return getOsName().toUpperCase().startsWith("LINUX");
   }

   public static boolean isHostUnix() {
      return File.pathSeparator.equals(":");
   }

   public static boolean isHostMac() {
      return getOsName().toUpperCase().startsWith("MAC OS X");
   }

   public static boolean isHostSolaris() {
      return getOsName().toUpperCase().startsWith("SUNOS");
   }

   public static boolean isHostAix() {
      return getOsName().toUpperCase().equals("AIX");
   }

   public static String getSunBoothClassPath() {
      return System.getProperty("sun.boot.class.path");
   }

   public static void setHttpProxy(String host, String port, String username, String password) {
      System.getProperties().put("http.proxyHost", host);
      System.getProperties().put("http.proxyPort", port);
      System.getProperties().put("http.proxyUser", username);
      System.getProperties().put("http.proxyPassword", password);
   }

   public static void setHttpProxy(String host, String port) {
      System.getProperties().put("http.proxyHost", host);
      System.getProperties().put("http.proxyPort", port);
   }

   static {
      try {
         javaVersionNumber = 10;
         Class.forName("java.lang.Void");
         ++javaVersionNumber;
         Class.forName("java.lang.ThreadLocal");
         ++javaVersionNumber;
         Class.forName("java.lang.StrictMath");
         ++javaVersionNumber;
         Class.forName("java.lang.CharSequence");
         ++javaVersionNumber;
         Class.forName("java.net.Proxy");
         ++javaVersionNumber;
         Class.forName("java.net.CookieStore");
         ++javaVersionNumber;
         Class.forName("java.nio.file.FileSystem");
         ++javaVersionNumber;
         Class.forName("java.lang.reflect.Executable");
         ++javaVersionNumber;
      } catch (Throwable var1) {
      }

   }
}
