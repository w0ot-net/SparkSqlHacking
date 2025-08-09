package com.univocity.parsers.common.input;

import java.io.IOException;
import java.io.Reader;

public class DefaultCharInputReader extends AbstractCharInputReader {
   private Reader reader;
   private boolean unwrapping = false;

   public DefaultCharInputReader(char normalizedLineSeparator, int bufferSize, int whitespaceRangeStart, boolean closeOnStop) {
      super(normalizedLineSeparator, whitespaceRangeStart, closeOnStop);
      super.buffer = new char[bufferSize];
   }

   public DefaultCharInputReader(char[] lineSeparator, char normalizedLineSeparator, int bufferSize, int whitespaceRangeStart, boolean closeOnStop) {
      super(lineSeparator, normalizedLineSeparator, whitespaceRangeStart, closeOnStop);
      super.buffer = new char[bufferSize];
   }

   public void stop() {
      try {
         if (!this.unwrapping && this.closeOnStop && this.reader != null) {
            this.reader.close();
         }

      } catch (IOException e) {
         throw new IllegalStateException("Error closing input", e);
      }
   }

   protected void setReader(Reader reader) {
      this.reader = reader;
      this.unwrapping = false;
   }

   public void reloadBuffer() {
      try {
         super.length = this.reader.read(this.buffer, 0, this.buffer.length);
      } catch (IOException e) {
         throw new IllegalStateException("Error reading from input", e);
      } catch (BomInput.BytesProcessedNotification notification) {
         this.unwrapping = true;
         this.unwrapInputStream(notification);
      }

   }
}
