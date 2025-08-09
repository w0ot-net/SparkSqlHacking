package org.apache.datasketches.tuple;

import java.lang.reflect.Array;
import java.nio.ByteOrder;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.thetacommon.HashOperations;

public final class CompactSketch extends Sketch {
   private static final byte serialVersionWithSummaryClassNameUID = 1;
   private static final byte serialVersionUIDLegacy = 2;
   private static final byte serialVersionUID = 3;
   private static final short defaultSeedHash = -27700;
   private final long[] hashArr_;
   private Summary[] summaryArr_;

   CompactSketch(long[] hashArr, Summary[] summaryArr, long thetaLong, boolean empty) {
      super(thetaLong, empty, (SummaryFactory)null);
      super.thetaLong_ = thetaLong;
      super.empty_ = empty;
      this.hashArr_ = hashArr;
      this.summaryArr_ = summaryArr;
   }

   CompactSketch(Memory mem, SummaryDeserializer deserializer) {
      super(Long.MAX_VALUE, true, (SummaryFactory)null);
      int offset = 0;
      byte preambleLongs = mem.getByte((long)(offset++));
      byte version = mem.getByte((long)(offset++));
      byte familyId = mem.getByte((long)(offset++));
      SerializerDeserializer.validateFamily(familyId, preambleLongs);
      if (version > 3) {
         throw new SketchesArgumentException("Unsupported serial version. Expected: 3 or lower, actual: " + version);
      } else {
         SerializerDeserializer.validateType(mem.getByte((long)(offset++)), SerializerDeserializer.SketchType.CompactSketch);
         if (version <= 2) {
            byte flags = mem.getByte((long)(offset++));
            boolean isBigEndian = (flags & 1 << CompactSketch.FlagsLegacy.IS_BIG_ENDIAN.ordinal()) > 0;
            if (isBigEndian ^ ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
               throw new SketchesArgumentException("Byte order mismatch");
            }

            this.empty_ = (flags & 1 << CompactSketch.FlagsLegacy.IS_EMPTY.ordinal()) > 0;
            boolean isThetaIncluded = (flags & 1 << CompactSketch.FlagsLegacy.IS_THETA_INCLUDED.ordinal()) > 0;
            if (isThetaIncluded) {
               this.thetaLong_ = mem.getLong((long)offset);
               offset += 8;
            } else {
               this.thetaLong_ = Long.MAX_VALUE;
            }

            boolean hasEntries = (flags & 1 << CompactSketch.FlagsLegacy.HAS_ENTRIES.ordinal()) > 0;
            if (hasEntries) {
               int classNameLength = 0;
               if (version == 1) {
                  classNameLength = mem.getByte((long)(offset++));
               }

               int count = mem.getInt((long)offset);
               offset += 4;
               if (version == 1) {
                  offset += classNameLength;
               }

               this.hashArr_ = new long[count];

               for(int i = 0; i < count; ++i) {
                  this.hashArr_[i] = mem.getLong((long)offset);
                  offset += 8;
               }

               for(int i = 0; i < count; ++i) {
                  offset += this.readSummary(mem, offset, i, count, deserializer);
               }
            } else {
               this.hashArr_ = new long[0];
               this.summaryArr_ = null;
            }
         } else {
            ++offset;
            byte flags = mem.getByte((long)(offset++));
            offset += 2;
            this.empty_ = (flags & 1 << CompactSketch.Flags.IS_EMPTY.ordinal()) > 0;
            this.thetaLong_ = Long.MAX_VALUE;
            int count = 0;
            if (!this.empty_) {
               if (preambleLongs == 1) {
                  count = 1;
               } else {
                  count = mem.getInt((long)offset);
                  offset += 4;
                  offset += 4;
                  if (preambleLongs > 2) {
                     this.thetaLong_ = mem.getLong((long)offset);
                     offset += 8;
                  }
               }
            }

            this.hashArr_ = new long[count];

            for(int i = 0; i < count; ++i) {
               this.hashArr_[i] = mem.getLong((long)offset);
               offset += 8;
               offset += this.readSummary(mem, offset, i, count, deserializer);
            }
         }

      }
   }

   private int readSummary(Memory mem, int offset, int i, int count, SummaryDeserializer deserializer) {
      Memory memRegion = mem.region((long)offset, mem.getCapacity() - (long)offset);
      DeserializeResult<S> result = deserializer.heapifySummary(memRegion);
      S summary = (S)((Summary)result.getObject());
      Class<S> summaryType = ((Summary)result.getObject()).getClass();
      if (this.summaryArr_ == null) {
         this.summaryArr_ = (Summary[])Array.newInstance(summaryType, count);
      }

      this.summaryArr_[i] = summary;
      return result.getSize();
   }

   public CompactSketch compact() {
      return this;
   }

   long[] getHashArr() {
      return this.hashArr_;
   }

   Summary[] getSummaryArr() {
      return this.summaryArr_;
   }

   public int getRetainedEntries() {
      return this.hashArr_ == null ? 0 : this.hashArr_.length;
   }

   public int getCountLessThanThetaLong(long thetaLong) {
      return HashOperations.count(this.hashArr_, thetaLong);
   }

   public byte[] toByteArray() {
      int count = this.getRetainedEntries();
      boolean isSingleItem = count == 1 && !this.isEstimationMode();
      int preambleLongs = !this.isEmpty() && !isSingleItem ? (this.isEstimationMode() ? 3 : 2) : 1;
      int summariesSizeBytes = 0;
      byte[][] summariesBytes = new byte[count][];
      if (count > 0) {
         for(int i = 0; i < count; ++i) {
            summariesBytes[i] = this.summaryArr_[i].toByteArray();
            summariesSizeBytes += summariesBytes[i].length;
         }
      }

      int sizeBytes = 8 * preambleLongs + 8 * count + summariesSizeBytes;
      byte[] bytes = new byte[sizeBytes];
      int offset = 0;
      bytes[offset++] = (byte)preambleLongs;
      bytes[offset++] = 3;
      bytes[offset++] = (byte)Family.TUPLE.getID();
      bytes[offset++] = (byte)SerializerDeserializer.SketchType.CompactSketch.ordinal();
      ++offset;
      bytes[offset++] = (byte)(1 << CompactSketch.Flags.IS_COMPACT.ordinal() | 1 << CompactSketch.Flags.IS_READ_ONLY.ordinal() | (this.isEmpty() ? 1 << CompactSketch.Flags.IS_EMPTY.ordinal() : 0));
      ByteArrayUtil.putShortLE(bytes, offset, (short)-27700);
      offset += 2;
      if (!this.isEmpty() && !isSingleItem) {
         ByteArrayUtil.putIntLE(bytes, offset, count);
         offset += 4;
         offset += 4;
         if (this.isEstimationMode()) {
            ByteArrayUtil.putLongLE(bytes, offset, this.thetaLong_);
            offset += 8;
         }
      }

      for(int i = 0; i < count; ++i) {
         ByteArrayUtil.putLongLE(bytes, offset, this.hashArr_[i]);
         offset += 8;
         System.arraycopy(summariesBytes[i], 0, bytes, offset, summariesBytes[i].length);
         offset += summariesBytes[i].length;
      }

      return bytes;
   }

   public TupleSketchIterator iterator() {
      return new TupleSketchIterator(this.hashArr_, this.summaryArr_);
   }

   private static enum FlagsLegacy {
      IS_BIG_ENDIAN,
      IS_EMPTY,
      HAS_ENTRIES,
      IS_THETA_INCLUDED;
   }

   private static enum Flags {
      IS_BIG_ENDIAN,
      IS_READ_ONLY,
      IS_EMPTY,
      IS_COMPACT,
      IS_ORDERED;
   }
}
