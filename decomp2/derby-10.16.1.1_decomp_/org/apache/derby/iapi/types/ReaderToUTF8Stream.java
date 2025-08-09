package org.apache.derby.iapi.types;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.apache.derby.iapi.services.io.DerbyIOException;
import org.apache.derby.iapi.services.io.LimitReader;
import org.apache.derby.shared.common.i18n.MessageService;

public final class ReaderToUTF8Stream extends InputStream {
   private LimitReader reader;
   private static final int FIRST_READ = Integer.MIN_VALUE;
   private static final int READ_BUFFER_RESERVATION = 6;
   private static final int MARK_UNSET_OR_EXCEEDED = -1;
   private byte[] buffer;
   private int boff;
   private int blen;
   private int mark;
   private int readAheadLimit;
   private boolean eof;
   private boolean multipleBuffer;
   private final StreamHeaderGenerator hdrGen;
   private int headerLength;
   private final int charsToTruncate;
   private static final char SPACE = ' ';
   private final int valueLength;
   private final String typeName;
   private int charCount;

   public ReaderToUTF8Stream(Reader var1, int var2, int var3, String var4, StreamHeaderGenerator var5) {
      this.blen = -1;
      this.mark = -1;
      this.reader = new LimitReader(var1);
      this.charsToTruncate = var3;
      this.valueLength = var2;
      this.typeName = var4;
      this.hdrGen = var5;
      int var6 = Math.abs(var2);
      this.reader.setLimit(var6);
      int var7 = 32768;
      if (var6 < var7 / 3) {
         var7 = this.hdrGen.getMaxHeaderLength() + Math.max(6, var6 * 3 + 3);
      }

      this.buffer = new byte[var7];
   }

   public ReaderToUTF8Stream(Reader var1, int var2, String var3, StreamHeaderGenerator var4) {
      this(var1, -1 * var2, 0, var3, var4);
      if (var2 < 0) {
         throw new IllegalArgumentException("Maximum length for a capped stream cannot be negative: " + var2);
      }
   }

   public int read() throws IOException {
      if (this.buffer == null) {
         throw new EOFException(MessageService.getTextMessage("XJ085.S", new Object[0]));
      } else {
         if (this.blen < 0) {
            this.fillBuffer(Integer.MIN_VALUE);
         }

         while(this.boff == this.blen) {
            if (this.eof) {
               this.close();
               return -1;
            }

            this.fillBuffer(0);
         }

         return this.buffer[this.boff++] & 255;
      }
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      if (this.buffer == null) {
         throw new EOFException(MessageService.getTextMessage("XJ085.S", new Object[0]));
      } else {
         if (this.blen < 0) {
            this.fillBuffer(Integer.MIN_VALUE);
         }

         int var4 = 0;

         while(var3 > 0) {
            int var5 = this.blen - this.boff;
            if (var5 == 0) {
               if (this.eof) {
                  if (var4 > 0) {
                     return var4;
                  }

                  this.close();
                  return -1;
               }

               this.fillBuffer(0);
            } else {
               if (var3 < var5) {
                  var5 = var3;
               }

               System.arraycopy(this.buffer, this.boff, var1, var2, var5);
               this.boff += var5;
               var3 -= var5;
               var4 += var5;
               var2 += var5;
            }
         }

         return var4;
      }
   }

   private void fillBuffer(int var1) throws IOException {
      if (var1 == Integer.MIN_VALUE) {
         if (this.hdrGen.expectsCharCount() && this.valueLength >= 0) {
            this.headerLength = this.hdrGen.generateInto(this.buffer, 0, (long)this.valueLength);
         } else {
            this.headerLength = this.hdrGen.generateInto(this.buffer, 0, -1L);
         }

         var1 = this.headerLength;
      }

      int var2 = var1;
      this.boff = 0;
      if (var1 == 0) {
         this.multipleBuffer = true;
      }

      if (this.mark >= 0) {
         int var3 = this.readAheadLimit + 6;
         if (this.mark + var3 > this.buffer.length) {
            if (this.blen != -1) {
               this.boff = var2 = this.blen - this.mark;
            }

            byte[] var4 = this.buffer;
            if (var3 > this.buffer.length) {
               this.buffer = new byte[var3];
            }

            System.arraycopy(var4, this.mark, this.buffer, 0, var2);
            this.mark = 0;
         } else if (this.blen != -1) {
            this.mark = -1;
         }
      }

      while(var2 <= this.buffer.length - 6) {
         int var8 = this.reader.read();
         if (var8 < 0) {
            this.eof = true;
            break;
         }

         ++this.charCount;
         if (var8 >= 1 && var8 <= 127) {
            this.buffer[var2++] = (byte)var8;
         } else if (var8 > 2047) {
            this.buffer[var2++] = (byte)(224 | var8 >> 12 & 15);
            this.buffer[var2++] = (byte)(128 | var8 >> 6 & 63);
            this.buffer[var2++] = (byte)(128 | var8 >> 0 & 63);
         } else {
            this.buffer[var2++] = (byte)(192 | var8 >> 6 & 31);
            this.buffer[var2++] = (byte)(128 | var8 >> 0 & 63);
         }
      }

      this.blen = var2;
      if (this.eof) {
         this.checkSufficientData();
      }

   }

   private void checkSufficientData() throws IOException {
      if (this.charsToTruncate > 0) {
         this.reader.setLimit(this.charsToTruncate);
         this.truncate();
      }

      int var1 = this.reader.clearLimit();
      if (var1 > 0 && this.valueLength > 0) {
         throw new DerbyIOException(MessageService.getTextMessage("XJ023.S", new Object[0]), "XJ023.S");
      } else {
         if (var1 == 0 && this.reader.read() >= 0) {
            if (this.valueLength > -1) {
               throw new DerbyIOException(MessageService.getTextMessage("XJ023.S", new Object[0]), "XJ023.S");
            }

            if (!this.canTruncate()) {
               throw new DerbyIOException(MessageService.getTextMessage("22001", new Object[]{this.typeName, "<stream-value>", String.valueOf(Math.abs(this.valueLength))}), "22001");
            }

            this.truncate();
         }

         if (!this.multipleBuffer) {
            int var2 = -1;
            if (this.hdrGen.expectsCharCount()) {
               var2 = this.charCount;
            } else {
               var2 = this.blen - this.headerLength;
            }

            int var3 = this.hdrGen.generateInto(this.buffer, 0, (long)var2);
            if (var3 != this.headerLength) {
               throw new IOException("Data corruption detected; user data overwritten by header bytes");
            }

            this.blen += this.hdrGen.writeEOF(this.buffer, this.blen, (long)var2);
         } else {
            this.blen += this.hdrGen.writeEOF(this.buffer, this.blen, (long)Math.max(this.valueLength, -1));
         }

      }
   }

   private boolean canTruncate() {
      if (this.typeName.equals("CLOB")) {
         return true;
      } else if (this.typeName.equals("VARCHAR")) {
         return true;
      } else {
         return this.typeName.equals("CHAR");
      }
   }

   private void truncate() throws IOException {
      int var1 = 0;

      do {
         var1 = this.reader.read();
         if (var1 < 0) {
            return;
         }
      } while(var1 == 32);

      throw new DerbyIOException(MessageService.getTextMessage("22001", new Object[]{this.typeName, "<stream-value>", String.valueOf(Math.abs(this.valueLength))}), "22001");
   }

   public void close() {
      this.buffer = null;
   }

   public final int available() {
      int var1 = this.reader.getLimit();
      return this.buffer.length > var1 ? var1 : this.buffer.length;
   }

   public void mark(int var1) {
      if (var1 > 0) {
         this.readAheadLimit = var1;
         this.mark = this.boff;
      } else {
         this.readAheadLimit = this.mark = -1;
      }

   }

   public void reset() throws IOException {
      if (this.buffer == null) {
         throw new EOFException(MessageService.getTextMessage("XJ085.S", new Object[0]));
      } else if (this.mark == -1) {
         throw new IOException(MessageService.getTextMessage("I027", new Object[0]));
      } else {
         this.boff = this.mark;
         this.readAheadLimit = this.mark = -1;
      }
   }

   public boolean markSupported() {
      return true;
   }
}
