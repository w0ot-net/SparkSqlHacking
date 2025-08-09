package org.apache.derby.impl.jdbc;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UTFDataFormatException;
import java.sql.SQLException;
import org.apache.derby.iapi.jdbc.CharacterStreamDescriptor;
import org.apache.derby.iapi.types.PositionedStream;
import org.apache.derby.shared.common.error.StandardException;

public final class UTF8Reader extends Reader {
   private static final String READER_CLOSED = "Reader closed";
   private static final int MAXIMUM_BUFFER_SIZE = 8192;
   private InputStream in;
   private final PositionedStream positionedIn;
   private long rawStreamPos = 0L;
   private long utfCount;
   private long readerCharCount;
   private final char[] buffer;
   private int charactersInBuffer;
   private int readPositionInBuffer;
   private boolean noMoreReads;
   private ConnectionChild parent;
   private final CharacterStreamDescriptor csd;

   public UTF8Reader(CharacterStreamDescriptor var1, ConnectionChild var2, Object var3) throws IOException {
      super(var3);
      this.csd = var1;
      this.positionedIn = var1.isPositionAware() ? var1.getPositionedStream() : null;
      this.parent = var2;
      int var4 = this.calculateBufferSize(var1);
      this.buffer = new char[var4];
      if (var1.isPositionAware()) {
         this.rawStreamPos = this.positionedIn.getPosition();
         if (this.rawStreamPos < var1.getDataOffset()) {
            this.rawStreamPos = var1.getDataOffset();
         }
      } else if (var1.getCurBytePos() < var1.getDataOffset()) {
         var1.getStream().skip(var1.getDataOffset() - var1.getCurBytePos());
      }

      if (var1.isBufferable()) {
         this.in = new BufferedInputStream(var1.getStream(), var4);
      } else {
         this.in = var1.getStream();
      }

      this.utfCount = var1.getDataOffset();
   }

   public int read() throws IOException {
      synchronized(this.lock) {
         if (this.noMoreReads) {
            throw new IOException("Reader closed");
         } else {
            return this.readPositionInBuffer >= this.charactersInBuffer && this.fillBuffer() ? -1 : this.buffer[this.readPositionInBuffer++];
         }
      }
   }

   public int read(char[] var1, int var2, int var3) throws IOException {
      synchronized(this.lock) {
         if (this.noMoreReads) {
            throw new IOException("Reader closed");
         } else if (this.readPositionInBuffer >= this.charactersInBuffer && this.fillBuffer()) {
            return -1;
         } else {
            int var5 = this.charactersInBuffer - this.readPositionInBuffer;
            if (var3 > var5) {
               var3 = var5;
            }

            System.arraycopy(this.buffer, this.readPositionInBuffer, var1, var2, var3);
            this.readPositionInBuffer += var3;
            return var3;
         }
      }
   }

   public long skip(long var1) throws IOException {
      if (var1 < 0L) {
         throw new IllegalArgumentException("Number of characters to skip must be positive: " + var1);
      } else {
         synchronized(this.lock) {
            if (this.noMoreReads) {
               throw new IOException("Reader closed");
            } else if (this.readPositionInBuffer >= this.charactersInBuffer && this.fillBuffer()) {
               return 0L;
            } else {
               int var4 = this.charactersInBuffer - this.readPositionInBuffer;
               if (var1 > (long)var4) {
                  var1 = (long)var4;
               }

               this.readPositionInBuffer = (int)((long)this.readPositionInBuffer + var1);
               return var1;
            }
         }
      }
   }

   public void close() {
      synchronized(this.lock) {
         this.closeIn();
         this.parent = null;
         this.noMoreReads = true;
      }
   }

   public int readInto(StringBuffer var1, int var2) throws IOException {
      synchronized(this.lock) {
         if (this.readPositionInBuffer >= this.charactersInBuffer && this.fillBuffer()) {
            return -1;
         } else {
            int var4 = this.charactersInBuffer - this.readPositionInBuffer;
            if (var2 > var4) {
               var2 = var4;
            }

            var1.append(this.buffer, this.readPositionInBuffer, var2);
            this.readPositionInBuffer += var2;
            return var2;
         }
      }
   }

   int readAsciiInto(byte[] var1, int var2, int var3) throws IOException {
      synchronized(this.lock) {
         if (this.readPositionInBuffer >= this.charactersInBuffer && this.fillBuffer()) {
            return -1;
         } else {
            int var5 = this.charactersInBuffer - this.readPositionInBuffer;
            if (var3 > var5) {
               var3 = var5;
            }

            char[] var6 = this.buffer;

            for(int var7 = 0; var7 < var3; ++var7) {
               char var8 = var6[this.readPositionInBuffer + var7];
               byte var9;
               if (var8 <= 255) {
                  var9 = (byte)var8;
               } else {
                  var9 = 63;
               }

               var1[var2 + var7] = var9;
            }

            this.readPositionInBuffer += var3;
            return var3;
         }
      }
   }

   private void closeIn() {
      if (this.in != null) {
         try {
            this.in.close();
         } catch (IOException var5) {
         } finally {
            this.in = null;
         }
      }

   }

   private IOException utfFormatException(String var1) {
      this.noMoreReads = true;
      this.closeIn();
      return new UTFDataFormatException(var1);
   }

   private boolean fillBuffer() throws IOException {
      if (this.in == null) {
         return true;
      } else {
         this.charactersInBuffer = 0;
         this.readPositionInBuffer = 0;

         try {
            try {
               this.parent.setupContextStack();
               if (this.positionedIn != null) {
                  try {
                     this.positionedIn.reposition(this.rawStreamPos);
                  } catch (StandardException var13) {
                     throw Util.generateCsSQLException(var13);
                  }
               }

               long var1 = this.csd.getByteLength();
               long var3 = this.csd.getMaxCharLength();

               while(true) {
                  int var6;
                  label299: {
                     if (this.charactersInBuffer < this.buffer.length && (this.utfCount < var1 || var1 == 0L) && (var3 == 0L || this.readerCharCount < var3)) {
                        int var5 = this.in.read();
                        if (var5 == -1) {
                           if (var1 != 0L) {
                              throw this.utfFormatException("Reached EOF prematurely, read " + this.utfCount + " out of " + var1 + " bytes");
                           }

                           if (!this.csd.isPositionAware()) {
                              this.closeIn();
                           }
                        } else {
                           switch (var5 >> 4) {
                              case 0:
                              case 1:
                              case 2:
                              case 3:
                              case 4:
                              case 5:
                              case 6:
                              case 7:
                                 ++this.utfCount;
                                 var6 = var5;
                                 break label299;
                              case 8:
                              case 9:
                              case 10:
                              case 11:
                              default:
                                 throw this.utfFormatException("Invalid UTF encoding at byte/char position " + this.utfCount + "/" + this.readerCharCount + ": (int)" + var5);
                              case 12:
                              case 13:
                                 this.utfCount += 2L;
                                 int var18 = this.in.read();
                                 if (var18 == -1) {
                                    throw this.utfFormatException("Reached EOF when reading second byte in a two byte character encoding; byte/char position " + this.utfCount + "/" + this.readerCharCount);
                                 }

                                 if ((var18 & 192) != 128) {
                                    throw this.utfFormatException("Second byte in a two bytecharacter encoding invalid: (int)" + var18 + ", byte/char pos " + this.utfCount + "/" + this.readerCharCount);
                                 }

                                 var6 = (var5 & 31) << 6 | var18 & 63;
                                 break label299;
                              case 14:
                                 this.utfCount += 3L;
                                 int var7 = this.in.read();
                                 int var8 = this.in.read();
                                 if (var7 == -1 || var8 == -1) {
                                    throw this.utfFormatException("Reached EOF when reading second/third byte in a three byte character encoding; byte/char position " + this.utfCount + "/" + this.readerCharCount);
                                 }

                                 if (var5 != 224 || var7 != 0 || var8 != 0) {
                                    if ((var7 & 192) != 128 || (var8 & 192) != 128) {
                                       throw this.utfFormatException("Second/third byte in a three byte character encoding invalid: (int)" + var7 + "/" + var8 + ", byte/char pos " + this.utfCount + "/" + this.readerCharCount);
                                    }

                                    var6 = (var5 & 15) << 12 | (var7 & 63) << 6 | (var8 & 63) << 0;
                                    break label299;
                                 }

                                 if (var1 != 0L) {
                                    throw this.utfFormatException("Internal error: Derby-specific EOF marker read");
                                 }

                                 if (!this.csd.isPositionAware()) {
                                    this.closeIn();
                                 }
                           }
                        }
                     }

                     if (var1 != 0L && this.utfCount > var1) {
                        throw this.utfFormatException("Incorrect encoded length in stream, expected " + var1 + ", have " + this.utfCount + " bytes");
                     }

                     if (this.charactersInBuffer == 0) {
                        if (!this.csd.isPositionAware()) {
                           this.closeIn();
                        }

                        boolean var17 = true;
                        return var17;
                     }

                     if (this.positionedIn != null) {
                        this.rawStreamPos = this.positionedIn.getPosition();
                     }

                     boolean var16 = false;
                     return var16;
                  }

                  this.buffer[this.charactersInBuffer++] = (char)var6;
                  ++this.readerCharCount;
               }
            } finally {
               ConnectionChild.restoreIntrFlagIfSeen(true, this.parent.getEmbedConnection());
               this.parent.restoreContextStack();
            }
         } catch (SQLException var15) {
            throw Util.newIOException(var15);
         }
      }
   }

   private void resetUTF8Reader() throws IOException, StandardException {
      this.positionedIn.reposition(this.csd.getDataOffset());
      this.utfCount = this.rawStreamPos = this.positionedIn.getPosition();
      if (this.csd.isBufferable()) {
         this.in = new BufferedInputStream(this.csd.getStream(), this.buffer.length);
      }

      this.readerCharCount = 0L;
      this.charactersInBuffer = this.readPositionInBuffer = 0;
   }

   void reposition(long var1) throws IOException, StandardException {
      if (var1 <= this.readerCharCount - (long)this.charactersInBuffer) {
         this.resetUTF8Reader();
      }

      long var3 = this.readerCharCount - (long)this.charactersInBuffer + (long)this.readPositionInBuffer;
      long var5 = var1 - 1L - var3;
      if (var5 <= 0L) {
         this.readPositionInBuffer = (int)((long)this.readPositionInBuffer + var5);
      } else {
         this.persistentSkip(var5);
      }

   }

   private final int calculateBufferSize(CharacterStreamDescriptor var1) {
      int var2 = 8192;
      long var3 = var1.getCharLength();
      long var5 = var1.getMaxCharLength();
      if (var3 < 1L) {
         var3 = var1.getByteLength();
      }

      if (var3 > 0L && var3 < (long)var2) {
         var2 = (int)var3;
      }

      if (var5 > 0L && var5 < (long)var2) {
         var2 = (int)var5;
      }

      return var2;
   }

   private final void persistentSkip(long var1) throws IOException {
      long var5;
      for(long var3 = var1; var3 > 0L; var3 -= var5) {
         var5 = this.skip(var3);
         if (var5 == 0L) {
            throw new EOFException();
         }
      }

   }
}
