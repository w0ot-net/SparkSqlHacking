package org.apache.derby.impl.load;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Locale;
import org.apache.derby.iapi.services.io.FileUtil;

final class ExportWriteData extends ExportWriteDataAbstract {
   private String outputFileName;
   private String lobsFileName;
   private boolean lobsInExtFile = false;
   private long lobFileOffset = 0L;
   private OutputStreamWriter aStream;
   private OutputStreamWriter lobCharStream;
   private BufferedOutputStream lobOutBinaryStream;
   private ByteArrayOutputStream lobByteArrayStream;
   private byte[] byteBuf;
   private char[] charBuf;

   ExportWriteData(String var1, ControlInfo var2) throws Exception {
      this.outputFileName = var1;
      this.controlFileReader = var2;
      this.init();
   }

   ExportWriteData(String var1, String var2, ControlInfo var3) throws Exception {
      this.outputFileName = var1;
      this.lobsFileName = var2;
      this.controlFileReader = var3;
      this.lobsInExtFile = true;
      this.byteBuf = new byte[8192];
      this.charBuf = new char[8192];
      this.init();
   }

   private void init() throws Exception {
      this.loadPropertiesInfo();
      this.run();
   }

   public final Object run() throws Exception {
      this.openFiles();
      return null;
   }

   private void openFiles() throws Exception {
      this.outputFileName = FileUtil.stripProtocolFromFileName(this.outputFileName);
      if (this.lobsInExtFile) {
         this.lobsFileName = FileUtil.stripProtocolFromFileName(this.lobsFileName);
      }

      FileOutputStream var1 = null;
      BufferedOutputStream var2 = null;
      FileOutputStream var3 = null;

      try {
         File var4 = new File(this.outputFileName);
         var1 = new FileOutputStream(this.outputFileName);
         FileUtil.limitAccessToOwner(var4);
         var2 = new BufferedOutputStream(var1);
         this.aStream = this.dataCodeset == null ? new OutputStreamWriter(var2) : new OutputStreamWriter(var2, this.dataCodeset);
         if (this.lobsInExtFile) {
            File var5 = new File(this.lobsFileName);
            if (var5.getParentFile() == null) {
               var5 = new File((new File(this.outputFileName)).getParentFile(), this.lobsFileName);
            }

            var3 = new FileOutputStream(var5);
            FileUtil.limitAccessToOwner(var5);
            this.lobOutBinaryStream = new BufferedOutputStream(var3);
            this.lobByteArrayStream = new ByteArrayOutputStream();
            this.lobCharStream = this.dataCodeset == null ? new OutputStreamWriter(this.lobByteArrayStream) : new OutputStreamWriter(this.lobByteArrayStream, this.dataCodeset);
         }

      } catch (Exception var6) {
         if (this.aStream == null) {
            if (var2 != null) {
               var2.close();
            } else if (var1 != null) {
               var1.close();
            }
         } else {
            this.aStream.close();
            if (this.lobOutBinaryStream != null) {
               this.lobOutBinaryStream.close();
            } else if (var3 != null) {
               var3.close();
            }
         }

         throw var6;
      }
   }

   void writeColumnDefinitionOptionally(String[] var1, String[] var2) throws Exception {
      boolean var3 = true;
      if (this.columnDefinition.toUpperCase(Locale.ENGLISH).equals("True".toUpperCase(Locale.ENGLISH))) {
         String var4 = "";

         for(int var5 = 0; var5 < var1.length; ++var5) {
            if (var5 > 0) {
               var4 = this.fieldSeparator;
            } else {
               var4 = "";
            }

            var4 = var4 + this.fieldStartDelimiter + var1[var5] + this.fieldStopDelimiter;
            if (!var3) {
               var4 = var4 + this.fieldSeparator + this.fieldStartDelimiter + var2[var5] + this.fieldStopDelimiter;
            }

            this.aStream.write(var4, 0, var4.length());
         }

         this.aStream.write(this.recordSeparator, 0, this.recordSeparator.length());
      }

   }

   private void writeNextColumn(String var1, boolean var2) throws Exception {
      if (var1 != null) {
         if (!var2) {
            this.aStream.write(this.fieldStartDelimiter, 0, this.fieldStartDelimiter.length());
         }

         if (this.doubleDelimiter) {
            var1 = this.makeDoubleDelimiterString(var1, this.fieldStartDelimiter);
         }

         this.aStream.write(var1, 0, var1.length());
         if (!var2) {
            this.aStream.write(this.fieldStopDelimiter, 0, this.fieldStopDelimiter.length());
         }
      }

   }

   String writeBinaryColumnToExternalFile(InputStream var1) throws Exception {
      long var2 = 0L;
      boolean var4 = false;
      if (var1 != null) {
         for(int var6 = var1.read(this.byteBuf); var6 != -1; var6 = var1.read(this.byteBuf)) {
            this.lobOutBinaryStream.write(this.byteBuf, 0, var6);
            var2 += (long)var6;
         }

         var1.close();
         this.lobOutBinaryStream.flush();
      } else {
         var2 = -1L;
      }

      String var5 = this.lobsFileName + "." + this.lobFileOffset + "." + var2 + "/";
      if (var2 != -1L) {
         this.lobFileOffset += var2;
      }

      return var5;
   }

   String writeCharColumnToExternalFile(Reader var1) throws Exception {
      long var2 = 0L;
      boolean var4 = false;
      if (var1 != null) {
         for(int var6 = var1.read(this.charBuf); var6 != -1; var6 = var1.read(this.charBuf)) {
            this.lobByteArrayStream.reset();
            this.lobCharStream.write(this.charBuf, 0, var6);
            this.lobCharStream.flush();
            var2 += (long)this.lobByteArrayStream.size();
            this.lobByteArrayStream.writeTo(this.lobOutBinaryStream);
         }

         var1.close();
         this.lobOutBinaryStream.flush();
      } else {
         var2 = -1L;
      }

      String var5 = this.lobsFileName + "." + this.lobFileOffset + "." + var2 + "/";
      if (var2 != -1L) {
         this.lobFileOffset += var2;
      }

      return var5;
   }

   public void writeData(String[] var1, boolean[] var2) throws Exception {
      if (this.format.equals("ASCII_DELIMITED")) {
         this.writeNextColumn(var1[0], var2[0]);

         for(int var3 = 1; var3 < var1.length; ++var3) {
            this.aStream.write(this.fieldSeparator, 0, this.fieldSeparator.length());
            this.writeNextColumn(var1[var3], var2[var3]);
         }

         if (this.hasDelimiterAtEnd) {
            this.aStream.write(this.fieldSeparator, 0, this.fieldSeparator.length());
         }
      }

      this.aStream.write(this.recordSeparator, 0, this.recordSeparator.length());
   }

   public void noMoreRows() throws IOException {
      this.aStream.flush();
      this.aStream.close();
      if (this.lobsInExtFile) {
         if (this.lobOutBinaryStream != null) {
            this.lobOutBinaryStream.flush();
            this.lobOutBinaryStream.close();
         }

         if (this.lobCharStream != null) {
            this.lobCharStream.close();
         }

         if (this.lobByteArrayStream != null) {
            this.lobByteArrayStream.close();
         }
      }

   }

   private String makeDoubleDelimiterString(String var1, String var2) {
      int var3 = var1.indexOf(var2);
      if (var3 == -1) {
         return var1;
      } else {
         StringBuffer var4 = new StringBuffer(var1);

         int var5;
         for(int var6 = var2.length(); var3 != -1; var3 = var4.toString().indexOf(var2, var5)) {
            var4 = var4.insert(var3, var2);
            var5 = var3 + var6 + 1;
         }

         return var4.toString();
      }
   }
}
