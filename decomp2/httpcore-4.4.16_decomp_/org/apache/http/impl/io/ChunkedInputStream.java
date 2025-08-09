package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.TruncatedChunkException;
import org.apache.http.config.MessageConstraints;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

public class ChunkedInputStream extends InputStream {
   private static final int CHUNK_LEN = 1;
   private static final int CHUNK_DATA = 2;
   private static final int CHUNK_CRLF = 3;
   private static final int CHUNK_INVALID = Integer.MAX_VALUE;
   private static final int BUFFER_SIZE = 2048;
   private final SessionInputBuffer in;
   private final CharArrayBuffer buffer;
   private final MessageConstraints constraints;
   private int state;
   private long chunkSize;
   private long pos;
   private boolean eof;
   private boolean closed;
   private Header[] footers;

   public ChunkedInputStream(SessionInputBuffer in, MessageConstraints constraints) {
      this.eof = false;
      this.closed = false;
      this.footers = new Header[0];
      this.in = (SessionInputBuffer)Args.notNull(in, "Session input buffer");
      this.pos = 0L;
      this.buffer = new CharArrayBuffer(16);
      this.constraints = constraints != null ? constraints : MessageConstraints.DEFAULT;
      this.state = 1;
   }

   public ChunkedInputStream(SessionInputBuffer in) {
      this(in, (MessageConstraints)null);
   }

   public int available() throws IOException {
      if (this.in instanceof BufferInfo) {
         int len = ((BufferInfo)this.in).length();
         return (int)Math.min((long)len, this.chunkSize - this.pos);
      } else {
         return 0;
      }
   }

   public int read() throws IOException {
      if (this.closed) {
         throw new IOException("Attempted read from closed stream.");
      } else if (this.eof) {
         return -1;
      } else {
         if (this.state != 2) {
            this.nextChunk();
            if (this.eof) {
               return -1;
            }
         }

         int b = this.in.read();
         if (b != -1) {
            ++this.pos;
            if (this.pos >= this.chunkSize) {
               this.state = 3;
            }
         }

         return b;
      }
   }

   public int read(byte[] b, int off, int len) throws IOException {
      if (this.closed) {
         throw new IOException("Attempted read from closed stream.");
      } else if (this.eof) {
         return -1;
      } else {
         if (this.state != 2) {
            this.nextChunk();
            if (this.eof) {
               return -1;
            }
         }

         int readLen = this.in.read(b, off, (int)Math.min((long)len, this.chunkSize - this.pos));
         if (readLen != -1) {
            this.pos += (long)readLen;
            if (this.pos >= this.chunkSize) {
               this.state = 3;
            }

            return readLen;
         } else {
            this.eof = true;
            throw new TruncatedChunkException("Truncated chunk (expected size: %,d; actual size: %,d)", new Object[]{this.chunkSize, this.pos});
         }
      }
   }

   public int read(byte[] b) throws IOException {
      return this.read(b, 0, b.length);
   }

   private void nextChunk() throws IOException {
      if (this.state == Integer.MAX_VALUE) {
         throw new MalformedChunkCodingException("Corrupt data stream");
      } else {
         try {
            this.chunkSize = this.getChunkSize();
            if (this.chunkSize < 0L) {
               throw new MalformedChunkCodingException("Negative chunk size");
            } else {
               this.state = 2;
               this.pos = 0L;
               if (this.chunkSize == 0L) {
                  this.eof = true;
                  this.parseTrailerHeaders();
               }

            }
         } catch (MalformedChunkCodingException ex) {
            this.state = Integer.MAX_VALUE;
            throw ex;
         }
      }
   }

   private long getChunkSize() throws IOException {
      int st = this.state;
      switch (st) {
         case 3:
            this.buffer.clear();
            int bytesRead1 = this.in.readLine(this.buffer);
            if (bytesRead1 == -1) {
               throw new MalformedChunkCodingException("CRLF expected at end of chunk");
            } else if (!this.buffer.isEmpty()) {
               throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
            } else {
               this.state = 1;
            }
         case 1:
            this.buffer.clear();
            int bytesRead2 = this.in.readLine(this.buffer);
            if (bytesRead2 == -1) {
               throw new ConnectionClosedException("Premature end of chunk coded message body: closing chunk expected");
            } else {
               int separator = this.buffer.indexOf(59);
               if (separator < 0) {
                  separator = this.buffer.length();
               }

               String s = this.buffer.substringTrimmed(0, separator);

               try {
                  return Long.parseLong(s, 16);
               } catch (NumberFormatException var7) {
                  throw new MalformedChunkCodingException("Bad chunk header: " + s);
               }
            }
         default:
            throw new IllegalStateException("Inconsistent codec state");
      }
   }

   private void parseTrailerHeaders() throws IOException {
      try {
         this.footers = AbstractMessageParser.parseHeaders(this.in, this.constraints.getMaxHeaderCount(), this.constraints.getMaxLineLength(), (LineParser)null);
      } catch (HttpException ex) {
         IOException ioe = new MalformedChunkCodingException("Invalid footer: " + ex.getMessage());
         ioe.initCause(ex);
         throw ioe;
      }
   }

   public void close() throws IOException {
      if (!this.closed) {
         try {
            if (!this.eof && this.state != Integer.MAX_VALUE) {
               byte[] buff = new byte[2048];

               while(this.read(buff) >= 0) {
               }
            }
         } finally {
            this.eof = true;
            this.closed = true;
         }
      }

   }

   public Header[] getFooters() {
      return (Header[])this.footers.clone();
   }
}
