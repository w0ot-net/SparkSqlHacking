package org.apache.datasketches.tuple;

import java.lang.reflect.Array;
import java.nio.ByteOrder;
import java.util.Objects;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.QuickSelect;

class QuickSelectSketch extends Sketch {
   private static final byte serialVersionUID = 2;
   private static final int DEFAULT_LG_RESIZE_FACTOR;
   private final int nomEntries_;
   private final int lgResizeFactor_;
   private final float samplingProbability_;
   private int lgCurrentCapacity_;
   private int retEntries_;
   private int rebuildThreshold_;
   private long[] hashTable_;
   Summary[] summaryTable_;

   QuickSelectSketch(int nomEntries, SummaryFactory summaryFactory) {
      this(nomEntries, DEFAULT_LG_RESIZE_FACTOR, summaryFactory);
   }

   QuickSelectSketch(int nomEntries, int lgResizeFactor, SummaryFactory summaryFactory) {
      this(nomEntries, lgResizeFactor, 1.0F, summaryFactory);
   }

   QuickSelectSketch(int nomEntries, int lgResizeFactor, float samplingProbability, SummaryFactory summaryFactory) {
      this(nomEntries, lgResizeFactor, samplingProbability, summaryFactory, Util.getStartingCapacity(nomEntries, lgResizeFactor));
   }

   private QuickSelectSketch(int nomEntries, int lgResizeFactor, float samplingProbability, SummaryFactory summaryFactory, int startingSize) {
      super((long)((double)Long.MAX_VALUE * (double)samplingProbability), true, summaryFactory);
      this.nomEntries_ = org.apache.datasketches.common.Util.ceilingPowerOf2(nomEntries);
      this.lgResizeFactor_ = lgResizeFactor;
      this.samplingProbability_ = samplingProbability;
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(startingSize);
      this.retEntries_ = 0;
      this.hashTable_ = new long[startingSize];
      this.rebuildThreshold_ = setRebuildThreshold(this.hashTable_, this.nomEntries_);
      this.summaryTable_ = null;
   }

   QuickSelectSketch(QuickSelectSketch sketch) {
      super(sketch.thetaLong_, sketch.empty_, sketch.summaryFactory_);
      this.nomEntries_ = sketch.nomEntries_;
      this.lgResizeFactor_ = sketch.lgResizeFactor_;
      this.samplingProbability_ = sketch.samplingProbability_;
      this.lgCurrentCapacity_ = sketch.lgCurrentCapacity_;
      this.retEntries_ = sketch.retEntries_;
      this.hashTable_ = (long[])sketch.hashTable_.clone();
      this.rebuildThreshold_ = sketch.rebuildThreshold_;
      this.summaryTable_ = Util.copySummaryArray(sketch.summaryTable_);
   }

   /** @deprecated */
   @Deprecated
   QuickSelectSketch(Memory mem, SummaryDeserializer deserializer, SummaryFactory summaryFactory) {
      this(new Validate(), mem, deserializer, summaryFactory);
   }

   private QuickSelectSketch(Validate val, Memory mem, SummaryDeserializer deserializer, SummaryFactory summaryFactory) {
      super(val.validate(mem, deserializer), val.myEmpty, summaryFactory);
      this.nomEntries_ = val.myNomEntries;
      this.lgResizeFactor_ = val.myLgResizeFactor;
      this.samplingProbability_ = val.mySamplingProbability;
      this.lgCurrentCapacity_ = val.myLgCurrentCapacity;
      this.retEntries_ = val.myRetEntries;
      this.rebuildThreshold_ = val.myRebuildThreshold;
      this.hashTable_ = val.myHashTable;
      this.summaryTable_ = (Summary[])val.mySummaryTable;
   }

   QuickSelectSketch copy() {
      return new QuickSelectSketch(this);
   }

   long[] getHashTable() {
      return this.hashTable_;
   }

   public int getRetainedEntries() {
      return this.retEntries_;
   }

   public int getCountLessThanThetaLong(long thetaLong) {
      return HashOperations.count(this.hashTable_, thetaLong);
   }

   Summary[] getSummaryTable() {
      return this.summaryTable_;
   }

   public int getNominalEntries() {
      return this.nomEntries_;
   }

   public int getLgK() {
      return org.apache.datasketches.common.Util.exactLog2OfLong((long)this.nomEntries_);
   }

   public float getSamplingProbability() {
      return this.samplingProbability_;
   }

   public int getCurrentCapacity() {
      return 1 << this.lgCurrentCapacity_;
   }

   public ResizeFactor getResizeFactor() {
      return ResizeFactor.getRF(this.lgResizeFactor_);
   }

   public void trim() {
      if (this.retEntries_ > this.nomEntries_) {
         this.updateTheta();
         this.resize(this.hashTable_.length);
      }

   }

   public void reset() {
      this.empty_ = true;
      this.retEntries_ = 0;
      this.thetaLong_ = (long)((double)Long.MAX_VALUE * (double)this.samplingProbability_);
      int startingCapacity = Util.getStartingCapacity(this.nomEntries_, this.lgResizeFactor_);
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(startingCapacity);
      this.hashTable_ = new long[startingCapacity];
      this.summaryTable_ = null;
      this.rebuildThreshold_ = setRebuildThreshold(this.hashTable_, this.nomEntries_);
   }

   public CompactSketch compact() {
      if (this.getRetainedEntries() == 0) {
         return this.empty_ ? new CompactSketch((long[])null, (Summary[])null, Long.MAX_VALUE, true) : new CompactSketch((long[])null, (Summary[])null, this.thetaLong_, false);
      } else {
         long[] hashArr = new long[this.getRetainedEntries()];
         S[] summaryArr = (S[])Util.newSummaryArray(this.summaryTable_, this.getRetainedEntries());
         int i = 0;

         for(int j = 0; j < this.hashTable_.length; ++j) {
            if (this.summaryTable_[j] != null) {
               hashArr[i] = this.hashTable_[j];
               summaryArr[i] = this.summaryTable_[j].copy();
               ++i;
            }
         }

         return new CompactSketch(hashArr, summaryArr, this.thetaLong_, this.empty_);
      }
   }

   /** @deprecated */
   @Deprecated
   public byte[] toByteArray() {
      byte[][] summariesBytes = (byte[][])null;
      int summariesBytesLength = 0;
      if (this.retEntries_ > 0) {
         summariesBytes = new byte[this.retEntries_][];
         int i = 0;

         for(int j = 0; j < this.summaryTable_.length; ++j) {
            if (this.summaryTable_[j] != null) {
               summariesBytes[i] = this.summaryTable_[j].toByteArray();
               summariesBytesLength += summariesBytes[i].length;
               ++i;
            }
         }
      }

      int sizeBytes = 8;
      if (this.isInSamplingMode()) {
         sizeBytes += 4;
      }

      boolean isThetaIncluded = this.isInSamplingMode() ? (float)this.thetaLong_ < this.samplingProbability_ : this.thetaLong_ < Long.MAX_VALUE;
      if (isThetaIncluded) {
         sizeBytes += 8;
      }

      if (this.retEntries_ > 0) {
         sizeBytes += 4;
      }

      sizeBytes += 8 * this.retEntries_ + summariesBytesLength;
      byte[] bytes = new byte[sizeBytes];
      int offset = 0;
      bytes[offset++] = 1;
      bytes[offset++] = 2;
      bytes[offset++] = (byte)Family.TUPLE.getID();
      bytes[offset++] = (byte)SerializerDeserializer.SketchType.QuickSelectSketch.ordinal();
      boolean isBigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
      bytes[offset++] = (byte)((isBigEndian ? 1 << QuickSelectSketch.Flags.IS_BIG_ENDIAN.ordinal() : 0) | (this.isInSamplingMode() ? 1 << QuickSelectSketch.Flags.IS_IN_SAMPLING_MODE.ordinal() : 0) | (this.empty_ ? 1 << QuickSelectSketch.Flags.IS_EMPTY.ordinal() : 0) | (this.retEntries_ > 0 ? 1 << QuickSelectSketch.Flags.HAS_ENTRIES.ordinal() : 0) | (isThetaIncluded ? 1 << QuickSelectSketch.Flags.IS_THETA_INCLUDED.ordinal() : 0));
      bytes[offset++] = (byte)Integer.numberOfTrailingZeros(this.nomEntries_);
      bytes[offset++] = (byte)this.lgCurrentCapacity_;
      bytes[offset++] = (byte)this.lgResizeFactor_;
      if (this.samplingProbability_ < 1.0F) {
         ByteArrayUtil.putFloatLE(bytes, offset, this.samplingProbability_);
         offset += 4;
      }

      if (isThetaIncluded) {
         ByteArrayUtil.putLongLE(bytes, offset, this.thetaLong_);
         offset += 8;
      }

      if (this.retEntries_ > 0) {
         ByteArrayUtil.putIntLE(bytes, offset, this.retEntries_);
         offset += 4;
      }

      if (this.retEntries_ > 0) {
         int i = 0;

         for(int j = 0; j < this.hashTable_.length; ++j) {
            if (this.summaryTable_[j] != null) {
               ByteArrayUtil.putLongLE(bytes, offset, this.hashTable_[j]);
               offset += 8;
               System.arraycopy(summariesBytes[i], 0, bytes, offset, summariesBytes[i].length);
               offset += summariesBytes[i].length;
               ++i;
            }
         }
      }

      return bytes;
   }

   void merge(long hash, Summary summary, SummarySetOperations summarySetOps) {
      this.empty_ = false;
      if (hash > 0L && hash < this.thetaLong_) {
         int index = this.findOrInsert(hash);
         if (index < 0) {
            this.insertSummary(~index, summary.copy());
         } else {
            this.insertSummary(index, summarySetOps.union(this.summaryTable_[index], summary.copy()));
         }

         this.rebuildIfNeeded();
      }

   }

   boolean isInSamplingMode() {
      return this.samplingProbability_ < 1.0F;
   }

   void setThetaLong(long theta) {
      this.thetaLong_ = theta;
   }

   void setEmpty(boolean value) {
      this.empty_ = value;
   }

   int findOrInsert(long hash) {
      int index = HashOperations.hashSearchOrInsert(this.hashTable_, this.lgCurrentCapacity_, hash);
      if (index < 0) {
         ++this.retEntries_;
      }

      return index;
   }

   boolean rebuildIfNeeded() {
      if (this.retEntries_ <= this.rebuildThreshold_) {
         return false;
      } else {
         if (this.hashTable_.length > this.nomEntries_) {
            this.updateTheta();
            this.rebuild();
         } else {
            this.resize(this.hashTable_.length * (1 << this.lgResizeFactor_));
         }

         return true;
      }
   }

   void rebuild() {
      this.resize(this.hashTable_.length);
   }

   void insert(long hash, Summary summary) {
      int index = HashOperations.hashInsertOnly(this.hashTable_, this.lgCurrentCapacity_, hash);
      this.insertSummary(index, summary);
      ++this.retEntries_;
      this.empty_ = false;
   }

   private void updateTheta() {
      long[] hashArr = new long[this.retEntries_];
      int i = 0;

      for(int j = 0; j < this.hashTable_.length; ++j) {
         if (this.summaryTable_[j] != null) {
            hashArr[i++] = this.hashTable_[j];
         }
      }

      this.thetaLong_ = QuickSelect.select((long[])hashArr, 0, this.retEntries_ - 1, this.nomEntries_);
   }

   private void resize(int newSize) {
      long[] oldHashTable = this.hashTable_;
      S[] oldSummaryTable = (S[])this.summaryTable_;
      this.hashTable_ = new long[newSize];
      this.summaryTable_ = Util.newSummaryArray(this.summaryTable_, newSize);
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(newSize);
      this.retEntries_ = 0;

      for(int i = 0; i < oldHashTable.length; ++i) {
         if (oldSummaryTable[i] != null && oldHashTable[i] < this.thetaLong_) {
            this.insert(oldHashTable[i], oldSummaryTable[i]);
         }
      }

      this.rebuildThreshold_ = setRebuildThreshold(this.hashTable_, this.nomEntries_);
   }

   private static int setRebuildThreshold(long[] hashTable, int nomEntries) {
      return hashTable.length > nomEntries ? (int)((double)hashTable.length * (double)0.9375F) : (int)((double)hashTable.length * (double)0.5F);
   }

   protected void insertSummary(int index, Summary summary) {
      if (this.summaryTable_ == null) {
         this.summaryTable_ = (Summary[])Array.newInstance(summary.getClass(), this.hashTable_.length);
      }

      this.summaryTable_[index] = summary;
   }

   public TupleSketchIterator iterator() {
      return new TupleSketchIterator(this.hashTable_, this.summaryTable_);
   }

   static {
      DEFAULT_LG_RESIZE_FACTOR = ResizeFactor.X8.lg();
   }

   private static enum Flags {
      IS_BIG_ENDIAN,
      IS_IN_SAMPLING_MODE,
      IS_EMPTY,
      HAS_ENTRIES,
      IS_THETA_INCLUDED;
   }

   private static final class Validate {
      long myThetaLong;
      boolean myEmpty;
      int myNomEntries;
      int myLgResizeFactor;
      float mySamplingProbability;
      int myLgCurrentCapacity;
      int myRetEntries;
      int myRebuildThreshold;
      long[] myHashTable;
      Object[] mySummaryTable;

      private Validate() {
      }

      long validate(Memory mem, SummaryDeserializer deserializer) {
         Objects.requireNonNull(mem, "SourceMemory must not be null.");
         Objects.requireNonNull(deserializer, "Deserializer must not be null.");
         org.apache.datasketches.common.Util.checkBounds(0L, 8L, mem.getCapacity());
         int offset = 0;
         byte preambleLongs = mem.getByte((long)(offset++));
         byte version = mem.getByte((long)(offset++));
         byte familyId = mem.getByte((long)(offset++));
         SerializerDeserializer.validateFamily(familyId, preambleLongs);
         if (version > 2) {
            throw new SketchesArgumentException("Unsupported serial version. Expected: 2 or lower, actual: " + version);
         } else {
            SerializerDeserializer.validateType(mem.getByte((long)(offset++)), SerializerDeserializer.SketchType.QuickSelectSketch);
            byte flags = mem.getByte((long)(offset++));
            boolean isBigEndian = (flags & 1 << QuickSelectSketch.Flags.IS_BIG_ENDIAN.ordinal()) > 0;
            if (isBigEndian ^ ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
               throw new SketchesArgumentException("Endian byte order mismatch");
            } else {
               this.myNomEntries = 1 << mem.getByte((long)(offset++));
               this.myLgCurrentCapacity = mem.getByte((long)(offset++));
               this.myLgResizeFactor = mem.getByte((long)(offset++));
               org.apache.datasketches.common.Util.checkBounds(0L, (long)preambleLongs * 8L, mem.getCapacity());
               boolean isInSamplingMode = (flags & 1 << QuickSelectSketch.Flags.IS_IN_SAMPLING_MODE.ordinal()) > 0;
               this.mySamplingProbability = isInSamplingMode ? mem.getFloat((long)offset) : 1.0F;
               if (isInSamplingMode) {
                  offset += 4;
               }

               boolean isThetaIncluded = (flags & 1 << QuickSelectSketch.Flags.IS_THETA_INCLUDED.ordinal()) > 0;
               if (isThetaIncluded) {
                  this.myThetaLong = mem.getLong((long)offset);
                  offset += 8;
               } else {
                  this.myThetaLong = (long)((double)Long.MAX_VALUE * (double)this.mySamplingProbability);
               }

               int count = 0;
               boolean hasEntries = (flags & 1 << QuickSelectSketch.Flags.HAS_ENTRIES.ordinal()) > 0;
               if (hasEntries) {
                  count = mem.getInt((long)offset);
                  offset += 4;
               }

               int currentCapacity = 1 << this.myLgCurrentCapacity;
               this.myHashTable = new long[currentCapacity];

               for(int i = 0; i < count; ++i) {
                  long hash = mem.getLong((long)offset);
                  offset += 8;
                  Memory memRegion = mem.region((long)offset, mem.getCapacity() - (long)offset);
                  DeserializeResult<?> summaryResult = deserializer.heapifySummary(memRegion);
                  S summary = (S)summaryResult.getObject();
                  offset += summaryResult.getSize();
                  int index = HashOperations.hashInsertOnly(this.myHashTable, this.myLgCurrentCapacity, hash);
                  if (this.mySummaryTable == null) {
                     this.mySummaryTable = Array.newInstance(summary.getClass(), this.myHashTable.length);
                  }

                  this.mySummaryTable[index] = summary;
                  ++this.myRetEntries;
                  this.myEmpty = false;
               }

               this.myEmpty = (flags & 1 << QuickSelectSketch.Flags.IS_EMPTY.ordinal()) > 0;
               this.myRebuildThreshold = QuickSelectSketch.setRebuildThreshold(this.myHashTable, this.myNomEntries);
               return this.myThetaLong;
            }
         }
      }
   }
}
