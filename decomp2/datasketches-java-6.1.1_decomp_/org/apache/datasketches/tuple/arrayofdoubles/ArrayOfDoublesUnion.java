package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.tuple.SerializerDeserializer;
import org.apache.datasketches.tuple.Util;

public abstract class ArrayOfDoublesUnion {
   static final byte serialVersionUID = 1;
   static final int PREAMBLE_SIZE_BYTES = 16;
   static final int PREAMBLE_LONGS_BYTE = 0;
   static final int SERIAL_VERSION_BYTE = 1;
   static final int FAMILY_ID_BYTE = 2;
   static final int SKETCH_TYPE_BYTE = 3;
   static final int FLAGS_BYTE = 4;
   static final int NUM_VALUES_BYTE = 5;
   static final int SEED_HASH_SHORT = 6;
   static final int THETA_LONG = 8;
   ArrayOfDoublesQuickSelectSketch gadget_;
   long unionThetaLong_;

   ArrayOfDoublesUnion(ArrayOfDoublesQuickSelectSketch sketch) {
      this.gadget_ = sketch;
      this.unionThetaLong_ = sketch.getThetaLong();
   }

   public static ArrayOfDoublesUnion heapify(Memory srcMem) {
      return heapify(srcMem, 9001L);
   }

   public static ArrayOfDoublesUnion heapify(Memory srcMem, long seed) {
      return HeapArrayOfDoublesUnion.heapifyUnion(srcMem, seed);
   }

   public static ArrayOfDoublesUnion wrap(Memory srcMem) {
      return wrap(srcMem, 9001L);
   }

   public static ArrayOfDoublesUnion wrap(Memory srcMem, long seed) {
      return DirectArrayOfDoublesUnion.wrapUnion((WritableMemory)srcMem, seed, false);
   }

   public static ArrayOfDoublesUnion wrap(WritableMemory srcMem) {
      return wrap(srcMem, 9001L);
   }

   public static ArrayOfDoublesUnion wrap(WritableMemory srcMem, long seed) {
      return DirectArrayOfDoublesUnion.wrapUnion(srcMem, seed, true);
   }

   public void union(ArrayOfDoublesSketch tupleSketch) {
      if (tupleSketch != null) {
         Util.checkSeedHashes(this.gadget_.getSeedHash(), tupleSketch.getSeedHash());
         if (this.gadget_.getNumValues() != tupleSketch.getNumValues()) {
            throw new SketchesArgumentException("Incompatible sketches: number of values mismatch " + this.gadget_.getNumValues() + " and " + tupleSketch.getNumValues());
         } else if (!tupleSketch.isEmpty()) {
            this.gadget_.setNotEmpty();
            this.setUnionThetaLong(Math.min(Math.min(this.unionThetaLong_, tupleSketch.getThetaLong()), this.gadget_.getThetaLong()));
            if (tupleSketch.getRetainedEntries() != 0) {
               ArrayOfDoublesSketchIterator it = tupleSketch.iterator();

               while(it.next()) {
                  if (it.getKey() < this.unionThetaLong_) {
                     this.gadget_.merge(it.getKey(), it.getValues());
                  }
               }

               if (this.gadget_.getThetaLong() < this.unionThetaLong_) {
                  this.setUnionThetaLong(this.gadget_.getThetaLong());
               }

            }
         }
      }
   }

   public ArrayOfDoublesCompactSketch getResult(WritableMemory dstMem) {
      long unionThetaLong = this.unionThetaLong_;
      if (this.gadget_.getRetainedEntries() > this.gadget_.getNominalEntries()) {
         unionThetaLong = Math.min(unionThetaLong, this.gadget_.getNewThetaLong());
      }

      return (ArrayOfDoublesCompactSketch)(dstMem == null ? new HeapArrayOfDoublesCompactSketch(this.gadget_, unionThetaLong) : new DirectArrayOfDoublesCompactSketch(this.gadget_, unionThetaLong, dstMem));
   }

   public ArrayOfDoublesCompactSketch getResult() {
      return this.getResult((WritableMemory)null);
   }

   public void reset() {
      this.gadget_.reset();
      this.setUnionThetaLong(this.gadget_.getThetaLong());
   }

   public byte[] toByteArray() {
      int sizeBytes = 16 + this.gadget_.getSerializedSizeBytes();
      byte[] byteArray = new byte[sizeBytes];
      WritableMemory mem = WritableMemory.writableWrap(byteArray);
      mem.putByte(0L, (byte)1);
      mem.putByte(1L, (byte)1);
      mem.putByte(2L, (byte)Family.TUPLE.getID());
      mem.putByte(3L, (byte)SerializerDeserializer.SketchType.ArrayOfDoublesUnion.ordinal());
      mem.putLong(8L, this.unionThetaLong_);
      this.gadget_.serializeInto(mem.writableRegion(16L, mem.getCapacity() - 16L));
      return byteArray;
   }

   public static int getMaxBytes(int nomEntries, int numValues) {
      return ArrayOfDoublesQuickSelectSketch.getMaxBytes(nomEntries, numValues) + 16;
   }

   void setUnionThetaLong(long thetaLong) {
      this.unionThetaLong_ = thetaLong;
   }
}
