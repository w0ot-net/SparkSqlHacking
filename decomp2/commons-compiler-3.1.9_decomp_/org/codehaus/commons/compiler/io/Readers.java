package org.codehaus.commons.compiler.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import org.codehaus.commons.compiler.util.LineAndColumnTracker;
import org.codehaus.commons.nullanalysis.NotNullByDefault;
import org.codehaus.commons.nullanalysis.Nullable;

public final class Readers {
   public static final Reader EMPTY_READER = new StringReader("");

   private Readers() {
   }

   public static Reader onFirstChar(Reader in, final Runnable runnable) {
      return new FilterReader(in) {
         private boolean hadChars;

         public int read() throws IOException {
            this.aboutToRead();
            return super.read();
         }

         @NotNullByDefault(false)
         public int read(char[] cbuf, int off, int len) throws IOException {
            this.aboutToRead();
            return super.read(cbuf, off, len);
         }

         public long skip(long n) throws IOException {
            this.aboutToRead();
            return super.skip(n);
         }

         private void aboutToRead() {
            if (!this.hadChars) {
               runnable.run();
               this.hadChars = true;
            }

         }
      };
   }

   public static Reader trackLineAndColumn(Reader in, final LineAndColumnTracker tracker) {
      return new FilterReader(in) {
         public int read() throws IOException {
            int c = super.read();
            if (c >= 0) {
               tracker.consume((char)c);
            }

            return c;
         }

         @NotNullByDefault(false)
         public int read(char[] cbuf, int off, int len) throws IOException {
            if (len <= 0) {
               return 0;
            } else {
               int c = this.read();
               if (c < 0) {
                  return -1;
               } else {
                  cbuf[off] = (char)c;
                  return 1;
               }
            }
         }

         public long skip(long n) throws IOException {
            if (n <= 0L) {
               return 0L;
            } else {
               int c = this.read();
               return c < 0 ? 0L : 1L;
            }
         }

         public boolean markSupported() {
            return false;
         }

         public void mark(int readAheadLimit) throws IOException {
            throw new IOException();
         }

         public void reset() throws IOException {
            throw new IOException();
         }
      };
   }

   public static Reader concat(Reader... delegates) {
      return concat((Iterable)Arrays.asList(delegates));
   }

   public static Reader concat(final Iterable delegates) {
      return new Reader() {
         private final Iterator delegateIterator = delegates.iterator();
         private Reader currentDelegate;

         {
            this.currentDelegate = Readers.EMPTY_READER;
         }

         public void close() throws IOException {
            for(Reader delegate : delegates) {
               delegate.close();
            }

         }

         public int read() throws IOException {
            while(true) {
               int result = this.currentDelegate.read();
               if (result != -1) {
                  return result;
               }

               if (!this.delegateIterator.hasNext()) {
                  return -1;
               }

               this.currentDelegate = (Reader)this.delegateIterator.next();
            }
         }

         public long skip(long n) throws IOException {
            while(true) {
               long result = this.currentDelegate.skip(n);
               if (result != -1L) {
                  return result;
               }

               if (!this.delegateIterator.hasNext()) {
                  return 0L;
               }

               this.currentDelegate = (Reader)this.delegateIterator.next();
            }
         }

         public int read(@Nullable char[] cbuf, int off, int len) throws IOException {
            while(true) {
               int result = this.currentDelegate.read(cbuf, off, len);
               if ((long)result != -1L) {
                  return result;
               }

               if (!this.delegateIterator.hasNext()) {
                  return -1;
               }

               this.currentDelegate = (Reader)this.delegateIterator.next();
            }
         }
      };
   }

   public static Reader teeReader(Reader in, final Writer out, final boolean closeWriterOnEoi) {
      return new FilterReader(in) {
         public void close() throws IOException {
            this.in.close();
            out.close();
         }

         public int read() throws IOException {
            int c = this.in.read();
            if (c == -1) {
               if (closeWriterOnEoi) {
                  out.close();
               } else {
                  out.flush();
               }
            } else {
               out.write(c);
            }

            return c;
         }

         public int read(@Nullable char[] cbuf, int off, int len) throws IOException {
            int bytesRead = this.in.read(cbuf, off, len);
            if (bytesRead == -1) {
               if (closeWriterOnEoi) {
                  out.close();
               } else {
                  out.flush();
               }
            } else {
               out.write(cbuf, off, bytesRead);
            }

            return bytesRead;
         }
      };
   }

   public static String readAll(Reader in) throws IOException {
      StringWriter sw = new StringWriter();
      copy(in, sw);
      return sw.toString();
   }

   public static void copy(Reader in, Writer out) throws IOException {
      char[] buffer = new char[8192];

      while(true) {
         int n = in.read(buffer);
         if (n == -1) {
            return;
         }

         out.write(buffer, 0, n);
      }
   }
}
