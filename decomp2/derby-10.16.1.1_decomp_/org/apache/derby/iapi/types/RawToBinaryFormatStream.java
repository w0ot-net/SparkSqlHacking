package org.apache.derby.iapi.types;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.iapi.services.io.DerbyIOException;
import org.apache.derby.iapi.services.io.LimitInputStream;
import org.apache.derby.shared.common.i18n.MessageService;

public final class RawToBinaryFormatStream extends LimitInputStream {
   private int encodedOffset;
   private byte[] encodedLength;
   private boolean eof = false;
   private final int length;
   private final int maximumLength;
   private final String typeName;

   public RawToBinaryFormatStream(InputStream var1, int var2) {
      super(var1);
      if (var2 < 0) {
         throw new IllegalArgumentException("Stream length cannot be negative: " + var2);
      } else {
         this.length = var2;
         this.maximumLength = -1;
         this.typeName = null;
         this.setLimit(var2);
         if (var2 <= 31) {
            this.encodedLength = new byte[1];
            this.encodedLength[0] = (byte)(128 | var2 & 255);
         } else if (var2 <= 65535) {
            this.encodedLength = new byte[3];
            this.encodedLength[0] = -96;
            this.encodedLength[1] = (byte)(var2 >> 8);
            this.encodedLength[2] = (byte)var2;
         } else {
            this.encodedLength = new byte[5];
            this.encodedLength[0] = -64;
            this.encodedLength[1] = (byte)(var2 >> 24);
            this.encodedLength[2] = (byte)(var2 >> 16);
            this.encodedLength[3] = (byte)(var2 >> 8);
            this.encodedLength[4] = (byte)var2;
         }

      }
   }

   public RawToBinaryFormatStream(InputStream var1, int var2, String var3) {
      super(var1);
      if (var2 < 0) {
         throw new IllegalArgumentException("Maximum length for a capped stream cannot be negative: " + var2);
      } else if (var3 == null) {
         throw new IllegalArgumentException("Type name cannot be null");
      } else {
         this.length = -1;
         this.maximumLength = var2;
         this.typeName = var3;
         this.encodedLength = new byte[4];
         this.setLimit(var2);
      }
   }

   public int read() throws IOException {
      if (this.eof) {
         throw new EOFException(MessageService.getTextMessage("XJ085.S", new Object[0]));
      } else if (this.encodedOffset < this.encodedLength.length) {
         return this.encodedLength[this.encodedOffset++] & 255;
      } else {
         int var1 = super.read();
         if (var1 == -1) {
            this.checkSufficientData();
         }

         return var1;
      }
   }

   private void checkSufficientData() throws IOException {
      this.eof = true;
      if (this.limitInPlace) {
         int var1 = this.clearLimit();
         if (this.length > -1 && var1 > 0) {
            throw new DerbyIOException(MessageService.getTextMessage("XJ023.S", new Object[0]), "XJ023.S");
         } else {
            if (var1 == 0) {
               int var2;
               try {
                  var2 = super.read();
               } catch (IOException var4) {
                  var2 = -1;
               }

               if (var2 != -1) {
                  if (this.length > -1) {
                     throw new DerbyIOException(MessageService.getTextMessage("XJ023.S", new Object[0]), "XJ023.S");
                  }

                  throw new DerbyIOException(MessageService.getTextMessage("22001", new Object[]{this.typeName, "XXXX", String.valueOf(this.maximumLength)}), "22001");
               }
            }

         }
      }
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      if (this.eof) {
         throw new EOFException(MessageService.getTextMessage("XJ085.S", new Object[0]));
      } else {
         int var4 = this.encodedLength.length - this.encodedOffset;
         if (var4 != 0) {
            if (var3 < var4) {
               var4 = var3;
            }

            System.arraycopy(this.encodedLength, this.encodedOffset, var1, var2, var4);
            this.encodedOffset += var4;
            var2 += var4;
            var3 -= var4;
            if (var3 == 0) {
               return var4;
            }
         }

         int var5 = super.read(var1, var2, var3);
         if (var5 < 0) {
            if (var4 != 0) {
               return var4;
            } else {
               this.checkSufficientData();
               return var5;
            }
         } else {
            return var4 + var5;
         }
      }
   }
}
