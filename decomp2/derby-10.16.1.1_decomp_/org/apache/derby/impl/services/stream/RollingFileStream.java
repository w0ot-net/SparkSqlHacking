package org.apache.derby.impl.services.stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;

public class RollingFileStream extends OutputStream {
   private MeteredStream meter;
   private boolean append;
   private int limit;
   private int count;
   private String pattern;
   private String lockFileName;
   private FileOutputStream lockStream;
   private File[] files;
   private static final int MAX_LOCKS = 100;
   private static HashMap locks = new HashMap();

   public RollingFileStream() throws IOException {
      this("%d/derby-%g.log", 0, 1, false);
   }

   public RollingFileStream(String var1, int var2, int var3, boolean var4) throws IOException {
      if (var2 >= 0 && var3 >= 1 && var1.length() >= 1) {
         this.pattern = var1;
         this.limit = var2;
         this.count = var3;
         this.append = var4;
         this.openFiles();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void write(int var1) throws IOException {
      this.meter.write(var1);
      this.checkMeter();
   }

   private void openFiles() throws IOException {
      if (this.count < 1) {
         throw new IllegalArgumentException("file count = " + this.count);
      } else {
         if (this.limit < 0) {
            this.limit = 0;
         }

         int var1 = -1;

         while(true) {
            ++var1;
            if (var1 > 100) {
               throw new IOException("Couldn't get lock for " + this.pattern);
            }

            File var10001 = this.generate(this.pattern, 0, var1);
            this.lockFileName = var10001.toString() + ".lck";
            synchronized(locks) {
               if (locks.get(this.lockFileName) != null) {
                  continue;
               }

               FileChannel var3;
               try {
                  this.lockStream = this.openFile(this.lockFileName, false);
                  var3 = this.lockStream.getChannel();
               } catch (IOException var6) {
                  continue;
               }

               label62:
               try {
                  FileLock var4 = var3.tryLock();
                  if (var4 != null) {
                     break label62;
                  }
                  continue;
               } catch (IOException var7) {
               }

               locks.put(this.lockFileName, this.lockFileName);
            }

            this.files = new File[this.count];

            for(int var2 = 0; var2 < this.count; ++var2) {
               this.files[var2] = this.generate(this.pattern, var2, var1);
            }

            if (this.append) {
               this.open(this.files[0], true);
            } else {
               this.rotate();
            }

            return;
         }
      }
   }

   private File generate(String var1, int var2, int var3) throws IOException {
      File var4 = null;
      String var5 = "";
      int var6 = 0;
      boolean var7 = false;
      boolean var8 = false;

      while(var6 < var1.length()) {
         char var9 = var1.charAt(var6);
         ++var6;
         char var10 = 0;
         if (var6 < var1.length()) {
            var10 = Character.toLowerCase(var1.charAt(var6));
         }

         if (var9 == '/') {
            if (var4 == null) {
               var4 = new File(var5);
            } else {
               var4 = new File(var4, var5);
            }

            var5 = "";
         } else {
            if (var9 == '%') {
               if (var10 == 't') {
                  String var12 = this.getSystemProperty("java.io.tmpdir");
                  if (var12 == null) {
                     var12 = this.getSystemProperty("user.home");
                  }

                  var4 = new File(var12);
                  ++var6;
                  var5 = "";
                  continue;
               }

               if (var10 == 'h') {
                  var4 = new File(this.getSystemProperty("user.home"));
                  ++var6;
                  var5 = "";
                  continue;
               }

               if (var10 == 'd') {
                  String var11 = this.getSystemProperty("derby.system.home");
                  if (var11 == null) {
                     var11 = this.getSystemProperty("user.dir");
                  }

                  var4 = new File(var11);
                  ++var6;
                  var5 = "";
                  continue;
               }

               if (var10 == 'g') {
                  var5 = var5 + var2;
                  var7 = true;
                  ++var6;
                  continue;
               }

               if (var10 == 'u') {
                  var5 = var5 + var3;
                  var8 = true;
                  ++var6;
                  continue;
               }

               if (var10 == '%') {
                  var5 = var5 + "%";
                  ++var6;
                  continue;
               }
            }

            var5 = var5 + var9;
         }
      }

      if (this.count > 1 && !var7) {
         var5 = var5 + "." + var2;
      }

      if (var3 > 0 && !var8) {
         var5 = var5 + "." + var3;
      }

      if (var5.length() > 0) {
         if (var4 == null) {
            var4 = new File(var5);
         } else {
            var4 = new File(var4, var5);
         }
      }

      return var4;
   }

   private synchronized void rotate() throws IOException {
      if (null != this.meter) {
         this.meter.close();
      }

      for(int var1 = this.count - 2; var1 >= 0; --var1) {
         File var2 = this.files[var1];
         File var3 = this.files[var1 + 1];
         if (this.fileExists(var2)) {
            if (this.fileExists(var3)) {
               this.fileDelete(var3);
            }

            this.fileRename(var2, var3);
         }
      }

      try {
         this.open(this.files[0], false);
      } catch (IOException var4) {
      }

   }

   public synchronized void close() {
      if (null != this.meter) {
         try {
            this.meter.close();
         } catch (IOException var5) {
         }
      }

      if (this.lockFileName != null) {
         try {
            this.lockStream.close();
         } catch (Exception var4) {
         }

         synchronized(locks) {
            locks.remove(this.lockFileName);
         }

         this.fileDelete(new File(this.lockFileName));
         this.lockFileName = null;
         this.lockStream = null;
      }
   }

   private String getSystemProperty(String var1) {
      return System.getProperty(var1);
   }

   private FileOutputStream openFile(String var1, boolean var2) throws IOException {
      FileOutputStream var3 = new FileOutputStream(var1, var2);
      return var3;
   }

   private boolean fileExists(File var1) {
      return var1.exists();
   }

   private void fileDelete(File var1) {
      var1.delete();
   }

   private boolean fileRename(File var1, File var2) {
      return var1.renameTo(var2);
   }

   private long fileLength(File var1) {
      return var1.length();
   }

   private void open(File var1, boolean var2) throws IOException {
      int var3 = 0;
      if (var2) {
         var3 = (int)this.fileLength(var1);
      }

      FileOutputStream var4 = this.openFile(var1.toString(), var2);
      this.meter = new MeteredStream(var4, var3);
   }

   private void checkMeter() throws IOException {
      if (this.limit > 0 && this.meter.written >= this.limit) {
         this.rotate();
      }

   }

   private class MeteredStream extends OutputStream {
      OutputStream out;
      int written;

      MeteredStream(OutputStream var2, int var3) {
         this.out = var2;
         this.written = var3;
      }

      public void write(int var1) throws IOException {
         this.out.write(var1);
         ++this.written;
      }

      public int getWritten() {
         return this.written;
      }

      public void close() throws IOException {
         this.out.close();
      }
   }
}
