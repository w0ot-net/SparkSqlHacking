package org.apache.derby.impl.services.stream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;
import org.apache.derby.shared.common.stream.InfoStreams;
import org.apache.derby.shared.common.stream.PrintWriterGetHeader;

public final class SingleStream implements InfoStreams, ModuleControl {
   private HeaderPrintWriter theStream;
   private String PBfileName;
   private PrintWriterGetHeader PBheader;

   public void boot(boolean var1, Properties var2) {
      this.theStream = this.makeStream();
   }

   public void stop() {
      ((BasicHeaderPrintWriter)this.theStream).complete();
   }

   public HeaderPrintWriter stream() {
      return this.theStream;
   }

   private HeaderPrintWriter makeStream() {
      PrintWriterGetHeader var1 = this.makeHeader();
      HeaderPrintWriter var2 = this.makeHPW(var1);
      if (var2 == null) {
         var2 = this.createDefaultStream(var1);
      }

      return var2;
   }

   private PrintWriterGetHeader makeHeader() {
      return new BasicGetLogHeader(true, true, (String)null);
   }

   private HeaderPrintWriter makeHPW(PrintWriterGetHeader var1) {
      String var2 = PropertyUtil.getSystemProperty("derby.stream.error.style");
      if (var2 != null) {
         return this.makeStyleHPW(var2, var1);
      } else {
         var2 = PropertyUtil.getSystemProperty("derby.stream.error.file");
         if (var2 != null) {
            return this.makeFileHPW(var2, var1);
         } else {
            var2 = PropertyUtil.getSystemProperty("derby.stream.error.method");
            if (var2 != null) {
               return this.makeMethodHPW(var2, var1, false);
            } else {
               var2 = PropertyUtil.getSystemProperty("derby.stream.error.field");
               return var2 != null ? this.makeFieldHPW(var2, var1) : null;
            }
         }
      }
   }

   private HeaderPrintWriter PBmakeFileHPW(String var1, PrintWriterGetHeader var2) {
      boolean var3 = PropertyUtil.getSystemBoolean("derby.infolog.append");
      File var4 = new File(var1);
      if (!var4.isAbsolute()) {
         Object var5 = getMonitor().getEnvironment();
         if (var5 instanceof File) {
            var4 = new File((File)var5, var1);
         }
      }

      FileOutputStream var8;
      try {
         if (var4.exists() && var3) {
            var8 = new FileOutputStream(var4.getPath(), true);
         } else {
            var8 = new FileOutputStream(var4);
         }

         FileUtil.limitAccessToOwner(var4);
      } catch (IOException var7) {
         return this.useDefaultStream(var2, var7);
      }

      return new BasicHeaderPrintWriter(new BufferedOutputStream(var8), var2, true, var4.getPath());
   }

   private HeaderPrintWriter makeMethodHPW(String var1, PrintWriterGetHeader var2, boolean var3) {
      int var4 = var1.lastIndexOf(46);
      String var5 = var1.substring(0, var4);
      String var6 = var1.substring(var4 + 1);

      Object var7;
      try {
         Class var8 = Class.forName(var5);

         try {
            Method var9 = var8.getMethod(var6);
            if (!Modifier.isStatic(var9.getModifiers())) {
               HeaderPrintWriter var10 = this.useDefaultStream(var2);
               var10.printlnWithHeader(var9.toString() + " is not static");
               return var10;
            }

            try {
               return this.makeValueHPW(var9, var9.invoke((Object)null), var2, var1, var3);
            } catch (IllegalAccessException var11) {
               var7 = var11;
            } catch (IllegalArgumentException var12) {
               var7 = var12;
            } catch (InvocationTargetException var13) {
               var7 = var13.getTargetException();
            }
         } catch (NoSuchMethodException var14) {
            var7 = var14;
         }
      } catch (ClassNotFoundException var15) {
         var7 = var15;
      }

      return this.useDefaultStream(var2, (Throwable)var7);
   }

   private HeaderPrintWriter makeStyleHPW(String var1, PrintWriterGetHeader var2) {
      Object var3 = null;
      HeaderPrintWriter var7;
      if ("rollingFile".equals(var1)) {
         String var4 = "org.apache.derby.impl.services.stream.RollingFileStreamProvider.getOutputStream";
         var7 = this.makeMethodHPW(var4, var2, true);
      } else {
         try {
            IllegalArgumentException var8 = new IllegalArgumentException("unknown derby.stream.error.style: " + var1);
            throw var8;
         } catch (IllegalArgumentException var5) {
            var7 = this.useDefaultStream(var2, var5);
         } catch (Exception var6) {
            var7 = this.useDefaultStream(var2, var6);
         }
      }

      return var7;
   }

   private HeaderPrintWriter makeFieldHPW(String var1, PrintWriterGetHeader var2) {
      int var3 = var1.lastIndexOf(46);
      String var4 = var1.substring(0, var3);
      String var5 = var1.substring(var3 + 1, var1.length());

      Object var6;
      try {
         Class var7 = Class.forName(var4);

         try {
            Field var8 = var7.getField(var5);
            if (!Modifier.isStatic(var8.getModifiers())) {
               HeaderPrintWriter var9 = this.useDefaultStream(var2);
               var9.printlnWithHeader(var8.toString() + " is not static");
               return var9;
            }

            try {
               return this.makeValueHPW(var8, var8.get((Object)null), var2, var1, false);
            } catch (IllegalAccessException var10) {
               var6 = var10;
            } catch (IllegalArgumentException var11) {
               var6 = var11;
            }
         } catch (NoSuchFieldException var12) {
            var6 = var12;
         }
      } catch (ClassNotFoundException var13) {
         var6 = var13;
      }

      return this.useDefaultStream(var2, (Throwable)var6);
   }

   private HeaderPrintWriter makeValueHPW(Member var1, Object var2, PrintWriterGetHeader var3, String var4, boolean var5) {
      if (var2 instanceof OutputStream) {
         return new BasicHeaderPrintWriter((OutputStream)var2, var3, var5, var4);
      } else if (var2 instanceof Writer) {
         return new BasicHeaderPrintWriter((Writer)var2, var3, var5, var4);
      } else {
         HeaderPrintWriter var6 = this.useDefaultStream(var3);
         if (var2 == null) {
            var6.printlnWithHeader(var1.toString() + "=null");
         } else {
            String var10001 = var1.toString();
            var6.printlnWithHeader(var10001 + " instanceof " + var2.getClass().getName());
         }

         return var6;
      }
   }

   private HeaderPrintWriter createDefaultStream(PrintWriterGetHeader var1) {
      return this.makeFileHPW("derby.log", var1);
   }

   private HeaderPrintWriter useDefaultStream(PrintWriterGetHeader var1) {
      return new BasicHeaderPrintWriter(System.err, var1, false, "System.err");
   }

   private HeaderPrintWriter useDefaultStream(PrintWriterGetHeader var1, Throwable var2) {
      HeaderPrintWriter var3;
      Throwable var4;
      for(var3 = this.useDefaultStream(var1); var2 != null; var2 = var4) {
         var4 = var2.getCause();
         String var5 = MessageService.getTextMessage("N001", new Object[0]);
         String var10001 = var2.toString();
         var3.printlnWithHeader(var10001 + (var4 != null ? " " + var5 : ""));
      }

      return var3;
   }

   private HeaderPrintWriter makeFileHPW(String var1, PrintWriterGetHeader var2) {
      this.PBfileName = var1;
      this.PBheader = var2;
      return this.run();
   }

   public final HeaderPrintWriter run() {
      return this.PBmakeFileHPW(this.PBfileName, this.PBheader);
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
