package jodd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import jodd.io.StreamUtil;
import jodd.io.StringInputStream;
import jodd.io.findfile.ClassFinder;
import jodd.io.findfile.ClassScanner;

public class PropertiesUtil {
   private static final StringTemplateParser stp = new StringTemplateParser();

   public static Properties createFromFile(String fileName) throws IOException {
      return createFromFile(new File(fileName));
   }

   public static Properties createFromFile(File file) throws IOException {
      Properties prop = new Properties();
      loadFromFile(prop, file);
      return prop;
   }

   public static void loadFromFile(Properties p, String fileName) throws IOException {
      loadFromFile(p, new File(fileName));
   }

   public static void loadFromFile(Properties p, File file) throws IOException {
      FileInputStream fis = null;

      try {
         fis = new FileInputStream(file);
         p.load(fis);
      } finally {
         StreamUtil.close((InputStream)fis);
      }

   }

   public static void writeToFile(Properties p, String fileName) throws IOException {
      writeToFile(p, (File)(new File(fileName)), (String)null);
   }

   public static void writeToFile(Properties p, String fileName, String header) throws IOException {
      writeToFile(p, new File(fileName), header);
   }

   public static void writeToFile(Properties p, File file) throws IOException {
      writeToFile(p, (File)file, (String)null);
   }

   public static void writeToFile(Properties p, File file, String header) throws IOException {
      FileOutputStream fos = null;

      try {
         fos = new FileOutputStream(file);
         p.store(fos, header);
      } finally {
         StreamUtil.close((OutputStream)fos);
      }

   }

   public static Properties createFromString(String data) throws IOException {
      Properties p = new Properties();
      loadFromString(p, data);
      return p;
   }

   public static void loadFromString(Properties p, String data) throws IOException {
      InputStream is = new StringInputStream(data, StringInputStream.Mode.ASCII);

      try {
         p.load(is);
      } finally {
         is.close();
      }

   }

   public static Properties subset(Properties p, String prefix, boolean stripPrefix) {
      if (StringUtil.isBlank(prefix)) {
         return p;
      } else {
         if (!prefix.endsWith(".")) {
            prefix = prefix + '.';
         }

         Properties result = new Properties();
         int baseLen = prefix.length();

         for(Object o : p.keySet()) {
            String key = (String)o;
            if (key.startsWith(prefix)) {
               result.setProperty(stripPrefix ? key.substring(baseLen) : key, p.getProperty(key));
            }
         }

         return result;
      }
   }

   public static Properties createFromClasspath(String... rootTemplate) {
      Properties p = new Properties();
      return loadFromClasspath(p, rootTemplate);
   }

   public static Properties loadFromClasspath(final Properties p, String... rootTemplate) {
      ClassScanner scanner = new ClassScanner() {
         protected void onEntry(ClassFinder.EntryData entryData) throws IOException {
            p.load(entryData.openInputStream());
         }
      };
      scanner.setIncludeResources(true);
      scanner.setIgnoreException(true);
      scanner.setIncludedEntries(rootTemplate);
      scanner.scanDefaultClasspath();
      return p;
   }

   public static String getProperty(Map map, String key) {
      return getProperty(map, key, (String)null);
   }

   public static String getProperty(Map map, String key, String defaultValue) {
      Object val = map.get(key);
      return val instanceof String ? (String)val : defaultValue;
   }

   public static void resolveAllVariables(Properties prop) {
      for(Object o : prop.keySet()) {
         String key = (String)o;
         String value = resolveProperty(prop, key);
         prop.setProperty(key, value);
      }

   }

   public static String resolveProperty(final Map map, String key) {
      String value = getProperty(map, key);
      if (value == null) {
         return null;
      } else {
         value = stp.parse(value, new StringTemplateParser.MacroResolver() {
            public String resolve(String macroName) {
               return PropertiesUtil.getProperty(map, macroName);
            }
         });
         return value;
      }
   }

   static {
      stp.setParseValues(true);
   }
}
