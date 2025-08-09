package org.sparkproject.jetty.http;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.component.Destroyable;
import org.sparkproject.jetty.util.compression.CompressionPool;
import org.sparkproject.jetty.util.compression.InflaterPool;

public class GZIPContentDecoder implements Destroyable {
   private static final long UINT_MAX = 4294967295L;
   private final List _inflateds;
   private final ByteBufferPool _pool;
   private final int _bufferSize;
   private final boolean _useDirectBuffers;
   private CompressionPool.Entry _inflaterEntry;
   private Inflater _inflater;
   private State _state;
   private int _size;
   private long _value;
   private byte _flags;
   private ByteBuffer _inflated;

   public GZIPContentDecoder() {
      this((ByteBufferPool)null, 2048);
   }

   public GZIPContentDecoder(int bufferSize) {
      this((ByteBufferPool)null, bufferSize);
   }

   public GZIPContentDecoder(ByteBufferPool pool, int bufferSize) {
      this(new InflaterPool(0, true), pool, bufferSize);
   }

   public GZIPContentDecoder(ByteBufferPool pool, int bufferSize, boolean useDirectBuffers) {
      this(new InflaterPool(0, true), pool, bufferSize, useDirectBuffers);
   }

   public GZIPContentDecoder(InflaterPool inflaterPool, ByteBufferPool pool, int bufferSize) {
      this(inflaterPool, pool, bufferSize, false);
   }

   public GZIPContentDecoder(InflaterPool inflaterPool, ByteBufferPool pool, int bufferSize, boolean useDirectBuffers) {
      this._inflateds = new ArrayList();
      this._inflaterEntry = inflaterPool.acquire();
      this._inflater = (Inflater)this._inflaterEntry.get();
      this._bufferSize = bufferSize;
      this._pool = pool;
      this._useDirectBuffers = useDirectBuffers;
      this.reset();
   }

   public ByteBuffer decode(ByteBuffer compressed) {
      this.decodeChunks(compressed);
      if (this._inflateds.isEmpty()) {
         if (!BufferUtil.isEmpty(this._inflated) && this._state != GZIPContentDecoder.State.CRC && this._state != GZIPContentDecoder.State.ISIZE) {
            ByteBuffer result = this._inflated;
            this._inflated = null;
            return result;
         } else {
            return BufferUtil.EMPTY_BUFFER;
         }
      } else {
         this._inflateds.add(this._inflated);
         this._inflated = null;
         int length = this._inflateds.stream().mapToInt(Buffer::remaining).sum();
         ByteBuffer result = this.acquire(length);

         for(ByteBuffer buffer : this._inflateds) {
            BufferUtil.append(result, buffer);
            this.release(buffer);
         }

         this._inflateds.clear();
         return result;
      }
   }

   protected boolean decodedChunk(ByteBuffer chunk) {
      if (this._inflated == null) {
         this._inflated = chunk;
      } else if (BufferUtil.space(this._inflated) >= chunk.remaining()) {
         BufferUtil.append(this._inflated, chunk);
         this.release(chunk);
      } else {
         this._inflateds.add(this._inflated);
         this._inflated = chunk;
      }

      return false;
   }

   protected void decodeChunks(ByteBuffer compressed) {
      ByteBuffer buffer = null;

      try {
         label278:
         while(true) {
            switch (this._state.ordinal()) {
               case 0:
                  this._state = GZIPContentDecoder.State.ID;
                  break;
               case 7:
                  if ((this._flags & 4) == 4) {
                     this._state = GZIPContentDecoder.State.EXTRA_LENGTH;
                     this._size = 0;
                     this._value = 0L;
                  } else if ((this._flags & 8) == 8) {
                     this._state = GZIPContentDecoder.State.NAME;
                  } else if ((this._flags & 16) == 16) {
                     this._state = GZIPContentDecoder.State.COMMENT;
                  } else {
                     if ((this._flags & 2) != 2) {
                        this._state = GZIPContentDecoder.State.DATA;
                        continue;
                     }

                     this._state = GZIPContentDecoder.State.HCRC;
                     this._size = 0;
                     this._value = 0L;
                  }
                  break;
               case 13:
                  while(true) {
                     if (buffer == null) {
                        buffer = this.acquire(this._bufferSize);
                     }

                     if (this._inflater.needsInput()) {
                        if (!compressed.hasRemaining()) {
                           return;
                        }

                        this._inflater.setInput(compressed);
                     }

                     try {
                        int pos = BufferUtil.flipToFill(buffer);
                        this._inflater.inflate(buffer);
                        BufferUtil.flipToFlush(buffer, pos);
                     } catch (DataFormatException x) {
                        throw new ZipException(x.getMessage());
                     }

                     if (buffer.hasRemaining()) {
                        ByteBuffer chunk = buffer;
                        buffer = null;
                        if (this.decodedChunk(chunk)) {
                           return;
                        }
                     } else if (this._inflater.finished()) {
                        this._state = GZIPContentDecoder.State.CRC;
                        this._size = 0;
                        this._value = 0L;
                        continue label278;
                     }
                  }
            }

            if (!compressed.hasRemaining()) {
               return;
            }

            byte currByte = compressed.get();
            switch (this._state.ordinal()) {
               case 1:
                  this._value += ((long)currByte & 255L) << 8 * this._size;
                  ++this._size;
                  if (this._size == 2) {
                     if (this._value != 35615L) {
                        throw new ZipException("Invalid gzip bytes");
                     }

                     this._state = GZIPContentDecoder.State.CM;
                  }
                  break;
               case 2:
                  if ((currByte & 255) != 8) {
                     throw new ZipException("Invalid gzip compression method");
                  }

                  this._state = GZIPContentDecoder.State.FLG;
                  break;
               case 3:
                  this._flags = currByte;
                  this._state = GZIPContentDecoder.State.MTIME;
                  this._size = 0;
                  this._value = 0L;
                  break;
               case 4:
                  ++this._size;
                  if (this._size == 4) {
                     this._state = GZIPContentDecoder.State.XFL;
                  }
                  break;
               case 5:
                  this._state = GZIPContentDecoder.State.OS;
                  break;
               case 6:
                  this._state = GZIPContentDecoder.State.FLAGS;
                  break;
               case 7:
               case 13:
               default:
                  throw new ZipException();
               case 8:
                  this._value += ((long)currByte & 255L) << 8 * this._size;
                  ++this._size;
                  if (this._size == 2) {
                     this._state = GZIPContentDecoder.State.EXTRA;
                  }
                  break;
               case 9:
                  --this._value;
                  if (this._value == 0L) {
                     this._flags &= -5;
                     this._state = GZIPContentDecoder.State.FLAGS;
                  }
                  break;
               case 10:
                  if (currByte == 0) {
                     this._flags &= -9;
                     this._state = GZIPContentDecoder.State.FLAGS;
                  }
                  break;
               case 11:
                  if (currByte == 0) {
                     this._flags &= -17;
                     this._state = GZIPContentDecoder.State.FLAGS;
                  }
                  break;
               case 12:
                  ++this._size;
                  if (this._size == 2) {
                     this._flags &= -3;
                     this._state = GZIPContentDecoder.State.FLAGS;
                  }
                  break;
               case 14:
                  this._value += ((long)currByte & 255L) << 8 * this._size;
                  ++this._size;
                  if (this._size == 4) {
                     this._state = GZIPContentDecoder.State.ISIZE;
                     this._size = 0;
                     this._value = 0L;
                  }
                  break;
               case 15:
                  this._value |= ((long)currByte & 255L) << 8 * this._size;
                  ++this._size;
                  if (this._size == 4) {
                     if (this._value != (this._inflater.getBytesWritten() & 4294967295L)) {
                        throw new ZipException("Invalid input size");
                     }

                     this.reset();
                     return;
                  }
            }
         }
      } catch (ZipException x) {
         throw new RuntimeException(x);
      } finally {
         if (buffer != null) {
            this.release(buffer);
         }

      }
   }

   private void reset() {
      this._inflater.reset();
      this._state = GZIPContentDecoder.State.INITIAL;
      this._size = 0;
      this._value = 0L;
      this._flags = 0;
   }

   public void destroy() {
      this._inflaterEntry.release();
      this._inflaterEntry = null;
      this._inflater = null;
   }

   public boolean isFinished() {
      return this._state == GZIPContentDecoder.State.INITIAL;
   }

   public ByteBuffer acquire(int capacity) {
      return this._pool == null ? BufferUtil.allocate(capacity) : this._pool.acquire(capacity, this._useDirectBuffers);
   }

   public void release(ByteBuffer buffer) {
      if (this._pool != null && !BufferUtil.isTheEmptyBuffer(buffer)) {
         this._pool.release(buffer);
      }

   }

   private static enum State {
      INITIAL,
      ID,
      CM,
      FLG,
      MTIME,
      XFL,
      OS,
      FLAGS,
      EXTRA_LENGTH,
      EXTRA,
      NAME,
      COMMENT,
      HCRC,
      DATA,
      CRC,
      ISIZE;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{INITIAL, ID, CM, FLG, MTIME, XFL, OS, FLAGS, EXTRA_LENGTH, EXTRA, NAME, COMMENT, HCRC, DATA, CRC, ISIZE};
      }
   }
}
