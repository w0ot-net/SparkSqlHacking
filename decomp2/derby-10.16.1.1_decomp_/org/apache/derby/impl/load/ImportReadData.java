package org.apache.derby.impl.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Locale;

final class ImportReadData {
   private String inputFileName;
   private short skipLines;
   private int[] columnWidths;
   private int rowWidth;
   private char[] tempString;
   private int numberOfCharsReadSoFar;
   private BufferedReader bufferedReader;
   private static final int START_SIZE = 10240;
   private char[] currentToken = new char[10240];
   private int currentTokenMaxSize = 10240;
   boolean foundStartDelimiter;
   int totalCharsSoFar;
   int positionOfNonWhiteSpaceCharInFront;
   int positionOfNonWhiteSpaceCharInBack;
   int lineNumber;
   int fieldStartDelimiterIndex;
   int fieldStopDelimiterIndex;
   int stopDelimiterPosition;
   boolean foundStartAndStopDelimiters;
   boolean streamOpenForReading;
   static final int DEFAULT_FORMAT_CODE = 0;
   static final int ASCII_FIXED_FORMAT_CODE = 1;
   private int formatCode = 0;
   private boolean hasColumnDefinition;
   private char recordSeparatorChar0;
   private char fieldSeparatorChar0;
   private boolean recordSepStartNotWhite = true;
   private boolean fieldSepStartNotWhite = true;
   protected ControlInfo controlFileReader;
   protected int numberOfColumns;
   protected String[] columnTypes;
   protected char[] fieldSeparator;
   protected int fieldSeparatorLength;
   protected char[] recordSeparator;
   protected int recordSeparatorLength;
   protected String nullString;
   protected String columnDefinition;
   protected String format;
   protected String dataCodeset;
   protected char[] fieldStartDelimiter;
   protected int fieldStartDelimiterLength;
   protected char[] fieldStopDelimiter;
   protected int fieldStopDelimiterLength;
   protected boolean hasDelimiterAtEnd;
   private ImportLobFile[] lobFileHandles;
   private String lobFileName;
   private long lobOffset;
   private int lobLength;
   private boolean haveSep = true;

   private void loadPropertiesInfo() throws Exception {
      this.fieldSeparator = this.controlFileReader.getFieldSeparator().toCharArray();
      this.fieldSeparatorLength = this.fieldSeparator.length;
      this.recordSeparator = this.controlFileReader.getRecordSeparator().toCharArray();
      this.recordSeparatorLength = this.recordSeparator.length;
      this.nullString = this.controlFileReader.getNullString();
      this.columnDefinition = this.controlFileReader.getColumnDefinition();
      this.format = this.controlFileReader.getFormat();
      this.dataCodeset = this.controlFileReader.getDataCodeset();
      this.fieldStartDelimiter = this.controlFileReader.getFieldStartDelimiter().toCharArray();
      this.fieldStartDelimiterLength = this.fieldStartDelimiter.length;
      this.fieldStopDelimiter = this.controlFileReader.getFieldEndDelimiter().toCharArray();
      this.fieldStopDelimiterLength = this.fieldStopDelimiter.length;
      this.hasDelimiterAtEnd = this.controlFileReader.getHasDelimiterAtEnd();
      if (this.recordSeparatorLength > 0) {
         this.recordSeparatorChar0 = this.recordSeparator[0];
         this.recordSepStartNotWhite = !Character.isWhitespace(this.recordSeparatorChar0);
      }

      if (this.fieldSeparatorLength > 0) {
         this.fieldSeparatorChar0 = this.fieldSeparator[0];
         this.fieldSepStartNotWhite = !Character.isWhitespace(this.fieldSeparatorChar0);
      }

   }

   ImportReadData(String var1, ControlInfo var2, short var3) throws Exception {
      this.skipLines = var3;
      this.inputFileName = var1;
      this.controlFileReader = var2;
      this.loadPropertiesInfo();
      this.loadMetaData();
      this.lobFileHandles = new ImportLobFile[this.numberOfColumns];
   }

   int getNumberOfColumns() {
      return this.numberOfColumns;
   }

   protected void ignoreFirstRow() throws Exception {
      this.readNextToken(this.recordSeparator, 0, this.recordSeparatorLength, true);
   }

   protected void ignoreHeaderLines() throws Exception {
      for(int var1 = 0; var1 < this.skipLines; ++var1) {
         if (!this.readNextToken(this.recordSeparator, 0, this.recordSeparatorLength, true)) {
            throw LoadError.unexpectedEndOfFile(this.lineNumber + 1);
         }

         ++this.lineNumber;
      }

   }

   protected void loadColumnTypes() throws Exception {
      this.findNumberOfColumnsInARow();
      this.closeStream();
      this.openFile();
      String[] var2 = new String[this.numberOfColumns];
      this.readNextDelimitedRow(var2);
      this.columnTypes = new String[this.numberOfColumns / 2];

      for(int var1 = 0; var1 < this.numberOfColumns; var1 += 2) {
         this.columnTypes[var1 / 2] = var2[var1 + 1];
      }

      this.closeStream();
      this.openFile();
      this.numberOfColumns = 0;
   }

   private void openFile() throws Exception {
      this.run();
   }

   public final Object run() throws Exception {
      this.realOpenFile();
      return null;
   }

   private void realOpenFile() throws Exception {
      Object var1;
      try {
         try {
            URL var2 = new URL(this.inputFileName);
            if (var2.getProtocol().equals("file")) {
               this.inputFileName = var2.getFile();
               throw new MalformedURLException();
            }

            var1 = var2.openStream();
         } catch (MalformedURLException var3) {
            var1 = new FileInputStream(this.inputFileName);
         }
      } catch (FileNotFoundException var4) {
         throw LoadError.dataFileNotFound(this.inputFileName, var4);
      }

      InputStreamReader var5 = this.dataCodeset == null ? new InputStreamReader((InputStream)var1) : new InputStreamReader((InputStream)var1, this.dataCodeset);
      this.bufferedReader = new BufferedReader(var5, 32768);
      this.streamOpenForReading = true;
   }

   private void loadMetaData() throws Exception {
      this.openFile();
      if (this.columnDefinition.toUpperCase(Locale.ENGLISH).equals("True".toUpperCase(Locale.ENGLISH))) {
         this.hasColumnDefinition = true;
         this.ignoreFirstRow();
      }

      if (this.formatCode == 0) {
         this.findNumberOfColumnsInARow();
      }

      this.closeStream();
   }

   void closeStream() throws Exception {
      if (this.streamOpenForReading) {
         this.bufferedReader.close();
         this.streamOpenForReading = false;
      }

      if (this.lobFileHandles != null) {
         for(int var1 = 0; var1 < this.numberOfColumns; ++var1) {
            if (this.lobFileHandles[var1] != null) {
               this.lobFileHandles[var1].close();
            }
         }
      }

   }

   int findNumberOfColumnsInARow() throws Exception {
      for(this.numberOfColumns = 1; !this.readTokensUntilEndOfRecord(); ++this.numberOfColumns) {
      }

      if (this.hasDelimiterAtEnd) {
         --this.numberOfColumns;
      }

      if (this.numberOfCharsReadSoFar == 0) {
         this.numberOfColumns = 0;
      }

      return this.numberOfColumns;
   }

   private void checkForWhiteSpaceInFront() {
      if (this.positionOfNonWhiteSpaceCharInFront + 1 == this.totalCharsSoFar && !this.foundStartDelimiter && !this.foundStartAndStopDelimiters) {
         char var1 = this.currentToken[this.positionOfNonWhiteSpaceCharInFront];
         if (Character.isWhitespace(var1) && (this.recordSepStartNotWhite || var1 != this.recordSeparatorChar0) && (this.fieldSepStartNotWhite || var1 != this.fieldSeparatorChar0)) {
            ++this.positionOfNonWhiteSpaceCharInFront;
         }
      }

   }

   private void checkForWhiteSpaceInBack() {
      boolean var1 = true;
      this.positionOfNonWhiteSpaceCharInBack = 0;

      for(int var2 = this.totalCharsSoFar; var2 > this.stopDelimiterPosition && var1; --var2) {
         char var3 = this.currentToken[var2];
         if (Character.isWhitespace(var3)) {
            if ((this.recordSepStartNotWhite || var3 != this.recordSeparatorChar0) && (this.fieldSepStartNotWhite || var3 != this.fieldSeparatorChar0)) {
               ++this.positionOfNonWhiteSpaceCharInBack;
            }
         } else {
            var1 = false;
         }
      }

   }

   boolean readTokensUntilEndOfRecord() throws Exception {
      int var2 = 0;
      int var3 = 0;
      this.fieldStopDelimiterIndex = 0;
      this.fieldStartDelimiterIndex = 0;
      this.totalCharsSoFar = 0;
      this.positionOfNonWhiteSpaceCharInFront = 0;
      this.foundStartDelimiter = false;
      this.foundStartAndStopDelimiters = false;
      this.numberOfCharsReadSoFar = 0;

      while(true) {
         int var1 = this.bufferedReader.read();
         if (var1 == -1) {
            return true;
         }

         ++this.numberOfCharsReadSoFar;
         this.currentToken[this.totalCharsSoFar++] = (char)var1;
         this.checkForWhiteSpaceInFront();
         if (this.totalCharsSoFar == this.currentTokenMaxSize) {
            this.currentTokenMaxSize *= 2;
            char[] var4 = new char[this.currentTokenMaxSize];
            System.arraycopy(this.currentToken, 0, var4, 0, this.totalCharsSoFar);
            this.currentToken = var4;
         }

         var2 = this.lookForPassedSeparator(this.fieldSeparator, var2, this.fieldSeparatorLength, var1, false);
         if (var2 == -1) {
            return false;
         }

         if (!this.foundStartDelimiter) {
            var3 = this.lookForPassedSeparator(this.recordSeparator, var3, this.recordSeparatorLength, var1, true);
            if (var3 == -1) {
               return true;
            }
         }
      }
   }

   private int lookForPassedSeparator(char[] var1, int var2, int var3, int var4, boolean var5) throws IOException {
      if (!this.foundStartDelimiter) {
         if (this.fieldStartDelimiterLength != 0 && !this.foundStartAndStopDelimiters && this.totalCharsSoFar != this.positionOfNonWhiteSpaceCharInFront && this.totalCharsSoFar - this.positionOfNonWhiteSpaceCharInFront <= this.fieldStartDelimiterLength) {
            if (var4 == this.fieldStartDelimiter[this.fieldStartDelimiterIndex]) {
               ++this.fieldStartDelimiterIndex;
               if (this.fieldStartDelimiterIndex == this.fieldStartDelimiterLength) {
                  this.foundStartDelimiter = true;
                  this.totalCharsSoFar = 0;
                  this.positionOfNonWhiteSpaceCharInFront = 0;
                  return 0;
               }
            } else if (this.fieldStartDelimiterIndex > 0) {
               this.reCheckRestOfTheCharacters(this.totalCharsSoFar - this.fieldStartDelimiterIndex, this.fieldStartDelimiter, this.fieldStartDelimiterLength);
            }
         }

         if (var5) {
            if (var4 != 13 && var4 != 10) {
               return var2;
            }

            this.recordSeparatorChar0 = (char)var4;
            if (var4 == 13) {
               this.omitLineFeed();
            }

            --this.totalCharsSoFar;
            return -1;
         }

         if (var4 == var1[var2]) {
            ++var2;
            if (var2 == var3) {
               this.totalCharsSoFar -= var3;
               return -1;
            }

            return var2;
         }

         if (var2 > 0) {
            return this.reCheckRestOfTheCharacters(this.totalCharsSoFar - var2, var1, var3);
         }
      } else {
         if (var4 == this.fieldStopDelimiter[this.fieldStopDelimiterIndex]) {
            ++this.fieldStopDelimiterIndex;
            if (this.fieldStopDelimiterIndex == this.fieldStopDelimiterLength) {
               boolean var6 = this.skipDoubleDelimiters(this.fieldStopDelimiter);
               if (!var6) {
                  this.foundStartDelimiter = false;
                  this.totalCharsSoFar -= this.fieldStopDelimiterLength;
                  this.stopDelimiterPosition = this.totalCharsSoFar;
                  this.foundStartAndStopDelimiters = true;
               } else {
                  this.fieldStopDelimiterIndex = 0;
               }

               return 0;
            }

            return 0;
         }

         if (this.fieldStopDelimiterIndex > 0) {
            this.reCheckRestOfTheCharacters(this.totalCharsSoFar - this.fieldStopDelimiterIndex, this.fieldStopDelimiter, this.fieldStopDelimiterLength);
            return 0;
         }
      }

      return 0;
   }

   private int reCheckRestOfTheCharacters(int var1, char[] var2, int var3) {
      int var4 = 0;

      for(int var5 = var1; var5 < this.totalCharsSoFar; ++var5) {
         if (this.currentToken[var5] == var2[var4]) {
            ++var4;
         } else {
            var4 = 0;
         }
      }

      return var4;
   }

   private boolean skipDoubleDelimiters(char[] var1) throws IOException {
      boolean var2 = true;
      int var3 = var1.length;
      this.bufferedReader.mark(var3);

      for(int var4 = 0; var4 < var3; ++var4) {
         int var5 = this.bufferedReader.read();
         if (var5 != var1[var4]) {
            this.bufferedReader.reset();
            var2 = false;
            break;
         }
      }

      return var2;
   }

   private void omitLineFeed() throws IOException {
      this.bufferedReader.mark(1);
      int var1 = this.bufferedReader.read();
      if (var1 != 10) {
         this.bufferedReader.reset();
      }

   }

   int getCurrentRowNumber() {
      return this.lineNumber;
   }

   boolean readNextRow(String[] var1) throws Exception {
      if (!this.streamOpenForReading) {
         this.openFile();
         if (this.hasColumnDefinition) {
            this.ignoreFirstRow();
         }

         this.ignoreHeaderLines();
      }

      boolean var2;
      if (this.formatCode == 0) {
         var2 = this.readNextDelimitedRow(var1);
      } else {
         var2 = this.readNextFixedRow(var1);
      }

      return var2;
   }

   private boolean readNextFixedRow(String[] var1) throws Exception {
      int var2 = 0;

      for(int var3 = 0; (var2 += this.bufferedReader.read(this.tempString, var2, this.rowWidth - var2)) < this.rowWidth; var3 = var2) {
         if (var2 == var3 - 1) {
            if (var2 == -1) {
               return false;
            }

            if (var3 != this.rowWidth - this.recordSeparator.length) {
               throw LoadError.unexpectedEndOfFile(this.lineNumber + 1);
            }

            this.haveSep = false;
            break;
         }
      }

      int var4 = 0;

      for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
         int var6 = this.columnWidths[var5];
         if (var6 == 0) {
            var1[var5] = null;
         } else {
            String var7 = new String(this.tempString, var4, var6);
            if (var7.trim().equals(this.nullString)) {
               var1[var5] = null;
            } else {
               var1[var5] = var7;
            }

            var4 += var6;
         }
      }

      if (this.haveSep) {
         for(int var8 = this.recordSeparatorLength - 1; var8 >= 0; --var8) {
            if (this.tempString[var4 + var8] != this.recordSeparator[var8]) {
               throw LoadError.recordSeparatorMissing(this.lineNumber + 1);
            }
         }
      } else {
         this.haveSep = true;
      }

      ++this.lineNumber;
      return true;
   }

   private boolean readNextDelimitedRow(String[] var1) throws Exception {
      int var2 = this.numberOfColumns - 1;
      if (var2 < 0) {
         return false;
      } else {
         for(int var3 = 0; var3 < var2; ++var3) {
            if (!this.readNextToken(this.fieldSeparator, 0, this.fieldSeparatorLength, false)) {
               if (var3 == 0) {
                  return false;
               }

               throw LoadError.unexpectedEndOfFile(this.lineNumber + 1);
            }

            if (this.stopDelimiterPosition != 0 && this.stopDelimiterPosition != this.totalCharsSoFar) {
               for(int var4 = this.stopDelimiterPosition + 1; var4 < this.totalCharsSoFar; ++var4) {
                  if (!Character.isWhitespace(this.currentToken[var4])) {
                     throw LoadError.dataAfterStopDelimiter(this.lineNumber + 1, var3 + 1);
                  }
               }

               this.totalCharsSoFar = this.stopDelimiterPosition;
            }

            if (this.totalCharsSoFar != -1) {
               var1[var3] = new String(this.currentToken, this.positionOfNonWhiteSpaceCharInFront, this.totalCharsSoFar);
            } else {
               var1[var3] = null;
            }
         }

         if (!this.readNextToken(this.recordSeparator, 0, this.recordSeparatorLength, true)) {
            if (var2 == 0) {
               return false;
            } else {
               throw LoadError.unexpectedEndOfFile(this.lineNumber + 1);
            }
         } else {
            if (this.stopDelimiterPosition != 0 && this.stopDelimiterPosition != this.totalCharsSoFar) {
               for(int var5 = this.stopDelimiterPosition + 1; var5 < this.totalCharsSoFar; ++var5) {
                  if (!Character.isWhitespace(this.currentToken[var5])) {
                     throw LoadError.dataAfterStopDelimiter(this.lineNumber + 1, this.numberOfColumns);
                  }
               }

               this.totalCharsSoFar = this.stopDelimiterPosition;
            }

            if (this.hasDelimiterAtEnd && this.fieldStopDelimiterLength <= 0) {
               --this.totalCharsSoFar;
            }

            if (this.totalCharsSoFar > -1) {
               if (!this.hasDelimiterAtEnd) {
                  var1[var2] = new String(this.currentToken, this.positionOfNonWhiteSpaceCharInFront, this.totalCharsSoFar);
               } else if (this.totalCharsSoFar == this.fieldSeparatorLength && this.isFieldSep(this.currentToken)) {
                  String var6 = new String(this.currentToken, this.positionOfNonWhiteSpaceCharInFront, this.totalCharsSoFar);
                  if (this.currentToken[this.totalCharsSoFar + 1] == this.fieldStopDelimiter[0]) {
                     var1[var2] = var6;
                  } else {
                     var1[var2] = null;
                  }
               } else if (this.totalCharsSoFar > 0) {
                  var1[var2] = new String(this.currentToken, this.positionOfNonWhiteSpaceCharInFront, this.totalCharsSoFar);
               } else {
                  var1[var2] = null;
               }
            } else {
               var1[var2] = null;
            }

            ++this.lineNumber;
            return true;
         }
      }
   }

   private boolean isFieldSep(char[] var1) {
      for(int var2 = 0; var2 < var1.length && var2 < this.fieldSeparatorLength; ++var2) {
         if (var1[var2] != this.fieldSeparator[var2]) {
            return false;
         }
      }

      return true;
   }

   boolean readNextToken(char[] var1, int var2, int var3, boolean var4) throws Exception {
      this.fieldStopDelimiterIndex = 0;
      this.fieldStartDelimiterIndex = 0;
      this.totalCharsSoFar = 0;
      this.positionOfNonWhiteSpaceCharInFront = 0;
      this.stopDelimiterPosition = 0;
      this.foundStartAndStopDelimiters = false;
      this.foundStartDelimiter = false;

      while(true) {
         int var5 = this.bufferedReader.read();
         if (var5 == -1) {
            return false;
         }

         this.currentToken[this.totalCharsSoFar++] = (char)var5;
         this.checkForWhiteSpaceInFront();
         if (this.totalCharsSoFar == this.currentTokenMaxSize) {
            this.currentTokenMaxSize *= 2;
            char[] var7 = new char[this.currentTokenMaxSize];
            System.arraycopy(this.currentToken, 0, var7, 0, this.totalCharsSoFar);
            this.currentToken = var7;
         }

         int var6 = this.lookForPassedSeparator(var1, var2, var3, var5, var4);
         if (var6 == -1) {
            if (!this.foundStartAndStopDelimiters) {
               if (this.totalCharsSoFar == 0) {
                  this.totalCharsSoFar = -1;
               } else {
                  this.checkForWhiteSpaceInBack();
                  this.totalCharsSoFar = this.totalCharsSoFar - this.positionOfNonWhiteSpaceCharInFront - this.positionOfNonWhiteSpaceCharInBack;
               }
            }

            return true;
         }

         var2 = var6;
      }
   }

   String getClobColumnFromExtFileAsString(String var1, int var2) throws SQLException {
      try {
         this.initExternalLobFile(var1, var2);
         return this.lobLength == -1 ? null : this.lobFileHandles[var2 - 1].getString(this.lobOffset, this.lobLength);
      } catch (Exception var4) {
         throw LoadError.unexpectedError(var4);
      }
   }

   Clob getClobColumnFromExtFile(String var1, int var2) throws SQLException {
      try {
         this.initExternalLobFile(var1, var2);
         return this.lobLength == -1 ? null : new ImportClob(this.lobFileHandles[var2 - 1], this.lobOffset, (long)this.lobLength);
      } catch (Exception var4) {
         throw LoadError.unexpectedError(var4);
      }
   }

   Blob getBlobColumnFromExtFile(String var1, int var2) throws SQLException {
      this.initExternalLobFile(var1, var2);
      return this.lobLength == -1 ? null : new ImportBlob(this.lobFileHandles[var2 - 1], this.lobOffset, (long)this.lobLength);
   }

   private void initExternalLobFile(String var1, int var2) throws SQLException {
      int var3 = var1.lastIndexOf(".");
      int var4 = var1.lastIndexOf(".", var3 - 1);
      this.lobLength = Integer.parseInt(var1.substring(var3 + 1, var1.length() - 1));
      this.lobOffset = Long.parseLong(var1.substring(var4 + 1, var3));
      this.lobFileName = var1.substring(0, var4);
      if (this.lobFileHandles[var2 - 1] == null) {
         try {
            File var5 = new File(this.lobFileName);
            if (var5.getParentFile() == null) {
               var5 = new File((new File(this.inputFileName)).getParentFile(), this.lobFileName);
            }

            this.lobFileHandles[var2 - 1] = new ImportLobFile(var5, this.controlFileReader.getDataCodeset());
         } catch (Exception var6) {
            throw LoadError.unexpectedError(var6);
         }
      }

   }
}
