package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.BlockInfo;
import org.tukaani.xz.index.IndexDecoder;

public class SeekableXZInputStream extends SeekableInputStream {
   private final ArrayCache arrayCache;
   private SeekableInputStream in;
   private final int memoryLimit;
   private int indexMemoryUsage;
   private final ArrayList streams;
   private int checkTypes;
   private long uncompressedSize;
   private long largestBlockSize;
   private int blockCount;
   private final BlockInfo curBlockInfo;
   private final BlockInfo queriedBlockInfo;
   private Check check;
   private final boolean verifyCheck;
   private BlockInputStream blockDecoder;
   private long curPos;
   private long seekPos;
   private boolean seekNeeded;
   private boolean endReached;
   private IOException exception;
   private final byte[] tempBuf;

   public SeekableXZInputStream(SeekableInputStream in) throws IOException {
      this(in, -1);
   }

   public SeekableXZInputStream(SeekableInputStream in, ArrayCache arrayCache) throws IOException {
      this(in, -1, arrayCache);
   }

   public SeekableXZInputStream(SeekableInputStream in, int memoryLimit) throws IOException {
      this(in, memoryLimit, true);
   }

   public SeekableXZInputStream(SeekableInputStream in, int memoryLimit, ArrayCache arrayCache) throws IOException {
      this(in, memoryLimit, true, arrayCache);
   }

   public SeekableXZInputStream(SeekableInputStream in, int memoryLimit, boolean verifyCheck) throws IOException {
      this(in, memoryLimit, verifyCheck, ArrayCache.getDefaultCache());
   }

   public SeekableXZInputStream(SeekableInputStream in, int memoryLimit, boolean verifyCheck, ArrayCache arrayCache) throws IOException {
      this.indexMemoryUsage = 0;
      this.streams = new ArrayList();
      this.checkTypes = 0;
      this.uncompressedSize = 0L;
      this.largestBlockSize = 0L;
      this.blockCount = 0;
      this.blockDecoder = null;
      this.curPos = 0L;
      this.seekNeeded = false;
      this.endReached = false;
      this.exception = null;
      this.tempBuf = new byte[1];
      this.arrayCache = arrayCache;
      this.verifyCheck = verifyCheck;
      this.in = in;
      DataInputStream inData = new DataInputStream(in);
      in.seek(0L);
      byte[] buf = new byte[XZ.HEADER_MAGIC.length];
      inData.readFully(buf);
      if (!Arrays.equals(buf, XZ.HEADER_MAGIC)) {
         throw new XZFormatException();
      } else {
         long pos = in.length();
         if ((pos & 3L) != 0L) {
            throw new CorruptedInputException("XZ file size is not a multiple of 4 bytes");
         } else {
            byte[] buf = new byte[12];
            long streamPadding = 0L;

            while(pos > 0L) {
               if (pos < 12L) {
                  throw new CorruptedInputException();
               }

               in.seek(pos - 12L);
               inData.readFully(buf);
               if (buf[8] == 0 && buf[9] == 0 && buf[10] == 0 && buf[11] == 0) {
                  streamPadding += 4L;
                  pos -= 4L;
               } else {
                  pos -= 12L;
                  StreamFlags streamFooter = DecoderUtil.decodeStreamFooter(buf);
                  if (streamFooter.backwardSize >= pos) {
                     throw new CorruptedInputException("Backward Size in XZ Stream Footer is too big");
                  }

                  this.check = Check.getInstance(streamFooter.checkType);
                  this.checkTypes |= 1 << streamFooter.checkType;
                  in.seek(pos - streamFooter.backwardSize);

                  IndexDecoder index;
                  try {
                     index = new IndexDecoder(in, streamFooter, streamPadding, memoryLimit);
                  } catch (MemoryLimitException e) {
                     assert memoryLimit >= 0;

                     throw new MemoryLimitException(e.getMemoryNeeded() + this.indexMemoryUsage, memoryLimit + this.indexMemoryUsage);
                  }

                  this.indexMemoryUsage += index.getMemoryUsage();
                  if (memoryLimit >= 0) {
                     memoryLimit -= index.getMemoryUsage();

                     assert memoryLimit >= 0;
                  }

                  if (this.largestBlockSize < index.getLargestBlockSize()) {
                     this.largestBlockSize = index.getLargestBlockSize();
                  }

                  long off = index.getStreamSize() - 12L;
                  if (pos < off) {
                     throw new CorruptedInputException("XZ Index indicates too big compressed size for the XZ Stream");
                  }

                  pos -= off;
                  in.seek(pos);
                  inData.readFully(buf);
                  StreamFlags streamHeader = DecoderUtil.decodeStreamHeader(buf);
                  if (!DecoderUtil.areStreamFlagsEqual(streamHeader, streamFooter)) {
                     throw new CorruptedInputException("XZ Stream Footer does not match Stream Header");
                  }

                  this.uncompressedSize += index.getUncompressedSize();
                  if (this.uncompressedSize < 0L) {
                     throw new UnsupportedOptionsException("XZ file is too big");
                  }

                  this.blockCount += index.getRecordCount();
                  if (this.blockCount < 0) {
                     throw new UnsupportedOptionsException("XZ file has over 2147483647 Blocks");
                  }

                  this.streams.add(index);
                  streamPadding = 0L;
               }
            }

            assert pos == 0L;

            this.memoryLimit = memoryLimit;
            IndexDecoder prev = (IndexDecoder)this.streams.get(this.streams.size() - 1);

            for(int i = this.streams.size() - 2; i >= 0; --i) {
               IndexDecoder cur = (IndexDecoder)this.streams.get(i);
               cur.setOffsets(prev);
               prev = cur;
            }

            IndexDecoder first = (IndexDecoder)this.streams.get(this.streams.size() - 1);
            this.curBlockInfo = new BlockInfo(first);
            this.queriedBlockInfo = new BlockInfo(first);
         }
      }
   }

   public int getCheckTypes() {
      return this.checkTypes;
   }

   public int getIndexMemoryUsage() {
      return this.indexMemoryUsage;
   }

   public long getLargestBlockSize() {
      return this.largestBlockSize;
   }

   public int getStreamCount() {
      return this.streams.size();
   }

   public int getBlockCount() {
      return this.blockCount;
   }

   public long getBlockPos(int blockNumber) {
      this.locateBlockByNumber(this.queriedBlockInfo, blockNumber);
      return this.queriedBlockInfo.uncompressedOffset;
   }

   public long getBlockSize(int blockNumber) {
      this.locateBlockByNumber(this.queriedBlockInfo, blockNumber);
      return this.queriedBlockInfo.uncompressedSize;
   }

   public long getBlockCompPos(int blockNumber) {
      this.locateBlockByNumber(this.queriedBlockInfo, blockNumber);
      return this.queriedBlockInfo.compressedOffset;
   }

   public long getBlockCompSize(int blockNumber) {
      this.locateBlockByNumber(this.queriedBlockInfo, blockNumber);
      return this.queriedBlockInfo.unpaddedSize + 3L & -4L;
   }

   public int getBlockCheckType(int blockNumber) {
      this.locateBlockByNumber(this.queriedBlockInfo, blockNumber);
      return this.queriedBlockInfo.getCheckType();
   }

   public int getBlockNumber(long pos) {
      this.locateBlockByPos(this.queriedBlockInfo, pos);
      return this.queriedBlockInfo.blockNumber;
   }

   public int read() throws IOException {
      return this.read(this.tempBuf, 0, 1) == -1 ? -1 : this.tempBuf[0] & 255;
   }

   public int read(byte[] buf, int off, int len) throws IOException {
      if (off >= 0 && len >= 0 && off + len >= 0 && off + len <= buf.length) {
         if (len == 0) {
            return 0;
         } else if (this.in == null) {
            throw new XZIOException("Stream closed");
         } else if (this.exception != null) {
            throw this.exception;
         } else {
            int size = 0;

            try {
               if (this.seekNeeded) {
                  this.seek();
               }

               if (this.endReached) {
                  return -1;
               }

               while(len > 0) {
                  if (this.blockDecoder == null) {
                     this.seek();
                     if (this.endReached) {
                        break;
                     }
                  }

                  int ret = this.blockDecoder.read(buf, off, len);
                  if (ret > 0) {
                     this.curPos += (long)ret;
                     size += ret;
                     off += ret;
                     len -= ret;
                  } else if (ret == -1) {
                     this.blockDecoder = null;
                  }
               }
            } catch (IOException var6) {
               IOException e = var6;
               if (var6 instanceof EOFException) {
                  e = new CorruptedInputException();
               }

               this.exception = e;
               if (size == 0) {
                  throw e;
               }
            }

            return size;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public int available() throws IOException {
      if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else if (this.exception != null) {
         throw this.exception;
      } else {
         return !this.endReached && !this.seekNeeded && this.blockDecoder != null ? this.blockDecoder.available() : 0;
      }
   }

   public void close() throws IOException {
      this.close(true);
   }

   public void close(boolean closeInput) throws IOException {
      if (this.in != null) {
         if (this.blockDecoder != null) {
            this.blockDecoder.close();
            this.blockDecoder = null;
         }

         try {
            if (closeInput) {
               this.in.close();
            }
         } finally {
            this.in = null;
         }
      }

   }

   public long length() {
      return this.uncompressedSize;
   }

   public long position() throws IOException {
      if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else {
         return this.seekNeeded ? this.seekPos : this.curPos;
      }
   }

   public void seek(long pos) throws IOException {
      if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else if (pos < 0L) {
         throw new XZIOException("Negative seek position: " + pos);
      } else {
         this.seekPos = pos;
         this.seekNeeded = true;
      }
   }

   public void seekToBlock(int blockNumber) throws IOException {
      if (this.in == null) {
         throw new XZIOException("Stream closed");
      } else if (blockNumber >= 0 && blockNumber < this.blockCount) {
         this.locateBlockByNumber(this.queriedBlockInfo, blockNumber);
         this.seekPos = this.queriedBlockInfo.uncompressedOffset;
         this.seekNeeded = true;
      } else {
         throw new XZIOException("Invalid XZ Block number: " + blockNumber);
      }
   }

   private void seek() throws IOException {
      if (!this.seekNeeded) {
         if (this.curBlockInfo.hasNext()) {
            this.curBlockInfo.setNext();
            this.initBlockDecoder();
            return;
         }

         this.seekPos = this.curPos;
      }

      this.seekNeeded = false;
      if (this.seekPos >= this.uncompressedSize) {
         this.curPos = this.seekPos;
         if (this.blockDecoder != null) {
            this.blockDecoder.close();
            this.blockDecoder = null;
         }

         this.endReached = true;
      } else {
         this.endReached = false;
         this.locateBlockByPos(this.curBlockInfo, this.seekPos);
         if (this.curPos <= this.curBlockInfo.uncompressedOffset || this.curPos > this.seekPos) {
            this.in.seek(this.curBlockInfo.compressedOffset);
            this.check = Check.getInstance(this.curBlockInfo.getCheckType());
            this.initBlockDecoder();
            this.curPos = this.curBlockInfo.uncompressedOffset;
         }

         if (this.seekPos > this.curPos) {
            long skipAmount = this.seekPos - this.curPos;
            if (this.blockDecoder.skip(skipAmount) != skipAmount) {
               throw new CorruptedInputException();
            }

            this.curPos = this.seekPos;
         }

      }
   }

   private void locateBlockByPos(BlockInfo info, long pos) {
      if (pos >= 0L && pos < this.uncompressedSize) {
         int i = 0;

         while(true) {
            IndexDecoder index = (IndexDecoder)this.streams.get(i);
            if (index.hasUncompressedOffset(pos)) {
               index.locateBlock(info, pos);

               assert (info.compressedOffset & 3L) == 0L;

               assert info.uncompressedSize > 0L;

               assert pos >= info.uncompressedOffset;

               assert pos < info.uncompressedOffset + info.uncompressedSize;

               return;
            }

            ++i;
         }
      } else {
         throw new IndexOutOfBoundsException("Invalid uncompressed position: " + pos);
      }
   }

   private void locateBlockByNumber(BlockInfo info, int blockNumber) {
      if (blockNumber >= 0 && blockNumber < this.blockCount) {
         if (info.blockNumber != blockNumber) {
            int i = 0;

            while(true) {
               IndexDecoder index = (IndexDecoder)this.streams.get(i);
               if (index.hasRecord(blockNumber)) {
                  index.setBlockInfo(info, blockNumber);
                  return;
               }

               ++i;
            }
         }
      } else {
         throw new IndexOutOfBoundsException("Invalid XZ Block number: " + blockNumber);
      }
   }

   private void initBlockDecoder() throws IOException {
      try {
         if (this.blockDecoder != null) {
            this.blockDecoder.close();
            this.blockDecoder = null;
         }

         this.blockDecoder = new BlockInputStream(this.in, this.check, this.verifyCheck, this.memoryLimit, this.curBlockInfo.unpaddedSize, this.curBlockInfo.uncompressedSize, this.arrayCache);
      } catch (MemoryLimitException e) {
         assert this.memoryLimit >= 0;

         throw new MemoryLimitException(e.getMemoryNeeded() + this.indexMemoryUsage, this.memoryLimit + this.indexMemoryUsage);
      } catch (IndexIndicatorException var3) {
         throw new CorruptedInputException();
      }
   }
}
