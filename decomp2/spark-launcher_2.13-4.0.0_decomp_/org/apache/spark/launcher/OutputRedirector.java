package org.apache.spark.launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

class OutputRedirector {
   private final BufferedReader reader;
   private final Logger sink;
   private final Thread thread;
   private final ChildProcAppHandle callback;
   private volatile boolean active;
   private volatile Throwable error;

   OutputRedirector(InputStream in, String loggerName, ThreadFactory tf) {
      this(in, loggerName, tf, (ChildProcAppHandle)null);
   }

   OutputRedirector(InputStream in, String loggerName, ThreadFactory tf, ChildProcAppHandle callback) {
      this.active = true;
      this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
      this.thread = tf.newThread(this::redirect);
      this.sink = Logger.getLogger(loggerName);
      this.callback = callback;
      this.thread.start();
   }

   private void redirect() {
      try {
         String line;
         try {
            while((line = this.reader.readLine()) != null) {
               if (this.active) {
                  this.sink.info(line.replaceFirst("\\s*$", ""));
                  if ((containsIgnoreCase(line, "Error") || containsIgnoreCase(line, "Exception")) && !line.contains("at ")) {
                     this.error = new RuntimeException(line);
                  }
               }
            }
         } catch (IOException e) {
            this.sink.log(Level.FINE, "Error reading child process output.", e);
         }
      } finally {
         if (this.callback != null) {
            this.callback.monitorChild();
         }

      }

   }

   void stop() {
      this.active = false;
   }

   boolean isAlive() {
      return this.thread.isAlive();
   }

   Throwable getError() {
      return this.error;
   }

   private static boolean containsIgnoreCase(String str, String searchStr) {
      if (str != null && searchStr != null) {
         int len = searchStr.length();
         int max = str.length() - len;

         for(int i = 0; i <= max; ++i) {
            if (str.regionMatches(true, i, searchStr, 0, len)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }
}
