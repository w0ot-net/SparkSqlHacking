package org.roaringbitmap;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import org.roaringbitmap.buffer.MappeableArrayContainer;
import org.roaringbitmap.buffer.MappeableBitmapContainer;
import org.roaringbitmap.buffer.MappeableContainer;
import org.roaringbitmap.buffer.MappeableRunContainer;

public final class RangeBitmap {
   private static final int COOKIE = 61453;
   private static final int BITMAP = 0;
   private static final int RUN = 1;
   private static final int ARRAY = 2;
   private static final int BITMAP_SIZE = 8192;
   private final ByteBuffer buffer;
   private final int masksOffset;
   private final int containersOffset;
   private final long mask;
   private final long max;
   private final byte bytesPerMask;

   public static Appender appender(long maxValue, IntFunction bufferSupplier, Consumer cleaner) {
      return new Appender(maxValue, bufferSupplier, cleaner);
   }

   public static Appender appender(long maxValue) {
      return appender(maxValue, (capacity) -> ByteBuffer.allocate(capacity).order(ByteOrder.LITTLE_ENDIAN), (b) -> {
      });
   }

   public static RangeBitmap map(ByteBuffer buffer) {
      ByteBuffer source = buffer.slice().order(ByteOrder.LITTLE_ENDIAN);
      int cookie = source.getChar();
      if (cookie != 61453) {
         throw new InvalidRoaringFormat("invalid cookie for range bitmap (expected 61453 but got " + cookie + ")");
      } else {
         int base = source.get() & 255;
         if (base != 2) {
            throw new InvalidRoaringFormat("Unsupported base for range bitmap: " + cookie);
         } else {
            int sliceCount = source.get() & 255;
            int maxKey = source.getChar();
            long mask = -1L >>> 64 - sliceCount;
            byte bytesPerMask = (byte)(sliceCount + 7 >>> 3);
            long maxRid = (long)source.getInt() & 4294967295L;
            int masksOffset = source.position();
            int containersOffset = masksOffset + maxKey * bytesPerMask;
            return new RangeBitmap(mask, maxRid, (ByteBuffer)source.position(buffer.position()), masksOffset, containersOffset, bytesPerMask);
         }
      }
   }

   RangeBitmap(long mask, long max, ByteBuffer buffer, int masksOffset, int containersOffset, byte bytesPerMask) {
      this.mask = mask;
      this.max = max;
      this.buffer = buffer;
      this.masksOffset = masksOffset;
      this.containersOffset = containersOffset;
      this.bytesPerMask = bytesPerMask;
   }

   public RoaringBitmap between(long min, long max) {
      if (min != 0L && Long.numberOfLeadingZeros(min) >= Long.numberOfLeadingZeros(this.mask)) {
         return Long.numberOfLeadingZeros(max) < Long.numberOfLeadingZeros(this.mask) ? this.gte(min) : (new DoubleEvaluation()).compute(min - 1L, max);
      } else {
         return this.lte(max);
      }
   }

   public long betweenCardinality(long min, long max) {
      if (min != 0L && Long.numberOfLeadingZeros(min) >= Long.numberOfLeadingZeros(this.mask)) {
         return Long.numberOfLeadingZeros(max) < Long.numberOfLeadingZeros(this.mask) ? this.gteCardinality(min) : (new DoubleEvaluation()).count(min - 1L, max);
      } else {
         return this.lteCardinality(max);
      }
   }

   public long betweenCardinality(long min, long max, RoaringBitmap context) {
      if (min != 0L && Long.numberOfLeadingZeros(min) >= Long.numberOfLeadingZeros(this.mask)) {
         return Long.numberOfLeadingZeros(max) < Long.numberOfLeadingZeros(this.mask) ? this.gteCardinality(min, context) : (new DoubleEvaluation()).count(min - 1L, max, context);
      } else {
         return this.lteCardinality(max, context);
      }
   }

   public RoaringBitmap lte(long threshold) {
      return (new SingleEvaluation()).computeRange(threshold, true);
   }

   public RoaringBitmap lte(long threshold, RoaringBitmap context) {
      return (new SingleEvaluation()).computeRange(threshold, true, context);
   }

   public long lteCardinality(long threshold) {
      return (new SingleEvaluation()).countRange(threshold, true);
   }

   public long lteCardinality(long threshold, RoaringBitmap context) {
      return (new SingleEvaluation()).countRange(threshold, true, context);
   }

   public RoaringBitmap lt(long threshold) {
      return threshold == 0L ? new RoaringBitmap() : this.lte(threshold - 1L);
   }

   public RoaringBitmap lt(long threshold, RoaringBitmap context) {
      return threshold == 0L ? new RoaringBitmap() : this.lte(threshold - 1L, context);
   }

   public long ltCardinality(long threshold) {
      return threshold == 0L ? 0L : this.lteCardinality(threshold - 1L);
   }

   public long ltCardinality(long threshold, RoaringBitmap context) {
      return threshold == 0L ? 0L : this.lteCardinality(threshold - 1L, context);
   }

   public RoaringBitmap gt(long threshold) {
      return (new SingleEvaluation()).computeRange(threshold, false);
   }

   public RoaringBitmap gt(long threshold, RoaringBitmap context) {
      return (new SingleEvaluation()).computeRange(threshold, false, context);
   }

   public long gtCardinality(long threshold) {
      return (new SingleEvaluation()).countRange(threshold, false);
   }

   public long gtCardinality(long threshold, RoaringBitmap context) {
      return (new SingleEvaluation()).countRange(threshold, false, context);
   }

   public RoaringBitmap gte(long threshold) {
      return threshold == 0L ? RoaringBitmap.bitmapOfRange(0L, this.max) : this.gt(threshold - 1L);
   }

   public RoaringBitmap gte(long threshold, RoaringBitmap context) {
      return threshold == 0L ? context.clone() : this.gt(threshold - 1L, context);
   }

   public long gteCardinality(long threshold) {
      return threshold == 0L ? this.max : this.gtCardinality(threshold - 1L);
   }

   public long gteCardinality(long threshold, RoaringBitmap context) {
      return threshold == 0L ? context.getLongCardinality() : this.gtCardinality(threshold - 1L, context);
   }

   public RoaringBitmap eq(long value) {
      return (new SingleEvaluation()).computePoint(value, false);
   }

   public RoaringBitmap eq(long value, RoaringBitmap context) {
      return (new SingleEvaluation()).computePoint(value, false, context);
   }

   public long eqCardinality(long value) {
      return (new SingleEvaluation()).countPoint(value, false);
   }

   public long eqCardinality(long value, RoaringBitmap context) {
      return (new SingleEvaluation()).countPoint(value, false, context);
   }

   public RoaringBitmap neq(long value) {
      return (new SingleEvaluation()).computePoint(value, true);
   }

   public RoaringBitmap neq(long value, RoaringBitmap context) {
      return (new SingleEvaluation()).computePoint(value, true, context);
   }

   public long neqCardinality(long value) {
      return (new SingleEvaluation()).countPoint(value, true);
   }

   public long neqCardinality(long value, RoaringBitmap context) {
      return (new SingleEvaluation()).countPoint(value, true, context);
   }

   private static long getContainerMask(ByteBuffer buffer, int position, long mask, int bytesPerMask) {
      switch (bytesPerMask) {
         case 0:
         case 1:
            return (long)buffer.get(position) & mask;
         case 2:
            return (long)buffer.getChar(position) & mask;
         case 3:
         case 4:
            return (long)buffer.getInt(position) & mask;
         default:
            return buffer.getLong(position) & mask;
      }
   }

   private final class SingleEvaluation {
      private final long[] bits;
      private final ByteBuffer buffer;
      private int position;
      private boolean empty;

      private SingleEvaluation() {
         this.bits = new long[1024];
         this.buffer = RangeBitmap.this.buffer.slice().order(ByteOrder.LITTLE_ENDIAN);
         this.position = RangeBitmap.this.containersOffset;
         this.empty = true;
      }

      public RoaringBitmap computePoint(long value, boolean negate) {
         if (Long.numberOfLeadingZeros(value) < Long.numberOfLeadingZeros(RangeBitmap.this.mask)) {
            return negate ? RoaringBitmap.bitmapOfRange(0L, RangeBitmap.this.max) : new RoaringBitmap();
         } else {
            RoaringArray output = new RoaringArray();
            long remaining = RangeBitmap.this.max;
            int mPos = RangeBitmap.this.masksOffset;

            for(char key = 0; remaining > 0L; mPos += RangeBitmap.this.bytesPerMask) {
               long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
               int limit = Math.min((int)remaining, 65536);
               this.evaluateHorizontalSlicePoint(limit, value, containerMask);
               if (negate) {
                  Util.flipBitmapRange(this.bits, 0, limit);
                  this.empty = false;
               }

               if (!this.empty) {
                  Container toAppend = (new BitmapContainer(this.bits, -1)).repairAfterLazy().runOptimize();
                  if (!toAppend.isEmpty()) {
                     output.append(key, toAppend instanceof BitmapContainer ? toAppend.clone() : toAppend);
                  }
               }

               ++key;
               remaining -= 65536L;
            }

            return new RoaringBitmap(output);
         }
      }

      public RoaringBitmap computePoint(long value, boolean negate, RoaringBitmap context) {
         if (context.isEmpty()) {
            return new RoaringBitmap();
         } else if (Long.numberOfLeadingZeros(value) < Long.numberOfLeadingZeros(RangeBitmap.this.mask)) {
            return negate ? context.clone() : new RoaringBitmap();
         } else {
            RoaringArray output = new RoaringArray();
            RoaringArray contextArray = context.highLowContainer;
            int contextPos = 0;
            int maxContextKey = contextArray.keys[contextArray.size - 1];
            long remaining = RangeBitmap.this.max;
            int mPos = RangeBitmap.this.masksOffset;

            for(int prefix = 0; prefix <= maxContextKey && remaining > 0L; ++prefix) {
               long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
               if (prefix < contextArray.keys[contextPos]) {
                  this.skipContainers(containerMask);
               } else {
                  int limit = Math.min((int)remaining, 65536);
                  this.evaluateHorizontalSlicePoint(limit, value, containerMask);
                  if (negate) {
                     Util.flipBitmapRange(this.bits, 0, limit);
                     this.empty = false;
                  }

                  if (!this.empty) {
                     Container toAppend = (new BitmapContainer(this.bits, -1)).iand((Container)contextArray.values[contextPos]).repairAfterLazy().runOptimize();
                     if (!toAppend.isEmpty()) {
                        output.append((char)prefix, toAppend instanceof BitmapContainer ? toAppend.clone() : toAppend);
                     }
                  }

                  ++contextPos;
               }

               remaining -= 65536L;
               mPos += RangeBitmap.this.bytesPerMask;
            }

            return new RoaringBitmap(output);
         }
      }

      public long countPoint(long value, boolean negate) {
         if (Long.numberOfLeadingZeros(value) < Long.numberOfLeadingZeros(RangeBitmap.this.mask)) {
            return negate ? RangeBitmap.this.max : 0L;
         } else {
            long count = 0L;
            long remaining = RangeBitmap.this.max;

            for(int mPos = RangeBitmap.this.masksOffset; remaining > 0L; mPos += RangeBitmap.this.bytesPerMask) {
               long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
               int limit = Math.min((int)remaining, 65536);
               this.evaluateHorizontalSlicePoint(limit, value, containerMask);
               int cardinality = Util.cardinalityInBitmapRange(this.bits, 0, limit);
               count += negate ? (long)(limit - cardinality) : (long)cardinality;
               remaining -= 65536L;
            }

            return count;
         }
      }

      private long countPoint(long threshold, boolean negate, RoaringBitmap context) {
         if (context.isEmpty()) {
            return 0L;
         } else if (Long.numberOfLeadingZeros(threshold) < Long.numberOfLeadingZeros(RangeBitmap.this.mask)) {
            return negate ? context.getLongCardinality() : 0L;
         } else {
            RoaringArray contextArray = context.highLowContainer;
            int contextPos = 0;
            int maxContextKey = contextArray.keys[contextArray.size - 1];
            long count = 0L;
            long remaining = RangeBitmap.this.max;
            int mPos = RangeBitmap.this.masksOffset;

            for(int prefix = 0; prefix <= maxContextKey && remaining > 0L; ++prefix) {
               int limit = Math.min(65536, (int)remaining);
               long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
               if (prefix < contextArray.keys[contextPos]) {
                  this.skipContainers(containerMask);
               } else {
                  this.evaluateHorizontalSlicePoint(limit, threshold, containerMask);
                  if (negate) {
                     Util.flipBitmapRange(this.bits, 0, limit);
                     this.empty = false;
                  }

                  Container container = contextArray.values[contextPos];
                  int cardinality = container.andCardinality(new BitmapContainer(this.bits, -1));
                  count += (long)cardinality;
                  ++contextPos;
               }

               remaining -= 65536L;
               mPos += RangeBitmap.this.bytesPerMask;
            }

            return count;
         }
      }

      public RoaringBitmap computeRange(long threshold, boolean upper) {
         if (Long.numberOfLeadingZeros(threshold) < Long.numberOfLeadingZeros(RangeBitmap.this.mask)) {
            return upper ? RoaringBitmap.bitmapOfRange(0L, RangeBitmap.this.max) : new RoaringBitmap();
         } else {
            RoaringArray output = new RoaringArray();
            long remaining = RangeBitmap.this.max;
            int mPos = RangeBitmap.this.masksOffset;

            for(char key = 0; remaining > 0L; mPos += RangeBitmap.this.bytesPerMask) {
               long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
               this.evaluateHorizontalSliceRange(remaining, threshold, containerMask);
               if (!upper) {
                  Util.flipBitmapRange(this.bits, 0, Math.min(65536, (int)remaining));
                  this.empty = false;
               }

               if (!this.empty) {
                  Container toAppend = (new BitmapContainer(this.bits, -1)).repairAfterLazy().runOptimize();
                  if (!toAppend.isEmpty()) {
                     output.append(key, toAppend instanceof BitmapContainer ? toAppend.clone() : toAppend);
                  }
               }

               ++key;
               remaining -= 65536L;
            }

            return new RoaringBitmap(output);
         }
      }

      private RoaringBitmap computeRange(long threshold, boolean upper, RoaringBitmap context) {
         if (context.isEmpty()) {
            return new RoaringBitmap();
         } else if (Long.numberOfLeadingZeros(threshold) < Long.numberOfLeadingZeros(RangeBitmap.this.mask)) {
            return upper ? context.clone() : new RoaringBitmap();
         } else {
            RoaringArray contextArray = context.highLowContainer;
            int contextPos = 0;
            int maxContextKey = contextArray.keys[contextArray.size - 1];
            RoaringArray output = new RoaringArray();
            long remaining = RangeBitmap.this.max;
            int mPos = RangeBitmap.this.masksOffset;

            for(int prefix = 0; prefix <= maxContextKey && remaining > 0L; ++prefix) {
               long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
               if (prefix < contextArray.keys[contextPos]) {
                  this.skipContainers(containerMask);
               } else {
                  this.evaluateHorizontalSliceRange(remaining, threshold, containerMask);
                  if (!upper) {
                     Util.flipBitmapRange(this.bits, 0, Math.min(65536, (int)remaining));
                     this.empty = false;
                  }

                  if (!this.empty) {
                     Container toAppend = (new BitmapContainer(this.bits, -1)).iand((Container)contextArray.values[contextPos]).repairAfterLazy().runOptimize();
                     if (!toAppend.isEmpty()) {
                        output.append((char)prefix, toAppend instanceof BitmapContainer ? toAppend.clone() : toAppend);
                     }
                  }

                  ++contextPos;
               }

               remaining -= 65536L;
               mPos += RangeBitmap.this.bytesPerMask;
            }

            return new RoaringBitmap(output);
         }
      }

      public long countRange(long threshold, boolean upper) {
         if (Long.numberOfLeadingZeros(threshold) < Long.numberOfLeadingZeros(RangeBitmap.this.mask)) {
            return upper ? RangeBitmap.this.max : 0L;
         } else {
            long count = 0L;
            long remaining = RangeBitmap.this.max;

            for(int mPos = RangeBitmap.this.masksOffset; remaining > 0L; mPos += RangeBitmap.this.bytesPerMask) {
               long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
               this.evaluateHorizontalSliceRange(remaining, threshold, containerMask);
               int remainder = Math.min((int)remaining, 65536);
               int cardinality = Util.cardinalityInBitmapRange(this.bits, 0, remainder);
               count += upper ? (long)cardinality : (long)(remainder - cardinality);
               remaining -= 65536L;
            }

            return count;
         }
      }

      private long countRange(long threshold, boolean upper, RoaringBitmap context) {
         if (context.isEmpty()) {
            return 0L;
         } else if (Long.numberOfLeadingZeros(threshold) < Long.numberOfLeadingZeros(RangeBitmap.this.mask)) {
            return upper ? context.getLongCardinality() : 0L;
         } else {
            RoaringArray contextArray = context.highLowContainer;
            int contextPos = 0;
            int maxContextKey = contextArray.keys[contextArray.size - 1];
            long count = 0L;
            long remaining = RangeBitmap.this.max;
            int mPos = RangeBitmap.this.masksOffset;

            for(int prefix = 0; prefix <= maxContextKey && remaining > 0L; ++prefix) {
               long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
               if (prefix < contextArray.keys[contextPos]) {
                  this.skipContainers(containerMask);
               } else {
                  this.evaluateHorizontalSliceRange(remaining, threshold, containerMask);
                  Container container = contextArray.values[contextPos];
                  int cardinality = upper ? container.andCardinality(new BitmapContainer(this.bits, -1)) : container.andNot((new BitmapContainer(this.bits, -1)).repairAfterLazy()).getCardinality();
                  count += (long)cardinality;
                  ++contextPos;
               }

               remaining -= 65536L;
               mPos += RangeBitmap.this.bytesPerMask;
            }

            return count;
         }
      }

      private void evaluateHorizontalSliceRange(long remaining, long threshold, long containerMask) {
         int skip = 64 - Long.numberOfLeadingZeros(~(threshold | containerMask) & RangeBitmap.this.mask);
         int slice = 0;
         if (skip <= 0) {
            if ((threshold & 1L) == 1L) {
               if (remaining >= 65536L) {
                  Arrays.fill(this.bits, -1L);
               } else {
                  Util.setBitmapRange(this.bits, 0, (int)remaining);
                  if (!this.empty) {
                     Util.resetBitmapRange(this.bits, (int)remaining, 65536);
                  }
               }

               if ((containerMask & 1L) == 1L) {
                  this.skipContainer();
               }

               this.empty = false;
            } else {
               if (!this.empty) {
                  Arrays.fill(this.bits, 0L);
                  this.empty = true;
               }

               if ((containerMask & 1L) == 1L) {
                  if ((threshold & 1L) == 0L) {
                     this.orNextIntoBits();
                     this.empty = false;
                  } else {
                     this.skipContainer();
                  }
               }
            }

            ++slice;
         } else {
            for(; slice < skip; ++slice) {
               if ((containerMask >>> slice & 1L) == 1L) {
                  this.skipContainer();
               }
            }

            if (!this.empty) {
               Arrays.fill(this.bits, 0L);
               this.empty = true;
            }
         }

         for(; slice < Long.bitCount(RangeBitmap.this.mask); ++slice) {
            if ((containerMask >>> slice & 1L) == 1L) {
               if ((threshold >>> slice & 1L) == 1L) {
                  this.orNextIntoBits();
                  this.empty = false;
               } else if (this.empty) {
                  this.skipContainer();
               } else {
                  this.andNextIntoBits();
               }
            }
         }

      }

      private void evaluateHorizontalSlicePoint(int limit, long value, long containerMask) {
         Util.setBitmapRange(this.bits, 0, limit);
         Util.resetBitmapRange(this.bits, limit, 65536);
         this.empty = false;

         for(int slice = 0; slice < Long.bitCount(RangeBitmap.this.mask); ++slice) {
            if ((value >>> slice & 1L) == 1L) {
               if ((containerMask >>> slice & 1L) == 1L) {
                  if (this.empty) {
                     this.skipContainer();
                  } else {
                     this.removeNextFromBits();
                  }
               }
            } else if ((containerMask >>> slice & 1L) == 1L) {
               if (this.empty) {
                  this.skipContainer();
               } else {
                  this.andNextIntoBits();
               }
            } else if (!this.empty) {
               Util.resetBitmapRange(this.bits, 0, limit);
               this.empty = true;
            }
         }

      }

      private void andNextIntoBits() {
         int type = this.buffer.get(this.position);
         ++this.position;
         int size = this.buffer.getChar(this.position) & '\uffff';
         this.position += 2;
         switch (type) {
            case 0:
               LongBuffer lb = (LongBuffer)((ByteBuffer)this.buffer.position(this.position)).asLongBuffer().limit(1024);
               MappeableBitmapContainer bitmap = new MappeableBitmapContainer(lb, size);
               bitmap.andInto(this.bits);
               this.position += 8192;
               break;
            case 1:
               int skip = size << 2;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableRunContainer run = new MappeableRunContainer(cb, size);
               run.andInto(this.bits);
               this.position += skip;
               break;
            case 2:
               int skip = size << 1;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableArrayContainer array = new MappeableArrayContainer(cb, size);
               array.andInto(this.bits);
               this.position += skip;
               break;
            default:
               throw new IllegalStateException("Unknown type " + type + " (this is a bug, please report it.)");
         }

      }

      private void orNextIntoBits() {
         int type = this.buffer.get(this.position);
         ++this.position;
         int size = this.buffer.getChar(this.position) & '\uffff';
         this.position += 2;
         switch (type) {
            case 0:
               LongBuffer lb = (LongBuffer)((ByteBuffer)this.buffer.position(this.position)).asLongBuffer().limit(1024);
               MappeableBitmapContainer bitmap = new MappeableBitmapContainer(lb, size);
               bitmap.orInto(this.bits);
               this.position += 8192;
               break;
            case 1:
               int skip = size << 2;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableRunContainer run = new MappeableRunContainer(cb, size);
               run.orInto(this.bits);
               this.position += skip;
               break;
            case 2:
               int skip = size << 1;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableArrayContainer array = new MappeableArrayContainer(cb, size);
               array.orInto(this.bits);
               this.position += skip;
               break;
            default:
               throw new IllegalStateException("Unknown type " + type + " (this is a bug, please report it.)");
         }

      }

      private void removeNextFromBits() {
         int type = this.buffer.get(this.position);
         ++this.position;
         int size = this.buffer.getChar(this.position) & '\uffff';
         this.position += 2;
         switch (type) {
            case 0:
               LongBuffer lb = (LongBuffer)((ByteBuffer)this.buffer.position(this.position)).asLongBuffer().limit(1024);
               MappeableBitmapContainer bitmap = new MappeableBitmapContainer(lb, size);
               bitmap.removeFrom(this.bits);
               this.position += 8192;
               break;
            case 1:
               int skip = size << 2;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableRunContainer run = new MappeableRunContainer(cb, size);
               run.removeFrom(this.bits);
               this.position += skip;
               break;
            case 2:
               int skip = size << 1;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableArrayContainer array = new MappeableArrayContainer(cb, size);
               array.removeFrom(this.bits);
               this.position += skip;
               break;
            default:
               throw new IllegalStateException("Unknown type " + type + " (this is a bug, please report it.)");
         }

      }

      private void skipContainer() {
         int type = this.buffer.get(this.position);
         int size = this.buffer.getChar(this.position + 1) & '\uffff';
         if (type == 0) {
            this.position += 8195;
         } else {
            this.position += 3 + (size << (type == 1 ? 2 : 1));
         }

      }

      private void skipContainers(long mask) {
         for(int i = 0; i < Long.bitCount(mask); ++i) {
            this.skipContainer();
         }

      }
   }

   private final class DoubleEvaluation {
      private final ByteBuffer buffer;
      private final Bits low;
      private final Bits high;
      private int position;

      private DoubleEvaluation() {
         this.buffer = RangeBitmap.this.buffer.slice().order(ByteOrder.LITTLE_ENDIAN);
         this.low = new Bits();
         this.high = new Bits();
         this.position = RangeBitmap.this.containersOffset;
      }

      public RoaringBitmap compute(long lower, long upper) {
         RoaringArray output = new RoaringArray();
         long remaining = RangeBitmap.this.max;
         int mPos = RangeBitmap.this.masksOffset;

         for(char key = 0; remaining > 0L; mPos += RangeBitmap.this.bytesPerMask) {
            long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
            this.evaluateHorizontalSlice(containerMask, remaining, lower, upper);
            if (!this.low.empty && !this.high.empty) {
               if (this.low.full && this.high.full) {
                  output.append(key, (Container)RunContainer.full());
               } else {
                  long[] bits;
                  if (this.low.full) {
                     bits = this.high.bits;
                  } else if (this.high.full) {
                     bits = this.low.bits;
                  } else {
                     bits = this.low.bits;

                     for(int i = 0; i < Math.min(bits.length, this.high.bits.length); ++i) {
                        bits[i] &= this.high.bits[i];
                     }
                  }

                  Container toAppend = (new BitmapContainer(bits, -1)).repairAfterLazy().runOptimize();
                  if (!toAppend.isEmpty()) {
                     output.append(key, toAppend instanceof BitmapContainer ? toAppend.clone() : toAppend);
                  }
               }
            }

            ++key;
            remaining -= 65536L;
         }

         return new RoaringBitmap(output);
      }

      public long count(long lower, long upper) {
         long count = 0L;
         long remaining = RangeBitmap.this.max;

         for(int mPos = RangeBitmap.this.masksOffset; remaining > 0L; mPos += RangeBitmap.this.bytesPerMask) {
            long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
            this.evaluateHorizontalSlice(containerMask, remaining, lower, upper);
            if (!this.low.empty && !this.high.empty) {
               int remainder = Math.min((int)remaining, 65536);
               if (this.low.full && this.high.full) {
                  count += (long)remainder;
               } else if (this.low.full) {
                  count += (long)Util.cardinalityInBitmapRange(this.high.bits, 0, remainder);
               } else if (this.high.full) {
                  count += (long)Util.cardinalityInBitmapRange(this.low.bits, 0, remainder);
               } else {
                  for(int i = 0; i < Math.min(this.low.bits.length, this.high.bits.length); ++i) {
                     long[] var10000 = this.high.bits;
                     var10000[i] &= this.low.bits[i];
                  }

                  count += (long)Util.cardinalityInBitmapRange(this.high.bits, 0, remainder);
               }
            }

            remaining -= 65536L;
         }

         return count;
      }

      public long count(long lower, long upper, RoaringBitmap context) {
         long count = 0L;
         long remaining = RangeBitmap.this.max;
         int mPos = RangeBitmap.this.masksOffset;
         RoaringArray contextArray = context.highLowContainer;
         int contextPos = 0;
         int maxContextKey = contextArray.keys[contextArray.size - 1];

         for(int prefix = 0; prefix <= maxContextKey && remaining > 0L; ++prefix) {
            long containerMask = RangeBitmap.getContainerMask(this.buffer, mPos, RangeBitmap.this.mask, RangeBitmap.this.bytesPerMask);
            if (prefix < contextArray.keys[contextPos]) {
               for(int i = 0; i < Long.bitCount(containerMask); ++i) {
                  this.skipContainer();
               }
            } else {
               this.evaluateHorizontalSlice(containerMask, remaining, lower, upper);
               if (!this.low.empty && !this.high.empty) {
                  Container container = contextArray.values[contextPos];
                  if (this.low.full && this.high.full) {
                     count += (long)container.getCardinality();
                  } else if (this.low.full) {
                     count += (long)(new BitmapContainer(this.high.bits, -1)).andCardinality((Container)container);
                  } else if (this.high.full) {
                     count += (long)(new BitmapContainer(this.low.bits, -1)).andCardinality((Container)container);
                  } else {
                     for(int i = 0; i < Math.min(this.low.bits.length, this.high.bits.length); ++i) {
                        long[] var10000 = this.high.bits;
                        var10000[i] &= this.low.bits[i];
                     }

                     count += (long)(new BitmapContainer(this.high.bits, -1)).andCardinality((Container)container);
                  }
               }
            }

            remaining -= 65536L;
            mPos += RangeBitmap.this.bytesPerMask;
         }

         return count;
      }

      private void evaluateHorizontalSlice(long containerMask, long remaining, long lower, long upper) {
         // $FF: Couldn't be decompiled
      }

      private void setupFirstSlice(long threshold, Bits bits, int remaining, boolean copy) {
         if ((threshold & 1L) == 1L) {
            if (remaining >= 65536) {
               bits.fill();
            } else {
               bits.reset(remaining);
            }
         } else {
            bits.clear();
            if (copy) {
               this.orNextIntoBits(bits);
            }
         }

      }

      private void orLowOrHigh() {
         int type = this.buffer.get(this.position);
         ++this.position;
         int size = this.buffer.getChar(this.position) & '\uffff';
         this.position += 2;
         switch (type) {
            case 0:
               LongBuffer lb = (LongBuffer)((ByteBuffer)this.buffer.position(this.position)).asLongBuffer().limit(1024);
               MappeableBitmapContainer bitmap = new MappeableBitmapContainer(lb, size);
               this.low.or(bitmap);
               this.high.or(bitmap);
               this.position += 8192;
               break;
            case 1:
               int skip = size << 2;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableRunContainer run = new MappeableRunContainer(cb, size);
               this.low.or(run);
               this.high.or(run);
               this.position += skip;
               break;
            case 2:
               int skip = size << 1;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableArrayContainer array = new MappeableArrayContainer(cb, size);
               this.low.or(array);
               this.high.or(array);
               this.position += skip;
               break;
            default:
               throw new IllegalStateException("Unknown type " + type + " (this is a bug, please report it.)");
         }

      }

      private void orLowAndHigh() {
         int type = this.buffer.get(this.position);
         ++this.position;
         int size = this.buffer.getChar(this.position) & '\uffff';
         this.position += 2;
         switch (type) {
            case 0:
               LongBuffer lb = (LongBuffer)((ByteBuffer)this.buffer.position(this.position)).asLongBuffer().limit(1024);
               MappeableBitmapContainer bitmap = new MappeableBitmapContainer(lb, size);
               this.low.or(bitmap);
               this.high.and(bitmap);
               this.position += 8192;
               break;
            case 1:
               int skip = size << 2;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableRunContainer run = new MappeableRunContainer(cb, size);
               this.low.or(run);
               this.high.and(run);
               this.position += skip;
               break;
            case 2:
               int skip = size << 1;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableArrayContainer array = new MappeableArrayContainer(cb, size);
               this.low.or(array);
               this.high.and(array);
               this.position += skip;
               break;
            default:
               throw new IllegalStateException("Unknown type " + type + " (this is a bug, please report it.)");
         }

      }

      private void andLowOrHigh() {
         int type = this.buffer.get(this.position);
         ++this.position;
         int size = this.buffer.getChar(this.position) & '\uffff';
         this.position += 2;
         switch (type) {
            case 0:
               LongBuffer lb = (LongBuffer)((ByteBuffer)this.buffer.position(this.position)).asLongBuffer().limit(1024);
               MappeableBitmapContainer bitmap = new MappeableBitmapContainer(lb, size);
               this.low.and(bitmap);
               this.high.or(bitmap);
               this.position += 8192;
               break;
            case 1:
               int skip = size << 2;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableRunContainer run = new MappeableRunContainer(cb, size);
               this.low.and(run);
               this.high.or(run);
               this.position += skip;
               break;
            case 2:
               int skip = size << 1;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableArrayContainer array = new MappeableArrayContainer(cb, size);
               this.low.and(array);
               this.high.or(array);
               this.position += skip;
               break;
            default:
               throw new IllegalStateException("Unknown type " + type + " (this is a bug, please report it.)");
         }

      }

      private void andLowAndHigh() {
         int type = this.buffer.get(this.position);
         ++this.position;
         int size = this.buffer.getChar(this.position) & '\uffff';
         this.position += 2;
         switch (type) {
            case 0:
               LongBuffer lb = (LongBuffer)((ByteBuffer)this.buffer.position(this.position)).asLongBuffer().limit(1024);
               MappeableBitmapContainer bitmap = new MappeableBitmapContainer(lb, size);
               this.low.and(bitmap);
               this.high.and(bitmap);
               this.position += 8192;
               break;
            case 1:
               int skip = size << 2;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableRunContainer run = new MappeableRunContainer(cb, size);
               this.low.and(run);
               this.high.and(run);
               this.position += skip;
               break;
            case 2:
               int skip = size << 1;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position)).asCharBuffer().limit(skip >>> 1);
               MappeableArrayContainer array = new MappeableArrayContainer(cb, size);
               this.low.and(array);
               this.high.and(array);
               this.position += skip;
               break;
            default:
               throw new IllegalStateException("Unknown type " + type + " (this is a bug, please report it.)");
         }

      }

      private void orNextIntoBits(Bits bits) {
         int type = this.buffer.get(this.position);
         int size = this.buffer.getChar(this.position + 1) & '\uffff';
         switch (type) {
            case 0:
               LongBuffer lb = (LongBuffer)((ByteBuffer)this.buffer.position(this.position + 3)).asLongBuffer().limit(1024);
               bits.or(new MappeableBitmapContainer(lb, size));
               break;
            case 1:
               int skip = size << 2;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position + 3)).asCharBuffer().limit(skip >>> 1);
               bits.or(new MappeableRunContainer(cb, size));
               break;
            case 2:
               int skip = size << 1;
               CharBuffer cb = (CharBuffer)((ByteBuffer)this.buffer.position(this.position + 3)).asCharBuffer().limit(skip >>> 1);
               bits.or(new MappeableArrayContainer(cb, size));
               break;
            default:
               throw new IllegalStateException("Unknown type " + type + " (this is a bug, please report it.)");
         }

      }

      private void skipContainer() {
         int type = this.buffer.get(this.position);
         int size = this.buffer.getChar(this.position + 1) & '\uffff';
         if (type == 0) {
            this.position += 8195;
         } else {
            this.position += 3 + (size << (type == 1 ? 2 : 1));
         }

      }
   }

   private static final class Bits {
      private final long[] bits;
      private boolean empty;
      private boolean full;

      private Bits() {
         this.bits = new long[1024];
         this.empty = true;
         this.full = false;
      }

      public void clear() {
         if (!this.empty) {
            Arrays.fill(this.bits, 0L);
            this.makeEmpty();
         }

      }

      public void fill() {
         if (!this.full) {
            Arrays.fill(this.bits, -1L);
            this.makeFull();
         }

      }

      public void reset(int boundary) {
         if (!this.full) {
            Util.setBitmapRange(this.bits, 0, boundary);
         }

         if (!this.empty) {
            Util.resetBitmapRange(this.bits, boundary, 65536);
         }

         this.makeNonEmpty();
         this.makeNonFull();
      }

      public void flip(int from, int to) {
         Util.flipBitmapRange(this.bits, from, to);
         if (!this.full) {
            if (this.empty) {
               this.makeFull();
            }
         } else {
            this.makeEmpty();
         }

      }

      public void or(MappeableContainer container) {
         if (container.isFull()) {
            this.fill();
         } else if (!this.full) {
            container.orInto(this.bits);
            this.makeNonEmpty();
         }

      }

      public void and(MappeableContainer container) {
         if (!this.empty && !container.isFull()) {
            container.andInto(this.bits);
            this.makeNonFull();
         }

      }

      private void makeEmpty() {
         this.empty = true;
         this.full = false;
      }

      private void makeNonEmpty() {
         this.empty = false;
      }

      private void makeFull() {
         this.full = true;
         this.empty = false;
      }

      private void makeNonFull() {
         this.full = false;
      }
   }

   public static final class Appender {
      private static final int GROWTH = 8;
      private final IntFunction bufferSupplier;
      private final Consumer bufferCleaner;
      private final byte bytesPerMask;
      private final long rangeMask;
      private final Container[] slice;
      private ByteBuffer maskBuffer;
      private ByteBuffer containers;
      private int bufferPos;
      private long mask;
      private int rid;
      private int key = 0;
      private int serializedContainerSize;
      private boolean dirty;

      Appender(long maxValue, IntFunction bufferSupplier, Consumer cleaner) {
         this.bufferSupplier = bufferSupplier;
         this.bufferCleaner = cleaner;
         this.rangeMask = rangeMask(maxValue);
         this.bytesPerMask = bytesPerMask(maxValue);
         this.slice = new Container[Long.bitCount(this.rangeMask)];

         for(int i = 0; i < this.slice.length; ++i) {
            this.slice[i] = this.containerForSlice(i);
         }

         this.maskBuffer = (ByteBuffer)bufferSupplier.apply(this.maskBufferGrowth());
         this.containers = (ByteBuffer)bufferSupplier.apply(this.containerGrowth() * 1024);
      }

      public RangeBitmap build(IntFunction supplier) {
         this.flush();
         return this.build((ByteBuffer)supplier.apply(this.serializedSizeInBytes()));
      }

      public RangeBitmap build() {
         return this.build((IntFunction)((capacity) -> ByteBuffer.allocate(capacity).order(ByteOrder.LITTLE_ENDIAN)));
      }

      public RangeBitmap build(ByteBuffer buffer) {
         this.serialize(buffer);
         buffer.flip();
         return RangeBitmap.map(buffer);
      }

      public void clear() {
         this.containers.position(0);
         this.bufferPos = 0;
         this.mask = 0L;
         this.rid = 0;
         this.key = 0;
         this.serializedContainerSize = 0;
      }

      public int serializedSizeInBytes() {
         this.flush();
         int cookieSize = 2;
         int baseSize = 1;
         int slicesSize = 1;
         int maxKeySize = 2;
         int maxRidSize = 4;
         int headerSize = cookieSize + baseSize + slicesSize + maxKeySize + maxRidSize;
         int keysSize = this.key * this.bytesPerMask;
         return headerSize + keysSize + this.serializedContainerSize;
      }

      public void serialize(ByteBuffer buffer) {
         if (this.flush()) {
            throw new IllegalStateException("Attempted to serialize without calling serializedSizeInBytes first");
         } else {
            ByteBuffer target = buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffer : buffer.slice().order(ByteOrder.LITTLE_ENDIAN);
            target.putChar('\uf00d');
            target.put((byte)2);
            target.put((byte)Long.bitCount(this.rangeMask));
            target.putChar((char)this.key);
            target.putInt(this.rid);
            int spaceForKeys = this.key * this.bytesPerMask;
            target.put((ByteBuffer)this.maskBuffer.slice().order(ByteOrder.LITTLE_ENDIAN).limit(spaceForKeys));
            target.put((ByteBuffer)this.containers.slice().order(ByteOrder.LITTLE_ENDIAN).limit(this.serializedContainerSize));
            if (buffer != target) {
               buffer.position(target.position());
            }

         }
      }

      public void add(long value) {
         if ((value & this.rangeMask) == value) {
            long bits = ~value & this.rangeMask;
            this.mask |= bits;

            while(bits != 0L) {
               int index = Long.numberOfTrailingZeros(bits);
               bits &= bits - 1L;
               Container c = this.slice[index];
               Container updated = c.add((char)this.rid);
               if (updated != c) {
                  this.slice[index] = updated;
               }
            }

            ++this.rid;
            this.dirty = true;
            if (this.rid >>> 16 > this.key) {
               this.append();
            }

         } else {
            throw new IllegalArgumentException(value + " too large");
         }
      }

      private boolean flush() {
         if (this.dirty) {
            this.append();
            return true;
         } else {
            return false;
         }
      }

      private void append() {
         if (this.maskBuffer.capacity() - this.bufferPos < 8) {
            this.maskBuffer = this.growBuffer(this.maskBuffer, this.maskBufferGrowth());
            this.maskBuffer.position(0);
         }

         this.maskBuffer.putLong(this.bufferPos, this.mask);
         this.bufferPos += this.bytesPerMask;

         for(Container container : this.slice) {
            if (!container.isEmpty()) {
               Container toSerialize = container.runOptimize();
               int serializedSize = toSerialize.serializedSizeInBytes();
               int type = toSerialize instanceof BitmapContainer ? 0 : (toSerialize instanceof RunContainer ? 1 : 2);
               int required = serializedSize + (type == 0 ? 3 : 1);
               if (this.containers.capacity() - this.serializedContainerSize < required) {
                  int growthFactor = 8192 * this.slice.length;
                  int newSize = Math.max(growthFactor, required + 8191 & -8192);
                  this.containers = this.growBuffer(this.containers, newSize);
               }

               this.containers.put(this.serializedContainerSize, (byte)type);
               if (type == 0) {
                  this.containers.putChar(this.serializedContainerSize + 1, (char)container.getCardinality());
                  this.containers.position(this.serializedContainerSize + 3);
                  toSerialize.writeArray(this.containers);
                  this.containers.position(0);
                  this.serializedContainerSize += required;
               } else if (type == 1) {
                  this.containers.position(this.serializedContainerSize + 1);
                  toSerialize.writeArray(this.containers);
                  this.containers.position(0);
                  this.serializedContainerSize += required;
               } else {
                  this.containers.putChar(this.serializedContainerSize + 1, (char)container.getCardinality());
                  this.containers.position(this.serializedContainerSize + 3);
                  toSerialize.writeArray(this.containers);
                  this.containers.position(0);
                  this.serializedContainerSize += required;
               }

               container.clear();
            }
         }

         this.mask = 0L;
         ++this.key;
         this.dirty = false;
      }

      private int maskBufferGrowth() {
         return 8 * this.bytesPerMask;
      }

      private int containerGrowth() {
         return 8 * this.slice.length;
      }

      private ByteBuffer growBuffer(ByteBuffer buffer, int growth) {
         ByteBuffer newBuffer = (ByteBuffer)this.bufferSupplier.apply(buffer.capacity() + growth);
         int pos = buffer.position();
         newBuffer.put(buffer);
         buffer.position(pos);
         this.bufferCleaner.accept(buffer);
         return newBuffer;
      }

      private Container containerForSlice(int sliceNumber) {
         return (Container)(sliceNumber >= 5 ? new RunContainer() : new BitmapContainer());
      }

      private static long rangeMask(long maxValue) {
         int lz = Long.numberOfLeadingZeros(maxValue | 1L);
         return -1L >>> lz;
      }

      private static byte bytesPerMask(long maxValue) {
         int lz = Long.numberOfLeadingZeros(maxValue | 1L);
         return (byte)(64 - lz + 7 >>> 3);
      }
   }
}
