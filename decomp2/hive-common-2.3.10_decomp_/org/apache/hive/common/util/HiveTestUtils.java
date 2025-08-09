package org.apache.hive.common.util;

import com.google.common.io.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.hadoop.hive.common.classification.InterfaceAudience;
import org.apache.hadoop.hive.common.classification.InterfaceStability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@InterfaceAudience.Private
@InterfaceStability.Unstable
public class HiveTestUtils {
   public static final Logger LOG = LoggerFactory.getLogger(HiveTestUtils.class);
   public static final String JAVA_FILE_EXT = ".java";
   public static final String CLAZZ_FILE_EXT = ".class";
   public static final String JAR_FILE_EXT = ".jar";
   public static final String TXT_FILE_EXT = ".txt";

   public static String getFileFromClasspath(String name) {
      URL url = ClassLoader.getSystemResource(name);
      if (url == null) {
         throw new IllegalArgumentException("Could not find " + name);
      } else {
         return url.getPath();
      }
   }

   private static void executeCmd(String[] cmdArr, File dir) throws IOException, InterruptedException {
      final Process p1 = Runtime.getRuntime().exec(cmdArr, (String[])null, dir);
      (new Thread(new Runnable() {
         public void run() {
            BufferedReader input = new BufferedReader(new InputStreamReader(p1.getErrorStream()));

            String line;
            try {
               while((line = input.readLine()) != null) {
                  System.out.println(line);
               }
            } catch (IOException e) {
               HiveTestUtils.LOG.error("Failed to execute the command due the exception " + e);
            }

         }
      })).start();
      p1.waitFor();
   }

   public static File genLocalJarForTest(String pathToClazzFile, String clazzName) throws IOException, InterruptedException {
      File dir = new File(pathToClazzFile);
      File parentDir = dir.getParentFile();
      File f = new File(parentDir, clazzName + ".java");
      Files.copy(dir, f);
      executeCmd(new String[]{"javac", clazzName + ".java"}, parentDir);
      executeCmd(new String[]{"jar", "cf", clazzName + ".jar", clazzName + ".class"}, parentDir);
      f.delete();
      (new File(parentDir, clazzName + ".class")).delete();
      return new File(parentDir, clazzName + ".jar");
   }
}
