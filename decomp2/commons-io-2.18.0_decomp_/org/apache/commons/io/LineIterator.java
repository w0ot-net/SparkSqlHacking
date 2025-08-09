package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LineIterator implements Iterator, Closeable {
   private final BufferedReader bufferedReader;
   private String cachedLine;
   private boolean finished;

   /** @deprecated */
   @Deprecated
   public static void closeQuietly(LineIterator iterator) {
      IOUtils.closeQuietly((Closeable)iterator);
   }

   public LineIterator(Reader reader) {
      Objects.requireNonNull(reader, "reader");
      if (reader instanceof BufferedReader) {
         this.bufferedReader = (BufferedReader)reader;
      } else {
         this.bufferedReader = new BufferedReader(reader);
      }

   }

   public void close() throws IOException {
      this.finished = true;
      this.cachedLine = null;
      IOUtils.close((Closeable)this.bufferedReader);
   }

   public boolean hasNext() {
      if (this.cachedLine != null) {
         return true;
      } else if (this.finished) {
         return false;
      } else {
         try {
            String line;
            do {
               line = this.bufferedReader.readLine();
               if (line == null) {
                  this.finished = true;
                  return false;
               }
            } while(!this.isValidLine(line));

            this.cachedLine = line;
            return true;
         } catch (IOException ioe) {
            Objects.requireNonNull(ioe);
            IOUtils.closeQuietly(this, ioe::addSuppressed);
            throw new IllegalStateException(ioe);
         }
      }
   }

   protected boolean isValidLine(String line) {
      return true;
   }

   public String next() {
      return this.nextLine();
   }

   /** @deprecated */
   @Deprecated
   public String nextLine() {
      if (!this.hasNext()) {
         throw new NoSuchElementException("No more lines");
      } else {
         String currentLine = this.cachedLine;
         this.cachedLine = null;
         return currentLine;
      }
   }

   public void remove() {
      throw new UnsupportedOperationException("remove not supported");
   }
}
