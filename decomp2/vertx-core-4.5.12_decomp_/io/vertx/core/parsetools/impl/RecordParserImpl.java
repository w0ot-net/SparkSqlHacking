package io.vertx.core.parsetools.impl;

import io.netty.buffer.Unpooled;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.parsetools.RecordParser;
import io.vertx.core.streams.ReadStream;
import java.util.Objects;

public class RecordParserImpl implements RecordParser {
   private static final Buffer EMPTY_BUFFER;
   private Buffer buff;
   private int pos;
   private int start;
   private int delimPos;
   private boolean delimited;
   private byte[] delim;
   private int recordSize;
   private int maxRecordSize;
   private long demand;
   private Handler eventHandler;
   private Handler endHandler;
   private Handler exceptionHandler;
   private boolean parsing;
   private boolean streamEnded;
   private final ReadStream stream;

   private RecordParserImpl(ReadStream stream) {
      this.buff = EMPTY_BUFFER;
      this.demand = Long.MAX_VALUE;
      this.stream = stream;
   }

   public void setOutput(Handler output) {
      Objects.requireNonNull(output, "output");
      this.eventHandler = output;
   }

   public static Buffer latin1StringToBytes(String str) {
      byte[] bytes = new byte[str.length()];

      for(int i = 0; i < str.length(); ++i) {
         char c = str.charAt(i);
         bytes[i] = (byte)(c & 255);
      }

      return Buffer.buffer(bytes);
   }

   public static RecordParser newDelimited(String delim, ReadStream stream, Handler output) {
      return newDelimited(latin1StringToBytes(delim), stream, output);
   }

   public static RecordParser newDelimited(Buffer delim, ReadStream stream, Handler output) {
      RecordParserImpl ls = new RecordParserImpl(stream);
      ls.handler(output);
      ls.delimitedMode(delim);
      return ls;
   }

   public static RecordParser newFixed(int size, ReadStream stream, Handler output) {
      Arguments.require(size > 0, "Size must be > 0");
      RecordParserImpl ls = new RecordParserImpl(stream);
      ls.handler(output);
      ls.fixedSizeMode(size);
      return ls;
   }

   public void delimitedMode(String delim) {
      this.delimitedMode(latin1StringToBytes(delim));
   }

   public void delimitedMode(Buffer delim) {
      Objects.requireNonNull(delim, "delim");
      this.delimited = true;
      this.delim = delim.getBytes();
      this.delimPos = 0;
   }

   public void fixedSizeMode(int size) {
      Arguments.require(size > 0, "Size must be > 0");
      this.delimited = false;
      this.recordSize = size;
   }

   public RecordParser maxRecordSize(int size) {
      Arguments.require(size > 0, "Size must be > 0");
      this.maxRecordSize = size;
      return this;
   }

   private void handleParsing() {
      if (!this.parsing) {
         this.parsing = true;

         try {
            while(true) {
               if (this.demand <= 0L) {
                  ReadStream<Buffer> s = this.stream;
                  if (s != null) {
                     s.pause();
                  }
                  break;
               }

               int next;
               if (this.delimited) {
                  next = this.parseDelimited();
               } else {
                  next = this.parseFixed();
               }

               if (next == -1) {
                  if (!this.streamEnded) {
                     ReadStream<Buffer> s = this.stream;
                     if (s != null) {
                        s.resume();
                     }

                     if (this.streamEnded) {
                        continue;
                     }
                     break;
                  }

                  if (this.buff.length() == 0) {
                     break;
                  }

                  next = this.buff.length();
               }

               if (this.demand != Long.MAX_VALUE) {
                  --this.demand;
               }

               Buffer event = this.buff.getBuffer(this.start, next);
               this.start = this.pos;
               Handler<Buffer> handler = this.eventHandler;
               if (handler != null) {
                  handler.handle(event);
               }

               if (this.streamEnded) {
                  break;
               }
            }

            int len = this.buff.length();
            if (this.start == len) {
               this.buff = EMPTY_BUFFER;
            } else if (this.start > 0) {
               this.buff = this.buff.getBuffer(this.start, len);
            }

            this.pos -= this.start;
            this.start = 0;
            if (this.streamEnded) {
               this.end();
            }
         } finally {
            this.parsing = false;
         }

      }
   }

   private int parseDelimited() {
      for(int len = this.buff.length(); this.pos < len; ++this.pos) {
         if (this.buff.getByte(this.pos) == this.delim[this.delimPos]) {
            ++this.delimPos;
            if (this.delimPos == this.delim.length) {
               ++this.pos;
               this.delimPos = 0;
               return this.pos - this.delim.length;
            }
         } else if (this.delimPos > 0) {
            this.pos -= this.delimPos;
            this.delimPos = 0;
         }
      }

      return -1;
   }

   private int parseFixed() {
      int len = this.buff.length();
      if (len - this.start >= this.recordSize) {
         int end = this.start + this.recordSize;
         this.pos = end;
         return end;
      } else {
         return -1;
      }
   }

   public void handle(Buffer buffer) {
      if (buffer.length() != 0) {
         if (this.buff == EMPTY_BUFFER) {
            this.buff = buffer.getBuffer(0, buffer.length());
         } else {
            this.buff.appendBuffer(buffer);
         }
      }

      this.handleParsing();
      if (this.buff != null && this.maxRecordSize > 0 && this.buff.length() > this.maxRecordSize) {
         IllegalStateException ex = new IllegalStateException("The current record is too long");
         if (this.exceptionHandler == null) {
            throw ex;
         }

         this.exceptionHandler.handle(ex);
      }

   }

   private void end() {
      Handler<Void> handler = this.endHandler;
      if (handler != null) {
         handler.handle((Object)null);
      }

   }

   public RecordParser exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   public RecordParser handler(Handler handler) {
      this.eventHandler = handler;
      if (this.stream != null) {
         if (handler != null) {
            this.stream.endHandler((v) -> {
               this.streamEnded = true;
               this.handleParsing();
            });
            this.stream.exceptionHandler((err) -> {
               if (this.exceptionHandler != null) {
                  this.exceptionHandler.handle(err);
               }

            });
            this.stream.handler(this);
         } else {
            this.stream.handler((Handler)null);
            this.stream.endHandler((Handler)null);
            this.stream.exceptionHandler((Handler)null);
         }
      }

      return this;
   }

   public RecordParser pause() {
      this.demand = 0L;
      return this;
   }

   public RecordParser fetch(long amount) {
      Arguments.require(amount > 0L, "Fetch amount must be > 0");
      this.demand += amount;
      if (this.demand < 0L) {
         this.demand = Long.MAX_VALUE;
      }

      this.handleParsing();
      return this;
   }

   public RecordParser resume() {
      return this.fetch(Long.MAX_VALUE);
   }

   public RecordParser endHandler(Handler handler) {
      this.endHandler = handler;
      return this;
   }

   static {
      EMPTY_BUFFER = Buffer.buffer(Unpooled.EMPTY_BUFFER);
   }
}
