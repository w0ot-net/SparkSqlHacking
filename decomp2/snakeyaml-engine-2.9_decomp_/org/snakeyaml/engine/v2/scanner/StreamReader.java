package org.snakeyaml.engine.v2.scanner;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Optional;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.common.CharConstants;
import org.snakeyaml.engine.v2.exceptions.Mark;
import org.snakeyaml.engine.v2.exceptions.ReaderException;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;

public final class StreamReader {
   private final String name;
   private final Reader stream;
   private final int bufferSize;
   private final char[] buffer;
   private final boolean useMarks;
   private int[] codePointsWindow;
   private int dataLength;
   private int pointer;
   private boolean eof;
   private int index;
   private int documentIndex;
   private int line;
   private int column;

   /** @deprecated */
   @Deprecated
   public StreamReader(Reader reader, LoadSettings loadSettings) {
      this(loadSettings, reader);
   }

   public StreamReader(LoadSettings loadSettings, Reader reader) {
      this.pointer = 0;
      this.index = 0;
      this.documentIndex = 0;
      this.line = 0;
      this.column = 0;
      this.name = loadSettings.getLabel();
      this.codePointsWindow = new int[0];
      this.dataLength = 0;
      this.stream = reader;
      this.eof = false;
      this.bufferSize = loadSettings.getBufferSize();
      this.buffer = new char[this.bufferSize + 1];
      this.useMarks = loadSettings.getUseMarks();
   }

   /** @deprecated */
   @Deprecated
   public StreamReader(String stream, LoadSettings loadSettings) {
      this((LoadSettings)loadSettings, (Reader)(new StringReader(stream)));
   }

   public StreamReader(LoadSettings loadSettings, String stream) {
      this((LoadSettings)loadSettings, (Reader)(new StringReader(stream)));
   }

   public static boolean isPrintable(String data) {
      int length = data.length();

      int codePoint;
      for(int offset = 0; offset < length; offset += Character.charCount(codePoint)) {
         codePoint = data.codePointAt(offset);
         if (!isPrintable(codePoint)) {
            return false;
         }
      }

      return true;
   }

   public static boolean isPrintable(int c) {
      return c >= 32 && c <= 126 || c == 9 || c == 10 || c == 13 || c == 133 || c >= 160 && c <= 55295 || c >= 57344 && c <= 65533 || c >= 65536 && c <= 1114111;
   }

   public Optional getMark() {
      return this.useMarks ? Optional.of(new Mark(this.name, this.index, this.line, this.column, this.codePointsWindow, this.pointer)) : Optional.empty();
   }

   public void forward() {
      this.forward(1);
   }

   public void forward(int length) {
      for(int i = 0; i < length && this.ensureEnoughData(); ++i) {
         int c = this.codePointsWindow[this.pointer++];
         this.moveIndices(1);
         if (!CharConstants.LINEBR.has(c) && (c != 13 || !this.ensureEnoughData() || this.codePointsWindow[this.pointer] == 10)) {
            if (c != 65279) {
               ++this.column;
            }
         } else {
            ++this.line;
            this.column = 0;
         }
      }

   }

   public int peek() {
      return this.ensureEnoughData() ? this.codePointsWindow[this.pointer] : 0;
   }

   public int peek(int index) {
      return this.ensureEnoughData(index) ? this.codePointsWindow[this.pointer + index] : 0;
   }

   public String prefix(int length) {
      if (length == 0) {
         return "";
      } else {
         return this.ensureEnoughData(length) ? new String(this.codePointsWindow, this.pointer, length) : new String(this.codePointsWindow, this.pointer, Math.min(length, this.dataLength - this.pointer));
      }
   }

   public String prefixForward(int length) {
      String prefix = this.prefix(length);
      this.pointer += length;
      this.moveIndices(length);
      this.column += length;
      return prefix;
   }

   private boolean ensureEnoughData() {
      return this.ensureEnoughData(0);
   }

   private boolean ensureEnoughData(int size) {
      if (!this.eof && this.pointer + size >= this.dataLength) {
         this.update();
      }

      return this.pointer + size < this.dataLength;
   }

   private void update() {
      try {
         int read = this.stream.read(this.buffer);
         if (read > 0) {
            int cpIndex = this.dataLength - this.pointer;
            this.codePointsWindow = Arrays.copyOfRange(this.codePointsWindow, this.pointer, this.dataLength + read);
            if (Character.isHighSurrogate(this.buffer[read - 1])) {
               if (this.stream.read(this.buffer, read, 1) == -1) {
                  throw new ReaderException(this.name, this.index + read, this.buffer[read - 1], "The last char is HighSurrogate (no LowSurrogate detected).");
               }

               ++read;
            }

            Optional<Integer> nonPrintable = Optional.empty();

            for(int i = 0; i < read; ++cpIndex) {
               int codePoint = Character.codePointAt(this.buffer, i);
               this.codePointsWindow[cpIndex] = codePoint;
               if (isPrintable(codePoint)) {
                  i += Character.charCount(codePoint);
               } else {
                  nonPrintable = Optional.of(codePoint);
                  i = read;
               }
            }

            this.dataLength = cpIndex;
            this.pointer = 0;
            if (nonPrintable.isPresent()) {
               throw new ReaderException(this.name, this.index + cpIndex - 1, (Integer)nonPrintable.get(), "special characters are not allowed");
            }
         } else {
            this.eof = true;
         }

      } catch (IOException ioe) {
         throw new YamlEngineException(ioe);
      }
   }

   public int getColumn() {
      return this.column;
   }

   private void moveIndices(int length) {
      this.index += length;
      this.documentIndex += length;
   }

   public int getDocumentIndex() {
      return this.documentIndex;
   }

   public void resetDocumentIndex() {
      this.documentIndex = 0;
   }

   public int getIndex() {
      return this.index;
   }

   public int getLine() {
      return this.line;
   }
}
